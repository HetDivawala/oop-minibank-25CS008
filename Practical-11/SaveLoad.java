import java.io.*;

class Player implements Serializable
{
    private static final long serialVersionUID = 1L;

    String name;
    int score;
    transient String password;

    Player(String name, int score, String password){
        this.name = name;
        this.score = score;
        this.password = password;
    }

    @Override
    public String toString(){ return name + " | " + score + " | password: " + password; }
}

public class SaveLoad {
public static void main(String[] args) throws IOException, ClassNotFoundException
{
    File file = new File("players.dat");

    Player[] players = {new Player("Riya", 90, "secret1"), new Player("Raj", 75, "secret2"), new Player("Tony", 60, "secret3")};

    try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))){
       out.writeObject(players);
    }
    System.out.println("Saved:");
    for(Player p : players){
       System.out.println(p);
    }

    Player[] loaded;
    try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))){
       loaded = (Player[]) in.readObject();
    }
    System.out.println("Loaded (transient password is not saved):");
    for(Player p : loaded){
       System.out.println(p);
    }

    file.delete();
}
}