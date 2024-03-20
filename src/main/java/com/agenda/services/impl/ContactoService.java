package com.agenda.services.impl;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.agenda.dto.ContactosPaginadosDTO;
import com.agenda.model.Contacto;
import com.agenda.repository.ContactoRepository;
import com.agenda.services.IContactoService;

/**
 * Implementación del servicio para la gestión de contactos.
 */
@Service("contactoService")
public class ContactoService implements IContactoService {

    @Autowired
    private ContactoRepository repository;
    
    private static final Integer TOTAL_ELEMENTOS_PAGINA = 5; 

    /**
     * Obtiene todos los contactos almacenados en la base de datos.
     *
     * @param orderBy Campo por el que se ordenarán los contactos.
     *                Si se proporciona un valor válido, se ordenará por ese campo.
     *                Si no se proporciona, se devolverán los contactos sin orden específico.
     * @return Lista de todos los contactos.
     */
    @Override
    public ContactosPaginadosDTO obtenerContactos(String parametro, String orderBy) {
    	List<Contacto> contactos = null;
    	switch(orderBy.toUpperCase()) {
	    	case "ASC":
	    		contactos = repository.findAllOrderedByParamASC(parametro);
	    		break;
	    	case "DESC":
	    		contactos = repository.findAllOrderedByParamDESC(parametro);
	    		break;
    	}
    	if(CollectionUtils.isEmpty(contactos)) {
    		return null;
    	}
        int totalElementos = contactos.size();
        int paginasTotales = calcularPaginasTotales(totalElementos);
        int paginaActual = 1; // Se asume que es la primera página

        List<Contacto> contactosPaginaActual = obtenerContactosPagina(contactos, paginaActual);

        ContactosPaginadosDTO contactosPaginados = new ContactosPaginadosDTO();
        contactosPaginados.setContactos(contactosPaginaActual);
        contactosPaginados.setTotalElementos(totalElementos);
        contactosPaginados.setPaginasTotales(paginasTotales);
        contactosPaginados.setElementosPagina(TOTAL_ELEMENTOS_PAGINA);
        contactosPaginados.setPaginaActual(paginaActual);

        return contactosPaginados;
    }

    /**
     * Calcula el número total de páginas en función del total de elementos.
     * 
     * @param totalElementos    Total de elementos.
     * @return                  Número total de páginas.
     */
    private int calcularPaginasTotales(int totalElementos) {
        return (int) Math.ceil((double) totalElementos / TOTAL_ELEMENTOS_PAGINA);
    }

    /**
     * Obtiene una lista de contactos correspondiente a una página específica.
     * 
     * @param contactos         Lista completa de contactos.
     * @param paginaActual      Número de página actual.
     * @return                   Lista de contactos correspondiente a la página especificada.
     */
    private List<Contacto> obtenerContactosPagina(List<Contacto> contactos, int paginaActual) {
        int indiceInicial = (paginaActual - 1) * TOTAL_ELEMENTOS_PAGINA;
        int indiceFinal = Math.min(indiceInicial + TOTAL_ELEMENTOS_PAGINA, contactos.size());
        return contactos.subList(indiceInicial, indiceFinal);
    }


   

    /**
     * Actualiza los datos de un contacto existente en la base de datos.
     *
     * @param id       El ID del contacto a actualizar.
     * @param contacto Los nuevos datos del contacto.
     * @return El contacto actualizado.
     */
    @Override
    public Contacto crearOActualizarContacto(Long id, Contacto contacto) {
        Optional<Contacto> optionalContacto = repository.findById(id);
        if (optionalContacto.isEmpty()) {
        	return repository.save(contacto);
        }         
        Contacto contactoExistente = optionalContacto.get();
        contactoExistente.setNombre(contacto.getNombre());
        contactoExistente.setApellidos(contacto.getApellidos());
        contactoExistente.setEmail(contacto.getEmail());
        return repository.save(contactoExistente);
    }

    /**
     * Elimina un contacto existente de la base de datos por su ID.
     *
     * @param id El ID del contacto a eliminar.
     */
    @Override
    public ResponseEntity<Void> eliminarContacto(Long id) {
    	try {
            repository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}

