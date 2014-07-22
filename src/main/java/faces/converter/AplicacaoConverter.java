package faces.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import model.entity.Aplicacao;
import model.facade.AplicacaoFacade;
import util.Component;

@FacesConverter(forClass = model.entity.Aplicacao.class)
public class AplicacaoConverter implements Converter {

	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		AplicacaoFacade facade = (AplicacaoFacade) Component.getInstance(AplicacaoFacade.class);
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
		Aplicacao obj = (Aplicacao) arg2;
		return String.valueOf(obj.getId());
	}
}
