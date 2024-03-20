package com.agenda.controllers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.agenda.dto.ContactosPaginadosDTO;
import com.agenda.model.Contacto;
import com.agenda.services.IContactoService;

@ExtendWith(MockitoExtension.class)
public class ContactosControllerTest {

    @Mock
    private IContactoService contactoService;

    @InjectMocks
    private ContactosController controller;

    private List<Contacto> contactos;
    private ContactosPaginadosDTO contactosPaginados;

    @BeforeEach
    public void setUp() {
        Contacto contacto1 = new Contacto(1L, "Juan", "Perez", "juan@example.com");
        Contacto contacto2 = new Contacto(2L, "Maria", "Gomez", "maria@example.com");
        contactos = Arrays.asList(contacto1, contacto2);

        contactosPaginados = new ContactosPaginadosDTO();
        contactosPaginados.setContactos(contactos);
        contactosPaginados.setPaginaActual(1);
        contactosPaginados.setPaginasTotales(1);
    }
    
    //Test Ok

    @Test
    public void testObtenerContactos() {
        when(contactoService.obtenerContactos("nombre", "asc")).thenReturn(contactosPaginados);

        ResponseEntity<?> response = controller.obtenerContactos("nombre", "asc");

        assertEquals(HttpStatus.PARTIAL_CONTENT, response.getStatusCode());
        assertEquals(contactosPaginados, response.getBody());
    }

    @Test
    public void testCrearContacto() {
        Contacto nuevoContacto = new Contacto(3L, "Pedro", "Martinez", "pedro@example.com");
        when(contactoService.crearOActualizarContacto(3L, nuevoContacto)).thenReturn(nuevoContacto);

        ResponseEntity<Contacto> response = controller.crearContacto(3L, nuevoContacto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(nuevoContacto, response.getBody());
    }

    @Test
    public void testActualizarContacto() {
        Contacto contactoActualizado = new Contacto(1L, "Juan", "Perez", "juan_updated@example.com");
        when(contactoService.crearOActualizarContacto(1L, contactoActualizado)).thenReturn(contactoActualizado);

        ResponseEntity<Contacto> response = controller.actualizarContacto(1L, contactoActualizado);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(contactoActualizado, response.getBody());
    }

    @Test
    public void testEliminarContacto() {
    	when(contactoService.eliminarContacto(1L)).thenReturn(new ResponseEntity<>(HttpStatus.OK));
        ResponseEntity<Void> response = controller.eliminarContacto(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(contactoService, times(1)).eliminarContacto(1L);
    }
    
    //Test KO
    
    @Test
    public void obtenerContactos_ParametrosInvalidos_Error400() {
        String parametro = "invalido";
        String orderBy = "asc";

        ResponseEntity<?> responseEntity = controller.obtenerContactos(parametro, orderBy);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void crearContacto_IdInvalido_Error404() {
        Long id = 0L;
        Contacto contacto = new Contacto("John", "Doe", "john.doe@example.com");

        ResponseEntity<Contacto> responseEntity = controller.crearContacto(id, contacto);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
    }

    @Test
    public void crearContacto_ContactoNulo_Error404() {
        Long id = 1L;

        ResponseEntity<Contacto> responseEntity = controller.crearContacto(id, null);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
    }

    @Test
    public void actualizarContacto_IdInvalido_Error404() {
        Long id = 0L;
        Contacto contacto = new Contacto("John", "Doe", "john.doe@example.com");

        ResponseEntity<Contacto> responseEntity = controller.actualizarContacto(id, contacto);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
    }

    @Test
    public void actualizarContacto_ContactoNulo_Error404() {
        Long id = 1L;

        ResponseEntity<Contacto> responseEntity = controller.actualizarContacto(id, null);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
    }

    @Test
    public void eliminarContacto_IdInvalido_Error404() {
        Long id = 0L;
        when(contactoService.eliminarContacto(id)).thenReturn(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        ResponseEntity<Void> responseEntity = controller.eliminarContacto(id);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

}
