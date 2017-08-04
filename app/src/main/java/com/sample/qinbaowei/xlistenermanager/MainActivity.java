package com.sample.qinbaowei.xlistenermanager;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.qbw.util.xlistener.XListenerManager;

public class MainActivity extends Activity implements XListenerManager.XListener {

    protected TextView mTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_main);
        XListenerManager.getInstance().addListener(this);

        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    final int j = i;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            XListenerManager.getInstance().notify(j);
                        }
                    });
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
        initView();
    }

    @Override
    protected void onDestroy() {
        XListenerManager.getInstance().removeListener(this);
        super.onDestroy();
    }

    @Override
    public void onXListen(Object o) {
        if (o instanceof Integer) {
            mTxt.setText(o.toString());
        }
    }

    private void initView() {
        mTxt = (TextView) findViewById(R.id.txt);
    }
}
