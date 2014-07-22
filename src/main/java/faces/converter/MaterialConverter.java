package faces.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import util.Component;
import model.entity.Material;
import model.facade.MaterialFacade;
  
@FacesConverter(forClass = model.entity.Material.class)
public class MaterialConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		
		MaterialFacade materialFacade = (MaterialFacade) Component.getInstance(MaterialFacade.class);
		
		int materialId;

		try {
			materialId = Integer.parseInt(arg2);
		} catch (NumberFormatException exception) {
			throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Type the name of a Dog and select it (or use the dropdow)", "Type the name of a Dog and select it (or use the dropdow)"));
		}

		return materialFacade.find(materialId) ;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {

		if (arg2 == null) {
			return "";
		}
		Material material = (Material) arg2;
		return String.valueOf(material.getId());
	}
}