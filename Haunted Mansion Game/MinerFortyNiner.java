import java.util.Random;
/**
 * @author Shruti Marx
 * @version 1.0
 */
public class MinerFortyNiner extends Monster {
    /**
     * Constructs a Miner Forty-Niner object.
     */
    public MinerFortyNiner() {
        super(49);
    }


    @Override
    public void haunt(Room[][] arr, Player player) {
        Random random = new Random();
        int newRow = random.nextInt(arr.length);
        int newCol = random.nextInt(arr[0].length);

        player.setPrevRow(player.getCurrRow());
        player.setPrevCol(player.getCurrCol());
        player.setCurrRow(newRow);
        player.setCurrCol(newCol);

        System.out.printf("The miner raises its pickaxe and disappears. "
            + "A hole appears under %s and they fall into another room...", player.getName());
    }

    @Override
    public String toString() {
        return "I am the Miner Forty-Niner! " + super.toString();
    }
}
