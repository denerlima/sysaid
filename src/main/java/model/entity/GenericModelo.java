package model.entity;

import java.io.Serializable;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;

public class GenericModelo implements Serializable {

	private static final long serialVersionUID = 1L;

	public String toJson() {
		XStream xstream = new XStream(new JettisonMappedXmlDriver());
        xstream.setMode(XStream.XPATH_RELATIVE_REFERENCES);
        xstream.alias(this.getClass().getName(), this.getClass());
        return xstream.toXML(this);
	}
	
}
