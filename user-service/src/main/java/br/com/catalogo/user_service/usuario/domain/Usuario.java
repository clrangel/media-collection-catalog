package br.com.catalogo.user_service.usuario.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(nullable = false, length = 100)
    private String nome;

    @NotNull
    @Size(max = 150)
    @Column(nullable = false, unique = true, length = 150)
    private String email;

    @NotNull
    @Size(max = 255)
    @Column(nullable = false)
    private String senha;

    @NotNull
    @Column(nullable = false)
    private Instant dataCadastro;

    // Executado automaticamente antes de inserir no banco.
    // Usado para preencher campos como dataCadastro sem precisar setar manualmente
    @PrePersist
    public void prePersist() {
        if (this.dataCadastro == null) {
            this.dataCadastro = Instant.now();
        }
    }
}
