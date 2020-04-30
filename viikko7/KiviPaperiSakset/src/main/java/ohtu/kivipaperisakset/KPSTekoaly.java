package ohtu.kivipaperisakset;

import java.util.Scanner;

public class KPSTekoaly extends KPS {

    private Tekoaly tekoaly;

    public KPSTekoaly(Scanner scanner) {
        super(scanner);
        this.tekoaly = new Tekoaly();
    }

    @Override
    protected String getTokanSiirto() {
        String tokanSiirto = tekoaly.annaSiirto();
        System.out.println("Tietokone valitsi: " + tokanSiirto);
        tekoaly.asetaSiirto(ekanSiirto);
        return tokanSiirto;
    }
}