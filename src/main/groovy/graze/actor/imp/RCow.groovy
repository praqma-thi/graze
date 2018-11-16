package graze.actor.imp

import graze.actor.*

class RCow extends Cow {
    def brain = new Random()

    Move move(def surroundings) {
        return Move.values()[brain.nextInt(Move.values().length)]
    }

    Action act(def surroundings) {
        return Action.values()[brain.nextInt(Action.values().length)]
    }
}
