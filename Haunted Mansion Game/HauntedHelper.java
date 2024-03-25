import java.util.HashSet;
import java.util.Random;
import java.awt.Point;

/**
 * Class containing utility functions to help with the creation and visualization of mansions.
 *
 * @author Ron Guter
 * @version 1.0
 */
public class HauntedHelper {
    public static final String EXPLORED_MARKER = " O ";
    public static final String UNEXPLORED_MARKER = " \u25A0 ";
    public static final String LOCKED_MARKER = " X ";
    public static final String GOAL_MARKER = " G ";
    public static final String LOC_MARKER = " @ ";

    private Random random;

    private HashSet<Point> monsterRooms;
    private HashSet<Point> snackRooms;

    private int monsterCounter = 0;

    private Room[][] rooms;

    private Room goalRoom;

    private int goalLocationRow;
    private int goalLocationColumn;

    /**
     * Constructs a new helper instance, which can be used to fill rooms of the given {@code Room}
     * array with certain properties.
     *
     * @param rooms The 2D Room array representing the mansion layout
     */
    public HauntedHelper(Room[][] rooms) {
        this.rooms = rooms;

        monsterRooms = new HashSet<>();
        snackRooms = new HashSet<>();

        monsterRooms.add(new Point(rooms.length >> 1, rooms[0].length >> 1));
        snackRooms.add(new Point(rooms.length >> 1, rooms[0].length >> 1));

        random = new Random(System.currentTimeMillis());
    }

    /**
     * Selects a random room (distinct from the starting room) to be the goal room and returns it.
     * @return The selected goal room
     */
    public Room selectGoalRoom() {
        do {
            goalLocationRow = random.nextInt(rooms.length);
            goalLocationColumn = random.nextInt(rooms[0].length);
        } while (goalLocationRow != (rooms.length >> 1) && goalLocationColumn != (rooms[0].length >> 1));

        goalRoom = rooms[goalLocationRow][goalLocationColumn];

        monsterRooms.add(new Point(goalLocationRow, goalLocationColumn));
        snackRooms.add(new Point(goalLocationRow, goalLocationColumn));

        return goalRoom;
    }

    /**
     * Places a new monster in a random room which does not already have one and is not the starting or goal room.
     * The monster which is placed is on a set rotation of MinerFortyNiner -> Ghost -> Ghoul.
     */
    public void placeMonster() {
        Point mPt = new Point();

        do {
            mPt.setLocation(random.nextInt(rooms.length), random.nextInt(rooms[0].length));
        } while (monsterRooms.contains(mPt));

        monsterRooms.add(mPt);

        Room room = rooms[mPt.x][mPt.y];
        if (monsterCounter % 3 == 0) {
            room.setMonster(new MinerFortyNiner());
        } else if (monsterCounter % 3 == 1) {
            room.setMonster(new Ghost());
        } else {
            room.setMonster(new Ghoul());
        }
        ++monsterCounter;
    }

    /**
     * Places a snack in a random room which does not already have one and is not the starting or goal room.
     */
    public void placeSnack() {
        Point sPt = new Point();

        do {
            sPt.setLocation(random.nextInt(rooms.length), random.nextInt(rooms[0].length));
        } while (snackRooms.contains(sPt));

        snackRooms.add(sPt);

        Room room = rooms[sPt.x][sPt.y];
        room.setHasSnacks(true);
    }


    /**
     * This is for debugging purposes only. Returns the row index of the goal room of the mansion
     * this helper is constructing.
     * @return The row index
     */
    public int getGoalLocationRow() {
        return goalLocationRow;
    }

    /**
     * This is for debugging purposes only. Returns the column index of the goal room of the mansion
     * this helper is constructing.
     * @return The column index
     */
    public int getGoalLocationColumn() {
        return goalLocationColumn;
    }

    /**
     * Creates a text visualization of the given mansion, marking the current given location.
     * An explored room is marked by {@value #EXPLORED_MARKER},
     * an unexplored room by {@value #UNEXPLORED_MARKER},
     * a locked room by {@value #LOCKED_MARKER},
     * current room by {@value #LOC_MARKER}.
     * @param rooms The room array of the mansion to visualize
     * @param currentRow The row index of the current room
     * @param currentColumn The column index of the current room.
     * @return A text visualization of the mansion
     *
     * @throws IllegalArgumentException If the given mansion is null
     */
    public static String createMansionMap(Room[][] rooms, int currentRow, int currentColumn) {
        String[][] mapArr = createMap(rooms);

        mapArr[currentRow + 1][currentColumn + 1] = LOC_MARKER;

        StringBuilder map = new StringBuilder();

        for (int r = 0; r < mapArr.length; ++r) {
            for (int c = 0; c < mapArr[r].length; ++c) {
                map.append(mapArr[r][c]);
            }
            map.append('\n');
        }

        return map.toString();
    }

    /**
     * This is for debugging purposes only. Creates a text visualization of the
     * given mansion similar to {@link #createMansionMap(Room[][], int, int)},
     * but does not display a current location and instead reveals
     * the location of the goal room with a {@value #GOAL_MARKER}.
     * @param rooms The room array of the mansion to visualize
     * @param goalRoom The goal room of the mansion
     * @return A text visualization of the mansion
     *
     * @throws IllegalArgumentException If the given mansion is null
     */
    public static String createFullMansionMap(Room[][] rooms, Room goalRoom) {
        String[][] mapArr = createMap(rooms);

        StringBuilder map = new StringBuilder();

        for (int r = 0; r < mapArr.length; ++r) {
            for (int c = 0; c < mapArr[r].length; ++c) {
                if (c > 0 && r > 0 && r <= rooms.length && c <= rooms[r - 1].length
                    && rooms[r - 1][c - 1] == goalRoom) {
                    map.append(GOAL_MARKER);
                } else {
                    map.append(mapArr[r][c]);
                }
            }
            map.append('\n');
        }

        return map.toString();
    }

    private static String[][] createMap(Room[][] rooms) {
        if (rooms.length == 0 || rooms[0].length == 0) {
            throw new IllegalArgumentException("You must pass in a room array with non-zero length!");
        }

        String[][] arr = new String[rooms.length + 2][rooms[0].length + 2];

        for (int r = 0; r < arr.length; ++r) {
            for (int c = 0; c < arr[r].length; ++c) {
                if (r == 0 || r == arr.length - 1) {
                    if (c == 0 || c == arr[r].length - 1) {
                        arr[r][c] = "-";
                    } else {
                        arr[r][c] = "---";
                    }
                } else if (c == 0 || c == arr[r].length - 1) {
                    arr[r][c] = "|";
                } else {
                    Room room = rooms[r - 1][c - 1];
                    if (room.isExplored()) {
                        arr[r][c] = room.isLocked() ? LOCKED_MARKER : EXPLORED_MARKER;
                    } else {
                        arr[r][c] = UNEXPLORED_MARKER;
                    }
                }
            }
        }

        return arr;
    }
}
