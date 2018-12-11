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

    @Test
    void never_stands_still() {
        def pasture = getTestPasture()
        def cow = new TtogttoghanCow()
        pasture[1][1].actors.add(cow)

        50.times {
            assert cow.move(pasture.surroundingsOf(cow)) != Move.STAND
        }
    }

    @Test
    void no_redundant_moves() {
        def pasture = getTestPasture()
        def cow = new TtogttoghanCow()
        pasture[1][1].actors.add(cow)

        def lastMove = Move.STAND
        50.times {
            def currentMove = cow.move(pasture.surroundingsOf(cow))
            switch(currentMove) {
                case Move.MOVE_LEFT:
                    assert lastMove != Move.MOVE_RIGHT
                    break
                case Move.MOVE_RIGHT:
                    assert lastMove != Move.MOVE_LEFT
                    break
                case Move.MOVE_DOWN:
                    assert lastMove != Move.MOVE_UP
                    break
                case Move.MOVE_UP:
                    assert lastMove != Move.MOVE_DOWN
                    break
                default:
                    throw new Exception("Did not expect move: ${currentMove}")
            }
            lastMove = currentMove
        }
    }
}
