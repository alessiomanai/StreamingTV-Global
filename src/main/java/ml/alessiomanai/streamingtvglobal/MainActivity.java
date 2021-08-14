package ml.alessiomanai.streamingtvglobal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

import ml.alessiomanai.streamingtvglobal.channels.ChannelInterface;
import ml.alessiomanai.streamingtvglobal.utils.Lister;
import ml.alessiomanai.streamingtvglobal.utils.LoadChannels;

public class MainActivity extends AppCompatActivity {

    private ArrayList<JSONObject> json;
    private ListView listView;
    private Lister adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String keyTarget = "url";

        this.json = LoadChannels.getInstance().getAssetJsonData(this);

        listView = findViewById(R.id.channelListView);
        adapter = new Lister(this, this.json);
        listView.setAdapter(adapter);

        listView.deferNotifyDataSetChanged();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long idonet) {

                try {
                    Intent channel = new Intent(getBaseContext(), ChannelInterface.class);
                    channel.putExtra(getResources().getString(R.string.intentUrlKey), Objects.requireNonNull(json).get(position).getString(keyTarget));
                    startActivity(channel);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private void resetList(){
        this.json = LoadChannels.getInstance().getAssetJsonData(this);
        adapter = new Lister(MainActivity.this, json);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        menu.add(getResources().getString(R.string.country_selection)).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                dialogBox();

                return true;
            }
        });

        return true;
    }

    private void dialogBox(){
        String[] countries = getResources().getStringArray(R.array.countries);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getResources().getString(R.string.country_selection));
        builder.setItems(countries, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                json = LoadChannels.getInstance().filterByCountry(getApplicationContext(), countries[which]);
                adapter = new Lister(MainActivity.this, json);
                listView.setAdapter(adapter);

                if(countries[which].equals(getResources().getString(R.string.resetCondition))){
                    resetList();
                }
            }
        });
        builder.show();

    }

}