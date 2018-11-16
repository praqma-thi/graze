package graze.engine

import graze.actor.*
import graze.pasture.*
import graze.engine.Canvas

class CowBehaviour {
    static void move(Cow cow, Pasture pasture, Move moveValue) {
        switch(moveValue) {
            case Move.STAND:
                stand(cow)
                break
            case Move.MOVE_UP:
                move(cow, pasture.tileOf(cow), pasture.above(cow))
                break
            case Move.MOVE_DOWN:
                move(cow, pasture.tileOf(cow), pasture.below(cow))
                break
            case Move.MOVE_LEFT:
                move(cow, pasture.tileOf(cow), pasture.leftOf(cow))
                break
            case Move.MOVE_RIGHT:
                move(cow, pasture.tileOf(cow), pasture.rightOf(cow))
                break
            default:
                throw new Exception("[${cow.tag} move] Cows can't ${moveValue}.");
        }
    }

    static void act(Cow cow, Pasture pasture, Action actionValue) {
        switch(actionValue) {
            case Action.EAT:
                eat(cow, pasture.tileOf(cow))
                break
            case Action.POOP:
                poop(cow)
                break
            case Action.PASS:
                pass(cow)
                break
            default:
                throw new Exception("[${cow.tag} act] Cows can't ${actionValue}.")
        }
    }

    static void pass(Cow cow) {
        Canvas.instance.log "[${cow.tag} pass] The only way to win is not to play."
    }

    static void eat(Cow cow, Tile tile) {
        def grass = tile.actors.find { it instanceof Grass }
        if (!grass) {
            Canvas.instance.log "[${cow.tag} eat] No grass. Sad."
            return
        }

        tile.actors.remove(grass)
        Canvas.instance.log "[${cow.tag} eat] Delicious!"
        cow.food += 1
        cow.poop += 1
    }

    static void poop(Cow cow) {
        if (cow.poop < 1) {
            Canvas.instance.log "[${cow.tag} poop] *toot*"
            return
        }

        Canvas.instance.log "[${cow.tag} poop] Pffrttt..."
        cow.poop = 0
    }

    static void stand(Cow cow) {
        Canvas.instance.log "[${cow.tag} stand] Biding my time."
    }

    static void move(Cow cow, Tile from, Tile to) {
        if(!to) {
            Canvas.instance.log "[${cow.tag} move] I don't want to fall off."
            return
        }

        Canvas.instance.log "[${cow.tag} move] Time to hustle."
        from.actors.remove(cow)
        to.actors.add(cow)
    }
}
