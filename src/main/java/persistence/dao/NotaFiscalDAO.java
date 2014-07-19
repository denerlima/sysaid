package persistence.dao;

import model.entity.NotaFiscal;

public class NotaFiscalDAO extends GenericDAO<NotaFiscal> {

	private static final long serialVersionUID = 1L;

	public NotaFiscalDAO() {
		super(NotaFiscal.class);
	}

	public void delete(NotaFiscal notaFiscal) {
		super.delete(notaFiscal.getId(), NotaFiscal.class);
	}

}