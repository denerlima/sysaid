package model.facade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import model.entity.UnidadeMedida;
import persistence.dao.UnidadeMedidaDAO;

public class UnidadeMedidaFacade implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private UnidadeMedidaDAO unidadeMedidaDAO = new UnidadeMedidaDAO();

	public void createUnidadeMedida(UnidadeMedida unidadeMedida) {
		unidadeMedidaDAO.beginTransaction();
		unidadeMedidaDAO.save(unidadeMedida);
		unidadeMedidaDAO.commitAndCloseTransaction();
	}

	public void updateUnidadeMedida(UnidadeMedida unidadeMedida) {
		unidadeMedidaDAO.beginTransaction();
		UnidadeMedida persistedUnidadeMedida = unidadeMedidaDAO.find(unidadeMedida.getId());
		persistedUnidadeMedida.setUnidadeEntrada(unidadeMedida.getUnidadeEntrada());
		persistedUnidadeMedida.setUnSaida(unidadeMedida.getUnSaida());
		persistedUnidadeMedida.setFatorConversao(unidadeMedida.getFatorConversao().setScale(3,BigDecimal.ROUND_DOWN));
		unidadeMedidaDAO.update(persistedUnidadeMedida);
		unidadeMedidaDAO.commitAndCloseTransaction();
	}

	public UnidadeMedida findUnidadeMedida(int unidadeMedidaId) {
		unidadeMedidaDAO.beginTransaction();
		UnidadeMedida unidadeMedida = unidadeMedidaDAO.find(unidadeMedidaId);
		unidadeMedidaDAO.closeTransaction();
		return unidadeMedida;
	}

	public List<UnidadeMedida> listAll() {
		unidadeMedidaDAO.beginTransaction();
		List<UnidadeMedida> result = unidadeMedidaDAO.findAll();
		unidadeMedidaDAO.closeTransaction();
		return result;
	}

	public void deleteUnidadeMedida(UnidadeMedida unidadeMedida) {
		unidadeMedidaDAO.beginTransaction();
		UnidadeMedida persistedUnidadeMedida = unidadeMedidaDAO.findReferenceOnly(unidadeMedida.getId());
		unidadeMedidaDAO.delete(persistedUnidadeMedida);
		unidadeMedidaDAO.commitAndCloseTransaction();
	}
}