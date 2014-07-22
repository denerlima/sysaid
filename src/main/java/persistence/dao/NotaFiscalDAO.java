package persistence.dao;

import javax.inject.Named;

import model.entity.NotaFiscal;

@Named
public class NotaFiscalDAO extends GenericDAO<NotaFiscal> {

	private static final long serialVersionUID = 1L;

	public NotaFiscalDAO() {
		super(NotaFiscal.class);
	}

	public void delete(NotaFiscal notaFiscal) {
		notaFiscal.setAtivo(0);
		super.update(notaFiscal);
	}

}