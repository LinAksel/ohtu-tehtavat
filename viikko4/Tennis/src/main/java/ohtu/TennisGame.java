package ohtu;

public class TennisGame {

    private int player1Points = 0;
    private int player2Points = 0;
    private String[] scoreNames = { "Love", "Fifteen", "Thirty", "Forty" };
    private String player1Name;
    private String player2Name;

    // Score->pointsmäppäys oli suoraviivainen, siksi taulukon käyttö mielestäni
    // järkevää. Nyt kaikki on pyritty refaktoroimaan "single responsibility" -
    // periaatetta mahdollisimman hyvin mukaillen, ja indentifioin tasapelin,
    // keskipelin ja loppupelin omiksi selkeiksi osikseen.
    // Vaikka en tehnyt uusia luokkia, kului tähän yllättävän paljon aikaa.

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName.equals(player1Name)) {
            player1Points++;
        } else {
            player2Points++;
        }
    }

    private String pointsToScore(int points) {
        return scoreNames[points];
    }

    private String tieScore() {
        if (player1Points >= 4) {
            return "Deuce";
        } else {
            String score = pointsToScore(player1Points);
            score += "-All";
            return score;
        }
    }

    // Tein tästä metodista useita refaktorointeja, mutta lopulta päädyin siihen,
    // että jokainen vain monimutkaisti entisestään, lopulta tämän metodin pienehkö
    // toisteisuus palvelee selkeyttä enemmän kuin osiin pilkkominen olisi palvellut
    // logiikkaa. Tämä olikin yllättäen kaikista vaikein kohta tehtävän kannalta,
    // odotan mielenkiinnolla malliratkaisuja!

    private String endGameScore() {
        int difference = player1Points - player2Points;
        if (difference == 1) {
            return "Advantage player1";
        } else if (difference == -1) {
            return "Advantage player2";
        } else if (difference >= 2) {
            return "Win for player1";
        } else {
            return "Win for player2";
        }
    }

    private String middleGameScore() {
        String score = pointsToScore(player1Points) + "-" + pointsToScore(player2Points);
        return score;
    }

    public String getScore() {
        if (player1Points == player2Points) {
            return tieScore();
        } else if (player1Points >= 4 || player2Points >= 4) {
            return endGameScore();
        }
        return middleGameScore();
    }
}