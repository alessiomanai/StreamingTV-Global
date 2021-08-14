package ml.alessiomanai.streamingtvglobal.utils;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;


import ml.alessiomanai.streamingtvglobal.R;

public final class LoadChannels {

    private static LoadChannels instance;

    private LoadChannels(){}

    public static LoadChannels getInstance(){

        if (instance == null){
            instance = new LoadChannels();
        }

        return instance;
    }

    public ArrayList<JSONObject> getAssetJsonData(Context context) {
        String json = null;
        JSONArray jobj = new JSONArray();
        ArrayList<JSONObject> list = new ArrayList<>();

        try {
            InputStream is = context.getResources().openRawResource(R.raw.channels);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }


        try {
            jobj = new JSONArray(json);

            for (int i = 0; i < jobj.length(); i++) {
                JSONObject temp = jobj.getJSONObject(i);
                list.add(temp);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return list;
    }

    public ArrayList<JSONObject> filterByCountry(Context context, String country){

        ArrayList<JSONObject> filteredList = new ArrayList<>();
        ArrayList<JSONObject> list = this.getAssetJsonData(context);

        assert list != null;
        for (JSONObject actual : list){

            try {

                if (actual.getString("countries").matches(".*" + country + ".*")){

                    filteredList.add(actual);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        return filteredList;
    }

}
