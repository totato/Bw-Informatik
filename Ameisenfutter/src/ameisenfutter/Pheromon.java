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
public class Pheromon {
   private final int startIntensity = 50;
    private int intensity;
    
    public Pheromon(int intensity){
         this.intensity = intensity;
    }

    public int getIntensity() {
        return intensity;
    }

    public void reduceIntensity() {
        intensity --;
    } 
}
