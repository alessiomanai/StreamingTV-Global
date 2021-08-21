package ml.alessiomanai.streamingtvglobal.channels;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.Objects;

import ml.alessiomanai.streamingtvglobal.R;
import ml.alessiomanai.streamingtvglobal.utils.M3u8Reader;
import ml.alessiomanai.streamingtvglobal.utils.WebViewSettings;

public class ChannelInterface extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel_interface);

        Intent intent = getIntent();

        WebView finestra = findViewById(R.id.channelwebview);

        WebViewSettings.doSettings(finestra);
        finestra.setWebViewClient(new WebViewClient());

        String html = M3u8Reader.getHtmlReader(intent.getStringExtra("URL"));
        finestra.loadData(html, "text/html", null);
    }

    public void fullScreencall() {

        int display_mode = getResources().getConfiguration().orientation;

        if (display_mode == Configuration.ORIENTATION_LANDSCAPE) {
            Objects.requireNonNull(getSupportActionBar()).hide();

            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        fullScreencall();
    }
}