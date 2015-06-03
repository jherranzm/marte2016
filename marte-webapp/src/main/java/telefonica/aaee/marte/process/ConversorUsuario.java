package telefonica.aaee.marte.process;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import telefonica.aaee.dao.maestras.model.Role;
import telefonica.aaee.dao.maestras.model.Usuario;
import telefonica.aaee.dao.maestras.service.RoleService;
import telefonica.aaee.dao.maestras.service.UsuarioService;
import telefonica.aaee.marte.acuerdos.dao.model.MarteUsuario;
import telefonica.aaee.marte.acuerdos.dao.service.MarteUsuarioService;

@Component
public class ConversorUsuario {
	
	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private MarteUsuarioService marteUsuarioService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public void conversorUsuarios(){
		
		List<MarteUsuario> usuariosMarteAntiguo = marteUsuarioService.findAll();
		
		for(MarteUsuario usuarioAntiguo : usuariosMarteAntiguo){
			
			logger.info(usuarioAntiguo.toString());
			
			Role roleUsuario = roleService.findByNombre("ROLE_USER").get(0);
			
			Usuario usuario = usuarioService.findByUsername(usuarioAntiguo.getCodUsuario());
			if(usuario == null){
				usuario = new Usuario();
				usuario.setApellido1(usuarioAntiguo.getApellido1() == null ? "": usuarioAntiguo.getApellido1());
				usuario.setApellido2(usuarioAntiguo.getApellido2() == null ? "": usuarioAntiguo.getApellido2());
				usuario.setNombre(usuarioAntiguo.getNombre() == null ? "": usuarioAntiguo.getNombre());
				String pass = usuarioAntiguo.getPass() == null ? "": usuarioAntiguo.getPass();
				usuario.setPassword(passwordEncoder.encode(pass));
				usuario.setUsername(usuarioAntiguo.getCodUsuario() == null ? "": usuarioAntiguo.getCodUsuario());
				usuario.setEmail(usuarioAntiguo.getEmail());
				usuario.setEnabled(true);
				Set<Role> roles = new HashSet<Role>();
				roles.add(roleUsuario);
				usuario.setRoles(roles);
				
				Usuario creado = usuarioService.create(usuario);
				
				logger.info(String.format("Creado! [%s]", creado.toString()));
			}
			
			
		}
	}

}
