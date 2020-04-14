package com.chess.engine.board;

public class BoardUtils {

    public static final boolean[] FIRST_COLUMN = initColumn(0);
    public static final boolean[] SECOND_COLUMN = initColumn(1);
    public static final boolean[] SEVEN_COLUMN = initColumn(6);
    public static final boolean[] EIGTH_COLUMN = initColumn(7);

    public static final boolean[] SECOND_ROW = null;

    public static final boolean[] SEVENTH_ROW = null;

    public static final  int NUM_TILES = 64;
    public static final  int NUM_TILES_ROW = 8;

    private BoardUtils() {
        throw new RuntimeException("You cannot instantiate me");
    }

    private static boolean[] initColumn(int columnNumber) {
        final boolean[] column = new boolean[NUM_TILES];

        do {
            column[columnNumber] = true;
            columnNumber +=NUM_TILES_ROW;
        } while (columnNumber<NUM_TILES);
        return column
    }

    public static boolean isValidTileCoordinate(final int coordinate) {
        return coordinate >=0 && coordinate<=64;
    }


}
