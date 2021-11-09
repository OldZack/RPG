import java.util.ArrayList;
import java.util.Objects;

public class Map {

    private Tile [][] tileList;
    private ArrayList tiles;
    private int size;
    private int num_markets;
    private int num_blocks;
    private int hero_number;
    private int curr_position;
    private int new_position;

    Map(int s, double market_ratio, double block_ratio, int hn){
        this.size = s;
        this.num_markets = (int)(size*size*market_ratio);
        this.num_blocks = (int)(size*size*block_ratio);
        this.hero_number = hn;

        tiles = new ArrayList();
        for (int i = 0; i < size*size; i++){
            tiles.add(i);
        }

        int num;
        int [] markets = new int [num_markets];
        int [] blocks = new int [num_blocks];
        for (int i = 0; i < num_markets; i++){
            while(true){
                num = (int) (Math.random()*(size*size));
                if (tiles.remove(Integer.valueOf(num))){
                    markets[i] = num;
                    break;
                }
            }
        }
        for (int i = 0; i < num_blocks; i++){
            while(true){
                num = (int) (Math.random()*(size*size));
                if (tiles.remove(Integer.valueOf(num))){
                    blocks[i] = num;
                    break;
                }
            }
        }

        tileList = new Tile[size][size];
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++) {
                tileList[i][j] = new Tile();
            }
        }

        int pos;
        for (int market : markets) {
            pos = market;
            tileList[(int) (pos / size)][pos % size].set_content("market");
        }
        for (int block : blocks) {
            pos = block;
            tileList[(int) (pos / size)][pos % size].set_content("block");
        }
        place_player();
    }

    public void place_player(){
        while(true){
            curr_position = (int) tiles.get((int) (Math.random()*(tiles.size())));
            if (this.is_movable("w") && this.is_movable("s") && this.is_movable("a") && this.is_movable("d")){
                break;
            }
        }
        tileList[(int)(curr_position/size)][curr_position%size].set_content("player");
    }

    public void print_map(){
        System.out.print("\033[32m"+ "+");
        for (int j = 0; j < size; j++){
            System.out.print("------+");
        }
        for (int i = 0; i < size; i++){


            System.out.print("\n|");
            for (int j = 0; j < size; j++){
                if (Objects.equals(tileList[i][j].get_content(), "market") || Objects.equals(tileList[i][j].get_content(), "market&player")){
                    System.out.print(" /**\\ |");
                }
                else if (Objects.equals(tileList[i][j].get_content(), "block")){
                    System.out.print(" ^^^^ |");
                }
                else if (Objects.equals(tileList[i][j].get_content(), "player")){
                    System.out.print("  "+"\033[31m"+ "o" + "\033[32m"+ "   |");
                }
                else{
                    System.out.print("      |");
                }
            }

            System.out.print("\n|");
            for (int j = 0; j < size; j++){
                if (Objects.equals(tileList[i][j].get_content(), "market")){
                    System.out.print(" |__| |");
                }
                else if (Objects.equals(tileList[i][j].get_content(), "block")){
                    System.out.print(" ^^^^ |");
                }
                else if (Objects.equals(tileList[i][j].get_content(), "market&player")){
                    System.out.print(" |" + "\033[31m"+ "o" + "\033[32m"+ "_| |");
                }
                else if (Objects.equals(tileList[i][j].get_content(), "player")){
                    if (hero_number == 3){
                        System.out.print(" "+"\033[31m"+ "o  o" + "\033[32m"+ " |");
                    }
                    else if (hero_number == 2){
                        System.out.print(" "+"\033[31m"+ "o" + "\033[32m"+ "    |");
                    }
                    else{
                        System.out.print("      |");
                    }
                }
                else{
                    System.out.print("      |");
                }
            }

            System.out.print("\n+");
            for (int j = 0; j < size; j++){
                System.out.print("------+");
            }
        }
        System.out.println("\n" +"\033[m");
    }

    public boolean is_movable(String direction){
        if (Objects.equals(direction, "w")){
            if (curr_position/size == 0){
                return false;
            }
            new_position = curr_position - size;
        }
        else if (Objects.equals(direction, "s")){
            if (curr_position/size == size-1){
                return false;
            }
            new_position = curr_position + size;
        }
        else if (Objects.equals(direction, "a")){
            if (curr_position%size == 0){
                return false;
            }
            new_position = curr_position - 1;
        }
        else if (Objects.equals(direction, "d")){
            if (curr_position%size == size-1){
                return false;
            }
            new_position = curr_position + 1;
        }
        else{
            return false;
        }

        // Check if the player will enter a blocked area.
        if (Objects.equals(tileList[new_position / size][new_position % size].get_content(), "block")){
            return false;
        }
        return true;
    }

    public void move(){
        if (Objects.equals(tileList[curr_position / size][curr_position % size].get_content(), "player")){
            tileList[curr_position/size][curr_position%size].set_content("Null");
        }
        else{
            tileList[curr_position/size][curr_position%size].set_content("market");
        }

        if (Objects.equals(tileList[new_position / size][new_position % size].get_content(), "Null")){
            tileList[new_position/size][new_position%size].set_content("player");
        }
        else{
            tileList[new_position/size][new_position%size].set_content("market&player");
        }
        curr_position = new_position;
    }

    public String get_current_content(){
        return tileList[curr_position/size][curr_position%size].get_content();
    }


}
