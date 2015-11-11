/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ameisenfutter;

import java.util.ArrayList;

/**
 *
 * @author Vika
 */
public class Feld {
 private ArrayList<Pheromon> pheromones;
     private final int startIntensity = 400;
    private int futterportion; // Anzahl der auf dem Feld liegenden Portion Futter
    private boolean nest; // gibt an, ob es sich um das Nest der Ameisen handelt

    //Konstruktor der Klasse Feld
    public Feld( ArrayList<Pheromon> pheromones, int futterportion, boolean nest ) {
        this.futterportion = futterportion;
        this.pheromones = pheromones;
        this.nest = nest;
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

    public ArrayList<Pheromon> getPheromones() {
        return pheromones;
    }

 
    public int getFutterportion() {
        return futterportion;
    }

    public boolean isNest() {
        return nest;
    }

    public void setPheromones(ArrayList<Pheromon> pheromones) {
        this.pheromones = pheromones;
    }
    public void addPheromon(){
        pheromones.add(new Pheromon(startIntensity));
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
    public void reducePheromones(){
        for (int i = 0; i < pheromones.size(); i++) {
             pheromones.get(i).reduceIntensity();
            if(pheromones.get(i).getIntensity()<1){
                pheromones.remove(i);
            }
        }
    }
}
