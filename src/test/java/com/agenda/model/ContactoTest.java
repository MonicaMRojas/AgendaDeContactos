package com.agenda.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;


public class ContactoTest {


    @Test
    public void testConstructor() {
        Contacto contacto = new Contacto("John", "Doe", "john.doe@example.com");

        assertNotNull(contacto);
        assertNull(contacto.getId());
        assertEquals("John", contacto.getNombre());
        assertEquals("Doe", contacto.getApellidos());
        assertEquals("john.doe@example.com", contacto.getEmail());
    }

    @Test
    public void testSettersAndGetters() {
        Contacto contacto = new Contacto();
        contacto.setId(1L);
        contacto.setNombre("Jane");
        contacto.setApellidos("Doe");
        contacto.setEmail("jane.doe@example.com");

        assertEquals(1L, contacto.getId());
        assertEquals("Jane", contacto.getNombre());
        assertEquals("Doe", contacto.getApellidos());
        assertEquals("jane.doe@example.com", contacto.getEmail());
    }

    @Test
    public void testConstructorWithId() {
        Contacto contacto = new Contacto(1L, "John", "Doe", "john.doe@example.com");

        assertEquals(1L, contacto.getId());
        assertEquals("John", contacto.getNombre());
        assertEquals("Doe", contacto.getApellidos());
        assertEquals("john.doe@example.com", contacto.getEmail());
    }


}
