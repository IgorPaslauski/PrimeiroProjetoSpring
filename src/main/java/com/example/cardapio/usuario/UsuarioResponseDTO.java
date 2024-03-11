package com.example.cardapio.usuario;

public record UsuarioResponseDTO(Long id, String usuario, String senha) {
    public UsuarioResponseDTO(Usuario usuario) {
        this(usuario.getId(), usuario.getUsuario(), usuario.getSenha());
    }
}
