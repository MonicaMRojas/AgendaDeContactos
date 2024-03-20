package com.agenda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.agenda.model.Contacto;

/**
 * Repositorio para manejar operaciones CRUD en la entidad Contacto.
 */
@Repository
public interface ContactoRepository extends JpaRepository<Contacto, Long> {

    /**
     * Obtiene todos los contactos almacenados en la base de datos.
     *
     * @return Lista de todos los contactos ordenados ascendente.
     */
	@Query("SELECT c FROM Contacto c ORDER BY "
		       + "(CASE WHEN :parametro = 'id' THEN c.id ELSE NULL END) ASC, "
		       + "(CASE WHEN :parametro = 'nombre' THEN c.nombre ELSE NULL END) ASC, "
		       + "(CASE WHEN :parametro = 'apellidos' THEN c.apellidos ELSE NULL END) ASC, "
		       + "(CASE WHEN :parametro = 'email' THEN c.email ELSE NULL END) ASC")
	    List<Contacto> findAllOrderedByParamASC(@Param("parametro") String parametro);
	
	/**
     * Obtiene todos los contactos almacenados en la base de datos.
     *
     * @return Lista de todos los contactos ordenados descendente.
     */
	@Query("SELECT c FROM Contacto c ORDER BY "
		       + "(CASE WHEN :parametro = 'id' THEN c.id ELSE NULL END) DESC, "
		       + "(CASE WHEN :parametro = 'nombre' THEN c.nombre ELSE NULL END) DESC, "
		       + "(CASE WHEN :parametro = 'apellidos' THEN c.apellidos ELSE NULL END) DESC, "
		       + "(CASE WHEN :parametro = 'email' THEN c.email ELSE NULL END) DESC")
	    List<Contacto> findAllOrderedByParamDESC(@Param("parametro") String parametro);


}
