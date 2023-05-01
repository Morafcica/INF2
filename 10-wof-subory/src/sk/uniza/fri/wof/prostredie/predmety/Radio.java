package sk.uniza.fri.wof.prostredie.predmety;

import sk.uniza.fri.wof.prostredie.Miestnost;
import sk.uniza.fri.wof.reakcie.ReakciaNaChodenie;
import sk.uniza.fri.wof.zaklad.Hrac;

public class Radio implements Predmet, ReakciaNaChodenie {
    private boolean maBaterky;

    public Radio() {
        this.maBaterky = false;
    }

    @Override
    public String getNazov() {
        return "radio";
    }

    @Override
    public boolean mozemPolozit() {
        return true;
    }

    public void vlozBaterky() {
        this.maBaterky = true;
    }

    @Override
    public void hracSaPohol(Hrac hrac, Miestnost miestnost) {
        if (this.maBaterky) {
            System.out.println("\uD83D\uDCFB - ♫♪♪\uD834\uDD5E\uD83C\uDFB6\uD834\uDD61");
        }
    }
}
