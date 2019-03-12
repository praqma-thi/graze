package graze.actor.imp

import graze.actor.*
import graze.pasture.*

class TheBestCowOffalTime extends Grazer {
    Random brain = new Random()

    String getIcon() {
        return 'This_Is_By_Far_The_Longest_Possible_Name_A_Bovine_Can_Have_And_You_Are_Absolutely_Free_To_Try_And_Prove_Me_Wrong_If_You_Can, Period. PS. Do_You_Want_Some_Offal? PPS. Have_You_Heard_Of_Warhammer_40K?, It\'s_A_Game_With_Miniatures_But_There_Are_Books_About_It_Too,_It\'s_Sci-fi_With_Fantasy_Elements_And_I_Think_You_Should_try_It.'
    }

    int lastResortMoves = 0
    Move lastResort = Move.values()[brain.nextInt(Move.values().length)]

    Move move(Pasture surroundings) {
        // given surroundings:

        // 0,0  0,1  0,2
        // 1,0  cow  2,2
        // 2,0  2,1  2,2
        Tile above = surroundings.above(this)
        Tile below = surroundings.below(this)
        Tile left = surroundings.leftOf(this)
        Tile right = surroundings.rightOf(this)

        if (hasGrass(above)) return Move.MOVE_UP
        if (hasGrass(below)) return Move.MOVE_DOWN
        if (hasGrass(left)) return Move.MOVE_LEFT
        if (hasGrass(right)) return Move.MOVE_RIGHT

        // Move in a different direction every now and then
        if (lastResortMoves >= 11) {
            lastResort = Move.values()[brain.nextInt(Move.values().length)]
            lastResortMoves = 0
        }

        Tile target = null
        boolean looking = true
        while (looking) {
            if (lastResort == Move.MOVE_UP) target = above
            if (lastResort == Move.MOVE_DOWN) target = below
            if (lastResort == Move.MOVE_LEFT) target = left
            if (lastResort == Move.MOVE_RIGHT) target = right

            if (target) {
                looking = false
            } else {
                lastResort = Move.values()[brain.nextInt(Move.values().length)]
            }
        }

        lastResortMoves += 1
        return lastResort
    }

    Action act(Pasture surroundings) {
        return Action.EAT
    }

    boolean hasGrass(Tile tile) {
        tile && tile.actors.any { actor ->
            actor instanceof Grass
        }
    }
}
