package ru.dorogova.tasks.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dorogova.tasks.aspects.TrackUserActions;
import ru.dorogova.tasks.model.Task;
import ru.dorogova.tasks.repository.TaskRepository;
import java.util.List;
import java.util.Optional;

/**
 * Класс, обеспечивающий функционирование "Задач"
 */
@AllArgsConstructor
@Service
public class TaskService implements IntServiceTask {
    /**
     *Ссылка на интерфейс, работающий с данными
     */
    private final TaskRepository taskRepository;

    /**
     * Метод добавления новой задачи
     * @param task задача, которую необходимо добавить
     */
    @Override
    @TrackUserActions
    public Task addTask(Task task) {
        return taskRepository.save(task);
    }

    /**
     *Метод получения всех существующих задач
     */
    @Override
    @TrackUserActions
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    /**
     * Метод поиска задачи по ее идентификатору
     * @param id идентификатор задачи
     * @return задача
     */
    @Override
    @TrackUserActions
    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    /**+
     * Метод изменения названия и описания задачи
     * @param taskDetails задача, которую необходимо изменить
     * @return измененная задача
     */
    @Override
    @TrackUserActions
    public Task updateTask(Long id, Task taskDetails) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.setTitle(taskDetails.getTitle());
            task.setDescription(taskDetails.getDescription());
            return taskRepository.save(task);
        } else {
            throw new IllegalArgumentException("Task not found with id: " + id);
        }
    }

    /**
     * Метод удаления задачи
     * @param id идентификатор задачи
     */
    @Override
    @TrackUserActions
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    /**
     * Метод изменения статуса задачи
     * @param id идентификатор задачи
     * @param task задача, чей статуc
     * @return сохраненная задача с измененным статусом
     */
    @Override
    @TrackUserActions
    public Task updateTaskStatus(Long id, Task task) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            Task t = optionalTask.get();
            t.setStatus(task.getStatus());
            return taskRepository.save(t);
        } else {
            throw new IllegalArgumentException("Task not found with id: " + id);
        }
    }

    /**
     * Метод получения всех задач по статусу
     * @param status статус задачи
     * @return список задач по статусу
     */
    @Override
    @TrackUserActions
    public List<Task> getTasksByStatus(Task.Status status) {
        return taskRepository.getTasksByStatus(status);
    }

    @Override
    @Transactional
    @TrackUserActions
    public List<Task> addTwoTasks(Task task1, Task task2) {
        Task savedTask1 = taskRepository.save(task1);
        Task savedTask2 = taskRepository.save(task2);
        return List.of(savedTask1, savedTask2);
    }

}
