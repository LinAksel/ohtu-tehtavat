package ohtu.intjoukkosovellus;

public class Yhdiste extends Operaatio {
    @Override
    public IntJoukko laske(IntJoukko a, IntJoukko b) {
        IntJoukko uusiJoukko = new IntJoukko();
        int[] aTaulu = a.getNollatonLista();
        int[] bTaulu = b.getNollatonLista();
        for (int i = 0; i < aTaulu.length; i++) {
            uusiJoukko.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            uusiJoukko.lisaa(bTaulu[i]);
        }
        return uusiJoukko;
    }
}