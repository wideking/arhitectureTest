package com.example.wideking.arhitecturetest.api.DataControllers;



/**
 * Created by WideKing on 8.3.2016..
 */
public class DataHandler extends DataProvider {

    private static DataHandler ourInstance = new DataHandler();

    public static DataHandler getInstance() {
        return ourInstance;
    }

    protected DataHandler() {
    }


    //this is rubbish. it blocks thread that calls it.
    /* ExecutorService executorService;
    JSONArray getResponseDataFromServer(final String url) {

        JSONArray jsonArrayData = null;
        ResponseData responseData;
        //create new task
        FutureTask<ResponseData> future = new FutureTask<>(new Callable<ResponseData>() {
            public ResponseData call() {
                return get(url, null);
            }
        });
        //pass task to the execution.
        executorService.execute(future);

        try {
            //wait for task result
            responseData = future.get();

            //do with result whatever you want.
            if (!responseData.hasError()) {

                try {
                    jsonArrayData = new JSONArray(responseData.getResponse());
                } catch (JSONException e) {
                    e.printStackTrace();
                    jsonArrayData = null;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            jsonArrayData = null;

        } catch (ExecutionException e) {
            e.printStackTrace();
            jsonArrayData = null;
        }

        return jsonArrayData;
    }
    */
}
