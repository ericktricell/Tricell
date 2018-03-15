/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tricell.bean;

import br.com.tricell.dao.PessoaJpaController;
import br.com.tricell.fabrica.FabricaSessao;
import br.com.tricell.model.Pessoa;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManagerFactory;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Eu
 */
@ManagedBean
@SessionScoped
public class SelecaoPessoa {
    
    private List<Pessoa> lsPes;
    private EntityManagerFactory emf;
    private String nome;
    private Pessoa p = new Pessoa();

    public void abrirDialogo(){
        Map<String,Object> opcoes = new HashMap<>();
        opcoes.put("modal", true);
        opcoes.put("resizable", false);
        opcoes.put("contentHeight", 370);
        
        RequestContext.getCurrentInstance().openDialog("buscaClientes", opcoes, null);
    }
    
    public void selecionar(Pessoa pes){
        RequestContext.getCurrentInstance().closeDialog(pes);
    }
    
    public void pesquisar(){
        emf = new FabricaSessao().getEmf();
        lsPes = new PessoaJpaController(emf).findPessoa(nome);
    }

    public List<Pessoa> getLsPes() {
        return lsPes;
    }

    public void setLsPes(List<Pessoa> lsPes) {
        this.lsPes = lsPes;
    }

    public EntityManagerFactory getEmf() {
        return emf;
    }

    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
}
