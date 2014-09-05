package www.epicmyanmar.com.andropos;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.Calendar;

import www.epicmyanmar.com.andropos.R;

public class Booking_activity extends ActionBarActivity {
    private int year;
    private int month;
    private int day;

    EditText txt_startdate;
    EditText txt_starttime;
    ImageButton btn_datepicker;
    ImageButton btn_timepicker;

    static final int DATE_PICKER_ID = 1111;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_activity);

        txt_startdate=(EditText) findViewById(R.id.txt_startdate);
        btn_datepicker=(ImageButton) findViewById(R.id.btn_datepicker);
        btn_timepicker=(ImageButton) findViewById(R.id.btn_timepicker);

        txt_starttime=(EditText) findViewById(R.id.txt_starttime);

        final Calendar c = Calendar.getInstance();
        year  = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day   = c.get(Calendar.DAY_OF_MONTH);


        txt_startdate.setText(new StringBuilder()
                .append(month + 1).append("-").append(day).append("-")
                .append(year).append(" "));

        add_listener();


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
                    break;
            }
        }
    };

    public void add_listener(){
        btn_datepicker.setOnClickListener(clk_listener);
        btn_timepicker.setOnClickListener(clk_listener);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_PICKER_ID:

                // open datepicker dialog.
                // set date picker for current date
                // add pickerListener listner to date picker
                return new DatePickerDialog(this, pickerListener, year, month,day);
        }
        return null;
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
}
