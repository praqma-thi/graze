package graze.engine

import org.junit.Test

class GameTest {
    @Test
    void setup_creates_map() {
        def g = new Game()
        g.setup()
        assert g.pasture != null && g.pasture[0] != null && g.pasture[0][0] != null
    }

    @Test
    void setup_creates_actors() {
        def g = new Game()
        g.setup()
        assert g.actors.size() == 10
    }

    @Test
    void setup_doesnt_overlap_actors() {
        def g = new Game()
        g.setup()
        def coordinates = g.actors.collect { [x: it.x, y: it.y] }
        coordinates.each { my ->
            assert coordinates.grep { their ->
                their.x == my.x && their.y == my.y 
            }.size() == 1
        }
    }

    @Test
    void setup_doesnt_place_actors_on_obstacles() {
        def g = new Game()
        g.setup()
        g.pastureGenerator.width = 7
        g.pastureGenerator.height = 4
        g.actors.each { actor ->
            assert !g.pasture[actor.x][actor.y].isObstacle
        }
    }

    @Test(expected = IllegalStateException.class)
    void setup_doesnt_generate_map_if_too_small() {
        def g = new Game()
        g.pastureGenerator.width = 5
        g.pastureGenerator.height = 2
        g.setup()
    }
}
