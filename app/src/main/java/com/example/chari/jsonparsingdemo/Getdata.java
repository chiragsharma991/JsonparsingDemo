package com.example.chari.jsonparsingdemo;

/**
 * Created by Chari on 8/27/2016.
 */

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Chari on 8/7/2016.
 */
class Getdata extends AsyncTask<String, String, String>
{

    private final Context context;
    ArrayList<HashMap<String,String>> Studentlist=new ArrayList<HashMap<String, String>>();


    ProgressDialog progress;
    WebRequest webreq;
    public static String json;


    public Getdata(Context context)
    {
        this.context=context;

    }

    @Override
    protected String doInBackground(String... params) {
        String url = params[0];

        webreq = new WebRequest();

// Making a request to url and getting response
        //String jsonStr = webreq.makeWebServiceCall(URL, WebRequest.GETRequest);
        json = webreq.makeWebServiceCall(url, WebRequest.GETRequest);
        Log.e(MainActivity.TAG,"response"+json);
        //  MainActivity main=new MainActivity();
        // Studentlist = main.Parsejson(json);
        //  Log.e(MainActivity.TAG,"student length"+Studentlist.size());
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progress=new ProgressDialog(context);
        progress.setMessage("please wait");
        progress.setCancelable(false);
        progress.show();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        progress.dismiss();
        MainActivity.result();
    }
}
