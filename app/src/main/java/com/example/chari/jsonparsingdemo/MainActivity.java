package com.example.chari.jsonparsingdemo;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Chari on 8/6/2016.
 */
public class MainActivity extends BaseActivity {
    public static String TAG = "tag";
    public static String TAG_NAME = "name";
    public static String TAG_DOB = "dob";


    ;
   // public static String URL = "http://microblogging.wingnity.com/JSONParsingTutorial/jsonActors";
    public static String URL = "http://echo.jsontest.com/Password/sharma619/user/chiragsharma991";

    private Button button;

    public static ArrayList<HashMap<String, String>> Studentlist = new ArrayList<HashMap<String, String>>();
    private static EditText password;
    private static EditText user;
    private static Context context;
    private RelativeLayout linear;
    private boolean checkNetwork;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       this.context= getBaseContext();
        find();

    }

    public void submit(View view)
    {
        if(user.getText().length()==0||password.getText().length()==0)
        {
            Toast.makeText(this,"Enter user and password", Toast.LENGTH_LONG).show();

        }else {

        Log.e("on click","log");
         checkNetwork = isNetworkOnline();
        if (checkNetwork) {
            Getdata getdata = new Getdata(this);
            getdata.execute(URL);

        }else {
            Snackbar snakbar=Snackbar.make(linear,"No internet connection!",Snackbar.LENGTH_LONG).setAction("Retry", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (checkNetwork) {
                        if(user.getText().length()==0||password.getText().length()==0)
                        {
                            Toast.makeText(MainActivity.this,"Enter user and password", Toast.LENGTH_LONG).show();

                        }else {
                        Getdata getdata = new Getdata(MainActivity.this);
                        getdata.execute(URL);}
                    }

                }
            });
            snakbar.setActionTextColor(Color.RED);
            snakbar.show();
            //Toast.makeText(this,"Check the network ", Toast.LENGTH_LONG).show();

        }
        }

    }




    //parsing method


    public static ArrayList<HashMap<String, String>> Parsejson(String json) {
        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        if (json != null) {
            try {
                HashMap<String, String> data = new HashMap<String, String>();
                JSONObject jsonobject = new JSONObject(json);
                JSONArray jsonarray = jsonobject.getJSONArray("actors");
                Log.e(TAG, "length of json" + jsonarray.length());
                for (int i = 0; i < jsonarray.length(); i++) {
                    JSONObject object = jsonarray.getJSONObject(i);
                    String name = String.valueOf(object.get("name"));
                    Log.e(TAG, "name of" + name);

                    String dob = object.getString("dob");
                    Log.e(TAG, " dob" + dob);

                    //using hash map
                    data.put(TAG_NAME, name);
                    data.put(TAG_DOB, dob);
                    //adding in hash map list
                    list.add(data);   //this is final list

                }
//

            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }


        }
        Log.e(TAG, " last if");
        return list;

    }


    private void find() {
        //  button = (Button) findViewById(R.id.button);
        user=(EditText)findViewById(R.id.username);
        password=(EditText)findViewById(R.id.password);
         linear=(RelativeLayout)findViewById(R.id.base);

    }


    public static void result() {
        String user_json = null;
        String password_json = null;
        String response = Getdata.json;
        Log.e(TAG, "res in main" + response);
        try {
            JSONObject jsonObject=new JSONObject(response.toString());
             user_json = jsonObject.getString("user");
            Log.e(TAG,"user name"+user_json);
             password_json=jsonObject.getString("Password");
            Log.e(TAG,"user password"+password_json);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        if(user.getText().toString().equals(user_json) && password.getText().toString().equals(password_json))
        {
            Toast.makeText(context, "Authantication sucess", Toast.LENGTH_SHORT).show();
          //  Log.e(TAG,"Authantication sucess");
        }else
        {
            Toast.makeText(context, "Authantication failed", Toast.LENGTH_SHORT).show();
           // Log.e(TAG,"Authantication failed");
        }

        // Studentlist = Parsejson(response);
       // Log.e(TAG, "Studentlist" + Studentlist.size());
    }
}

