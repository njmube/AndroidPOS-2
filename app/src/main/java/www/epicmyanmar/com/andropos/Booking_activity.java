package www.epicmyanmar.com.andropos;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import CustomControls.CustomAdapter;
import CustomControls.TableListView_Adapter;
import entity.Table;
import model.SpinnerModel;
import util.ServiceHandler;

public class Booking_activity extends ActionBarActivity {
    private int year;
    private int month;
    private int day;


    private int hour;
    private int minute;

    EditText txt_startdate;
    EditText txt_starttime;
    EditText txt_endtime;
    EditText txt_itemcount;
    EditText txt_customer_name;
    EditText txt_phoneno;
    ImageButton btn_datepicker;
    ImageButton btn_timepicker;
    ImageButton btn_endtimepicker;
    ImageButton btn_book;
    Button btn_addtolist;


    TextView lbl_count;
    TextView lbl_itemname;


    Spinner food_spinner;

    static final int DATE_PICKER_ID = 1111;
    static final int TIME_DIALOG_ID = 2222;
    static final int END_TIME_DIALOG_ID=3333;

    private ProgressDialog pDialog;

    Booking_activity activity=null;
    public ArrayList<SpinnerModel> CustomListViewValuesArr = new ArrayList<SpinnerModel>();

    public static  HashMap<String,String> keyvaluelist=new HashMap<String,String>();
    //static String to post
    public static String BookingDate;
    public static String StartTime;
    public static String EndTime;
    public static String CustomerName;
    public static String PhoneNo;

    public static  String Food_name=null;
    public static  String Item_Count=null;
    CustomAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_activity);

        txt_startdate=(EditText) findViewById(R.id.txt_startdate);

        txt_itemcount=(EditText) findViewById(R.id.txt_itemcount);
        txt_customer_name=(EditText) findViewById(R.id.txt_customer_name);
        txt_phoneno=(EditText) findViewById(R.id.txt_phoneno);


        btn_datepicker=(ImageButton) findViewById(R.id.btn_datepicker);
        btn_timepicker=(ImageButton) findViewById(R.id.btn_timepicker);

        btn_endtimepicker=(ImageButton) findViewById(R.id.btn_endtimepicker);
        btn_addtolist=(Button) findViewById(R.id.btn_addtoList);
        btn_book=(ImageButton) findViewById(R.id.btn_book);


        txt_starttime=(EditText) findViewById(R.id.txt_starttime);
        txt_endtime=(EditText) findViewById(R.id.txt_endtime);

        lbl_itemname=(TextView) findViewById(R.id.lbl_itemname);
        lbl_count=(TextView) findViewById(R.id.lbl_count);




        final Calendar c = Calendar.getInstance();
        year  = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day   = c.get(Calendar.DAY_OF_MONTH);


        hour = c.get(Calendar.HOUR_OF_DAY);
        // Current Minute
        minute = c.get(Calendar.MINUTE);



        //add food spinner to select user choice
        food_spinner=(Spinner) findViewById(R.id.food_spin);




        setdata();

        activity  = this;
        adapter = new CustomAdapter(activity, R.layout.spinner_rows, CustomListViewValuesArr);
        food_spinner.setAdapter(adapter);


        add_listener();

        food_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View v, int position, long id) {
                // your code here

                // Get selected row data to show on screen
                String Food    = ((TextView) v.findViewById(R.id.company)).getText().toString();
                String Food_description = ((TextView) v.findViewById(R.id.sub)).getText().toString();

                String OutputMsg = "Selected Food : \n\n"+Food+"\n"+Food_description;



//                Toast.makeText(
//                        getApplicationContext(),OutputMsg, Toast.LENGTH_LONG).show();

                Food_name=Food;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });





    }

    private void setdata(){

        final SpinnerModel spinmodel1 = new SpinnerModel();
        final SpinnerModel spinmodel2 = new SpinnerModel();
        final SpinnerModel spinmodel3 = new SpinnerModel();
        final SpinnerModel spinmodel4 = new SpinnerModel();
        //need to change company name
        spinmodel1.setCompanyName("Burger");
        spinmodel1.setDescription("Jumbo Burger");

        spinmodel2.setCompanyName("Sandwich");
        spinmodel2.setDescription("Salad Sand Wich");

        spinmodel3.setCompanyName("Muffin");
        spinmodel3.setDescription("good Muffin");

        spinmodel4.setCompanyName("Cookies");
        spinmodel4.setDescription("Ginger cookies");

        CustomListViewValuesArr.add(spinmodel1);
        CustomListViewValuesArr.add(spinmodel2);
        CustomListViewValuesArr.add(spinmodel3);
        CustomListViewValuesArr.add(spinmodel4);

    }

    private void Confirm(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage(R.string.dialog_message);
        builder.setTitle(R.string.dialog_title);

// 2. Chain together various setter methods to set the dialog characteristics
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
                Toast.makeText(getBaseContext(),"Booking Has been success Fully Reserved",Toast.LENGTH_SHORT).show();
                Intent i=new Intent(getApplicationContext(),Table_activity.class);
                startActivity(i);
                overridePendingTransition(R.anim.anim_out_y,R.anim.anim_in_y);

            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
            }
        });

