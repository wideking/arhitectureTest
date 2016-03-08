package com.example.wideking.arhitecturetest.api.DataControllers;

import com.example.wideking.arhitecturetest.R;
import com.example.wideking.arhitecturetest.activities.baseActivity.BaseActivity;
import com.example.wideking.arhitecturetest.api.apiAsyncTasks.ApiAsyncTask;
import com.example.wideking.arhitecturetest.api.runnables.ApiRunnable;

/**
 * Created by WideKing on 8.3.2016..
 */
public class ApiMethods extends DataHandler {
    private static ApiMethods ourInstance = new ApiMethods();

    public static ApiMethods getInstance() {
        return ourInstance;
    }

    private ApiMethods() {
        super();
    }

    // We send activity to enable calls to the loader method.
    public void setUpTestTask(final BaseActivity baseActivity, ApiRunnable postExecuteApiRunnable) {

        //here you do whatever u want. get data from cache, load from server. on start show loader and on receive data close loader.
        //create  task that need to be done.
        Runnable preExecuteRunnable = new Runnable() {
            @Override
            public void run() {
                baseActivity.showLoaderDialog(R.color.colorPrimary);
            }
        };
        //get data
        Runnable closeLoaderRunnable = new Runnable() {
            @Override
            public void run() {
                baseActivity.closeLoader();
            }
        };
        new ApiAsyncTask(preExecuteRunnable, postExecuteApiRunnable, closeLoaderRunnable).execute("http://jsonplaceholder.typicode.com/comments");

    }
}
