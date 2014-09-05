package util;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import entity.Table;
import www.epicmyanmar.com.andropos.Menu_Activity;

/**
 * Created by jr on 9/4/14.
 */
public class Table_AsyncHelper extends AsyncTask<Void, Void, ArrayList<Table>> {
    private ProgressDialog pDialog;









    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }
    JSONArray table_list=null;// tables list to bind

    @Override
    protected ArrayList<Table> doInBackground(Void... arg0) {
        ServiceHandler sh=new ServiceHandler();

        String jsonStr=sh.makeServiceCall(Menu_Activity.url,ServiceHandler.GET);


        Log.d("Response: ", "> " + jsonStr);

        ArrayList<Table> arrlist_table =new ArrayList<Table>();
        if(jsonStr!=null){
            try{
                JSONObject jsonObj = new JSONObject(jsonStr);
                table_list=jsonObj.getJSONArray("table_list");//selecting array from json string

                Log.d("Response: ", "> " + table_list);



                for(int i=0;i<table_list.length();i++){
                    JSONObject o=table_list.getJSONObject(i);
                    Table t=new Table();
                    t.setId(Integer.parseInt(o.getString("id")));
                    t.setTable_name(o.getString("table_name"));
                    t.setIs_reserved(Boolean.parseBoolean(o.getString("is_reserved")));
                    arrlist_table.add(t);
                }
                return arrlist_table;

            }catch(JSONException e){
                e.getMessage();
            }

        }else{

        }
        return arrlist_table ;
    }

   @Override protected void onPostExecute(ArrayList<Table> result){
      super.onPostExecute(result);



    }
}

