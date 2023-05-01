package sk.uniza.fri.wof.prostredie.vybavenie;

import sk.uniza.fri.wof.prostredie.Pouzitelny;
import sk.uniza.fri.wof.prostredie.vychody.VychodZVytahu;
import sk.uniza.fri.wof.zaklad.Hrac;

public class OvladacVytahu implements Vybavenie, Pouzitelny {
    @Override
    public void pouzi(Hrac hrac) {
        //Tuto neriešim bezpečné pretypovanie, lebo ak ovladac vytahu nieje v miestnosti vytah,
        //tak som dačo zle naprogramoval a budem rad ak to padne
        var vytahovyVychod = (VychodZVytahu) hrac.getAktualnaMiestnost().getVychodVSmere("von").get();
        vytahovyVychod.posun();
    }

    @Override
    public String getNazov() {
        return "ovladanie";
    }

    @Override
    public String getPopis() {
        return "Ovládaci panel výťahu, čo asi robí?";
    }
}
