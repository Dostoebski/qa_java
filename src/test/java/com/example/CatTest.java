package com.example;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class CatTest {

    @Mock
    Feline feline;

    @Test
    public void getSoundReturnsCorrectValue() {
        Cat cat = new Cat(feline);
        String expected = "Мяу";
        String actual = cat.getSound();

        Assert.assertEquals("Кот должен сказать: 'Мяу'.", expected, actual);
    }

    @Test
    public void getFoodReturnsFoodListForCat() throws Exception {
        Cat cat = new Cat(feline);
        Mockito.when(feline.eatMeat()).thenReturn(List.of("Животные", "Птицы", "Рыба"));
        List<String> expected = List.of("Животные", "Птицы", "Рыба");
        List<String> actual = cat.getFood();

        Assert.assertEquals("Должен вернуться список еды для хищника.", expected, actual);
    }
}
