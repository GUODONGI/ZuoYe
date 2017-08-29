package zuoye.bwiei.com.zuoye01;

import android.app.Application;

import org.xutils.x;

/**
 * Created by 0514 on 2017/8/29.
 */

public class App  extends Application{


    @Override
    public void onCreate() {
        super.onCreate();

        x.Ext.init(this);


    }
}
