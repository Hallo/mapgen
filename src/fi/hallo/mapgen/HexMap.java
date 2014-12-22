package fi.hallo.mapgen;

public class HexMap {

    public enum Region {NORTH, RIVERLAND, CROWNLAND, VALE, WESTERLAND, IRON, DORNE, REACH, STORMLAND};
    private int height, width;
    private Hex[][] map;
    private Region region;

    public HexMap(int height, int width, Region region) {
        this.height = height;
        this.width = width;
        this.region = region;
        this.map = new Hex[height][width];

    }

    public void createMap() {
        int[] typePortion = hexTypePortion();

    }


    private int[] hexTypePortion() {
        switch (region) {
            case NORTH:
                return new int[8];
            case RIVERLAND:
                return new int[8];
            case CROWNLAND:
                return new int[8];
            case VALE:
                return new int[8];
            case WESTERLAND:
                return new int[8];
            case IRON:
                return new int[8];
            case DORNE:
                return new int[8];
            case REACH:
                return new int[8];
            case STORMLAND:
                return new int[8];
        }
        return new int[] {12, 12, 12, 12, 12 ,12, 12, 12};
    }


}
