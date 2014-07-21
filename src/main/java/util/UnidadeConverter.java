package util;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import model.entity.UnidadeEntrada;
import model.facade.UnidadeEntradaFacade;

@FacesConverter(forClass = model.entity.UnidadeEntrada.class)
public class UnidadeConverter implements Converter {

	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		UnidadeEntradaFacade facade = (UnidadeEntradaFacade) Component.getInstance(UnidadeEntradaFacade.class);
		int id;
		try {
			id = Integer.parseInt(arg2);
		} catch (NumberFormatException exception) {
			throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Type the name of a Dog and select it (or use the dropdow)", "Type the name of a Dog and select it (or use the dropdow)"));
		}
		return facade.find(id) ;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		if (arg2 == null) {
			return "";
		}
		UnidadeEntrada obj = (UnidadeEntrada) arg2;
		return String.valueOf(obj.getId());
	}
}
