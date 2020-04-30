package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public abstract class Komento {
    protected TextField tuloskentta;
    protected TextField syotekentta;
    protected Button nollaa;
    protected Button undo;
    protected Sovelluslogiikka sovellus;

    public Komento(TextField tuloskentta, TextField syotekentta, Sovelluslogiikka sovellus, Button nollaa,
            Button undo) {
        this.tuloskentta = tuloskentta;
        this.syotekentta = syotekentta;
        this.nollaa = nollaa;
        this.undo = undo;
        this.sovellus = sovellus;
    }

    public abstract void suorita();

    public void peru() {
        tuloskentta.setText("" + sovellus.undo());
        syotekentta.setText("");
        undo.disableProperty().set(true);
    }
}
