package model.facade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import model.entity.Material;
import model.entity.NotaFiscal;
import model.entity.NotaFiscalMaterial;
import model.entity.OrdemDeCompra;
import model.entity.OrdemDeCompraMaterial;
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
	
	@Inject
	private MaterialFacade materialFacade;
	
	public void create(NotaFiscal notaFiscal) throws Exception {
		try {
			getDAO().beginTransaction();
			for (NotaFiscalMaterial nfm : notaFiscal.getMateriais()) {
				Material mat = nfm.getOrdemDeCompraMaterial().getMaterial();
				BigDecimal estoque = mat.getEstoque();
				mat.setEstoque(estoque.add(nfm.getQuantidade()));
				mat.setPrecoUnitario(nfm.getPrecoUnitario());
				
				BigDecimal totalOC =  materialDAO.totalMatOC(mat);
				BigDecimal totalNF =  materialDAO.totalMatNF(mat);	
				totalNF = totalNF.add(nfm.getQuantidade());
				mat.setQtdSolicitada(totalOC.subtract(totalNF));
				materialDAO.save(mat);							
			}
			getDAO().save(notaFiscal);
			getDAO().commit();
		} catch (Exception e) {
			getDAO().rollback();
			throw e;
		}
	}
	
	public void updateNota(NotaFiscal notaFiscal) throws Exception {
		try {
			getDAO().beginTransaction();
			for (NotaFiscalMaterial nfm : notaFiscal.getMateriais()) {
				Material mat = nfm.getOrdemDeCompraMaterial().getMaterial();
				mat.setPrecoUnitario(nfm.getPrecoUnitario());
				materialDAO.save(mat);
			}
			getDAO().update(notaFiscal);
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

	public List<NotaFiscalMaterial> pesquisarNotaFiscalbyFilters(
			NotaFiscal notaFiscal, OrdemDeCompra ordemDeCompra,
			Material material, Date dtEmissaoInicial, Date dtEmissaoFinal,
			Date dtEntregaInicial, Date dtEntregaFinal) {
		
		List<NotaFiscalMaterial> result = notaFiscalDAO.listMateriaisNotasFiscais(notaFiscal,
				ordemDeCompra, material, dtEmissaoInicial , dtEmissaoFinal , dtEntregaInicial , dtEntregaFinal);      
		return result;		
	}
	
}
