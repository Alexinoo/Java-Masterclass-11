package generics.part4_generics_challenge;

import java.util.ArrayList;
import java.util.Collections;

public class League<T extends Team> {
    private String name;
    private ArrayList<T> league;

    public League(String name) {
        this.name = name;
        this.league = new ArrayList<>();
    }

    public boolean addTeam(T team){
        if(this.league.contains(team)){
            System.out.println(team.getName() + " already exists in the league");
           return false;
        }
        this.league.add(team);
        return true;
    }

    public void showLeagueTable(){
        Collections.sort(this.league);
        for (T team : this.league){
            System.out.println(team.getName() + " : "+ team.ranking());
        }
    }
}
