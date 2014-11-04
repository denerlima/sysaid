package model.facade;

import java.io.Serializable;
import java.util.List;

import javax.inject.Named;

import model.entity.vo.CustoPorEnderecoFilterVO;
import model.entity.vo.CustoPorEnderecoVO;
import model.entity.vo.CustoPorUnidadeFilterVO;
import model.entity.vo.CustoPorUnidadeVO;
import model.entity.vo.ItemVO;
import persistence.dao.ExtraDAO;
import util.Component;

@Named
public class ExtraFacade implements Serializable {

	private static final long serialVersionUID = 6832015607129893269L;
	
	//@Inject
	//private getExtraDAO getExtraDAO;
	
	public List<ItemVO> consultarDemandantes() {
		return getExtraDAO().consultarDemandantes();
	}
	
	public List<ItemVO> consultarDemandantesCustoPorUnidade() {
		return getExtraDAO().consultarDemandantesCustoPorUnidade();
	}
	
	public ItemVO capturarDemandanteCustoPorUnidade(String demandanteId) {
		if(demandanteId == null || demandanteId.isEmpty()) {
			return null;
		}
		return getExtraDAO().capturarDemandanteCustoPorUnidade(demandanteId);
	}
	
	public List<ItemVO> consultarAgrupadores() {
		return getExtraDAO().consultarAgrupadores();
	}
	
	public List<ItemVO> consultarEnderecos() {
		return getExtraDAO().consultarEnderecos();
	}
	
	public List<ItemVO> consultarNiveis(Integer nivel, String valorNivel) {
		return getExtraDAO().consultarNiveis(nivel, valorNivel);
	}
	
	public ItemVO capturarNivel(Integer nivel, String valorNivel) {
		if(valorNivel == null || valorNivel.isEmpty()) {
			return null;
		}
		return getExtraDAO().capturarNivel(nivel, valorNivel);
	}
	
	public List<CustoPorEnderecoVO> consultarCustoPorEndereco(CustoPorEnderecoFilterVO filterVO) {
		List<CustoPorEnderecoVO> lista = getExtraDAO().consultarCustoPorEndereco(filterVO);
		/*
		if(filterVO.getNivelDetalhe() == null) {
			return lista;
		}
		else if(filterVO.getNivelDetalhe().intValue() == 1) {
			for(CustoPorEnderecoVO ce : lista) {
				ce.setEndereco(null);
				ce.setOrdemServico(null);
				ce.setSubOs(null);
				ce.setData(null);
				ce.setDemandante(null);
				ce.setComplementoEndereco(null);
			}
		}
		else if(filterVO.getNivelDetalhe().intValue() == 2) {
			for(CustoPorEnderecoVO ce : lista) {
				ce.setOrdemServico(null);
				ce.setSubOs(null);
				ce.setData(null);
				ce.setDemandante(null);
				ce.setComplementoEndereco(null);
			}
		}
		else if(filterVO.getNivelDetalhe().intValue() == 3) {
			for(CustoPorEnderecoVO ce : lista) {
				ce.setOrdemServico(null);
				ce.setSubOs(null);
				ce.setData(null);
				ce.setDemandante(null);
			}
		}
		else if(filterVO.getNivelDetalhe().intValue() == 4) {
			for(CustoPorEnderecoVO ce : lista) {
				ce.setOrdemServico(null);
				ce.setSubOs(null);
				ce.setData(null);
			}
		}
		*/
		return lista; 
	}
	
	public List<CustoPorUnidadeVO> consultarCustoPorUnidade(CustoPorUnidadeFilterVO filterVO) {
		return getExtraDAO().consultarCustoPorUnidade(filterVO);
	}
	
	public ExtraDAO getExtraDAO() {
		return (ExtraDAO) Component.getInstance(ExtraDAO.class);
	}
	
}
