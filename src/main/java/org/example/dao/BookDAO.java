package org.example.dao;

import org.example.models.Book;
import org.example.models.Person;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //Возвращается список книг
    public List<Book> index() {
        return jdbcTemplate.query("select * from Book", new BeanPropertyRowMapper<>(Book.class));
    }

    //Возвращается книгу по её id
    public Book show(int id) {
        return jdbcTemplate.query("select * from Book where id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny().orElse(null);
    }

    //Возвращается владелец книги
    public Person getBookOwner(int id) {
        return jdbcTemplate.query("select * from Book join Person on Book.person_id = Person.id " +
                "where Book.id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }

    //Сохранение книги в БД
    public void save(Book book) {
        jdbcTemplate.update("insert into Book(title, author, year) values(?, ?, ?)", book.getTitle(), book.getAuthor(), book.getYear());
    }

    //Обновление книги в БД
    public void update(int id, Book updatedBook) {
        jdbcTemplate.update("update Book set title = ?, author = ?, year = ? where id = ?", updatedBook.getTitle(),
                updatedBook.getAuthor(), updatedBook.getYear(), id);
    }

    //Удаление книги из БД
    public void delete(int id) {
        jdbcTemplate.update("delete from Book where id = ?", id);
    }

    //Освобождает книгу
    public void release(int id) {
        jdbcTemplate.update("update Book set person_id = null where id=?", id);
    }

    // Назначает книгу человеку (этот метод вызывается, когда человек забирает книгу из библиотеки)
    public void assign(int id, Person selectedPerson) {
        jdbcTemplate.update("update Book set person_id = ? where id = ?", selectedPerson.getId(), id);
    }
}
