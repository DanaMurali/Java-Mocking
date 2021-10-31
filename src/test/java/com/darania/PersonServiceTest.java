package com.darania;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.InstanceOfAssertFactories.OPTIONAL;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
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
        when(PersonDAO.savePerson(person)).thenReturn(1);

        //when
        int result = underTest.savePerson(person);

        //then
        assertThat(result).isEqualTo(1);

        //Captor
        ArgumentCaptor<Person> personArgumentCaptor = ArgumentCaptor.forClass(Person.class);

        verify(PersonDAO).savePerson(personArgumentCaptor.capture());

        Person capturedPerson = personArgumentCaptor.getValue();

        assertThat(capturedPerson).isEqualTo(person);


    }

    @Test
    void itCanDeletePerson() {
    //if person's ID exists then delete person.

        //given
        Person person = new Person(5, "Lily", 27);
        List<Person> people = List.of(person);

        //when
        //teaching our mock what to do by returning the people list
        when(PersonDAO.getPeople()).thenReturn(people);
        when(PersonDAO.deletePerson(5)).thenReturn(1);

        //then
        int result = underTest.deletePerson(5);
        assertThat(result).isEqualTo(1);

        //Captor
        ArgumentCaptor<Integer> captor = ArgumentCaptor.forClass(Integer.class);

        //verifying this method was evoked and capturing it.
        verify(PersonDAO).deletePerson(captor.capture());
        Integer capturedValue = captor.getValue();

        assertThat(capturedValue).isEqualTo(5);

    }

    @Test
    void canGetPeopleFromDB() {

        //when
        underTest.getPeople();

        //then
        verify(PersonDAO).getPeople();


    }

    @Captor
    private ArgumentCaptor<Integer> captorTwo;

    @Test
    void canGetPersonById() {

        //given
        Person person = new Person(5, "Lily", 27);
        List<Person> people = List.of(person);

        //mocking PersonDAO
        when(PersonDAO.getPeople()).thenReturn(people);

        //when - because in PersonDAO get person by ID is listed as optional
        Optional<Person> actual = underTest.getPersonById(5);

        //then
        assertThat(actual).isEqualTo(Optional.of(person));

    }

    @Test
    void willNotSaveWhenPersonHasEmptyFields() {

       /* Person person = new Person(null, "", null);

        when(PersonDAO.savePerson(person)).thenReturn(1);

        underTest.savePerson(person);

        assertThatThrownBy(() -> underTest.savePerson(person))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Person cannot have empty fields");

        verifyNoInteractions(savePerson);*/
    }

    @Test
    void willThrowWhenIdAlreadyExists() {
       /* Person person = new Person(5, "Lily", 27);

        given(underTest.getPersonById(5)).willReturn(false);

        assertThatThrownBy(() -> underTest.getPersonById(5))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("person with id " + person.getId() + " already exists");*/

    }

    @Test
    void willThrowWhenIdDoesNotAlreadyExist() {


    }


}
