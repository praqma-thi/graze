package graze.actor.imp

import graze.actor.*
import graze.pasture.*
import graze.engine.Color

class Wolf extends Hunter {
    Random brain = new Random()

    String getIcon() {
        return Color.red('W')
    }

    Move move(Pasture surroundings) {
        return Move.values()[brain.nextInt(Move.values().length)]
    }

    Action act(Pasture surroundings) {
        return Action.EAT
    }
}
