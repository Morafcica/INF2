package sk.uniza.fri.wof.prostredie.vychody;

import sk.uniza.fri.wof.prostredie.Miestnost;
import sk.uniza.fri.wof.zaklad.Hrac;

public class VychodZVytahu implements Vychod {

    private final Miestnost[] poschodia;
    private int aktualnePoschodie;
    private boolean ideHore;

    public VychodZVytahu(Miestnost[] poschodia) {
        this.poschodia = poschodia;
        this.aktualnePoschodie = 1;
        this.ideHore = true;
    }

    @Override
    public Miestnost dajMiestnost() {
        return this.poschodia[this.aktualnePoschodie];
    }

    @Override
    public boolean mozemVstupit(Hrac hrac) {
        return true;
    }

    public void posun() {
        if (this.ideHore) {
            this.aktualnePoschodie++;
            if (this.aktualnePoschodie >= this.poschodia.length) {
                this.aktualnePoschodie -= 2;
                this.ideHore = false;
            }
        }
        else {
            this.aktualnePoschodie--;
            if (this.aktualnePoschodie < 0) {
                this.aktualnePoschodie = 1;
                this.ideHore = true;
            }
        }

        System.out.println("Aktuálne si na poschodí: " + this.aktualnePoschodie);
    }
}
