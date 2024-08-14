package dev.borriguel.quiz.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_quiz")
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String titulo;
    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pergunta> perguntas = new ArrayList<>();
    private String descricao;
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDate dataCriacao;
    @ManyToOne
    @JoinColumn(name = "criador_id")
    private Usuario criador;

    @Override
    public String toString() {
        return "Quiz{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", perguntas=" + perguntas +
                ", descricao='" + descricao + '\'' +
                ", dataCriacao=" + dataCriacao +
                ", criador=" + criador +
                '}';
    }
}
