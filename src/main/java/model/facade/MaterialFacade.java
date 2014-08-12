package model.facade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import model.entity.Material;
import model.entity.UnidadeMedidaSaida;
import persistence.dao.GenericDAO;
import persistence.dao.MaterialDAO;

@Named
public class MaterialFacade extends GenericFacade<Material> implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private MaterialDAO materialDAO;

	public Material findMaterialbyNomeMaterial(String nomeMaterial) {
		//materialDAO.beginTransaction();
		Material material = materialDAO.findMaterialByMaterial(nomeMaterial);
        //materialDAO.closeTransaction();
		return material;
    }
	
	public List<Material> findMateriaisByFilter(Material material) {
		//materialDAO.beginTransaction();
		//List<Material> result = materialDAO.findMateriaisByFilter(material);
		List<Material> result = materialDAO.findAll();
		//materialDAO.closeTransaction();
		return result;
	}
	
	public List<Material> pesquisarListaMateriaisbyNomeMaterial(String nomeMaterial) {		
		List<Material> result = materialDAO.findListMaterialByMaterial(nomeMaterial);        
		return result;
    }
	
	public void atualizaEstoqueMinimoCalculado(Material material , Integer qtdeMeses) throws Exception {
		BigDecimal mediaMensal = new BigDecimal(30);		
		// Será calculada a média mensal de todas as SAÍDAS de estoque, descontando as DEVOLUÇÕES;
		// @qtdeMeses Quantidade de meses de movimentação imediatamente anteriores ao mês atual a serem considerados no cálculo do estoque mínimo; 
		// Verificar o SUM da Coluna
		// mediaMensal = materialDAO.findMediaMaterial(material , qtdeMeses);
		
		//Adicionar uma dataAtual na MF_ORDEMSERVICO_MF_MATERIAL
		material.setEstoqueCalculado(mediaMensal);		
		update(material);
    }
	
	public void retirarEstoque(Material material, BigDecimal quantidade, UnidadeMedidaSaida unidadeMedidaSaida) {
		//materialDAO.beginTransaction();
		BigDecimal novaQuantidade = quantidade;
		if(material.getUnidadeMedida().getUnidadeEntrada()
					.getId().intValue() != unidadeMedidaSaida.getUnidade().getId().intValue()) {
			novaQuantidade = quantidade.divide(unidadeMedidaSaida.getFatorConversao());
		}
		material.setEstoque(material.getEstoque().subtract(novaQuantidade));
		//materialDAO.commit();
	}
	
	public void adicionarEstoque(Material material, BigDecimal quantidade, UnidadeMedidaSaida unidadeMedidaSaida) {
		//materialDAO.beginTransaction();
		BigDecimal novaQuantidade = quantidade;
		if(material.getUnidadeMedida().getUnidadeEntrada()
				.getId().intValue() != unidadeMedidaSaida.getUnidade().getId().intValue()) {
			novaQuantidade = quantidade.divide(unidadeMedidaSaida.getFatorConversao());
		}
		material.setEstoque(material.getEstoque().add(novaQuantidade));
		//materialDAO.commit();
	}
	
	@Override
	public GenericDAO<Material> getDAO() {
		return materialDAO;
	}

	public List<Material> pesquisarMaterialByFilter(Material material) {
		List<Material> result = materialDAO.listMateriais(material);        
		return result;
		
	}

}
