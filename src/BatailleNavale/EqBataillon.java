package BatailleNavale;
import java.util.Scanner;
import java.awt.*;


public class EqBataillon extends Equipe{
    //attributs
    int nbrDestroyers;
    int nbrSousMarins;
    //constructeur

    public EqBataillon(int numEquipe) {
        super(numEquipe, Nature.MILITAIRE);
        this.nbrDestroyers=1;
        this.nbrSousMarins=1;
    }

    void initEquipe() {
        for (int i = 1; i <= this.nbrDestroyers; i++) {
            NavDestroyer navire = new NavDestroyer(i, this.numEquipe, 0, 0);
            this.listeNav.add(navire);
        }
        for (int i = 1; i <= this.nbrSousMarins; i++) {
            NavSousMarin navire = new NavSousMarin(nbrDestroyers + i, this.numEquipe, 0, 0);
            this.listeNav.add(navire);
        }
    }


    //methodes
}
