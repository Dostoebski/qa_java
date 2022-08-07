package com.example;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

@RunWith(Enclosed.class)
public class LionTest {

    @RunWith(MockitoJUnitRunner.class)
    public static class NotParametrizedTest {
        @Mock
        Feline feline;

        @Test
        public void lionConstructorIncorrectSexThrowException() {
            String expected = "Используйте допустимые значения пола животного - самец или самка";
            Exception actual = new Exception();
            try {
                new Lion("Что-то", feline);
            } catch (Exception e) {
                actual = e;
            }
            Assert.assertEquals("Должна вернуться ошибка.", expected, actual.getMessage());
        }
    }
    @RunWith(Parameterized.class)
    public static class ParametrizedTest {
        @Mock
        Feline feline;

        String sex;

        public ParametrizedTest(String sex) {
            this.sex = sex;
        }

        @Before
        public void setUp() {
            MockitoAnnotations.openMocks(this);
        }

        @Parameterized.Parameters
        public static Object[] data() {
            return new Object[]{"Самец", "Самка"};
        }

        @Test
        public void getKittensWithoutParamsReturnsOne() throws Exception {
            Lion lion = new Lion(sex, feline);
            Mockito.when(feline.getKittens()).thenReturn(1);
            int expected = 1;
            int actual = lion.getKittens();

            Assert.assertEquals("getKittens без параметров должен возвращать 1.", expected, actual);
        }

        @Test
        public void doesHaveManeReturnsCorrectValue() throws Exception {
            Lion lion = new Lion(sex, feline);
            boolean expected = sex.equals("Самец");
            boolean actual = lion.doesHaveMane();

            Assert.assertEquals("Если самка = false, если самец = true.", expected, actual);
        }

        @Test
        public void getFoodReturnsFoodListForPredator() throws Exception {
            Lion lion = new Lion(sex, feline);
            Mockito.when(feline.getFood("Хищник")).thenReturn(List.of("Животные", "Птицы", "Рыба"));
            List<String> expected = List.of("Животные", "Птицы", "Рыба");
            List<String> actual = lion.getFood();

            Assert.assertEquals("Должен вернуться список еды для хищника.", expected, actual);
        }
    }
}
