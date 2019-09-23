package com.wf.f1.wfactura1.controller;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.wf.f1.wfactura1.model.Usuario;

/**
 * Clase de tipo EJB que implementa los metodos cargados de la interface de un
 * usuario
 *
 * @author Ing. Luis Fernando Pedroza Taborda
 * @version 1.0 19-oct-2017
 */
@Stateless
public class UsuarioDaoImpl implements UsuarioDao {

    /**
     * Atributo que define la unidad de persistencia para conectar la aplicacion
     * a la BD
     */
    @PersistenceContext(unitName = "codicePU")
    EntityManager em;

    /**
     * Metodo para obtener todos los usuarios de la base de datos
     *
     * @return una lista de usuarios
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Usuario> findAllUsuarios() {
        return em.createNamedQuery("Usuario.findAll").getResultList();
    }

    /**
     * Metodo para traer un usuario a partir de un identificador
     *
     * @param usuario a encontrar
     * @return el usuario encontrado
     */
    @Override
    public Usuario findUsuarioByIdentifica(Usuario usuario) {
        return em.find(Usuario.class, usuario.getIdentifica());
    }

    /**
     * Metodo para insertar un usuario a la base de datos
     *
     * @param usuario a insertar
     */
    @Override
    public void insertUsuario(Usuario usuario) {
        em.persist(usuario);

    }

    /**
     * Metodo para actualizar un usuario a la base de datos
     *
     * @param usuario a actualizar
     */
    @Override
    public void updateUsuario(Usuario usuario) {
        em.merge(usuario);

    }

    /**
     * Metodo para eliminar un usuario a la base de datos
     *
     * @param usuario a eliminar
     */
    @Override
    public void deleteUsuario(Usuario usuario) {
        usuario = em.find(Usuario.class, usuario.getIdentifica());
        em.remove(usuario);

    }

    /**
     * Metodo para obtener un usuario en la base de datos a partir de su nombre
     * y password
     *
     * @param nombreUsuario
     * @param password
     * @return el usuario encontrado
     */
    @Override
    public Usuario encontrarUsuarioPorNombreYPassword(Usuario usuario) {
        Usuario usuarioElegido = null;
        try {
            Query query = em.createQuery("from Usuario u join u.sucursal s where u.nombre =:nombre and u.password =:password");
            query.setParameter("nombre", usuario.getNombre());
            query.setParameter("password", usuario.getPassword());
            usuarioElegido = (Usuario) query.getSingleResult();
            System.out.println("Usuario " + usuarioElegido.getNombreCompleto());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usuarioElegido;
    }

    /**
     * Metodo para listar todos los usuarios que tengan un determinado status
     * desde la BD
     *
     * @param status estado de actividad del Usuario
     * @return una lista de Usuarios
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Usuario> listarUsuariosPorStatusYUsuario(boolean status, Usuario usuario) {
        List<Usuario> usuarios = null;
        try {
            Query query = em.createQuery("from Usuario u where u.status =:status and u.responsableCreacion =:nombre");
            query.setParameter("status", status);
            query.setParameter("nombre", usuario.getNombre());
            usuarios = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    @Override
    public Usuario buscarUsuarioYContrasena(String nombre, String contrasena) {
        System.out.println("buscando datos "+nombre+"   "+ contrasena);
        Usuario usuario = new Usuario();
        try {
            Query query = em.createQuery("from Usuario u where u.nombre=:nombre and u.password=:contrasena");
            query.setParameter("nombre", nombre);
            query.setParameter("contrasena", contrasena);
            List<Usuario> usuarios = query.getResultList();
            if(usuarios.size()>0){
                System.out.println("tamaño de lista "+usuarios.size());
                usuario=usuarios.get(0);
            }
        } catch (Exception e) {
            usuario = null;
            e.printStackTrace();
        }
        return usuario;
    }
}
