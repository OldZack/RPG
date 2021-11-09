import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// GameManager class is like a "menu" for all games available. Only contains gameList which is a list of games.
public class GameManager {
    private RPG[] gameList;

    // The entire list will have 2 default games for now :)
    public GameManager() throws FileNotFoundException {
        LMH b = new LMH();
        this.gameList = new RPG[]{b};
    }

    // get the current game list
    public RPG[] getGameList() {
        return this.gameList;
    }

    // prompts the user to select a game from the game list.
    public RPG selection() {
        Scanner in = new Scanner(System.in);
        String pattern = "[1]";
        String bORt = "";
        while(true) {
            bORt = in.next();
            if(!bORt.matches(pattern)) {
                System.out.println("Error: Wrong input! Please input again.");
                continue;
            }
            else
                break;
        }
        return getGameList()[0];
    }

    // the GameManager system starts. It allows user to select and play the games in game list.
    public void run() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        System.out.println("Welcome to the Game Center. What game you want to play?");
        System.out.println("1. Legends: Heroes and Monsters.");
        System.out.println("Other games under development...");
        boolean next = true;

        RPG g = selection();
        g.startGame();

    }

}
