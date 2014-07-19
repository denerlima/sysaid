package control.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import model.entity.MaoDeObra;
import model.facade.MaoDeObraFacade;

@ViewScoped
@ManagedBean
public class MaoDeObraBean extends AbstractBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private MaoDeObra maoDeObra;	
	private List<MaoDeObra> maosDeObra;
	private MaoDeObraFacade maoDeObraFacade;

	
	public MaoDeObraFacade getMaoDeObraFacade() {
		if (maoDeObraFacade == null) {
			maoDeObraFacade = new MaoDeObraFacade();
		}

		return maoDeObraFacade;
	}

	public MaoDeObra getMaoDeObra() {
		if (maoDeObra == null) {
			maoDeObra = new MaoDeObra();
		}

		return maoDeObra;
	}

	public void setMaoDeObra(MaoDeObra maoDeObra) {
		this.maoDeObra = maoDeObra;
	}

	public void createMaoDeObra() {
		try {
			getMaoDeObraFacade().create(maoDeObra);
			closeDialog();
			displayInfoMessageToUser("Criado com Sucesso");
			loadMaosDeObra();
			resetMaoDeObra();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ops, não foi possivel criar. ERRO");
			e.printStackTrace();
		}
	}
	
	public void updateMaoDeObra() {
		try {
			getMaoDeObraFacade().update(maoDeObra);
			closeDialog();
			displayInfoMessageToUser("Alterado com  Sucesso");
			loadMaosDeObra();
			resetMaoDeObra();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ops, não foi possivel alterar. ERRO");
			e.printStackTrace();
		}
	}
	
	public void deleteMaoDeObra() {
		try {
			getMaoDeObraFacade().delete(maoDeObra);
			closeDialog();
			displayInfoMessageToUser("Excluído com Sucesso");
			loadMaosDeObra();
			resetMaoDeObra();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ops, não foi possivel excluir. ERRO");
			e.printStackTrace();
		}
	}

	public List<MaoDeObra> getAllMaosDeObra() {
		if (maosDeObra == null) {
			loadMaosDeObra();
		}

		return maosDeObra;
	}

	private void loadMaosDeObra() {
		maosDeObra = getMaoDeObraFacade().listAll();
	}

	public void resetMaoDeObra() {
		maoDeObra = new MaoDeObra();
	}

}