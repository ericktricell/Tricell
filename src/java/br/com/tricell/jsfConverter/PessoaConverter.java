/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tricell.jsfConverter;

import br.com.tricell.dao.PessoaJpaController;
import br.com.tricell.fabrica.FabricaSessao;
import br.com.tricell.model.Pessoa;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Eu
 */
@FacesConverter(value = "PessoaConverter")
public class PessoaConverter implements Converter{

    private PessoaJpaController jpa = new PessoaJpaController(new FabricaSessao().getEmf());
    
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        try{
            return jpa.findPessoa(Long.parseLong(string));
        }catch(Exception e){
            System.out.println("\n\ndado nao encontrado \t" + string + "\n\n");
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        try{
            return String.valueOf(((Pessoa)o).getIdPessoa());
        }catch(Exception e){
            return "";
        }
    }
    
}
