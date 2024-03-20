package com.agenda.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.agenda.dto.ContactosPaginadosDTO;
import com.agenda.model.Contacto;
import com.agenda.repository.ContactoRepository;

@ExtendWith(MockitoExtension.class)
public class ContactoServiceTest {

    @Mock
    private ContactoRepository repository;

    @InjectMocks
    private ContactoService service;

    private Contacto contacto1;
    private Contacto contacto2;

    @BeforeEach
    public void setUp() {
        contacto1 = new Contacto(1L, "John", "Doe", "john@example.com");
        contacto2 = new Contacto(2L, "Jane", "Doe", "jane@example.com");
    }

    @Test
    public void testObtenerContactosOrdenASC() {
        when(repository.findAllOrderedByParamASC(any(String.class))).thenReturn(Arrays.asList(contacto1, contacto2));

        ContactosPaginadosDTO result = service.obtenerContactos("parametro", "ASC");

        assertEquals(2, result.getTotalElementos());
        assertEquals(1, result.getPaginasTotales());
        assertEquals(1, result.getPaginaActual());
        assertEquals(Arrays.asList(contacto1, contacto2), result.getContactos());
    }

    @Test
    public void testObtenerContactosOrdenDESC() {
        when(repository.findAllOrderedByParamDESC(any(String.class))).thenReturn(Arrays.asList(contacto2, contacto1));

        ContactosPaginadosDTO result = service.obtenerContactos("parametro", "DESC");

        assertEquals(2, result.getTotalElementos());
        assertEquals(1, result.getPaginasTotales());
        assertEquals(1, result.getPaginaActual());
        assertEquals(Arrays.asList(contacto2, contacto1), result.getContactos());
    }




    @Test
    public void testCrearContacto() {
        Long id = 1L;
        Contacto contacto = new Contacto("Nombre", "Apellidos", "correo@ejemplo.com");

        when(repository.findById(id)).thenReturn(Optional.empty());
        when(repository.save(contacto)).thenReturn(contacto);

        Contacto resultado = service.crearOActualizarContacto(id, contacto);

        assertEquals(contacto, resultado);
        verify(repository, times(1)).findById(id);
        verify(repository, times(1)).save(contacto);
    }

    @Test
    public void testActualizarContacto() {
        Long id = 1L;
        Contacto contacto = new Contacto("NombreNuevo", "ApellidosNuevo", "correo_nuevo@ejemplo.com");

        when(repository.findById(id)).thenReturn(Optional.of(contacto1));
        when(repository.save(contacto1)).thenReturn(contacto1);

        Contacto resultado = service.crearOActualizarContacto(id, contacto);

        assertEquals(contacto1, resultado);
        assertEquals(contacto.getNombre(), contacto1.getNombre());
        assertEquals(contacto.getApellidos(), contacto1.getApellidos());
        assertEquals(contacto.getEmail(), contacto1.getEmail());
        verify(repository, times(1)).findById(id);
        verify(repository, times(1)).save(contacto1);
    }

  
    @Test
    void testEliminarContacto_Existente() {
        doNothing().when(repository).deleteById(1L);

        ResponseEntity<Void> resultado = service.eliminarContacto(1L);

        assertNotNull(resultado);
        assertEquals(HttpStatus.OK, resultado.getStatusCode());
    }
    
    
    
    //TestKO
    
    
    @Test
    public void testObtenerContactos_EmptyList() {
        when(repository.findAllOrderedByParamASC(anyString())).thenReturn(Collections.emptyList());

        ContactosPaginadosDTO resultado = service.obtenerContactos("parametro", "ASC");

        assertNull(resultado);
    }
    
    @Test
    public void testEliminarContacto_NotFound() {
        Long id = 1L;
        doThrow(EmptyResultDataAccessException.class).when(repository).deleteById(id);

        ResponseEntity<Void> resultado = service.eliminarContacto(id);

        assertEquals(HttpStatus.NOT_FOUND, resultado.getStatusCode());
        verify(repository, times(1)).deleteById(id);
    }



}
