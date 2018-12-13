package graze.actor

import graze.pasture.*
import graze.actor.*
import graze.actor.imp.*
import org.junit.*

class CowTest {
    Pasture pasture
    ArrayList<Actor> actors
    PastureGenerator generator = new PastureGenerator().actors(WeightedMap.from([null: 1])).width(5).height(5)

    @Test
    void equals_same_cow() {
        def cow = new RCow()
        assert cow == cow
    }

    @Test
    void equals_different_cow() {
        def cow1 = new RCow()
        def cow2 = new RCow()
        assert cow1 != cow2
    }

    @Test
    void equals_no_cow() {
        def cow = new RCow()
        assert cow != null
    }

    @Before
    void setup() {
        def actor = new RCow()
        pasture = generator.generate()
        pasture.getTile(2, 2).actors.add(actor)
        actors = [actor]
    }

    @Test
    void cow_poops() {
        actors.each { actor ->
            actor.poop = 5
            actor.act(pasture, Action.POOP)
            assert actor.poop == 0
        }
    }

    @Test
    void cow_eats_grass() {
        pasture.getTile(2, 2).actors.add(new Grass())
        actors.each { actor ->
            actor.food = 0
            actor.poop = 0
            actor.act(pasture, Action.EAT)
            assert actor.food == 1
            assert actor.poop == 1
            assert !pasture.getTile(2, 2).actors.any { it instanceof Grass }
        }
    }

    @Test
    void cow_eats_troll_grass() {
        pasture.getTile(2, 2).actors.add(new TrollGrass())
        actors.each { actor ->
            actor.food = 0
            actor.poop = 0
            actor.act(pasture, Action.EAT)
            assert actor.food == 1
            assert actor.poop == 1
            assert !pasture.getTile(2, 2).actors.any { it instanceof TrollGrass }
        }
    }

    @Test
    void cow_doesnt_eat_grass_when_sharing_tile_with_other_actors() {
        pasture.getTile(2, 2).actors.add(new Grass())
        pasture.getTile(2, 2).actors.add(new RCow())
        actors.each { actor ->
            actor.food = 0
            actor.poop = 0
            actor.act(pasture, Action.EAT)
            assert actor.food == 0
            assert actor.poop == 0
            assert pasture.getTile(2, 2).actors.any { it instanceof Grass }
        }
    }

    @Test
    void cow_doesnt_eat_without_grass() {
        actors.each { actor ->
            actor.food = 0
            actor.poop = 0
            actor.act(pasture, Action.EAT)
            assert actor.food == 0
            assert actor.poop == 0
        }
    }

    @Test
    void cow_doesnt_escape() {
        actors.each { actor ->
            10.times {
                actor.move(pasture, Move.MOVE_LEFT)
            }
            def c = pasture.coordinatesOf(actor)
            assert c.x == 0
        }
    }

    @Test
    void cow_passes() {
        actors.each { actor ->
            actor.act(pasture, Action.PASS)
        }
    }

    @Test
    void cow_stands() {
        actors.each { actor ->
            def was = pasture.coordinatesOf(actor)
            actor.move(pasture, Move.STAND)
            def is = pasture.coordinatesOf(actor)
            assert was == is
        }
    }

    @Test
    void cow_moves_left() {
        actors.each { actor ->
            actor.move(pasture, Move.MOVE_LEFT)
            def c = pasture.coordinatesOf(actor)
            assert c.x == 1
            assert c.y == 2
        }
    }

    @Test
    void cow_moves_right() {
        actors.each { actor ->
            actor.move(pasture, Move.MOVE_RIGHT)
            def c = pasture.coordinatesOf(actor)
            assert c.x == 3
            assert c.y == 2
        }
    }

    @Test
    void cow_moves_up() {
        actors.each { actor ->
            actor.move(pasture, Move.MOVE_UP)
            def c = pasture.coordinatesOf(actor)
            assert c.x == 2
            assert c.y == 1
        }
    }

    @Test
    void cow_moves_down() {
        actors.each { actor ->
            actor.move(pasture, Move.MOVE_DOWN)
            def c = pasture.coordinatesOf(actor)
            assert c.x == 2
            assert c.y == 3
        }
    }
}
