package com.wf.f1.wfactura1.controller;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.wf.f1.wfactura1.model.Cliente;
import com.wf.f1.wfactura1.model.Usuario;
import java.util.ArrayList;

/**
 * Clase de tipo EJB que implementa los metodos cargados de la interface de un
 * Cliente
 *
 * @author Ing. Luis Fernando Pedroza Taborda
 * @version 1.0 19-oct-2017
 */
@Stateless
public class ClienteDaoImpl implements ClienteDao {

    /**
     * Atributo que define la unidad de persistencia para conectar la aplicacion
     * a la BD
     */
    @PersistenceContext(unitName = "codicePU")
    EntityManager em;

    /**
     * Metodo para obtener todos los Clientes de la base de datos
     *
     * @return una lista de Clientes
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Cliente> findAllClientes() {
        return em.createNamedQuery("Cliente.findAll").getResultList();
    }

    /**
     * Metodo para traer un Cliente a partir de un identificador
     *
     * @param Cliente a encontrar
     * @return el Cliente encontrado
     */
    @Override
    public Cliente findClienteByIdentifica(Cliente cliente) {
        return em.find(Cliente.class, cliente.getIdentifica());
    }

    /**
     * Metodo para insertar un Cliente a la base de datos
     *
     * @param Cliente a insertar
     */
    @Override
    public void insertCliente(Cliente cliente) {
        em.persist(cliente);

    }

    /**
     * Metodo para actualizar un Cliente a la base de datos
     *
     * @param Cliente a actualizar
     */
    @Override
    public void updateCliente(Cliente cliente) {
        em.merge(cliente);

    }

    /**
     * Metodo para eliminar un Cliente a la base de datos
     *
     * @param Cliente a eliminar
     */
    @Override
    public void deleteCliente(Cliente cliente) {
        cliente = em.find(Cliente.class, cliente.getIdentifica());
        em.remove(cliente);

    }

    /**
     * Metodo para listar todos los clienrs que tengan un determinado status
     * desde la BD
     *
     * @param status estado de actividad del concepto
     * @return una lista de clientes
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Cliente> listarClientesPorStatusYUsuario(boolean status, Usuario usuario) {
        List<Cliente> clientes = null;
        try {
            Query query = em.createQuery("from Cliente c where c.status =:status and c.responsableCreacion =:nombre");
            query.setParameter("status", status);
            query.setParameter("nombre", usuario.getNombre());
            clientes = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clientes;
    }

    @Override
    public Cliente buscarIdentifica(String rfc, String rz, Usuario u) {
        Cliente cliente = null;
        try {
            Query query = em.createQuery("from Cliente c where c.rfc=:rfc and c.razonSocial=:rz and c.responsableCreacion =:u");
            query.setParameter("rfc", rfc);
            query.setParameter("rz", rz);
            query.setParameter("u", u.getNombre());
            List<Cliente> clientes = query.getResultList();
            if (clientes.size() > 0) {
                cliente = clientes.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cliente;
    }
}
