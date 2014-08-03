package faces.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import util.Component;
import model.entity.MaoDeObra;
import model.facade.MaoDeObraFacade;

@FacesConverter(forClass = model.entity.MaoDeObra.class)
public class MaoDeObraConverter implements Converter {
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		
		if (arg2 == null || arg2.equals("null")) {
			return null;
		}
		
		MaoDeObraFacade facade = (MaoDeObraFacade) Component.getInstance(MaoDeObraFacade.class);
		int id;
		try {
			id = Integer.parseInt(arg2);
		} catch (NumberFormatException exception) {
			throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao converter M�o de Obra", "Erro ao converter M�o de Obra"));
		}
		return facade.find(id) ;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		if (arg2 == null) {
			return "";
		}
		MaoDeObra obj = (MaoDeObra) arg2;
		return String.valueOf(obj.getId());
	}
}
