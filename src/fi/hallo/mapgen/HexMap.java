package fi.hallo.mapgen;

import java.math.BigDecimal;
import java.util.Random;

import fi.hallo.mapgen.Hex.Type;

public class HexMap {

    public enum Region {NORTH, RIVERLAND, CROWNLAND, VALE, WESTERLAND, IRON, DORNE, REACH, STORMLAND};
    private int height, width, total;
    private Hex[][] map;
    private Region region;

    public HexMap(int height, int width, Region region) {
        this.height = height;
        this.width = width;
        this.region = region;
        this.total = height*width;
        this.map = new Hex[height][width];

    }

    public void createMap() {
        int[] typePortion = hexTypePortion();
        divideIntoPortions(typePortion);

    }

    private void divideIntoPortions(int[] typePortion) {
        Random rand = new Random();
        int i = 0;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                do {
                    i = rand.nextInt(7);
                } while (typePortion[i] < 1);
                System.out.print(getRandomType(i));
                typePortion[i] -= 1;
            }
            System.out.println();
        }
    }

    private String getRandomType(int i) {
        if (Type.values()[i].equals(Type.WATER)) {
            return "~";
        }
        return Type.values()[i].toString().substring(0, 1);
    }


    private int[] hexTypePortion() {
        //GRASSLAND, HILL, FOREST, MOUNTAIN, WASTELAND, WATER, PLAIN
        switch (region) {
            case NORTH:
                return new int[]{new BigDecimal(total*0.05).setScale(0, BigDecimal.ROUND_HALF_UP).intValue(),
                        new BigDecimal(total*0.2).setScale(0, BigDecimal.ROUND_HALF_UP).intValue(),
                        new BigDecimal(total*0.15).setScale(0, BigDecimal.ROUND_HALF_UP).intValue(),
                        new BigDecimal(total*0.05).setScale(0, BigDecimal.ROUND_HALF_UP).intValue(),
                        new BigDecimal(total*0.35).setScale(0, BigDecimal.ROUND_HALF_UP).intValue(),
                        new BigDecimal(total*0.05).setScale(0, BigDecimal.ROUND_HALF_UP).intValue(),
                        new BigDecimal(total*0.15).setScale(0, BigDecimal.ROUND_HALF_UP).intValue()};
            case RIVERLAND:
                return new int[]{new BigDecimal(total*0.2).setScale(0, BigDecimal.ROUND_HALF_UP).intValue(),
                        new BigDecimal(total*0.2).setScale(0, BigDecimal.ROUND_HALF_UP).intValue(),
                        new BigDecimal(total*0.3).setScale(0, BigDecimal.ROUND_HALF_UP).intValue(),
                        new BigDecimal(total*0.05).setScale(0, BigDecimal.ROUND_HALF_UP).intValue(),
                        new BigDecimal(total*0.1).setScale(0, BigDecimal.ROUND_HALF_UP).intValue(),
                        new BigDecimal(total*0.05).setScale(0, BigDecimal.ROUND_HALF_UP).intValue(),
                        new BigDecimal(total*0.1).setScale(0, BigDecimal.ROUND_HALF_UP).intValue()};
            case CROWNLAND:
                return new int[]{new BigDecimal(total*0.1).setScale(0, BigDecimal.ROUND_HALF_UP).intValue(),
                        new BigDecimal(total*0.1).setScale(0, BigDecimal.ROUND_HALF_UP).intValue(),
                        new BigDecimal(total*0.3).setScale(0, BigDecimal.ROUND_HALF_UP).intValue(),
                        0,
                        new BigDecimal(total*0.2).setScale(0, BigDecimal.ROUND_HALF_UP).intValue(),
                        new BigDecimal(total*0.1).setScale(0, BigDecimal.ROUND_HALF_UP).intValue(),
                        new BigDecimal(total*0.2).setScale(0, BigDecimal.ROUND_HALF_UP).intValue()};
            case VALE:
                return new int[]{new BigDecimal(total*0.1).setScale(0, BigDecimal.ROUND_HALF_UP).intValue(),
                        new BigDecimal(total*0.2).setScale(0, BigDecimal.ROUND_HALF_UP).intValue(),
                        new BigDecimal(total*0.1).setScale(0, BigDecimal.ROUND_HALF_UP).intValue(),
                        new BigDecimal(total*0.3).setScale(0, BigDecimal.ROUND_HALF_UP).intValue(),
                        new BigDecimal(total*0.1).setScale(0, BigDecimal.ROUND_HALF_UP).intValue(),
                        new BigDecimal(total*0.1).setScale(0, BigDecimal.ROUND_HALF_UP).intValue(),
                        new BigDecimal(total*0.1).setScale(0, BigDecimal.ROUND_HALF_UP).intValue()};
            case WESTERLAND:
                return new int[]{new BigDecimal(total*0.2).setScale(0, BigDecimal.ROUND_HALF_UP).intValue(),
                        new BigDecimal(total*0.3).setScale(0, BigDecimal.ROUND_HALF_UP).intValue(),
                        new BigDecimal(total*0.1).setScale(0, BigDecimal.ROUND_HALF_UP).intValue(),
                        new BigDecimal(total*0.1).setScale(0, BigDecimal.ROUND_HALF_UP).intValue(),
                        new BigDecimal(total*0.05).setScale(0, BigDecimal.ROUND_HALF_UP).intValue(),
                        new BigDecimal(total*0.05).setScale(0, BigDecimal.ROUND_HALF_UP).intValue(),
                        new BigDecimal(total*0.1).setScale(0, BigDecimal.ROUND_HALF_UP).intValue()};
            case IRON:
                return new int[]{new BigDecimal(total*0.05).setScale(0, BigDecimal.ROUND_HALF_UP).intValue(),
                        new BigDecimal(total*0.05).setScale(0, BigDecimal.ROUND_HALF_UP).intValue(),
                        new BigDecimal(total*0.05).setScale(0, BigDecimal.ROUND_HALF_UP).intValue(),
                        new BigDecimal(total*0.05).setScale(0, BigDecimal.ROUND_HALF_UP).intValue(),
                        new BigDecimal(total*0.2).setScale(0, BigDecimal.ROUND_HALF_UP).intValue(),
                        new BigDecimal(total*0.3).setScale(0, BigDecimal.ROUND_HALF_UP).intValue(),
                        new BigDecimal(total*0.3).setScale(0, BigDecimal.ROUND_HALF_UP).intValue()};
            case DORNE:
                return new int[]{new BigDecimal(total*1).setScale(0, BigDecimal.ROUND_HALF_UP).intValue(),
                        new BigDecimal(total*0.1).setScale(0, BigDecimal.ROUND_HALF_UP).intValue(),
                        new BigDecimal(total*0.0).setScale(0, BigDecimal.ROUND_HALF_UP).intValue(),
                        new BigDecimal(total*0.0).setScale(0, BigDecimal.ROUND_HALF_UP).intValue(),
                        new BigDecimal(total*0.4).setScale(0, BigDecimal.ROUND_HALF_UP).intValue(),
                        new BigDecimal(total*0.1).setScale(0, BigDecimal.ROUND_HALF_UP).intValue(),
                        new BigDecimal(total*0.3).setScale(0, BigDecimal.ROUND_HALF_UP).intValue()};
            case REACH:
                return new int[]{new BigDecimal(total*0.2).setScale(0, BigDecimal.ROUND_HALF_UP).intValue(),
                        new BigDecimal(total*0.2).setScale(0, BigDecimal.ROUND_HALF_UP).intValue(),
                        new BigDecimal(total*0.15).setScale(0, BigDecimal.ROUND_HALF_UP).intValue(),
                        new BigDecimal(total*0.05).setScale(0, BigDecimal.ROUND_HALF_UP).intValue(),
                        new BigDecimal(total*0.1).setScale(0, BigDecimal.ROUND_HALF_UP).intValue(),
                        new BigDecimal(total*0.05).setScale(0, BigDecimal.ROUND_HALF_UP).intValue(),
                        new BigDecimal(total*0.25).setScale(0, BigDecimal.ROUND_HALF_UP).intValue()};
            case STORMLAND:
                return new int[]{new BigDecimal(total*0.2).setScale(0, BigDecimal.ROUND_HALF_UP).intValue(),
                        new BigDecimal(total*0.1).setScale(0, BigDecimal.ROUND_HALF_UP).intValue(),
                        new BigDecimal(total*0.2).setScale(0, BigDecimal.ROUND_HALF_UP).intValue(),
                        new BigDecimal(total*0.1).setScale(0, BigDecimal.ROUND_HALF_UP).intValue(),
                        new BigDecimal(total*0.1).setScale(0, BigDecimal.ROUND_HALF_UP).intValue(),
                        new BigDecimal(total*0.1).setScale(0, BigDecimal.ROUND_HALF_UP).intValue(),
                        new BigDecimal(total*0.2).setScale(0, BigDecimal.ROUND_HALF_UP).intValue()};
        }
        return new int[] {new BigDecimal(total*0.1428).setScale(0, BigDecimal.ROUND_HALF_UP).intValue(),
                new BigDecimal(total*0.1428).setScale(0, BigDecimal.ROUND_HALF_UP).intValue(),
                new BigDecimal(total*0.1428).setScale(0, BigDecimal.ROUND_HALF_UP).intValue(),
                new BigDecimal(total*0.1428).setScale(0, BigDecimal.ROUND_HALF_UP).intValue(),
                new BigDecimal(total*0.1430).setScale(0, BigDecimal.ROUND_HALF_UP).intValue(),
                new BigDecimal(total*0.1428).setScale(0, BigDecimal.ROUND_HALF_UP).intValue(),
                new BigDecimal(total*0.1430).setScale(0, BigDecimal.ROUND_HALF_UP).intValue()};
    }


}
