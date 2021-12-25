package BatailleNavale;

import java.util.ArrayList;
import java.util.Scanner;

public class Equipe {
    //attributs
    int numEquipe;
    Nature natureEq;
    ArrayList<Navire> listeNav;
    //constructeur

    public Equipe(int numEquipe, Nature natureEq) {
        this.numEquipe = numEquipe;
        this.natureEq=natureEq;
        this.listeNav = new ArrayList<Navire>();
    }

    public Navire ChoixNavire (){
        int exception = 1;
        while(exception !=0) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Choisissez un navire parmi ceux que vous possedez : ");
            System.out.println(this.listeNav.toString());
            String choix = scanner.next();
            for (Navire nav : this.listeNav) {
                if (choix.equals(nav.toString())) {
                    exception=0;
                    return (nav);
                }
            }
            System.out.println("Vous n'avez pas choisi un navire que vous possedez, recommencez");
            exception=1;
        }
        return null;
    }




    public boolean aPerdu(){

        if (this.listeNav.size()==0){
            return true;
        }
        return false;
    }

}
