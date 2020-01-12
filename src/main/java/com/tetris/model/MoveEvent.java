package com.tetris.model;


public class MoveEvent {
    private final MoveEventType type;

    public MoveEvent(MoveEventType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return  type.toString();
    }

    public MoveEventType getType() {
        return type;
    }

    public enum MoveEventType {
        MOVE_RIGHT,
        MOVE_LEFT,
        MOVE_DOWN,
        LEFT_ROTATE,
        RIGHT_ROTATE
    }
}
