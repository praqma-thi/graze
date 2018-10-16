package graze.engine

import graze.actor.*
import graze.pasture.*

class CowBehaviour {
    static void act(def cow, Pasture pasture, Action action) {
        switch(action) {
            case Action.EAT:
                eat(cow, pasture)
                break
            case Action.POOP:
                poop(cow)
                break
            case Action.STAND:
                stand(cow)
                break
            case Action.MOVE_LEFT:
                move(leftOf(cow), cow, pasture)
                break
            case Action.MOVE_RIGHT:
                move(rightOf(cow), cow, pasture)
                break
            case Action.MOVE_UP:
                move(above(cow), cow, pasture)
                break
            case Action.MOVE_DOWN:
                move(below(cow), cow, pasture)
                break
            default:
                throw new Exception("Cows can't ${action}.")
        }
    }

    static void eat(cow, pasture) {
        def tile = pasture.getTile(cow.value.x, cow.value.y)
        if (tile != Tile.GRASS) {
            println "[${cow.key.id}:eat] I can't eat ${tile}!"
            return
        }

        pasture.setTile(cow.value.x, cow.value.y, Tile.DIRT)
        println "[${cow.key.id}:eat] Delicious!"
        cow.value.food += 1
        cow.value.poop += 1
    }

    static void poop(cow) {
        println "[${cow.key.id}:poop] Pfffrffrfrrtrt..."
        cow.value.poop = 0
    }

    static void stand(cow) {
        println "[${cow.key.id}:stand] I shouldn't be wasting time."
    }

    static void move(coordinates, cow, pasture) {
        if (pasture.isOutOfBounds(coordinates.x, coordinates.y)) {
            println "[${cow.key.id}:move] I don't want to fall off."
            return
        }

        println "[${cow.key.id}:move] Time to hustle."
        cow.value.x = coordinates.x
        cow.value.y = coordinates.y
    }

    static leftOf(cow) {
        return [x: (cow.value.x - 1), y: cow.value.y]
    }

    static rightOf(cow) {
        return [x: (cow.value.x + 1), y: cow.value.y]
    }

    static above(cow) {
        return [x: (cow.value.x), y: cow.value.y - 1]
    }

    static below(cow) {
        return [x: (cow.value.x), y: cow.value.y + 1]
    }
}
