package com.example;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.List;

@RunWith(Enclosed.class)
public class FelineTest {

    @RunWith(MockitoJUnitRunner.class)
    public static class NotParametrizedTest {

        @Spy
        private Feline feline;

        @Test
        public void eatMeatReturnsFoodListForPredator() throws Exception {
            Mockito.when(feline.getFood("Хищник")).thenReturn(List.of("Животные", "Птицы", "Рыба"));
            List<String> expected = List.of("Животные", "Птицы", "Рыба");
            List<String> actual = feline.eatMeat();

            Assert.assertEquals("Должен вернуться список еды для хищника.", expected, actual);
        }

        @Test
        public void getFamilyReturnsFeline() {
            String expected = "Кошачьи";
            String actual = feline.getFamily();

            Assert.assertEquals("Для Feline семейство - Кошачьи.", expected, actual);
        }

        @Test
        public void getKittensWithoutParamsReturnsOne() {
            Mockito.when(feline.getKittens(1)).thenReturn(1);
            int expected = 1;
            int actual = feline.getKittens();

            Assert.assertEquals("getKittens без параметров должен возвращать 1.", expected, actual);
        }
    }

    @RunWith(Parameterized.class)
    public static class ParametrizedTest {

        Feline feline = new Feline();

        private final int kittensCount;

        public ParametrizedTest(int kittensCount) {
            this.kittensCount = kittensCount;
        }

        @Parameterized.Parameters
        public static Object[] data() {
            return new Object[]{-1, 0, 5};
        }

        @Test
        public void getKittensReturnsKittensCount() {
            int actual = feline.getKittens(kittensCount);
            Assert.assertEquals("getKittens должен вернуть переданное количество.", kittensCount, actual);
        }
    }
}
