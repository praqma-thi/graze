package graze.actor.imp

import org.junit.Test
import graze.actor.*
import graze.pasture.*
import graze.actor.imp.TtogttoghanCow
import graze.utils.TestUtils

class TheBestCowOffalTimeTest {
    @Test
    void always_devours() {
        def cow = new TheBestCowOffalTime()

        50.times {
            assert cow.act(null) == Action.EAT
        }
    }

    @Test
    void has_grass() {
        def cow = new TheBestCowOffalTime()
        def tile = new Tile()

        assert cow.hasGrass(tile) == false
        tile.actors.add(new Grass())
        assert cow.hasGrass(tile) == true
    }

    @Test
    void always_moves_to_grass() {
        def pasture = TestUtils.getEmptyPasture(3, 3)
        def cow = new TheBestCowOffalTime()
        def grass = new Grass()

        pasture[1][1].actors.add(cow)
        pasture[2][1].actors.add(grass)

        50.times {
            assert cow.move(pasture) == Move.MOVE_DOWN
        }

        pasture[2][1].actors.remove(grass)
        pasture[1][2].actors.add(grass)

        50.times {
            assert cow.move(pasture) == Move.MOVE_RIGHT
        }
    }
}
