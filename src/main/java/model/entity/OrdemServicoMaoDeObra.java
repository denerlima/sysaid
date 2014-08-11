package model.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@SequenceGenerator(name = "ordemServicoMaoObraSequence", sequenceName = "MF_OS_MAO_OBRA_ID_SEQ", allocationSize = 1)
@Table(name = "MF_ORDEMSERVICO_MF_MAOOBRA")
public class OrdemServicoMaoDeObra {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ordemServicoMaoObraSequence")
	@Column
	private Integer id;	
	
	@Column
	private int ativo = 1;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_ordem_servico", referencedColumnName="id")
	private OrdemServico ordemServico;
	
	@ManyToOne
	@JoinColumn(name="id_mao_obra", referencedColumnName="id")
	private MaoDeObra maoDeObra;
	
	@Column(name="quantidade", nullable = false)
	private BigDecimal quantidade = new BigDecimal(0);
	
	@Column(name="periodo", nullable = false)
	private BigDecimal periodo = new BigDecimal(0);
	
	@Column(name="unidade_medida")
	private Integer unidadeMedida;
	
	@Column(name="valor_unitario", length = 20, precision = 20, scale= 2 , nullable = false)
	private BigDecimal valorUnitario = new BigDecimal(0);
	
	@Column(name="total", length = 20, precision = 20, scale= 2 , nullable = false)
	private BigDecimal total = new BigDecimal(0);

	/*
	 * Getters and Setters
	 */
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getAtivo() {
		return ativo;
	}

	public void setAtivo(int ativo) {
		this.ativo = ativo;
	}

	public OrdemServico getOrdemServico() {
		return ordemServico;
	}

	public void setOrdemServico(OrdemServico ordemServico) {
		this.ordemServico = ordemServico;
	}

	public MaoDeObra getMaoDeObra() {
		return maoDeObra;
	}

	public void setMaoDeObra(MaoDeObra maoDeObra) {
		this.maoDeObra = maoDeObra;
	}

	public BigDecimal getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getPeriodo() {
		return periodo;
	}

	public void setPeriodo(BigDecimal periodo) {
		this.periodo = periodo;
	}

	public Integer getUnidadeMedida() {
		return unidadeMedida;
	}

	public void setUnidadeMedida(Integer unidadeMedida) {
		this.unidadeMedida = unidadeMedida;
	}
	
	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

}
