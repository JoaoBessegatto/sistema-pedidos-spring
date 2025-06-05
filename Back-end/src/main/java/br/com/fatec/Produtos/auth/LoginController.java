package br.com.fatec.Produtos.auth;

import br.com.fatec.Produtos.dto.request.UsuarioRequestDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class LoginController {

	@Autowired
	private LoginService loginService;
	@Autowired
	private UsuarioService usuarioService;


	@PostMapping("/login")
	public ResponseEntity<String> logar(@RequestBody Login login) {

		String token = loginService.logar(login);
		return new ResponseEntity<>(token, HttpStatus.OK);
		
	}

	@PostMapping("/cadastrar")
	public ResponseEntity<Void> cadastrar(@RequestBody @Valid UsuarioRequestDTO dto) {
		usuarioService.cadastrar(dto);
		return ResponseEntity.ok().build();
	}

}
