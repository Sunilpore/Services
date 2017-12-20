package com.example.sunil.serviceapp10;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by Sunil on 12/19/2017.
 */

public class TheService extends Service {


    final class TheThread implements Runnable{

        int serviceID;

        TheThread(int serviceID) {
            this.serviceID = serviceID;
        }

        @Override
        public void run() {

            synchronized (this){
                try {
                    wait(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                stopSelf(serviceID);
            }

        }
    }


    @Override
    public void onCreate(){

    }

    @Override
    public int onStartCommand(Intent intent,/* @IntDef(value = {Service.START_FLAG_REDELIVERY, Service.START_FLAG_RETRY}, flag = true)*/ int flags, int startId) {
        //return super.onStartCommand(intent, flags, startId);
        Toast.makeText(TheService.this,"Service Start",Toast.LENGTH_SHORT).show();

        Thread thread=new Thread(new TheThread(startId));
        thread.start();

        return START_STICKY;
    }


    @Override
    public void onDestroy() {
        //super.onDestroy();
        Toast.makeText(TheService.this,"Service Stop",Toast.LENGTH_SHORT).show();
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
