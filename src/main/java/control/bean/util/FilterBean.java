package control.bean.util;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;

import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;

import util.DataUtil;

@Named
@ViewScoped
public class FilterBean implements Serializable {

	private static final long serialVersionUID = 6578803285573958358L;

	public boolean filterContains(Object value, Object filter, Locale locale) {
		String filterText = (filter == null) ? null : filter.toString().trim();
	    if (filterText == null || filterText.equals("")) {
	        return true;
	    }
	    if (value == null) {
	        return false;
	    }
		return value.toString().toLowerCase().contains(filterText.toLowerCase());
    }
	
	public boolean filterContainsBigDecimalSimples(Object value, Object filter, Locale locale) {
		String filterText = (filter == null) ? null : filter.toString().trim();
	    if (filterText == null || filterText.equals("")) {
	        return true;
	    }
	    if (value == null) {
	        return false;
	    }
	    return ((BigDecimal) value).toString().replace(".", ",").toLowerCase().contains(filter.toString().toLowerCase());
    }
	
	public boolean filterContainsBigDecimal(Object value, Object filter, Locale locale) {
		String filterText = (filter == null) ? null : filter.toString().trim();
	    if (filterText == null || filterText.equals("")) {
	        return true;
	    }
	    if (value == null) {
	        return false;
	    }
	    final NumberFormat nf = NumberFormat.getInstance(new Locale("pt", "BR"));
		nf.setMinimumFractionDigits(2);
		nf.setMaximumFractionDigits(2);
		String valor = nf.format(Double.valueOf(value.toString()));
	    return valor.toLowerCase().contains(filter.toString().toLowerCase());
    }
	
	public boolean filterContainsDate(Object value, Object filter, Locale locale) {
		String filterText = (filter == null) ? null : filter.toString().trim();
	    if (filterText == null || filterText.equals("")) {
	        return true;
	    }
	    if (value == null) {
	        return false;
	    }
	    return DataUtil.converterDateParaString((Date)value).contains(filter.toString().toLowerCase());
    }
	
}
