package graze.pasture

import org.junit.Test
import org.junit.Before

class PastureTest {
    Pasture pasture = null
    PastureGenerator generator = new PastureGenerator()
        .width(3)
        .height(3)
        .border(Tile.FENCE)
        .tiles(WeightedMap.from([(Tile.DIRT): 1]))

    @Before
    void Setup() {
        pasture = generator.generate()
    }
    
    @Test
    void setTile_sets() {
        assert pasture.getTile(0, 1) == Tile.FENCE
        pasture.setTile(0, 1, Tile.DIRT)
        assert pasture.getTile(0, 1) == Tile.DIRT
        assert pasture.surroundingsOf(1, 1) == [
            [Tile.FENCE, Tile.FENCE, Tile.FENCE],
            [Tile.DIRT , Tile.DIRT,  Tile.FENCE],
            [Tile.FENCE, Tile.FENCE, Tile.FENCE],
        ]
    }

    @Test
    void surroundingsOf_center() {
        assert pasture.surroundingsOf(1, 1) == [
            [Tile.FENCE, Tile.FENCE, Tile.FENCE],
            [Tile.FENCE, Tile.DIRT,  Tile.FENCE],
            [Tile.FENCE, Tile.FENCE, Tile.FENCE],
        ]
    }

    @Test
    void surroundingsOf_bottom_right() {
        assert pasture.surroundingsOf(2, 2) == [
            [Tile.DIRT,  Tile.FENCE],
            [Tile.FENCE, Tile.FENCE],
        ]
    }

    @Test
    void surroundingsOf_top_left() {
        assert pasture.surroundingsOf(0, 0) == [
            [Tile.FENCE, Tile.FENCE],
            [Tile.FENCE,  Tile.DIRT],
        ]
    }

    @Test
    void surroundingsOf_right_edge() {
        assert pasture.surroundingsOf(2, 1) == [
            [Tile.FENCE, Tile.FENCE],
            [Tile.DIRT,  Tile.FENCE],
            [Tile.FENCE, Tile.FENCE],
        ]
    }

    @Test
    void surroundingsOf_left_edge() {
        assert pasture.surroundingsOf(0, 1) == [
            [Tile.FENCE, Tile.FENCE],
            [Tile.FENCE, Tile.DIRT ],
            [Tile.FENCE, Tile.FENCE],
        ]
    }

    @Test
    void surroundingsOf_bottom_edge() {

        assert pasture.surroundingsOf(1, 2) == [
            [Tile.FENCE, Tile.DIRT,  Tile.FENCE],
            [Tile.FENCE, Tile.FENCE, Tile.FENCE],
        ]
    }

    @Test
    void surroundingsOf_top_edge() {
        assert pasture.surroundingsOf(1, 0) == [
            [Tile.FENCE, Tile.FENCE, Tile.FENCE],
            [Tile.FENCE, Tile.DIRT,  Tile.FENCE],
        ]
    }
}
