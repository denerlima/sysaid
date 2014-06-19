package model.facade;

import java.io.Serializable;
import java.util.List;

import model.entity.UnidadeEntrada;
import persistence.dao.UnidadeEntradaDAO;

public class UnidadeEntradaFacade implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private UnidadeEntradaDAO unidadeEntradaDAO = new UnidadeEntradaDAO();

	public void createUnidadeEntrada(UnidadeEntrada unidadeEntrada) {
		unidadeEntradaDAO.beginTransaction();
		unidadeEntradaDAO.save(unidadeEntrada);
		unidadeEntradaDAO.commitAndCloseTransaction();
	}

	public void updateUnidadeEntrada(UnidadeEntrada unidadeEntrada) {
		unidadeEntradaDAO.beginTransaction();
		UnidadeEntrada persistedUnidadeEntrada = unidadeEntradaDAO.find(unidadeEntrada.getId());
		persistedUnidadeEntrada.setDescricao(unidadeEntrada.getDescricao());
		unidadeEntradaDAO.update(persistedUnidadeEntrada);
		unidadeEntradaDAO.commitAndCloseTransaction();
	}

	public UnidadeEntrada findUnidadeEntrada(int unidadeEntradaId) {
		unidadeEntradaDAO.beginTransaction();
		UnidadeEntrada unidadeEntrada = unidadeEntradaDAO.find(unidadeEntradaId);
		unidadeEntradaDAO.closeTransaction();
		return unidadeEntrada;
	}

	public List<UnidadeEntrada> listAll() {
		unidadeEntradaDAO.beginTransaction();
		List<UnidadeEntrada> result = unidadeEntradaDAO.findAll();
		unidadeEntradaDAO.closeTransaction();
		return result;
	}

	public void deleteUnidadeEntrada(UnidadeEntrada unidadeEntrada) {
		unidadeEntradaDAO.beginTransaction();
		UnidadeEntrada persistedUnidadeEntrada = unidadeEntradaDAO.findReferenceOnly(unidadeEntrada.getId());
		unidadeEntradaDAO.delete(persistedUnidadeEntrada);
		unidadeEntradaDAO.commitAndCloseTransaction();
	}
}