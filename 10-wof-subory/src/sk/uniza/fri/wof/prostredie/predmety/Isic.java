package sk.uniza.fri.wof.prostredie.predmety;

public class Isic implements Predmet {
    private int kredit;

    public Isic(int kredit) {
        this.kredit = kredit;
    }

    @Override
    public String getNazov() {
        return "isic";
    }

    @Override
    public boolean mozemPolozit() {
        return false;
    }

    public int getKredit() {
        return this.kredit;
    }

    public void upravKredit(int zmena) {
        this.kredit += zmena;
    }
}
