package faces.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import model.entity.Marca;
import model.facade.MarcaFacade;
import util.Component;

@FacesConverter(forClass = model.entity.Marca.class)
public class MarcaConverter implements Converter {

	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		MarcaFacade facade = (MarcaFacade) Component.getInstance(MarcaFacade.class);
		int id;
		try {
			id = Integer.parseInt(arg2);
		} catch (NumberFormatException exception) {
			throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao converter Marca", "Erro ao converter Marca"));
		}
		return facade.find(id) ;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		if (arg2 == null) {
			return "";
		}
		Marca obj = (Marca) arg2;
		return String.valueOf(obj.getId());
	}
}
