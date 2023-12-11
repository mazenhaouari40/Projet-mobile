package tn.rn.projet_mobile;


import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";
    private EditText username,password;
    private Button btn;
    private String[][] m;
    private int n;
    private  long pause;
    private Chronometer chronometer;

    public MainActivity()
    {
        this.m=new String[100][100];
        this.n=0;
    }

    public void add(String adresse,String mdp )
    {
        this.m[n][0]=adresse ;
        this.m[n][1]=mdp ;
        n++;
    }

    public  String[][] inst()
    {return this.m;}


    @Override
    protected void onStart() {

        super.onStart();
    }

    @Override
    protected void onRestart() {
        Log.i(TAG,"onRestart");
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        Log.i(TAG,"onDestroy");
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        chronometer.stop();
        pause=SystemClock.elapsedRealtime() - chronometer.getBase();
        super.onPause();
    }

    protected void onResume() {
        chronometer.setBase(SystemClock.elapsedRealtime() - pause);
        chronometer.start();
        super.onResume();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        add("mazen@gmail.com","123");
        add("mahdi@gmail.com","123");
        add("bachir@gmail.com","123");

        username= (EditText) findViewById(R.id.username);
        password= (EditText) findViewById(R.id.password);
        chronometer= (Chronometer) findViewById(R.id.chronometer);
        btn =(Button) findViewById(R.id.btn);
        chronometer.start();
        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {

                if (   (SystemClock.elapsedRealtime() - chronometer.getBase())>=100000 )
                {
                    btn.setEnabled(false);
                }
            }
        });

        this.btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String[][] client=inst();
                String user= username.getText().toString();
                int i=0;
                while(i<n) {
                    boolean ok=false ;
                    for (int j=0;j<user.length();j++)
                    {
                        if (user.charAt(j)=='@')
                        {
                            ok=true ;
                            break ;
                        }
                    }
                    if (ok==false)
                    {
                        Toast helloToast = Toast.makeText(MainActivity.this,
                                " email incorrect", Toast.LENGTH_LONG);
                        helloToast.setGravity(Gravity.CENTER, 0, 0);
                        helloToast.show();
                        break ;
                    }
                    else
                    {
                        if ( (username.getText().toString().equals(client[i][0])  ) && (password.getText().toString().equals(client[i][1]) ) )
                        {
                            Toast helloToast = Toast.makeText(MainActivity.this,
                                    " correct "+username.getText().toString(), Toast.LENGTH_LONG);
                            helloToast.setGravity(Gravity.CENTER, 0, 0);
                            helloToast.show();

                            Intent myIntentA1A2 = new Intent(MainActivity.this,Qcm.class);
                            startActivityForResult(myIntentA1A2, 101);
                            break;}
                    }
                    i++;
                }
                if (i==n) {
                    Toast helloToast2 = Toast.makeText(MainActivity.this,
                            " incorrect "+username.getText().toString(), Toast.LENGTH_LONG);
                    helloToast2.setGravity(Gravity.CENTER, 0, 0);
                    helloToast2.show();
                    Intent myIntentA1A2 = new Intent(MainActivity.this,compte.class);
                    Bundle myData = new Bundle();
                    myData.putString("adresse", username.getText().toString());
                    myData.putString("mdp", password.getText().toString());
                    myIntentA1A2.putExtras(myData);
                    startActivityForResult(myIntentA1A2, 101);
                }
            }});
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if ((requestCode == 101) && (resultCode ==
                    Activity.RESULT_OK)) {
                Bundle myResults = data.getExtras();
                String adresse = myResults.getString("adresse");
                String mdp = myResults.getString("mdp");
                add(adresse,mdp);
                username.setText(adresse);
                password.setText(mdp);
            }
        } catch (Exception e){}
    }

}