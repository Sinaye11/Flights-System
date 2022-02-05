package com.example.sSqlapplication;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";
    public final static String DATABASE_NAME = "Flight.db";

    //region FLIGHT TABLE
    public final static String FTable_NAME = "Flight_Table";
    public final static String FCol_1 = "Id";
    public final static String FCol_2 = "Departure";
    public final static String FCol_3 = "Price";
    public final static String FCol_4 = "DepatureDateTime";
    public final static String FCol_5 = "Duration";
    public final static String FCol_6 = "ArrivalDateTime";
    public final static String FCol_7 = "AvailableSpace";
    public final static String FCol_8 = "Destination";

    //endregion

    //region CUSTOMER TABLE
    public final static String CTable_NAME = "users";
    public final static String CCol_1 = "Id";
    public final static String CCol_2 = "Name";
    public final static String CCol_3 = "MiddleName";
    public final static String CCol_4 = "Surname";
    public final static String CCol_5 = "Email";
    public final static String CCol_6 = "PhoneNumber";
    public final static String CCol_7 = "IDNumber";
    public final static String CCol_8 = "Password";
    public final static String CCol_9 = "Username";

    //endregion


    //region CUSTOMER_FLIGHT TABLE
    public final static String CFTable_NAME = "CustomerFlight_Table";
    public final static String CFCol_1 = "Id";
    public final static String CFCol_2 = "Customer_Id";
    public final static String CFCol_3 = "Flight_Id";
    public final static String CFCol_4 = "FlightStatus_Id";
    //endregion


    //region FLLIGHT STATUS TABLE
    public final static String FSTable_NAME = "FlightStatus_Table";
    public final static String FSCol_1 = "Id";
    public final static String FSCol_2 = "FlightStatusName";
    //endregion

    //region Account Balance  TABLE
    public final static String Account_Table = "CustomerAccountBalance";
    public final static String CustomerAccountBalance_Id = "Id";
    public final static String CustomerID = "Customer_Id";
    public final static String CustomerBalance = "Balance";
    //endregion

    //region Receipt  TABLE
    public final static String Receipt_Table = "Receipt";
    public final static String Receipt_ID = "Id";
    public final static String Customer_ID = "Customer_Id";
    public final static String Flight_ID = "Flight_Id";
    public final static String PaymentDate = "PaymentDate";
    public final static String ReceiptNarration = "Narration";
    //endregion

    public final static int MAX_SEATS = 40;


    public DatabaseHelper(@Nullable Context context) {
        //whenever constructor is called, your datatabase is created
        super(context, DATABASE_NAME, null, 1);
        onCreate(getWritableDatabase());
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // this executes the string query which a sql create Table Tablename

        //Region flight table
        db.execSQL("CREATE TABLE IF NOT EXISTS "+ FTable_NAME +
                "("+FCol_1+" INTEGER PRIMARY KEY AUTOINCREMENT" +
                ","+FCol_2+" TEXT" +
                ","+FCol_3+" DOUBLE" +
                ","+FCol_4+" TEXT" +
                ","+FCol_5+" TEXT" +
                ","+FCol_6+" TEXT" +
                ","+FCol_7+" INTEGER"+
                ","+FCol_8+" TEXT)"
        );



        //customer table
        db.execSQL("CREATE TABLE IF NOT EXISTS "+ CTable_NAME +
                "("+CCol_1+" INTEGER PRIMARY KEY AUTOINCREMENT" +
                ","+CCol_2+" TEXT" +
                ","+CCol_3+" TEXT" +
                ","+CCol_4+" TEXT" +
                ","+CCol_5+" TEXT" +
                ","+CCol_6+" TEXT" +
                ","+CCol_7+" TEXT" +
                ","+CCol_8+" TEXT" +
                ","+CCol_9+" TEXT)"
        );

        //customerFlight table

        /* String ROW1 = "INSERT INTO " + CTable_NAME  + " ("
                + CCol_2 + ", " + CCol_4 + ", "
                + CCol_8 + ", " + CCol_9 + ", "
                + CCol_5 + ") Values ('Cash', '', '', '0', '')";
        */

        String CUSTOMER1 = "INSERT INTO "+ CTable_NAME + "("+ CCol_2 +", "+ CCol_4+", "+CCol_9 +", "+CCol_8+")" +
        "SELECT * FROM (SELECT 'Calvin', 'Klein', 'Calvin.Klein','ckleinpassword') AS tmp" +
        " WHERE NOT EXISTS (" +
                "SELECT "+ CCol_2 +" FROM "+ CTable_NAME +" WHERE "+CCol_2 +" = 'Calvin'" +
        ") LIMIT 1";
        db.execSQL(CUSTOMER1);

        String CUSTOMER2 = "INSERT INTO "+ CTable_NAME + "("+ CCol_2 +", "+ CCol_4+", "+CCol_9 +", "+CCol_8+")" +
                "SELECT * FROM (SELECT 'Adriano', 'Carvalho', 'Adriano.Carvalho','acarvhalopassword') AS tmp" +
                " WHERE NOT EXISTS (" +
                "SELECT "+ CCol_2 +" FROM "+ CTable_NAME +" WHERE "+CCol_2 +" = 'Adriano'" +
                ") LIMIT 1";
        db.execSQL(CUSTOMER2);

        String CUSTOMER3 = "INSERT INTO "+ CTable_NAME + "("+ CCol_2 +", "+ CCol_4+", "+CCol_9 +", "+CCol_8+")" +
                "SELECT * FROM (SELECT 'Tommy', 'Vercetti', 'Tommy.Vercetti','tvercettipassword') AS tmp" +
                " WHERE NOT EXISTS (" +
                "SELECT "+ CCol_2 +" FROM "+ CTable_NAME +" WHERE "+CCol_2 +" = 'Tommy'" +
                ") LIMIT 1";
        db.execSQL(CUSTOMER3);

        String CUSTOMER4 = "INSERT INTO "+ CTable_NAME + "("+ CCol_2 +", "+ CCol_4+", "+CCol_9 +", "+CCol_8+")" +
                "SELECT * FROM (SELECT 'Zola', 'Seven', 'Zola.Seven','zsevenpassword') AS tmp" +
                " WHERE NOT EXISTS (" +
                "SELECT "+ CCol_2 +" FROM "+ CTable_NAME +" WHERE "+CCol_2 +" = 'Zola'" +
                ") LIMIT 1";
        db.execSQL(CUSTOMER4);

        String CUSTOMER5 = "INSERT INTO "+ CTable_NAME + "("+ CCol_2 +", "+ CCol_4+", "+CCol_9 +", "+CCol_8+")" +
                "SELECT * FROM (SELECT 'Sbu', 'Kola', 'Sbu.Kola','skolapassword') AS tmp" +
                " WHERE NOT EXISTS (" +
                "SELECT "+ CCol_2 +" FROM "+ CTable_NAME +" WHERE "+CCol_2 +" = 'Sbu'" +
                ") LIMIT 1";
        db.execSQL(CUSTOMER5);

        //flightStatus table
        db.execSQL("CREATE TABLE IF NOT EXISTS "+ FSTable_NAME +
                "("+FSCol_1+" INTEGER PRIMARY KEY AUTOINCREMENT" +
                ","+FSCol_2+" TEXT)"
        );

        //customerFlight table
        db.execSQL("CREATE TABLE IF NOT EXISTS "+ CFTable_NAME +
                "("+CFCol_1+" INTEGER PRIMARY KEY AUTOINCREMENT" +
                ","+CFCol_2+" INTEGER" +
                ","+CFCol_3+" INTEGER" +
                ","+CFCol_4+" INTEGER" +
                ",FOREIGN KEY ("+CFCol_2+") REFERENCES "+ CTable_NAME +" (" + CFCol_2 +")" +
                ",FOREIGN KEY ("+CFCol_3+") REFERENCES "+ FTable_NAME +" (" + CFCol_3 +")" +
                ",FOREIGN KEY ("+CFCol_4+") REFERENCES "+ FSTable_NAME +" (" + CFCol_4 +")" +")"
        );

        //customer Account Balance table
        db.execSQL("CREATE TABLE IF NOT EXISTS "+ Account_Table +
                "("+CustomerAccountBalance_Id+" INTEGER PRIMARY KEY AUTOINCREMENT" +
                ","+CustomerID+" INTEGER" +
                ","+CustomerBalance+" DOUBLE" +
                ",FOREIGN KEY ("+CustomerID+") REFERENCES "+ CTable_NAME +" (" + CustomerID +")" +")"
        );

        String loadCalvinFunds = "INSERT INTO "+ Account_Table + "("+ CustomerID +", "+ CustomerBalance +")" +
                "SELECT * FROM (SELECT 1, 500.89) AS tmp" +
                " WHERE NOT EXISTS (" +
                "SELECT "+ CustomerID +" FROM "+ Account_Table +" WHERE "+ CustomerID +" = 1" +
                ") LIMIT 1";
        db.execSQL(loadCalvinFunds);

        String loadAdrianoFunds = "INSERT INTO "+ Account_Table + "("+ CustomerID +", "+ CustomerBalance +")" +
                "SELECT * FROM (SELECT 2, 700.89) AS tmp" +
                " WHERE NOT EXISTS (" +
                "SELECT "+ CustomerID +" FROM "+ Account_Table +" WHERE "+ CustomerID +" = 2" +
                ") LIMIT 1";
        db.execSQL(loadAdrianoFunds);

        String loadTommyFunds = "INSERT INTO "+ Account_Table + "("+ CustomerID +", "+ CustomerBalance +")" +
                "SELECT * FROM (SELECT 3, 880.89) AS tmp" +
                " WHERE NOT EXISTS (" +
                "SELECT "+ CustomerID +" FROM "+ Account_Table +" WHERE "+ CustomerID +" = 3" +
                ") LIMIT 1";
        db.execSQL(loadTommyFunds);

        String loadZolaFunds = "INSERT INTO "+ Account_Table + "("+ CustomerID +", "+ CustomerBalance +")" +
                "SELECT * FROM (SELECT 4, 575.89) AS tmp" +
                " WHERE NOT EXISTS (" +
                "SELECT "+ CustomerID +" FROM "+ Account_Table +" WHERE "+ CustomerID +" = 4" +
                ") LIMIT 1";
        db.execSQL(loadZolaFunds);

        String loadSbuFunds = "INSERT INTO "+ Account_Table + "("+ CustomerID +", "+ CustomerBalance +")" +
                "SELECT * FROM (SELECT 5, 900.89) AS tmp" +
                " WHERE NOT EXISTS (" +
                "SELECT "+ CustomerID +" FROM "+ Account_Table +" WHERE "+ CustomerID +" = 5" +
                ") LIMIT 1";
        db.execSQL(loadSbuFunds);


        //Receipt table
        db.execSQL("CREATE TABLE IF NOT EXISTS "+ Receipt_Table +
                "("+Receipt_ID+" INTEGER PRIMARY KEY AUTOINCREMENT" +
                ","+Customer_ID+" INTEGER" +
                ","+Flight_ID+" INTEGER" +
                ","+ReceiptNarration+" TEXT" +
                ","+PaymentDate+" TEXT" +
                ",FOREIGN KEY ("+Customer_ID+") REFERENCES "+ CTable_NAME +" (" + Customer_ID +")" +
                ",FOREIGN KEY ("+Flight_ID+") REFERENCES "+ FTable_NAME +" (" + Flight_ID +")" +")"
        );

        String loadReceipt = "INSERT INTO "+ Receipt_Table + "("+ Customer_ID +", "+ Flight_ID +", "+ ReceiptNarration +")" +
                "SELECT * FROM (SELECT 1, 2, 'Hello Receipt') AS tmp" +
                " WHERE NOT EXISTS (" +
                "SELECT "+ Customer_ID +" FROM "+ Receipt_Table +" WHERE "+ Customer_ID +" = 1" +
                ") LIMIT 1";
        db.execSQL(loadReceipt);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // when upgrading database drop all tables
        db.execSQL("DROP TABLE IF EXISTS "+ CFTable_NAME );
        db.execSQL("DROP TABLE IF EXISTS "+ CTable_NAME );
        db.execSQL("DROP TABLE IF EXISTS "+ FTable_NAME );
        db.execSQL("DROP TABLE IF EXISTS "+ FSTable_NAME);

        //pass this database instance
        onCreate(db);
    }

    public boolean insertFlightData(String Destination, Double Price, String DepartureDateTime, String Duration,
                                    String ArrivalDateTime, int AvailableSpace, String Departure ){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FCol_2, Destination);
        values.put(FCol_3, Price);
        values.put(FCol_4, DepartureDateTime);
        values.put(FCol_5, Duration);
        values.put(FCol_6, ArrivalDateTime);
        values.put(FCol_7, AvailableSpace);
        values.put(FCol_8, Departure);

        Log.d(TAG, "insertFlightData - : "+ DepartureDateTime);
        Log.d(TAG, "insertFlightData --- : "+ ArrivalDateTime);

        long result = db.insert(FTable_NAME, null, values);
        db.close();
       return result!=-1;
    }

    public Cursor GetFlightData()
    {
        SQLiteDatabase db  = this.getWritableDatabase();
        Cursor resultCursor = db.rawQuery("SELECT * FROM "+ FTable_NAME,null );
        return resultCursor;
    }


    public Boolean insertData(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Username", username);
        contentValues.put("Password", password);
        long result = MyDB.insert("users", null,contentValues);

        if (result==-1) return false;
        else
            return true;

    }
    public Boolean checkusername(String _username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where Username = ?", new String[] {_username});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public int checkusernamepassword(String _username, String _password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Log.d(TAG, "checkusernamepassword: UserName"+_username+" Password "+_password);
        Cursor cursor = MyDB.rawQuery("Select * from users where Username = ? and Password = ?", new String[] {_username, _password});
        Log.d(TAG, "checkusernamepassword: cursor returned 0" + cursor.toString());

        if (cursor.moveToFirst()) {
            Log.d(TAG, "checkusernamepassword: cursor returned 1 " + cursor.toString());
            return cursor.getInt(0);
        }
        return -1;
    }

    public String GetUserInitials(String UserId){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * FROM "+ CTable_NAME  + " WHERE "+ CCol_1 +"= ?", new String[] {UserId});

        if (cursor.moveToFirst()){

            String usrId = cursor.getString(0);
            String FirstName =  cursor.getString(1);
            String LastName = cursor.getString(3);
            Log.d(TAG, "GetUserInitials: UsrId :"+ usrId +", FirstName :"+ FirstName+ ", LastName "+ LastName);

            String fullUserInitials = (FirstName != null && LastName != null) ?
                    String.format("%s%s",FirstName.substring(0,1).toUpperCase(),LastName .substring(0,1).toUpperCase()) :
                    (FirstName != null && LastName == null) ?
                            String.format("%s",FirstName.substring(0,1).toUpperCase()) :
                            (FirstName == null && LastName != null) ?
                                    String.format("%s",LastName.substring(0,1).toUpperCase()) :
                                    String.format("%s",usrId);


            return fullUserInitials;
        }

        else
            return "ANON.";
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public String CreateCustomerBooking(String customerId, String FlightId){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Log.d(TAG, "createCustomerBooking: Getting Db Instance");
        Log.d(TAG, "createCustomerBooking: Customer ID "+customerId);
        Log.d(TAG, "createCustomerBooking: Flight ID "+ FlightId);

        Cursor Customercursor = MyDB.rawQuery("SELECT * FROM "+ CTable_NAME +" WHERE "+ CCol_1+" = ?", new String[] {customerId});
        Cursor CustomerAccountBalanceQuery = MyDB.rawQuery("SELECT * FROM "+ Account_Table +" WHERE "+ Customer_ID+" = ?", new String[] {customerId});
        Cursor cursor = MyDB.rawQuery("SELECT * FROM FLight_Table WHERE "+ FCol_1+" = ?", new String[] {FlightId});

        if(cursor.moveToFirst() && ( Customercursor.moveToFirst() && CustomerAccountBalanceQuery.moveToFirst() ) ){

            int seatsLeft = Integer.parseInt(cursor.getString(6));
            if(seatsLeft == 0){
                return "FULLYBOOKED";
            }

            if(Double.parseDouble(CustomerAccountBalanceQuery.getString(2)) <  Double.parseDouble(cursor.getString(2)) ){
                return "INSUFFICIENT FUNDS";
            }

            ContentValues contentValues = new ContentValues();
            contentValues.put(CFCol_2, customerId);
            contentValues.put(CFCol_3, FlightId);

            long result = MyDB.insert(CFTable_NAME, null,contentValues);

            ContentValues updatedSeatsCount = new ContentValues();
            updatedSeatsCount.put(FCol_7, (seatsLeft-1));
            String whereFlightId = FCol_1+"=?";
            String[] whereArgs = new String[] { FlightId };

            long  updatedFlightPassengerCount = MyDB.update(FTable_NAME,updatedSeatsCount,whereFlightId,whereArgs);
            if(updatedFlightPassengerCount == -1 ) return "ERROR UPDATING FLIGHT PASSENGER COUNT";
            //MyDB.execSQL("UPDATE "+ FTable_NAME + " SET "+FCol_7 +" = "+ (seatsLeft-1) + " WHERE "+ FCol_1+ "= ?", new String[] {FlightId});

            //Credit the User back their money
            Cursor resultCursor = MyDB.rawQuery("SELECT accBalance.Balance  FROM "+ Account_Table +" accBalance WHERE "+ CustomerID +" = ?",new String[]{customerId} );
            double balance = 0;
            double flightCost = cursor.getDouble(2);
            System.out.println("FLIGHT COST 22:13 "+ flightCost);
            if(null != resultCursor)
                if(resultCursor.getCount() > 0){
                    resultCursor.moveToFirst();
                    balance = Double.parseDouble(resultCursor.getString(0));
                    System.out.println("CUSTOMER BALANCE 22:13 "+ balance );
                    balance -= flightCost;
                    System.out.println("CUSTOMER BALANCE 22:13 "+ balance );

                    System.out.println(flightCost);
                    System.out.println(flightCost);
                    System.out.println(flightCost);

                    ContentValues debitBack = new ContentValues();
                    debitBack.put(CustomerBalance, balance );
                    String whereCustomerId = CustomerID+"=?";
                    String[] matchesArgsCustomerId = new String[] { customerId };
                    long  updatedCustomerBalance = MyDB.update(Account_Table,debitBack,whereCustomerId,matchesArgsCustomerId);
                    if(updatedCustomerBalance ==-1) return "ERROR DEBITING USER ACCOUNT";
                }

            if (result==-1) return "CUSTOMERBOOKINGERROR";
            else{

                String ReceiptcustomerName  = Customercursor.getString(1);
                String ReceiptFlightName  = String.format("%s - %s %s", cursor.getString(1), cursor.getString(7) ,cursor.getString(4));
                String ReceiptPaymentMade  = cursor.getString(2);

                String narration  = String.format("Customer: %s , Made Payment of : %s for Flight : %s",ReceiptcustomerName,ReceiptPaymentMade,ReceiptFlightName);

                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                String currentDate = dtf.format(now);

                ContentValues assembledReceiptRecord = new ContentValues();
                assembledReceiptRecord.put(Customer_ID, customerId);
                assembledReceiptRecord.put(Flight_ID, FlightId);
                assembledReceiptRecord.put(ReceiptNarration, narration);
                assembledReceiptRecord.put(PaymentDate, currentDate);

                long receiptStatus = MyDB.insert(Receipt_Table, null,assembledReceiptRecord);
                if(receiptStatus == -1) return "RECEIPT COMMITING ERROR";

                try(FileWriter fw = new FileWriter(Customercursor.getString(1)+".txt", true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    PrintWriter out = new PrintWriter(bw))
                {
                    out.println("MADE PAYMENT : "+ String.format("On Day %s Customer: %s , Made Payment of : %s for Flight : %s",ReceiptcustomerName,ReceiptPaymentMade,ReceiptFlightName, currentDate));
                } catch (IOException e) {
                    //exception handling left as an exercise for the reader
                }

                cursor.close();
                Customercursor.close();
                CustomerAccountBalanceQuery.close();

                return "CUSTOMERBOOKINGSUCCESSFUL";
            }

        }
        return "FLIGHTNOTFOUND";
    }

    public int GetFlightSeatsLeft(String FlightId){

        //SQLiteDatabase MyDB = this.getWritableDatabase();
        //Cursor cursor = MyDB.rawQuery("SELECT COUNT(*) AS 'ActiveBookings' FROM "+ CFTable_NAME + " WHERE "+ FCol_1+ "= ?", new String[] {FlightId});
        //cursor.moveToFirst();
        //System.out.println(cursor.getInt(0));
        //return MAX_SEATS - Integer.parseInt(cursor.getString(cursor.getColumnIndex("ActiveBookings")));
        //return MAX_SEATS - cursor.getInt(0);
        //return 0;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT COUNT (*) FROM " + CFTable_NAME + " WHERE " + CFCol_3 + "=?",
                new String[] { FlightId });
        int count = 0;
        int diff = 0;
        if(null != cursor)
            if(cursor.getCount() > 0){
                cursor.moveToFirst();
                count = cursor.getInt(0);
                diff = MAX_SEATS - count;
            }
        cursor.close();
        System.out.println(diff);
        db.close();
        return diff;
    }

    public String CancelCustomerBooking(String customerId, String FlightId){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Log.d(TAG, "CancelCustomerBooking: ");

        Cursor cursor = MyDB.rawQuery("SELECT * FROM "+ FTable_NAME + " WHERE "+ FCol_1+ "= ?", new String[] {FlightId});
        if(cursor.moveToFirst()){
            int seatsLeft = Integer.parseInt(cursor.getString(6));
            Log.d(TAG, "CancelCustomerBooking: seatsleft "+ seatsLeft);
            if(seatsLeft == 40){
                return "FLIGHTEMPTY";
            }

            // MyDB.execSQL("DELETE FROM "+ CFTable_NAME + " WHERE "+ CFCol_2+ "= ? AND "+CFCol_3+"= ?", new String[] {customerId, FlightId});
            String where = CFCol_3+"=? AND "+CFCol_2+"=?";
            String[] matchingArgs = new String[] { FlightId, customerId };
            long  resultAfterBookingDelete = MyDB.delete(CFTable_NAME, where, matchingArgs);
            if(resultAfterBookingDelete==-1) return "ERROR REVERTING BOOKING";

            ContentValues updatedSeatsCount = new ContentValues();
            updatedSeatsCount.put(FCol_7, (seatsLeft+1));
            String whereFlightId = FCol_1+"=?";
            String[] matchesArgsFlightId = new String[] { FlightId };
            long  updatedFlightPassengerCount = MyDB.update(FTable_NAME,updatedSeatsCount,whereFlightId,matchesArgsFlightId);
            if(updatedFlightPassengerCount==-1) return "ERROR INCREASING FLIGHT AVAILABLE SEATS";

            // MyDB.execSQL("UPDATE "+ FTable_NAME + " SET "+FCol_7 +" = "+ (seatsLeft+1) + " WHERE "+ FCol_1+ "= ?", new String[] {FlightId});

            //Credit the User back their money
            Cursor resultCursor = MyDB.rawQuery("SELECT accBalance.Balance  FROM "+ Account_Table +" accBalance WHERE "+ CustomerID +" = ?",new String[]{customerId} );
            double balance = 0;
            double flightCost = Double.parseDouble(cursor.getString(2));
            if(null != resultCursor)
                if(resultCursor.getCount() > 0){
                    resultCursor.moveToFirst();
                    balance = Double.parseDouble(resultCursor.getString(0));
                    balance += flightCost;

                    ContentValues creditBack = new ContentValues();
                    creditBack.put(CustomerBalance, balance );
                    String whereCustomerId = CustomerID+"=?";
                    String[] matchesArgsCustomerId = new String[] { customerId };
                    long  updatedCustomerBalance = MyDB.update(Account_Table,creditBack,whereCustomerId,matchesArgsCustomerId);
                    if(updatedCustomerBalance ==-1) return "ERROR CREDITING USER ACCOUNT";
                }
            return "BOOKINGCANCELLED";
        }
        return "FLIGHTNOTFOUND";
    }

    public Cursor GetFlightCustomers(String FlightId){

        SQLiteDatabase db  = this.getWritableDatabase();
        Cursor resultCursor = db.rawQuery("SELECT usr.Id, usr.Name,usr.MiddleName,usr.Surname  FROM "+ CFTable_NAME +" cftable"
                +" JOIN "+CTable_NAME+" usr ON usr.Id = cftable.Customer_Id"
                +" WHERE "+CFCol_3+" = ?",new String[]{FlightId} );
        return resultCursor;
    }

    public String GetCustomerBalance(String UsrId){

        SQLiteDatabase db  = this.getWritableDatabase();
        Cursor resultCursor = db.rawQuery("SELECT accBalance.Balance  FROM "+ Account_Table +" accBalance WHERE "+ CustomerID +" = ?",new String[]{UsrId} );
        double balance = 0;
        if(null != resultCursor)
            if(resultCursor.getCount() > 0){
                resultCursor.moveToFirst();
                balance = Double.parseDouble(resultCursor.getString(0));
            }
        resultCursor.close();
        return String.valueOf(balance);
    }
}