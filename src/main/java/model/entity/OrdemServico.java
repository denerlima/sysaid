package model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "MF_ORDEMSERVICO")
public class OrdemServico extends GenericModelo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column
	private Integer id;	
	
	@Column
	private int ativo = 1;
	
	@Column
	private String justificativa;
	
	@OneToMany(mappedBy="ordemServico", cascade=CascadeType.ALL)
	private List<OrdemServicoMaterial> materiais = new ArrayList<OrdemServicoMaterial>();
	
	@OneToMany(mappedBy="ordemServico", cascade=CascadeType.ALL)
	private List<OrdemServicoMaoDeObra> maosDeObras = new ArrayList<OrdemServicoMaoDeObra>();
	
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

	public String getJustificativa() {
		return justificativa;
	}

	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
	}

	public List<OrdemServicoMaterial> getMateriais() {
		return materiais;
	}

	public void setMateriais(List<OrdemServicoMaterial> materiais) {
		this.materiais = materiais;
	}

	public List<OrdemServicoMaoDeObra> getMaosDeObras() {
		return maosDeObras;
	}

	public void setMaosDeObras(List<OrdemServicoMaoDeObra> maosDeObras) {
		this.maosDeObras = maosDeObras;
	}
		
}
