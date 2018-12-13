package graze.actor.imp

import graze.actor.*
import graze.engine.Color
import graze.pasture.*

class SmartCow extends Grazer {
    Move lastMove

    String getIcon() {
        return Color.cyan('S')
    }

    Move move(Pasture surroundings) {
        // Look at nearby tiles, check which have grass
        def options = [
            (Move.STAND): hasGrass(surroundings.tileOf(this)),
            (Move.MOVE_LEFT): hasGrass(surroundings.leftOf(this)),
            (Move.MOVE_RIGHT): hasGrass(surroundings.rightOf(this)),
            (Move.MOVE_UP): hasGrass(surroundings.above(this)),
            (Move.MOVE_DOWN): hasGrass(surroundings.below(this)),
        ]

        // Pick a tile that has grass on it,
        // if none have grass, pick a random one
        def ideas = options.keySet() as List
        def goodIdeas = ideas.grep { options[it] } ?: ideas
        Collections.shuffle(goodIdeas)
        def currentMove = goodIdeas.first()
        
        // Avoid redundant moves
        if (isRedundant(currentMove) && goodIdeas.size() > 1) {
            currentMove = goodIdeas.drop(1).first()
        }

        // Remember our move
        def lastMove = currentMove
        return currentMove
    }

    Action act(Pasture surroundings) {
        if (hasGrass(surroundings.tileOf(this))) {
            return Action.EAT
        }
        return Action.POOP
    }

    boolean hasGrass(Tile tile) {
        if (tile == null) {
            return false
        }
        return tile.actors.any { it instanceof Grass }
    }

    boolean isRedundant(Move currentMove) {
        switch(currentMove) {
            case Move.STAND:
                return lastMove == Move.STAND
            case Move.MOVE_UP:
                return lastMove == Move.MOVE_DOWN
            case Move.MOVE_DOWN:
                return lastMove == Move.MOVE_UP
            case Move.MOVE_RIGHT:
                return lastMove == Move.MOVE_LEFT
            case Move.MOVE_LEFT:
                return lastMove == Move.MOVE_RIGHT
            default:
                return false
        }
    }
}
