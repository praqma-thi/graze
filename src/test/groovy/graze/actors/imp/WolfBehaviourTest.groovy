package graze.behaviour.imp

import org.junit.Test
import org.junit.Before

import graze.actor.*
import graze.actor.imp.*
import graze.pasture.*
import graze.utils.TestUtils

class WolfBehaviourTest {
    Pasture pasture
    Wolf wolf

    @Test
    void wolf_always_eats() {
        pasture = TestUtils.getEmptyPasture()
        wolf = new Wolf()
        pasture[1][1].actors.add(wolf)

        assert wolf.act(pasture.surroundingsOf(wolf)) == Action.EAT
    }

    @Test
    void wolf_eats_cows_trail_mix() {
        pasture = TestUtils.getEmptyPasture()
        wolf = new Wolf()
        wolf.food = 0

        def cow = new RCow()

        pasture[1][1].actors.add(wolf)
        pasture[1][1].actors.add(cow)
        wolf.eat(pasture.tileOf(wolf))

        assert wolf.food == 5
        assert pasture.tileOf(cow) == null
    }

    @Test
    void wolf_doesnt_eat_wolf() {
        pasture = TestUtils.getEmptyPasture()
        wolf = new Wolf()
        wolf.food = 0

        def wolf2 = new Wolf()

        pasture[1][1].actors.add(wolf)
        pasture[1][1].actors.add(wolf2)
        wolf.eat(pasture.tileOf(wolf))

        assert wolf.food == 0
        assert pasture.tileOf(wolf2) == pasture.tileOf(wolf)
    }

    @Test
    void wolf_doesnt_eat_grass() {
        pasture = TestUtils.getEmptyPasture()
        wolf = new Wolf()
        wolf.food = 0

        def grass = new Grass()

        pasture[1][1].actors.add(wolf)
        pasture[1][1].actors.add(grass)
        wolf.eat(pasture.tileOf(wolf))

        assert wolf.food == 0
        assert pasture.tileOf(grass) == pasture.tileOf(wolf)
    }
}
