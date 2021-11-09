import java.util.ArrayList;

public class Inventory {
    private ArrayList<Armory> arms;
    private ArrayList<Weaponry> weapons;
    private ArrayList<Potion> potions;
    private ArrayList<Spell> spells;


    Inventory(){
        arms = new ArrayList<Armory>();
        weapons = new ArrayList<Weaponry>();
        potions = new ArrayList<Potion>();
        spells = new ArrayList<Spell>();
    }

    Inventory(ArrayList<Armory> a, ArrayList<Weaponry> w, ArrayList<Potion> p, ArrayList<Spell> s){
        arms = a;
        weapons = w;
        potions = p;
        spells = s;
    }

    public void print_armor(){
        System.out.println("Name                   cost  required_level  damage_reduction");
        for (int i = 0; i < arms.size(); i++){
            System.out.print(i+1 + ". ");
            arms.get(i).print_armor();
        }
    }

    public void print_weapon(){
        System.out.println("Name                cost  required_level  damage   required_hands");
        for (int i = 0; i < weapons.size(); i++){
            System.out.print(i+1 + ". ");
            weapons.get(i).print_weapon();
        }
    }

    public void print_potion(){
        System.out.println("Name                   cost  required_level  increase  attribute_affected");
        for (int i = 0; i < potions.size(); i++){
            System.out.print(i+1 + ". ");
            potions.get(i).print_potion();
        }
    }

    public void print_spell(){
        System.out.println("    Name                cost   required_level  damage   mana_cost");
        for (int i = 0; i < spells.size(); i++){
            System.out.format("%-2d%s", i+1, ". ");
            spells.get(i).print_spell();
        }
    }

    public void add_arm (Armory a){
        arms.add(a);
    }
    public void add_weapon (Weaponry w){
        weapons.add(w);
    }
    public void add_potion (Potion p){
        potions.add(p);
    }
    public void add_spell (Spell s){
        spells.add(s);
    }

    public void remove_armor (Armory a){
        arms.remove(a);
    }
    public void remove_weapon (Weaponry a){
        weapons.remove(a);
    }
    public void remove_potion (Potion a){
        potions.remove(a);
    }
    public void remove_spell (Spell a){
        spells.remove(a);
    }

    public Armory get_armor (int order){
        return arms.get(order);
    }
    public Weaponry get_weapon (int order){
        return weapons.get(order);
    }
    public Potion get_potion (int order){
        return potions.get(order);
    }
    public Spell get_spell (int order){
        return spells.get(order);
    }

    public int get_armor_num (){
        return arms.size();
    }
    public int get_weapon_num (){
        return weapons.size();
    }
    public int get_potion_num (){
        return potions.size();
    }
    public int get_spell_num (){
        return spells.size();
    }



}
