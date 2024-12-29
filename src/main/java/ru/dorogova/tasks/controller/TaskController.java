package ru.dorogova.tasks.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.dorogova.tasks.model.Task;
import ru.dorogova.tasks.service.IntServiceTask;

import java.util.List;
import java.util.Optional;

/**
 * класс, обрабатывающий входящие HTTP-запросы и возвращающий ответы по задачам
 */
@RestController
@RequestMapping("/tasks")
@AllArgsConstructor
public class TaskController {

    /**
     * Ссылка на интерфейс, работающий с функционированием "Задач"
     */
    private final IntServiceTask taskService;

    /**
     * Метод добавления новой задачи
     */
    @PostMapping
    public Task addTask(@RequestBody Task task){
        return taskService.addTask(task);
    }

    /**
     * Метод получения всех существующих задач
     */
    @GetMapping
    public List<Task> getAllTasks(){
        return taskService.getAllTasks();
    }

    /**
     * Метод получения задачи по ее идентификатору
     */
    @GetMapping("/{id}")
    public Optional<Task> getTaskById(@PathVariable Long id){
        return taskService.getTaskById(id);
    }

    /**
     * Метод изменения названия и описания задачи
     * @param taskDetails задача, которую необходимо изменить
     */
    @PutMapping("/edit/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task taskDetails){
        return taskService.updateTask(id, taskDetails);
    }

    /**
     * Метод удаления задачи
     */
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
    }

    /**
     * Метод изменения статуса задачи
     * @param task задача, чей статус нужно изменить
     */
    @PutMapping("/{id}")
    public Task updateTaskStatus(@PathVariable Long id, @RequestBody Task task){
        return taskService.updateTaskStatus(id, task);
    }

    /**
     * Метод получения всех задач по статусу
     * @param status статус задачи
     */
    @GetMapping("/status/{status}")
    public List<Task> getTasksByStatus(@PathVariable Task.Status status){
        return taskService.getTasksByStatus(status);
    }

    @PostMapping("/addTwo")
    public void addTwoTasks(@RequestBody List<Task> tasks){
        taskService.addTwoTasks(tasks.get(0), tasks.get(1));
    }

}
