package com.tetris.game.handler;


public enum  MoveEvent {
        MOVE_RIGHT ("MOVE_RIGHT"),
        MOVE_LEFT("MOVE_LEFT"),
        MOVE_DOWN("MOVE_DOWN"),
        LEFT_ROTATE("LEFT_ROTATE"),
        RIGHT_ROTATE("RIGHT_ROTATE");
        String move;
        MoveEvent(String move){
                this.move = move;
        }

}
