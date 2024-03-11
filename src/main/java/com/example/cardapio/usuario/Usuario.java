package com.example.cardapio.usuario;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "usuarios")
@Entity(name = "usuarios")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String senha;
    @Column(nullable = false)
    private String usuario;
    public Usuario(UsuarioRequestDTO data)
    {
        this.senha = data.senha();
        this.usuario = data.usuario();
    }
}
