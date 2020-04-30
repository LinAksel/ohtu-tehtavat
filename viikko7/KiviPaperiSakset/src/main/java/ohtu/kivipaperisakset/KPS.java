package ohtu.kivipaperisakset;

import java.util.Scanner;

public abstract class KPS {

    protected Scanner scanner;
    private Tuomari tuomari;
    protected String ekanSiirto;
    protected String tokanSiirto;

    public KPS(Scanner scanner) {
        tuomari = new Tuomari();
        this.scanner = scanner;
    }

    public void pelaa() {

        System.out.print("Ensimmäisen pelaajan siirto: ");
        ekanSiirto = scanner.nextLine();

        tokanSiirto = getTokanSiirto();

        while (onkoOkSiirto(ekanSiirto) && onkoOkSiirto(tokanSiirto)) {
            tuomari.kirjaaSiirto(ekanSiirto, tokanSiirto);
            System.out.println(tuomari);
            System.out.println();

            System.out.print("Ensimmäisen pelaajan siirto: ");
            ekanSiirto = scanner.nextLine();

            tokanSiirto = getTokanSiirto();
        }

        System.out.println();
        System.out.println("Kiitos!");
        System.out.println(tuomari);
    }

    protected abstract String getTokanSiirto();

    private static boolean onkoOkSiirto(String siirto) {
        return "k".equals(siirto) || "p".equals(siirto) || "s".equals(siirto);
    }

}