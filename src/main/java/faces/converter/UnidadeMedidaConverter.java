package faces.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import util.Component;
import model.entity.UnidadeMedida;
import model.facade.UnidadeMedidaFacade;

@FacesConverter(forClass = model.entity.UnidadeMedida.class)
public class UnidadeMedidaConverter implements Converter {

	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		UnidadeMedidaFacade facade = (UnidadeMedidaFacade) Component.getInstance(UnidadeMedidaFacade.class);
		int id;
		try {
			id = Integer.parseInt(arg2);
		} catch (NumberFormatException exception) {
			throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao converter Unidade", "Erro ao converter Unidade"));
		}
		return facade.find(id) ;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		if (arg2 == null) {
			return "";
		}
		UnidadeMedida obj = (UnidadeMedida) arg2;
		return String.valueOf(obj.getId());
	}
}
