package graze.actor

import graze.engine.Color

class Grass extends Actor {
    String getIcon() {
        return Color.green('.')
    }

    Move move(def surroundings) {
        return Move.STAND
    }

    Action act(def surroundings) {
        return Action.PASS
    }
}
