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
    void newCows_creates_cows() {
        def setup = new Setup()
        def pasture = setup.newPasture()
        def cows = setup.newCows(pasture)
        assert cows.size() == 10
    }

    @Test
    void newCows_cows_dont_overlap() {
        def setup = new Setup()
        def pasture = setup.newPasture()
        def cows = setup.newCows(pasture)
        def coordinates = cows.attributes.collect { [x: it.x, y: it.y] }
        coordinates.each { my ->
            assert coordinates.grep { their ->
                their.x == my.x && their.y == my.y 
            }.size() == 1
        }
    }

    @Test
    void newCows_cows_arent_placed_on_obstacles() {
        def setup = new Setup()
        setup.pastureGenerator.width(7) // TODO: move to a config file or something
        setup.pastureGenerator.height(4)
        def pasture = setup.newPasture()
        def cows = setup.newCows(pasture)
        cows.each { cow, attributes ->
            assert !pasture.getTile(attributes.x, attributes.y).isObstacle
        }
    }

    @Test(expected = IllegalStateException.class)
    void newCows_throws_exception_when_map_too_small() {
        def setup = new Setup()
        setup.pastureGenerator.border(Tile.FENCE)
        setup.pastureGenerator.width(5)
        setup.pastureGenerator.height(2)
        def pasture = setup.newPasture()
        def cows = setup.newCows(pasture)
    }
}
