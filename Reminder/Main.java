package Reminder;

import Reminder.Exceptions;
import Reminder.Note;
import Reminder.repository.NoteRepositorySerializableImpl;
import Reminder.repository.NoteRepository;

import java.time.Instant;

public class Main {
    // Добавлять заметки на определенное время
    // Отмечать заметки как выполнено
    // Удалять заметки
    // Получать список всех заметок на день
    // Получать список всех выполненных заметок на день
    public static void main(String[] args) {
        try {
            NoteRepository repository = new NoteRepositorySerializableImpl("C:\\Users\\agent\\Downloads\\" + "notes_database");
            repository.save(new Note("Test1", Instant.now(), false));
            System.out.println(repository.getUndoneByDate(Instant.now()).size());
        } catch (Exceptions e) {
            System.out.println(e.getMessage());
        }
    }
}