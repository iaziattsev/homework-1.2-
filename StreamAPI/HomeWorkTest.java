package StreamAPI;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class HomeWorkTest {
    @Test
    public void checkTask1() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4);

        // Применить умножение каждого элемента на 2
        list = list.stream().map(x -> x *= 2).collect(Collectors.toList());

        Assert.assertEquals(Integer.valueOf(2), list.get(0));
        Assert.assertEquals(Integer.valueOf(4), list.get(1));
        Assert.assertEquals(Integer.valueOf(6), list.get(2));
        Assert.assertEquals(Integer.valueOf(8), list.get(3));
    }

    @Test
    public void checkTask2() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4);

        // Отфильтровать элементы, оставив только четные
        list = list.stream().filter(x -> x % 2 == 0).collect(Collectors.toList());

        Assert.assertEquals(2, list.size());
        Assert.assertEquals(Integer.valueOf(2), list.get(0));
        Assert.assertEquals(Integer.valueOf(4), list.get(1));
    }

    @Test
    public void checkTask3() {
        List<String> list = Arrays.asList("Ivanov", "Sidorov", "Antonov", "Kuznecov", "Kulikov", "Ahmetov");

        // Отсортировать по алфавиту
        list = list.stream().sorted(String::compareTo).collect(Collectors.toList());

        Assert.assertEquals("Antonov", list.get(1));
        Assert.assertEquals("Ivanov", list.get(2));
        Assert.assertEquals("Kuznecov", list.get(4));
    }

    @Test
    public void checkTask4() {
        List<Student> list = Arrays.asList(
                new Student("Ivan", "Ivanov", 50),
                new Student("Vasya", "Sidorov", 60),
                new Student("Anton", "Antonov", 70),
                new Student("Andrey", "Kuznecov", 80),
                new Student("Oleg", "Kulikov", 10),
                new Student("Ashot", "Ahmetov", 20)
        );

        // Отсортировать по score и по алфавиту, выбрать только тех, у кого score больше или равно 56
        list = list.stream().sorted((o1, o2) -> {
            if (o1.getScore() > o2.getScore())
                return -1;
            if (o1.getScore() < o2.getScore())
                return 1;

            int firstNameSorted = o1.getFirstName().compareTo(o2.getFirstName());

            if (firstNameSorted != 0)
                return firstNameSorted;

            return o1.getLastName().compareTo(o2.getLastName());
        }).collect(Collectors.toList());

        Assert.assertEquals("Kuznecov", list.get(0).getLastName());
        Assert.assertEquals("Antonov", list.get(1).getLastName());
        Assert.assertEquals(60, list.get(2).getScore());
    }
}
