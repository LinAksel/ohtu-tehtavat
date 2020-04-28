package ohtu.intjoukkosovellus;

public class Leikkaus extends Operaatio {
    @Override
    public IntJoukko laske(IntJoukko a, IntJoukko b) {
        IntJoukko uusiJoukko = new IntJoukko();
        int[] aTaulu = a.getNollatonLista();
        int[] bTaulu = b.getNollatonLista();
        for (int i = 0; i < aTaulu.length; i++) {
            for (int j = 0; j < bTaulu.length; j++) {
                if (aTaulu[i] == bTaulu[j]) {
                    uusiJoukko.lisaa(bTaulu[j]);
                }
            }
        }
        return uusiJoukko;
    }
}