package be.msk.calcelection;
/**
 * le role de cette classe c'est de récupérer le nom et le nombre de siege de chaque liste
 */

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;




import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Etape2 extends AppCompatActivity {

    EditText nomListe, nbVoix;
    TextView messageError2;
    Button add;
    String name,voix;
    TextView messageListe;
    int cpt = 0;
    int nbSiege,nbListe;


    public static ListeElectorale listeElectorale;
    public static ListeElectorale[] listes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etape2);

        nbVoix=findViewById(R.id.voixListe);
        nomListe =findViewById(R.id.nomListe);

        add=findViewById(R.id.ajouter);
        messageError2=findViewById(R.id.messageError2);
        messageListe=findViewById(R.id.messageListe);

        //récuperer les infos stocker au SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("key1" ,MODE_PRIVATE);
        nbSiege = sharedPreferences.getInt("nbSiege",0);
        nbListe = sharedPreferences.getInt("nbListe",0);

        if(listes == null){
            listes = new ListeElectorale[nbListe];
        }

        //gestion des messages d'erreur
        messageError2.setText(null);

        messageListe.setText("Liste N° "+(cpt+1));
        add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //gestion des messages d'erreur
                messageError2.setText(null);

                //Eliminer les espaces des champs input
                name = nomListe.getText().toString().trim();
                voix = nbVoix.getText().toString().trim();


                //Si les champs sont vide -> affiche message d'erreur
                if ((name.length() == 0) || (voix.length() == 0)) {
                    messageError2.setText("Les deux champs doivent être remplis. Merci ");
                }else {
                    //Création d'un objet liste
                    listeElectorale = new ListeElectorale();

                    //modifier les attributs de l'objet ListeElectorale
                    listeElectorale.setId(cpt+1);
                    listeElectorale.setNom(nomListe.getText().toString());
                    listeElectorale.setVoix(Integer.parseInt(nbVoix.getText().toString()));
                    listeElectorale.setElimine(false);
                    listeElectorale.setSiege(0);
                    listes[cpt] = listeElectorale;


                    nbVoix.setText(null);
                    nomListe.setText(null);
                    cpt++;
                    if(cpt != nbListe){
                        messageListe.setText("Liste N°  " +(cpt+1));
                    }

                    if (cpt == nbListe) {
                        Intent intent = new Intent(getApplicationContext(), Etape3.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }

        });
    }
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Exit")
                .setMessage("Vous ne pouvez pas retourné en arrière... ")
                .setPositiveButton("Recommencer", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getApplicationContext(), Etape1.class);
                        startActivity(intent);
                        finish();
                    }
                }).setNegativeButton("Continuer", null).show();
    }

}