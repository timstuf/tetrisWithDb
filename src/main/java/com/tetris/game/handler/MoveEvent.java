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
        public static MoveEvent get(String move){
                for(MoveEvent e : MoveEvent.values()){
                        if(move.equals(e.move)) return e;
                }
                return null;
        }

}
