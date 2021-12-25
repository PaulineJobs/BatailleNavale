package BatailleNavale;

public class LimitException extends Exception {
    public LimitException() {
        super("Cette case est hors du plateau, veuillez choisir une case du plateau\n") ;
    }
}
