package graze.actor.imp

import graze.actor.*
import graze.pasture.Pasture

class SoCow extends Cow {

    String getIcon() {
        return 'B'
    }

    Move move(Pasture surroundings) {
        return Move.STAND
    }

    Action act(Pasture surroundings) {
        return Action.PASS
    }
}
