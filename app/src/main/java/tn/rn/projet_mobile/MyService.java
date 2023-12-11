package tn.rn.projet_mobile;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;
import java.util.concurrent.Executors;


import java.util.concurrent.ExecutorService;

public class MyService extends Service {
    public static final String TAG = "MyService";
    private ExecutorService executorService;

    public MyService() {
    }
    @Override
    public IBinder onBind(Intent intent) {
        //Intent Lien = new Intent(FirstService.this,ComposantsGraph.class);
        return null;
    }

    @Override
    public void onCreate() {
        Log.i(TAG, "Lancement du service");
        Toast.makeText(this, "Service Create", Toast.LENGTH_LONG).show();
        this.executorService = Executors.newFixedThreadPool(5);
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "Service Destroy", Toast.LENGTH_LONG).show();
        Toast.makeText(this, "ServiceData1: ", Toast.LENGTH_LONG).show();
        Log.i(TAG, "Destruction du service");
        this.executorService.shutdown();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "Service StartCommand", Toast.LENGTH_LONG).show();
        Log.i(TAG, "Appel à onStartCommand");

        String ch1= intent.getStringExtra("q1");
        String ch2= intent.getStringExtra("q2");
        String ch3= intent.getStringExtra("q3");
        int score=0;
        if (ch1.equals("str")){
            score++;
        }
        if (ch2.equals("print(len(s))affichera 9.")){
            score++;
        }
        if (ch3.equals("print(s[2])affichera ’b’."))
        {
            score=score+1;
        }

        String ch = String.valueOf(score);
        Toast helloToast = Toast.makeText(this,
                "Votre score = "+ch+"/3", Toast.LENGTH_LONG);
        helloToast.setGravity(Gravity.CENTER, 0, 0);
        helloToast.show();
        return START_REDELIVER_INTENT;
    }
}