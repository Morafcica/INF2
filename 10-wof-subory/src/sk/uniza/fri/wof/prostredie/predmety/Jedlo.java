package sk.uniza.fri.wof.prostredie.predmety;

import sk.uniza.fri.wof.prostredie.Pouzitelny;
import sk.uniza.fri.wof.zaklad.Hrac;

public class Jedlo implements Predmet, Pouzitelny {
    private final String nazov;
    private int zostavajuciPocetPouziti;

    public Jedlo(String nazov, int pocetPouziti) {
        this.nazov = nazov;
        this.zostavajuciPocetPouziti = pocetPouziti;
    }

    @Override
    public void pouzi(Hrac hrac) {
        if (this.zostavajuciPocetPouziti > 0) {
            System.out.printf("Mňam, dal si si kúsok z %s%n", this.nazov);
            this.zostavajuciPocetPouziti--;
        }
        else {
            System.out.println("Hm, už tu nemáš čo zjesť");
        }
    }

    @Override
    public String getNazov() {
        return this.nazov;
    }

    @Override
    public boolean mozemPolozit() {
        return true;
    }
}
