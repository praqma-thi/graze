package graze.behaviour.imp

import org.junit.Test
import org.junit.Before

import graze.actor.*
import graze.actor.imp.*
import graze.pasture.*

class CowBehaviourTest {
    Pasture pasture
    ArrayList<Actor> actors
    CowBehaviour cowBehaviour = new CowBehaviour()
    PastureGenerator generator = new PastureGenerator().actors(WeightedMap.from([null: 1])).width(5).height(5)

    @Before
    void setup() {
        pasture = generator.generate()
        def actor = new RCow()
        pasture.getTile(2, 2).actors.add(actor)
        actors = [actor]
    }

    @Test
    void actor_poops() {
        actors.each { actor ->
            actor.poop = 5
            cowBehaviour.act(actor, pasture, Action.POOP)
            assert actor.poop == 0
        }
    }

    @Test
    void actor_eats_grass() {
        pasture.getTile(2, 2).actors.add(new Grass())
        actors.each { actor ->
            actor.food = 0
            actor.poop = 0
            cowBehaviour.act(actor, pasture, Action.EAT)
            assert actor.food == 1
            assert actor.poop == 1
            assert !pasture.getTile(2, 2).actors.any { it instanceof Grass }
        }
    }

    @Test
    void actor_doesnt_eat_grass_when_sharing_tile_with_other_actors() {
        pasture.getTile(2, 2).actors.add(new Grass())
        pasture.getTile(2, 2).actors.add(new RCow())
        actors.each { actor ->
            actor.food = 0
            actor.poop = 0
            cowBehaviour.act(actor, pasture, Action.EAT)
            assert actor.food == 0
            assert actor.poop == 0
            assert pasture.getTile(2, 2).actors.any { it instanceof Grass }
        }
    }

    @Test
    void actor_doesnt_eat_without_grass() {
        actors.each { actor ->
            actor.food = 0
            actor.poop = 0
            cowBehaviour.act(actor, pasture, Action.EAT)
            assert actor.food == 0
            assert actor.poop == 0
        }
    }

    @Test
    void actor_doesnt_escape() {
        actors.each { actor ->
            10.times {
                cowBehaviour.move(actor, pasture, Move.MOVE_LEFT)
            }
            def c = pasture.coordinatesOf(actor)
            assert c.x == 0
        }
    }

    @Test
    void actor_passes() {
        actors.each { actor ->
            cowBehaviour.act(actor, pasture, Action.PASS)
        }
    }

    @Test
    void actor_stands() {
        actors.each { actor ->
            def was = pasture.coordinatesOf(actor)
            cowBehaviour.move(actor, pasture, Move.STAND)
            def is = pasture.coordinatesOf(actor)
            assert was == is
        }
    }

    @Test
    void actor_moves_left() {
        actors.each { actor ->
            cowBehaviour.move(actor, pasture, Move.MOVE_LEFT)
            def c = pasture.coordinatesOf(actor)
            assert c.x == 1
            assert c.y == 2
        }
    }

    @Test
    void actor_moves_right() {
        actors.each { actor ->
            cowBehaviour.move(actor, pasture, Move.MOVE_RIGHT)
            def c = pasture.coordinatesOf(actor)
            assert c.x == 3
            assert c.y == 2
        }
    }

    @Test
    void actor_moves_up() {
        actors.each { actor ->
            cowBehaviour.move(actor, pasture, Move.MOVE_UP)
            def c = pasture.coordinatesOf(actor)
            assert c.x == 2
            assert c.y == 1
        }
    }

    @Test
    void actor_moves_down() {
        actors.each { actor ->
            cowBehaviour.move(actor, pasture, Move.MOVE_DOWN)
            def c = pasture.coordinatesOf(actor)
            assert c.x == 2
            assert c.y == 3
        }
    }
}
