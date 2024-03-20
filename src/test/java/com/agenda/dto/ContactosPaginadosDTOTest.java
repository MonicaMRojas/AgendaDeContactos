package com.agenda.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.agenda.model.Contacto;

public class ContactosPaginadosDTOTest {

    @Test
    public void testGettersAndSetters() {
        List<Contacto> contactos = new ArrayList<>();
        contactos.add(new Contacto("John", "Doe", "john.doe@example.com"));
        contactos.add(new Contacto("Jane", "Doe", "jane.doe@example.com"));
        ContactosPaginadosDTO dto = new ContactosPaginadosDTO();

        dto.setContactos(contactos);
        dto.setTotalElementos(10);
        dto.setPaginasTotales(5);
        dto.setElementosPagina(20);
        dto.setPaginaActual(2);

        List<Contacto> retrievedContactos = dto.getContactos();
        assertNotNull(retrievedContactos);
        assertEquals(2, retrievedContactos.size());
        assertEquals("John", retrievedContactos.get(0).getNombre());
        assertEquals("Doe", retrievedContactos.get(0).getApellidos());
        assertEquals("john.doe@example.com", retrievedContactos.get(0).getEmail());
        assertEquals("Jane", retrievedContactos.get(1).getNombre());
        assertEquals("Doe", retrievedContactos.get(1).getApellidos());
        assertEquals("jane.doe@example.com", retrievedContactos.get(1).getEmail());

        assertEquals(10, dto.getTotalElementos());
        assertEquals(5, dto.getPaginasTotales());
        assertEquals(20, dto.getElementosPagina());
        assertEquals(2, dto.getPaginaActual());
    }
}

