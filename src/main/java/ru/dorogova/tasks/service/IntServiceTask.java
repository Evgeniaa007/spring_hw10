package ru.dorogova.tasks.service;

import ru.dorogova.tasks.model.Task;

import java.util.List;
import java.util.Optional;

/**
 * Интерфейс, который реализует класс TaskService
 */
public interface IntServiceTask {
    /**
     * Метод добавления новой задачи
     * @param task задача, которую необходимо добавить
     */
    public Task addTask(Task task);

    /**
     *Метод получения всех существующих задач
     */
    public List<Task>getAllTasks();

    /**
     * Метод поиска задачи по ее идентификатору
     * @param id идентификатор задачи
     * @return задача
     */
    public Optional<Task> getTaskById(Long id);

    /**+
     * Метод изменения названия и описания задачизадачи
     * @param taskDetails задача, которую необходимо изменить
     * @return измененная задача
     */
    public Task updateTask(Long id, Task taskDetails);

    /**
     * Метод удаления задачи
     * @param id идентификатор задачи
     */
    public void deleteTask(Long id);

    /**
     * Метод изменения статуса задачи
     * @param id идентификатор задачи
     * @param task задача, чей статус нужно изменить
     * @return сохраненная задача с измененным статусом
     */
    public Task updateTaskStatus(Long id, Task task);

    /**
     * Метод получения всех задач по статусу
     * @param status статус задачи
     * @return список задач по статусу
     */
    public List<Task> getTasksByStatus(Task.Status status);

    /**
     *
     * @param task1 тело первой задачи
     * @param task2 второй задачи
     * @return возвращает лист тасков
     */
    public List<Task> addTwoTasks(Task task1, Task task2);

}
