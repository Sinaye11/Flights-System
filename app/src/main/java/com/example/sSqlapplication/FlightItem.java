package com.example.sSqlapplication;

public class FlightItem {

    private int imageResource;
    private String DepartureToDestinationStr;
    private String Price;
    private String Id;
    private int seatsleft;
    private String combinedFlightTime;
    private String arrivalTime;
    private String departureTime;
    private String flightDurationTime;


    public FlightItem(int imageResource, String DepartureToDestinationStr, String Price){
        this.imageResource = imageResource;
        this.DepartureToDestinationStr = DepartureToDestinationStr;
        this.Price = Price;
    }

    public int getImageResource() {
        return this.imageResource;
    }

    public String getDepartureToDestinationStr() {
        return this.DepartureToDestinationStr;
    }

    public String getPrice() {
        return this.Price;
    }

    public String getId() {
        return this.Id;
    }

    public int getSeatsleft() {
        return this.seatsleft;
    }

    public void setId(String Id) {
         this.Id = Id;
    }

    public void setAvailableSpace(int seatsleft) {
        this.seatsleft = seatsleft;
    }

    public void setFlightArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setFlightDepartureTime(String departureTime)
    {
        this.departureTime = departureTime;
    }

    public String getFlightDuration() {
        return this.flightDurationTime;
    }

    public String getFlightArrivalTime() {
        return this.arrivalTime;
    }

    public String getFlightDepartureTime()
    {
        return this.departureTime;
    }

    public void setFlightDuration(String flightDuration) {
        this.flightDurationTime = flightDuration;
    }

    public String getFlightTimeSlotAndDuration() {
        return String.format("%s - %s, %s",this.departureTime,this.arrivalTime,this.flightDurationTime);
    }

    public String getFlightTimeSlotWithoutDuration() {
        return String.format("%s - %s",this.departureTime,this.arrivalTime);
    }

}
