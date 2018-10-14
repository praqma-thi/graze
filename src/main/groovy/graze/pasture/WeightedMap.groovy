package graze.pasture

class WeightedMap extends HashMap<Object,Integer> {

    final Random random = new Random()

    int weightSum() {
        this.values().sum()
    }

    /** 
    *   Returns a random item by weight
    */
    def pick() {
        int target = random.nextInt(weightSum())

        int sum = 0
        for (def item in this) {
            sum += item.value
            if (target < sum) {
                return item.key
            }
        }

        return null // Shouldn't happen
    }

    static WeightedMap from(Map map) {
        def weightedMap = new WeightedMap()
        map.each { k, v -> weightedMap.put(k, v)}
        return weightedMap
    }
}
