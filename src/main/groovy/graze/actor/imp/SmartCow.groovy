package graze.actor.imp

import graze.actor.*

class SmartCow extends Cow {
    Random brain = new Random()

    String getIcon() {
        return 'S'
    }

    Move move(def surroundings) {
        return Move.values()[brain.nextInt(Move.values().length)]
    }

    Action act(def surroundings) {
        if (surroundings[1][1].actors.any { it instanceof Grass })
            return Action.EAT
        return Action.POOP
    }
}
