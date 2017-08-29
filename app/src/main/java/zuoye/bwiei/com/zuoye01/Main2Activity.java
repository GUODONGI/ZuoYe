package zuoye.bwiei.com.zuoye01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.activity_main2)
public class Main2Activity extends AppCompatActivity {

    @ViewInject(R.id.web_view)
    WebView web_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent in = getIntent();
        x.view().inject(this);

        String url = in.getStringExtra("url");
        /*web_view.getSettings().setJavaScriptEnabled(true);
        web_view.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);*/
        web_view.loadUrl(url);


    }
}
