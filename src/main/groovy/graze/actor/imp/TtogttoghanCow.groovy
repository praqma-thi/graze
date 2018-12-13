package graze.actor.imp

import graze.actor.*
import graze.pasture.*

/**
* author: Beatrice Pendleton
*/
class TtogttoghanCow extends Grazer {
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
        // Only ever stand still once (on the first turn)
        if (hasGrass(surroundings.tileOf(this))) {
            return Move.STAND
        }

        // Start with a list of potential moves
        List<Move> potentialMoves = moves.keySet().toList()

        // Remove moves that would lead to non-existent tiles
        removeVoidMoves(potentialMoves, surroundings)

        // Remove redundant move from the pool of possible moves
        Move redundantMove = moves[lastMove]
        potentialMoves.remove(redundantMove)

        // Find which movements lead us to grass
        HashMap potentialTiles = [:]
        potentialMoves.each { move ->
            switch (move) {
                case Move.MOVE_UP:
                    potentialTiles[move] = hasGrass(surroundings.above(this))
                    break
                case Move.MOVE_DOWN:
                    potentialTiles[move] = hasGrass(surroundings.below(this))
                    break
                case Move.MOVE_LEFT:
                    potentialTiles[move] = hasGrass(surroundings.leftOf(this))
                    break
                case Move.MOVE_RIGHT:
                    potentialTiles[move] = hasGrass(surroundings.rightOf(this))
                    break
            }
        }

        Move chosenMove
        if (allOrNoneHaveGrass(potentialTiles)) {
            // Move entirely randomly
            int chosen = brain.nextInt(potentialMoves.size())
            chosenMove = potentialMoves[chosen]
        } else {
            // Pick a random move that leads to grass
            def movesWithGrass = potentialTiles.keySet().grep { potentialTiles[it] }
            int chosen = brain.nextInt(movesWithGrass.size())
            chosenMove = movesWithGrass[chosen]
        }

        // Remember our chosen move
        lastMove = chosenMove
        return chosenMove
    }

    Action act(Pasture surroundings) {
        return Action.EAT
    }

    boolean hasGrass(Tile tile) {
        return tile.actors.any { it instanceof Grass }
    }

    boolean allOrNoneHaveGrass(HashMap<Move, Boolean> potentialTiles) {
        return potentialTiles.values().toList().unique().size() == 1
    }

    void removeVoidMoves (List<Move> givenMoves, Pasture surroundings) {
        def badMoves = []
        givenMoves.each { move ->
            Tile targetTile

            switch (move) {
                case Move.MOVE_UP:
                    targetTile = surroundings.above(this)
                    break
                case Move.MOVE_DOWN:
                    targetTile = surroundings.below(this)
                    break
                case Move.MOVE_LEFT:
                    targetTile = surroundings.leftOf(this)
                    break
                case Move.MOVE_RIGHT:
                    targetTile = surroundings.rightOf(this)
                    break
            }

            if (targetTile == null) {
                badMoves.add(move)
            }
        }

        givenMoves.removeAll(badMoves)
    }
}
