package com.wf.f1.wfactura1.controller;

import com.wf.f1.wfactura1.model.Cliente;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.wf.f1.wfactura1.model.Factura;
import com.wf.f1.wfactura1.model.Usuario;
import java.util.ArrayList;

/**
 * Clase de tipo EJB que implementa los metodos cargados de la interface de un
 * Factura
 *
 * @author Ing. Luis Fernando Pedroza Taborda
 * @version 1.0 19-oct-2017
 */
@Stateless
public class FacturaDaoImpl implements FacturaDao {

    /**
     * Atributo que define la unidad de persistencia para conectar la aplicacion
     * a la BD
     */
    @PersistenceContext(unitName = "codicePU")
    EntityManager em;

    /**
     * Metodo para obtener todos los Facturas de la base de datos
     *
     * @return una lista de Facturas
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Factura> findAllFacturas() {
        return em.createNamedQuery("Factura.findAll").getResultList();
    }

    /**
     * Metodo para traer una Factura a partir de un identificador
     *
     * @param Factura a encontrar
     * @return el Factura encontrado
     */
    @Override
    public Factura findFacturaByIdentifica(Factura factura) {
        return em.find(Factura.class, factura.getIdentifica());
    }

    /**
     * Metodo para insertar una Factura a la base de datos
     *
     * @param Factura a insertar
     */
    @Override
    public void insertFactura(Factura factura) {
        em.persist(factura);

    }

    /**
     * Metodo para actualizar una Factura a la base de datos
     *
     * @param Factura a actualizar
     */
    @Override
    public void updateFactura(Factura factura) {
        em.merge(factura);

    }

    /**
     * Metodo para eliminar una factura de la base de datos
     *
     * @param factura a eliminar
     */
    @Override
    public void deleteFactura(Factura factura) {
        factura = em.find(Factura.class, factura.getIdentifica());
        em.remove(factura);

    }

    /**
     * Metodo para listar todos las facturas que tengan un determinado estado de
     * pago desde la BD
     *
     * @param estadoPago estado de pago de la factura
     * @param usuario usuario que tiene la sesion
     * @return una lista de facturas
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Factura> listarFacturasPorEstadoPagoYUsuario(String estado, Usuario usuario) {
        List<Factura> facturas = null;
        try {
            String queryStr = "from Factura f where f.tipoComprobante != 'P' and f.estado =:estado and f.responsableCreacion =:nombre order by f.fechaCreacion desc";
            if(estado.equals("FACTURA CANCELADA")){
                queryStr = "from Factura f where f.estado =:estado and f.responsableCreacion =:nombre order by f.fechaCreacion desc";
            }
            Query query = em.createQuery(queryStr);
            query.setParameter("estado", estado);
            query.setParameter("nombre", usuario.getNombre());
            facturas = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return facturas;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Factura> listarFacturasPorEstadoPagoYUsuarioPagos(String estadoPago, Usuario usuario) {
        List<Factura> facturas = null;
        try {
            Query query = em.createQuery("from Factura f where f.tipoComprobante = 'P' and f.estado =:estado and f.responsableCreacion =:nombre order by f.fechaCreacion desc");
            query.setParameter("estado", estadoPago);
            query.setParameter("nombre", usuario.getNombre());
            facturas = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return facturas;
    }

    @Override
    public Factura encontrarFacturaPorUuid(String uuid) {
        Factura factura = null;
        try {
            Query query = em.createQuery("from Factura f where f.uuid ='"+uuid+"'");
            //query.setParameter("uuid", uuid);
            factura = (Factura) query.getSingleResult();
        } catch (Exception e) {
            factura = null;
            e.printStackTrace();
        }
        return factura;
    }
    
    @Override
    public List<Factura> listarFacturasPorEstadoUsuarioPagosRFC(String estadoPago, Usuario usuario,Cliente identifica, String uuid) {
        List<Factura> facturas = null;
        try {
            Query query = em.createQuery("from Factura f where f.tipoComprobante = 'I' and f.estado =:estado and f.responsableCreacion =:nombre and f.uuid not in('"+uuid+"')  order by f.fechaCreacion desc");
            query.setParameter("estado", estadoPago);
            query.setParameter("nombre", usuario.getNombre());
            //query.setParameter("identifica", identifica);
            facturas = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return facturas;
    }
    
    @Override
    public Factura consultaPagos(String uuid) {
        Factura factura = null;
        try {
            Query query = em.createQuery("from Factura f where f.uuid='"+uuid+"' order by f.fechaCreacion desc");
           List<Factura> lfacturas = query.getResultList();
           factura=lfacturas.get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return factura;
    }
    
    @Override
    public List<Factura> buscarFacturas (Usuario usuario){
        List<Factura> lista= null;
        try {
            Query query = em.createQuery("from Factura f where f.responsableCreacion=:nombre and f.totalImpuestoRetenidos=0.0000 and f.uuid is not null order by f.fechaCreacion desc");
            query.setParameter("nombre", usuario.getNombre());
           lista = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return lista;
        
    }
    
    @Override
    public List<Factura> buscarcanceladas (Usuario usuario){
        List<Factura> lista= null;
        try {
            Query query = em.createQuery("from Factura f where f.responsableCreacion=:nombre and f.estado=:estado and f.uuid is not null order by f.fechaCreacion desc");
            query.setParameter("nombre", usuario.getNombre());
            query.setParameter("estado", "FACTURA CANCELADA");
           lista = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return lista;
        
    }
    
    @Override
        public List<Factura> petcanceladas (Usuario usuario){
        List<Factura> lista= null;
        try {
            Query query = em.createQuery("from Factura f where f.responsableCreacion=:nombre and f.estado=:estado and f.uuid is not null order by f.fechaCreacion desc");
            query.setParameter("nombre", usuario.getNombre());
            query.setParameter("estado", "FACTURA CREADA EXITOSAMENTE");
           lista = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return lista;
        
    }
        
    @Override
    public List<Factura> buscarRetenciones (Usuario usuario){
        List<Factura> lista= null;
        try {
            Query query = em.createQuery("from Factura f where f.responsableCreacion=:nombre and f.totalImpuestoRetenidos>0.0000 and f.uuid is not null order by f.fechaCreacion desc");
            query.setParameter("nombre", usuario.getNombre());
            //query.setParameter("totret", "0.0000");
           lista = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return lista;
        
    }
}