// 3. Get the AlertDialog from create()
        AlertDialog dialog = builder.create();
        dialog.show();
    }






    private View.OnClickListener clk_listener =new View.OnClickListener(){

        public void onClick(View v){
            switch (v.getId()){
                case R.id.btn_datepicker:
                    Toast.makeText(getBaseContext(),"date picker",Toast.LENGTH_SHORT).show();
                    showDialog(DATE_PICKER_ID);
                    break;
                case R.id.btn_timepicker:
                    Toast.makeText(getBaseContext(),"Time Picker",Toast.LENGTH_SHORT).show();
                    showDialog(TIME_DIALOG_ID);
                    break;
                case R.id.btn_endtimepicker:
                    Toast.makeText(getBaseContext(),"End Time Picker",Toast.LENGTH_SHORT).show();
                    showDialog(END_TIME_DIALOG_ID);
                    break;
                case R.id.btn_addtoList:
                    Item_Count=txt_itemcount.getText().toString();
                    Toast.makeText(getBaseContext(),Food_name + Item_Count,Toast.LENGTH_SHORT).show();

                    lbl_count.append("\n"+Item_Count);
                    lbl_itemname.append("\n"+Food_name);
                    keyvaluelist.put(Food_name,Item_Count);


                    break;
                case R.id.btn_book:


                    CustomerName=txt_customer_name.getText().toString();
                    PhoneNo=txt_phoneno.getText().toString();
                    BookingDate=txt_startdate.getText().toString();
                    StartTime=txt_starttime.getText().toString();
                    EndTime=txt_endtime.getText().toString();

                    Confirm();
                    // 1. Instantiate an AlertDialog.Builder with its constructor


                   // new GetContacts().execute();

                    break;



            }
        }
    };

    public void add_listener(){

        btn_datepicker.setOnClickListener(clk_listener);
        btn_timepicker.setOnClickListener(clk_listener);
        btn_endtimepicker.setOnClickListener(clk_listener);
        btn_addtolist.setOnClickListener(clk_listener);
        btn_book.setOnClickListener(clk_listener);

    }



    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_PICKER_ID:

                return new DatePickerDialog(this, pickerListener, year, month,day);
            case TIME_DIALOG_ID:
                return new TimePickerDialog(this,timePickerListener,hour,minute,false);
            case END_TIME_DIALOG_ID:
                return new TimePickerDialog(this,endtimePickerListener,hour,minute,false);
        }
        return null;
    }

    private TimePickerDialog.OnTimeSetListener timePickerListener = new TimePickerDialog.OnTimeSetListener() {


        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minutes) {
            // TODO Auto-generated method stub
            hour   = hourOfDay;
            minute = minutes;

            updateTime(hour,minute,txt_starttime);

        }

    };
    private TimePickerDialog.OnTimeSetListener endtimePickerListener = new TimePickerDialog.OnTimeSetListener() {


        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minutes) {
            // TODO Auto-generated method stub
            hour   = hourOfDay;
            minute = minutes;

            updateTime(hour,minute,txt_endtime);

        }

    };


    private static String utilTime(int value) {

        if (value < 10)
            return "0" + String.valueOf(value);
        else
            return String.valueOf(value);
    }

    // Used to convert 24hr format to 12hr format with AM/PM values
    private void updateTime(int hours, int mins,EditText parm) {

        String timeSet = "";
        if (hours > 12) {
            hours -= 12;
            timeSet = "PM";
        } else if (hours == 0) {
            hours += 12;
            timeSet = "AM";
        } else if (hours == 12)
            timeSet = "PM";
        else
            timeSet = "AM";


        String minutes = "";
        if (mins < 10)
            minutes = "0" + mins;
        else
            minutes = String.valueOf(mins);

        // Append in a StringBuilder
        String aTime = new StringBuilder().append(hours).append(':')
                .append(minutes).append(" ").append(timeSet).toString();

        parm.setText(aTime);//parameter end Time
    }

    private DatePickerDialog.OnDateSetListener pickerListener = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        @Override
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {

            year  = selectedYear;
            month = selectedMonth;
            day   = selectedDay;

            // Show selected date
            txt_startdate.setText(new StringBuilder().append(month + 1)
                    .append("-").append(day).append("-").append(year)
                    .append(" "));

        }
    };









    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.booking_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

   //################ ASYNC TASK doing #######################################
   private class GetContacts extends AsyncTask<Void, Void, ArrayList<Table>> {

       @Override
       protected void onPreExecute() {
           pDialog = new ProgressDialog(Booking_activity.this);
           pDialog.setMessage("Please wait...");
           pDialog.setCancelable(false);
           pDialog.show();

           super.onPreExecute();

       }




       JSONArray table_list = null;// tables list to bind
       ArrayList<Table> arrlist_table = new ArrayList<Table>();
       @Override
       protected ArrayList<Table> doInBackground(Void... arg0) {
           ServiceHandler sh = new ServiceHandler();
           List<NameValuePair> namevaluepair=new ArrayList<NameValuePair>();
           namevaluepair.add(new BasicNameValuePair("bdate",BookingDate));
           namevaluepair.add(new BasicNameValuePair("btimefrom",StartTime));
           namevaluepair.add(new BasicNameValuePair("btimeto",EndTime));
           namevaluepair.add(new BasicNameValuePair("table_id","3"));
           namevaluepair.add(new BasicNameValuePair("customer_name",CustomerName));
           namevaluepair.add(new BasicNameValuePair("phone_no",PhoneNo));



           String jsonStr = sh.makeServiceCall(Menu_Activity.post_url, ServiceHandler.POST);

           Log.d("Response: ", "> " + jsonStr);


           if (jsonStr != null) {
               try {

                   JSONObject jsonObj = new JSONObject(jsonStr);
                   table_list = jsonObj.getJSONArray("table_list");//selecting array from json string

                   Log.d("Response: ", "> " + table_list);


                   for (int i = 0; i < table_list.length(); i++) {
                       JSONObject o = table_list.getJSONObject(i);
                       Table t = new Table();
                       t.setId(Integer.parseInt(o.getString("id")));
                       t.setTable_name(o.getString("table_name"));
                       t.setIs_reserved(Boolean.parseBoolean(o.getString("is_reserved")));
                       arrlist_table.add(t);
                   }


               } catch (JSONException e) {
                   e.getMessage();
               }

           } else {

           }
           return null;
       }

       @Override
       protected void onPostExecute(ArrayList<Table> result) {
           super.onPostExecute(result);
           arrlist_table.size();
           if (pDialog.isShowing())
               pDialog.dismiss();



           //Here we can Bind Menu data with async data


       }
   }



}
