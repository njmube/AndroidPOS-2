package www.epicmyanmar.com.andropos;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import entity.Table;
import util.ServiceHandler;
import util.Table_AsyncHelper;

public class Menu_Activity extends  ActionBarActivity {
    TextView lbl_sales;
    TextView lbl_menu;
    ImageButton btn_menu;
    ImageButton btn_customer;

    private ProgressDialog pDialog;





    public static final String url="http://10.56.66.85/slimtest/tables";
    public static final String post_url="http://10.56.66.85/slimtest/post/Win_ha";


    //static arraylist to pass list returned from async


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_);



        lbl_sales=(TextView) findViewById(R.id.lbl_sales);


        addlistener();





    }


    private View.OnClickListener clk_listener=new View.OnClickListener(){
            public void onClick(View V){
                switch(V.getId()){
                    case R.id.btn_menu:
                        //do menu function
                        break;
                    case R.id.btn_customers:

                            fire();
                        break;
                }
            }
    };

    public void fire(){
        Intent intent=new Intent(this,Table_activity.class);
        startActivity(intent);
    }




    public void addlistener() {
        final Context context = this;
        btn_menu = (ImageButton) findViewById(R.id.btn_menu);

        btn_menu.setOnClickListener(clk_listener);

        btn_customer = (ImageButton) findViewById(R.id.btn_customers);

        btn_customer.setOnClickListener(clk_listener);


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_, menu);
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
            pDialog = new ProgressDialog(Menu_Activity.this);
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

            String jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);


            Log.d("Response: ", "> " + jsonStr);


            if (jsonStr != null) {
                try {

                    JSONObject jsonObj = new JSONObject(jsonStr);
                    table_list = jsonObj.getJSONArray("table_list");//selecting array from json string

                    Log.d("Response:", "> " + table_list);


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




        }
    }

}


/**
 * Async task class to get json by making HTTP call
 * */


