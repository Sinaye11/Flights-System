package com.example.sSqlapplication;

import android.content.Intent;
import android.os.Debug;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FlightItemsAdapter extends RecyclerView.Adapter<FlightItemsAdapter.FlightViewHolder> {
    ArrayList<FlightItem> listOfFlights;
    String customerId;

    public static class FlightViewHolder extends RecyclerView.ViewHolder{
        public ImageView flightItemUIImage;
        public TextView flightItemUIRouteText;
        public TextView flightItemUIPriceText;
        public TextView flightTimeSlot;
        public RelativeLayout flightItemParentLayout;

        public FlightViewHolder(@NonNull View itemView) {
            super(itemView);

            // grabbing the UI elements in the UI so we can assign data to them dynamically
            //refferences to views, we will pass data to them on onBindViewHolder() method
            flightItemUIImage = itemView.findViewById(R.id.imageview);
            flightItemUIRouteText = itemView.findViewById(R.id.textview);
            flightItemUIPriceText = itemView.findViewById(R.id.textview2);
            flightTimeSlot = itemView.findViewById(R.id.flightTimeSlot);
            flightItemParentLayout = itemView.findViewById(R.id.parentLayout);
        }
    }

    //passing in the data we got from the db/ in MainActivity.java
    public FlightItemsAdapter(ArrayList<FlightItem> listOfFlights,String customerId){
        this.listOfFlights = listOfFlights;
        this.customerId = customerId;
    }

    //region

    @NonNull
    @Override
    public FlightViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //pass layout for a single flight item in list to adapter

        View vw  = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_flight_item,parent,false);
        FlightViewHolder fvh = new FlightViewHolder(vw);
        return fvh;
    }

    @Override
    public void onBindViewHolder(@NonNull FlightViewHolder holder, int position) {

        //pass 2nd current flight item in list to 2nd card item in Recycler View
        FlightItem currentFlightItem = listOfFlights.get(position);
        holder.flightItemUIImage.setImageResource(currentFlightItem.getImageResource());
        holder.flightItemUIRouteText.setText(currentFlightItem.getDepartureToDestinationStr());
        holder.flightItemUIPriceText.setText(currentFlightItem.getPrice());
        holder.flightTimeSlot.setText(currentFlightItem.getFlightTimeSlotWithoutDuration());


        holder.flightItemParentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),CustomerBookingActivity.class);
                intent.putExtra("route_text",currentFlightItem.getDepartureToDestinationStr());
                intent.putExtra("price_text",currentFlightItem.getPrice());
                intent.putExtra("Id_text",currentFlightItem.getId());
                intent.putExtra("flightTimeSlotAndDuration",currentFlightItem.getFlightTimeSlotAndDuration());
                intent.putExtra("availableseats",String.valueOf(currentFlightItem.getSeatsleft()));
                intent.putExtra("customerId", customerId);
                v.getContext().startActivity(intent);
            }
        });

    }

    //endregion

    @Override
    public int getItemCount() {
        return listOfFlights.size();
    }
}
