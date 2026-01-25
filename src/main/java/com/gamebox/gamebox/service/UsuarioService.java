package com.gamebox.gamebox.service;

import com.gamebox.gamebox.exception.EmailJaCadastrado;
import com.gamebox.gamebox.exception.RecursoNaoEncontrado;
import com.gamebox.gamebox.model.Usuario;
import com.gamebox.gamebox.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario cadastrar(String nome, String email, String senha, String imagemPerfil) {

        if (usuarioRepository.existsByEmail(email)) {
            throw new EmailJaCadastrado("Email já cadastrado");
        }

        Usuario usuario = new Usuario(
                nome,
                email,
                senha,
                imagemPerfil
        );

        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontrado("Usuário não encontrado"));
    }

    public Usuario login(String email, String senha) {

        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!usuario.getSenha().equals(senha)) {
            throw new RuntimeException("Senha inválida");
        }

        return usuario;
    }

    public Usuario atualizarPerfil(Long id, String nome, String imagemPerfil) {
        Usuario usuario = buscarPorId(id);

        usuario.atualizarPerfil(nome, imagemPerfil);

        return usuarioRepository.save(usuario);
    }

    public Usuario alterarSenha(Long id, String novaSenha) {
        Usuario usuario = buscarPorId(id);

        usuario.alterarSenha(novaSenha);

        return usuarioRepository.save(usuario);
    }

    public void deletar(Long id) {
        Usuario usuario = buscarPorId(id);

        usuarioRepository.delete(usuario);
    }
}
