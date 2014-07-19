package model.facade;

import java.io.Serializable;

import model.entity.MaoDeObra;
import persistence.dao.GenericDAO;
import persistence.dao.MaoDeObraDAO;

public class MaoDeObraFacade extends GenericFacade<MaoDeObra> implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private MaoDeObraDAO maoDeObraDAO = new MaoDeObraDAO();

	public MaoDeObra MaoDeObra(int maoDeObraId) {
		maoDeObraDAO.beginTransaction();
		MaoDeObra maoDeObra = maoDeObraDAO.find(maoDeObraId);
		maoDeObraDAO.closeTransaction();
		return maoDeObra;
	}
	
	@Override
	public GenericDAO<MaoDeObra> getDAO() {
		return maoDeObraDAO;
	}

}