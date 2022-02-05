package com.example.sSqlapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper SinayesFlightDb;
    private RecyclerView recyclerView; //variable to store recycler view in activity_main.xml
    private RecyclerView.Adapter recyclerView_adapter; //adapter acts as a bridge between arraylist (data) and the recycler view
    private RecyclerView.LayoutManager layoutManager; //controls layout for the single flight items
    private static final String TAG = "MainActivity";
    private TextView userInitialsTxtview;
    private TextView userBalanceTxtview;
    private Toolbar toolbar;
    String usr_Id;


    @RequiresApi(api = Build.VERSION_CODES.O)
    //@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SinayesFlightDb = new DatabaseHelper(this);
        // View inflatedView = getLayoutInflater().inflate(R.layout.custom_toolbar, null);
        // toolbar =  inflatedView.findViewById(R.id.toolbar);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_toolbar);
        View view = getSupportActionBar().getCustomView();
        toolbar =  view.findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        userInitialsTxtview = view.findViewById(R.id.usr_initials);
        userBalanceTxtview = view.findViewById(R.id.usr_balance);

        ReceiveLoggedInUser();


        seedData(SinayesFlightDb);
        ArrayList<FlightItem> flights = GetAllAvailableFlights(SinayesFlightDb);

        //initialize RecyclerView in XML layout by id defined in activity_main.xml layout file
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true); // for performance in Recycler View

        //define LinearLayout Manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView_adapter = new FlightItemsAdapter(flights,usr_Id); //initialize adapter with list of flights as read from db

        //set Recycler View Layout manager and Adapter responsible for feeding Recycler View with list of flights
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerView_adapter);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void seedData(DatabaseHelper SinayesFlightDb){

        LocalDateTime flightDate = LocalDateTime.of(2021,11,17,15,00,00);
        System.out.println("Before formatting: " + flightDate);
        DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = flightDate.format(dateformatter);

        LocalDateTime flightDateA = LocalDateTime.of(2021,11,17,16,30,0);
        DateTimeFormatter dateformatterA = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDateA = flightDateA.format(dateformatterA);

        LocalDateTime flightDateB = LocalDateTime.of(2021,11,17,17,00,00);
        DateTimeFormatter dateformatterB = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDateB = flightDateB.format(dateformatterB);

        LocalDateTime flightDateC = LocalDateTime.of(2021,11,17,17,30,00);
        DateTimeFormatter dateformatterC = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDateC = flightDateC.format(dateformatterC);

        LocalDateTime flightDateD = LocalDateTime.of(2021,11,17,18,00,00);
        DateTimeFormatter dateformatterD = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDateD = flightDateD.format(dateformatterD);

        LocalDateTime flightDateE = LocalDateTime.of(2021,11,17,18,30,00);
        DateTimeFormatter dateformatterE = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDateE = flightDateE.format(dateformatterE);

        LocalDateTime flightDateF = LocalDateTime.of(2021,11,17,19,00,00);
        DateTimeFormatter dateformatterF = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDateF = flightDateF.format(dateformatterF);

        LocalDateTime flightDateG = LocalDateTime.of(2021,11,17,19,30,00);
        DateTimeFormatter dateformatterG = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDateG = flightDateG.format(dateformatterG);

        LocalDateTime flightDateH = LocalDateTime.of(2021,11,17,20,00,00);
        DateTimeFormatter dateformatterH = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDateH = flightDateH.format(dateformatterH);


        System.out.println("Before formatting: " + formattedDateA);
        System.out.println("Before formatting: " + formattedDateB);
        System.out.println("Before formatting: " + formattedDateC);
        System.out.println("Before formatting: " + formattedDateD);
        System.out.println("Before formatting: " + formattedDateE);
        System.out.println("Before formatting: " + formattedDateF);
        System.out.println("Before formatting: " + formattedDateG);
        System.out.println("Before formatting: " + formattedDateH);
        SinayesFlightDb.insertFlightData("Bram Fischer International Airport, Bloemfontein",
                700.00,
                formattedDate,
                "2hrs & 30 minutes",
                formattedDateB,
                40,
                "Cape Town International Airport"
        );

        SinayesFlightDb.insertFlightData(" King Shaka International Airport, Durban ",
                550.00,
                formattedDateA,
                "2hrs & 30 minutes",
                formattedDateE,
                40,
                "Cape Town International Airport"
        );

        SinayesFlightDb.insertFlightData("O.R. Tambo International Airport, Johannesburg",
                550.00,
                formattedDateA,
                "2.5hrs",
                formattedDateE,
                40,
                "East London Airport, Eastern Cape "
        );

        SinayesFlightDb.insertFlightData("King Shaka International Airport, Durban",
                400.00,
                formattedDateA,
                "3hrs",
                formattedDateF,
                40,
                "O.R. Tambo International Airport, Johannesburg"
        );

        SinayesFlightDb.insertFlightData("O.R. Tambo International Airport, Johannesburg",
                400.00,
                formattedDateA,
                "3hrs",
                formattedDateF,
                40,
                "King Shaka International Airport, Durban"
        );

        SinayesFlightDb.insertFlightData("Cape Town International Airport",
                550.00,
                formattedDateA,
                "3hrs",
                formattedDateF,
                40,
                "O.R. Tambo International Airport, Johannesburg"
        );

        SinayesFlightDb.insertFlightData("O.R. Tambo International Airport, Johannesburg",
                650.00,
                formattedDateA,
                "1.5hrs",
                formattedDateD,
                40,
                "Cape Town International Airport"
        );

        SinayesFlightDb.insertFlightData("Lanseria International Airport, Randburg",
                750.00,
                formattedDateA,
                "3hrs",
                formattedDateE,
                40,
                "King Shaka International Airport, Durban"
        );

        SinayesFlightDb.insertFlightData("O.R. Tambo International Airport, Johannesburg",
                550.00,
                formattedDateA,
                "2hrs",
                formattedDateC,
                40,
                "Cape Town International Airport"
        );

        SinayesFlightDb.insertFlightData("Cape Town International Airport",
                450.00,
                formattedDateA,
                "3.5hrs",
                formattedDateF,
                40,
                "Port Elizabeth International Airport"
        );

        SinayesFlightDb.insertFlightData("Port Elizabeth International Airport",
                699.00,
                formattedDateA,
                "3.5hrs",
                formattedDateF,
                40,
                "O.R. Tambo International Airport, Johannesburg"
        );

        SinayesFlightDb.insertFlightData("O.R. Tambo International Airport, Johannesburg",
                570.00,
                formattedDateA,
                "2.5hrs",
                formattedDateG,
                40,
                "Bram Fischer International Airport, Bloemfontein"
        );

        SinayesFlightDb.insertFlightData("Bram Fischer International Airport, Bloemfontein ",
                580.00,
                formattedDateA,
                "3hrs",
                formattedDateF,
                40,
                "King Shaka International Airport, Durban"
        );

        SinayesFlightDb.insertFlightData(" Bram Fischer International Airport, Bloemfontein ",
                370.00,
                formattedDateA,
                "3hrs",
                formattedDateF,
                40,
                "Cape Town International Airport"
        );

        SinayesFlightDb.insertFlightData(" Bram Fischer International Airport, Bloemfontein ",
                870.00,
                formattedDateA,
                "4hrs",
                formattedDateH,
                40,
                "East London Airport, Eastern Cape "
        );

        SinayesFlightDb.insertFlightData(" East London Airport, Eastern Cape ",
                870.00,
                formattedDateA,
                "4hrs",
                formattedDateG,
                40,
                "Bram Fischer International Airport, Bloemfontein "
        );

        SinayesFlightDb.insertFlightData(" East London Airport, Eastern Cape ",
                990.00,
                formattedDateA,
                "4hrs",
                formattedDateG,
                40,
                "King Shaka International Airport, Durban "
        );

        SinayesFlightDb.insertFlightData("King Shaka International Airport, Durban ",
                990.00,
                formattedDateA,
                "4hrs",
                formattedDateG,
                40,
                "East London Airport, Eastern Cape"
        );

        SinayesFlightDb.insertFlightData("King Shaka International Airport, Durban ",
                790.00,
                formattedDateA,
                "4hrs",
                formattedDateG,
                40,
                "East London Airport, Eastern Cape"
        );

        SinayesFlightDb.insertFlightData("King Shaka International Airport, Durban ",
                790.00,
                formattedDateA,
                "4hrs",
                formattedDateG,
                40,
                "East London Airport, Eastern Cape"
        );
        //---------------
    }

    public ArrayList<FlightItem> GetAllAvailableFlights(DatabaseHelper db){

        Cursor flightsRows = db.GetFlightData();
        if(flightsRows.getCount() == 0)
            return new ArrayList<FlightItem>(
                    Arrays.asList(new FlightItem(R.drawable.ic_baseline_flight_takeoff, "Grounded - Grounded", "R000.00"))
            );

        ArrayList<FlightItem> AllflightsFromDb = new ArrayList<FlightItem>();

        while(flightsRows.moveToNext())
        {
            /*
                flightsRows.getString(0); // Id
                flightsRows.getString(1); // Destination
                flightsRows.getString(2); // Price
                flightsRows.getString(3); // DepartureDateTime
                flightsRows.getString(4); // Duration
                flightsRows.getString(5); // ArrivalDateTime
                flightsRows.getString(6); // AvailableSpace
                flightsRows.getString(7); // Departure
             */


            String DepartureToDestination = flightsRows.getString(7)+" - "+flightsRows.getString(1);
            FlightItem currentFlightRow = new FlightItem(R.drawable.ic_baseline_flight_takeoff, DepartureToDestination,"R"+flightsRows.getString(2));

            currentFlightRow.setId(flightsRows.getString(0));
            currentFlightRow.setFlightDepartureTime(flightsRows.getString(3));
            currentFlightRow.setFlightArrivalTime(flightsRows.getString(5));
            currentFlightRow.setFlightDuration(flightsRows.getString(4));

            Log.d(TAG, "GetAllAvailableFlights: Reading Available Space Count From Database "+Integer.parseInt(flightsRows.getString(6)));
            currentFlightRow.setAvailableSpace(SinayesFlightDb.GetFlightSeatsLeft(currentFlightRow.getId()));
            AllflightsFromDb.add(currentFlightRow);
        }
        return AllflightsFromDb;
    }

    private void ReceiveLoggedInUser() {
        Log.d(TAG, "ReceiveLoggedInUser: Started Trying to Check Intent For Data");

        if (getIntent().hasExtra("usrId")){

            Log.d(TAG, "ReceiveLoggedInUser: Data exists, Data Read From Intent");
            usr_Id = getIntent().getStringExtra("usrId");
            DisplayUserInitials(usr_Id);
        }
    }

    private void DisplayUserInitials(String usrId) {
        Log.d(TAG, "DisplayUserInitials: About to set Circle User Initials");
        Log.d(TAG, "DisplayUserInitials: About to get User Initials From Db");
        String DbUserInitials = SinayesFlightDb.GetUserInitials(usrId);
        String UserBalance = "R"+ SinayesFlightDb.GetCustomerBalance(usrId);

        Log.d(TAG, "DisplayUserInitials: Retrieved  "+ DbUserInitials+" From Database");
        userInitialsTxtview.setText(DbUserInitials);;
        userBalanceTxtview.setText(UserBalance);
    }
}