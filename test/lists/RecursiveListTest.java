package lists;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecursiveListTest {

    RecursiveList<Integer> test;

    @BeforeEach
    void setUp() {
        test = new RecursiveList<Integer>();
        test.add(1);
        test.add(4);
        test.add(3);
        assertFalse(test.isEmpty());
        assertEquals(3, test.size());
        test.remove(1);
        assertEquals(2, test.size());
    }
    @Test
    void test() {
        RecursiveList<String> list = new RecursiveList<String>();

    }
}