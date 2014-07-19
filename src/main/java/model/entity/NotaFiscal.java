package model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@SequenceGenerator(name = "notaFiscalSequence", sequenceName = "NOTAFISCAL_ID_SEQ", allocationSize = 1)
@Table(name = "MF_NOTAFISCAL")
@Where(clause = "ativo = '1'")  
@SQLDelete(sql = "UPDATE sysaid_java.MF_NOTAFISCAL SET ativo  = 0 WHERE id = ?")

public class NotaFiscal extends GenericModelo implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public NotaFiscal() {
		super();
		this.notas = new ArrayList<Material>();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "notaFiscalSequence")
	@Column
	private int id;	
	private int ativo = 1;
	private Long numeroOC;
	private Date dataEmissao;
	private Date dataAutorizacao;
	private String autorizador;
	private String contratado;
	
	@ManyToMany
	private List<Material> notas;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
			
	public Long getNumeroOC() {
		return numeroOC;
	}

	public void setNumeroOC(Long numeroOC) {
		this.numeroOC = numeroOC;
	}

	public Date getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public Date getDataAutorizacao() {
		return dataAutorizacao;
	}

	public void setDataAutorizacao(Date dataAutorizacao) {
		this.dataAutorizacao = dataAutorizacao;
	}

	public String getAutorizador() {
		return autorizador;
	}

	public void setAutorizador(String autorizador) {
		this.autorizador = autorizador;
	}

	public String getContratado() {
		return contratado;
	}

	public void setContratado(String contratado) {
		this.contratado = contratado;
	}

	public List<Material> getMateriais() {
		return notas;
	}

	public void setMateriais(List<Material> notas) {
		this.notas = notas;
	}
	
	public int getAtivo() {
		return ativo;
	}

	public void setAtivo(int ativo) {
		this.ativo = ativo;
	}

	@Override
	public int hashCode() {
		return id;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof NotaFiscal) {
			NotaFiscal notaFiscal = (NotaFiscal) obj;
			return notaFiscal.getId() == id;
		}

		return false;
	}
	

}