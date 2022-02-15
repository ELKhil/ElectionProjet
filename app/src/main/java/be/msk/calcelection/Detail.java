package be.msk.calcelection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;

public class Detail extends AppCompatActivity {

    EditText nomListe,nbVoix;
    TextView messageError ;
    Button mod;
    int position;
    Params p = new Params();
    HashMap<String,String> m;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        nomListe = findViewById(R.id.nListe);
        nbVoix =findViewById(R.id.nVoix);
        mod=findViewById(R.id.modifier);
        messageError =findViewById(R.id.messageErrorDetail);


        Bundle extras = getIntent().getExtras();
        if(extras!=null){

            position = extras.getInt("position");

            //récuperer le hashmap avec la position
            m = p.values.get(position);

            nomListe.setText((m.get("nomParti")).replace("Nom du parti      :      ",""));
            nbVoix.setText((m.get("nbVoix")).replace("Nombre de voix :      ",""));

            mod.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Si les champs sont vide -> affiche message d'erreur
                    if ((nomListe.getText().toString().length() == 0) || (nbVoix.getText().toString().length() == 0)) {
                        messageError.setText("Les deux champs doivent être remplis. Merci ");
                    } else{
                        Etape2.listes[position].setNom(nomListe.getText().toString());
                        Etape2.listes[position].setVoix(Integer.parseInt(nbVoix.getText().toString()));

                        Intent i =new Intent(getApplicationContext(),Etape3.class);
                        startActivity(i);
                        finish();
                    }

                }
            });

        }

    }@Override
    public void onBackPressed(){
        // TODO Auto-generated method stub
        Intent intent = new Intent(Detail.this,Etape3.class);
        startActivity(intent);
        finish();
    }
}