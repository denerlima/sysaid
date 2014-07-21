package model.facade;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;

import model.entity.MaoDeObra;
import persistence.dao.GenericDAO;
import persistence.dao.MaoDeObraDAO;

@Named
public class MaoDeObraFacade extends GenericFacade<MaoDeObra> implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private MaoDeObraDAO maoDeObraDAO;

	public MaoDeObra MaoDeObra(int maoDeObraId) {
		//maoDeObraDAO.beginTransaction();
		MaoDeObra maoDeObra = maoDeObraDAO.find(maoDeObraId);
		//maoDeObraDAO.closeTransaction();
		return maoDeObra;
	}
	
	@Override
	public GenericDAO<MaoDeObra> getDAO() {
		return maoDeObraDAO;
	}

}