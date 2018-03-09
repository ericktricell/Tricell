/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tricell.jsfConverter;

import br.com.tricell.dao.UsuarioJpaController;
import br.com.tricell.fabrica.FabricaSessao;
import br.com.tricell.model.Usuario;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Eu
 */
@FacesConverter(value = "UsuarioConverter")
public class UsuarioConverter implements Converter{

    UsuarioJpaController jpa = new UsuarioJpaController(new FabricaSessao().getEmf());
    
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        try{
            return jpa.findUsuario(Long.parseLong(string));
        }catch(Exception e){
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        try{
            return String.valueOf(((Usuario)o).getNome());
        }catch(Exception e){
            return "";
        }
    }
    
}
