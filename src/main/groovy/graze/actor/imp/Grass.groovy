package graze.actor.imp

import graze.actor.*
import graze.engine.Color
import graze.pasture.Pasture

class Grass extends Plant {
    String getIcon() {
        return Color.green('.')
    }

    Move move(Pasture surroundings) {
        return Move.STAND
    }

    Action act(Pasture surroundings) {
        return Action.PASS
    }
}
