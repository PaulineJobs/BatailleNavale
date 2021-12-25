package BatailleNavale;

import java.util.ArrayList;
import java.util.Scanner;

public class Commande {

    //attributs
    Plateau plateau;
    Equipe equipe;


    //constructeur

    public Commande(Plateau plateau, Equipe equipe) {
        this.equipe=equipe;
        this.plateau=plateau;
    }

    //methodes


    public int ChoixAction(){
        int exception=1;
        while(exception!=0) {
            Scanner scanner=new Scanner(System.in);
            System.out.println("Que voulez vous faire ? ATTAQUER ou DEPLACER ?");
            String choix ="";
            choix = scanner.next();

            if (choix.equals("ATTAQUER") ) {
                exception=0;
                return 1;
            }

            if (choix.equals("DEPLACER")) {
                exception=0;
                return 2;
            }
            System.out.println("S'il vous plait, choisissez parmi les options proposées");
            exception=1;
        }
        return 0;
    }

    void placerNavire()  {
        for (Navire navire : this.equipe.listeNav) {
            int exception=1;
            while(exception==1) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Placez votre navire " + (this.equipe.listeNav.indexOf(navire) + 1) + " : Un " + navire.typeNav);
                System.out.print("x = ");
                navire.x = scanner.nextInt();
                System.out.print("y = ");
                navire.y = scanner.nextInt();
                if (((navire.y < plateau.taille) && (navire.x < plateau.taille) && (navire.y >= 0) && (navire.x >= 0))) {
                    try {
                        this.deplacerNavire(navire, navire.x, navire.y);
                        exception = 0;
                    }catch(OccupException e){
                        exception = 1;
                    }
                } else {
                    exception = 1;
                    System.out.println("Veuillez choisir une case du plateau");
                }
            }
        }
    }

    void deplacerNavire(Navire navire,int x,int y) throws OccupException{
        if( ((navire.typeNav==TypeNav.SOUSMARIN)&&(this.plateau.plateau[x][y].NavireBas==null)) || ((navire.typeNav!=TypeNav.SOUSMARIN)&&(this.plateau.plateau[x][y].NavireHaut==null)) ){
            this.plateau.plateau[navire.x][navire.y].removeNavire(navire);
            this.plateau.plateau[x][y].addNavire(navire);

        } else {
            System.out.println("Impossible de déplacer le navire en ("+ this.plateau.plateau[x][y].x +","+this.plateau.plateau[x][y].y +"), Un navire est déja à cet emplacement");
            throw new OccupException();

            }

    }


    public ArrayList<Navire> navireAtteignables(Navire navire) {

        ArrayList<Navire> listeNavAtteignable = new ArrayList<Navire>();
        int portee = navire.portee;

        if (navire.typeNav == TypeNav.DESTROYER) {
            for (int i = 0; i <= portee; i++) {
                if ((navire.x+ i< plateau.taille)&&(plateau.plateau[navire.x + i][navire.y].NavireHaut != null)) {
                    if (!listeNavAtteignable.contains(plateau.plateau[navire.x + i][navire.y].NavireHaut)){
                        listeNavAtteignable.add(plateau.plateau[navire.x + i][navire.y].NavireHaut);
                    }
                }
            }

            for (int i = 0; i <= portee; i++) {
                if ((navire.x - i>=0) && (plateau.plateau[navire.x - i][navire.y].NavireHaut != null)) {
                    if(!listeNavAtteignable.contains(plateau.plateau[navire.x - i][navire.y].NavireHaut)) {
                        listeNavAtteignable.add(plateau.plateau[navire.x - i][navire.y].NavireHaut);
                    }
                }
            }

            for (int i = 0; i <= portee; i++) {
                if ((navire.y+ i< plateau.taille)&&(plateau.plateau[navire.x][navire.y + i].NavireHaut != null)) {
                    if(!listeNavAtteignable.contains(plateau.plateau[navire.x][navire.y + i].NavireHaut)){
                        listeNavAtteignable.add(plateau.plateau[navire.x][navire.y + i].NavireHaut);
                    }
                }
            }

            for (int i = 0; i <= portee; i++) {
                if ((navire.y- i>=0)&&(plateau.plateau[navire.x][navire.y - i].NavireHaut != null)) {
                    if(!listeNavAtteignable.contains((plateau.plateau[navire.x][navire.y - i].NavireHaut))){
                        listeNavAtteignable.add(plateau.plateau[navire.x][navire.y - i].NavireHaut);
                    }
                }
            }
            listeNavAtteignable.remove(listeNavAtteignable.get(0));
        }

        if (navire.typeNav == TypeNav.SOUSMARIN) {

            for (int i = 0; i <= portee; i++) {
                if ((navire.x+ i< plateau.taille)&&(plateau.plateau[navire.x + i][navire.y].NavireBas != null)) {
                    if(!listeNavAtteignable.contains(plateau.plateau[navire.x + i][navire.y].NavireBas)) {
                        listeNavAtteignable.add(plateau.plateau[navire.x + i][navire.y].NavireBas);
                    }
                }
            }

            for (int i = 0; i <= portee; i++) {
                if ((navire.x - i>=0)&&(plateau.plateau[navire.x - i][navire.y].NavireBas != null)) {
                    if(!listeNavAtteignable.contains(plateau.plateau[navire.x - i][navire.y].NavireBas)) {
                        listeNavAtteignable.add(plateau.plateau[navire.x - i][navire.y].NavireBas);
                    }
                }
            }

            for (int i = 0; i <= portee; i++) {
                if ((navire.y+ i< plateau.taille)&&(plateau.plateau[navire.x][navire.y + i].NavireBas != null)) {
                    if(!listeNavAtteignable.contains((plateau.plateau[navire.x][navire.y + i].NavireBas))) {
                        listeNavAtteignable.add(plateau.plateau[navire.x][navire.y + i].NavireBas);
                    }
                }
            }

            for (int i = 0; i <= portee; i++) {
                if ((navire.y- i>=0)&&(plateau.plateau[navire.x][navire.y - i].NavireBas != null)) {
                    if(!listeNavAtteignable.contains(plateau.plateau[navire.x][navire.y - i].NavireBas)) {
                        listeNavAtteignable.add(plateau.plateau[navire.x][navire.y - i].NavireBas);
                    }
                }
            }
            listeNavAtteignable.remove(listeNavAtteignable.get(0));
        }

        if (navire.typeNav == TypeNav.CHALUTIER){
            if (plateau.plateau[navire.x][navire.y].NavireBas != null) {
                    listeNavAtteignable.add(plateau.plateau[navire.x][navire.y].NavireBas);

            }
        }

        ArrayList<Integer> index = new ArrayList<Integer>();

        for (int i = 0; i < listeNavAtteignable.size(); i++) {
            if (listeNavAtteignable.get(i).numEquipe == navire.numEquipe) {
                index.add(i);
            }
        }

        for (int i = 0; i < index.size() ; i++) {
            listeNavAtteignable.remove(index.get(i).intValue());
        }

        return listeNavAtteignable;
    }

    public Navire ChoixCible (ArrayList<Navire> listeNavAtteignables){
        int exception = 1;
        while(exception !=0) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Choisissez votre cible ! : ");
            System.out.println(listeNavAtteignables.toString());
            String choix = scanner.next();
            for (Navire nav : listeNavAtteignables) {
                if (choix.equals(nav.toString())) {
                    exception=0;
                    return(nav);
                }
            }
            System.out.println("Vous n'avez pas choisi une cible atteignable ");
            exception=1;
        }
        return null;
    }


    public void attaquercible(Navire navirecible, Navire navire){
        System.out.println("PAM ! Touché ! ");
        System.out.print("Le navire "+ navirecible.toString()+" est maintenant ");
        if ((navirecible.typeNav==TypeNav.SOUSMARIN)&&(navire.typeNav==TypeNav.CHALUTIER)) {
                navirecible.etat = "E";
                System.out.println(" ENDOMMAGÉ ");
        } else {
            navirecible.etat="M";
            System.out.println(" MORT ");
        }

    }


    public void miseAJourNav(){
        ArrayList<Integer> index = new ArrayList<Integer>();
        for (int i = 0; i < this.equipe.listeNav.size(); i++) {
            if (this.equipe.listeNav.get(i).etat=="M") {
                index.add(i);
                this.plateau.plateau[this.equipe.listeNav.get(i).x][this.equipe.listeNav.get(i).y].removeNavire(this.equipe.listeNav.get(i));
            }
            if(this.equipe.listeNav.get(i).etat=="E"){
                this.plateau.plateau[this.equipe.listeNav.get(i).x][this.equipe.listeNav.get(i).y].addNavire(this.equipe.listeNav.get(i));
            }
        }

        for (int i = 0; i <index.size(); i++) {
            this.equipe.listeNav.remove(index.get(i).intValue());
        }
    }

        public ArrayList<Case> casesAtteignables(Navire navire) {
            ArrayList<Case> casesAtteignables = new ArrayList<Case>();

            if (navire.typeNav == TypeNav.SOUSMARIN) {
                if ((navire.x - 1 >= 0) && (plateau.plateau[navire.x - 1][navire.y].NavireBas == null)) {
                    casesAtteignables.add(plateau.plateau[navire.x - 1][navire.y]);
                }

                if ((navire.x + 1 < plateau.taille) && (plateau.plateau[navire.x + 1][navire.y].NavireBas == null)) {
                    casesAtteignables.add(plateau.plateau[navire.x + 1][navire.y]);
                }

                if ((navire.y -1 >= 0) && (plateau.plateau[navire.x][navire.y-1].NavireBas == null)) {
                    casesAtteignables.add(plateau.plateau[navire.x][navire.y-1]);
                }

                if ((navire.y + 1 < plateau.taille) && (plateau.plateau[navire.x][navire.y +1].NavireBas == null)) {
                    casesAtteignables.add(plateau.plateau[navire.x][navire.y+1]);
                }

            }else {
                if ((navire.x - 1 >= 0) && (plateau.plateau[navire.x - 1][navire.y].NavireHaut == null)) {
                    casesAtteignables.add(plateau.plateau[navire.x - 1][navire.y]);
                }

                if ((navire.x + 1 < plateau.taille) && (plateau.plateau[navire.x + 1][navire.y].NavireHaut == null)) {
                    casesAtteignables.add(plateau.plateau[navire.x + 1][navire.y]);
                }

                if ((navire.y -1 >= 0) && (plateau.plateau[navire.x][navire.y-1].NavireHaut == null)) {
                    casesAtteignables.add(plateau.plateau[navire.x][navire.y-1]);
                }

                if ((navire.y + 1 < plateau.taille) && (plateau.plateau[navire.x][navire.y +1].NavireHaut == null)) {
                    casesAtteignables.add(plateau.plateau[navire.x][navire.y+1]);
                }

            }
            return casesAtteignables;
        }



    public Case ChoixCase (ArrayList<Case> listeCaseAtteignables) {
        int exception = 1;
        while (exception != 0) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Vous pouvez atteindre les cases suivantes : ");
            System.out.print("[");
            for (Case c : listeCaseAtteignables) {
                System.out.print(c.toString2());
                System.out.print(",");
            }
            System.out.println("]");

            System.out.println("Sur quelle case voulez-vous aller  ? ");
            String choix = scanner.next();
            for (Case c : listeCaseAtteignables) {
                if (choix.equals(c.toString2())) {
                    exception = 0;
                    return (c);
                }
            }
            System.out.println("Vous n'avez pas choisi une case atteignable ");
            exception = 1;
        }
        return null;
    }
}
