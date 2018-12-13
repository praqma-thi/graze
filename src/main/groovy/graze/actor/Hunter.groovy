package graze.actor

import graze.actor.*
import graze.actor.imp.*
import graze.pasture.*
import graze.engine.Canvas

abstract class Hunter extends Actor {
    void eat(Tile tile) {
        List<Actor> cows = cowsOn(tile)

        if (cows.size() == 0) {
            return // Sad wolf :(
        }

        if (cows.size() == 1) {
            Actor firstCow = cows.first()
            scare(firstCow, tile)
        } else {
            Actor randomCow = cows[new Random().nextInt(cows.size())]
            scare(randomCow, tile)
        } 

        eatTrailMix()
    }

    List<Actor> cowsOn(Tile tile) {
        tile.actors.grep { actor ->
            !(actor instanceof Grass) && !(actor instanceof Wolf)
         }
    }

    void scare(Actor cow, Tile tile) {
        tile.actors.remove(cow) // Cow escapes
    }

    void eatTrailMix(Actor wolf) {
        food += 5
    }
}
