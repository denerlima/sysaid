package faces.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import util.Component;
import model.entity.Fornecedor;
import model.facade.FornecedorFacade;

@FacesConverter(forClass = model.entity.Fornecedor.class)
public class FornecedorConverter implements Converter {
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		FornecedorFacade facade = (FornecedorFacade) Component.getInstance(FornecedorFacade.class);
		int id;
		try {
			id = Integer.parseInt(arg2);
		} catch (NumberFormatException exception) {
			throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao converter Fornecedor", "Erro ao converter Fornecedor"));
		}
		return facade.find(id) ;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		if (arg2 == null) {
			return "";
		}
		Fornecedor obj = (Fornecedor) arg2;
		return String.valueOf(obj.getId());
	}
}
