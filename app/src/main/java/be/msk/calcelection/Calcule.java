package be.msk.calcelection;
/**
 * cette classe gère le calcule (l'lgorithme de fonctionement de l'application
 */

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class Calcule {


    int nbSiegeObtenu =0;
    int nbSiegeRest;
    float moyenneListe ;
    int nbTotalVoix =0;




    //La méthode qui eliminer les liste qui n'ont pas atteint les 5%
    public void calculer(int nbListe,int nbSiege){
        for(int i=0;i<nbListe;i++){
            nbTotalVoix += Etape2.listes[i].getVoix();
        }
        int total = 0;
        for(int i=0;i<nbListe;i++) {
            //On élimine la partie qui a moins de 5%
            if(((Etape2.listes[i].getVoix() * 100)/(nbTotalVoix)) < 5) {
                total = nbTotalVoix - Etape2.listes[i].getVoix();
                Etape2.listes[i].setElimine(true);
            }
        }
        nbTotalVoix = total;

    //Calcule nombre de siege ->Le calcule du quotient:
        int nbSiegeTemp = 0;
        for (int i = 0; i < nbListe; i++) {

            if (!Etape2.listes[i].isElimine()) {
                int aa = nbTotalVoix;
                int bb = nbSiege;
                int aabb = aa / bb;
                int cc = Etape2.listes[i].getVoix();
                if (aabb != 0) {
                    nbSiegeObtenu = (cc / aabb);
                    Etape2.listes[i].setSiege(nbSiegeObtenu);
                }

                if (nbSiegeObtenu > 0) {
                    nbSiegeTemp += nbSiegeObtenu;
                }
            }

        }
        nbSiegeRest = nbSiege - nbSiegeTemp;

        //Méthode qui calcule la moyenne de chaque liste
        if (nbSiegeRest != 0) {
            int j = 0;
            while (j < nbSiegeRest) {
                float max = 0;
                int indexMax = 0;
                for (int i = 0; i < nbListe; i++) {
                    if (!Etape2.listes[i].isElimine()) {
                        if ((Etape2.listes[i].getSiege() + 1) != 0) {
                            moyenneListe = (float) (Etape2.listes[i].getVoix() / (Etape2.listes[i].getSiege() + 1));
                            if (moyenneListe >= max) {
                                max = moyenneListe;
                                indexMax = i;
                            }
                        }

                    }
                }
                int nbSiegeAct = Etape2.listes[indexMax].getSiege();
                Etape2.listes[indexMax].setSiege(nbSiegeAct + 1);
                j++;

            }
        }

    }


}
