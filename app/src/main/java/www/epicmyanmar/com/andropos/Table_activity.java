package www.epicmyanmar.com.andropos;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import CustomControls.TableListView_Adapter;
import entity.Table;
import util.ServiceHandler;
import www.epicmyanmar.com.andropos.R;

public class Table_activity extends ListActivity {
    private ProgressDialog pDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        new GetContacts().execute();
    }

    private ArrayList<Table> db(){
        ArrayList<Table> l=new ArrayList<Table>();
        Table t1=new Table();
        Table t2=new Table();
        Table t3=new Table();
        Table t4=new Table();
        Table t5=new Table();
        Table t6=new Table();
        Table t7=new Table();
        Table t8=new Table();
        Table t9=new Table();

        t1.setTable_name("1");
        t1.setTable_id("1");

        t2.setTable_name("2");
        t2.setTable_id("2");
        t3.setTable_name("1");
        t3.setTable_id("1");
        t4.setTable_name("1");
        t4.setTable_id("1");
        t5.setTable_name("1");
        t5.setTable_id("1");
        t6.setTable_name("1");
        t6.setTable_id("1");
        t7.setTable_name("1");
        t7.setTable_id("1");
        t8.setTable_name("1");
        t8.setTable_id("1");
        t9.setTable_name("1");
        t9.setTable_id("1");

        l.add(t1);
        l.add(t2);
        l.add(t3);
        l.add(t4);
        l.add(t5);
        l.add(t6);
        l.add(t7);
        l.add(t8);
        l.add(t9);

        return l;



    }



    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {

        try{
           // String selectedValue = (String) getListAdapter().getItem(position).;
            Table selectedValue = (Table) getListAdapter().getItem(position);
            Toast.makeText(this, selectedValue.getTable_name(), Toast.LENGTH_SHORT).show();


            Intent intent=new Intent(this,Booking_activity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.anim_out,R.anim.anim_in);

        }catch(Exception e){

            Log.i("s",e.getMessage().toString());

        }

       //

    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.table_activity, menu);
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


    private class GetContacts extends AsyncTask<Void, Void, ArrayList<Table>> {

        @Override
        protected void onPreExecute() {
            pDialog = new ProgressDialog(Table_activity.this);
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

            String jsonStr = sh.makeServiceCall(Menu_Activity.url, ServiceHandler.GET);


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
                        if(Integer.parseInt(o.getString("is_reserved").toString())==0){
                            t.setIs_reserved(false);
                        }else{
                            t.setIs_reserved(true);
                        }

                        t.setFrom_time(o.getString("from_time"));
                        t.setTo_time(o.getString("To_time"));
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



            setListAdapter(new TableListView_Adapter(Table_activity.this,arrlist_table));


        }
    }
}
