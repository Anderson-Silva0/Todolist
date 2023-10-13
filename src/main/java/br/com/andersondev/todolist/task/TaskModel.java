package br.com.andersondev.todolist.task;

import br.com.andersondev.todolist.exception.BusinessException;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name = "tb_tasks")
public class TaskModel {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private String description;

    @Column(length = 50)
    private String title;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private String priority;

    private UUID idUser;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public String setTitle(String title) {
        if (title.length() > 50) {
            throw new BusinessException("O campo title deve conter no máximo 50 caracteres");
        }
        return this.title = title;
    }
}
