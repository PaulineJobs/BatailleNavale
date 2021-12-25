package BatailleNavale;

public class NavDestroyer extends Navire{
    public NavDestroyer( int numBateau, int numEquipe, int x, int y) {
        super(TypeNav.DESTROYER, numBateau, numEquipe, x, y,2);
    }
}
