package utilities;

import controller.ViewUpdatePacket;

import java.util.LinkedList;

public class Constants {
    public static final int minCellIndex = 0;
    public static final int maxCellIndex = 15;

    public static final int mazeLength = 16;

    public static final ViewUpdatePacket emptyViewUpdatePacket = new ViewUpdatePacket(new LinkedList<>());
    public static final String MY_STRING_CONSTANT = "Hello, World!";
    // Add more constants here as needed
}

