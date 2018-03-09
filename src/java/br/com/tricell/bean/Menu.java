/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tricell.bean;

import java.io.IOException;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Eu
 */
@ManagedBean(name = "menu")
@ViewScoped
public class Menu implements Serializable{
    
    public void resumo() throws IOException{
        FacesContext.getCurrentInstance().getExternalContext().redirect("inicio.jsf");
    }
    
    public void usuarios() throws IOException{
        FacesContext.getCurrentInstance().getExternalContext().redirect("usuarios.jsf");
    }
    
    public void movimentos() throws IOException{
        FacesContext.getCurrentInstance().getExternalContext().redirect("movimentos.jsf");
    }
    
    public void empresas() throws IOException{
        FacesContext.getCurrentInstance().getExternalContext().redirect("empresas.jsf");
    }
    
    public void contas() throws IOException{
        FacesContext.getCurrentInstance().getExternalContext().redirect("contas.jsf");
    }
    
    public void pessoas() throws IOException{
        FacesContext.getCurrentInstance().getExternalContext().redirect("pessoas.jsf");
    }
    
    public void depositos() throws IOException{
        FacesContext.getCurrentInstance().getExternalContext().redirect("deposito.jsf");
    }
    
}
