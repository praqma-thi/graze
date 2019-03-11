package graze.actor.imp

import graze.actor.*
import graze.pasture.Pasture

class TheBestCowOffalTime extends Grazer {
    Random brain = new Random()

    String getIcon() {
        return 'OffalTime'
    }

    Move move(Pasture surroundings) {
        return Move.values()[brain.nextInt(Move.values().length)]
    }

    Action act(Pasture surroundings) {
        return Action.values()[brain.nextInt(Action.values().length)]
    }
}
