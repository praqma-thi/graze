package graze.engine

import org.junit.Test

class MapGeneratorTest {
    @Test
    void generate_dimensions_match() {
        def map = new MapGenerator().width(15).height(30).generate()
        assert map.size() == 15     // width
        assert map[0].size == 30    // height
    }
}