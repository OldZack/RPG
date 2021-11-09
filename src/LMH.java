import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.*;

public class LMH extends RPG {

    private Map map;
    private Market market;
    private Enemy enemy;

    private Scanner input = new Scanner(System.in);
    private Parser p = new Parser();

    LMH() throws FileNotFoundException {
        super();
        market = new Market();
        enemy = new Enemy();
    }

    @Override
    public void character_selection() throws FileNotFoundException {
        System.out.println("Please enter the number of heroes in your team (at most 3):");

        // Let player choose the number of heroes.
        int s;
        while (true){
            try {
                s = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("The input is not a number. Please re-enter:");
                continue;
            }
            if (s == 1 || s == 2 || s == 3){
                break;
            }
            System.out.println("The input number is incorrect. Please re-enter:");
        }

        // Let player pick each of the heroes.
        ArrayList<Hero> warriors = p.parse_hero("Warriors");
        ArrayList<Hero> sorcerers = p.parse_hero("Sorcerers");
        ArrayList<Hero> paladins = p.parse_hero("Paladins");

        String [] order  = {"first", "second", "third"};
        int role_num;
        for (int i = 0; i < s; i++){
            System.out.println("Please choose the role of your " + order[i] + " hero (by entering the number): ");
            System.out.println("1. Warrior");
            System.out.println("2. Sorcerer");
            System.out.println("3. Paladin");
            while (true){
                try {
                    role_num = Integer.parseInt(input.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("The input is not a number. Please re-enter:");
                    continue;
                }
                if (role_num == 1){
                    generate_hero(warriors);
                    break;
                }
                else if (role_num == 2){
                    generate_hero(sorcerers);
                    break;
                }
                else if (role_num == 3){
                    generate_hero(paladins);
                    break;
                }
                System.out.println("The input number is incorrect. Please re-enter:");
            }

        }
        System.out.println("\nYou have formed your team! Now it's the time to start your adventure!");
    }

    private void generate_hero(ArrayList<Hero> heroes){
        int hero_num;
        for (int j = 0; j < heroes.size(); j++){
            System.out.println(j+1 + ". " + heroes.get(j).get_name());
        }
        System.out.println("Please choose one of the heroes above (by entering the number): ");
        while(true){
            try {
                hero_num = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("The input is not a number. Please re-enter:");
                continue;
            }
            if (0 < hero_num && hero_num <= heroes.size()){
                players.get(0).add_heroes(heroes.get(hero_num-1));
                System.out.println("You have recruited "+heroes.get(hero_num-1).get_name()+" to your team!");
                heroes.remove(hero_num-1);
                break;
            }
            System.out.println("The input number does not have a corresponding hero. Please re-enter:");
        }
    }

    public void round(){
        first_loop:
        while (true){
            map.print_map();
            System.out.println("+----------------------------------------------------------+");
            System.out.println("|   Please Enter One of the Following Keys to Take Action  |");
            System.out.println("| [w]Move Up | [a]Move Left | [s]Move down | [d]Move Right |");
            System.out.println("|    [e]Check Equipments | [i]Show info | [q]Quit Game     |");
            System.out.println("+----------------------------------------------------------+");
            String command;
            second_loop:
            while(true){
                command = input.nextLine();
                if (Objects.equals(command, "w") || Objects.equals(command, "s") || Objects.equals(command, "a") || Objects.equals(command, "d")){
                    if(this.map.is_movable(command)){
                        map.move();
                        if (this.map.get_current_content() == "market&player"){
                            this.enter_market();
                        }
                        else{
                            this.enter_common_space();
                        }
                        break first_loop;
                    }
                    else{
                        System.out.println("The move cannot be done. Please re-enter the action:");
                        continue;
                    }
                }
                else if (Objects.equals(command, "e")){
                    this.check_equips();
                    break second_loop;
                }
                else if (Objects.equals(command, "i")){
                    players.get(0).print_detailed_status();
                    System.out.println("Enter c to close.");
                    command = input.nextLine();
                    while (true){
                        if (Objects.equals(command, "c")){
                            break second_loop;
                        }
                        System.out.println("The input is invalid. Please re-enter:");
                    }
                }
                else if (Objects.equals(command, "q")){
                    System.exit(0);
                }
                System.out.println("The input is not an action. Please re-enter:");
            }
        }

    }

    public void enter_market(){
        first_loop:
        while(true){
            System.out.println("Welcome to the market! Whose gear are you purchasing/selling?");
            for (int i = 0; i < players.get(0).get_hero_number(); i++){
                System.out.println(i+1 + ". " + players.get(0).get_hero(i).get_name());
            }
            System.out.println("0. Leave");
            int choice;
            while(true){
                try {
                    choice = Integer.parseInt(input.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("The input is not a number. Please re-enter:");
                    continue;
                }
                if (choice > 0 && choice <= players.get(0).get_hero_number()){
                    market.enter_market(players.get(0).get_hero(choice-1));
                    break;
                }
                else if (choice == 0){
                    break first_loop;
                }
                System.out.println("The input is invalid. Please re-enter:");
            }
        }
    }

    public void enter_common_space(){
        if (Math.random() < 0.5){
            System.out.println("Entering combat...");

            int team_size = players.get(0).get_hero_number();
            int highest_level = players.get(0).get_highest_level();

            enemy.generate_team(team_size, highest_level);
            while(players.get(0).isAlive() && enemy.isAlive()){
                this.battle_round();
            }
            if (!players.get(0).isAlive()){
                players.get(0).reset();
            }
            else if (!enemy.isAlive()){
                players.get(0).reward();
            }

        }
    }

    public void battle_round(){
        players.get(0).print_status();
        enemy.print_status();
        players.get(0).action(enemy);
        enemy.action(players.get(0));
    }

    public void check_equips(){
        first_loop:
        while(true){
            System.out.println("Which hero's equipment do you want to check?");
            for (int i = 0; i < players.get(0).get_hero_number(); i++){
                System.out.println(i+1 + ". " + players.get(0).get_hero(i).get_name());
            }
            System.out.println("0. Close");
            int choice;
            while(true){
                try {
                    choice = Integer.parseInt(input.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("The input is not a number. Please re-enter:");
                    continue;
                }
                if (choice > 0 && choice <= players.get(0).get_hero_number()){
                    players.get(0).get_hero(choice-1).check_equips();
                    break;
                }
                else if (choice == 0){
                    break first_loop;
                }
                System.out.println("The input is invalid. Please re-enter:");
            }
        }
    }

    public void play_music() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        String filename = System.getProperty("user.dir") + "/src/files/" + "intro.wav";
        File file = new File(filename);
        AudioInputStream am;
        am = AudioSystem.getAudioInputStream(file);
        AudioFormat af = am.getFormat();
        SourceDataLine sd;
        sd = AudioSystem.getSourceDataLine(af);
        sd.open();
        sd.start();
        int sumByteRead = 0;
        byte[] b = new byte[320];
        while (sumByteRead != -1) {
            sumByteRead = am.read(b, 0, b.length);
            if (sumByteRead >= 0) {
                sd.write(b, 0, b.length);
            }
        }
    }

    @Override
    public void startGame() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        System.out.println("+---------------------------------------------+");
        System.out.println("|   "+ "\033[31m"+ "Welcome to Legends: Monsters and Heroes"+ "\033[m"+ "   |");
        System.out.println("+---------------------------------------------+");
        System.out.println("In this game you will control a team of heroes");
        System.out.println("taking adventure in a mysterious land. You will");
        System.out.println("be fighting monsters, leveling up and upgrading ");
        System.out.println("your equipments. You will start with forming up");
        System.out.println("your team, good luck, and have fun!\n");
        this.play_music();
        this.character_selection();

        this.map = new Map(8, 0.2, 0.3, players.get(0).get_hero_number());
        System.out.println("The map is shown as following. Each square represents a tile. ");
        System.out.println("Tiles that contains a house represents market, you can shop if you get there.");
        System.out.println("Tiles that contains a fence represents a non-entry area, you cannot get in there.");
        System.out.println("Tiles that contains 1/2/3 dots represents your position, each dot is a hero.");
        System.out.println("Tiles that contains nothing is a common-area, you may encounter monsters there.");
        while(true){
            this.round();
        }

    }
}

