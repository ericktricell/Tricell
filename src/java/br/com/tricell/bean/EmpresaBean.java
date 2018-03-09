/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tricell.bean;

import br.com.tricell.dao.EmpresaJpaController;
import br.com.tricell.model.Empresa;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Eu
 */
@ManagedBean
@SessionScoped
public class EmpresaBean implements Serializable{
    
    private EntityManagerFactory emf;
    private Empresa emp = new Empresa();
    private List<Empresa> listemp;

    public EmpresaBean() {
        //emf = new FabricaSessao().getEmf();
    }

    public void pegaEmpresa(){
        listemp = new EmpresaJpaController(emf).findEmpresaEntities();
    }
    
    public void create(){
        new EmpresaJpaController(emf).create(emp);
        emp = new Empresa();
        this.pegaEmpresa();
    }

    public EntityManagerFactory getEmf() {
        return emf;
    }

    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public Empresa getEmp() {
        return emp;
    }

    public void setEmp(Empresa emp) {
        this.emp = emp;
    }

    public List<Empresa> getListemp() {
        return listemp;
    }

    public void setListemp(List<Empresa> listemp) {
        this.listemp = listemp;
    }
    
    
}
