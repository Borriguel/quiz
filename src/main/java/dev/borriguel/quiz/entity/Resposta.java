package dev.borriguel.quiz.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_respostas")
public class Resposta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "quiz_id", nullable = false)
    private Quiz quiz;
    @ManyToOne
    @JoinColumn(name = "pergunta_id", nullable = false)
    private Pergunta pergunta;
    @ManyToOne
    @JoinColumn(name = "alternativa_escolhida_id", nullable = false)
    private Alternativa alternativaEscolhida;
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime dataResposta;

    @Override
    public String toString() {
        return "Resposta{" +
                "id=" + id +
                ", quiz=" + quiz +
                ", pergunta=" + pergunta +
                ", alternativaEscolhida=" + alternativaEscolhida +
                ", usuario=" + usuario +
                ", dataResposta=" + dataResposta +
                '}';
    }
}
