package control.bean;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.entity.Log;
import model.entity.Usuario;
import model.facade.LogFacade;
import model.facade.UsuarioFacade;

import org.apache.commons.codec.binary.Base64;
import org.primefaces.context.RequestContext;

import util.JSFMessageUtil;

public class AbstractBean {
	
	private Usuario user;
	
	@Inject
	private UsuarioFacade usuarioFacade;

	@Inject
	private LogFacade logFacade;
	
	public AbstractBean() {
		super();
	}

	protected void displayInfoMessageToUser(String message) {
		JSFMessageUtil messageUtil = new JSFMessageUtil();
		messageUtil.sendInfoMessageToUser(message);
	}
	
	protected void displayWarnMessageToUser(String message) {
		JSFMessageUtil messageUtil = new JSFMessageUtil();
		messageUtil.sendWarnMessageToUser(message);
	}
	
	protected void displayErrorMessageToUser(String message) {
		JSFMessageUtil messageUtil = new JSFMessageUtil();
		messageUtil.sendErrorMessageToUser(message);
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
	
	public void gravarCookie(String username) {
		try {
			username = Base64.encodeBase64String(username.getBytes());
			FacesContext context = FacesContext.getCurrentInstance();
			//Cookie ck = new Cookie("communityUserName", "BASE64c3lzdGVt");
			Cookie ck = new Cookie("communityUserName", "BASE64"+username);
			ck.setMaxAge(-1); // tempo de vida
			((HttpServletResponse) context.getExternalContext().getResponse()).addCookie(ck);
			System.out.println("Cookie salvo...");
		} catch (Exception x) {
			x.printStackTrace();
		}
	}
	
	public void removerCookie() {
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
			Cookie[] cookies = request.getCookies();
			for (Cookie cookie : cookies) {
				if (cookie.getName().trim().equalsIgnoreCase("communityUserName")) {
					Cookie ck = new Cookie("communityUserName", "xx");
					ck.setMaxAge(0); // tempo de vida
					((HttpServletResponse) context.getExternalContext().getResponse()).addCookie(ck);
				}
			}
		} catch (Exception x) {
			x.printStackTrace();
		}
	}

	public  Usuario getUsuarioLogadoCookie() {
		try {
			if(user != null) {
				return user;
			}
			FacesContext facesContext = FacesContext.getCurrentInstance();
			HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();	
			Cookie[] cookies = request.getCookies();
			user = null;
			for (Cookie cookie : cookies) {
				if (cookie.getName().trim().equalsIgnoreCase("communityUserName")) {	
					if(cookie.getValue().length() > 6) {
						String decoded = new String(Base64.decodeBase64(cookie.getValue().substring(6)));
						System.out.println("Encontrou o cookie communityUserName. Valor: " + cookie.getValue() + " Valor Real: " + decoded);
						user = usuarioFacade.find(decoded);					
						return user;
					}
				}
				if (cookie.getName().trim().equalsIgnoreCase("sysaidcookie")) {
					System.out.println("Encontrou o cookie sysaidcookie. Valor: " + cookie.getValue());
					user = usuarioFacade.find(cookie.getValue());					
					return user;
				}
			}
		} catch (Exception e) {
			System.out.println("Erro ao caputurar o usuário pelo cookie. Erro: " + e.getMessage());
			e.printStackTrace();
		}
		System.out.println("Não encontrou os cookie communityUserName ou sysaidcookie");
		return null;
	}
	
	public void verificarUsuarioLogado() {
		if(getUsuarioLogadoCookie() == null) {
			throw new RuntimeException("É necessário está logado.");
		}
	}
	
	public void appendLog(String acao, Integer identificador, String entidade, String descricao) {
		try {
			Log l = new Log(getUsuarioLogadoCookie().getUserName(), acao, identificador, entidade, descricao);
			logFacade.create(l);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
