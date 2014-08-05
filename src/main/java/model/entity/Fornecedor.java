package model.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "COMPANY")
public class Fornecedor extends GenericModelo implements Serializable {
	private static final long serialVersionUID = 1L;

	public Fornecedor() {
		super();
	}

	@Id
	@Column(name="COMPANY_ID", length=11)
	private Integer id;
	
	@Column(name = "COMPANY_NAME")
	private String nome;

	@OneToMany(mappedBy="fornecedor")
	private List<NotaFiscal> notasFiscais;
	
	@OneToMany(mappedBy="contratado")
	private List<OrdemDeCompra> ordensCompra;
	
	/*
	 * Métodos getters and setters
	 */
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public List<NotaFiscal> getNotasFiscais() {
		return notasFiscais;
	}

	public void setNotasFiscais(List<NotaFiscal> notasFiscais) {
		this.notasFiscais = notasFiscais;
	}	

	public List<OrdemDeCompra> getOrdensCompra() {
		return ordensCompra;
	}

	public void setOrdensCompra(List<OrdemDeCompra> ordensCompra) {
		this.ordensCompra = ordensCompra;
	}

}
