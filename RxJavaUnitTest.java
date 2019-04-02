package test.vive.com.myapplication;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void verify_isEmptyValue() {
        //Arrange
        Optional<String> optional = Optional.empty();

        //Assert
        assertFalse(optional.isPresent());
    }

    @Test
    public void verifyOptionalValue() {
        //Arrange
        String nanme = "Mohan";

        //Act
        Optional<String> nameList = Optional.of(nanme);

        //Assert
        assertEquals(nanme, nameList.get());
        assertNotNull(nameList);
        assertTrue(nameList.isPresent());
    }

    @Test(expected = NullPointerException.class)
    public void verify_null_optional() {
        Optional<String> optional = Optional.of(null);
    }

    @Test
    public void verify_optional_value() {
        Optional<String> optional = Optional.ofNullable(null);
        String optional1 = (String) Optional.ofNullable(null).orElse("Ben");
        assertEquals("Ben", optional1);
        assertEquals(Optional.empty(), optional);
    }

    @Test(expected = NullPointerException.class)
    public void verify_exception_optional() throws Throwable {
        Optional<String> optional = null;
        String name = (String) Optional.ofNullable(null).orElseThrow(
                NullPointerException::new);

        List<String> newList = Arrays.asList("Mohan", "Ben", "Chandrasekar");

        Optional<List<String>> optionalList = Optional.of(newList);

        optionalList.map(List::size).orElse(0);


    }

    @Test
    public void when_optional_filter_works() {
        int isYear = 2019;
        Optional<Integer> optionalInteger = Optional.of(isYear);
        optionalInteger.map(new Function<Integer, Object>() {
            @Override
            public Object apply(Integer integer) {
                return integer == 1221;
            }
        });
        boolean present = optionalInteger.filter(m -> m == isYear).isPresent();
        boolean present1 = optionalInteger.filter(m -> m == 2012).isPresent();

        boolean present2 = optionalInteger.filter(new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                return integer == 222;
            }
        }).isPresent();
        assertFalse(present2);
        assertTrue(present);
        assertFalse(present1);
    }
}