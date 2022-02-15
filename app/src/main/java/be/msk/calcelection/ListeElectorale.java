package be.msk.calcelection;

/**
 * Créer une liste electorale
 */

public class ListeElectorale {
    /**
     * identité de la liste
     */
    private int id;
    /**
     * nom de la liste
     */
    private String nom;
    /**
     * nombre de voix de la liste
     */
    private int voix;
    /**
     * nombre de sièges de la liste
     */
    private int siege;
    /**
     * indique si la liste est éliminée ou non
     */
    private boolean elimine;

    /**
     * constructeur par défaut
     */
    public ListeElectorale() {
    }

    /**
     *
     * @param id int : identité de la liste
     * @param nom String : le nom de la liste
     * @param voix int : son nombre de voix
     * @param siege int : son nombre de sieges
     * @param elimine boolean : son état éliminé ou non
     */
    public ListeElectorale(int id, String nom, int voix, int siege, boolean elimine) {
        this.id = id;
        this.nom = nom;
        this.voix = voix;
        this.siege = siege;
        this.elimine = elimine;
    }

    /**
     *
     * @return int : l'identifiant de la liste
     */
    public int getId() {
        return id;
    }

    /**
     * initialise l'identifiant de liste
     * @param id int : identifiant de la liste
     * @throws ElectionsException si id<1
     */

    public void setId(int id) {
        if(id<1){
            throw new ElectionsException("l'id doit etre supérieur à 1 ");
        }else{
            this.id = id;
        }

    }

    /**
     *
     * @return String : le nom de la liste
     */
    public String getNom() {
        return nom;
    }

    /**
     70. * initialise le nom de la liste
     71. * @param nom String : nom de la liste
     72. * @throws ElectionsException si le nom est vide ou blanc
     73. */

    public void setNom(String nom) {
        if(!(nom.isEmpty()) && nom.trim() != ""){
            this.nom = nom;
        }else{
            throw new ElectionsException("le nom ne doit pas être vide..");
        }

    }

    /**
     *
     * @return int : le nombre de voix de la liste
     */
    public int getVoix() {
        return voix;
    }

    /**
     * initialise le nombre de voix de la liste
     * @param voix : le nombre de voix de la liste
     */
    public void setVoix(int voix) {
        this.voix = voix;
    }

    /**
     *
     * @return int : le nombre de sièges de la liste
     */
    public int getSiege() {
        return siege;
    }

    /**
     * fixe le nombre de sièges de la liste
     * @param siege int : le nombre de siège de la liste
     */
    public void setSiege(int siege) {
        this.siege = siege;
    }

    /**
     *
     * @return boolean : valeur du champs elimine
     */
    public boolean isElimine() {
        return elimine;
    }

    /**
     *
     * @param elimine boolean : indique si la liste est éliminée ou non
     */
    public void setElimine(boolean elimine) {
        this.elimine = elimine;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "ListeElectorale{}";
    }
}
