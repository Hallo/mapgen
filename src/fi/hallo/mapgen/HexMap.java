package fi.hallo.mapgen;

import java.math.BigDecimal;
import java.util.Random;

import fi.hallo.mapgen.Hex.Type;

public class HexMap {

    public enum Region {NORTH, RIVERLAND, CROWNLAND, VALE, WESTERLAND, IRON, DORNE, REACH, STORMLAND, FOO};
    private int height, width, total;
    private Hex[][] map;
    private static Region region;
    private int[] typePortion;

    public HexMap(int height, int width, Region region) {
        this.height = height;
        this.width = width;
        this.region = region;
        this.total = height*width;
        this.map = new Hex[height][width];

    }

    public void createMap() {
        typePortion = hexTypePortion();
        divideIntoPortions(typePortion);

    }

    private void divideIntoPortions(int[] typePortion) {
        Type value;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                do {
                    value = getWeightedRandomType(y, x);
                } while (typePortion[value.ordinal()] < 1);
                map[y][x] = new Hex(value);
                System.out.print(getRandomType(value));
                typePortion[value.ordinal()] -= 1;
            }
            System.out.println();
        }
    }

    private String getRandomType(Type value) {
        if (value.equals(Type.WATER)) {
            return "~";
        }
        return value.toString().substring(0, 1);
    }

    private Type getWeightedRandomType(int y, int x) {
        Random rand = new Random();

        int lengthMountain = getLengthOfType(Type.MOUNTAIN, 0, y, x);
        int lengthHill = getLengthOfType(Type.HILL, 0, y, x);
        int lengthForest = getLengthOfType(Type.FOREST, 0, y, x);
        int lengthWater = getLengthOfType(Type.WATER, 0, y, x);
        int lengthWasteland = getLengthOfType(Type.WASTELAND, 0, y, x);
        int lengthPlain = getLengthOfType(Type.PLAIN, 0, y, x);
        int lengthGrassland = getLengthOfType(Type.GRASSLAND, 0, y, x);

        int weightRandomizer = rand.nextInt(7);
        boolean notFirst = false;
        for (int i = weightRandomizer; i < 7; i++) {
            if (notFirst && i == weightRandomizer) {
                break;
            }
            switch (i) {
                case 0:
                    if (lengthForest > 0 && typePortion[Type.FOREST.ordinal()] > 0) {
                        double weight = 1.2 - (lengthForest * 0.1);
                        if (rand.nextDouble() < weight) {
                            return Type.FOREST;
                        }
                    }
                    break;
                case 1:
                    if (lengthMountain > 0 && typePortion[Type.MOUNTAIN.ordinal()] > 0) {
                        double weight = 1.2 - (lengthMountain * 0.1);
                        if (rand.nextDouble() < weight) {
                            return Type.MOUNTAIN;
                        }
                    }
                    break;
                case 2:
                    if (lengthWater > 0 && typePortion[Type.WATER.ordinal()] > 0) {
                        double weight = 1.2 - (lengthWater * 0.1);
                        if (rand.nextDouble() < weight) {
                            return Type.WATER;
                        }
                    }
                    break;
                case 3:
                    if (lengthWasteland > 0 && typePortion[Type.WASTELAND.ordinal()] > 0) {
                        double weight = 1.2 - (lengthWasteland * 0.1);
                        if (rand.nextDouble() < weight) {
                            return Type.WASTELAND;
                        }
                    }
                    break;
                case 4:
                    if (lengthHill > 0 && typePortion[Type.HILL.ordinal()] > 0) {
                        double weight = 1.2 - (lengthHill * 0.1);
                        if (rand.nextDouble() < weight) {
                            return Type.HILL;
                        }
                    }
                    break;
                case 5:
                    if (lengthPlain > 0 && typePortion[Type.PLAIN.ordinal()] > 0) {
                        double weight = 1.2 - (lengthPlain * 0.1);
                        if (rand.nextDouble() < weight) {
                            return Type.PLAIN;
                        }
                    }
                    break;
                case 6:
                    if (lengthGrassland > 0 && typePortion[Type.GRASSLAND.ordinal()] > 0) {
                        double weight = 1.2 - (lengthGrassland * 0.1);
                        if (rand.nextDouble() < weight) {
                            return Type.GRASSLAND;
                        }
                    }
                    break;
            }
            if (i == 6) {
                i = -1;
            }
            notFirst = true;
        }

        return Type.values()[rand.nextInt(7)];
    }

    private int getLengthOfType(Type type, int i, int y, int x) {

        if (y > 0 && map[y-1][x] != null && map[y-1][x].getType().equals(type)) {
            i = getLengthOfType(type, i, y-1, x) + 1;
        } else if (y > 0 && x < width-1 && map[y-1][x+1] != null && map[y-1][x+1].getType().equals(type)) {
            i = getLengthOfType(type, i, y-1, x+1) + 1;
        } else if (x > 0 && map[y][x-1] != null && map[y][x-1].getType().equals(type)) {
            i = getLengthOfType(type, i, y, x-1) + 1;
        }

        return i;
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
                        new BigDecimal(total*0.2).setScale(0, BigDecimal.ROUND_HALF_UP).intValue(),
                        new BigDecimal(total*0.1).setScale(0, BigDecimal.ROUND_HALF_UP).intValue(),
                        new BigDecimal(total*0.1).setScale(0, BigDecimal.ROUND_HALF_UP).intValue(),
                        new BigDecimal(total*0.1).setScale(0, BigDecimal.ROUND_HALF_UP).intValue(),
                        new BigDecimal(total*0.1).setScale(0, BigDecimal.ROUND_HALF_UP).intValue(),
                        new BigDecimal(total*0.25).setScale(0, BigDecimal.ROUND_HALF_UP).intValue()};
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
        return new int[] {new BigDecimal(total*0.1440).setScale(0, BigDecimal.ROUND_HALF_UP).intValue(),
                new BigDecimal(total*0.1440).setScale(0, BigDecimal.ROUND_HALF_UP).intValue(),
                new BigDecimal(total*0.1440).setScale(0, BigDecimal.ROUND_HALF_UP).intValue(),
                new BigDecimal(total*0.1440).setScale(0, BigDecimal.ROUND_HALF_UP).intValue(),
                new BigDecimal(total*0.1440).setScale(0, BigDecimal.ROUND_HALF_UP).intValue(),
                new BigDecimal(total*0.1440).setScale(0, BigDecimal.ROUND_HALF_UP).intValue(),
                new BigDecimal(total*0.1440).setScale(0, BigDecimal.ROUND_HALF_UP).intValue()};
    }

    public Hex[][] getMap() {
        return map;
    }

    public static Region getRegion() {
        return region;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

}
