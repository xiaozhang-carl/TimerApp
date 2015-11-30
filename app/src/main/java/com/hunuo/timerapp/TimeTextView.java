package com.hunuo.timerapp;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;


public class TimeTextView extends TextView implements Runnable{


    private long mday, mhour, mmin, msecond;//天，时，钟，秒
    private boolean run = false; //是否启动了
    private long mTime;

    public TimeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public TimeTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public TimeTextView(Context context) {
        super(context);
    }

    public void setTimes(long time) {
        mTime = time;
        this.msecond = (int) (time / 1000) % 60;
        this.mmin = (int) (time / (60 * 1000) % 60);
        this.mhour = (int) (time / (60 * 60 * 1000) % 24);
        this.mday = (int) (time / (24 * 60 * 60 * 1000));
        if(!run){
            start();
        }
    }


    public boolean isRun() {
        return run;
    }

    public void stop() {
        this.run = false;
    }

    public void start() {
        this.run = true;
        run();
    }

    @Override
    public void run() {
        if(run){
            setTimes(mTime - 1000);
            Log.i("TAG", mTime + "");
            if(mTime > 0){
                StringBuilder sb = new StringBuilder("还剩");
                sb.append(mday    + "天");
                sb.append(mhour   + "时");
                sb.append(mmin    + "分");
                sb.append(msecond + "秒");
                this.setText(sb.toString());
                postDelayed(this, 1000);
            }else{
                this.setText("抢购结束");
                run = false;
            }
        }
    }

}
