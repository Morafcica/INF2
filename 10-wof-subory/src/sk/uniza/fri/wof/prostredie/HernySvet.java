package sk.uniza.fri.wof.prostredie;

import sk.uniza.fri.wof.prostredie.predmety.*;
import sk.uniza.fri.wof.prostredie.vybavenie.Automat;
import sk.uniza.fri.wof.prostredie.vybavenie.OvladacVytahu;
import sk.uniza.fri.wof.prostredie.vychody.VstupDoLabaku;
import sk.uniza.fri.wof.prostredie.vychody.VychodZVytahu;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class HernySvet {
    private Miestnost startovaciaMiestnost;
    private final HashMap<String, Miestnost> miestnosti;
    private String aktualnaMiestnost;
    /**
     * Vytvorí herný svet s mapou definovanou v zdrojovom kóde
     */
    public HernySvet() {
        this.miestnosti = new HashMap<>();
        this.startovaciaMiestnost = this.vytvorMapu();
    }

    /**
     * Vytvori mapu definovanú pomocou zdrojového kódu
     */
    private Miestnost vytvorMapu() {
        final Miestnost startovaciaMiestnost;
        File subor = new File("/C:/Users/Lenovo/Downloads/10-wof-subory/resources/mapa.txt");

        /*
         *Vytvorenie miestnosti
         */
        try {
            Scanner scanner = new Scanner(subor);
            while (scanner.hasNext()) {
                if (scanner.next().equals("miestnost")) {
                    String popisMiestnosti = null;
                    String menoMiestnosti = null;
                    if (scanner.hasNextLine()) {
                        popisMiestnosti = scanner.nextLine();
                        String[] slova = popisMiestnosti.split(" ", 3);
                        menoMiestnosti = slova[1];
                        try {
                            popisMiestnosti = slova[2];
                        } catch (ArrayIndexOutOfBoundsException e) {
                            popisMiestnosti = menoMiestnosti;
                        }
                    }

                    this.miestnosti.put(menoMiestnosti, new Miestnost(popisMiestnosti));


                }


            }


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }


        /*
         * Vychody
         */

        try {
            Scanner sc = new Scanner(subor);
            while (sc.hasNext()) {
                String aktualnyRiadok = sc.next().trim();
                String[] slovaRiadku = aktualnyRiadok.split(" ");
                if (slovaRiadku[0].equals("miestnost")) {
                    String riadok = sc.nextLine();
                    String[] slova = riadok.split(" ", 3);
                    this.aktualnaMiestnost = slova[1];

                    if (sc.next().equals("vychody")) {
                        sc.nextLine();
                        while (sc.hasNext()) {
                            riadok = sc.nextLine().trim();
                            if (riadok.equals("predmety")) {
                                break;
                            }
                            try {
                                String vychod = riadok.substring(0, riadok.indexOf(":"));
                                String miestnost = riadok.substring(riadok.indexOf(":") + 1).trim();
                                String[] slovaMiestnosti = miestnost.split(" ");
                                if (slovaMiestnosti.length > 1) {
                                    switch (slovaMiestnosti[0]) {
                                        case "VstupDoLabaku" -> {
                                            Miestnost labak = this.miestnosti.get("labak");
                                            this.miestnosti.get(this.aktualnaMiestnost).nastavVychod(vychod, new VstupDoLabaku(labak));
                                        }
                                        case "VychodZVytahu" -> {
                                            /*while (sc.hasNextLine()) {
                                                Miestnost[] vychodyVytahu = new Miestnost[3];
                                                if (this.miestnosti.get(sc.hasNext()) == null) {
                                                    this.miestnosti.put(sc.)
                                                }
                                            }*/
                                        }
                                    }
                                } else {
                                    Miestnost ciel = this.miestnosti.get(miestnost);
                                    this.miestnosti.get(this.aktualnaMiestnost).nastavVychod(vychod, ciel);
                                }

                            } catch (StringIndexOutOfBoundsException ignored) {

                            }


                        }

                    }
                }
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }


        /*
            Predmety
         */
        try {
            Scanner sc = new Scanner(subor);
            boolean uzBolaMiestnost = false;
            while (sc.hasNext()) {
                String aktualnyRiadok = sc.next();
                String[] slovaRiadku = aktualnyRiadok.split(" ");

                if (uzBolaMiestnost) {
                    this.aktualnaMiestnost = slovaRiadku[0];
                    uzBolaMiestnost = false;
                }

                if (slovaRiadku[0].equals("miestnost")) {
                    String riadok = sc.nextLine();
                    String[] slova = riadok.split(" ");
                    this.aktualnaMiestnost = slova[1];
                } else if (slovaRiadku[0].equals("predmety")) {
                    sc.nextLine();
                    while (sc.hasNext()) {
                        String riadok = sc.next();
                        if (riadok.equals("vybavenie") || riadok.equals("miestnost")) {
                            uzBolaMiestnost = true;
                            break;
                        }

                        String[] slovo = riadok.split(" ");
                        switch (slovo[0]) {
                            case "Hodinky" -> {
                                this.miestnosti.get(this.aktualnaMiestnost).polozPredmet(new Hodinky());
                            } case "Navleky" -> {
                                this.miestnosti.get(this.aktualnaMiestnost).polozPredmet(new Navleky());
                            } case "Radio" -> {
                                this.miestnosti.get(this.aktualnaMiestnost).polozPredmet(new Radio());
                            } case "Isic" -> {
                                int kredit = sc.nextInt();
                                this.miestnosti.get(this.aktualnaMiestnost).polozPredmet(new Isic(kredit));
                            } case "Baterky" -> {
                                this.miestnosti.get(this.aktualnaMiestnost).polozPredmet(new Baterky());
                            } case "ObycajnyPredmet" -> {
                                String nazov = sc.next();
                                this.miestnosti.get(this.aktualnaMiestnost).polozPredmet(new ObycajnyPredmet(nazov));
                            }
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {

        }


        /*
        Vybavenie
         */
        try {
            Scanner sc = new Scanner(subor);
            while (sc.hasNext()) {
                String aktualnyRiadok = sc.next();
                String[] slovaRiadku = aktualnyRiadok.split(" ");
                if (slovaRiadku[0].equals("miestnost")) {
                    String riadok = sc.nextLine();
                    String[] slova = riadok.split(" ");
                    this.aktualnaMiestnost = slova[1];
                } else if (slovaRiadku[0].equals("vybavenie")) {
                    sc.nextLine();
                    while (sc.hasNext()) {
                        String riadok = sc.next();
                        if (riadok.equals("miestnost")) {
                            break;
                        }

                        String[] slovo = riadok.split(" ");
                        switch (slovo[0]) {
                            case "Automat" -> {
                                this.miestnosti.get(this.aktualnaMiestnost).pridajVybavenie(new Automat());
                            } case  "OvladacVytahu" -> {
                                this.miestnosti.get(this.aktualnaMiestnost).pridajVybavenie(new OvladacVytahu());
                            }
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {

        }


        for (Map.Entry<String, Miestnost> m : this.miestnosti.entrySet()) {
            m.getValue().vypisInfoOMiestnosti();
            System.out.println();
        }



        /*Miestnost terasa = new Miestnost("terasa - hlavny vstup na fakultu");
        Miestnost aula = new Miestnost("aula");
        Miestnost bufet = new Miestnost("bufet");
        Miestnost labak = new Miestnost("pocitacove laboratorium");
        Miestnost kancelaria = new Miestnost("kancelaria spravcu pocitacoveho laboratoria");

        Miestnost vytah = new Miestnost("vytah");
        Miestnost atomovyKryt = new Miestnost("atomovy kryt v pivnici");
        Miestnost chodba1Poschodie = new Miestnost("chodba na prvom poschodí");


        // inicializacia miestnosti = nastavenie vychodov
        terasa.nastavVychod("vychod", aula);
        terasa.nastavVychod("juh", new VstupDoLabaku(labak));
        terasa.nastavVychod("zapad", bufet);
        aula.nastavVychod("zapad", terasa);
        labak.nastavVychod("sever", terasa);
        labak.nastavVychod("vychod", kancelaria);
        kancelaria.nastavVychod("zapad", labak);
        bufet.nastavVychod("vychod", terasa);

        //Z daných miestnosti sa dá vojsť do výtahu
        terasa.nastavVychod("vytah", vytah);
        atomovyKryt.nastavVychod("vytah", vytah);
        chodba1Poschodie.nastavVychod("vytah", vytah);

        vytah.nastavVychod("von", new VychodZVytahu(new Miestnost[]{atomovyKryt, terasa, chodba1Poschodie}));

        terasa.polozPredmet(new ObycajnyPredmet("kamen"));
        terasa.polozPredmet(new Hodinky());
        terasa.polozPredmet(new Navleky());
        labak.polozPredmet(new ObycajnyPredmet("mys"));
        aula.polozPredmet(new Isic(6));
        //bufet.polozPredmet(new ObycajnyPredmet("bageta"));
        terasa.polozPredmet(new Radio());
        bufet.polozPredmet(new Baterky());

        bufet.pridajVybavenie(new Automat());

        vytah.pridajVybavenie(new OvladacVytahu());
        return terasa;*/
        return this.miestnosti.get("terasa");
    }

    public Miestnost getStartovaciaMiestnost() {
        return this.startovaciaMiestnost;
    }
}
