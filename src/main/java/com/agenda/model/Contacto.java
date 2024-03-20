package com.agenda.model;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Entidad Contacto
 */
@Entity
public class Contacto {
	
	/**
	 * Identificador único del contacto.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * Nombre del contacto.
	 */
	private String nombre;

	/**
	 * Apellidos del contacto.
	 */
	private String apellidos;

	/**
	 * Correo electrónico del contacto.
	 */
	private String email;

    /**
     * Constructor por defecto.
     */
    public Contacto() {
    }

    /**
     * Constructor con parámetros.
     *
     * @param nombre El nombre del contacto.
     * @param apellidos Los apellidos del contacto.
     * @param email El correo electrónico del contacto.
     */
    public Contacto(String nombre, String apellidos, String email) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
    }

    /**
     * Constructor con todos los parámetros.
     * @param id El id del contacto
     * @param nombre  El nombre del contacto.
     * @param apellidos Los apellidos del contacto.
     * @param email El correo electrónico del contacto.
     */
    public Contacto(Long id, String nombre, String apellidos, String email) {
    	this.id = id;
    	this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
	}

	/**
     * Obtiene el ID del contacto.
     * @return El ID del contacto.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el ID del contacto.
     * @param id El ID del contacto.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del contacto.
     * @return El nombre del contacto.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del contacto.
     * @param nombre El nombre del contacto.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene los apellidos del contacto.
     * @return Los apellidos del contacto.
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * Establece los apellidos del contacto.
     * @param apellidos Los apellidos del contacto.
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * Obtiene el correo electrónico del contacto.
     * @return El correo electrónico del contacto.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Establece el correo electrónico del contacto.
     * @param email El correo electrónico del contacto.
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
