/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ameisenfutter;

/**
 *
 * @author Vika
 */
public class Feld {
 private int X; //X-Koordinate des Feldes innerhalb des zweidimensionalen Arrays
    private int Y; //Y-Koordinate des Feldes innerhalb des zweidimensionalen Arrays
    private int DuftstoffEinheiten; // gibt an, mit wie viel Duftstoff ein Feld versehen wurde
    private int futterportion; // Anzahl der auf dem Feld liegenden Portion Futter
    private boolean nest; // gibt an, ob es sich um das Nest der Ameisen handelt

    //Konstruktor der Klasse Feld
    public Feld( int isDuftstoffVerspruet, int futterportion, boolean nest ) {
        this.X = X;
        this.Y = Y;
        this.futterportion = futterportion;
        this.DuftstoffEinheiten = isDuftstoffVerspruet;
    }

    /**
     * Diese Methode verringert die Futterportion auf diesem Feld, solange 
     * sie vorhanden ist. 
     * Sie wird benutzt um die Portion zu verringern, sobald eine Ameise sich
     * in diesem Feld befindet.
     * 
     */
    public void portionAufheben() {
        if (futterportion > 0) {
            futterportion--;
        }
    }
    
    /**
     * 
     * 
     */
    public void versprueheDuft(){ // brauchen wir nicht
        DuftstoffEinheiten = 10;
    }

    public int getDuftstoffEinheiten() {
        return DuftstoffEinheiten;
    }

    public int getFutterportion() {
        return futterportion;
    }

    public boolean isNest() {
        return nest;
    }

    public void setDuftstoffEinheiten(int DuftstoffEinheiten) {
        this.DuftstoffEinheiten = DuftstoffEinheiten;
    }

    public void setFutterportion(int futterportion) {
        this.futterportion = futterportion;
    }
     public void futterportionAbziehen() {
        this.futterportion = futterportion-1;
    }

    public void setNest(boolean nest) {
        this.nest = nest;
    }
}
