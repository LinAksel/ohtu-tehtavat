package ohtu.verkkokauppa;

import java.util.ArrayList;

public interface Kirjaava {

    ArrayList<String> getTapahtumat();

    void lisaaTapahtuma(String tapahtuma);

}