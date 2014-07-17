package net.redlinesoft.hellowear;

import android.app.Activity;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;


public class WearActivity extends Activity {

    public TextView mTextDate;
    public TextView mTextTime;

    public Timer timer;
    public MyTimerTask myTimerTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wear);
        WatchViewStub stub;
        stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                mTextDate = (TextView) stub.findViewById(R.id.textDate);
                mTextTime = (TextView) stub.findViewById(R.id.textTime);
                if (timer != null) {
                    timer.cancel();
                }
                timer = new Timer();
                myTimerTask = new MyTimerTask();
                timer.schedule(myTimerTask, 1000, 1000);
            }
        });
    }

    class MyTimerTask extends TimerTask {
        @Override
        public void run() {
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat simpleDate = new SimpleDateFormat("dd MMMM yyyy");
            SimpleDateFormat simpleTime = new SimpleDateFormat("HH:mm:ss");
            final String strDate = simpleDate.format(calendar.getTime());
            final String strTime = simpleTime.format(calendar.getTime());
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mTextDate.setText(strDate);
                    mTextTime.setText(strTime);
                }
            });
        }
    }
}
