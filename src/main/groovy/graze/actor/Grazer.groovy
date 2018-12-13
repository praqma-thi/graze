package graze.actor

import graze.actor.*
import graze.actor.imp.*
import graze.pasture.*
import graze.engine.Canvas

abstract class Grazer extends Actor {
    void eat(Tile tile) {
        def grass = tile.actors.find { it instanceof Grass }
        if (!grass) {
            Canvas.instance.log "[${tag} eat] No grass. Sad."
            return
        }

        if (tile.actors.grep { !(it instanceof Grass) }.size() > 1) {
            Canvas.instance.log "[${tag} eat] I can't eat while there's someone looking."
            return
        }

        tile.actors.remove(grass)
        Canvas.instance.log "[${tag} eat] Delicious!"
        food += 1
        poop += 1
    }
}
