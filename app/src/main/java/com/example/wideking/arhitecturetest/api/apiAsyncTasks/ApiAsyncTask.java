package com.example.wideking.arhitecturetest.api.apiAsyncTasks;

import android.os.AsyncTask;

import com.example.wideking.arhitecturetest.api.DataControllers.DataHandler;
import com.example.wideking.arhitecturetest.api.dataModels.ResponseData;
import com.example.wideking.arhitecturetest.api.runnables.ApiRunnable;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by WideKing on 8.3.2016..
 */
public class ApiAsyncTask extends AsyncTask<String, Integer, JSONArray> {

    Runnable preExecuteRunnable;
    ApiRunnable postExecuteRunnable;
    Runnable closeLoaderRunnable;

    /**
     * @param preExecuteRunnable  runnable that contains show loader code/ pre execude stuff.
     * @param postExecuteRunnable background stuff.
     * @param closeLoaderRunnable runnable that contains close loader code. This could be handled with passing dialog, but that could lead to leaking window exception...
     */
    public ApiAsyncTask(Runnable preExecuteRunnable, ApiRunnable postExecuteRunnable, Runnable closeLoaderRunnable) {

        this.preExecuteRunnable = preExecuteRunnable;
        this.postExecuteRunnable = postExecuteRunnable;
        this.closeLoaderRunnable = closeLoaderRunnable;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        if (preExecuteRunnable != null) {
            preExecuteRunnable.run();
        }
    }

    @Override
    protected JSONArray doInBackground(String... url) {
        ResponseData responseData = DataHandler.getInstance().get(url[0], null);
        JSONArray jsonArrayData;

        if (!responseData.hasError()) {

            try {
                jsonArrayData = new JSONArray(responseData.getResponse());
            } catch (JSONException e) {
                e.printStackTrace();
                jsonArrayData = null;
            }
        } else {

            jsonArrayData = null;
        }

        return jsonArrayData;
    }

    @Override
    protected void onPostExecute(JSONArray jsonArray) {
        super.onPostExecute(jsonArray);

        if (preExecuteRunnable != null) {
            postExecuteRunnable.setJsonArray(jsonArray);
            postExecuteRunnable.createRunnable().run();
        }
        closeLoaderRunnable.run();
    }
}


