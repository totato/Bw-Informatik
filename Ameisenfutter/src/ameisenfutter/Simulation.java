package ameisenfutter;

import java.util.Random;

/**
 *
 * @author Marc und Vika
 */
public class Simulation {

    private final int gesamtAmeisen = 100;
    private Ameise[] ameisenKolonie;

    private final int futterproQuelle = 50;
    private final int futterquellenproFeld = 5; // gemeint ist das 2D Array
    private final int feldGroesse = 500; // quadratisch
    private final int[] nestPosition = new int[2];
    private Feld[][] feld;
 
    public Simulation() {
        feldErzeugen(feldGroesse, futterquellenproFeld);
        ameisenSpawnen(gesamtAmeisen);
    }

    private Feld[][] feldErzeugen(int groesse, int futterquellen) {
        feld = new Feld[groesse][groesse];
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
            int x = r.nextInt(groesse + 1);
            int y = r.nextInt(groesse + 1);
            if (feld[x][y].getFutterportion() <= 0 && !feld[x][y].isNest()) {
                feld[x][y].setFutterportion(futterproQuelle);

            }

        }
        return feld;

    }

    /*
     Erzeugt die Ameisen
     */
    private Ameise[] ameisenSpawnen(int anzahl) {
        ameisenKolonie = new Ameise[anzahl];
        for (int i = 0; i < anzahl; i++) {
            ameisenKolonie[i] = new Ameise(false, nestPosition[0], nestPosition[1]);
        }
        return ameisenKolonie;

    }

    private void futtersuche(Ameise ameise) {
    // prüfen ob Duftpunkte in der Nähe sind
        for (int i = 0; i < 3; i++) { // 3 , da 3x3 Feld überprüft wird 
            for (int j = 0; j < ameisenKolonie.length; j++) {
                
                
            }
            
            
        }
    }

    private void nachHause(Ameise ameise) {
        Random r = new Random();
        if (r.nextInt(1) >= 0.5 && ameise.getX() != nestPosition[0]) { // richtung x Koordinate
            if (nestPosition[0] < ameise.getX()) {
                ameise.setX(ameise.getX() - 1);
            } else {
                ameise.setX(ameise.getX() + 1);
            }
        } else if (ameise.getY() != nestPosition[1]) { // richtung y Koordinate
            if (nestPosition[0] < ameise.getY()) {
                ameise.setY(ameise.getY() - 1);
            } else {
                ameise.setY(ameise.getY() + 1);
            }
        }
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

    public Ameise[] getAmeisenKolonie() {
        return ameisenKolonie;
    }

    public Feld[][] getFeld() {
        return feld;
    }

}
