package generics.part2_generic_class_without_types;

import java.util.ArrayList;

public class Team {
    private String name;
    int played = 0;
    int won = 0;
    int lost = 0;
    int drew = 0;

    private ArrayList<Player> members;

    public Team(String name) {
        this.name = name;
        this.members = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public boolean addPlayer(Player player){
        if (this.members.contains(player)){
            System.out.println(player.getName() + " is already on this team");
            return false;
        }
        this.members.add(player);
        System.out.println(player.getName() + " signed by "+this.name);
        return true;
    }

    public int numberOfPlayers(){
        return this.members.size();
    }

    public void matchResult(Team opponent , int ourScore , int theirScore){
        if (ourScore > theirScore){
            won++;
        } else if (ourScore == theirScore) {
            drew++;
        }else {
            lost++;
        }
        played++;
        if (opponent != null){
            opponent.matchResult(null,theirScore , ourScore); // update opponent score; pass null as opponent to avoid infinite loop
        }
    }

    public int ranking(){
        return (won * 2) + drew;
    }
}
