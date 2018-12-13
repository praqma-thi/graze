package graze.actor

import graze.pasture.*
import graze.engine.*

@groovy.transform.EqualsAndHashCode
abstract class Actor {
    final String id = UUID.randomUUID().toString()
    final String tag = "${this.class.simpleName}:${this.id.substring(0, 5)}"

    int food = 3
    int poop = 0

    abstract String getIcon()
    abstract Action act(Pasture surroundings)
    abstract Move move(Pasture surroundings)
    abstract void eat(Tile tile)

    void move(Pasture pasture, Move moveValue) {
        switch(moveValue) {
            case Move.STAND:
                stand()
                break
            case Move.MOVE_UP:
                move(pasture.tileOf(this), pasture.above(this))
                break
            case Move.MOVE_DOWN:
                move(pasture.tileOf(this), pasture.below(this))
                break
            case Move.MOVE_LEFT:
                move(pasture.tileOf(this), pasture.leftOf(this))
                break
            case Move.MOVE_RIGHT:
                move(pasture.tileOf(this), pasture.rightOf(this))
                break
            default:
                throw new Exception("[${tag} move] Unexpected move: ${moveValue}.");
        }
    }

    void stand() {
        Canvas.instance.log "[${tag} stand] Biding my time."
    }

    void move(Tile from, Tile to) {
        if(!to) {
            Canvas.instance.log "[${tag} move] I don't want to fall off."
            return
        }

        Canvas.instance.log "[${tag} move] Time to hustle."
        from.actors.remove(this)
        to.actors.add(this)
    }

    void act(Pasture pasture, Action actionValue) {
        switch(actionValue) {
            case Action.EAT:
                eat(pasture.tileOf(this))
                break
            case Action.POOP:
                poop()
                break
            case Action.PASS:
                pass()
                break
            default:
                throw new Exception("[${tag} act] Unexpected action: ${actionValue}.")
        }
    }

    void pass() {
        Canvas.instance.log "[${tag} pass] The only way to win is not to play."
    }

    void poop() {
        if (poop < 1) {
            Canvas.instance.log "[${tag} poop] *toot*"
            return
        }

        Canvas.instance.log "[${tag} poop] Pffrttt..."
        poop = 0
    }

    void getHungry(Pasture pasture) {
        food--
        if (food >= 0) { return }

        Canvas.instance.log "[${tag} exit] I'm out!"
        pasture.tileOf(this).actors.remove(this)
    }
}
