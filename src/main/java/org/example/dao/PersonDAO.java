package org.example.dao;

import org.example.models.Person;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

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

    public Optional<Person> show(String email) {
            return jdbcTemplate.query("select * from Person where email = ?", new Object[]{email},
                    new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }

    //Возвращается человек по его id
    public Person show(int id) {
        return jdbcTemplate.query("select * from Person where id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }

    //Сохранение человека в БД
    public void save(Person person) {
        jdbcTemplate.update("insert into Person(name, age, email) values(?, ?, ?)", person.getName(), person.getAge(), person.getEmail());
    }

    //Обновление человека в БД
    public void update(int id, Person updatedPerson) {
        jdbcTemplate.update("update Person set name = ?, age = ?, email = ? where id = ?", updatedPerson.getName(),
                updatedPerson.getAge(), updatedPerson.getEmail(), id);
    }

    //Удаление "персона" из БД
    public void delete(int id) {
        jdbcTemplate.update("delete from Person where id = ?", id);
    }
}
