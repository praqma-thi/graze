package graze.pasture

import org.junit.Test

class PastureGeneratorTest {
    @Test
    void generate_dimensions_match() {
        def pasture = new PastureGenerator().width(15).height(30).generate()
        assert pasture.size() == 15     // width
        assert pasture[0].size == 30    // height
    }

    @Test
    void generate_with_border() {
        def pasture = new PastureGenerator().width(3).height(3).border(Tile.FENCE).generate()
        println pasture
        pasture.eachWithIndex { row, r -> 
            row.eachWithIndex { tile, c -> 
                print "testing $r $c $tile: "
                // The middle tile should be the only non-fence
                if (r == 1 && c == 1) {
                    println "mustn't be fence"
                    assert tile != Tile.FENCE
                } else {
                    println "must be fence"
                    assert tile == Tile.FENCE
                }
            }
        }
    }
}
