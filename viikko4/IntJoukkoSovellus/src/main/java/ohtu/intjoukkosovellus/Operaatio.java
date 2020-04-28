package ohtu.intjoukkosovellus;

public abstract class Operaatio {

    public static Operaatio luo(String operaatio) {
        if (operaatio.equals("yhdiste")) {
            return new Yhdiste();
        } else if (operaatio.equals("erotus")) {
            return new Erotus();
        }
        return new Leikkaus();
    }

    public abstract IntJoukko laske(IntJoukko a, IntJoukko b);
}
