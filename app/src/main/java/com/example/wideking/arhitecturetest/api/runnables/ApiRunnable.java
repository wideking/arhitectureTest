package com.example.wideking.arhitecturetest.api.runnables;

import android.widget.TextView;

import org.json.JSONArray;

/**
 * Created by WideKing on 8.3.2016..
 * <p>
 * Runnable that all other runnables should extend. Create your own runnable that sets activity view.
 * For example this runnable sets {@link com.example.wideking.arhitecturetest.activities.appActivity.ApplicationActivity } text from downloaded json Array.
 */

public class ApiRunnable {
    JSONArray jsonArray; //global return type of AsyncTask methods
    //views that need to be set
    TextView textView;



    // create constructor that receives all needed fields, avoid leaking activity, but it could be used.
    public ApiRunnable(JSONArray jsonArray, TextView textView) {
        this.jsonArray = jsonArray;
        this.textView = textView;
    }

    public void setJsonArray(JSONArray jsonArray) {
        this.jsonArray = jsonArray;
    }

    public Runnable createRunnable() {

        return new Runnable() {
            @Override
            public void run() {
                if (jsonArray != null) {
                    String stringJson = jsonArray.toString();
                    textView.setText(stringJson);
                } else {
                    textView.setText("Hardcodani sadržaj");
                }
            }
        };
    }
}
