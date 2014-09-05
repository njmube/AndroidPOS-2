package CustomControls;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import entity.Table;
import www.epicmyanmar.com.andropos.R;

/**
 * Created by jr on 9/5/14.
 */
public class TableListView_Adapter extends ArrayAdapter<Table> {

    private final Context context;
    private final  ArrayList<Table> values;


    public TableListView_Adapter(Context context, ArrayList<Table> values) {
        super(context,R.layout.table_listview, values);
        this.context=context;
        this.values=values;
        // TODO Auto-generated constructor stub
    }

    public View getView(int position,View convertView,ViewGroup parent){
        LayoutInflater inflater=(LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView=inflater.inflate(R.layout.table_listview,parent,false);

        TextView txt_label=(TextView) rowView.findViewById(R.id.lbltable_name);
        TextView txt_status=(TextView) rowView.findViewById(R.id.lbltable_status);
        txt_label.setText(values.get(position).getTable_name());
        txt_status.setText(values.get(position).getTable_id());


        return rowView;
    }






}
