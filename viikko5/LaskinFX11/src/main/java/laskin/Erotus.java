package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Erotus extends Komento {

    public Erotus(TextField tuloskentta, TextField syotekentta, Sovelluslogiikka sovellus, Button nollaa, Button undo) {
        super(tuloskentta, syotekentta, sovellus, nollaa, undo);
    }

    @Override
    public void suorita() {
        int luku = Integer.parseInt(syotekentta.getText());
        sovellus.miinus(luku);
        tuloskentta.setText("" + sovellus.tulos());
        syotekentta.setText("");
        if (sovellus.tulos() == 0) {
            nollaa.disableProperty().set(true);
        } else {
            nollaa.disableProperty().set(false);
        }
        undo.disableProperty().set(false);
    }
}