package persistence.dao;

import javax.inject.Named;

import model.entity.Parametro;

@Named
public class ParametroDAO extends GenericDAO<Parametro> {

	private static final long serialVersionUID = 1L;

	public ParametroDAO() {
		super(Parametro.class);
	}
}
