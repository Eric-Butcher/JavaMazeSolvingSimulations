package model.generators;

import controller.ViewUpdatePacket;

public class DummyGenerator extends Generator{

    public void iterate(){
        return;
    }

    public void finish(){
        return;
    }

    @Override
    public ViewUpdatePacket makeViewUpdatePacket() {
        return null;
    }
}
