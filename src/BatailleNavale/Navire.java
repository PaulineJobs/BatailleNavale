package BatailleNavale;
import java.awt.*;

public class Navire {
    //attributs
    TypeNav typeNav;
    int numBateau;
    int numEquipe;
    String etat;
    int portee;
    int x;
    int y;

    //constructeurs

    public Navire(TypeNav typeNav, int numBateau, int numEquipe, int x, int y,int portee) {
        this.etat = "V";
        this.numBateau = numBateau;
        this.typeNav = typeNav;
        this.numEquipe = numEquipe;
        this.portee=portee;
        this.x=x;
        this.y=y;
    }


    public String toString() {
        String s = "";
        if (this.typeNav == TypeNav.CHALUTIER) {
            s+= this.numEquipe + "C" + this.numBateau + this.etat;
        } else if (this.typeNav == TypeNav.DESTROYER){
            s+= this.numEquipe + "D" + this.numBateau + this.etat;
        } else {
            s+= this.numEquipe + "S" + this.numBateau + this.etat;
        }
        return s;
    }
    //methodes

}
