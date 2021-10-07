import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Blocks definition reader.
 */
//Allen Bronshtein
//206228751
public class BlocksDefinitionReader {
    private BufferedReader reader;
    private Map<String, String> mapDefault;
    private Map<String, Map<String, String>> mapBlock;
    private Map<String, String> mapSpacers;

    /**
     * Load.
     *
     * @param filename the filename
     * @throws IOException the io exception
     */
    public void load(String filename) throws IOException {
        mapDefault = new HashMap<>();
        mapBlock = new HashMap<>();
        mapSpacers = new HashMap<>();
        InputStream in = BlocksDefinitionReader.class.getResourceAsStream(filename);
        InputStreamReader inputStreamReader = new InputStreamReader(in);
        reader = new BufferedReader(inputStreamReader);
    }

    /**
     * Run.
     *
     * @throws IOException the io exception
     */
    public void run() throws IOException {
        String line;
        String[] strings;
        line = reader.readLine();
        while (line.equals("")) {
            line = reader.readLine();
        }
        if (line.contains("#default") || line.contains("# default")) {
            line = reader.readLine();
            strings = line.split(" ");
            for (String s : strings) {
                if (s.equals("default")) {
                    continue;
                }
                String[] defS = s.split(":");
                mapDefault.put(defS[0], defS[1]);
            }
            reader.readLine();
            line = reader.readLine();
        }
        if (line.contains("#block") || line.contains("# block")) {
            line = reader.readLine();
            while (!line.equals("")) {
                Map<String, String> blockValue = new HashMap<>();
                String[] blockString = line.split(" ");
                for (int i = blockString.length - 1; i >= 1; i--) {
                    String[] bString = blockString[i].split(":");
                    blockValue.put(bString[0], bString[1]);
                    if (i == 1) {
                        mapBlock.put(bString[1], blockValue);
                    }
                }
                line = reader.readLine();
            }
        }
        line = reader.readLine();
        if (line.contains("#spacers") || line.contains("# spacers")) {
            line = reader.readLine();
            while (line != null) {
                strings = line.split(" ");
                String[] symbols = strings[1].split(":");
                String[] widths = strings[2].split(":");
                mapSpacers.put(symbols[1], widths[1]);
                line = reader.readLine();
            }
        }
    }

    /**
     * Gets map block.
     *
     * @return the map block
     */
    public Map<String, Map<String, String>> getMapBlock() {
        return mapBlock;
    }

    /**
     * Gets map default.
     *
     * @return the map default
     */
    public Map<String, String> getMapDefault() {
        return mapDefault;
    }

    /**
     * Gets map spacers.
     *
     * @return the map spacers
     */
    public Map<String, String> getMapSpacers() {
        return mapSpacers;
    }
}
