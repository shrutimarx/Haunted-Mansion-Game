/**
 * @author Shruti Marx
 * @version 1.0
 * Represents a Ghoul
 */
public class Ghoul extends Ghost {

    @Override
    public void haunt(Room[][] arr, Player player) {
        super.haunt(arr, player);
        Room currRoom = arr[player.getCurrRow()][player.getCurrCol()];
        currRoom.setLocked(true);
        System.out.printf(
            "The doors slam shut behind %s. It doesn’t seem like they can be reopened...", player.getName());
    }
    @Override
    public String toString() {
        return "I am the Ghoul! Watch out!";
    }
}
