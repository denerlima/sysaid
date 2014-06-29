package model.facade;

import java.io.Serializable;
import java.util.List;

import model.entity.Marca;
import persistence.dao.MarcaDAO;

public class MarcaFacade implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private MarcaDAO marcaDAO = new MarcaDAO();

	public void createMarca(Marca marca) {
		marcaDAO.beginTransaction();
		marcaDAO.save(marca);
		marcaDAO.commitAndCloseTransaction();
	}

	public void updateMarca(Marca marca) {
		marcaDAO.beginTransaction();
		Marca persistedMarca = marcaDAO.find(marca.getId());
		persistedMarca.setDescricao(marca.getDescricao());
		marcaDAO.update(persistedMarca);
		marcaDAO.commitAndCloseTransaction();
	}

	public Marca findMarca(int marcaId) {
		marcaDAO.beginTransaction();
		Marca marca = marcaDAO.find(marcaId);
		marcaDAO.closeTransaction();
		return marca;
	}

	public List<Marca> listAll() {
		marcaDAO.beginTransaction();
		List<Marca> result = marcaDAO.findAll();
		marcaDAO.closeTransaction();
		return result;
	}

	public void deleteMarca(Marca marca) {
		marcaDAO.beginTransaction();
		Marca persistedMarca = marcaDAO.findReferenceOnly(marca.getId());
		marcaDAO.delete(persistedMarca);
		marcaDAO.commitAndCloseTransaction();
	}
}