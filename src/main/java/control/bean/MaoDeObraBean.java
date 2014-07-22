package control.bean;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import model.entity.MaoDeObra;
import model.facade.MaoDeObraFacade;

import org.omnifaces.cdi.ViewScoped;
import org.primefaces.context.RequestContext;

@Named
@ViewScoped
public class MaoDeObraBean extends AbstractBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private MaoDeObra maoDeObra;	
	private List<MaoDeObra> maosDeObra;
	
	@Inject
	private MaoDeObraFacade maoDeObraFacade;

	
	public MaoDeObraFacade getMaoDeObraFacade() {
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

	public void nova() {
		resetMaoDeObra();
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
		RequestContext.getCurrentInstance().addCallbackParam("success", true);
	}
	
	public void edit(Integer id) {
		maoDeObra = maoDeObraFacade.find(id);
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
		RequestContext.getCurrentInstance().addCallbackParam("success", true);
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
	
	public boolean isManaged() {
		return maoDeObra.getId() != null;
	}

}