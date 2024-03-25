/**
 * @author Shruti Marx
 * @version 1.0
 * Represents a player in the game
 */
public class Player {
    private String name;
    private static final int MAX_COURAGE = 150;
    private int courage;
    private int mansionSize;
    private int currRow;
    private int currCol;
    private int prevRow;
    private int prevCol;

    /**
     * Contstructs a new Player object.
     * @param name the name of the player.
     * @param mansionSize the size of the mansion.
     */
    public Player(String name, int mansionSize) {
        this.name = name;
        this.courage = MAX_COURAGE;
        this.mansionSize = mansionSize;
        this.currCol = mansionSize / 2;
        this.currRow = mansionSize / 2;
        this.prevCol = currCol;
        this.prevRow = currRow;

    }


    /**
     * Reduces the courage by a fright factor.
     * @param fright takes in an integer fright value.
     */
    public void frighten(int fright) {
        this.courage -= fright;
        this.courage = Math.max(0, this.courage);
        System.out.printf("%s got scared and lost some courage! %d/%d remaining...\n",
            this.name, this.courage, this.MAX_COURAGE);
    }

    /**
     * Increments courage value depending on whether room has Scooby Snacks.
     * @param room represents a room in the mansion.
     */
    public void lookForSnacks(Room room) {
        if (room.getHasSnacks()) {
            room.setHasSnacks(false);
            this.courage += 25;
            this.courage = Math.min(this.MAX_COURAGE, this.courage);
            System.out.printf("%s ate a Scooby Snack they found in the room and felt a little more confident. "
                + "Courage increased to %d/%d.\n", this.name, this.courage, this.MAX_COURAGE);
        }
    }

    /**
     * Getter method for name.
     * @return player's name.
     */
    public String getName() {
        return this.name;
    }
    /**
     * Getter method for courage.
     * @return player's courage.
     */
    public int getCourage() {
        return this.courage;
    }
    /**
     * Setter method for player's courage.
     * @param courage player's courage.
     */
    public void setCourage(int courage) {
        this.courage = courage;
    }
    /**
     * Getter method for current column.
     * @return current column.
     */
    public int getCurrCol() {
        return this.currCol;
    }
    /**
     * Getter method for previous column.
     * @return previous column.
     */
    public int getPrevCol() {
        return this.prevCol;
    }
    /**
     * Getter method for current row.
     * @return current row.
     */
    public int getCurrRow() {
        return this.currRow;
    }
    /**
     * Getter method for previous row.
     * @return previous row.
     */
    public int getPrevRow() {
        return this.prevRow;
    }
    /**
     * Setter method for current column.
     * @param currCol current column.
     */
    public void setCurrCol(int currCol) {
        this.currCol = currCol;
    }
    /**
     * Setter method for previous column.
     * @param prevCol previous column.
     */
    public void setPrevCol(int prevCol) {
        this.prevCol = prevCol;
    }
    /**
     * Setter method for current row.
     * @param currRow current row.
     */
    public void setCurrRow(int currRow) {
        this.currRow = currRow;
    }

    /**
     * Setter for previous row.
     * @param prevRow previous row.
     */
    public void setPrevRow(int prevRow) {
        this.prevRow = prevRow;
    }

    /**
     * Getter method for mansion size.
     * @return mansion size.
     */
    public int getMansionSize() {
        return this.mansionSize;
    }
}
