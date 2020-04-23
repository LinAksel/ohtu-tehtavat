package ohtu.verkkokauppa;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class KauppaTest {

    Pankki pankki;
    Viitegeneraattori viite;
    Varasto varasto;
    Kauppa k;

    @Before
    public void setUp() {
        pankki = mock(Pankki.class);
        viite = mock(Viitegeneraattori.class);
        varasto = mock(Varasto.class);
        k = new Kauppa(varasto, pankki, viite);
    }

    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaan() {

        // määritellään että viitegeneraattori palauttaa viitten 42
        when(viite.uusi()).thenReturn(42);

        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1); // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(), anyInt());
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    }

    @Test
    public void yhdenOstoksenTilisiirtoToimiiOikein() {

        when(viite.uusi()).thenReturn(50);
        when(varasto.saldo(1)).thenReturn(8);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "voi", 4));

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("pasi", "667");

        verify(pankki).tilisiirto(eq("pasi"), anyInt(), eq("667"), anyString(), eq(4));
    }

    @Test
    public void kahdenEriOstoksenTilisiirtoToimiiOikein() {

        when(viite.uusi()).thenReturn(55);
        when(varasto.saldo(1)).thenReturn(8);
        when(varasto.saldo(2)).thenReturn(16);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "voi", 4));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "hiiva", 6));

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(2);
        k.tilimaksu("pena", "8887");

        verify(pankki).tilisiirto(eq("pena"), anyInt(), eq("8887"), anyString(), eq(10));
    }

    @Test
    public void kahdenSamanOstoksenTilisiirtoToimiiOikein() {

        when(viite.uusi()).thenReturn(50);
        when(varasto.saldo(1)).thenReturn(8);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "kasvis", 3));

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(1);
        k.tilimaksu("tobbe", "1234-T");

        verify(pankki).tilisiirto(eq("tobbe"), anyInt(), eq("1234-T"), anyString(), eq(6));
    }

    @Test
    public void yhdenRiittavanJaYhdenLoppuneenOstoksenTilisiirtoToimiiOikein() {

        when(viite.uusi()).thenReturn(55);
        when(varasto.saldo(1)).thenReturn(7);
        when(varasto.saldo(2)).thenReturn(0);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "kananmuna", 1));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "vapaus", 42));

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(2);
        k.tilimaksu("erkki", "000C");

        verify(pankki).tilisiirto(eq("erkki"), anyInt(), eq("000C"), anyString(), eq(1));
    }

    @Test
    public void aloitaAsiointiNollaaEdellisetOstokset() {
        when(viite.uusi()).thenReturn(50);
        when(varasto.saldo(1)).thenReturn(8);
        when(varasto.saldo(2)).thenReturn(8);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "kasvis", 3));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "piiras", 7));

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(2);
        k.aloitaAsiointi();
        k.lisaaKoriin(2);
        k.lisaaKoriin(2);
        k.lisaaKoriin(2);
        k.tilimaksu("tobbe", "1234-T");

        verify(pankki).tilisiirto(eq("tobbe"), anyInt(), eq("1234-T"), anyString(), eq(21));
    }

    @Test
    public void kauppaPyytaaUudenViitenumeronJokaiselleTapahtumalle() {
        when(viite.uusi()).thenReturn(50);
        when(varasto.saldo(1)).thenReturn(8);
        when(varasto.saldo(2)).thenReturn(8);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "kasvis", 3));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "piiras", 7));

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(2);
        k.tilimaksu("tobbe", "1234-T");
        k.aloitaAsiointi();
        k.lisaaKoriin(2);
        k.lisaaKoriin(2);
        k.lisaaKoriin(2);
        k.tilimaksu("tobbe", "1234-T");

        verify(viite, times(2)).uusi();
    }

    @Test
    public void tuotePoistuuKoristaOikein() {
        when(viite.uusi()).thenReturn(888);
        when(varasto.saldo(1)).thenReturn(9);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "olut", 2));

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(1);
        k.poistaKorista(1);
        k.tilimaksu("pasi", "667");

        verify(pankki).tilisiirto(eq("pasi"), anyInt(), eq("667"), anyString(), eq(2));
    }
}
