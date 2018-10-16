package graze.engine

import org.junit.Test
import org.junit.Before

import graze.actor.*
import graze.actor.imp.*
import graze.pasture.*

class CowBehaviourTest {
    Pasture pasture
    ArrayList<Cow> cows
    PastureGenerator generator = new PastureGenerator().actors(WeightedMap.from([null: 1])).width(5).height(5)

    @Before
    void setup() {
        pasture = generator.generate()
        def cow = new RCow()
        pasture.getTile(2, 2).actors.add(cow)
        cows = [cow]
    }

    @Test
    void cow_poops() {
        cows.each { cow ->
            cow.poop = 5
            CowBehaviour.act(cow, pasture, Action.POOP)
            assert cow.poop == 0
        }
    }

    @Test
    void cow_eats_grass() {
        pasture.getTile(2, 2).actors.add(new Grass())
        cows.each { cow ->
            CowBehaviour.act(cow, pasture, Action.EAT)
            assert cow.food == 1
            assert cow.poop == 1
            assert !pasture.getTile(2, 2).actors.any { it instanceof Grass }
        }
    }

    @Test
    void cow_doesnt_eat_without_grass() {
        cows.each { cow ->
            CowBehaviour.act(cow, pasture, Action.EAT)
            assert cow.food == 0
            assert cow.poop == 0
        }
    }

    @Test
    void cow_doesnt_escape() {
        cows.each { cow ->
            10.times {
                CowBehaviour.move(cow, pasture, Move.MOVE_LEFT)
            }
            def c = pasture.coordinatesOf(cow)
            assert c.x == 0
        }
    }

    @Test
    void cow_passes() {
        cows.each { cow ->
            CowBehaviour.act(cow, pasture, Action.PASS)
        }
    }

    @Test
    void cow_stands() {
        cows.each { cow ->
            def was = pasture.coordinatesOf(cow)
            CowBehaviour.move(cow, pasture, Move.STAND)
            def is = pasture.coordinatesOf(cow)
            assert was == is
        }
    }

    @Test
    void cow_moves_left() {
        cows.each { cow ->
            CowBehaviour.move(cow, pasture, Move.MOVE_LEFT)
            def c = pasture.coordinatesOf(cow)
            assert c.x == 1
            assert c.y == 2
        }
    }

    @Test
    void cow_moves_right() {
        cows.each { cow ->
            CowBehaviour.move(cow, pasture, Move.MOVE_RIGHT)
            def c = pasture.coordinatesOf(cow)
            assert c.x == 3
            assert c.y == 2
        }
    }

    @Test
    void cow_moves_up() {
        cows.each { cow ->
            CowBehaviour.move(cow, pasture, Move.MOVE_UP)
            def c = pasture.coordinatesOf(cow)
            assert c.x == 2
            assert c.y == 1
        }
    }

    @Test
    void cow_moves_down() {
        cows.each { cow ->
            CowBehaviour.move(cow, pasture, Move.MOVE_DOWN)
            def c = pasture.coordinatesOf(cow)
            assert c.x == 2
            assert c.y == 3
        }
    }
}
