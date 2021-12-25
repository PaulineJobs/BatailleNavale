package BatailleNavale;

public class OccupException extends Exception {
    public OccupException() {
        super("Cet emplacement est déjà occupé par un navire, Veuillez choisir un autre emplacement\n") ;
    }
}