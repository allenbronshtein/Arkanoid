import java.awt.Color;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Blocks from symbols factory.
 */
//Allen Bronshtein
//206228751
public class BlocksFromSymbolsFactory {
    private BlocksDefinitionReader blocksDefinitionReader;

    /**
     * Load.
     *
     * @param fileName the file name
     * @throws IOException the io exception
     */
    public void load(String fileName) throws IOException {
        blocksDefinitionReader = new BlocksDefinitionReader();
        blocksDefinitionReader.load("data/" + fileName);
        blocksDefinitionReader.run();
    }

    /**
     * Is space symbol boolean.
     *
     * @param s the s
     * @return the boolean
     */
    public boolean isSpaceSymbol(String s) {
        return blocksDefinitionReader.getMapSpacers().containsKey(s);
    }

    /**
     * Is block symbol boolean.
     *
     * @param s the s
     * @return the boolean
     */
    public boolean isBlockSymbol(String s) {
        return blocksDefinitionReader.getMapBlock().containsKey(s);
    }

    /**
     * Gets block.
     *
     * @param s    the s
     * @param xpos the xpos
     * @param ypos the ypos
     * @return the block
     */
    public Block getBlock(String s, int xpos, int ypos) {
        int width = 0, height = 0;
        Block block;
        if (blocksDefinitionReader.getMapBlock().get(s).containsKey("width")) {
            width = Integer.parseInt(blocksDefinitionReader.getMapBlock().get(s).get("width"));
        } else if (blocksDefinitionReader.getMapDefault().containsKey("width")) {
            width = Integer.parseInt(blocksDefinitionReader.getMapDefault().get("width"));
        }
        if (blocksDefinitionReader.getMapBlock().get(s).containsKey("height")) {
            height = Integer.parseInt(blocksDefinitionReader.getMapBlock().get(s).get("height"));
        } else if (blocksDefinitionReader.getMapDefault().containsKey("height")) {
            height = Integer.parseInt(blocksDefinitionReader.getMapDefault().get("height"));
        }
        block = new Block(new Rectangle(new Point(xpos, ypos), width, height));
        return block;
    }

    /**
     * Gets space width.
     *
     * @param s the s
     * @return the space width
     */
    public int getSpaceWidth(String s) {
        return Integer.parseInt(blocksDefinitionReader.getMapSpacers().get(s));
    }

    /**
     * Color from string map.
     *
     * @param s the s
     * @return the map
     */
    public Map<String, Color> colorFromString(String s) {
        Map<String, Color> map = new HashMap<>();
        boolean bright = false;
        Color c = null;
        if (blocksDefinitionReader.getMapDefault().containsKey("stroke")) {
            if (blocksDefinitionReader.getMapDefault().get("stroke").startsWith("color")) {
                String colorString = blocksDefinitionReader.getMapDefault().get("stroke");
                String color = colorString.substring(6, colorString.length() - 1);
                if (color.startsWith("lighter")) {
                    color = color.substring(7);
                    bright = true;
                }
                try {
                    c = (Color) Color.class.getField(color.toUpperCase()).get(null);
                    if (bright) {
                        c = c.brighter();
                    }
                    bright = false;
                } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException e) {
                    e.printStackTrace();
                }
                map.put("stroke", c);
            }
        }
        for (Map.Entry<String, String> entry : blocksDefinitionReader.getMapBlock().get(s).entrySet()) {
            if (entry.getKey().equals("fill")) {
                String colorString = blocksDefinitionReader.getMapBlock().get(s).get("fill");
                if (colorString.contains("color(")) {
                    String color = colorString.substring(6, colorString.length() - 1);
                    if (color.startsWith("lighter")) {
                        color = color.substring(7);
                        bright = true;
                    }
                    try {
                        c = (Color) Color.class.getField(color.toUpperCase()).get(null);
                        if (bright) {
                            c = c.brighter();
                        }
                        bright = false;
                    } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException e) {
                        e.printStackTrace();
                    }
                    map.put("fill", c);
                }
            }
            if (entry.getKey().equals("fill-1")) {
                String colorString = blocksDefinitionReader.getMapBlock().get(s).get("fill-1");
                if (colorString.contains("color(")) {
                    String color = colorString.substring(6, colorString.length() - 1);
                    if (color.startsWith("lighter")) {
                        color = color.substring(7);
                        bright = true;
                    }
                    try {
                        c = (Color) Color.class.getField(color.toUpperCase()).get(null);
                        if (bright) {
                            c = c.brighter();
                        }
                        bright = false;
                    } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException e) {
                        e.printStackTrace();
                    }
                    map.put("fill", c);
                }
            }
            if (entry.getKey().equals("fill-2")) {
                String colorString = blocksDefinitionReader.getMapBlock().get(s).get("fill-2");
                if (colorString.contains("color(")) {
                    String color = colorString.substring(6, colorString.length() - 1);
                    if (color.startsWith("lighter")) {
                        color = color.substring(7);
                        bright = true;
                    }
                    try {
                        c = (Color) Color.class.getField(color.toUpperCase()).get(null);
                        if (bright) {
                            c = c.brighter();
                        }
                        bright = false;
                    } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException e) {
                        e.printStackTrace();
                    }
                    map.put("fill-2", c);
                }
            }
            if (entry.getKey().equals("fill-3")) {
                String colorString = blocksDefinitionReader.getMapBlock().get(s).get("fill-3");
                if (colorString.contains("color(")) {
                    String color = colorString.substring(6, colorString.length() - 1);
                    if (color.startsWith("lighter")) {
                        color = color.substring(7);
                        bright = true;
                    }
                    try {
                        c = (Color) Color.class.getField(color.toUpperCase()).get(null);
                        if (bright) {
                            c = c.brighter();
                        }
                        bright = false;
                    } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException e) {
                        e.printStackTrace();
                    }
                    map.put("fill-3", c);
                }
            }
        }
        return map;
    }

    /**
     * Gets blocks definition reader.
     *
     * @return the blocks definition reader
     */
    public BlocksDefinitionReader getBlocksDefinitionReader() {
        return blocksDefinitionReader;
    }
}
