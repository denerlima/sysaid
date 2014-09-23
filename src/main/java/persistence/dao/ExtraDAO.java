package persistence.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import util.DataUtil;
import model.entity.vo.CustoPorEnderecoFilterVO;
import model.entity.vo.CustoPorEnderecoVO;
import model.entity.vo.CustoPorUnidadeFilterVO;
import model.entity.vo.CustoPorUnidadeVO;
import model.entity.vo.ItemVO;

@Named
public class ExtraDAO implements Serializable {

	private static final long serialVersionUID = -2560853681488349891L;
	
	@Inject
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<ItemVO> consultarDemandantes() {
		ArrayList<ItemVO> lista = new ArrayList<ItemVO>();
 		String sql = "Select su.CALCULATED_USER_NAME, su.USER_NAME FROM SYSAID_USER su";
		Query query = em.createNativeQuery(sql);
		List<Object[]> retorno = query.getResultList();
		for(Object[] obj : retorno) {
			lista.add(new ItemVO((String) obj[0], (String) obj[0]));
		}
		return lista;
	}
	
	@SuppressWarnings("unchecked")
	public List<ItemVO> consultarAgrupadores() {
		ArrayList<ItemVO> lista = new ArrayList<ItemVO>();
 		String sql = "Select eh.VALUE_KEY, eh.AGRUPADOR FROM MF_ENDERECO_HIERARQUIA eh";
		Query query = em.createNativeQuery(sql);
		List<Object[]> retorno = query.getResultList();
		for(Object[] obj : retorno) {
			lista.add(new ItemVO((String) obj[1], (String) obj[1]));
		}
		return lista;
	}
	
	@SuppressWarnings("unchecked")
	public List<ItemVO> consultarEnderecos() {
		ArrayList<ItemVO> lista = new ArrayList<ItemVO>();
 		String sql = "Select cv.VALUE_CAPTION, cv.LIST_NAME FROM CUST_VALUES cv WHERE LIST_NAME = 'location'";
		Query query = em.createNativeQuery(sql);
		List<Object[]> retorno = query.getResultList();
		for(Object[] obj : retorno) {
			lista.add(new ItemVO((String) obj[0], (String) obj[0]));
		}
		return lista;
	}
	
	@SuppressWarnings("unchecked")
	public List<ItemVO> consultarNiveis(Integer nivel, String valorNivel) {
		ArrayList<ItemVO> lista = new ArrayList<ItemVO>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT COD_ESTRUTURAL, NOM_UNID_PR FROM MF_TAB0025_NIVEL"+nivel);
		sql.append(" WHERE cod_estrutural like '"+substituirNivel(nivel, valorNivel)+"'");
 		sql.append(" ORDER BY COD_ESTRUTURAL");
		Query query = em.createNativeQuery(sql.toString());
		List<Object[]> retorno = query.getResultList();
		for(Object[] obj : retorno) {
			lista.add(new ItemVO((String) obj[0], (String) obj[0]));
		}
		return lista;
	}
	
