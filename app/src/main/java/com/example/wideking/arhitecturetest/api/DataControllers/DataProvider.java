package com.example.wideking.arhitecturetest.api.DataControllers;

import android.util.Log;

import com.example.wideking.arhitecturetest.api.dataModels.ResponseData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

/**
 * Created by WideKing on 8.3.2016..
 */
public class DataProvider {

    private static final String TAG = "DataProvider";

    /**
     * Make get request using url and parametars only
     *
     * @param urlString      String url what we are going to request
     * @param postDataParams Parametars as HashMap key value pairs
     * @return ResponseData data object with request results
     */
    public synchronized ResponseData get(String urlString,
                                         HashMap<String, Object> postDataParams) {
        return get(urlString, postDataParams, null, null, null);
    }

    /**
     * Make post request to server
     *
     * @param urlString              - String URL to send request to
     * @param dataParams             - parametars as HashMap key value pairs
     * @param notification           - Notification to notify core api observers if needed, will be stored in response object for later use
     * @param controllerNotification - Notification to notify view controllers
     * @param customObject           - Custom object for more information that will also be passed back through Response object
     * @return ResponseData Represents response object with data processed by request
     */
    protected synchronized ResponseData get(final String urlString,
                                         final HashMap<String, Object> dataParams,
                                         final String notification,
                                         final String controllerNotification,
                                         final Object customObject
                                        ) {

        //urlString = appendApiKeytoURL(urlString);
        ResponseData responseData = new ResponseData();
        responseData.setCustomObject(customObject);
        responseData.setNotification(notification);
        responseData.setControllerNotification(controllerNotification);

        HttpURLConnection connection = null;
        try {

            StringBuilder builder = new StringBuilder();
            String queryUrlString;
            if (dataParams != null) {

                queryUrlString = urlString;
            } else {
                queryUrlString = urlString;
            }

            URL url = new URL(queryUrlString);

            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

             /*
            This is for hashmapKey.
             */
            responseData.setURL(queryUrlString);

            int statusCode = connection.getResponseCode();

            if (statusCode == 200) {
                InputStream content = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(content));
                String line;
                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                }
                String data = builder.toString();
                responseData.setResponse(data);

                Log.d(TAG, data);
            } else {
                responseData.setError(true, connection.getResponseMessage());
            }


        } catch (MalformedURLException e1) {
            e1.printStackTrace();
            responseData.setError(true, "Malformed url error");
        } catch (IOException e1) {
            e1.printStackTrace();
            responseData.setError(true, "Io error processing request");
        } finally {
            if (connection != null) {

                connection.disconnect();
            }

        }

        return responseData;
    }
}
