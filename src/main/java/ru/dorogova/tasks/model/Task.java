package ru.dorogova.tasks.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.dialect.unique.CreateTableUniqueDelegate;

import java.time.LocalDate;

/**
 * Класс, определяющий характеристики задачи
 */
@Data
@Entity
@Table(name = "tasks")
public class Task {

    /**
     *Перечисление статуса задачи
     */
    public enum Status{
        NOT_STARTED{
            @Override
            public String toString() {
                return "NOT_STARTED";
            }
        },
        IN_PROGRESS{
            @Override
            public String toString() {
                return "IN_PROGRESS";
            }
        },
        COMPLETED{
            @Override
            public String toString() {
                return "COMPLETED";
            }
        }
    }

    /**
     *Идентификатор задачи
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     *Название задачи
     */
    @Column(name = "title", nullable = false)
    private String title;

    /**
     *Описание задачи
     */
    @Column(name = "description", nullable = false)
    private String description;

    /**
     * Статус задачи
     */
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    /**
     * Дата создания задачи
     */
    @Column(name = "creation_Date")
    private LocalDate creation_Date = LocalDate.now();


}
