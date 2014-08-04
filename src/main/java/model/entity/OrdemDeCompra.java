package model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@SequenceGenerator(name = "ordemDeCompraSequence", sequenceName = "MF_ORDEMDECOMPRA_ID_SEQ", allocationSize = 1)
@Table(name = "MF_ORDEMDECOMPRA")
public class OrdemDeCompra extends GenericModelo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ordemDeCompraSequence")
	@Column
	private Integer id;	
	
	@Column
	private int ativo = 1;
	
	@Column(length=1000)
	private String justificativa;
	
	@Column
	private Long numeroOC;
	
	@Column
	private Date dataEmissao;
	
	@Column
	private Date dataAutorizacao;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_name_autorizador", referencedColumnName="user_name")
	private Usuario autorizador;
	
	@ManyToOne
	@JoinColumn(name="id_fornecedor", referencedColumnName="id")
	private Fornecedor contratado;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "ordemDeCompra", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<OrdemDeCompraMaterial> materiais = new ArrayList<OrdemDeCompraMaterial>();
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public Usuario getAutorizador() {
		return autorizador;
	}

	public void setAutorizador(Usuario autorizador) {
		this.autorizador = autorizador;
	}

	public Fornecedor getContratado() {
		return contratado;
	}

	public void setContratado(Fornecedor contratado) {
		this.contratado = contratado;
	}

	public List<OrdemDeCompraMaterial> getMateriais() {
		return materiais;
	}

	public void setMateriais(List<OrdemDeCompraMaterial> materiais) {
		this.materiais = materiais;
	}
	
	public int getAtivo() {
		return ativo;
	}

	public void setAtivo(int ativo) {
		this.ativo = ativo;
	}

	public String getJustificativa() {
		return justificativa;
	}

	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		OrdemDeCompra other = (OrdemDeCompra) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
