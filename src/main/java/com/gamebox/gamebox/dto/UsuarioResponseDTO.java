package com.gamebox.gamebox.dto;

import com.gamebox.gamebox.model.Usuario;

import java.time.LocalDateTime;

public class UsuarioResponseDTO {

    private Long id;
    private String nome;
    private String email;
    private String imagemPerfil;
    private LocalDateTime criadoEm;

    public UsuarioResponseDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.imagemPerfil = usuario.getImagemPerfil();
        this.criadoEm = usuario.getCriadoEm();
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getImagemPerfil() { return imagemPerfil; }
    public LocalDateTime getCriadoEm() { return criadoEm; }
}