	private String substituirNivel(Integer nivel, String valorNivel) {
		String[] niveis = valorNivel.split("\\.");
		niveis[nivel-1] = "__";
		String retorno = Arrays.asList(niveis).toString(); 
		retorno = retorno.replace(", ", ".");
		return retorno.substring(1, retorno.length()-1);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<CustoPorEnderecoVO> consultarCustoPorEndereco(CustoPorEnderecoFilterVO filterVO) {
		ArrayList<CustoPorEnderecoVO> lista = new ArrayList<CustoPorEnderecoVO>();
		StringBuffer sql = new StringBuffer();
		sql.append(
	 		"SELECT "+
	 		"	EH.AGRUPADOR AS AGRUPADOR, "+
	 		"	CV.VALUE_CAPTION AS ENDERECO_ATENDIMENTO, "+
	 		"	OM.ID_ORDEM_SERVICO AS OS, "+
	 		"	SR.SR_CUST_NUMBERMAINOSI AS OS_PAI, "+
	 		"	TO_CHAR(SR.insert_time, 'DD/MM/YYYY') AS DATA, "+
	 		"	SU.CALCULATED_USER_NAME, "+
	 		"	SR.SR_CUST_TXTCOMPLEMENTO AS COMP_END, "+
	 		"	SUM((OM.PRECO_UNITARIO * OM.QUANTIDADE_UTILIZADA)) AS MATERIAL, "+
	 		"	SUM(OMO.TOTAL) AS MAO_OBRA, "+
	 		"	SUM(((OM.PRECO_UNITARIO * OM.QUANTIDADE_UTILIZADA) + OMO.TOTAL)) AS TOTAL "+
	 		"FROM mf_ordemservico_mf_material OM "+
		 	"	INNER JOIN mf_ordemservico O "+
		 	"	  ON om.id_ordem_servico = O.ID "+
		 	"	INNER JOIN mf_ordemservico_mf_maoobra OMO "+
		 	"	  ON om.id_ordem_servico = OMO.id_ordem_servico "+
		 	"	INNER JOIN SERVICE_REQ SR "+
		 	"	  ON om.id_ordem_servico = SR.ID "+
		 	"	INNER JOIN SYSAID_USER SU "+
		 	"	  ON SR.REQUEST_USER = SU.USER_NAME "+
		 	"	INNER JOIN CUST_VALUES CV "+
		 	"	  ON SR.LOCATION  = CV.VALUE_KEY "+
		 	"	  AND CV.LIST_NAME = 'location' "+
		 	"	INNER JOIN mf_endereco_hierarquia EH "+
		 	"	  ON EH.VALUE_KEY  = CV.VALUE_KEY " +
		 	"WHERE O.ATIVO = 1 "
	 	);	 	
		if (filterVO.getEmissaoInicio() != null) {
			sql.append(" AND SR.insert_time >= to_date('"+ DataUtil.formataData(filterVO.getEmissaoInicio())+ "','dd/MM/yy')");
		} 
		if (filterVO.getEmissaoFim() != null) {
			sql.append(" AND SR.insert_time <= to_date('"+ DataUtil.formataData(filterVO.getEmissaoFim())+ "','dd/MM/yy')");
		}
		if (filterVO.getDemandante() != null) {
			sql.append(" AND SU.CALCULATED_USER_NAME = '"+filterVO.getDemandante()+"'");
		}
		if (filterVO.getAgrupador() != null) {
			sql.append(" AND EH.AGRUPADOR = '"+filterVO.getAgrupador()+"'");
		}
		if (filterVO.getEndereco() != null) {
			sql.append(" AND UPPER(CV.VALUE_CAPTION) = UPPER('"+filterVO.getEndereco()+"')");
		}
		if (filterVO.getComplementoEndereco() != null && filterVO.getComplementoEndereco().trim().length() > 0) {
			sql.append(" AND SR.SR_CUST_TXTCOMPLEMENTO LIKE '%"+filterVO.getComplementoEndereco()+"%'");
		}
		sql.append(
	 		"GROUP BY SR.SR_CUST_TXTCOMPLEMENTO, SU.CALCULATED_USER_NAME, EH.AGRUPADOR, CV.VALUE_CAPTION ,  OM.ID_ORDEM_SERVICO , SR.SR_CUST_NUMBERMAINOSI, SR.insert_time "+
	 		"ORDER BY SR.SR_CUST_TXTCOMPLEMENTO, SU.CALCULATED_USER_NAME, EH.AGRUPADOR, CV.VALUE_CAPTION ,  OM.ID_ORDEM_SERVICO ");
 		
		Query query = em.createNativeQuery(sql.toString());
		List<Object[]> retorno = query.getResultList();
		for(Object[] obj : retorno) {
			CustoPorEnderecoVO custoPorEndereco = new CustoPorEnderecoVO();
			custoPorEndereco.setAgrupador((String) obj[0]);
			custoPorEndereco.setEndereco((String) obj[1]);
			custoPorEndereco.setOrdemServico((BigDecimal) obj[2]);
			custoPorEndereco.setSubOs((BigDecimal) obj[3]);
			custoPorEndereco.setData((String) obj[4]);
			custoPorEndereco.setDemandante((String) obj[5]);
			custoPorEndereco.setComplementoEndereco((String) obj[6]);
			custoPorEndereco.setCustoMaterial((BigDecimal) obj[7]);
			custoPorEndereco.setCustoMaoDeObra((BigDecimal) obj[8]);
			custoPorEndereco.setCustoTotal((BigDecimal) obj[9]);
			lista.add(custoPorEndereco);
		}
		return lista;
	}
	
	@SuppressWarnings("unchecked")
	public List<CustoPorUnidadeVO> consultarCustoPorUnidade(CustoPorUnidadeFilterVO filterVO) {
		ArrayList<CustoPorUnidadeVO> lista = new ArrayList<CustoPorUnidadeVO>();
		StringBuffer sql = new StringBuffer();
		sql.append(
	 		"SELECT "+
	 		"	CPU.UNIDADE_SIGLA, "+
	 		"	CPU.UNIDADE_NOME, "+
	 		"	CPU.VALOR_MATERIAL, "+
	 		"	CPU.VALOR_MAO_OBRA, "+
	 		"	CPU.VALOR_TOTAL, "+
	 		"	CPU.OS_PRINCIPAL, "+
	 		"	CPU.SUB_OS, "+
	 		"	CPU.DATA, "+
	 		"	CPU.DEMANDANTE "+
	 		"FROM MF_CUSTO_POR_UNIDADE CPU "+
		 	"WHERE 1 = 1 "
	 	);	
		if (filterVO.getEmissaoInicio() != null) {
			sql.append(" AND CPU.DATA >= to_date('"+ DataUtil.formataData(filterVO.getEmissaoInicio())+ "','dd/MM/yy')");
		} 
		if (filterVO.getEmissaoFim() != null) {
			sql.append(" AND CPU.DATA <= to_date('"+ DataUtil.formataData(filterVO.getEmissaoFim())+ "','dd/MM/yy')");
		}
 		sql.append(clausulaNivel(filterVO));
		Query query = em.createNativeQuery(sql.toString());
		List<Object[]> retorno = query.getResultList();
		for(Object[] obj : retorno) {
			CustoPorUnidadeVO custoPorUnidade = new CustoPorUnidadeVO();
			custoPorUnidade.setSigla((String) obj[0]);
			custoPorUnidade.setNomeUnidade((String) obj[1]);
			custoPorUnidade.setCustoMaterial((BigDecimal) obj[2]);
			custoPorUnidade.setCustoMaoDeObra((BigDecimal) obj[3]);
			custoPorUnidade.setCustoTotal((BigDecimal) obj[4]);
			custoPorUnidade.setOsPrincipal((BigDecimal) obj[5]);
			custoPorUnidade.setOrdemServico((BigDecimal) obj[6]);
			custoPorUnidade.setData((Date) obj[7]);
			custoPorUnidade.setDemandante((String) obj[8]);
			lista.add(custoPorUnidade);
		}
		return lista;
	}
	
	public String clausulaNivel(CustoPorUnidadeFilterVO filterVO) {
		String nivel = null;
		if(filterVO.getNivel8() != null) {
			nivel = substituirNivelRecursivo(8, filterVO.getNivel8()); 
		}
		else if(filterVO.getNivel7() != null) {
			nivel = substituirNivelRecursivo(7, filterVO.getNivel7()); 
		}
		else if(filterVO.getNivel6() != null) {
			nivel = substituirNivelRecursivo(6, filterVO.getNivel6()); 
		}
		else if(filterVO.getNivel5() != null) {
			nivel = substituirNivelRecursivo(5, filterVO.getNivel5()); 
		}
		else if(filterVO.getNivel4() != null) {
			nivel = substituirNivelRecursivo(4, filterVO.getNivel4()); 
		}
		else if(filterVO.getNivel3() != null) {
			nivel = substituirNivelRecursivo(3, filterVO.getNivel3()); 
		}
		else if(filterVO.getNivel2() != null) {
			nivel = substituirNivelRecursivo(2, filterVO.getNivel2()); 
		}
		else if(filterVO.getNivel1() != null) {
			nivel = substituirNivelRecursivo(1, filterVO.getNivel1()); 
		}
		if(nivel != null) {
			return " AND CPU.COD_ESTRUTURAL = '"+nivel+"'";
		} else {
			return "";
		}
	}
	
	private String substituirNivelRecursivo(Integer nivel, String valorNivel) {
		String[] niveis = valorNivel.split("\\.");
		while(nivel < niveis.length) {
			niveis[nivel] = "__";
			nivel++;
		}
		String retorno = Arrays.asList(niveis).toString(); 
		retorno = retorno.replace(", ", ".");
		return retorno.substring(1, retorno.length()-1);
	}
	
}
