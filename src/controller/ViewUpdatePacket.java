package controller;

import java.util.ArrayList;

public record ViewUpdatePacket(ArrayList<TileUpdate> tileUpdates) {

    public void addTileUpdate(TileUpdate tileUpdate) {
        tileUpdates.add(tileUpdate);
    }
}
