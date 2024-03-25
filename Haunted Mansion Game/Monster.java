/**
 * @author Shruti Marx
 * @version 1.0
 */
public abstract class Monster {
    private int frightFactor;
    /**
     * Constructs a Monster object.
     * @param frightFactor a monster's fright factor
     */
    public Monster(int frightFactor) {
        this.frightFactor = frightFactor;
    }
    /**
     * Reduces the player's courage by the fright factor.
     * @param player Player object.
     */
    public void frighten(Player player) {
        System.out.println("Suddenly, something appears in front of " + player.getName());
        System.out.println(this.toString());
        player.setCourage(player.getCourage() - this.frightFactor);
        player.frighten(frightFactor);

    }

    /**
     * This is the haunt methods that child classes must override.
     * @param arr represents the Rooms array.
     * @param player Player object.
     */
    public abstract void haunt(Room[][] arr, Player player);

    /**
     * Monster's to String method.
     * @return the details of the monster.
     */
    public String toString() {
        return  "Fright factor of " + frightFactor;
    }
}
