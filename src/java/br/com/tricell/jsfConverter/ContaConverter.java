/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tricell.jsfConverter;

import br.com.tricell.dao.ContaJpaController;
import br.com.tricell.fabrica.FabricaSessao;
import br.com.tricell.model.Conta;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Eu
 */
@FacesConverter(value = "ContaConverter")
public class ContaConverter implements Converter{

    ContaJpaController cjc = new ContaJpaController(new FabricaSessao().getEmf());
    
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        try{
            return cjc.findContaNum(string);
        }catch(Exception e){
            //System.out.println("\n\ndado nao encontrado \t" + string + "\n\n");
            //e.printStackTrace();
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        try{
            return String.valueOf(((Conta)o).getNumconta());
        }catch(Exception e){
            return "";
        }
    }
    
}
