/**
 * Represents a Room.
 * @author Shruti Marx
 * @version 1.0
 */
public class Room {
    private Monster monster;
    private boolean hasSnacks;
    private boolean explored;
    private boolean locked;
    /**
     * Constructs a Room object.
     * @param monster the type of monster.
     * @param hasSnacks whether or not the room has snacks.
     */
    public Room(Monster monster, boolean hasSnacks) {
        this.monster = monster;
        this.hasSnacks = hasSnacks;
        this.explored = false;
        this.locked = false;
    }
    /**
     * Setter method to lock the room.
     * @param bool boolean to set the room to locked or unlocked.
     */
    public void setLocked(boolean bool) {
        this.locked = true;
    }
    /**
     * Getter method for whether the room is explored.
     * @return whether the room is explored.
     */
    public boolean isExplored() {
        return this.explored;
    }
    /**
     * Getter method locked.
     * @return whether or not the room is locked.
     */
    public boolean isLocked() {
        return this.locked;
    }
    /**
     * Setter method to set snacks in a room.
     * @param bool boolean to set snacks in a room.
     */
    public void setHasSnacks(boolean bool) {
        this.hasSnacks = bool;
    }

    /**
     * Returns whether room has snacks.
     * @return whether room has snacks
     */
    public boolean getHasSnacks() {
        return this.hasSnacks;
    }

    /**
     * Getter for monster.
     * @return the room's monster
     */
    public Monster getMonster() {
        return this.monster;
    }

    /**
     * Setter for monster.
     * @param monster the monster that is in this room
     */
    public void setMonster(Monster monster) {
        this.monster = monster;
    }

    /**
     * Explores the current room.
     */
    public void beExplored() {
        if (this.monster == null && !(this.hasSnacks)) {
            System.out.println("It doesn’t look like there’s anything here...");
        }
        this.explored = true;
    }
}
