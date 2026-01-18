package com.gamebox.gamebox.dto;

public class UsuarioCreateDTO {

    private String nome;
    private String email;
    private String senha;
    private String imagemPerfil;

    public String getNome () {
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
}
