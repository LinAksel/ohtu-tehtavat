package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Nollaa extends Komento {

    public Nollaa(TextField tuloskentta, TextField syotekentta, Sovelluslogiikka sovellus, Button nollaa, Button undo) {
        super(tuloskentta, syotekentta, sovellus, nollaa, undo);
    }

    @Override
    public void suorita() {
        sovellus.nollaa();
        tuloskentta.setText("" + sovellus.tulos());
        syotekentta.setText("");
        undo.disableProperty().set(false);
        nollaa.disableProperty().set(true);
    }
}