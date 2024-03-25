import java.util.Scanner;
/**
 * @author Shruti Marx
 * @version 1.0
 * Represents a Haunted Mansion.
 */
public class HauntedMansion {
    private String mansionName;
    private Player player;
    private Room[][] rooms;
    private Scanner scan;
    private Room goalRoom;
    /**
     * Constructs a new Haunted Mansion.
     * @param mansionName the name of mansion.
     * @param player the player in the mansion.
     * @param scan the Scanner object to take in user input.
     * @param mansionSize the size of the mansion.
     * @param monsterPercentage the percentage of rooms out of 100 that have monsters.
     * @param snackRooms the number of rooms with Scooby Snacks.
     */
    public HauntedMansion(String mansionName, Player player, Scanner scan, int mansionSize,
        int monsterPercentage, int snackRooms) {
        this.mansionName = mansionName;
        this.player = player;
        this.scan = scan;
        double percent = monsterPercentage / 100.0;
        int numMonsterRooms = (int) (percent * mansionSize * mansionSize);
        this.rooms = new Room[mansionSize][mansionSize];
        HauntedHelper h = new HauntedHelper(rooms);
        for (int i = 0; i < mansionSize; i++) {
            for (int j = 0; j < mansionSize; j++) {
                rooms[i][j] = new Room(null, false);
            }
        }

        for (int i = 0; i < numMonsterRooms; i++) {
            h.placeMonster();
        }
        for (int i = 0; i < snackRooms; i++) {
            h.placeSnack();
        }
        goalRoom = h.selectGoalRoom();
    }


    /**
     * The message returned when the player wins.
     */
    public void win() {
        System.out.println("message of victory: boom-chaka-laka-boom-boom! you won! yayayayayay");
    }
    /**
     * The message returned when the player lose.
     */
    public void lose() {
        System.out.println("defeat message: aw shucks you lost :(");
    }
    /**
     * Places player into game and starts the game.
     */
    public void enter() {
        System.out.printf("%s enters %s. The halls are "
            + "dimly lit and sounds echo from deeper within... "
                + "%s looks behind them only to see the "
                    + "entrance is gone! They’ll have to find a way out...",
                        player.getName(), this.mansionName, player.getName());
        while (true) {
            Room currRoom = rooms[player.getCurrRow()][player.getCurrCol()];
            if (currRoom.equals(goalRoom)) {
                win();
                break;
            }
            player.lookForSnacks(currRoom);

            Monster monster = currRoom.getMonster();
            if (monster != null) {
                monster.frighten(player);
                monster.haunt(rooms, player);
            } else {
                move();
            }

            if (player.getCourage() <= 0) {
                lose();
                break;
            }
        }
    }
    /**
     * Moves player.
     */
    public void move() {
        System.out.print("Enter the direction you would like to move in (l/r/u/d/map): ");
        String direction = scan.nextLine();
        if (direction.equals("l")) {
            if (player.getCurrCol() - 1 < 0) {
                System.out.println("There is only a wall in that direction!");
                move();
            } else if (rooms[player.getCurrRow()][player.getCurrCol() - 1].isLocked()) {
                System.out.println("The room in that direction is locked off!");
                move();
            } else {
                player.setPrevCol(player.getCurrCol());
                player.setCurrCol(player.getCurrCol() - 1);
                rooms[player.getCurrRow()][player.getCurrCol()].beExplored();
            }
        } else if (direction.equals("r")) {
            if (player.getCurrCol() + 1 >= rooms.length) {
                System.out.println("There is only a wall in that direction!");
                move();
            } else if (rooms[player.getCurrRow()][player.getCurrCol() + 1].isLocked()) {
                System.out.println("The room in that direction is locked off!");
                move();
            } else {
                player.setPrevCol(player.getCurrCol());
                player.setCurrCol(player.getCurrCol() + 1);
                rooms[player.getCurrRow()][player.getCurrCol()].beExplored();
            }
        } else if (direction.equals("u")) {
            if (player.getCurrRow() - 1 < 0) {
                System.out.println("There is only a wall in that direction!");
                move();
            } else if (rooms[player.getCurrRow() - 1][player.getCurrCol()].isLocked()) {
                System.out.println("The room in that direction is locked off!");
                move();
            } else {
                player.setPrevRow(player.getCurrRow());
                player.setCurrRow(player.getCurrRow() - 1);
                rooms[player.getCurrRow()][player.getCurrCol()].beExplored();
            }
        } else if (direction.equals("d")) {
            if (player.getCurrRow() + 1 >= rooms.length) {
                System.out.println("There is only a wall in that direction!");
                move();
            } else if (rooms[player.getCurrRow() + 1][player.getCurrCol()].isLocked()) {
                System.out.println("The room in that direction is locked off!");
                move();
            } else {
                player.setPrevRow(player.getCurrRow());
                player.setCurrRow(player.getCurrRow() + 1);
                rooms[player.getCurrRow()][player.getCurrCol()].beExplored();
            }
        } else if (direction.equals("map")) {
            System.out.println(HauntedHelper.createMansionMap(rooms, player.getCurrRow(), player.getCurrCol()));
            move();
        } else {
            System.out.println("Invalid Entry, please try again!");
            move();
        }
    }
}
