package ameisenfutter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

/**
 *
 * @author Marc und Vika
 */
public class Simulation {

    private final int gesamtAmeisen = 200;
    private Ameise[] ameisenKolonie;

    private final int futterproQuelle = 50;
    private final int futterquellenaufFeld = 50; // gemeint ist das 2D Array
    private final int feldGroesse = 500; // quadratisch
    private int duftstoffZeit = 400;
    private final int[] nestPosition = new int[2];
    private HashMap< Integer, Feld> hm;
    //private Feld[][] feld;
    private int futterImNest = 0;
    private int[][] moves = {{0, 1, 0, -1}, {1, 0, -1, 0}};

    public Simulation() {
        feldErzeugen(feldGroesse, futterquellenaufFeld);
        ameisenSpawnen(gesamtAmeisen);

    }

    public void los() {
        //   while (futterImNest < futterproQuelle * futterquellenaufFeld) {
        // Duftstoffe verschwinden mit jedem Durchlauf
        for (Integer pos : new HashSet<>(hm.keySet())) {
            hm.get(pos).reducePheromones();
            if (hm.get(pos).getPheromones().isEmpty() && !hm.get(pos).isNest() && hm.get(pos).getFutterportion() <= 0) {
                hm.remove(pos);
            }

        }
        // eine Runde - jede Ameise macht einen Zug
        for (int i = 0; i < ameisenKolonie.length; i++) {
            // Feld aktFeld = feld[ameisenKolonie[i].getX()][ameisenKolonie[i].getY()];
            int pos = schluesselBerrechnen(ameisenKolonie[i].getX(), ameisenKolonie[i].getY());
            Feld aktFeld = hm.get(pos);

            if (aktFeld != null && !ameisenKolonie[i].isTraegtFutter() && aktFeld.getFutterportion() > 0) {
                futterAufnehmen(ameisenKolonie[i]);
                duftstoffVerspruehen(aktFeld);
            } else if (aktFeld != null && ameisenKolonie[i].isTraegtFutter() && aktFeld.isNest()) {
                futterAblegen(ameisenKolonie[i]);
            } else if (ameisenKolonie[i].isTraegtFutter()) {
                nachHause(ameisenKolonie[i]);
                if (aktFeld == null) {
                    hm.put(pos, new Feld(new ArrayList<Pheromon>(), 0, false));
                    hm.get(pos).addPheromon();
                } else {
                    duftstoffVerspruehen(aktFeld);
                }
            } else if (aktFeld != null && !aktFeld.getPheromones().isEmpty()) {
                followPheromon(ameisenKolonie[i]);
            } else {
                futtersuche(ameisenKolonie[i]);
            }
            // }

        }
    }

    private void feldErzeugen(int groesse, int futterquellen) {
        hm = new HashMap< Integer, Feld>();

        // Nest wird in die Mitte gesetzt
        hm.put(schluesselBerrechnen(groesse / 2, groesse / 2), new Feld(new ArrayList<Pheromon>(), 0, true));
        System.out.println(hm.get(schluesselBerrechnen(groesse / 2, groesse / 2)).getPheromones().isEmpty());
        nestPosition[0] = groesse / 2;
        nestPosition[1] = groesse / 2;
        // Futterstellen werden zufällig verteielt
        Random r = new Random();
        while (0 < futterquellen) {
            int x = r.nextInt(groesse);
            int y = r.nextInt(groesse);
            if (hm.get(schluesselBerrechnen(x, y)) == null) {
                hm.put(schluesselBerrechnen(x, y), new Feld(new ArrayList<Pheromon>(), futterproQuelle, false));
                futterquellen--;

            }

        }

    }

    /*
     Erzeugt die Ameisen
     */
    private void ameisenSpawnen(int anzahl) {
        ameisenKolonie = new Ameise[anzahl];
        for (int i = 0; i < anzahl; i++) {
            ameisenKolonie[i] = new Ameise(false, nestPosition[0], nestPosition[1]);
        }

    }

    private void futtersuche(Ameise ameise) {
        // prüfen ob Duftpunkte in der Nähe, wenn ja geht es zu dem welches weiter vom Nest entfernt ist
        Random r = new Random();
        int x = ameise.getX();
        int y = ameise.getY();
        boolean gefunden = false;

        for (int i = 0; i < moves[0].length; i++) {
            int key = schluesselBerrechnen(ameise.getX() + moves[0][i], ameise.getY() + moves[1][i]);
            if (hm.get(key) != null &&( hm.get(key).getFutterportion() > 0 || !hm.get(key).getPheromones().isEmpty())) {
                ameise.setX(ameise.getX() + moves[0][i]);
                ameise.setY(ameise.getY() + moves[1][i]);
                gefunden = true;
            }

        }

        if (!gefunden) {

// wenn Kein Duftpunkt gefunden wurde 
// es wird eine zufällige bewegung ausgefürt
            boolean searchWay = true;
            while (searchWay) {
                int zahl = r.nextInt(4);
                if (ameise.getX() + moves[0][zahl] < feldGroesse && ameise.getY() + moves[1][zahl] < feldGroesse) {
                    ameise.setX(ameise.getX() + moves[0][zahl]);
                    ameise.setY(ameise.getY() + moves[1][zahl]);
                    searchWay = false;
                }
            }

        }

    }

    private void followPheromon(Ameise ameise) {
        boolean searching = true;
        for (int i = 0; i < moves[0].length; i++) {
            int key = schluesselBerrechnen(ameise.getX() + moves[0][i], ameise.getY() + moves[1][i]);
            if (hm.get(key) != null && Math.abs(koordinatenBerrechnen(key)[0] - nestPosition[0]) > Math.abs(ameise.getX() - nestPosition[0]) || Math.abs(koordinatenBerrechnen(key)[1] - nestPosition[1]) > Math.abs(ameise.getY() - nestPosition[1])) {
                ameise.setX(ameise.getX() + moves[0][i]);
                ameise.setY(ameise.getY() + moves[1][i]);
                searching = false;
                break;
            }

        }
        if (searching) {
            futtersuche(ameise);
        }

    }

    private void nachHause(Ameise ameise) {
        Random r = new Random();
        if (r.nextInt(2) >= 0.5 && ameise.getX() != nestPosition[0]) { // richtung x Koordinate
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

    private int schluesselBerrechnen(int x, int y) {
        int key = feldGroesse * x + y;
        return key;
    }

    public int[] koordinatenBerrechnen(int schlüssel) {
        int[] koordinaten = new int[2];
        koordinaten[0] = schlüssel / feldGroesse;
        koordinaten[1] = schlüssel % feldGroesse;
        return koordinaten;
    }

    private void futterAufnehmen(Ameise ameise) {
        ameise.setTraegtFutter(true);
        hm.get(schluesselBerrechnen(ameise.getX(), ameise.getY())).futterportionAbziehen();
    }

    private void futterAblegen(Ameise ameise) {
        ameise.setTraegtFutter(false);
        futterImNest++;
        System.out.println("Futter im Nest = " + futterImNest);
    }

    private void duftstoffVerspruehen(Feld feld) {
        feld.addPheromon();

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

    public HashMap<Integer, Feld> getHm() {
        return hm;
    }

}
