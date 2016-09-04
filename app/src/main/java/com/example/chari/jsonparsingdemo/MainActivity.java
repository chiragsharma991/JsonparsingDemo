package com.example.chari.jsonparsingdemo;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Chari on 8/6/2016.
 */
class MainActivity extends AppCompatActivity {
    public static String TAG = "tag";
    public static String TAG_NAME = "name";
    public static String TAG_DOB = "dob";
    public static String URL = "http://microblogging.wingnity.com/JSONParsingTutorial/jsonActors";

    private Button button;
    Context context;
    public static ArrayList<HashMap<String,String>>Studentlist=new ArrayList<HashMap<String, String>>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        find();
        Getdata getdata=new Getdata(this);
        getdata.execute(URL);



    }




    //parsing method


    public static ArrayList<HashMap<String, String>> Parsejson(String json)
    {
        ArrayList<HashMap<String,String>>list=new ArrayList<HashMap<String, String>>();
        if (json != null)
        {
            try {
                HashMap<String,String>data=new HashMap<String, String>();
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
                    data.put(TAG_NAME,name);
                    data.put(TAG_DOB,dob);
                    //adding in hash map list
                    list.add(data);

                }
//

            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }


        }
        Log.e(TAG, " last if" );
        return list;

    }


    private void find()
    {
      //  button = (Button) findViewById(R.id.submit);

    }


    public static void result()
    {
        String response=Getdata.json;
        Log.e(TAG,"res in main"+response);
        Studentlist =Parsejson(response);
        Log.e(TAG,"Studentlist"+Studentlist.size());
    }
}

