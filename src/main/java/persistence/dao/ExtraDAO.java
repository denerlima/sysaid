package persistence.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.entity.vo.CustoPorEnderecoFilterVO;
import model.entity.vo.CustoPorEnderecoVO;
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
			lista.add(new ItemVO(obj[0].toString(), obj[0].toString()));
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
			lista.add(new ItemVO(obj[0].toString(), obj[1].toString()));
		}
		return lista;
	}
	
	@SuppressWarnings("unchecked")
	public List<ItemVO> consultarEnderecos() {
		ArrayList<ItemVO> lista = new ArrayList<ItemVO>();
 		String sql = "Select cv.ACCOUNT_ID, cv.LIST_NAME FROM CUST_VALUES cv";
		Query query = em.createNativeQuery(sql);
		List<Object[]> retorno = query.getResultList();
		for(Object[] obj : retorno) {
			lista.add(new ItemVO(obj[0].toString(), obj[0].toString()));
		}
		return lista;
	}
	
	@SuppressWarnings("unchecked")
	public List<CustoPorEnderecoVO> consultarCustoPorEndereco(CustoPorEnderecoFilterVO filterVO) {
		ArrayList<CustoPorEnderecoVO> lista = new ArrayList<CustoPorEnderecoVO>();
 		String sql = ""+
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
	 	"	  ON EH.VALUE_KEY  = CV.VALUE_KEY "+
	 	//"	--WHERE SR.insert_time between $P{fromDate} and $P{toDate} "+
 		"GROUP BY SR.SR_CUST_TXTCOMPLEMENTO, SU.CALCULATED_USER_NAME, EH.AGRUPADOR, CV.VALUE_CAPTION ,  OM.ID_ORDEM_SERVICO , SR.SR_CUST_NUMBERMAINOSI, SR.insert_time "+
 		"ORDER BY SR.SR_CUST_TXTCOMPLEMENTO, SU.CALCULATED_USER_NAME, EH.AGRUPADOR, CV.VALUE_CAPTION ,  OM.ID_ORDEM_SERVICO ";
 		
		Query query = em.createNativeQuery(sql);
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
	
}
