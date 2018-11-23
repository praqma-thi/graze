package graze.actor.imp

import graze.actor.*

class RCow extends Cow {
    Random brain = new Random()

    String getIcon() {
        return 'R'
    }

    Move move(def surroundings) {
        return Move.values()[brain.nextInt(Move.values().length)]
    }

    Action act(def surroundings) {
        return Action.values()[brain.nextInt(Action.values().length)]
    }
}
