package graze.engine

import org.junit.Test
import graze.pasture.Tile

class SetupTest {
    @Test
    void newPasture_creates_pasture() {
        def setup = new Setup()
        def pasture = setup.newPasture()
        assert pasture != null && pasture.getTile(0, 0) != null
    }

    @Test
    void newActors_creates_actors() {
        def setup = new Setup()
        def pasture = setup.newPasture()
        def actors = setup.newActors(pasture)
        assert actors.size() == 10
    }

    @Test
    void newActors_actors_dont_overlap() {
        def setup = new Setup()
        def pasture = setup.newPasture()
        def actors = setup.newActors(pasture)
        def coordinates = actors.attributes.collect { [x: it.x, y: it.y] }
        coordinates.each { my ->
            assert coordinates.grep { their ->
                their.x == my.x && their.y == my.y 
            }.size() == 1
        }
    }

    @Test
    void newActors_actors_arent_placed_on_obstacles() {
        def setup = new Setup()
        setup.pastureGenerator.width(7) // TODO: move to a config file or something
        setup.pastureGenerator.height(4)
        def pasture = setup.newPasture()
        def actors = setup.newActors(pasture)
        actors.each { actor, attributes ->
            assert !pasture.getTile(attributes.x, attributes.y).isObstacle
        }
    }

    @Test(expected = IllegalStateException.class)
    void newActors_throws_exception_when_map_too_small() {
        def setup = new Setup()
        setup.pastureGenerator.border(Tile.FENCE)
        setup.pastureGenerator.width(5)
        setup.pastureGenerator.height(2)
        def pasture = setup.newPasture()
        def actors = setup.newActors(pasture)
    }
}
