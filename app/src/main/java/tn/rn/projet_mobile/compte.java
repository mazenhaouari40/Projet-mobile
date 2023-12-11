package tn.rn.projet_mobile;



import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

public class compte extends Activity {
    private EditText nom;
    private EditText prenom;
    private EditText adresse;
    private EditText password;
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_compte);
        nom=(EditText) findViewById(R.id.nom);
        prenom=(EditText) findViewById(R.id.prenom);
        adresse=(EditText) findViewById(R.id.email);
        password=(EditText) findViewById(R.id.password);
        btn = findViewById(R.id.btn);
        Intent myLocalIntent = getIntent();
        Bundle myBundle = myLocalIntent.getExtras();
        String v1 = myBundle.getString("adresse");
        String v2 = myBundle.getString("mdp");

        adresse.setText(v1);
        password.setText(v2);

        adresse=(EditText) findViewById(R.id.email);
        password=(EditText) findViewById(R.id.password);

        this.btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntentA1A2 = new Intent(compte.this,MainActivity .class);
                Bundle myData = new Bundle();
                myData.putString("adresse", adresse.getText().toString());
                myData.putString("mdp", password.getText().toString());
                myIntentA1A2.putExtras(myData);

                setResult(Activity.RESULT_OK, myLocalIntent);
                finish();
            }
        });
    }
    public void onClick(View v) {
        finish();
    }

}


