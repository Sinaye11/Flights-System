package com.example.sSqlapplication;

import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CustomerBookingActivity extends AppCompatActivity {

    DatabaseHelper SinayesFlightDb;
    private static final String TAG = "CustomerBookingActivity";
    private TextView pricetxtv;
    private TextView routetxtv;
    private TextView Idtxtv;
    private TextView flighttimestxtv;
    private TextView seatslefttxtv;

    private int seatsLeft;

    private Button confirmBookingBtn;
    private Button cancelBookingBtn;

    public String CustomerId;
    public String FlightId;
    public String FlightTimes;
    public ArrayAdapter adapter;
    public ListView listView;
    public List<String> allCustomers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: Booking!");
        setContentView(R.layout.activity_customer_booking);
        seatsLeft = 0;
        SinayesFlightDb = new DatabaseHelper(this);
        pricetxtv = findViewById(R.id.priceView);
        routetxtv = findViewById(R.id.routeView);
        flighttimestxtv = findViewById(R.id.booking_flighttimes);
        seatslefttxtv = findViewById(R.id.availableseats);

        receiveFlightItemData();

        allCustomers = GetAllBookedFlightCustomers(FlightId);
        adapter = new ArrayAdapter<String>(this, R.layout.customer_item_view, allCustomers);
        ListView listView = (ListView) findViewById(R.id.passengers_list);
        listView.setAdapter(adapter);

        confirmBookingBtn  = findViewById(R.id.bookingbtn);
        cancelBookingBtn  = findViewById(R.id.cancelbtn);


        confirmBookingBtn.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                //create customer booking

                String transactionMessage = SinayesFlightDb.CreateCustomerBooking(CustomerId,FlightId);
                Log.d(TAG, "onClick: Create Booking transactional Message "+transactionMessage);

                seatsLeft = SinayesFlightDb.GetFlightSeatsLeft(FlightId);
                Log.d(TAG, "onClick: Seats Left After Confirm Booking Current Count "+seatsLeft);
                //update the count of seats left

                int livePassengers = refreshListView();
                seatslefttxtv.setText(String.valueOf(seatsLeft));
                //update the count of seats left

                showTextMessage(transactionMessage);
             }
        });


        cancelBookingBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // Cancel customer booking
                String transactionMessage = SinayesFlightDb.CancelCustomerBooking(CustomerId,FlightId);
                Log.d(TAG, "onClick: Cancel transactional Message "+transactionMessage);
                seatsLeft = SinayesFlightDb.GetFlightSeatsLeft(FlightId);
                Log.d(TAG, "onClick: Seats Left After Cancel Booking :"+seatsLeft);
                int livePassengers = refreshListView();
                seatslefttxtv.setText(String.valueOf(seatsLeft));
                //update the count of seats left

                showTextMessage(transactionMessage);
            }
        });

        refreshListView();
    }

    private void receiveFlightItemData() {
        Log.d(TAG, "receiveFlightItemData: Receiving data");
        if (getIntent().hasExtra("route_text") &&
                getIntent().hasExtra("price_text") &&
                getIntent().hasExtra("Id_text") &&
                getIntent().hasExtra("availableseats") &&
                getIntent().hasExtra("flightTimeSlotAndDuration")
        ) {
            Log.d(TAG, "receiveFlightItemData: ExtractingData");
            String route = getIntent().getStringExtra("route_text");
            String price = getIntent().getStringExtra("price_text");
            FlightId = getIntent().getStringExtra("Id_text");
            CustomerId = getIntent().getStringExtra("customerId");
            seatsLeft = Integer.parseInt(getIntent().getStringExtra("availableseats"));
            FlightTimes = getIntent().getStringExtra("flightTimeSlotAndDuration");

            DisplayFlightItemData(price,route,FlightTimes);
        }
    }

    private void DisplayFlightItemData(String price, String route, String flighttimes) {

        Log.d(TAG, "DisplayFlightItemData: DisplayingDataToUi");
        pricetxtv.setText(price);
        routetxtv.setText(route);
        flighttimestxtv.setText(flighttimes);
        seatslefttxtv.setText(String.valueOf(seatsLeft));
    }

    public ArrayList<String> GetAllBookedFlightCustomers(String FlightId){

        Cursor allFlightBookedPassengers = SinayesFlightDb.GetFlightCustomers(FlightId);
        if(allFlightBookedPassengers.getCount() == 0)
            return new ArrayList<String>();

        ArrayList<String> allCustomers = new ArrayList<String>();

        while(allFlightBookedPassengers.moveToNext())
        {
            allFlightBookedPassengers.getString(0); // Id
            allFlightBookedPassengers.getString(1); // usr.Name
            allFlightBookedPassengers.getString(2); // usr.MiddleName
            allFlightBookedPassengers.getString(3); // usr.Surname

            PassengerModel passengerModel = new PassengerModel(
                    allFlightBookedPassengers.getString(1), // usr.Name
                    allFlightBookedPassengers.getString(2), // usr.MiddleName
                    allFlightBookedPassengers.getString(3), // usr.Surname
                    allFlightBookedPassengers.getString(0)
            );

            Log.d(TAG, "GetAllBookedFlightCustomers: "+ passengerModel.toString());
            allCustomers.add(passengerModel.toString());
        }
        allFlightBookedPassengers.close();
        return allCustomers;
    }

    public int refreshListView()
    {
        List<String> clientele = GetAllBookedFlightCustomers(FlightId);
        adapter.clear();
        adapter.addAll(clientele);
        adapter.notifyDataSetChanged();
        return clientele.size();
    }

    public void showTextMessage(String messageToShow){

        Toast.makeText(CustomerBookingActivity.this,messageToShow, Toast.LENGTH_LONG).show();
    }

}