package be.msk.calcelection;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;



import java.util.ArrayList;
import java.util.HashMap;

public class Etape3 extends AppCompatActivity  {

    ListView ls;
    HashMap<String,String> map;
    Params p = new Params();
    Button calculerR;
    String str;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etape3);


        ls =findViewById(R.id.lstFinal);
        calculerR =findViewById(R.id.calculer);

        //récuperer les infos stocker au SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("key1" ,MODE_PRIVATE);
        int nbListe = sharedPreferences.getInt("nbListe",0);




        //Créer une Hashmap et remplir

            p.values.clear();
            for(int i=0;i<nbListe;i++){
                map= new HashMap<String,String>();
                String nomTmp = Etape2.listes[i].getNom();
                map.put("nomParti","Nom du parti      :      "+nomTmp);
                int voixTmp =Etape2.listes[i].getVoix();
                str = String.valueOf(voixTmp);
                map.put("nbVoix","Nombre de voix :      " +str);
                //ajouter la Hashmap a un arrayListe
                p.values.add(map);

            }


        //adapter
        SimpleAdapter adapter = new SimpleAdapter(Etape3.this, p.values,
                R.layout.item,
                new String[]{"nomParti", "nbVoix"},
                new int[]{R.id.nomDeListe, R.id.nbVoix});
        ls.setAdapter(adapter);


        //les details de la parti -> pour puvoir modifier les données de la parti avant le calcule
        ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent jj =new Intent(getApplicationContext(),Detail.class);
                jj.putExtra("position",position);
                startActivity(jj);
                finish();
            }
        });



        //le button pour calculer
        calculerR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent kk =new Intent(Etape3.this,CountDown.class);
                startActivity(kk);
                finish();
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