package ru.dorogova.tasks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dorogova.tasks.model.Task;

import java.util.List;

/**
 * Интерфейс, расширяющий другой интерфейс JpaRepository,
 * предоставляя готовые методы операций CRUD над сущностями
 * (Create, Read, Update, Delete)
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    /**
     * метод, позволяющий получить список задач с определенным статусом.
     * Он принимает в качестве аргумента объект enum и возвращает список задач,
     * у которых статус совпадает с заданным
     * @param status - статус заметки
     * @return список заметок, имеющих запрошенный статус
     */
    List<Task> getTasksByStatus(Task.Status status);
}
