package model.facade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import model.entity.MaoDeObra;
import persistence.dao.MaoDeObraDAO;

public class MaoDeObraFacade implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private MaoDeObraDAO maoDeObraDAO = new MaoDeObraDAO();

	public void createMaoDeObra(MaoDeObra maoDeObra) {
		maoDeObraDAO.beginTransaction();
		maoDeObraDAO.save(maoDeObra);
		maoDeObraDAO.commitAndCloseTransaction();
	}

	public void updateMaoDeObra(MaoDeObra maoDeObra) {
		maoDeObraDAO.beginTransaction();
		MaoDeObra persistedMaoDeObra = maoDeObraDAO.find(maoDeObra.getId());
		persistedMaoDeObra.setDescricao(maoDeObra.getDescricao());
		persistedMaoDeObra.setValordia(maoDeObra.getValordia().setScale(2,BigDecimal.ROUND_DOWN));
		persistedMaoDeObra.setValorhora(maoDeObra.getValorhora().setScale(2,BigDecimal.ROUND_DOWN));
		maoDeObraDAO.update(persistedMaoDeObra);
		maoDeObraDAO.commitAndCloseTransaction();
	}

	public MaoDeObra MaoDeObra(int maoDeObraId) {
		maoDeObraDAO.beginTransaction();
		MaoDeObra maoDeObra = maoDeObraDAO.find(maoDeObraId);
		maoDeObraDAO.closeTransaction();
		return maoDeObra;
	}

	public List<MaoDeObra> listAll() {
		maoDeObraDAO.beginTransaction();
		List<MaoDeObra> result = maoDeObraDAO.findAll();
		maoDeObraDAO.closeTransaction();
		return result;
	}

	public void deleteMaoDeObra(MaoDeObra maoDeObra) {
		maoDeObraDAO.beginTransaction();
		MaoDeObra persistedMaoDeObra = maoDeObraDAO.findReferenceOnly(maoDeObra.getId());
		maoDeObraDAO.delete(persistedMaoDeObra);
		maoDeObraDAO.commitAndCloseTransaction();
	}
}