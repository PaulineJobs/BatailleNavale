package BatailleNavale;
import java.lang.String;


public class Case {
    //attributs
    int x;
    int y;
    String etatHaut;
    String etatBas;
    Navire NavireHaut;
    Navire NavireBas;


    //constructeurs
    public Case(int x, int y) {
        this.etatHaut = "....";
        this.etatBas = "....";
        this.NavireBas = null;
        this.NavireHaut = null;
        this.x=x;
        this.y=y;
    }

    //methodes

    public String toString() {
        String s = "";
        s += this.etatHaut;
        s += "/";
        s += this.etatBas;
        return s;
    }

    public void addNavire(Navire navire) {
        if (navire.typeNav == TypeNav.SOUSMARIN) {
            this.NavireBas = navire;
            this.NavireBas.x=this.x;
            this.NavireBas.y=this.y;
            this.etatBas = navire.numEquipe + "S" + navire.numBateau + navire.etat;
        }
        if (navire.typeNav == TypeNav.DESTROYER) {
            this.NavireHaut = navire;
            this.NavireHaut.x=this.x;
            this.NavireHaut.y=this.y;
            this.etatHaut = navire.numEquipe + "D" + navire.numBateau + navire.etat;
        }
        if (navire.typeNav == TypeNav.CHALUTIER) {
            this.NavireHaut = navire;
            this.NavireHaut.x=this.x;
            this.NavireHaut.y=this.y;
            this.etatHaut = navire.numEquipe + "C" + navire.numBateau + navire.etat;
        }

    }

    public void removeNavire(Navire navire) {
        if (navire.typeNav == TypeNav.SOUSMARIN) {
            this.NavireBas = null;
            this.etatBas = "....";
        } else {
            this.NavireHaut = null;
            this.etatHaut = "....";
        }

    }

    public String toString2() {
        String s="("+this.x+","+this.y+")";
        return s;
    }
}
