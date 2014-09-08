package model.facade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import model.entity.Material;
import model.entity.OrdemDeCompraMaterial;
import model.entity.UnidadeMedidaSaida;
import persistence.dao.GenericDAO;
import persistence.dao.MaterialDAO;
import persistence.dao.OrdemServicoDAO;
import persistence.dao.ParametroDAO;
import util.DataUtil;

@Named
public class MaterialFacade extends GenericFacade<Material> implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private MaterialDAO materialDAO;
	
	@Inject
	private ParametroDAO parametroDAO;

	@Inject
	private OrdemServicoDAO ordemServicoDAO;
	
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
	
	/**
	 * Será calculada a média mensal de todas as SAÍDAS de estoque, descontando as DEVOLUÇÕES * Percentual Ajuste no periodo @qtdMeses;
	 * 
	 * @param material
	 * @param qtdMeses Quantidade de meses de movimentação imediatamente anteriores ao mês atual a serem considerados no cálculo do estoque mínimo;
	 * @throws Exception
	 */
	public void atualizaEstoqueMinimoCalculado(Material material) throws Exception {
		BigDecimal mediaMensal = new BigDecimal(1);		
		Integer qtdMeses = parametroDAO.findReferenceOnly(new Integer(1)).getQtdMeses();
		Date dataInicial = new Date();
		Date dataFinal = new Date();		
		
		dataInicial = DataUtil.subtrairDiasAData(qtdMeses * 30);		
		BigDecimal totalSaidas =  ordemServicoDAO.calculaTotalSaidas(material , dataInicial , dataFinal);
		mediaMensal = totalSaidas.divide(new BigDecimal(qtdMeses * 30), 2, RoundingMode.HALF_UP);
		
		material.setEstoqueCalculado(mediaMensal);		
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

	public void atualizaQuantidadeSolicitadaOC(List<OrdemDeCompraMaterial> materiais) {
		try {
			getDAO().beginTransaction();
			for(OrdemDeCompraMaterial ocm : materiais) {
				if(ocm.getOrdemDeCompra() != null && ocm.getMaterial() != null) {
					BigDecimal totalOC =  materialDAO.totalMatOC(ocm.getMaterial());
					BigDecimal totalNF =  materialDAO.totalMatNF(ocm.getMaterial());					
					
					//[QUANTIDADE SOLICITADA]: é a soma das quantidades do material de todas Ordens de Compra menos a soma de todas as NF de COMPRA,
					//ou seja, este campo representa tudo o que foi comprado mas ainda n‹o chegou
					ocm.getMaterial().setQtdSolicitada(totalOC.subtract(totalNF));
					getDAO().update(ocm.getMaterial());
				}
			}
			
			getDAO().commit();
		} catch (Exception e) {
			getDAO().rollback();
			throw new RuntimeException(e);		
		}
	}

}
