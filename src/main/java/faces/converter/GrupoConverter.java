package faces.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import model.entity.Grupo;
import model.facade.GrupoFacade;
import util.Component;

@FacesConverter(forClass = model.entity.Grupo.class)
public class GrupoConverter implements Converter {

	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		GrupoFacade facade = (GrupoFacade) Component.getInstance(GrupoFacade.class);
		int id;
		try {
			id = Integer.parseInt(arg2);
		} catch (NumberFormatException exception) {
			throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao converter Grupo", "Erro ao converter Grupo"));
		}
		return facade.find(id) ;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		if (arg2 == null) {
			return "";
		}
		Grupo obj = (Grupo) arg2;
		return String.valueOf(obj.getId());
	}
}
