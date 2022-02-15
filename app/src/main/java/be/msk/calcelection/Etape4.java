package be.msk.calcelection;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.HashMap;

public class Etape4 extends AppCompatActivity {

    ListView lsFinal;
    HashMap<String,String> mapResultat;
    Params r = new Params();
    Calcule calc =new Calcule();
    Button exit,fichierPdf,recomencer;
    int nbListe,nbSiege;



            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_etape4);
                exit = findViewById(R.id.exit);
                fichierPdf=findViewById(R.id.gPdf);
                recomencer = findViewById(R.id.recommencer);


                //récuperer les infos stocker au SharedPreferences
                SharedPreferences sharedPreferences = getSharedPreferences("key1" ,MODE_PRIVATE);
                nbSiege = sharedPreferences.getInt("nbSiege",0);
                nbListe = sharedPreferences.getInt("nbListe",0);



                recomencer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                    recommencer();
                    }
                });



                fichierPdf.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                    pdfEncours();
                    }
                });

                exit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onBackPressed();
                    }
                });

                //faire appel aux fonctions qui sont dans la classe calcule
                calc.calculer(nbListe,nbSiege);


                //l'affichage
                lsFinal =findViewById(R.id.lstFinal);

                //vider la liste si il n'est pas vide
                if(!r.values.isEmpty()){
                    r.values.clear();
                }
                for(int i=0;i<nbListe;i++){
                    if(!Etape2.listes[i].isElimine() && Etape2.listes[i].getSiege()>0){
                        //Création d'une liste HashMap
                        mapResultat= new HashMap<String,String>();

                        //récuperation du nom de la parti
                        String nomTmp = Etape2.listes[i].getNom();
                        String afficheName = "Nom du Parti : "+nomTmp;

                        //récipération de voix de la parti
                        int siegTmp = Etape2.listes[i].getSiege();
                        String afficheSiege = (siegTmp > 1) ? siegTmp+" Sièges obtenus" : siegTmp+" Siège obtenu" ;

                        //ajouter le nom et les voix dans le hashmap
                        mapResultat.put("parti",afficheName);
                        mapResultat.put("siege",afficheSiege);

                        //ajouter la Hashmap a un arrayListe
                        r.values.add(mapResultat);

                    }
                }

                //adapter qui gere l'affichage des item2
                SimpleAdapter adapter = new SimpleAdapter(Etape4.this, r.values,
                        R.layout.item2,
                        new String[]{"parti", "siege"},
                        new int[]{R.id.parti, R.id.siege});
                lsFinal.setAdapter(adapter);
            }

    /**
     * cette méthode gére le button de retour
     */
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Exit")
                .setMessage("Etes-vous sûr.e de vouloir quitter ?")
                .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    }
                }).setNegativeButton("Non", null).show();
    }

    //cette méthode gére le button de generer pdf
    public void pdfEncours (){
        new AlertDialog.Builder(this)
                .setMessage("Cette fonctionnalité est en cours de réalisation. Merci")

                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.ok, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
        }

    //cette méthode gére le button de recommmencer
    public void recommencer (){
        new AlertDialog.Builder(this)
                .setMessage("Etes-vous sûr.e de vouloir recommencer ?")
                .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                    }
                })

                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton("Non", null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

}




