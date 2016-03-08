package com.example.wideking.arhitecturetest.activities.baseActivity;

import android.app.Dialog;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.wideking.arhitecturetest.R;

/**
 * This is base activity. All other activities should extends it.
 * This activity should contain all common methods that all activities use like loaders.
 */
public class BaseActivity extends AppCompatActivity {

    private Dialog dialogLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }

    public void showLoaderDialog(int loaderColorId) {

       /* if (dialogLoader == null || dialogLoaderView == null) {
            initLoader(loaderColorId);
        }*/

        if (dialogLoader == null) {

            initLoader(loaderColorId);
        }

        try {
            dialogLoader.show();

        } catch (Exception e) {
            Log.d("CUCA", "Previous fatal exception BadToken");
            //e.printStackTrace();
        }
    }

    public void initLoader(int loaderColorId) {


        dialogLoader = new Dialog(BaseActivity.this, android.R.style.Theme_Translucent_NoTitleBar);


        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);


        View dialogLoaderView = inflater.inflate(R.layout.dialog_loader_layout, (ViewGroup) this.findViewById(android.R.id.content), false);

        ProgressBar progressBar = (ProgressBar) dialogLoaderView.findViewById(R.id.pb_loader);

        progressBar.getIndeterminateDrawable().setColorFilter(
                ContextCompat.getColor(BaseActivity.this, loaderColorId),
                android.graphics.PorterDuff.Mode.SRC_IN);


        dialogLoader.setContentView(dialogLoaderView);

    }
    public void closeLoader() {
        if (dialogLoader != null) dialogLoader.dismiss();
    }
}
