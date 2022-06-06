package Reminder;

import Reminder.Note;
import Reminder.repository.NoteRepositoryCsvImpl;
import Reminder.repository.NoteRepository;
import org.junit.Assert;
import org.junit.Test;

import java.time.Instant;

public class Test {
    private final NoteRepository repository = new NoteRepositoryCsvImpl("C:\\Users\\agent\\Downloads\\" + "notes_test.csv");

    @org.junit.Test
    public void checkSave() {
        repository.save(new Note("Test1", Instant.ofEpochSecond(1648190891), false));
        repository.save(new Note("Test2", Instant.ofEpochSecond(1648190891), false));

        Assert.assertEquals(2, repository.getAll().size());
        repository.deleteAll();
    }

    // Тест на получение по id
    @org.junit.Test
    public void getById() {
        repository.save(new Note("Test1", Instant.now(), false));
        repository.save(new Note("Test2", Instant.now(), false));

        Assert.assertEquals("Test1", repository.getById(1).getText());
        Assert.assertEquals("Test2", repository.getById(2).getText());
        repository.deleteAll();
    }

    // Тест на получение невыполненных
    @org.junit.Test
    public void getUndone() {
        repository.save(new Note("Test1", Instant.now(), true));
        repository.save(new Note("Test2", Instant.now(), false));
        repository.save(new Note("Test3", Instant.now(), true));
        repository.save(new Note("Test4", Instant.now(), false));

        Assert.assertEquals(2, repository.getUndoneByDate(Instant.now()).size());
        repository.deleteAll();
    }

    // Тест на удаление по id
    @org.junit.Test
    public void deleteById() {
        repository.save(new Note("Test1", Instant.now(), true));
        repository.save(new Note("Test2", Instant.now(), false));
        repository.save(new Note("Test3", Instant.now(), true));
        repository.save(new Note("Test4", Instant.now(), false));

        repository.delete(2);
        repository.delete(3);
        Assert.assertEquals("Test4", repository.getAll().get(1).getText());
        repository.deleteAll();
    }
}
