package model.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "SYSAID_USER")
public class Usuario extends GenericModelo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="USER_NAME")
	private String userName;
	
	@Column(name = "FIRST_NAME")
	private String nome;
	
	@Column(name = "LAST_NAME")
	private String sobrenome;

	@Column(name = "CALCULATED_USER_NAME")
	private String userNameCalculado;
	
	@OneToMany(mappedBy="atendente")
	private List<Inventario> inventarios;
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	
	public String getUserNameCalculado() {
		return userNameCalculado;
	}

	public void setUserNameCalculado(String userNameCalculado) {
		this.userNameCalculado = userNameCalculado;
	}

	public List<Inventario> getInventarios() {
		return inventarios;
	}

	public void setInventarios(List<Inventario> inventarios) {
		this.inventarios = inventarios;
	}

	/* 
	 * Regras
	 */
	
	public String getNomeCompleto() {
		return (nome + " " + sobrenome);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result
				+ ((userName == null) ? 0 : userName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}

}
