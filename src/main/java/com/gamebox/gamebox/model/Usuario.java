package com.gamebox.gamebox.model;

import jakarta.persistence.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "email", nullable = false, unique = true, length = 150)
    private String email;

    @Column(name = "senha", nullable = false, length = 255)
    private String senha;

    @Column(name = "imagem_perfil", length = 255)
    private String imagemPerfil;

    @Column(name = "criado_em", nullable = false, updatable = false)
    private LocalDateTime criadoEm;

    @Column(name = "atualizado_em")
    private LocalDateTime atualizadoEm;

    protected Usuario () {}

    public Usuario(String nome, String email, String senha, String imagemPerfil) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.imagemPerfil = imagemPerfil;
        this.criadoEm = LocalDateTime.now();
        this.atualizadoEm = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }
    public String getNome() {
        return nome;
    }
    public String getEmail() {
        return email;
    }
    public String getSenha() {
        return senha;
    }
    public String getImagemPerfil() {
        return imagemPerfil;
    }
    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }
    public LocalDateTime getAtualizadoEm() { return atualizadoEm; }

    public void atualizarPerfil(String nome, String imagemPerfil) {
        this.nome = nome;
        this.imagemPerfil = imagemPerfil;
        this.atualizadoEm = LocalDateTime.now();
    }

    public void alterarSenha(String novaSenha) {
        this.senha = novaSenha;
        this.atualizadoEm = LocalDateTime.now();
    }
}
