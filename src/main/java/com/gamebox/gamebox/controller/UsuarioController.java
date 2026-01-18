package com.gamebox.gamebox.controller;

import com.gamebox.gamebox.dto.UsuarioCreateDTO;
import com.gamebox.gamebox.dto.UsuarioLoginDTO;
import com.gamebox.gamebox.dto.UsuarioResponseDTO;
import com.gamebox.gamebox.dto.UsuarioUpdateDTO;
import com.gamebox.gamebox.model.Usuario;
import com.gamebox.gamebox.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> cadastrar(
            @RequestBody UsuarioCreateDTO dto
    ) {
        Usuario usuario = usuarioService.cadastrar(
                dto.getNome(),
                dto.getEmail(),
                dto.getSenha(),
                dto.getImagemPerfil()
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new UsuarioResponseDTO(usuario));
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioResponseDTO> login(
            @RequestBody UsuarioLoginDTO dto
    ) {
        Usuario usuario = usuarioService.login(
                dto.getEmail(),
                dto.getSenha()
        );

        return ResponseEntity.ok(new UsuarioResponseDTO(usuario));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> buscarPorId(
            @PathVariable Long id
    ) {
        Usuario usuario = usuarioService.buscarPorId(id);

        return ResponseEntity.ok(new UsuarioResponseDTO(usuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> atualizarPerfil(
            @PathVariable Long id,
            @RequestBody UsuarioUpdateDTO dto
    ) {
        Usuario usuario = usuarioService.atualizarPerfil(
                id,
                dto.getNome(),
                dto.getImagemPerfil()
        );

        return ResponseEntity.ok(new UsuarioResponseDTO(usuario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(
            @PathVariable Long id
    ) {
        usuarioService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
