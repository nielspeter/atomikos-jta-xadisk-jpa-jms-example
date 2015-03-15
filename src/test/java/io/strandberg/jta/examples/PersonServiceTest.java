package io.strandberg.jta.examples;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.IOException;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class PersonServiceTest {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonService personService;

    @Before
    public void before() throws IOException {
        FileUtils.deleteDirectory(new File("build/tmp/person"));
    }

    @Test
    public void testCommit() throws Exception {

        // setup
        Person person = new Person();
        person.setName("Niels Peter");

        // when
        personService.registerNewPerson(person, false);

        // then
        assertEquals(1, personRepository.count());
        assertEquals("Niels Peter", personRepository.findByName("Niels Peter").getName());
        assertEquals("{name: 'Niels Peter'}", personService.receivePersonCreatedMessage());
        assertTrue(new File("build/tmp/person/Niels Peter.txt").exists());
    }

    @Test
    public void testRollback() {

        // setup
        Person person = new Person();
        person.setName("Niels Peter");

        // when
        try {
            personService.registerNewPerson(person, true);
        } catch (Exception e) {
            assertEquals("BOOM", e.getMessage());
        }

        // then
        assertEquals(0, personRepository.count());
        assertNull(personService.receivePersonCreatedMessage());
        assertFalse(new File("build/tmp/person/Niels Peter.txt").exists());
    }
}
