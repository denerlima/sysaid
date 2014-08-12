package model.facade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import model.entity.Material;
import persistence.dao.GenericDAO;
import persistence.dao.MaterialDAO;
import persistence.dao.NotaFiscalDAO;
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
	private NotaFiscalDAO notaFiscalDAO;
	
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
	public void atualizaEstoqueMinimoCalculado(Material material ) throws Exception {
		BigDecimal mediaMensal = new BigDecimal(30);		
		Integer qtdMeses = parametroDAO.find(new Integer(1)).getQtdMeses();
		Date dataInicial = new Date();
		Date dataFinal = new Date();
		
		dataFinal = DataUtil.subtrairDiasAData(qtdMeses * 30);
		// Será calculada a média mensal de todas as SAÍDAS de estoque, descontando as DEVOLUÇÕES * Percentual Ajuste no periodo de meses do @qtdMeses;		 
		// Verificar o SUM da Coluna
		
		BigDecimal totalSaidas =  notaFiscalDAO.calculaTotalSaidas(material , dataInicial , dataFinal);
		
		//Adicionar uma dataAtual na MF_ORDEMSERVICO_MF_MATERIAL
		material.setEstoqueCalculado(mediaMensal);		
		update(material);
    }
	
	public void retirarEstoque(Material material, BigDecimal quantidade) {
		//materialDAO.beginTransaction();
		material.setEstoque(material.getEstoque().subtract(quantidade));
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
