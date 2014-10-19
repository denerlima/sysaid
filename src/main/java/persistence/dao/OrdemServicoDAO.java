package persistence.dao;

import java.math.BigDecimal;
import java.util.Date;

import javax.inject.Named;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import model.entity.Material;
import model.entity.OrdemServico;

@Named
public class OrdemServicoDAO extends GenericDAO<OrdemServico> {

	private static final long serialVersionUID = 1L;

	public OrdemServicoDAO() {
		super(OrdemServico.class);
	}

	public void delete(OrdemServico ordemServico) {
		ordemServico.setAtivo(0);
		super.update(ordemServico);
	}

	public Predicate clausulaWhere(CriteriaBuilder cb, Root<OrdemServico> rootCriteria) {
		return cb.notEqual(rootCriteria.get("ativo"), 0);
	}

	public BigDecimal calculaTotalSaidas(Material mat, Date dataInicial,
			Date dataFinal) {

		BigDecimal total = new BigDecimal(0);
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT SUM(osmh.quantidade) FROM OrdemServicoMaterialHistorico osmh LEFT JOIN osmh.ordemServicoMaterial osm WHERE osm.ativo = 1");
			sql.append(" AND osmh.tipo >=1 ");
			
			if (mat.getId() != null) {
				sql.append(" AND osm.material.id = :idMat ");
			}
			if (dataInicial != null && dataFinal != null) {
				sql.append(" AND osmh.data BETWEEN :dataInicial  AND :dataFinal ");
			}

			Query query = getEntityManager().createQuery(sql.toString());

			if (mat.getId() != null) {
				query.setParameter("idMat", mat.getId());
			}
			if (dataInicial != null && dataFinal != null) {
				query.setParameter("dataInicial", dataInicial);
				query.setParameter("dataFinal", dataFinal);
			}

			total = (BigDecimal) query.getSingleResult();
			if(total == null) {
				total = new BigDecimal(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return total;
	}
	
	public void carregarDadosOrdemServico(OrdemServico os) {
		try {
			StringBuffer sql = new StringBuffer();
			sql.append(
					"SELECT " +
					"	 S.ID AS ID_ORDEM_SERVICO, " +
				    "    U.CALCULATED_USER_NAME as NOME_SOLICITANTE, " +
				    "    s.sr_cust_txtramal AS RAMAL, " +
				    "    TAB25.SIG_UNID_PR AS AREA_SOLICITANTE, " +
				    "    s.insert_time AS DATA_ABERTURA, " +
				    "    s.close_time AS DATA_FECHAMENTO, " +
				    "    CONCAT(CV.VALUE_CAPTION, CONCAT(' ', S.SR_CUST_TXTCOMPLEMENTO))  AS ENDERECO_ATENDIMENTO " +   
				    " FROM " +
				    "    service_req S " +   
				    " INNER JOIN " +
				    "    CUST_VALUES CV " +       
				    "        ON S.LOCATION  = CV.VALUE_KEY " +         
				    "        AND CV.list_name = 'location'  " +
				    " INNER JOIN " +
				    "    sysaid_user U " +       
				    "        ON S.REQUEST_USER = U.USER_NAME " +   
				    " INNER JOIN " +
				    "    viw0001pes_sysaid VPES " +     
				    "        ON U.LOGIN_USER_UPPER = VPES.NOM_LOGIN " +   
				    " INNER JOIN " +
				    "    MF_TAB0025 TAB25 " +     
				    "        ON VPES.COD_LOTACAO_INF = TAB25.COD_UNID_PR " +   
				    " WHERE S.ID = " + os.getId());

			Query query = getEntityManager().createNativeQuery(sql.toString());
			Object[] obj = (Object[]) query.getSingleResult();
			
			os.setSolicitante((String) obj[1]);
			os.setRamal((BigDecimal) obj[2]);
			os.setAreaSolicitante((String) obj[3]);
			os.setDataAbertura((Date) obj[4]);
			os.setDataFechamento((Date) obj[5]);
			os.setEnderecoAtendimento((String) obj[6]);
			
		} catch (Exception e) {
			e.printStackTrace();
			
			os.setSolicitante("José");
			os.setRamal(new BigDecimal(1234));
			os.setAreaSolicitante("Dilog");
			os.setDataAbertura(new Date());
			os.setDataFechamento(new Date());
			os.setEnderecoAtendimento("SCS");
			
		}
	}
	
	
}
