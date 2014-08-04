package faces.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import model.entity.Usuario;
import model.facade.UsuarioFacade;
import util.Component;

@FacesConverter(forClass = model.entity.Usuario.class)
public class UsuarioConverter implements Converter {
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		UsuarioFacade facade = (UsuarioFacade) Component.getInstance(UsuarioFacade.class);
		if(arg2 == null || arg2.trim().length() == 0) {
			return null;
		}
		return facade.find(arg2);
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		if (arg2 == null) {
			return "";
		}
		Usuario obj = (Usuario) arg2;
		return String.valueOf(obj.getUserName());
	}
}
