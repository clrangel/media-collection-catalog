package br.com.catalogo.mediacollectioncatalog.midia;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "midias")
// Define estratégia de herança no banco de dados.
// JOINED cria uma tabela para cada subclasse, mantendo o modelo normalizado
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public abstract class Midia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(nullable = false, length = 100)
    private String titulo;

    private Integer anoLancamento;

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
