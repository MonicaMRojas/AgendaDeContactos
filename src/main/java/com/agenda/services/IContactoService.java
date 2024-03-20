package com.agenda.services;

import org.springframework.http.ResponseEntity;

import com.agenda.dto.ContactosPaginadosDTO;
import com.agenda.model.Contacto;

/**
 * Interfaz que define las operaciones disponibles para gestionar contactos.
 */
public interface IContactoService {

    /**
     * Obtiene una lista paginada de contactos según el parámetro de orden y la dirección de ordenación.
     *
     * @param parametro Parámetro de búsqueda opcional.
     * @param orderBy Campo por el cual se ordenarán los contactos (ASC o DESC).
     * @return Un objeto ContactosPaginadosDTO que contiene la lista de contactos paginada.
     */
    ContactosPaginadosDTO obtenerContactos(String parametro, String orderBy);

    /**
     * Crea un nuevo contacto o actualiza uno existente si el ID ya existe.
     *
     * @param id El ID del contacto a crear o actualizar.
     * @param contacto El objeto Contacto que contiene los datos del contacto.
     * @return El contacto creado o actualizado.
     */
    Contacto crearOActualizarContacto(Long id, Contacto contacto);

    /**
     * Elimina un contacto existente por su ID.
     *
     * @param id El ID del contacto a eliminar.
     * @return ResponseEntity que indica el resultado de la operación.
     */
    ResponseEntity<Void> eliminarContacto(Long id);
}
