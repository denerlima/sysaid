package control.bean;

import java.io.Serializable;
import java.util.Date;

import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;

@Named
@ViewScoped
public class HomeBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4691727887817841891L;

	public String getDataAtual() {
		return new Date().toString();
	}
	
}
