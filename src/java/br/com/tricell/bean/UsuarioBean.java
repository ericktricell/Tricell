/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tricell.bean;

import br.com.tricell.dao.EmpresaJpaController;
import br.com.tricell.dao.UsuarioJpaController;
import br.com.tricell.model.Empresa;
import br.com.tricell.model.Usuario;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Eu
 */
@ManagedBean
@ApplicationScoped
public class UsuarioBean implements Serializable{
    
    private Usuario u = new Usuario();
    private List<Usuario> listU;
    private EntityManagerFactory emf;
    private List<Empresa> listemp = new ArrayList<>();

    public UsuarioBean(){
//        emf = new FabricaSessao().getEmf();
    }
    
    public void salvar() throws IOException{
        new UsuarioJpaController(emf).create(u);
        new Menu().usuarios();
        
    }
    
    public void limpaCampos(){
        u = new Usuario();
        u.setAtivo(false);
        u.setBairro("");
        u.setCep("");
        u.setCidade("");
        u.setEmail("");
        u.setEndereco("");
        u.setIdusuario(Long.parseLong("0"));
        u.setLogin("");
        u.setNome("");
        u.setSenha("");
        u.setTelefone("");
        u.setTelefone1("");
    }
    
    public void listaTodos(){
        listU = new UsuarioJpaController(emf).findUsuarioEntities();
        listemp = new EmpresaJpaController(emf).findEmpresaEntities();
    }

    public EntityManagerFactory getEmf() {
        return emf;
    }

    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public List<Empresa> getListemp() {
        return listemp;
    }

    public void setListemp(List<Empresa> listemp) {
        this.listemp = listemp;
    }
    
    public Usuario getU() {
        return u;
    }

    public void setU(Usuario u) {
        this.u = u;
    }

    public List<Usuario> getListU() {
        return listU;
    }

    public void setListU(List<Usuario> listU) {
        this.listU = listU;
    }
    
    
}
