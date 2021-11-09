import java.util.Objects;

public class Spell extends Gear implements manaRequirement{

    private int damage;
    private int mana;
    private String type;

    Spell(String n, int c, int l, int d,int m, String t){
        super(n, c, l);
        damage = d;
        mana = m;
        type = t;
    }

    public void print_spell(){
        if (Objects.equals(type, "FireSpells")){
            System.out.format("\033[31m%-20s%-7d%-16d%-8d%-5d\n\033[m", name, cost, level, damage, mana);
        }
        else if (Objects.equals(type, "IceSpells")){
            System.out.format("\033[36m%-20s%-7d%-16d%-8d%-5d\n\033[m", name, cost, level, damage, mana);
        }
        else if (Objects.equals(type, "LightningSpells")){
            System.out.format("\033[33m%-20s%-7d%-16d%-8d%-5d\n\033[m", name, cost, level, damage, mana);
        }

    }

    public void spell_effects(Monster m){
        if (Objects.equals(type, "FireSpells")){
            m.decrease_damage();
        }
        else if (Objects.equals(type, "IceSpells")){
            m.decrease_defence();
        }
        else if (Objects.equals(type, "LightningSpells")){
            m.decrease_dodge();
        }
    }

    public int get_damage(int dexterity){
        return (int) (damage*(0.5+dexterity/10000));
    }

    public int get_mana(){
        return mana;
    }

    public String get_type(){
        return this.type;
    }

    @Override
    public boolean is_castable(int m) {
        return m >= mana;
    }
}
