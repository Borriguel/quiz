package dev.borriguel.quiz.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_alternativas")
public class Alternativa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String proposicao;
    private Boolean correta;
    @ManyToOne
    @JoinColumn(name = "pergunta_id")
    private Pergunta pergunta;

    @Override
    public String toString() {
        return "Alternativa{" +
                "id=" + id +
                ", proposicao='" + proposicao + '\'' +
                ", correta=" + correta +
                '}';
    }
}
