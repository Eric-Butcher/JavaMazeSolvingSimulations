package controller;

public record TileUpdate(int xPos, int yPos, boolean hasTopBorder, boolean hasRightBorder, boolean hasBottomBorder, boolean hasLeftBorder, boolean hasMouse, boolean initialized, boolean traversed, boolean highlighted) {
}
