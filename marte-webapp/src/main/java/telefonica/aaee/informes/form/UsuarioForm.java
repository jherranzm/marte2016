package telefonica.aaee.informes.form;

import java.io.Serializable;
import java.util.Arrays;

import telefonica.aaee.dao.maestras.model.Role;

public class UsuarioForm   implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private long id;
	
	private String username;
	
	// 2014-11-19: Implementación de creación de passwords.
	private String password1;
	private String password2;

	private String nombre;
	private String apellido1;
	private String apellido2;

	private String email;
	private boolean enabled;
	private String comentarios;
	
	private Role[] roles;
	
	
	public UsuarioForm() {
		super();
	}
	
	
	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public Role[] getRoles() {
		return roles;
	}


	public void setRoles(Role[] roles) {
		this.roles = roles;
	}


	public String getApellido1() {
		return apellido1;
	}


	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}


	public String getApellido2() {
		return apellido2;
	}


	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public boolean isEnabled() {
		return enabled;
	}


	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}


	public String getComentarios() {
		return comentarios;
	}


	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}


	public String getPassword1() {
		return password1;
	}


	public void setPassword1(String password1) {
		this.password1 = password1;
	}


	public String getPassword2() {
		return password2;
	}


	public void setPassword2(String password2) {
		this.password2 = password2;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UsuarioForm [id=");
		builder.append(id);
		builder.append(", username=");
		builder.append(username);
		builder.append(", nombre=");
		builder.append(nombre);
		builder.append(", apellido1=");
		builder.append(apellido1);
		builder.append(", apellido2=");
		builder.append(apellido2);
		builder.append(", email=");
		builder.append(email);
		builder.append(", enabled=");
		builder.append(enabled);
		builder.append(", comentarios=");
		builder.append(comentarios);
		builder.append(", roles=");
		builder.append(Arrays.toString(roles));
		builder.append("]");
		return builder.toString();
	}





}