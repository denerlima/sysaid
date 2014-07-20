package model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@SequenceGenerator(name = "notaFiscalMaterialSequence", sequenceName = "NOTAFISCAL_MATERIAL_ID_SEQ", allocationSize = 1)
@Table(name = "MF_NOTAFISCAL_MF_MATERIAL")
@Where(clause = "ativo = '1'")  
@SQLDelete(sql = "UPDATE sysaid_java.MF_NOTAFISCAL_MF_MATERIAL SET ativo  = 0 WHERE id = ?")
public class NotaFiscalMaterial {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "notaFiscalMaterialSequence")
	@Column
	private Integer id;	
	
	@Column
	private int ativo = 1;
	
	@ManyToOne
	@JoinColumn(name="id_notafiscal", referencedColumnName="id")
	private NotaFiscal notaFiscal;
	
	@ManyToOne
	@JoinColumn(name="id_material", referencedColumnName="id")
	private Material material;
	
	
	
}
