package BatailleNavale;

import java.util.Arrays;

public class Plateau {
    //attributs
    int taille;
    Case[][] plateau;

    //constructeurs

    public Plateau(int n) {
        if (n>=1) {
            this.taille = n;
            this.plateau = new Case[this.taille][this.taille];
            for (int i = 0; i < this.taille; i++) {
                for (int j = 0; j < this.taille; j++) {
                    plateau[i][j] = new Case(i,j);
                }
            }
        }
    }

    //methodes

    public String toString() {
        String s="  ";
        for (int j=0; j<this.taille;j++){
            s+="     "+j+"    ";
        }
        s+="\n";
        s+="  -";
        for (int j=0; j<this.taille;j++){
            s+="----------";
        }
        s+="\n";

        for (int i=0; i<this.taille;i++){
            s+=i+" ";
            for (int j=0; j<this.taille;j++){
                s+="|";
                s+=plateau[i][j].toString();
            }
            s+="| \n";
        }

        s+="  -";
        for (int j=0; j<this.taille;j++){
            s+="----------";
        }
        s+="\n";

        return s;
    }



}
