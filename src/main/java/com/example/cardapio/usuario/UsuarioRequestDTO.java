package com.example.cardapio.usuario;

import lombok.NonNull;

public record UsuarioRequestDTO(@NonNull String usuario, @NonNull String senha) {
}
