package control.bean;

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
	
}