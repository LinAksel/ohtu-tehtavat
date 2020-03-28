
package ohtu;

public class Player {
    private String name;
    private int goals;
    private int assists;
    private String team;
    private String nationality;

    public void setName(String name) {
        this.name = name;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getName() {
        return name;
    }

    public int getGoals() {
        return goals;
    }

    public int getAssists() {
        return assists;
    }

    public String getTeam() {
        return team;
    }

    public String getNationality() {
        return nationality;
    }

    @Override
    public String toString() {
        // return name + " team " + team + " goals " + goals + " assists " + assists;
        return String.format("%-18s %3s    %2s + %2s  =  %2s", name, team, goals, assists, (goals + assists));
    }

}
