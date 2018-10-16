package graze.engine

import org.junit.Test
import graze.actor.imp.RCow
import graze.pasture.Tile

class SetupTest {
    @Test
    void newPasture_creates_pasture() {
        def setup = new Setup()
        def pasture = setup.newPasture()
        assert pasture != null && pasture.getTile(0, 0) != null
    }

    @Test
    void newCows_creates_cows() {
        def setup = new Setup()
        def cows = setup.newCows()
        assert cows.size() == 10
    }

    @Test
    void placeCows_places_all_cows() {
        def setup = new Setup()
        def pasture = setup.newPasture()
        def cows = setup.newCows()
        setup.placeCows(cows, pasture)
        cows.each { cow ->
            assert pasture.coordinatesOf(cow) != null
        }
    }
}
