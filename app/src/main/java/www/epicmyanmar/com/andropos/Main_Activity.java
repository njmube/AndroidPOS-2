package www.epicmyanmar.com.andropos;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import entity.Room;
import entity.Table;


public class Main_Activity extends Activity {
    public static final String Light_font_path = "fonts/Ubuntu-L.ttf";
    public static final String Reg_font_path="fonts/Ubuntu-R.ttf";

    TextView lbl_username;
    TextView lbl_password;
    TextView lbl_appname;
    EditText txt_username;
    EditText txt_password;
    Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_);
        final Context context=this;

        Typeface tf_light = Typeface.createFromAsset(getAssets(), Light_font_path);
        Typeface tf_reg=Typeface.createFromAsset(getAssets(),Reg_font_path);
        lbl_username=(TextView) findViewById(R.id.lbl_username);
        lbl_password=(TextView) findViewById(R.id.lbl_password);
        lbl_appname=(TextView) findViewById(R.id.lbl_appname);
        btn_login=(Button) findViewById(R.id.btn_login);
        txt_username=(EditText)findViewById(R.id.txt_username);
        txt_password=(EditText)findViewById(R.id.txt_password);

        lbl_username.setTypeface(tf_light);
        lbl_password.setTypeface(tf_light);
        lbl_appname.setTypeface(tf_reg); 

        txt_username.setTypeface(tf_light);
        txt_password.setTypeface(tf_light);
        btn_login.setTypeface(tf_light);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,Menu_Activity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_out_y,R.anim.anim_in_y);
            }
        });




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.main_, menu);
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
