package graze.behaviour.imp

import org.junit.Test
import org.junit.Before

import graze.actor.*
import graze.actor.imp.*
import graze.pasture.*
import graze.utils.TestUtils

class TrollGrassTest {
    Pasture pasture
    TrollGrass grass = new TrollGrass()

    @Test
    void trollGrass_moves_right() {
        pasture = TestUtils.getEmptyPasture()
        pasture[0][1].actors.add(grass)

        grass.direction = Move.MOVE_UP
        assert grass.move(pasture.surroundingsOf(grass)) == Move.MOVE_RIGHT
    }

    @Test
    void trollGrass_moves_down() {
        pasture = TestUtils.getEmptyPasture()
        pasture[0][2].actors.add(grass)

        grass.direction = Move.MOVE_RIGHT
        assert grass.move(pasture.surroundingsOf(grass)) == Move.MOVE_DOWN
    }

    @Test
    void trollGrass_moves_left() {
        pasture = TestUtils.getEmptyPasture()
        pasture[2][2].actors.add(grass)

        grass.direction = Move.MOVE_DOWN
        assert grass.move(pasture.surroundingsOf(grass)) == Move.MOVE_LEFT
    }

    @Test
    void trollGrass_moves_up() {
        pasture = TestUtils.getEmptyPasture()
        pasture[2][0].actors.add(grass)

        grass.direction = Move.MOVE_LEFT
        assert grass.move(pasture.surroundingsOf(grass)) == Move.MOVE_UP
    }
}
