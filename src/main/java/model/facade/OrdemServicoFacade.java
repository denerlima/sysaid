package model.facade;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.NoResultException;

import model.entity.OrdemServico;
import persistence.dao.GenericDAO;
import persistence.dao.OrdemServicoDAO;

@Named
public class OrdemServicoFacade extends GenericFacade<OrdemServico> implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private OrdemServicoDAO ordemServicoDAO;
	
	@Override
	public GenericDAO<OrdemServico> getDAO() {
		return ordemServicoDAO;
	}
	
	public OrdemServico findOrCreate(Integer id) {
		try {
			return getDAO().find(id);
		} catch (NoResultException e) {
			OrdemServico os = new OrdemServico();
			os.setId(id);
			getDAO().save(os);
			return os;
		}
	}
	
}
