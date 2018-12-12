package graze.engine

import org.junit.Test
import graze.actor.Actor
import graze.actor.imp.RCow
import graze.pasture.Tile
import graze.pasture.Pasture

class SetupTest {
    Config getTestConfig() {
        Config c = new Config()
        c["pasture.width"] = 15
        c["pasture.height"] = 15
        c["actors"] = [
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
    void newActors_creates_actors() {
        Setup setup = new Setup(getTestConfig())
        ArrayList<Actor> actors = setup.newActors()
        assert actors.size() == 15
    }

    @Test
    void placeActors_places_all_actors() {
        Setup setup = new Setup(getTestConfig())
        Pasture pasture = setup.newPasture()
        ArrayList<Actor> actors = setup.newActors()
        setup.placeActors(actors, pasture)
        actors.each { actor ->
            assert pasture.coordinatesOf(actor) != null
        }
    }
}
