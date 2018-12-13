package graze.actor.imp

import graze.actor.*
import graze.engine.Color
import graze.pasture.Pasture

class TrollGrass extends Grass {
    Move direction

    TrollGrass() {
        List<Move> directions = [Move.MOVE_DOWN, Move.MOVE_UP, Move.MOVE_LEFT, Move.MOVE_RIGHT]
        Collections.shuffle(directions)
        direction = directions.first()
    }

    String getIcon() {
        return Color.green('-')
    }

    Move move(Pasture surroundings) {
        if (direction == Move.MOVE_UP && surroundings.above(this) == null) {
            direction = Move.MOVE_RIGHT
        }

        if (direction == Move.MOVE_RIGHT && surroundings.rightOf(this) == null) {
            direction = Move.MOVE_DOWN
        }

        if (direction == Move.MOVE_DOWN && surroundings.below(this) == null) {
            direction = Move.MOVE_LEFT
        }

        if (direction == Move.MOVE_LEFT && surroundings.leftOf(this) == null) {
            direction = Move.MOVE_UP
        }

        return direction
    }

    Action act(Pasture surroundings) {
        return Action.PASS
    }
}
