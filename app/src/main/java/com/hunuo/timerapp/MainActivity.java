package com.hunuo.timerapp;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.start)
    Button start;

    @InjectView(R.id.stop)
    Button stop;

    @InjectView(R.id.text)
    TimeTextView text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
    }


    @OnClick(R.id.start)
    public void startClick(View v){
        Calendar c = Calendar.getInstance();
        DatePickerDialog dialog = new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker dp, int year,int month, int dayOfMonth) {
                        showTimeDialog(year, month, dayOfMonth);
                    }
                },
                c.get(Calendar.YEAR), // 传入年份
                c.get(Calendar.MONTH), // 传入月份
                c.get(Calendar.DAY_OF_MONTH) // 传入天数
        );
        dialog.show();
    }


    private void showTimeDialog(final int year,final int month, final int dayOfMonth){
        Calendar c = Calendar.getInstance();
        TimePickerDialog dialog = new TimePickerDialog(
                this,
                new TimePickerDialog.OnTimeSetListener(){
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        //String data = dateFormat.format(new java.util.Date(timestamp * 1000));   //String 日期格式
                        //Date date=format.parse(d);   //日期格式
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        try {
                            Date time = dateFormat.parse(year + "-" + (month+1) + "-" + dayOfMonth + " " + hourOfDay + ":"  + minute + ":"  + 00);
                            long shopTime = (time.getTime()-System.currentTimeMillis());
                            //long shopTime = ((time.getTime()/1000)-(System.currentTimeMillis()/1000));
                            Log.i("TAG", "当前日期:" + formatTime(System.currentTimeMillis()/1000));
                            Log.i("TAG", "到期日期:" + formatTime(time.getTime()/1000));
                            Log.i("TAG", "剩余日期:" + formatTime(shopTime/1000));
                            text.setTimes(shopTime);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                },
                c.get(Calendar.HOUR_OF_DAY),
                c.get(Calendar.MINUTE),
                false
        );
        dialog.show();
    }


    private String formatTime(long timestamp){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(new java.util.Date(timestamp * 1000));   //String 日期格式
    }



    @OnClick(R.id.stop)
    public void stopClick(View v){
        text.stop();
    }



}
