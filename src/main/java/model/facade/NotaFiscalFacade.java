package model.facade;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;

import model.entity.NotaFiscal;
import persistence.dao.GenericDAO;
import persistence.dao.NotaFiscalDAO;

@Named
public class NotaFiscalFacade extends GenericFacade<NotaFiscal> implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private NotaFiscalDAO notaFiscalDAO;
	
	@Override
	public GenericDAO<NotaFiscal> getDAO() {
		return notaFiscalDAO;
	}
	
}
