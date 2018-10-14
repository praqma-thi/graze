package graze.engine

import org.junit.Test

class MapGeneratorTest {
    @Test
    void generate_dimensions_match() {
        def map = new MapGenerator().width(15).height(30).generate()
        assert map.size() == 15     // width
        assert map[0].size == 30    // height
    }

    @Test
    void generate_with_border() {
        def map = new MapGenerator().width(3).height(3).border(Tile.FENCE).generate()
        map.each { r -> 
            r.eachWithIndex { tile, c -> 
                // The middle tile should be the only non-fence
                if (r != 1 && c != 1) {
                    assert tile == Tile.FENCE
                } else {
                    assert tile != Tile.FENCE
                }
            }
        }
    }
}
