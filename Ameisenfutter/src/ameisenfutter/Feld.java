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
    private boolean isDuftstoffVerspruet; // gibt an, ob ein Feld mit Duftstoffen versehen wurde
    private int futterportion; // Anzahl der auf dem Feld liegenden Portion Futter

    //Konstruktor der Klasse Feld
    public Feld(int X, int Y, boolean isDuftstoffVerspruet, int futterportion) {
        this.X = X;
        this.Y = Y;
        this.futterportion = futterportion;
        this.isDuftstoffVerspruet = isDuftstoffVerspruet;
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
    public void versprueheDuft(){
        isDuftstoffVerspruet = true;
    }

}
