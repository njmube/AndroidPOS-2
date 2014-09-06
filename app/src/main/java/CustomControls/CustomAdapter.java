package CustomControls;
import java.util.ArrayList;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import model.SpinnerModel;
import www.epicmyanmar.com.andropos.Booking_activity;
import www.epicmyanmar.com.andropos.R;

/***** Adapter class extends with ArrayAdapter ******/
public class CustomAdapter extends ArrayAdapter<String>{

    private Activity activity;
    private ArrayList data;
    public Resources res;
    SpinnerModel tempValues=null;
    LayoutInflater inflater;

    /*************  CustomAdapter Constructor *****************/
    public CustomAdapter(
            Booking_activity activitySpinner,
            int textViewResourceId,
            ArrayList objects

    )
    {
        super(activitySpinner, textViewResourceId, objects);

        /********** Take passed values **********/
        activity = activitySpinner;
        data     = objects;


        /***********  Layout inflator to call external xml layout () **********************/
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public View getDropDownView(int position, View convertView,ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    // This funtion called for each row ( Called data.size() times )
    public View getCustomView(int position, View convertView, ViewGroup parent) {

        /********** Inflate spinner_rows.xml file for each row ( Defined below ) ************/
        View row = inflater.inflate(R.layout.spinner_rows, parent, false);

        /***** Get each Model object from Arraylist ********/
        tempValues = null;
        tempValues = (SpinnerModel) data.get(position);

        TextView label        = (TextView)row.findViewById(R.id.company);
        TextView sub          = (TextView)row.findViewById(R.id.sub);
        //ImageView companyLogo = (ImageView)row.findViewById(R.id.image);

        if(position==0){

            // Default selected Spinner item 
            label.setText("Please select Food");
            sub.setText("");
        }
        else
        {
            // Set values for spinner each row 
            label.setText(tempValues.getCompanyName());
            sub.setText(tempValues.getDescription());


        }

        return row;
    }
}