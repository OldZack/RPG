public class Gear implements levelRequirement{
    protected String name;
    protected int cost;
    protected int level;

    Gear() {

    }
    Gear(String n, int c, int l){
        name = n;
        cost = c;
        level = l;
    }

    public String get_name(){
        return name;
    }

    public int get_cost(){
        return cost;
    }

    public int get_level(){
        return level;
    }

    @Override
    public boolean is_equipable(int l) {
        if (l >= level){
           return true;
        }
        return false;
    }
}
