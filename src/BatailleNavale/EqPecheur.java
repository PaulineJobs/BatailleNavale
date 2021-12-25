package BatailleNavale;
import java.util.Scanner;

public class EqPecheur extends Equipe{
    //attributs
    int nbrChalutier;
    //constructeurs

    public EqPecheur(int numEquipe) {
        super(numEquipe, Nature.NEUTRE);
        this.nbrChalutier=1;
    }


    //methodes
    void initEquipe() {
        for (int i = 1; i <= this.nbrChalutier; i++) {
            NavChalutier navire = new NavChalutier(i, this.numEquipe, 0, 0);
            this.listeNav.add(navire);
        }
    }


}
