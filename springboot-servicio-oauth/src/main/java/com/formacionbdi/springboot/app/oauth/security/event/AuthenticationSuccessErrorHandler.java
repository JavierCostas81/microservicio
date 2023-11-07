package com.formacionbdi.springboot.app.oauth.security.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import com.formacionbdi.springboot.app.commons.usuarios.models.entity.Usuario;
import com.formacionbdi.springboot.app.oauth.services.IUsuarioService;

import feign.FeignException;

@Component
public class AuthenticationSuccessErrorHandler implements AuthenticationEventPublisher{
	private Logger log = LoggerFactory.getLogger(AuthenticationSuccessErrorHandler.class);
	
	@Autowired
	private IUsuarioService iUsuarioService; 
	@Override
	public void publishAuthenticationSuccess(Authentication authentication) {
		if (authentication.getDetails() instanceof WebAuthenticationDetails ) {
			return; 
		}
		Usuario usuario = iUsuarioService.findByUsername(authentication.getName());
		if (usuario.getIntentos() == null || usuario.getIntentos() > 0) {
			log.info("Seteando intentos en 0");
			usuario.setIntentos(0);
		}
		iUsuarioService.update(usuario, usuario.getId());
		UserDetails user = (UserDetails) authentication.getPrincipal();
		log.info("Success Login: " + user.getUsername());
	}

	@Override
	public void publishAuthenticationFailure(AuthenticationException exception, Authentication authentication) {
		try {
		Usuario usuario = iUsuarioService.findByUsername(authentication.getName());
		if (usuario.getIntentos() == null) {
			log.info("Seteando intentos en 0");
			usuario.setIntentos(0);
		}
		
		usuario.setIntentos(usuario.getIntentos()+1);
		log.info("Ampliando intentos en 1 (" + usuario.getIntentos() + ")");
		if (usuario.getIntentos() > 2) {
			usuario.setEnabled(false);
			log.error(String.format("@|bold,red Usuario %s deshabilitado|@", usuario.getNombre()));
		}
		iUsuarioService.update(usuario, usuario.getId());
		}
		catch (FeignException e) {
			log.error(String.format("El usuario %s no existe en la DB", authentication.getName()));
		}
		String mensaje = "Error en el Login: " + exception.getMessage();
		log.error(mensaje);
	}
	
}
