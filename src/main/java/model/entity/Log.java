
package model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@SequenceGenerator(name = "logSequence", sequenceName = "MF_LOG_ID_SEQ", allocationSize = 1)
@Table(name = "MF_LOG")
public class Log extends GenericModelo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public static final String ACAO_CREATE = "C";
	public static final String ACAO_UPDATE = "U";
	public static final String ACAO_DELETE = "D";
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "logSequence")
	@Column
	private Integer id;	
	
	@Column
	private String usuario;
	
	@Column
	private String acao;
	
	@Column
	private Integer identificador;
	
	@Column
	private String entidade;
	
	@Lob
	@Column
	private String descricao;

	public Log(String usuario, String acao, Integer identificador, String entidade, String descricao) {
		super();
		this.usuario = usuario;
		this.acao = acao;
		this.identificador = identificador;
		this.entidade = entidade;
		this.descricao = descricao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}

	public Integer getIdentificador() {
		return identificador;
	}

	public void setIdentificador(Integer identificador) {
		this.identificador = identificador;
	}
	
	public String getEntidade() {
		return entidade;
	}

	public void setEntidade(String entidade) {
		this.entidade = entidade;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Log other = (Log) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}