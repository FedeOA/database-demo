package com.in28minutes.database.database_demo.jdbc;

import com.in28minutes.database.database_demo.entity.Person;
import com.in28minutes.database.database_demo.jdbc.mapper.PersonMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PersonJdbcDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public PersonJdbcDao(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public List findAll(){

        String GET_ALL_PERSON = "SELECT * FROM PERSON";

        return namedParameterJdbcTemplate.query(GET_ALL_PERSON,new MapSqlParameterSource(),new PersonMapper());
    }

    public Person findById(int id){

        String GET_PERSON_BY_ID = "SELECT *FROM PERSON WHERE id = :idPerson";

        Map<String, Object> params = new HashMap<>();
        params.put("idPerson",id);

        return (Person) namedParameterJdbcTemplate.queryForObject(GET_PERSON_BY_ID,params,new PersonMapper());
    }

    public int deleteById(int id){

        String DELETE_PERSON_BY_ID = "DELETE FROM PERSON WHERE id = :idPerson";

        Map<String, Object> params = new HashMap<>();
        params.put("idPerson",id);

        return namedParameterJdbcTemplate.update(DELETE_PERSON_BY_ID,params);
    }

    public int insertPerson(Person person){

        String INSERT_PERSON = "INSERT INTO PERSON (ID,NAME,LOCATION,BIRTH_DATE) VALUES (:idPerson, :name, :location, :birthDate)";

        Map<String,Object> params = new HashMap<>();
        params.put("idPerson",person.getId());
        params.put("name",person.getName());
        params.put("location",person.getLocation());
        params.put("birthDate",new Timestamp(person.getBirthDate().getTime()));

        return namedParameterJdbcTemplate.update(INSERT_PERSON,params);
    }

    public int updatePerson(Person person){

        String UPDATE_PERSON = "UPDATE PERSON SET name = :name, location = :location, birth_date = birthDate WHERE id = :idPerson";

        Map<String,Object> params = new HashMap<>();
        params.put("idPerson",person.getId());
        params.put("name",person.getName());
        params.put("location",person.getLocation());
        params.put("birthDate",new Timestamp(person.getBirthDate().getTime()));

        return namedParameterJdbcTemplate.update(UPDATE_PERSON,params);
    }
}
