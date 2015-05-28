package telefonica.aaee.informes.model;

public class InitMessage {

	private String from;
	private String to;
	private String subject;
	private long numReinicios;
	private String ahora;
	private long numAcuerdos;
	
	
	
	

	public InitMessage() {
		super();
	}
	
	
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public long getNumReinicios() {
		return numReinicios;
	}
	public void setNumReinicios(long numReinicios) {
		this.numReinicios = numReinicios;
	}


	public String getAhora() {
		return ahora;
	}


	public void setAhora(String ahora) {
		this.ahora = ahora;
	}


	public long getNumAcuerdos() {
		return numAcuerdos;
	}


	public void setNumAcuerdos(long numAcuerdos) {
		this.numAcuerdos = numAcuerdos;
	}

}
