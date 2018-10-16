package graze.engine

import org.junit.Test
import org.junit.Before

import graze.pasture.*
import graze.actor.*
import graze.actor.imp.*

class CowBehaviourTest {
    Pasture pasture
    Map<Cow, Attributes> cows
    PastureGenerator generator = new PastureGenerator().width(5).height(5).border(Tile.FENCE)

    @Before
    void setup() {
        pasture = generator.generate()
        cows = [
            (new RCow()): new Attributes()
        ]
        cows.each { cow ->
            cow.value.x = 2
            cow.value.y = 2
        }
    }

    @Test
    void cow_poops() {
        cows.each { cow ->
            cow.value.food = 5
            cow.value.poop = 5
            CowBehaviour.act(cow, pasture, Action.POOP)
            assert cow.value.food == 5
            assert cow.value.poop == 0
        }
    }

    @Test
    void cow_eats_grass() {
        pasture.setTile(2, 2, Tile.GRASS)
        cows.each { cow ->
            CowBehaviour.act(cow, pasture, Action.EAT)
            assert cow.value.food == 1
            assert cow.value.poop == 1
            assert pasture.getTile(cow.value.x, cow.value.y) == Tile.DIRT
        }
    }

    @Test
    void cow_doesnt_eat_dirt() {
        pasture.setTile(2, 2, Tile.DIRT)
        cows.each { cow ->
            CowBehaviour.act(cow, pasture, Action.EAT)
            assert cow.value.food == 0
            assert cow.value.poop == 0
            assert pasture.getTile(cow.value.x, cow.value.y) == Tile.DIRT
        }
    }

    @Test
    void cow_doesnt_escape() {
        cows.each { cow ->
            cow.value.x = 0
            CowBehaviour.act(cow, pasture, Action.MOVE_LEFT)
            assert cow.value.x == 0
        }
    }

    @Test
    void cow_stands() {
        cows.each { cow ->
            CowBehaviour.act(cow, pasture, Action.STAND)
            assert cow.value.x == 2
            assert cow.value.y == 2
        }
    }

    @Test
    void cow_moves_left() {
        cows.each { cow ->
            CowBehaviour.act(cow, pasture, Action.MOVE_LEFT)
            assert cow.value.x == 1
            assert cow.value.y == 2
        }
    }

    @Test
    void cow_moves_right() {
        cows.each { cow ->
            CowBehaviour.act(cow, pasture, Action.MOVE_RIGHT)
            assert cow.value.x == 3
            assert cow.value.y == 2
        }
    }

    @Test
    void cow_moves_up() {
        cows.each { cow ->
            CowBehaviour.act(cow, pasture, Action.MOVE_UP)
            assert cow.value.x == 2
            assert cow.value.y == 1
        }
    }

    @Test
    void cow_moves_down() {
        cows.each { cow ->
            CowBehaviour.act(cow, pasture, Action.MOVE_DOWN)
            assert cow.value.x == 2
            assert cow.value.y == 3
        }
    }
}
