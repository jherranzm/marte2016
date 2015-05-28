package telefonica.aaee.informes.form;

import java.io.Serializable;

public class RoleForm   implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private long id;
	
	private String nombre;
	private String comentarios;

	public RoleForm() {
		super();
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getComentarios() {
		return comentarios;
	}
	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}
	

	@Override
	public String toString() {
		StringBuilder builder2 = new StringBuilder();
		builder2.append("RoleForm [id=");
		builder2.append(getId());
		builder2.append(", nombre=");
		builder2.append(nombre);
		builder2.append(", comentarios=");
		builder2.append(comentarios);
		builder2.append("]");
		return builder2.toString();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	
	
	
	

}