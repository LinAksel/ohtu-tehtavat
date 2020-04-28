
package ohtu.intjoukkosovellus;

public class IntJoukko {

    // Poistettu turhat ja nimetty uudelleen Lista kuvaavammaksi
    private int kasvatuskoko;
    private int[] joukkoLista;
    private int alkioidenLkm;
    private static Operaatio operaatio;

    // Konstruktoreista poistettu turha copypaste, virheellisen syötteen käsittely
    // pidetty vain returnina
    public IntJoukko() {
        this(5, 5);
    }

    public IntJoukko(int kapasiteetti) {
        this(kapasiteetti, 5);
    }

    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0) {
            return;
        }
        if (kasvatuskoko < 0) {
            return;
        }
        joukkoLista = new int[kapasiteetti];
        for (int i = 0; i < joukkoLista.length; i++) {
            joukkoLista[i] = 0;
        }
        alkioidenLkm = 0;
        this.kasvatuskoko = kasvatuskoko;

    }

    // Tiivistetty ja eriytetty
    public boolean lisaa(int luku) {
        if (!kuuluu(luku)) {
            joukkoLista[alkioidenLkm] = luku;
            alkioidenLkm++;
            laajenna();
            return true;
        }
        return false;
    }

    // Uusi metodi, eriytetty lisaa-metodista
    public void laajenna() {
        if (alkioidenLkm % joukkoLista.length == 0) {
            int[] uusiTaulukko = new int[alkioidenLkm + kasvatuskoko];
            kopioiTaulukko(joukkoLista, uusiTaulukko);
            this.joukkoLista = uusiTaulukko;
        }
    }

    // Tiivistetty
    public boolean kuuluu(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == joukkoLista[i]) {
                return true;
            }
        }
        return false;
    }

    // Selkeytetty
    public boolean poista(int luku) {
        boolean loytyi = false;
        int muisti;
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == joukkoLista[i] && !loytyi) {
                loytyi = true;
                joukkoLista[i] = 0;
                alkioidenLkm--;
            }
            if (loytyi) {
                muisti = joukkoLista[i];
                joukkoLista[i] = joukkoLista[i + 1];
                joukkoLista[i + 1] = muisti;
            }
        }
        return loytyi;
    }

    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        for (int i = 0; i < vanha.length; i++) {
            uusi[i] = vanha[i];
        }
    }

    public int mahtavuus() {
        return alkioidenLkm;
    }

    // Tiivistetty hieman
    @Override
    public String toString() {
        String tuotos = "{";
        int[] tulosteLista = getNollatonLista();
        for (int i = 0; i < tulosteLista.length; i++) {
            if (i > 0) {
                tuotos += ", ";
            }
            tuotos += tulosteLista[i];
        }
        tuotos += "}";
        return tuotos;
    }

    // Parempi nimi
    public int[] getNollatonLista() {
        int[] taulu = new int[alkioidenLkm];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = joukkoLista[i];
        }
        return taulu;
    }

    // Käytetty strategia-suunnittelumallia
    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        operaatio = Operaatio.luo("yhdiste");
        return operaatio.laske(a, b);
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        operaatio = Operaatio.luo("leikkaus");
        return operaatio.laske(a, b);
    }

    public static IntJoukko erotus(IntJoukko a, IntJoukko b) {
        operaatio = Operaatio.luo("erotus");
        return operaatio.laske(a, b);
    }

}
