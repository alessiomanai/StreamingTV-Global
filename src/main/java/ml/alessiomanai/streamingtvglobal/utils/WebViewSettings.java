package ml.alessiomanai.streamingtvglobal.utils;

import android.annotation.SuppressLint;
import android.webkit.WebView;

public class WebViewSettings {

    @Deprecated
    @SuppressLint("SetJavaScriptEnabled")
    public static WebView doSettings(WebView finestra){

        finestra.getSettings().setJavaScriptEnabled(true);
        finestra.getSettings().setLoadsImagesAutomatically(true);
        finestra.getSettings().setMediaPlaybackRequiresUserGesture(false);

        finestra.setHorizontalScrollBarEnabled(true);
        finestra.setVerticalScrollBarEnabled(true);

        return finestra;
    }

}
