package com.agenda.dto;

import java.util.List;

import com.agenda.model.Contacto;

/**
 * DTO que representa la respuesta de la API para obtener un listado de contactos.
 */
public class ContactosPaginadosDTO {

    /**
     * Lista de contactos obtenidos.
     */
    private List<Contacto> contactos;

    /**
     * Total de contactos en la lista.
     */
    private int totalElementos;

    /**
     * Número total de páginas.
     */
    private int paginasTotales;

    /**
     * Número de elementos por página.
     */
    private int elementosPagina;

    /**
     * Página actual.
     */
    private int paginaActual;

    /**
     * Obtiene la lista de contactos obtenidos.
     *
     * @return Lista de contactos.
     */
    public List<Contacto> getContactos() {
        return contactos;
    }

    /**
     * Establece la lista de contactos obtenidos.
     *
     * @param contactos Lista de contactos.
     */
    public void setContactos(List<Contacto> contactos) {
        this.contactos = contactos;
    }

    /**
     * Obtiene el total de contactos en la lista.
     *
     * @return Total de contactos.
     */
    public int getTotalElementos() {
        return totalElementos;
    }

    /**
     * Establece el total de contactos en la lista.
     *
     * @param totalElementos Total de contactos.
     */
    public void setTotalElementos(int totalElementos) {
        this.totalElementos = totalElementos;
    }

    /**
     * Obtiene el número total de páginas.
     *
     * @return Número total de páginas.
     */
    public int getPaginasTotales() {
        return paginasTotales;
    }

    /**
     * Establece el número total de páginas.
     *
     * @param paginasTotales Número total de páginas.
     */
    public void setPaginasTotales(int paginasTotales) {
        this.paginasTotales = paginasTotales;
    }

    /**
     * Obtiene el número de elementos por página.
     *
     * @return Número de elementos por página.
     */
    public int getElementosPagina() {
        return elementosPagina;
    }

    /**
     * Establece el número de elementos por página.
     *
     * @param elementosPagina Número de elementos por página.
     */
    public void setElementosPagina(int elementosPagina) {
        this.elementosPagina = elementosPagina;
    }

    /**
     * Obtiene la página actual.
     *
     * @return Página actual.
     */
    public int getPaginaActual() {
        return paginaActual;
    }

    /**
     * Establece la página actual.
     *
     * @param paginaActual Página actual.
     */
    public void setPaginaActual(int paginaActual) {
        this.paginaActual = paginaActual;
    }
}
