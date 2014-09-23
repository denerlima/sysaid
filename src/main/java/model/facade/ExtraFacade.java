package model.facade;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import model.entity.vo.CustoPorEnderecoFilterVO;
import model.entity.vo.CustoPorEnderecoVO;
import model.entity.vo.CustoPorUnidadeFilterVO;
import model.entity.vo.CustoPorUnidadeVO;
import model.entity.vo.ItemVO;
import persistence.dao.ExtraDAO;

@Named
public class ExtraFacade implements Serializable {

	private static final long serialVersionUID = 6832015607129893269L;
	
	@Inject
	private ExtraDAO extraDAO;
	
	public List<ItemVO> consultarDemandantes() {
		return extraDAO.consultarDemandantes();
	}
	
	public List<ItemVO> consultarAgrupadores() {
		return extraDAO.consultarAgrupadores();
	}
	
	public List<ItemVO> consultarEnderecos() {
		return extraDAO.consultarEnderecos();
	}
	
	public List<ItemVO> consultarNiveis(Integer nivel, String valorNivel) {
		return extraDAO.consultarNiveis(nivel, valorNivel);
	}
	
	public List<CustoPorEnderecoVO> consultarCustoPorEndereco(CustoPorEnderecoFilterVO filterVO) {
		return extraDAO.consultarCustoPorEndereco(filterVO);
	}
	
	public List<CustoPorUnidadeVO> consultarCustoPorUnidade(CustoPorUnidadeFilterVO filterVO) {
		return extraDAO.consultarCustoPorUnidade(filterVO);
	}
	
}
