package graze.engine

import org.junit.Test
import graze.actor.Cow
import graze.actor.imp.RCow
import graze.pasture.Tile
import graze.pasture.Pasture

class SetupTest {
    Config getTestConfig() {
        Config c = new Config()
        c["pasture.width"] = 15
        c["pasture.height"] = 15
        c["cows"] = [
            "graze.actor.imp.RCow": 15
        ]
        return c
    }

    @Test
    void newPasture_creates_pasture() {
        Setup setup = new Setup(getTestConfig())
        Pasture pasture = setup.newPasture()
        assert pasture != null && pasture.getTile(0, 0) != null
    }

    @Test
    void newCows_creates_cows() {
        Setup setup = new Setup(getTestConfig())
        ArrayList<Cow> cows = setup.newCows()
        assert cows.size() == 15
    }

    @Test
    void placeCows_places_all_cows() {
        Setup setup = new Setup(getTestConfig())
        Pasture pasture = setup.newPasture()
        ArrayList<Cow> cows = setup.newCows()
        setup.placeCows(cows, pasture)
        cows.each { cow ->
            assert pasture.coordinatesOf(cow) != null
        }
    }
}
