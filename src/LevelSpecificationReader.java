import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type Level specification reader.
 */
//Allen Bronshtein
//206228751
public class LevelSpecificationReader {
    private BufferedReader reader;

    /**
     * Load.
     *
     * @param fileName the file name
     * @throws IOException the io exception
     */
    public void load(String fileName) throws IOException {
        InputStream in = LevelSpecificationReader.class.getResourceAsStream("data/" + fileName);
        InputStreamReader inputStreamReader = new InputStreamReader(in);
        reader = new BufferedReader(inputStreamReader);
    }

    /**
     * Run list.
     *
     * @return the list
     * @throws IOException the io exception
     */
    public List<LevelDataStructure> run() throws IOException {
        String line = "";
        List<LevelDataStructure> levelInformation = new ArrayList<>();
        while (line != null) {
            String buildData = "";
            Map<String, String> dataMap = new HashMap<>();
            try {
                line = reader.readLine();
            } catch (IOException e) {
                System.out.println("ERROR READING LINE IN FILE ");
                break;
            }
            while (!line.equals("START_BLOCKS")) {
                if (!line.contains("START_LEVEL")) {
                    String[] strings = line.split(":");
                    if (strings[0].equals("background")) {
                        strings[1] = strings[1].substring(6, strings[1].length() - 1);
                    }
                    dataMap.put(strings[0], strings[1]);
                }
                try {
                    line = reader.readLine();
                } catch (IOException e) {
                    System.out.println("ERROR READING LINE IN FILE ");
                    break;
                }
            }
            try {
                line = reader.readLine();
            } catch (IOException e) {
                System.out.println("ERROR READING LINE IN FILE ");
                break;
            }
            while (!line.equals("END_BLOCKS")) {
                buildData = buildData + line + "/n";
                try {
                    line = reader.readLine();
                } catch (IOException e) {
                    System.out.println("ERROR READING LINE IN FILE ");
                    break;
                }
            }
            reader.readLine();
            line = reader.readLine();
            levelInformation.add(new LevelDataStructure(dataMap, buildData));
        }
        return levelInformation;
    }
}


