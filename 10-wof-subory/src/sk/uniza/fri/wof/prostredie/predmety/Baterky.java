package sk.uniza.fri.wof.prostredie.predmety;

import sk.uniza.fri.wof.prostredie.Pouzitelny;
import sk.uniza.fri.wof.zaklad.Hrac;

public class Baterky implements Predmet, Pouzitelny {
    @Override
    public String getNazov() {
        return "baterky";
    }

    @Override
    public void pouzi(Hrac hrac) {
        var predmet = hrac.najdiPredmet("radio");
        if (predmet.isEmpty()) {
            System.out.println("Tento predmet nemám");
        } else {
            var radio = (Radio) predmet.get();
            radio.vlozBaterky();
        }
    }

    @Override
    public boolean mozemPolozit() {
        return true;
    }

}
