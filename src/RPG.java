import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public abstract class RPG {
    protected ArrayList<Player> players = new ArrayList<Player>();

    public RPG() {
        this.players.add(new Player());
    }

    public abstract void character_selection() throws FileNotFoundException;
    public abstract void startGame() throws IOException, UnsupportedAudioFileException, LineUnavailableException;
    public abstract void round() throws FileNotFoundException;



}
