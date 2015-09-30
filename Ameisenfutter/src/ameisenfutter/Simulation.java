
package ameisenfutter;

/**
 *
 * @author Marc und Vika
 */
public class Simulation {

    public Simulation() {
        feldErzeugen(500, 5);
    }

    private Feld[][] feldErzeugen(int größe, int futterquellen) {
        Feld[][] feld = new Feld[größe][größe];
        for (int i = 0; i < größe; i++) {
            for (int j = 0; j < größe; j++) {
                feld[i][j] = new Feld(false,0);
                
            }
            
        }
        return feld;

    }
}
