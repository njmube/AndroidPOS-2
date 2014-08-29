package www.epicmyanmar.com.andropos;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class Menu_Activity extends  ActionBarActivity {
    TextView lbl_sales;
    TextView lbl_menu;
    ImageButton btn_menu;
    ImageButton btn_customer;
    public static final String Light_font_path = "fonts/Ubuntu-L.ttf";
    public static final String Reg_font_path="fonts/Ubuntu-R.ttf";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_);

        Typeface tf_light = Typeface.createFromAsset(getAssets(), Light_font_path);
        Typeface tf_reg=Typeface.createFromAsset(getAssets(),Reg_font_path);

        lbl_sales=(TextView) findViewById(R.id.lbl_sales);

        addlistener();





    }

    public void addlistener(){
        final Context context=this;
        btn_menu=(ImageButton) findViewById(R.id.btn_menu);

        btn_menu.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 System.out.print("ss");
             }
         });

        btn_customer=(ImageButton) findViewById(R.id.btn_customers);

        btn_customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Giid");
            }
        });



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
}
