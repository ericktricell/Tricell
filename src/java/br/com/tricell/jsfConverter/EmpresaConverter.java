/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tricell.jsfConverter;

import br.com.tricell.dao.EmpresaJpaController;
import br.com.tricell.fabrica.FabricaSessao;
import br.com.tricell.model.Empresa;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Eu
 */
@FacesConverter(value = "EmpresaConverter")
public class EmpresaConverter implements Converter{

    EmpresaJpaController ejc = new EmpresaJpaController(new FabricaSessao().getEmf());
    
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        try{
            return ejc.findEmpresa(Long.parseLong(string));
        }catch(Exception e){
            System.out.println("\n\ndado nao encontrado\t" + string + "\n\n");
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        try{
            return String.valueOf(((Empresa)o).getIdempresa());
        }catch(Exception e){
            return "";
        }
    }
    
}
