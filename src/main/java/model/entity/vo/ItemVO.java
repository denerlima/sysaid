package model.entity.vo;

public class ItemVO {

	private String valor;
	private String label;
	
	public ItemVO(String valor, String label) {
		this.valor = valor;
		this.label = label;
	}
	
	public String getValor() {
		return valor;
	}
	
	public void setValor(String valor) {
		this.valor = valor;
	}
	
	public String getLabel() {
		return label;
	}
	
	public void setLabel(String label) {
		this.label = label;
	}
	
}
