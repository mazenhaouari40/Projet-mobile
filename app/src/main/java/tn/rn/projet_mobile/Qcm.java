package tn.rn.projet_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Qcm extends AppCompatActivity {
    private Button service;
    //private Button btn;
    private RadioButton radioButton;
    private RadioGroup r1,r2,r3;

    public String recupere(RadioGroup r1){
        int radioId= r1.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
        return (String) radioButton.getText();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_qcm);
        this.r1=this.findViewById(R.id.radioGroup1);
        this.r2=this.findViewById(R.id.radioGroup2);
        this.r3=this.findViewById(R.id.radioGroup3);
        this.service = (Button) this.findViewById(R.id.service);
      //  this.btn = (Button) this.findViewById(R.id.btn);
/*
        this.btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String ch1=recupere(r1);
                String ch2=recupere(r2);
                String ch3=recupere(r3);

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
                Toast helloToast = Toast.makeText(Qcm.this,
                        "Votre score = "+ch+"/3", Toast.LENGTH_LONG);
                helloToast.setGravity(Gravity.CENTER, 0, 0);
                helloToast.show();
            }});*/

        this.service.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String ch1=recupere(r1);
                String ch2=recupere(r2);
                String ch3=recupere(r3);

                Intent Lien = new Intent(Qcm.this,MyService.class);
                Lien.putExtra("q1",ch1);
                Lien.putExtra("q2",ch2);
                Lien.putExtra("q3",ch3);
                startService(Lien);

            }});

    }
}