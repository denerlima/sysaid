package model.facade;

import java.io.Serializable;

import model.entity.NotaFiscal;
import persistence.dao.GenericDAO;
import persistence.dao.NotaFiscalDAO;

public class NotaFiscalFacade extends GenericFacade<NotaFiscal> implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private NotaFiscalDAO notaFiscalDAO = new NotaFiscalDAO();
	
	@Override
	public GenericDAO<NotaFiscal> getDAO() {
		return notaFiscalDAO;
	}
}