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

	public String getNomeCompleto() {
		return (nome + " " + sobrenome);
	}
	
	public List<Inventario> getInventarios() {
		return inventarios;
	}

	public void setInventarios(List<Inventario> inventarios) {
		this.inventarios = inventarios;
	}

}