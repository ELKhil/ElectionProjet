package be.msk.calcelection;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
/**
 * Le role de cette classe c'est de récupérer le nombre de sièges et le nombre de listes,
 * ces deux informations entrées par l'utlisteur vont être vérifier
 */

public class Etape1 extends AppCompatActivity {
    private EditText siege, liste;
    private TextView messageError;
    private Button valider;
    private int nbSiege = 0, nbListe =0;
    private String listeVide, siege1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etape1);

        //Récuperer les id de chaque variable:
        siege = findViewById(R.id.nbSiege);
        liste = findViewById(R.id.nbListe);


        messageError = findViewById(R.id.messageError);



        valider = findViewById(R.id.btnValider);
        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Eliminer les espaces des champs input
                siege1 = siege.getText().toString().trim();
                listeVide = liste.getText().toString().trim();

                //Si les champs sont vide -> affiche message d'erreur
                if ((siege1.length() == 0) || (listeVide.length() == 0)) {
                    messageError.setText("Les deux champs doivent être remplis. Merci ");
                } else {
                    //Transformer String to Int
                    nbSiege = Integer.parseInt(siege.getText().toString());
                    nbListe = Integer.parseInt(liste.getText().toString());

                    //stocker les deux variable dans le localStorage:
                    SharedPreferences sharedPreferences = getSharedPreferences("key1", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("nbSiege",nbSiege);
                    editor.putInt("nbListe",nbListe);
                    editor.apply();

                    //respcter les conditions
                    if (nbSiege <= 0) {
                        messageError.setText("Le nombre de sièges doit être supérieur que 0. Merci ");
                    } else if (nbListe < 2) {
                        messageError.setText("Le nombre de listes doit être supérieur que 1. Merci");
                    } else {
                        //si tout est ok
                        Intent intent = new Intent(Etape1.this, Etape2.class);
                        startActivity(intent);
                        finish();

                    }
                }

            }
        });

    }

}