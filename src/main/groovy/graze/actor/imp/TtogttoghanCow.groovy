package graze.actor.imp

import graze.actor.*
import graze.pasture.Pasture

/**
* author: Beatrice Pendleton
*/

class TtogttoghanCow extends Cow {
    Random brain = new Random()

    String getIcon() {
        return 'B'
    }

    Move move(Pasture surroundings) {
        return Move.values()[brain.nextInt(Move.values().length)]
    }

    Action act(Pasture surroundings) {
        return Action.EAT
    }
}
