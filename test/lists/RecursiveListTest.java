package lists;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecursiveListTest {

    RecursiveList<Integer> test;

    @BeforeEach
    void setUp() {
        test = new RecursiveList<Integer>();
        test.append(1);
        test.append(4);
        test.append(3);
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