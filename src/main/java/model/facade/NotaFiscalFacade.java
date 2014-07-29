package model.facade;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.inject.Inject;
import javax.inject.Named;

import model.entity.Material;
import model.entity.NotaFiscal;
import model.entity.NotaFiscalMaterial;
import persistence.dao.GenericDAO;
import persistence.dao.MaterialDAO;
import persistence.dao.NotaFiscalDAO;

@Named
public class NotaFiscalFacade extends GenericFacade<NotaFiscal> implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private NotaFiscalDAO notaFiscalDAO;
	
	@Inject
	private MaterialDAO materialDAO;
	
	public void create(NotaFiscal notaFiscal) throws Exception {
		try {
			getDAO().beginTransaction();
			for (NotaFiscalMaterial nfm : notaFiscal.getMateriais()) {
				Material mat = nfm.getOrdemDeCompraMaterial().getMaterial();
				BigDecimal estoque = mat.getEstoqueCalculado();
				mat.setEstoqueCalculado(estoque.subtract(nfm.getQuantidade()));
				materialDAO.save(mat);
			}
			getDAO().save(notaFiscal);
			getDAO().commit();
		} catch (Exception e) {
			getDAO().rollback();
			throw e;
		}
	}
	
	@Override
	public GenericDAO<NotaFiscal> getDAO() {
		return notaFiscalDAO;
	}
	
}
