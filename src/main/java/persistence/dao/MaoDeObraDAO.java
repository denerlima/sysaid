package persistence.dao;

import javax.inject.Named;

import model.entity.MaoDeObra;

@Named
public class MaoDeObraDAO extends GenericDAO<MaoDeObra> {

	private static final long serialVersionUID = 1L;

	public MaoDeObraDAO() {
		super(MaoDeObra.class);
	}

	public void delete(MaoDeObra maoDeObra) {
		maoDeObra.setAtivo(0);
		super.update(maoDeObra);
	}

}