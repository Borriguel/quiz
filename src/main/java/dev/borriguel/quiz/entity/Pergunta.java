package dev.borriguel.quiz.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_perguntas")
public class Pergunta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String enunciado;
    @Enumerated(EnumType.STRING)
    private Dificuldade dificuldade;
    @OneToMany(mappedBy = "pergunta",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Alternativa> alternativas = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    @Override
    public String toString() {
        return "Pergunta{" +
                "id=" + id +
                ", enunciado='" + enunciado + '\'' +
                ", dificuldade=" + dificuldade +
                ", alternativas=" + alternativas +
                '}';
    }
}
