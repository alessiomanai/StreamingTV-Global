package ml.alessiomanai.streamingtvglobal.channels;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

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
}