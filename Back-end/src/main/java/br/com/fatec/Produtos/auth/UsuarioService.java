package br.com.fatec.Produtos.auth;

import br.com.fatec.Produtos.dto.request.UsuarioRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void cadastrar(UsuarioRequestDTO dto){
        Usuario usuario = new Usuario();
        usuario.setUsername(dto.getUsername());
        String senhaCriptografada = passwordEncoder.encode(dto.getPassword());
        usuario.setPassword(senhaCriptografada);
        usuario.setRole("USER");
        usuarioRepository.save(usuario);
    }

}
