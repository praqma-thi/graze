package graze.pasture

import org.junit.Test
import org.junit.Before

class PastureTest {
    Pasture pasture = null
    PastureGenerator generator = new PastureGenerator()
        .width(3)
        .height(3)
        .actors(WeightedMap.from([(null): 1]))

    @Before
    void Setup() {
        pasture = generator.generate()
    }

    @Test
    void coordinates_of_returns_correct_coordinates() {
        def cow = new graze.actor.imp.RCow()
        pasture.getTile(1, 1).actors.add(cow)
        def coordinates = pasture.coordinatesOf(cow)
        assert coordinates.x == 1
        assert coordinates.y == 1
    }
    
    @Test
    void setTile_sets() {
        def tile = pasture.getTile(0, 1)
        pasture.setTile(0, 1, null)
        assert pasture.getTile(0, 1) == null
        pasture.setTile(0, 1, tile)
        assert pasture.getTile(0, 1) == tile
    }

    @Test
    void surroundingsOf_center() {
        def surroundings = pasture.surroundingsOf(1, 1)
        assert surroundings.size() == 3
        assert surroundings[0].size() == 3
    }

    @Test
    void surroundingsOf_bottom_right() {
        def surroundings = pasture.surroundingsOf(2, 2)
        assert surroundings.size() == 2
        assert surroundings[0].size() == 2
    }

    @Test
    void surroundingsOf_top_left() {
        def surroundings = pasture.surroundingsOf(0, 0)
        assert surroundings.size() == 2
        assert surroundings[0].size() == 2
    }

    @Test
    void surroundingsOf_right_edge() {
        def surroundings = pasture.surroundingsOf(2, 1)
        assert surroundings.size() == 3
        assert surroundings[0].size() == 2
    }

    @Test
    void surroundingsOf_left_edge() {
        def surroundings = pasture.surroundingsOf(0, 1)
        assert surroundings.size() == 3
        assert surroundings[0].size() == 2
    }

    @Test
    void surroundingsOf_bottom_edge() {
        def surroundings = pasture.surroundingsOf(1, 2)
        assert surroundings.size() == 2
        assert surroundings[0].size() == 3
    }

    @Test
    void surroundingsOf_top_edge() {
        def surroundings = pasture.surroundingsOf(1, 0)
        assert surroundings.size() == 2
        assert surroundings[0].size() == 3
    }
}
