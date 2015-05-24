package telefonica.aaee.marte.acuerdos.dao.model;

public class YearMonthEstatusVO {
	
	private Integer year;
	private Integer month;
	
	private Long peticiones;
	private Long pendientes;
	private Long rechazadas;
	private Long tramitadas;
	
	
	/**
	 * 
	 * @param year
	 * @param month
	 * @param peticiones
	 */
	public YearMonthEstatusVO(Integer year, Integer month, Long peticiones) {
		super();
		this.year = year;
		this.month = month;
		this.peticiones = peticiones;
	}



	/**
	 * @param year
	 * @param month
	 * @param peticiones
	 * @param pendientes
	 * @param rechazadas
	 * @param tramitadas
	 */
	public YearMonthEstatusVO(Integer year, Integer month, Long peticiones,
			Long pendientes, Long rechazadas, Long tramitadas) {
		super();
		this.year = year;
		this.month = month;
		this.peticiones = peticiones;
		this.pendientes = pendientes;
		this.rechazadas = rechazadas;
		this.tramitadas = tramitadas;
	}



	public Integer getYear() {
		return year;
	}



	public void setYear(Integer year) {
		this.year = year;
	}



	public Integer getMonth() {
		return month;
	}



	public void setMonth(Integer month) {
		this.month = month;
	}



	public Long getPeticiones() {
		return peticiones;
	}



	public void setPeticiones(Long peticiones) {
		this.peticiones = peticiones;
	}



	public Long getPendientes() {
		return pendientes;
	}



	public void setPendientes(Long pendientes) {
		this.pendientes = pendientes;
	}



	public Long getRechazadas() {
		return rechazadas;
	}



	public void setRechazadas(Long rechazadas) {
		this.rechazadas = rechazadas;
	}



	public Long getTramitadas() {
		return tramitadas;
	}



	public void setTramitadas(Long tramitadas) {
		this.tramitadas = tramitadas;
	}



	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("YearMonthEstatusVO [year=");
		builder.append(year);
		builder.append(", month=");
		builder.append(month);
		builder.append(", peticiones=");
		builder.append(peticiones);
		builder.append(", pendientes=");
		builder.append(pendientes);
		builder.append(", rechazadas=");
		builder.append(rechazadas);
		builder.append(", tramitadas=");
		builder.append(tramitadas);
		builder.append("]");
		return builder.toString();
	}
	
	
	
	

}
