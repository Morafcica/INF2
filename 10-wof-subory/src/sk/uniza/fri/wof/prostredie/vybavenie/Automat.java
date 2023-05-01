package sk.uniza.fri.wof.prostredie.vybavenie;

import sk.uniza.fri.wof.prostredie.Pouzitelny;
import sk.uniza.fri.wof.prostredie.predmety.Isic;
import sk.uniza.fri.wof.prostredie.predmety.Jedlo;
import sk.uniza.fri.wof.zaklad.Hrac;

import java.util.Scanner;

public class Automat implements Vybavenie, Pouzitelny {
    @Override
    public String getNazov() {
        return "automat";
    }

    @Override
    public String getPopis() {
        return "Výpredaj bagiet a minerálok";
    }

    @Override
    public void pouzi(Hrac hrac) {
        var predmet = hrac.najdiPredmet("isic");
        if (predmet.isEmpty()) {
            System.out.println("Nemáš isic, automat bez neho použiť nevieš");
            return;
        }
        var isic = (Isic) predmet.get();

        System.out.println("Čo si chceš kúpiť?");
        System.out.println("1 - Bagetu");
        System.out.println("2 - Mineralku");
        System.out.println("0 - Zisti stav kreditu");
        System.out.print("> ");
        var scanner = new Scanner(System.in);
        var volba = scanner.nextLine();
        switch (volba) {
            case "1" -> {
                if (isic.getKredit() > 5) {
                    hrac.pridajPredmet(new Jedlo("bageta", 5));
                    isic.upravKredit(-5);
                }
                else {
                    System.out.println("Nemáš dosť kreditu");
                }
            }
            case "2" -> {
                if (isic.getKredit() > 2) {
                    hrac.pridajPredmet(new Jedlo("mineralka", 2));
                    isic.upravKredit(-2);
                }
                else {
                    System.out.println("Nemáš dosť kreditu");
                }
            }
            case "0" -> System.out.println(isic.getKredit());
            default -> System.out.println("Neplatná voľba");
        }
    }
}
