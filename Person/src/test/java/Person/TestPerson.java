package Person;


import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;


import java.sql.SQLException;

import static Person.TestUtil.*;
import static org.junit.Assert.assertEquals;


public class TestPerson {
    @BeforeAll
    public static void init() throws SQLException {
        createPersonTable();
    }
    @AfterAll
    public static void tearDown() throws SQLException {
        closeConnection();
    }

    @Test
    public void shouldSaveAndGet() throws SQLException, ClassNotFoundException, NoSuchMethodException {
        Person person1 = Person.builder().name("John").surname("Smith").build();
        DataForTable<Person> data = new DataForTable<>(new Person());
        data.updateInfoInData();
        DAOPerson daoPerson = new DAOPersonImpl(data);
        person1 = daoPerson.save(person1);
        assertEquals("John", person1.getName());
        assertEquals("Smith", person1.getSurname());



    }
    @Test
    public void shouldUpdate() throws SQLException, ClassNotFoundException, NoSuchMethodException {
        Person person1 = Person.builder().name("John").surname("Smith").build();
        DataForTable<Person> data = new DataForTable<>(new Person());
        data.updateInfoInData();
        DAOPerson daoPerson = new DAOPersonImpl(data);
        person1 = daoPerson.save(person1);
        person1.setName("John II");
        daoPerson.update(person1);
        assertEquals("John II", person1.getName());
    }
    @Test
    public void shouldDelete() throws SQLException, ClassNotFoundException, NoSuchMethodException {
        Person person1 = Person.builder().name("John").surname("Smith").build();
        DataForTable<Person> data = new DataForTable<>(new Person());
        data.updateInfoInData();
        DAOPerson daoPerson = new DAOPersonImpl(data);
        person1 = daoPerson.save(person1);
        System.out.println(person1);
        int rows = daoPerson.delete(1);
        assertEquals(1, rows);
    }

}
