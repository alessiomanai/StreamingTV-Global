package ml.alessiomanai.streamingtvglobal.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import ml.alessiomanai.streamingtvglobal.R;


public class Lister extends ArrayAdapter<JSONObject> {

    private Activity context;
    private ArrayList<JSONObject> channelsList;

    public Lister(Activity context, ArrayList<JSONObject> channelsList) {
        super(context, R.layout.listatore, channelsList);

        this.context = context;
        this.channelsList = channelsList;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();

        @SuppressLint("ViewHolder")
        View rowView = inflater.inflate(R.layout.listatore, null, true);

        TextView txtTitle = rowView.findViewById(R.id.nomeT);

        try {
            txtTitle.setText(channelsList.get(position).getString("name"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return rowView;

    }

}
