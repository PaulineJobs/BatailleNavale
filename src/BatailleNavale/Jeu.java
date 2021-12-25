package BatailleNavale;



import java.lang.reflect.Array;
import java.util.ArrayList;

public class Jeu {
    //attribut
    Plateau plateau;
    ArrayList<Equipe> listeEquipe;
    ArrayList<Commande> listecommande;

    //constructeurs

    public Jeu(Plateau plateau) {
        this.listecommande= new ArrayList<Commande>();
        this.listeEquipe= new ArrayList<Equipe>();
        this.plateau = plateau;
    }

    //methodes

    public void initJeu() {
        ConsoleColors color=new ConsoleColors();
        System.out.println(" DEBUT DE LA PARTIE ");
        System.out.print("\n");
        System.out.println("Vous jouerez à la Bataille Navale sur le plateau suivant : ");

        //plateau vide
        System.out.print("\n");
        System.out.println(this.plateau);
        System.out.print("\n");


        //Joueur 1
        System.out.println("Joueur 1 :");
        EqBataillon equipe1 = new EqBataillon(1);
        Commande commande1=new Commande (this.plateau,equipe1);

        equipe1.initEquipe();
        commande1.placerNavire();

        System.out.print("\n");
        System.out.print(this.plateau);
        System.out.print("\n");


        //Joueur 2
        System.out.println("Joueur 2 :");
        EqPecheur equipe2 = new EqPecheur(2);
        Commande commande2=new Commande (this.plateau,equipe2);

        equipe2.initEquipe();
        commande2.placerNavire();

        System.out.print("\n");
        System.out.print(this.plateau);
        System.out.print("\n");


        //Joueur 3
        System.out.println("Joueur 3 :");
        EqBataillon equipe3 = new EqBataillon(3);
        Commande commande3=new Commande (this.plateau,equipe3);

        equipe3.initEquipe();
        commande3.placerNavire();

        System.out.print("\n");
        System.out.print(this.plateau);
        System.out.print("\n");

        this.listecommande.add(commande1);
        this.listecommande.add(commande2);
        this.listecommande.add(commande3);

        this.listeEquipe.add(equipe1);
        this.listeEquipe.add(equipe2);
        this.listeEquipe.add(equipe3);

        System.out.println("Que le jeu commence ! ");
        System.out.print("\n");

        int jeu=1;
        while(jeu==1){
            for (int i = 0; i < this.listecommande.size() ; i++) {
                this.jouerUnTour(this.listecommande.get(i));
            }

        }



    }


