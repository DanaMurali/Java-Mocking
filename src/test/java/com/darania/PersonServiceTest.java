package com.darania;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;


class PersonServiceTest {

    @Mock
    private PersonDAO PersonDAO;
    private PersonService underTest;


    private PersonDAO savePerson;
    private PersonDAO deletePerson;
    private PersonDAO getPeople;
    private PersonDAO getPersonById;


    @BeforeEach
    void setUp() {

        // TODO: create a mock for personDAO
        // TODO: create an instance of underTest and pass personDAO into it

        PersonDAO = mock(PersonDAO.class);

       /* savePerson = mock(PersonDAO.class);
        deletePerson = mock(PersonDAO.class);
        getPeople = mock(PersonDAO.class);
        getPersonById = mock(PersonDAO.class);*/

        underTest = new PersonService(PersonDAO);

    }

    /*
       TODO: Test all these methods.
        You might need to create additional methods. Check test coverage
    */

//    Good luck :)

    @Test
    void itCanSavePerson() {
        //if person's id already exists, throw error "id already exists"
        //and save person to database

        //given
        Person person = new Person(5, "Lily", 27);

        //when
        underTest.savePerson(person);

        //then
        ArgumentCaptor<Person> personArgumentCaptor = ArgumentCaptor.forClass(Person.class);

        verify(PersonDAO).savePerson(personArgumentCaptor.capture());

        Person capturedPerson = personArgumentCaptor.getValue();

        assertThat(capturedPerson).isEqualTo(person);


    }

    @Test
    void itCanDeletePerson() {
    //if person's ID doesn't exist then delete person.
        //given
        Person person = new Person(5, "Lily", 27);
        List<Person> getPeople = mock(List.class);
        //when
//        underTest.savePerson(person);
//
//        //then - capture our person in Argument Captor
//        ArgumentCaptor<Person> personArgumentCaptor = ArgumentCaptor.forClass(Person.class);
//        //verifying whether PersonDAO was called with save method, and we are
//        // capturing the person passed into it
//        verify(PersonDAO).savePerson(personArgumentCaptor.capture());
//
//        Person capturedPerson = personArgumentCaptor.getValue();

        getPeople.add(person);
        verify(getPeople).add(person);

        when(underTest.getPeople()).thenReturn(getPeople);

        //performing the deletion
        underTest.deletePerson(person.getId());
        //checking if
        assertThat(getPeople).isEmpty();

        //when(underTest.deletePerson(5)).thenReturn(person.getId()).thenReturn(null);

        //assertThat(underTest.getPeople()).isEmpty();

        //when(underTest.getPersonById(5)).thenReturn(person).thenReturn(null);

//        int result = underTest.deletePerson(5);
//
//        verify(underTest, times(1)).deletePerson(person.getId());

        //assertThat(result, equalTo(true));
      //  assertThat(result).isNull();

        //Person removed = underTest.getPersonById(5);


    }

    @Test
    void canGetPeopleFromDB() {

        //when
        underTest.getPeople();

        //then
       verify(PersonDAO).getPeople();


    }

    @Test
    void canGetPersonById() {

        //given

        //when
      //  underTest.getPersonById();
        //then


    }


}
