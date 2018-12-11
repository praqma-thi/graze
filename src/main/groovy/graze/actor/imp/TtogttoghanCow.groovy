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
        def movelist = [
            Move.MOVE_UP,
            Move.MOVE_DOWN,
            Move.MOVE_LEFT,
            Move.MOVE_RIGHT,
        ]
        def chosen = brain.nextInt(movelist.size())
        return movelist[chosen]
    }

    Action act(Pasture surroundings) {
        return Action.EAT
    }
}
