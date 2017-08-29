package zuoye.bwiei.com.zuoye01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import bean.bean;

@ContentView(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    @ViewInject(R.id.lv_list)
    ListView lv_list;
    private List<bean> list = new ArrayList<>();
    private Myadapter ma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initGet();
        ma = new Myadapter();

        lv_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent in = new Intent(MainActivity.this,Main2Activity.class);
                in.putExtra("url",list.get(i).url);
                startActivity(in);

            }
        });

    }

    private void initGet() {


        RequestParams  parmas = new RequestParams("http://v.juhe.cn/toutiao/index");
        parmas.addQueryStringParameter("key","22a108244dbb8d1f49967cd74a0c144d");
        x.view().inject(this);
        x.http().get(parmas, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {

                try {
                    JSONObject obj = new JSONObject(result);
                    JSONObject result1  = obj.getJSONObject("result");
                    JSONArray data = result1.getJSONArray("data");
                    for (int i = 0; i <data.length() ; i++) {

                        JSONObject array = (JSONObject) data.get(i);
                        bean b = new bean();
                        b.author_name = array.getString("author_name");
                        b.date = array.getString("date");
                        b.thumbnail_pic_s = array.getString("thumbnail_pic_s");
                        b.title = array.getString("title");
                        b.url = array.getString("url");
                        list.add(b);

                        System.out.println(b.url);



                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }


                System.out.println(result);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

                lv_list.setAdapter(ma);

            }
        });


    }




    class  Myadapter extends BaseAdapter{


        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }


        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = View.inflate(MainActivity.this, R.layout.item, null);
            TextView te_date = view.findViewById(R.id.te_date);
            ImageView te_img = view.findViewById(R.id.te_img);
            TextView te_title = view.findViewById(R.id.te_title);
            TextView te_name = view.findViewById(R.id.te_name);

            te_date.setText(list.get(i).date);
            te_name.setText(list.get(i).author_name);
            te_title.setText(list.get(i).title);

            ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(MainActivity.this).build();
            ImageLoader.getInstance().init(configuration);
            ImageLoader.getInstance().displayImage(list.get(i).thumbnail_pic_s,te_img);

            return view;
        }
    }
}
