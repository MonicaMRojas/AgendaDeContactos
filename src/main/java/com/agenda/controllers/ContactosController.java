package com.agenda.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agenda.dto.ContactosPaginadosDTO;
import com.agenda.model.Contacto;
import com.agenda.services.IContactoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/agenda")
public class ContactosController {
   

   @Autowired
   private IContactoService contactoService;

   /**
    * Obtiene una lista de contactos.
    * @param parametro Campo que indica el parametro por el que se debe ordenar al obtener los contactos.
    * @param orderBy Campo de ordenación opcional.
    * @return ResponseEntity con la lista de contactos y el código de estado correspondiente.
    */
   @GetMapping("/contactos/{parametro}/{orderBy}")
   public ResponseEntity<?> obtenerContactos(@PathVariable String parametro, @PathVariable String orderBy) {
    	ContactosPaginadosDTO contactosPaginados = contactoService.obtenerContactos(parametro, orderBy);
    	if(contactosPaginados == null) {
    		return new ResponseEntity<>(contactosPaginados, HttpStatus.BAD_REQUEST);
    	}
        return new ResponseEntity<>(contactosPaginados, HttpStatus.PARTIAL_CONTENT);
   }

   /**
    * Crea un nuevo contacto.
    * @param id Es el 
    * @param contacto El nuevo contacto a crear.
    * @return ResponseEntity con el contacto creado y el código de estado correspondiente.
    */
   @PostMapping("/contactos/{id}")
   public ResponseEntity<Contacto> crearContacto(@PathVariable Long id, @RequestBody Contacto contacto) {
       Contacto nuevoContacto = contactoService.crearOActualizarContacto(id, contacto);
       if(nuevoContacto == null) {
    	   return new ResponseEntity<>(nuevoContacto, HttpStatus.NOT_FOUND);
       }
       return new ResponseEntity<>(nuevoContacto, HttpStatus.CREATED);
   }

   /**
    * Actualiza los datos de un contacto existente.
    *
    * @param id       El ID del contacto a actualizar.
    * @param contacto Los nuevos datos del contacto.
    * @return ResponseEntity con el contacto actualizado y el código de estado correspondiente.
    */
   @PutMapping("/contactos/{id}")
   public ResponseEntity<Contacto> actualizarContacto(@PathVariable Long id, @Valid @RequestBody Contacto contacto) {
       Contacto contactoActualizado = contactoService.crearOActualizarContacto(id, contacto);
       if(contactoActualizado == null) {
    	   return new ResponseEntity<>(contactoActualizado, HttpStatus.NOT_FOUND);
       }
       return new ResponseEntity<>(contactoActualizado, HttpStatus.OK);
   }

   /**
    * Elimina un contacto existente.
    *
    * @param id El ID del contacto a eliminar.
    * @return ResponseEntity con el código de estado correspondiente.
    */
   @DeleteMapping("/contactos/{id}")
   public ResponseEntity<Void> eliminarContacto(@PathVariable Long id) {
	   ResponseEntity<Void> responseEntity = contactoService.eliminarContacto(id);
       return responseEntity;
   }
}
