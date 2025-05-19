package org.example.dao;

import org.example.models.Book;
import org.example.models.Person;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //Возвращается список людей
    public List<Person> index() {
        return jdbcTemplate.query("select * from Person", new BeanPropertyRowMapper<>(Person.class));
    }

    //Возвращается человек по его id
    public Person show(int id) {
        return jdbcTemplate.query("select * from Person where id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }

    //Сохранение человека в БД
    public void save(Person person) {
        jdbcTemplate.update("insert into Person(full_name, year_of_birth) values(?, ?)", person.getFullName(), person.getYearOfBirth());
    }

    //Обновление человека в БД
    public void update(int id, Person updatedPerson) {
        jdbcTemplate.update("update Person set full_name = ?, year_of_birth = ? where id = ?", updatedPerson.getFullName(),
                updatedPerson.getYearOfBirth(), id);
    }

    //Удаление "персона" из БД
    public void delete(int id) {
        jdbcTemplate.update("delete from Person where id = ?", id);
    }

    public List<Book> getBooks(int id) {
        return jdbcTemplate.query("select * from Book where person_id = ?", new Object[]{id},
                        new BeanPropertyRowMapper<>(Book.class));
    }
}
