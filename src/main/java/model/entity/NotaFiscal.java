package model.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@SequenceGenerator(name = "notaFiscalSequence", sequenceName = "NOTAFISCAL_ID_SEQ", allocationSize = 1)
@Table(name = "MF_NOTAFISCAL")
public class NotaFiscal extends GenericModelo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public NotaFiscal() {
		super();
		this.materiais = new ArrayList<NotaFiscalMaterial>();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "notaFiscalSequence")
	@Column
	private Integer id;	
	private int ativo = 1;
	private String justificativa;
	private Long nrNotaFiscal;
	private Date dataEmissao;
	private Date dataEntrega;
	@Column(length = 20, precision = 20, scale= 2 , nullable = false)
	private BigDecimal acrescimos;
	@Column(length = 20, precision = 20, scale= 2 , nullable = false)
	private BigDecimal totalProdutos;
	@Column(length = 20, precision = 20, scale= 2 , nullable = false)
	private BigDecimal totalGeralNota;
		
	@ManyToOne
	@JoinColumn(name="id_fornecedor", referencedColumnName="id")
	private Fornecedor fornecedor;
	
	@OneToMany(mappedBy="notaFiscal", cascade=CascadeType.ALL)
	private List<NotaFiscalMaterial> materiais = new ArrayList<NotaFiscalMaterial>();
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
			
	public Date getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}
	
	public Long getNrNotaFiscal() {
		return nrNotaFiscal;
	}

	public void setNrNotaFiscal(Long nrNotaFiscal) {
		this.nrNotaFiscal = nrNotaFiscal;
	}

	public Date getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public BigDecimal getAcrescimos() {
		return acrescimos;
	}

	public void setAcrescimos(BigDecimal acrescimos) {
		this.acrescimos = acrescimos;
	}

	public BigDecimal getTotalProdutos() {
		return totalProdutos;
	}

	public void setTotalProdutos(BigDecimal totalProdutos) {
		this.totalProdutos = totalProdutos;
	}

	public BigDecimal getTotalGeralNota() {
		return totalGeralNota;
	}

	public void setTotalGeralNota(BigDecimal totalGeralNota) {
		this.totalGeralNota = totalGeralNota;
	}

	public List<NotaFiscalMaterial> getMateriais() {
		return materiais;
	}

	public void setMateriais(List<NotaFiscalMaterial> materiais) {
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

}
