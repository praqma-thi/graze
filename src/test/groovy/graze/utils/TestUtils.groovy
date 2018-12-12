package graze.utils

import graze.pasture.*

class TestUtils {
    static Pasture getEmptyPasture() {
        return getEmptyPasture(3, 3)
    }

    static Pasture getEmptyPasture(int rows, int columns) {
        Pasture pasture = [] as Pasture
        rows.times {
            def row = []
            columns.times {
                row.add(new Tile())
            }
            pasture.add(row)
        }
        return pasture
    }
}
