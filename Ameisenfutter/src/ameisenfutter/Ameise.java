package ameisenfutter;

/**
 *
 * @author Marc und Vika
 */
public class Ameise {

    private boolean traegtFutter;
    private int x;
    private int y;

    public Ameise(boolean traegtFutter , int x , int y) {
        this.traegtFutter = false;
        this.x = x;
        this.y = y;
    }

    public boolean isTraegtFutter() {
        return traegtFutter;
    }

    public void setTraegtFutter(boolean traegtFutter) {
        this.traegtFutter = traegtFutter;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    
}
