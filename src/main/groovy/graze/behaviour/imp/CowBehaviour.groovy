package graze.behaviour.imp

import graze.actor.*
import graze.actor.imp.*
import graze.behaviour.ActorBehaviour
import graze.pasture.*
import graze.engine.Canvas

class CowBehaviour extends ActorBehaviour {
    void eat(Actor actor, Tile tile) {
        def grass = tile.actors.find { it instanceof Grass }
        if (!grass) {
            Canvas.instance.log "[${actor.tag} eat] No grass. Sad."
            return
        }

        if (tile.actors.grep { !(it instanceof Grass) }.size() > 1) {
            Canvas.instance.log "[${actor.tag} eat] I can't eat while there's someone looking."
            return
        }

        tile.actors.remove(grass)
        Canvas.instance.log "[${actor.tag} eat] Delicious!"
        actor.food += 1
        actor.poop += 1
    }
}
