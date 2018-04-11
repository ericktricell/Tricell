/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tricell.bean;

import br.com.tricell.dao.UsuarioJpaController;
import br.com.tricell.fabrica.FabricaSessao;
import br.com.tricell.model.Usuario;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Eu
 */
@ManagedBean
@ApplicationScoped
public class LoginBean implements Serializable {

    private Usuario user = new Usuario();
    private String login, senha;
    private List<Usuario> luser;
    private EntityManagerFactory emf;

    public LoginBean() {
        this.emf = new FabricaSessao().getEmf();
    }

    public String valida() throws IOException {

        if (login.equals("admin") && senha.equals("tricell")) {
            return "/soft/inicio?faces-redirect=true";
        } else {
            luser = new UsuarioJpaController(emf).findUsuarioEntities();
            FacesContext context = FacesContext.getCurrentInstance();
            boolean v = false;
            for (Usuario u : luser) {
                if (u.getLogin().equals(login) && u.getSenha().equals(senha)) {
                    this.user = u;
                    v = true;
                    break;
                }
            }

            if (v) {

                new UsuarioBean().setU(user);
                return "/soft/restrito/inicio?faces-redirect=true";
            } else {
                context.addMessage(null, new FacesMessage("Falha", "Login ou senha inválidos"));
                System.out.println("\ndeu errado " + login + " " + senha);
                return null;
            }
        }
    }

    public EntityManagerFactory getEmf() {
        return emf;
    }

    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public String logout() {
        
        return "/soft/login?faces-redirect=true";
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

}
