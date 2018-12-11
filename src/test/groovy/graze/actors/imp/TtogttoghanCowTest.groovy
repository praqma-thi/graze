package graze.actor.imp

import org.junit.Test
import graze.pasture.*
import graze.actor.*

class TtogttoghanCowTest {
    Pasture getTestPasture() {
        return [
            [new Tile(), new Tile(), new Tile()],
            [new Tile(), new Tile(), new Tile()],
            [new Tile(), new Tile(), new Tile()],
        ] as Pasture
    }

    @Test
    void always_eats() {
        def pasture = getTestPasture()
        def cow = new TtogttoghanCow()
        pasture[1][1].actors.add(cow)

        def action = cow.act(pasture.surroundingsOf(cow))

        assert action == Action.EAT
    }

    /*
    @Test
    void never_stands_still() {
        def pasture = getTestPasture()
        def cow = new TtogttoghanCow()
        pasture[1][1].actors.add(cow)

        50.times {
            assert cow.move(pasture.surroundingsOf(cow)) != Move.STAND
        }
    }
    */
}
