import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

public class Market {
    private Inventory goods;
    private Hero customer;
    private Parser p = new Parser();
    private Scanner input = new Scanner(System.in);

    Market() throws FileNotFoundException {
        goods = new Inventory(p.parse_armory(), p.parse_weaponry(), p.parse_potion(), p.parse_all_spell());
    }

    public void enter_market(Hero h){
        customer = h;
        first_loop:
        while(true){
            System.out.println("Do you want to sell or buy?");
            System.out.println("1. Sell");
            System.out.println("2. Buy");
            System.out.println("0. Back to previous menu");
            int choice;
            second_loop:
            while(true){
                try {
                    choice = Integer.parseInt(input.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("The input is not a number. Please re-enter:");
                    continue;
                }
                if (choice == 0){
                    break first_loop;
                }
                else if (choice == 2){
                    System.out.println("What type of merchandise are you looking for?");
                    System.out.println("1. Armor");
                    System.out.println("2. Weapon");
                    System.out.println("3. Potion");
                    System.out.println("4. Spell");
                    System.out.println("0. Back to previous menu");
                    int type;
                    while(true){
                        try {
                            type = Integer.parseInt(input.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("The input is not a number. Please re-enter:");
                            continue;
                        }
                        if (0 < type && type <= 4 ){
                            purchase(type);
                            break;
                        }
                        else if (type == 0){
                            break second_loop;
                        }
                        System.out.println("The input number is invalid. Please re-enter:");
                    }
                    break;
                }
                else if (choice == 1){
                    System.out.println("What type of item are you selling?");
                    System.out.println("1. Armor");
                    System.out.println("2. Weapon");
                    System.out.println("3. Potion");
                    System.out.println("0. Back to previous menu");
                    int type;
                    while(true){
                        try {
                            type = Integer.parseInt(input.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("The input is not a number. Please re-enter:");
                            continue;
                        }
                        if (0 < type && type <= 3 ){
                            sell(type);
                            break;
                        }
                        else if (type == 0){
                            break second_loop;
                        }
                        System.out.println("The input number is invalid. Please re-enter:");
                    }
                    break;
                }
                System.out.println("The input is invalid. Please re-enter:");
            }
        }
    }

    public void purchase(int type){
        first_loop:
        while (true){
            System.out.println("Here's what we have: ");
            if (type == 1){
                goods.print_armor();
            }
            else if (type == 2){
                goods.print_weapon();
            }
            else if (type == 3){
                goods.print_potion();
            }
            else {
                goods.print_spell();
            }
            System.out.println("-------------------------------------------------------------------------------------");
            System.out.format("%-23s%d\n", "You have ", customer.get_money());
            System.out.println("0. Back to previous menu");
            int choice;
            Gear g;
            while(true) {
                try {
                    choice = Integer.parseInt(input.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("The input is not a number. Please re-enter:");
                    continue;
                }
                if (choice == 0) {
                    break first_loop;
                } else {
                    if (type == 1 && choice <= goods.get_armor_num()) {
                        g = goods.get_armor(choice-1);
                    } else if (type == 2 && choice <= goods.get_weapon_num()) {
                        g = goods.get_weapon(choice-1);
                    } else if (type == 3 && choice <= goods.get_potion_num()) {
                        g = goods.get_potion(choice-1);
                    } else if (choice <= goods.get_spell_num()){
                        g = goods.get_spell(choice-1);
                    } else {
                        System.out.println("The input is invalid. Please re-enter:");
                        continue;
                    }
                }
                if (!g.is_equipable(customer.get_level())){
                    System.out.println("The required level is higher than yours. Please choose another one:");
                }
                else if (g.get_cost() > customer.get_money()){
                    System.out.println("You do not have enough money to purchase this item. Please choose another one:");
                }
                else{
                    customer.change_money(-1*g.get_cost());
                    if (type == 1){
                        customer.get_gears().add_arm((Armory) g);
                    }
                    else if (type == 2){
                        customer.get_gears().add_weapon((Weaponry) g);
                    }
                    else if (type == 3){
                        customer.get_gears().add_potion((Potion) g);
                    }
                    else {
                        customer.get_gears().add_spell((Spell) g);
                        goods.remove_spell((Spell) g);
                    }
                    System.out.println(g.get_name() + " has added to your inventory!");
                    break;
                }
            }
        }
    }

    public void sell(int type){
        first_loop:
        while (true){
            System.out.println("Here's what you have: ");
            if (type == 1){
                customer.get_gears().print_armor();
            }
            else if (type == 2){
                customer.get_gears().print_weapon();
            }
            else if (type == 3){
                customer.get_gears().print_potion();
            }
            System.out.println("------------------------------------------------------------------");
            System.out.println("0. Back to previous menu");
            int choice;
            Gear g;
            second_loop:
            while(true) {
                try {
                    choice = Integer.parseInt(input.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("The input is not a number. Please re-enter:");
                    continue;
                }
                if (choice == 0) {
                    break first_loop;
                } else {
                    if (type == 1 && choice <= customer.get_gears().get_armor_num()) {
                        g = customer.get_gears().get_armor(choice-1);
                    } else if (type == 2 && choice <= customer.get_gears().get_weapon_num()) {
                        g = customer.get_gears().get_weapon(choice-1);
                    } else if (type == 3 && choice <= customer.get_gears().get_potion_num()) {
                        g = customer.get_gears().get_potion(choice-1);
                    } else {
                        System.out.println("The input is invalid. Please re-enter:");
                        continue;
                    }
                }
                String confirm;
                System.out.println("You will sell this item for " + g.get_cost()/2 + " golds, are you sure? (y/n)");
                confirm = input.nextLine();
                while (true){
                    if (Objects.equals(confirm, "y")){
                        customer.change_money(g.get_cost()/2);
                        if (type == 1){
                            customer.get_gears().remove_armor((Armory) g);
                        }
                        else if (type == 2){
                            customer.get_gears().remove_weapon((Weaponry) g);
                        }
                        else {
                            customer.get_gears().remove_potion((Potion) g);
                        }
                        System.out.println("You successfully sold " + g.get_name() + "!");
                        break second_loop;
                    }
                    else if (Objects.equals(confirm, "n")){
                        break second_loop;
                    }
                    else{
                        System.out.println("The input is invalid. Please re-enter:");
                    }
                }
            }
        }

    }
}
