package com.example.oseias.sverse.OthersClass;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class ContBackgroundService extends Service {
    public List<Worker> workers = new ArrayList<>();
    public List<Intent> its = new ArrayList<>();

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate(){
        super.onCreate();
        Log.i("Script","onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("Script","onStartCommand()");
        Log.i("Script", intent.hasExtra("maxWorkTimeInSeconds")?"O Extra funcionou!":"O Extra não funcionou.");
        Worker w = new Worker(startId, intent.getIntExtra("maxWorkTimeInSeconds", 10), intent);
        workers.add(w);
        its.add(intent);
        w.start();
        return super.onStartCommand(intent, flags, startId);
    }

    public class Worker extends Thread{
        int count, startId;
        long maxWorkTimeInSeconds; //in millis seconds
        Intent it;
        boolean ativo = true;

        public Worker(int startId, long maxWorkTimeInSeconds, Intent it){
            this.startId = startId;
            this.maxWorkTimeInSeconds = maxWorkTimeInSeconds;
            this.it = it;
        }

        public void run(){
            while(ativo && count<maxWorkTimeInSeconds){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                it.putExtra("getMillis", it.getIntExtra("getMillis", 0)-1000);
                if(it.getIntExtra("getMillis", 0)<1000){
                    it.putExtra("getMillis",0);
                }
                count++;
                Log.i("Script","Count: "+count);
            }
            Log.i("Script","A contagem foi encerrada!");
            stopSelf(startId);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        for(Worker w : workers){
            w.ativo = false;
        }
    }
}
