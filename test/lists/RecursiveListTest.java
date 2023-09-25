package lists;

import lists.RecursiveList;
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
        assertEquals(3, test.getCount());
        test.remove(1);
        assertEquals(2, test.getCount());
    }
    @Test
    void test() {
        RecursiveList<String> list = new RecursiveList<String>();

    }
}