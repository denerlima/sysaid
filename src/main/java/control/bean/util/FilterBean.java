package control.bean.util;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Locale;

import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;

@Named
@ViewScoped
public class FilterBean implements Serializable {

	private static final long serialVersionUID = 6578803285573958358L;

	public boolean filterContains(Object value, Object filter, Locale locale) {
        if(value != null && filter != null && filter.toString().length() > 0) {
        	return value.toString().toLowerCase().contains(filter.toString().toLowerCase());
        }
		return false;
    }
	
	public boolean filterContainsBigDecimalSimples(Object value, Object filter, Locale locale) {
        if(value != null && filter != null && filter.toString().length() > 0) {
        	return ((BigDecimal) value).toString().replace(".", ",").toLowerCase().contains(filter.toString().toLowerCase());
        }
		return false;
    }
	
	
}
