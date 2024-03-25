/**
 * @author Shruti Marx
 * @version 1.0
 * Represents a Ghost
 */
public class Ghost extends Monster {
    /**
     * Constructs a Ghost object.
     */
    public Ghost() {
        super(10);

    }

    @Override
    public void haunt(Room[][] arr, Player player) {
        player.setCurrRow(player.getPrevRow());
        player.setCurrCol(player.getCurrRow());
        System.out.printf("%s is teleported back to where they were before.\n", player.getName());
    }

    @Override
    public String toString() {
        return "I am a Ghost! " + super.toString();
    }
}
