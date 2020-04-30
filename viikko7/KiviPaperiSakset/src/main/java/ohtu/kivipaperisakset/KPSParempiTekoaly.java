package ohtu.kivipaperisakset;

import java.util.Scanner;

public class KPSParempiTekoaly extends KPS {
    private TekoalyParannettu tekoaly;

    public KPSParempiTekoaly(Scanner scanner) {
        super(scanner);
        this.tekoaly = new TekoalyParannettu(20);
    }

    @Override
    protected String getTokanSiirto() {
        String tokanSiirto = tekoaly.annaSiirto();
        System.out.println("Tietokone valitsi: " + tokanSiirto);
        tekoaly.asetaSiirto(ekanSiirto);
        return tokanSiirto;
    }
}
