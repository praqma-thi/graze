package graze.actor

import org.junit.Test

class CowTest {
    // Cow to test with
    class TestCow extends Cow {
        Action takeTurn(def surroundings) { return Action.POOP }
    }

    @Test
    void equals_same_cow() {
        def cow = new TestCow()
        assert cow == cow
    }

    @Test
    void equals_different_cow() {
        def cow1 = new TestCow()
        def cow2 = new TestCow()
        assert cow1 != cow2
    }

    @Test
    void equals_no_cow() {
        def cow = new TestCow()
        assert cow != null
    }
}
