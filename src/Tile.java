public class Tile {
    private String content;

    Tile(){
        content = "Null";
    }

    Tile(String c){
        content = c;
    }

    public String get_content(){
        return this.content;
    }

    public void set_content(String c){
        content = c;
    }
}
