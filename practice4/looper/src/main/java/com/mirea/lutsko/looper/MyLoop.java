package com.mirea.lutsko.looper;

import android.annotation.SuppressLint;
import android.nfc.cardemulation.HostNfcFService;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;


import java.util.logging.LogRecord;

class MyLooper extends Thread {
    private int number = 0;
    Handler handler;

    @SuppressLint("HandlerLeak")
    @Override
    public void run(){
        Log.d("MyLooper", "run");
        Looper.prepare();
        handler = new Handler() {
            @Override
            public void handleMessage(Message message) {
                int age = message.getData().getInt("AGE");
                String job = message.getData().getString("JOB");

                try {
                    Thread.sleep(age);
                }
                catch (InterruptedException exception) {
                    exception.printStackTrace();
                }

                Log.d("MyLooper", "Age: " + age);
                Log.d("MyLooper", "Job: " + job);

                number++;
            }
        };

        Looper.loop();
    }
}
