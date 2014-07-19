package model.facade;

import java.io.Serializable;
import java.util.List;

import model.entity.NotaFiscal;
import persistence.dao.GenericDAO;
import persistence.dao.NotaFiscalDAO;

public class NotaFiscalFacade extends GenericFacade<NotaFiscal> implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private NotaFiscalDAO notaFiscalDAO = new NotaFiscalDAO();


//	public void updateNotaFiscal(NotaFiscal notaFiscal) {
//		notaFiscalDAO.beginTransaction();
//		NotaFiscal persistedNotaFiscal = notaFiscalDAO.find(notaFiscal.getId());
//		persistedNotaFiscal.setNumeroOC(notaFiscal.getNumeroOC());
//		persistedNotaFiscal.setDataAutorizacao(notaFiscal.getDataAutorizacao());
//		persistedNotaFiscal.setDataEmissao(notaFiscal.getDataEmissao());
//		persistedNotaFiscal.setAutorizador(notaFiscal.getAutorizador());
//		persistedNotaFiscal.setContratado(notaFiscal.getContratado());
//		//persistedNotaFiscal.setMateriais(notaFiscal.getMateriais());
//		notaFiscalDAO.update(persistedNotaFiscal);
//		notaFiscalDAO.commitAndCloseTransaction();
//	}

	public List<NotaFiscal> listAll() {
		notaFiscalDAO.beginTransaction();
		List<NotaFiscal> result = notaFiscalDAO.findAll();
		notaFiscalDAO.closeTransaction();
		return result;
	}

	@Override
	public GenericDAO<NotaFiscal> getDAO() {
		return notaFiscalDAO;
	}
}