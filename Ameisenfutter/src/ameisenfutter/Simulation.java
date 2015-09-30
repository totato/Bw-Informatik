package ameisenfutter;

import java.util.Random;

/**
 *
 * @author Marc und Vika
 */
public class Simulation {

    private final int gesamtAmeisen = 100;
    
    private final int futterproQuelle = 50;
    private final int futterquellenproFeld = 5; // gemeint ist das 2D Array
    private final int feldGroesse = 500; // quadratisch
    private final int[] nestPosition = new int[2];

    public Simulation() {
        feldErzeugen(feldGroesse, futterquellenproFeld);
        ameisenSpawnen(gesamtAmeisen);
    }

    private Feld[][] feldErzeugen(int groesse, int futterquellen) {
        Feld[][] feld = new Feld[groesse][groesse];
        // Feld wird gefüllt, erst nur 'leere' Feld Objekte
        for (int i = 0; i < groesse; i++) {
            for (int j = 0; j < groesse; j++) {
                feld[i][j] = new Feld(0, 0, false);

            }

        }
        // Nest wird in die Mitte gesetzt
        feld[groesse / 2][groesse / 2].setNest(true);
        nestPosition[0] = groesse / 2;
        nestPosition[1] = groesse / 2;
        // Futterstellen werden zufällig verteielt
        Random r = new Random();
        for (int i = 0; i < futterquellen; i++) {
            int a = r.nextInt(groesse + 1);
            int b = r.nextInt(groesse + 1);
            if (feld[a][b].getFutterportion() <= 0 && !feld[a][b].isNest()) {
                feld[a][b].setFutterportion(futterproQuelle);

            }

        }
        return feld;

    }

    private Ameise[] ameisenSpawnen (int anzahl){
        Ameise[] ameisen = new Ameise[anzahl];
        return null;
        
    }
    
    private void futtersuche() {

    }

    private void nachHause() {
        Random r = new Random();
    }

    public int getGesamtAmeisen() {
        return gesamtAmeisen;
    }

    public int getFeldGroesse() {
        return feldGroesse;
    }

    public int[] getNestPosition() {
        return nestPosition;
    }
    
    
}
