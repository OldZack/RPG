import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Enemy {

    private ArrayList<Monster> all_monsters;
    private ArrayList<Monster> members;
    private Parser p = new Parser();

    Enemy() throws FileNotFoundException {
        all_monsters = p.parse_all_monster();
        members = new ArrayList<Monster>();
    }

    public Monster get_monster(int h){
        return members.get(h);
    }

    public void generate_team(int size, int level){
        members = new ArrayList<Monster>();
        ArrayList<Monster> eligible_monsters = new ArrayList<Monster>();
        for (Monster m : all_monsters) {
            if (m.get_level() <= level){
                eligible_monsters.add(m);
            }
        }

        for (int i = 0; i < size; i++){
            members.add(eligible_monsters.get((int)(Math.random()*eligible_monsters.size())));
        }
    }

    public boolean isAlive(){
        for (Monster m : members){
            if (m.isAlive()){
                return true;
            }
        }
        return false;
    }

    public void print_status(){
        System.out.println("+------------------------------------+");
        System.out.println("|            Enemy Status            |");
        System.out.println("+------------------------------------+");
        System.out.println("Name                level  HP    Damage    Defence   Dodgechance");
        for (Monster m : members){
            m.print_status();
        }
    }

    public void action(Player p){
        Hero h;
        Monster m;
        for (int i = 0; i <members.size(); i++){
            h = p.get_hero(i);
            m = members.get(i);
            if (members.get(i).isAlive()){
                int j = i;
                while(!p.isAlive()){
                    h = p.get_hero((j+1)%members.size());
                    j += 1;
                }
                System.out.println(m.get_name() +" dealt "+ h.take_damage(m.get_damage()) + " to " + h.get_name());
            }

        }
    }

}
