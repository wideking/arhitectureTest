package com.example.wideking.arhitecturetest.activities.appActivity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.wideking.arhitecturetest.R;
import com.example.wideking.arhitecturetest.activities.baseActivity.BaseActivity;
import com.example.wideking.arhitecturetest.api.DataControllers.ApiMethods;
import com.example.wideking.arhitecturetest.api.runnables.ApiRunnable;

public class ApplicationActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        setUpView();
    }

    private void setUpView() {
        TextView tvText = (TextView) findViewById(R.id.tv_text);
        //Create task that should set view based on received data.
        ApiRunnable runnable = new ApiRunnable(null, tvText);
        //Call Method that gets data you need. Send it your tasks so method can set your view.
        ApiMethods.getInstance().setUpTestTask(this, runnable);

    }


}
