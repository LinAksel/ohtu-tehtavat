package ohtu.kivipaperisakset;

import java.util.HashMap;
import java.util.Scanner;

public class KPSTehdas {
    private HashMap<String, KPS> pelit;

    public KPSTehdas(Scanner scanner) {
        pelit = new HashMap<String, KPS>();
        pelit.put("a", new KPSPelaajaVsPelaaja(scanner));
        pelit.put("b", new KPSTekoaly(scanner));
        pelit.put("c", new KPSParempiTekoaly(scanner));

    }

    public KPS haePeli(String valinta) {
        return pelit.getOrDefault(valinta, null);
    }

}