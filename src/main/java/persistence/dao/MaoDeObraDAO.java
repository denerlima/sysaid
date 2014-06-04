package persistence.dao;

import model.entity.MaoDeObra;

public class MaoDeObraDAO extends GenericDAO<MaoDeObra> {

	private static final long serialVersionUID = 1L;

	public MaoDeObraDAO() {
		super(MaoDeObra.class);
	}

	public void delete(MaoDeObra maoDeObra) {
		super.delete(maoDeObra.getId(), MaoDeObra.class);
	}

}