package controller;

import java.util.LinkedList;

public record ViewUpdatePacket(LinkedList<TileUpdate> tileUpdates) {

    public void addTileUpdate(TileUpdate tileUpdate){
        tileUpdates.add(tileUpdate);
    }
}
