package control.bean;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.context.RequestContext;

import util.JSFMessageUtil;

public class AbstractBean {

	public AbstractBean() {
		super();
	}

	protected void displayErrorMessageToUser(String message) {
		JSFMessageUtil messageUtil = new JSFMessageUtil();
		messageUtil.sendErrorMessageToUser(message);
	}
	
	protected void displayInfoMessageToUser(String message) {
		JSFMessageUtil messageUtil = new JSFMessageUtil();
		messageUtil.sendInfoMessageToUser(message);
	}
	
	protected RequestContext getRequestContext(){
		return RequestContext.getCurrentInstance();
	}
	
	public void geraExtractCSV(String output, String nomeArquivo) throws IOException {
		InputStream inputStream = null;
		OutputStream outputStream = null;
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		char[] chCsvData = output.toCharArray();
		response.setHeader("Content-Disposition","attachment;filename="+nomeArquivo+".csv");
		response.setContentType("application/ms-excel");
		response.setIntHeader("Content-length", chCsvData.length);
		inputStream = new ByteArrayInputStream(output.getBytes("ISO-8859-1"));
		outputStream = response.getOutputStream();
		byte buffer[] = new byte[1024 * 1024];
		int value;
		while ((value = inputStream.read(buffer)) != -1){
			outputStream.write(buffer, 0, value);
		}
		FacesContext.getCurrentInstance().responseComplete();
	}
	
}