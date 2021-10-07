import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The type Level data structure.
 */
//Allen Bronshtein
//206228751
public class LevelDataStructure {
    private Map<String, String> dataMap;
    private String dataString;
    private String name;
    private String background;
    private String ballVelocities;
    private int paddleSpeed;
    private int paddleWidth;
    private File blockDefinitions;
    private int blockStartX;
    private int blockStartY;
    private int rowHeight;
    private int numBlocks;
    private List<Velocity> velocityArrayList = new ArrayList<>();

    /**
     * Instantiates a new Level data structure.
     *
     * @param dataMap    the data map
     * @param dataString the data string
     */
    public LevelDataStructure(Map<String, String> dataMap, String dataString) {
        this.dataMap = dataMap;
        this.dataString = dataString;
        make();
    }

    /**
     * Make.
     */
    public void make() {
        name = dataMap.get("level_name");
        background = dataMap.get("background");
        ballVelocities = dataMap.get("ball_velocities");
        blockDefinitions = new File(dataMap.get("block_definitions"));
        paddleSpeed = Integer.parseInt(dataMap.get("paddle_speed"));
        blockStartX = Integer.parseInt(dataMap.get("blocks_start_x"));
        blockStartY = Integer.parseInt(dataMap.get("blocks_start_y"));
        paddleWidth = Integer.parseInt(dataMap.get("paddle_width"));
        rowHeight = Integer.parseInt(dataMap.get("row_height"));
        numBlocks = Integer.parseInt(dataMap.get("num_blocks"));
        String[] velocities = ballVelocities.split(" ");
        for (String velocity : velocities) {
            String[] v = velocity.split(",");
            velocityArrayList.add(new Velocity(Integer.parseInt(v[0]), Integer.parseInt(v[1])));
        }
    }

    /**
     * Gets block definitions.
     *
     * @return the block definitions
     */
    public File getBlockDefinitions() {
        return blockDefinitions;
    }

    /**
     * Gets block start x.
     *
     * @return the block start x
     */
    public int getBlockStartX() {
        return blockStartX;
    }

    /**
     * Gets block start y.
     *
     * @return the block start y
     */
    public int getBlockStartY() {
        return blockStartY;
    }

    /**
     * Gets num blocks.
     *
     * @return the num blocks
     */
    public int getNumBlocks() {
        return numBlocks;
    }

    /**
     * Gets paddle speed.
     *
     * @return the paddle speed
     */
    public int getPaddleSpeed() {
        return paddleSpeed;
    }

    /**
     * Gets paddle width.
     *
     * @return the paddle width
     */
    public int getPaddleWidth() {
        return paddleWidth;
    }

    /**
     * Gets row height.
     *
     * @return the row height
     */
    public int getRowHeight() {
        return rowHeight;
    }

    /**
     * Gets velocity array list.
     *
     * @return the velocity array list
     */
    public List<Velocity> getVelocityArrayList() {
        return velocityArrayList;
    }

    /**
     * Gets background.
     *
     * @return the background
     */
    public String getBackground() {
        return background;
    }

    /**
     * Gets ball velocities.
     *
     * @return the ball velocities
     */
    public String getBallVelocities() {
        return ballVelocities;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets data string.
     *
     * @return the data string
     */
    public String getDataString() {
        return dataString;
    }

}

