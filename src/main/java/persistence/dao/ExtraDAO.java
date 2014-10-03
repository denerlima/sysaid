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
import javax.persistence.NoResultException;
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
	public List<ItemVO> consultarDemandantesCustoPorUnidade() {
		ArrayList<ItemVO> lista = new ArrayList<ItemVO>();
 		String sql = "SELECT DISTINCT DEMANDANTE_ID, DEMANDANTE FROM MF_CUSTO_POR_UNIDADE ORDER BY DEMANDANTE ASC";
		Query query = em.createNativeQuery(sql);
		List<Object[]> retorno = query.getResultList();
		for(Object[] obj : retorno) {
			lista.add(new ItemVO(obj[0].toString(), (String) obj[1]));
		}
		return lista;
	}
	
	public ItemVO capturarDemandanteCustoPorUnidade(String demandanteId) {
		String sql = "SELECT DISTINCT DEMANDANTE_ID, DEMANDANTE FROM MF_CUSTO_POR_UNIDADE WHERE DEMANDANTE_ID = "+demandanteId;
		Query query = em.createNativeQuery(sql);
		try {
			Object[] obj = (Object[]) query.getSingleResult();
			return new ItemVO(obj[0].toString(), (String) obj[1]);
		} catch (NoResultException e) {
			return null;
		}
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
		sql.append("SELECT COD_ESTRUTURAL, NOM_UNID_PR_ABREV FROM MF_TAB0025_NIVEL"+nivel);
		sql.append(" WHERE cod_estrutural like '"+substituirNivel(nivel, valorNivel)+"'");
 		sql.append(" ORDER BY COD_ESTRUTURAL");
		Query query = em.createNativeQuery(sql.toString());
		List<Object[]> retorno = query.getResultList();
		for(Object[] obj : retorno) {
			//lista.add(new ItemVO((String) obj[0], (String) obj[1]));
			lista.add(new ItemVO((String) obj[0], (String) obj[0] + " - "+ (String) obj[1]));
		}
		return lista;
	}
	
	public ItemVO capturarNivel(Integer nivel, String valorNivel) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT DISTINCT COD_ESTRUTURAL, NOM_UNID_PR_ABREV FROM MF_TAB0025_NIVEL"+nivel);
		sql.append(" WHERE cod_estrutural = '"+valorNivel+"'");
 		sql.append(" ORDER BY COD_ESTRUTURAL");
		Query query = em.createNativeQuery(sql.toString());
		try {
			Object[] obj = (Object[]) query.getSingleResult();
			return new ItemVO((String) obj[0], (String) obj[1]);
		} catch (NoResultException e) {
			return null;
		}
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
		
		if(filterVO.isImprimirOrdemServico()) {
			sql.append(
		 		"SELECT "+
		 		"	CPU.COD_ESTRUTURAL,"+
		 		"	CPU.UNIDADE_SIGLA, "+
		 		"	CPU.UNIDADE_NOME, "+
		 		"	CPU.VALOR_MATERIAL, "+
		 		"	CPU.VALOR_MAO_OBRA, "+
		 		"	CPU.VALOR_TOTAL, "+
		 		"	CPU.OS_PRINCIPAL, "+
		 		"	CPU.SUB_OS, "+
		 		"	CPU.DATA, "+
		 		"	CPU.DEMANDANTE "+
		 		"FROM MF_CUSTO_POR_UNIDADE CPU "
		 	);
		} else {
			sql.append(
		 		"SELECT "+
		 		"   CPU.COD_ESTRUTURAL, "+
		 		"	CPU.UNIDADE_SIGLA, "+
		 		"	CPU.UNIDADE_NOME, "+
		 		"	SUM(CPU.VALOR_MATERIAL), "+
		 		"	SUM(CPU.VALOR_MAO_OBRA), "+
		 		"	SUM(CPU.VALOR_TOTAL) "+
		 		"FROM MF_CUSTO_POR_UNIDADE CPU "
		 	);
		}
		
		if(!filterVO.isImprimirOrdemServico()) {
			Integer nivel = getNivel(filterVO);
			if(nivel == 0) {
				sql.append(
					" inner join mf_tab0025_nivel2 N on " +
					" N.COD_ESTRUTURAL = concat(substr(CPU.COD_ESTRUTURAL,1,3), '00.00.00.00.00.00.00') ");
			} else {
				sql.append(
					" inner join mf_tab0025_nivel"+(nivel+1)+" N on " +
					" N.COD_ESTRUTURAL = concat(substr(CPU.COD_ESTRUTURAL,1,"+((nivel+1)*3)+"), '"+getRestanteNivel(nivel+1)+"') ");
			}
		} 
		
		sql.append("WHERE 1 = 1 ");
		
		if (filterVO.getDemandante() != null) {
			sql.append(" AND CPU.DEMANDANTE_ID = "+filterVO.getDemandante().getValor()+" ");
		}
		if (filterVO.getEmissaoInicio() != null) {
			sql.append(" AND CPU.DATA >= to_date('"+ DataUtil.formataData(filterVO.getEmissaoInicio())+ "','dd/MM/yy')");
		} 
		if (filterVO.getEmissaoFim() != null) {
			sql.append(" AND CPU.DATA <= to_date('"+ DataUtil.formataData(filterVO.getEmissaoFim())+ "','dd/MM/yy')");
		}
		if(filterVO.isImprimirOrdemServico()) {
			sql.append(clausulaNivel(filterVO));
		} else {
			sql.append(clausulaNivelNaoImprimeDados(filterVO));
			sql.append(" GROUP BY CPU.COD_ESTRUTURAL, CPU.UNIDADE_SIGLA, CPU.UNIDADE_NOME ");
		}
		
		Query query = em.createNativeQuery(sql.toString());
		List<Object[]> retorno = query.getResultList();
		for(Object[] obj : retorno) {
			CustoPorUnidadeVO custoPorUnidade = new CustoPorUnidadeVO();
			custoPorUnidade.setCodEstrutural((String) obj[0]);
			custoPorUnidade.setSigla((String) obj[1]);
			custoPorUnidade.setNomeUnidade((String) obj[2]);
			custoPorUnidade.setCustoMaterial((BigDecimal) obj[3]);
			custoPorUnidade.setCustoMaoDeObra((BigDecimal) obj[4]);
			custoPorUnidade.setCustoTotal((BigDecimal) obj[5]);
			if(filterVO.isImprimirOrdemServico()) {
				custoPorUnidade.setOsPrincipal((BigDecimal) obj[6]);
				custoPorUnidade.setOrdemServico((BigDecimal) obj[7]);
				custoPorUnidade.setData((Date) obj[8]);
				custoPorUnidade.setDemandante((String) obj[9]);
			}
			lista.add(custoPorUnidade);
		}
		return lista;
	}
	
	public String clausulaNivel(CustoPorUnidadeFilterVO filterVO) {
		String nivel = null;
		if(filterVO.getNivel8() != null) {
			nivel = substituirNivelRecursivo(8, filterVO.getNivel8().getValor()); 
		}
		else if(filterVO.getNivel7() != null) {
			nivel = substituirNivelRecursivo(7, filterVO.getNivel7().getValor()); 
		}
		else if(filterVO.getNivel6() != null) {
			nivel = substituirNivelRecursivo(6, filterVO.getNivel6().getValor()); 
		}
		else if(filterVO.getNivel5() != null) {
			nivel = substituirNivelRecursivo(5, filterVO.getNivel5().getValor()); 
		}
		else if(filterVO.getNivel4() != null) {
			nivel = substituirNivelRecursivo(4, filterVO.getNivel4().getValor()); 
		}
		else if(filterVO.getNivel3() != null) {
			nivel = substituirNivelRecursivo(3, filterVO.getNivel3().getValor()); 
		}
		else if(filterVO.getNivel2() != null) {
			nivel = substituirNivelRecursivo(2, filterVO.getNivel2().getValor()); 
		}
		else if(filterVO.getNivel1() != null) {
			nivel = substituirNivelRecursivo(1, filterVO.getNivel1().getValor()); 
		}
		if(nivel != null) {
			return " AND CPU.COD_ESTRUTURAL like '"+nivel+"'";
		} else {
			return "";
		}
	}
	
	public String clausulaNivelNaoImprimeDados(CustoPorUnidadeFilterVO filterVO) {
		Integer numeroNivel = getNivel(filterVO); 
		String nivel = null;
		if(filterVO.getNivel8() != null) {
			nivel = filterVO.getNivel8().getValor(); 
		}
		else if(filterVO.getNivel7() != null) {
			nivel = filterVO.getNivel7().getValor(); 
		}
		else if(filterVO.getNivel6() != null) {
			nivel = filterVO.getNivel6().getValor(); 
		}
		else if(filterVO.getNivel5() != null) {
			nivel = filterVO.getNivel5().getValor(); 
		}
		else if(filterVO.getNivel4() != null) {
			nivel = filterVO.getNivel4().getValor(); 
		}
		else if(filterVO.getNivel3() != null) {
			nivel = filterVO.getNivel3().getValor(); 
		}
		else if(filterVO.getNivel2() != null) {
			nivel = filterVO.getNivel2().getValor(); 
		}
		else if(filterVO.getNivel1() != null) {
			nivel = filterVO.getNivel1().getValor(); 
		}
		if(numeroNivel == 0) {
			return " AND N.COD_ESTRUTURAL like '__.00.00.00.00.00.00.00'";
		} else {
			String[] niveis = nivel.split("\\.");
			if(numeroNivel < 8) {
				niveis[numeroNivel] = "__";
			}
			String novoNivel = Arrays.asList(niveis).toString().replace(", ", "."); 
			novoNivel = novoNivel.substring(1, novoNivel.length()-1);
			return " AND N.COD_ESTRUTURAL like '"+novoNivel+"'";
		}
	}
	
	public Integer getNivel(CustoPorUnidadeFilterVO filterVO) {
		Integer nivel = 0;
		if(filterVO.getNivel8() != null) {
			nivel = 8; 
		}
		else if(filterVO.getNivel7() != null) {
			nivel = 7; 
		}
		else if(filterVO.getNivel6() != null) {
			nivel = 6; 
		}
		else if(filterVO.getNivel5() != null) {
			nivel = 5; 
		}
		else if(filterVO.getNivel4() != null) {
			nivel = 4; 
		}
		else if(filterVO.getNivel3() != null) {
			nivel = 3; 
		}
		else if(filterVO.getNivel2() != null) {
			nivel = 2; 
		}
		else if(filterVO.getNivel1() != null) {
			nivel = 1; 
		}
		return nivel;
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
	
	private String getRestanteNivel(Integer nivel) {
		List<String> niveis = new ArrayList<String>();
		for(int i=0; i < (8-nivel); i++) {
			niveis.add("00");
		}
		String retorno = niveis.toString(); 
		retorno = retorno.replace(", ", ".");
		if(retorno.length() == 0) {
			return "";
		}
		return retorno.substring(1, retorno.length()-1);
	}

	
}
