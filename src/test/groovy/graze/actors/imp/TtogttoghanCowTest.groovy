package graze.actor.imp

import org.junit.Test
import graze.actor.*
import graze.actor.imp.TtogttoghanCow
import graze.utils.TestUtils

class TtogttoghanCowTest {

    @Test
    void always_eats() {
        def pasture = TestUtils.getEmptyPasture()
        def cow = new TtogttoghanCow()
        pasture[1][1].actors.add(cow)

        def action = cow.act(pasture.surroundingsOf(cow))

        assert action == Action.EAT
    }

    @Test
    void never_stands_still() {
        def pasture = TestUtils.getEmptyPasture()
        def cow = new TtogttoghanCow()
        pasture[1][1].actors.add(cow)

        50.times {
            assert cow.move(pasture.surroundingsOf(cow)) != Move.STAND
        }
    }

    @Test
    void doesnt_walk_off_map() {
        def pasture = TestUtils.getEmptyPasture(1, 2)
        def cow = new TtogttoghanCow()
        pasture[0][0].actors.add(cow)

        50.times {
            assert cow.move(pasture.surroundingsOf(cow)) == Move.MOVE_RIGHT
        }
    }

    @Test
    void moves_to_grass() {
        def pasture = TestUtils.getEmptyPasture(3, 3)
        def cow = new TtogttoghanCow()
        pasture[1][1].actors.add(cow)
        pasture[1][0].actors.add(new Grass())

        50.times {
            assert cow.move(pasture.surroundingsOf(cow)) == Move.MOVE_LEFT
        }
    }

    @Test
    void removes_void_moves() {
        def pasture = TestUtils.getEmptyPasture(1, 2)
        def cow = new TtogttoghanCow()
        pasture[0][0].actors.add(cow)

        def options = cow.moves.keySet().toList()
        cow.removeVoidMoves(options, pasture)
        assert options.size() == 1
        assert options[0] == Move.MOVE_RIGHT
    }

    @Test
    void no_redundant_moves() {
        def pasture = TestUtils.getEmptyPasture()
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
