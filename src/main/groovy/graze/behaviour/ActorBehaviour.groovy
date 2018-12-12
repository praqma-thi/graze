package graze.behaviour

import graze.actor.*
import graze.actor.imp.*
import graze.pasture.*
import graze.engine.Canvas

abstract class ActorBehaviour {
    abstract void eat(Actor actor, Tile tile)

    void move(Actor actor, Pasture pasture, Move moveValue) {
        switch(moveValue) {
            case Move.STAND:
                stand(actor)
                break
            case Move.MOVE_UP:
                move(actor, pasture.tileOf(actor), pasture.above(actor))
                break
            case Move.MOVE_DOWN:
                move(actor, pasture.tileOf(actor), pasture.below(actor))
                break
            case Move.MOVE_LEFT:
                move(actor, pasture.tileOf(actor), pasture.leftOf(actor))
                break
            case Move.MOVE_RIGHT:
                move(actor, pasture.tileOf(actor), pasture.rightOf(actor))
                break
            default:
                throw new Exception("[${actor.tag} move] Unexpected move: ${moveValue}.");
        }
    }

    void stand(Actor actor) {
        Canvas.instance.log "[${actor.tag} stand] Biding my time."
    }

    void move(Actor actor, Tile from, Tile to) {
        if(!to) {
            Canvas.instance.log "[${actor.tag} move] I don't want to fall off."
            return
        }

        Canvas.instance.log "[${actor.tag} move] Time to hustle."
        from.actors.remove(actor)
        to.actors.add(actor)
    }

    void act(Actor actor, Pasture pasture, Action actionValue) {
        switch(actionValue) {
            case Action.EAT:
                eat(actor, pasture.tileOf(actor))
                break
            case Action.POOP:
                poop(actor)
                break
            case Action.PASS:
                pass(actor)
                break
            default:
                throw new Exception("[${actor.tag} act] Unexpected action: ${actionValue}.")
        }
    }

    void pass(Actor actor) {
        Canvas.instance.log "[${actor.tag} pass] The only way to win is not to play."
    }

    void poop(Actor actor) {
        if (actor.poop < 1) {
            Canvas.instance.log "[${actor.tag} poop] *toot*"
            return
        }

        Canvas.instance.log "[${actor.tag} poop] Pffrttt..."
        actor.poop = 0
    }

    void getHungry(Actor actor, Pasture pasture) {
        actor.food--
        if (actor.food >= 0) { return }

        Canvas.instance.log "[${actor.tag} exit] I'm out!"
        pasture.tileOf(actor).actors.remove(actor)
    }
}
