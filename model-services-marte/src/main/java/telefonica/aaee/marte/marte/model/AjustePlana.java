package telefonica.aaee.marte.marte.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="tbl_ajuste_plana")
@NamedQueries({
	@NamedQuery(name="AjustePlana.findAll", query="SELECT a FROM AjustePlana a")
	, @NamedQuery(
			name="AjustePlana.findByUnique", 
			query="SELECT a FROM AjustePlana a "
					+ "WHERE 1 = 1 "
					+ "	AND a.tipoDoc = :t "
					+ "	AND a.cif = :c "
					+ "	AND a.acuerdoNumero = :a "
					+ "	AND a.negocio = :n "
					+ "	AND a.fechaAny = :f "
					+ "")
})
public class AjustePlana implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Long id;

	@Column(name="acuerdo_numero", nullable=false, length=6)
	private String acuerdoNumero;

	private double ajustes001;

	private double ajustes002;

	private double ajustes003;

	private double ajustes004;

	private double ajustes005;

	private double ajustes006;

	private double ajustes007;

	private double ajustes008;

	private double ajustes009;

	private double ajustes010;

	@Column(nullable=false, length=10)
	private String cif;

	@Column(name="fecha_any", nullable=false, length=4)
	private String fechaAny;

	@Column(name="fecha_carga")
	private Timestamp fechaCarga;

	@Column(nullable=false, length=10)
	private String negocio;

	@Column(name="tipo_doc", nullable=false, length=1)
	private String tipoDoc;

	@Column(name="total_ajustes")
	private double totalAjustes;

	public AjustePlana() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAcuerdoNumero() {
		return this.acuerdoNumero;
	}

	public void setAcuerdoNumero(String acuerdoNumero) {
		this.acuerdoNumero = acuerdoNumero;
	}

	public double getAjustes001() {
		return this.ajustes001;
	}

	public void setAjustes001(double ajustes001) {
		this.ajustes001 = ajustes001;
		this.totalAjustes = calculaTotalAjustes();
	}

	public double getAjustes002() {
		return this.ajustes002;
	}

	public void setAjustes002(double ajustes002) {
		this.ajustes002 = ajustes002;
		this.totalAjustes = calculaTotalAjustes();
	}

	public double getAjustes003() {
		return this.ajustes003;
	}

	public void setAjustes003(double ajustes003) {
		this.ajustes003 = ajustes003;
		this.totalAjustes = calculaTotalAjustes();
	}

	public double getAjustes004() {
		return this.ajustes004;
	}

	public void setAjustes004(double ajustes004) {
		this.ajustes004 = ajustes004;
		this.totalAjustes = calculaTotalAjustes();
	}

	public double getAjustes005() {
		return this.ajustes005;
	}

	public void setAjustes005(double ajustes005) {
		this.ajustes005 = ajustes005;
		this.totalAjustes = calculaTotalAjustes();
	}

	public double getAjustes006() {
		return this.ajustes006;
	}

	public void setAjustes006(double ajustes006) {
		this.ajustes006 = ajustes006;
		this.totalAjustes = calculaTotalAjustes();
	}

	public double getAjustes007() {
		return this.ajustes007;
	}

	public void setAjustes007(double ajustes007) {
		this.ajustes007 = ajustes007;
		this.totalAjustes = calculaTotalAjustes();
	}

	public double getAjustes008() {
		return this.ajustes008;
	}

	public void setAjustes008(double ajustes008) {
		this.ajustes008 = ajustes008;
		this.totalAjustes = calculaTotalAjustes();
	}

	public double getAjustes009() {
		return this.ajustes009;
	}

	public void setAjustes009(double ajustes009) {
		this.ajustes009 = ajustes009;
		this.totalAjustes = calculaTotalAjustes();
	}

	public double getAjustes010() {
		return this.ajustes010;
	}

	public void setAjustes010(double ajustes010) {
		this.ajustes010 = ajustes010;
		this.totalAjustes = calculaTotalAjustes();
	}

	public String getCif() {
		return this.cif;
	}

	public void setCif(String cif) {
		this.cif = cif;
	}

	public String getFechaAny() {
		return this.fechaAny;
	}

	public void setFechaAny(String fechaAny) {
		this.fechaAny = fechaAny;
	}

	public Timestamp getFechaCarga() {
		return this.fechaCarga;
	}

	public void setFechaCarga(Timestamp fechaCarga) {
		this.fechaCarga = fechaCarga;
	}

	public String getNegocio() {
		return this.negocio;
	}

	public void setNegocio(String negocio) {
		this.negocio = negocio;
	}

	public String getTipoDoc() {
		return this.tipoDoc;
	}

	public void setTipoDoc(String tipoDoc) {
		this.tipoDoc = tipoDoc;
	}

	public double getTotalAjustes() {
		return this.totalAjustes;
	}

	public void setTotalAjustes(double totalAjustes) {
		this.totalAjustes = totalAjustes;
	}
	
	private double calculaTotalAjustes(){
		return this.ajustes001 +
				this.ajustes002 +
				this.ajustes003 +
				this.ajustes004 +
				this.ajustes005 +
				this.ajustes006 +
				this.ajustes007 +
				this.ajustes008 +
				this.ajustes009 +
				this.ajustes010;
				
	}
	
	public AjustePlana copyAjustes(AjustePlana ajustePlana){
		this.setAjustes001(ajustePlana.getAjustes001());
		this.setAjustes002(ajustePlana.getAjustes002());
		this.setAjustes003(ajustePlana.getAjustes003());
		this.setAjustes004(ajustePlana.getAjustes004());
		this.setAjustes005(ajustePlana.getAjustes005());
		
		this.setAjustes006(ajustePlana.getAjustes006());
		this.setAjustes007(ajustePlana.getAjustes007());
		this.setAjustes008(ajustePlana.getAjustes008());
		this.setAjustes009(ajustePlana.getAjustes009());
		this.setAjustes010(ajustePlana.getAjustes010());
		
		return this;
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((acuerdoNumero == null) ? 0 : acuerdoNumero.hashCode());
		long temp;
		temp = Double.doubleToLongBits(ajustes001);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(ajustes002);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(ajustes003);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(ajustes004);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(ajustes005);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(ajustes006);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(ajustes007);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(ajustes008);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(ajustes009);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(ajustes010);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((cif == null) ? 0 : cif.hashCode());
		result = prime * result
				+ ((fechaAny == null) ? 0 : fechaAny.hashCode());
		result = prime * result
				+ ((fechaCarga == null) ? 0 : fechaCarga.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((negocio == null) ? 0 : negocio.hashCode());
		result = prime * result + ((tipoDoc == null) ? 0 : tipoDoc.hashCode());
		temp = Double.doubleToLongBits(totalAjustes);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		AjustePlana other = (AjustePlana) obj;
		if (acuerdoNumero == null) {
			if (other.acuerdoNumero != null)
				return false;
		} else if (!acuerdoNumero.equals(other.acuerdoNumero))
			return false;
		if (Double.doubleToLongBits(ajustes001) != Double
				.doubleToLongBits(other.ajustes001))
			return false;
		if (Double.doubleToLongBits(ajustes002) != Double
				.doubleToLongBits(other.ajustes002))
			return false;
		if (Double.doubleToLongBits(ajustes003) != Double
				.doubleToLongBits(other.ajustes003))
			return false;
		if (Double.doubleToLongBits(ajustes004) != Double
				.doubleToLongBits(other.ajustes004))
			return false;
		if (Double.doubleToLongBits(ajustes005) != Double
				.doubleToLongBits(other.ajustes005))
			return false;
		if (Double.doubleToLongBits(ajustes006) != Double
				.doubleToLongBits(other.ajustes006))
			return false;
		if (Double.doubleToLongBits(ajustes007) != Double
				.doubleToLongBits(other.ajustes007))
			return false;
		if (Double.doubleToLongBits(ajustes008) != Double
				.doubleToLongBits(other.ajustes008))
			return false;
		if (Double.doubleToLongBits(ajustes009) != Double
				.doubleToLongBits(other.ajustes009))
			return false;
		if (Double.doubleToLongBits(ajustes010) != Double
				.doubleToLongBits(other.ajustes010))
			return false;
		if (cif == null) {
			if (other.cif != null)
				return false;
		} else if (!cif.equals(other.cif))
			return false;
		if (fechaAny == null) {
			if (other.fechaAny != null)
				return false;
		} else if (!fechaAny.equals(other.fechaAny))
			return false;
		if (fechaCarga == null) {
			if (other.fechaCarga != null)
				return false;
		} else if (!fechaCarga.equals(other.fechaCarga))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (negocio == null) {
			if (other.negocio != null)
				return false;
		} else if (!negocio.equals(other.negocio))
			return false;
		if (tipoDoc == null) {
			if (other.tipoDoc != null)
				return false;
		} else if (!tipoDoc.equals(other.tipoDoc))
			return false;
		if (Double.doubleToLongBits(totalAjustes) != Double
				.doubleToLongBits(other.totalAjustes))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AjustePlana [id=");
		builder.append(id);
		builder.append(", acuerdoNumero=");
		builder.append(acuerdoNumero);
		builder.append(", ajustes001=");
		builder.append(ajustes001);
		builder.append(", ajustes002=");
		builder.append(ajustes002);
		builder.append(", ajustes003=");
		builder.append(ajustes003);
		builder.append(", ajustes004=");
		builder.append(ajustes004);
		builder.append(", ajustes005=");
		builder.append(ajustes005);
		builder.append(", ajustes006=");
		builder.append(ajustes006);
		builder.append(", ajustes007=");
		builder.append(ajustes007);
		builder.append(", ajustes008=");
		builder.append(ajustes008);
		builder.append(", ajustes009=");
		builder.append(ajustes009);
		builder.append(", ajustes010=");
		builder.append(ajustes010);
		builder.append(", cif=");
		builder.append(cif);
		builder.append(", fechaAny=");
		builder.append(fechaAny);
		builder.append(", fechaCarga=");
		builder.append(fechaCarga);
		builder.append(", negocio=");
		builder.append(negocio);
		builder.append(", tipoDoc=");
		builder.append(tipoDoc);
		builder.append(", totalAjustes=");
		builder.append(totalAjustes);
		builder.append("]");
		return builder.toString();
	}
	
	

}