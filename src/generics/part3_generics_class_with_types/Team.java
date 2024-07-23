package generics.part3_generics_class_with_types;

import java.util.ArrayList;

public class Team<T extends Player> {
    private String name;
    int played = 0;
    int won = 0;
    int lost = 0;
    int drew = 0;

    private ArrayList<T> members;

    public Team(String name) {
        this.name = name;
        this.members = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public boolean addPlayer(T player){
        if (this.members.contains(player)){
            System.out.println( player.getName() + " is already on this team");
            return false;
        }
        this.members.add(player);
        System.out.println( player.getName() + " signed by "+this.name);
        return true;
    }

    public int numberOfPlayers(){
        return this.members.size();
    }

    public void matchResult(Team<T> opponent , int ourScore , int theirScore){
        String message;
        if (ourScore > theirScore){
            won++;
            message = " beat ";
        } else if (ourScore == theirScore) {
            drew++;
            message = " drew with ";
        }else {
            lost++;
            message = " lost to ";
        }
        played++;
        if (opponent != null){
            System.out.println(this.getName() + message + opponent.getName());
            opponent.matchResult(null,theirScore , ourScore); // update opponent score; pass null as opponent to avoid infinite loop
        }
    }

    public int ranking(){
        return (won * 2) + drew;
    }
}
