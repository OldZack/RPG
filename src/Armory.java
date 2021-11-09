public class Armory extends Gear{

    private int damage_reduction;

    Armory(String n, int c, int l, int d) {
        super(n, c, l);
        damage_reduction = d;
    }

    public int get_damage_reduction(){
       return damage_reduction;
    }

    public void print_armor(){
        System.out.format("%-20s%-6d%-16d%-10d\n", name, cost, level, damage_reduction);
    }




}