    void jouerUnTour(Commande commande) {

        System.out.println("Joueur " + commande.equipe.numEquipe);

        //Choix du navire
        Navire choixNav = commande.equipe.ChoixNavire();
        System.out.print("Vous avez choisi le navire  : ");
        System.out.print(choixNav);
        System.out.print("\n");
        int choix;

        //Choix de l'action

        //si le navire ne peut rien faire
        if ((commande.navireAtteignables(choixNav).size() == 0)&&(commande.casesAtteignables(choixNav).size()==0)){
            System.out.println("Vous ne pouvez ni attaquer, ni vous déplacer avec ce navire, Tant pis pour vous, vous passez votre tour ");
        }


        //si le navire ne peut que se deplacer
        if ((commande.navireAtteignables(choixNav).size() == 0)&&(commande.casesAtteignables(choixNav).size()!=0)) {
            //Si le navire ne peut pas attaquer de cible
            System.out.println("Aucune cible en vue, vous ne pouvez que deplacer ce navire ");
            if ((choixNav.typeNav == TypeNav.SOUSMARIN) && (choixNav.etat == "E")) {
                System.out.println("Votre sous marin " + choixNav.toString() + " est ENDOMMAGÉ. Vous ne pouvez pas le déplacer ");
            } else {
                Case deplacement = commande.ChoixCase(commande.casesAtteignables(choixNav));
                System.out.println("Navire déplacé ! ");
                try {
                    commande.deplacerNavire(choixNav, deplacement.x, deplacement.y);
                } catch (OccupException e) {
                    System.out.println("Impossible de deplacer ce navire");
                }
            }
        }
        //si le navire ne peut qu'attaquer
        if ((commande.navireAtteignables(choixNav).size() != 0)&&(commande.casesAtteignables(choixNav).size()==0)){
            System.out.println("Vous êtes bloqué, vous ne pouvez qu'attaquer ");
            Navire cible = commande.ChoixCible(commande.navireAtteignables(choixNav));
            commande.attaquercible(cible,choixNav);
            for (Commande c : listecommande) {
                c.miseAJourNav();
            }
        }


        //Si le navire peut attaquer une cible et se déplacer
        if ((commande.navireAtteignables(choixNav).size() != 0) && (commande.casesAtteignables(choixNav).size()!=0)) {
            choix = commande.ChoixAction();
            if (choix == 1) {
                System.out.println("Vous avez choisi d'attaquer un autre navire ");
                Navire cible = commande.ChoixCible(commande.navireAtteignables(choixNav));
                commande.attaquercible(cible,choixNav);
                for (Commande c : listecommande) {
                    c.miseAJourNav();
                }
            }
            if ((choix == 2)) {
                System.out.println("Vous avez choisi de deplacer votre navire ");
                if ((choixNav.typeNav == TypeNav.SOUSMARIN) && (choixNav.etat == "E")) {
                    System.out.println("Votre sous marin " + choixNav.toString() + " est ENDOMMAGÉ. Vous ne pouvez pas le déplacer ");
                } else {
                    Case deplacement = commande.ChoixCase(commande.casesAtteignables(choixNav));
                    System.out.println("Navire déplacé ! ");
                    try {
                        commande.deplacerNavire(choixNav, deplacement.x, deplacement.y);
                    } catch (OccupException e) {
                        System.out.println("Impossible de deplacer ce navire");
                    }
                }
            }
        }

        System.out.print("\n");
        System.out.println("Capitaines, voici la situation !");
        System.out.print("\n");
        System.out.print(this.plateau);
        System.out.print("\n");



        //Si une equipe a perdu
        int index = 0;
        for (int i = 0; i < this.listeEquipe.size(); i++) {
            if (this.listeEquipe.get(i).aPerdu()) {
                index = i;
            }
        }
        if (this.listeEquipe.get(index).aPerdu()) {
            System.out.println(" L'equipe "+ this.listeEquipe.get(index).numEquipe + " a perdue toute sa flotte, elle est maintenant hors jeu !");
            listeEquipe.remove(listeEquipe.get(index));
        }
        if (listeEquipe.size()==2) {
            if (listeEquipe.get(0).listeNav.size() == listeEquipe.get(1).listeNav.size()) {
                System.out.println("C'est une egalité parfaite entre l'equipe " + listeEquipe.get(0).numEquipe + " et l'equipe " + listeEquipe.get(1).numEquipe + " !");
                System.out.println(" FIN DE LA PARTIE ");
                System.exit(0);
            } else {
                System.out.println("C'est une victoire écrasante pour l'équipe numero " + this.vainqueur().numEquipe + " !");
                System.out.println(" FIN DE LA PARTIE ");
                System.exit(0);
            }
        }
    }


    Equipe vainqueur() {
        Equipe vainqueur=listeEquipe.get(0);
        for (int j = 0; j < listeEquipe.size(); j++) {
            if (listeEquipe.get(j).listeNav.size()>vainqueur.listeNav.size()){
                vainqueur=listeEquipe.get(j);
            }
        }
        return vainqueur;
    }

    public static void main(String[] args) {
        Plateau monPlateau= new Plateau(5);
        Jeu jeu=new Jeu(monPlateau);
        jeu.initJeu();


    }
}
