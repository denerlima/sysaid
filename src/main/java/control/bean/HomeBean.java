package control.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import model.entity.Usuario;
import model.facade.UsuarioFacade;

import org.omnifaces.cdi.ViewScoped;

@Named
@ViewScoped
public class HomeBean extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 4691727887817841891L;
		
	@Inject
	private UsuarioFacade usuarioFacade;
	
	private List<Usuario> usuarios;
	private Usuario usuario;
	private boolean iniciado;
	
	public void initHome() {
		if(!iniciado) {
			usuarios = usuarioFacade.listAll();
			usuario = getUsuarioLogadoCookie();
			iniciado = true;
		}
	}

	public void loginUser() {
		gravarCookie(usuario.getUserName());
		displayErrorMessageToUser("Cookie gravado com sucesso!");
	}
	
	public void logoutUser() {
		removerCookie();
		usuario = null;
		displayErrorMessageToUser("Cookie Removido com sucesso!");
	}
	
	public String getDataAtual() {
		return new Date().toString();
	}
	
	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public boolean isLogado() {
		return usuario != null;
	}

}
