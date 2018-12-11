package graze.actor.imp

import graze.actor.*
import graze.pasture.Pasture

/**
* author: Beatrice Pendleton
*/
class TtogttoghanCow extends Cow {
    // The brain of the cow, used to make intelligent decisions
    final Random brain = new Random()

    // A map of moves and their opposites, used to avoid making dumb moves
    final HashMap<Move, Move> moves = [
        (Move.MOVE_UP): Move.MOVE_DOWN,
        (Move.MOVE_DOWN): Move.MOVE_UP,
        (Move.MOVE_LEFT): Move.MOVE_RIGHT,
        (Move.MOVE_RIGHT): Move.MOVE_LEFT,
    ]

    // The last move we took, used to avoid making dumb moves
    Move lastMove

    String getIcon() {
        return 'B'
    }

    Move move(Pasture surroundings) {
        // Remove redundant move from the pool of possible moves
        def redundantMove = moves[lastMove]
        def goodMoves = moves
        goodMoves.remove(redundantMove)

        // Pick a random move
        def chosen = brain.nextInt(goodMoves.size())
        def chosenMove = goodMoves.keySet()[chosen]

        // Remember our chosen move
        lastMove = chosenMove

        return chosenMove
    }

    Action act(Pasture surroundings) {
        return Action.EAT
    }
}
