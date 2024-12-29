package ru.dorogova.tasks.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.dorogova.tasks.model.Task;
import ru.dorogova.tasks.repository.TaskRepository;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskServiceWithAnnotationsUnitTests {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    @Test
    public void addTaskTest(){
        Task task1 = new Task();
        task1.setTitle("Test tittle");
        task1.setDescription("Test Description");
        task1.setStatus(Task.Status.NOT_STARTED);
        List<Task> tasks = Collections.singletonList(task1);
        when(taskRepository.save(task1)).thenReturn(task1);
        List<Task> tasks2 = new ArrayList<>();
        tasks2.add(taskService.addTask(task1));
        assertEquals(tasks.getFirst(), tasks2.getFirst());
    }

    @Test
    public void getAllTasksTest(){
        Task task1 = new Task();
        task1.setTitle("Test tittle");
        task1.setDescription("Test Description");
        task1.setStatus(Task.Status.NOT_STARTED);
        Task task2 = new Task();
        task2.setTitle("Test tittle2");
        task2.setDescription("Test Description2");
        task2.setStatus(Task.Status.COMPLETED);

        List<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);
        when(taskRepository.findAll()).thenReturn(tasks);

        List<Task> tasks2 = taskService.getAllTasks();

        assertEquals(tasks, tasks2);
    }

    @Test
    public void getTaskByIdTest(){
        Task task1 = new Task();
        task1.setTitle("Test tittle1");
        task1.setDescription("Test Description1");
        task1.setStatus(Task.Status.NOT_STARTED);
        Task task2 = new Task();
        task2.setTitle("Test tittle2");
        task2.setDescription("Test Description2");
        task2.setStatus(Task.Status.COMPLETED);


        List<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);

        when(taskRepository.findById(2L)).thenReturn(Optional.of(task2));

        Optional<Task> task = taskService.getTaskById(2L);

        assertEquals(Optional.of(task2), task);
    }

    @Test
    public void updateTaskTest(){
        Task task = new Task();
        task.setTitle("Test tittle");
        task.setDescription("Test Description");
        task.setStatus(Task.Status.NOT_STARTED);
        Task updateTask = new Task();
        updateTask.setTitle("Updated tittle");
        updateTask.setDescription("Updated Description");
        updateTask.setStatus(Task.Status.NOT_STARTED);

        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        when(taskRepository.save(task)).thenReturn(task);

        Task testTask = taskService.updateTask(1L, updateTask);
        assertEquals(updateTask.getId(), testTask.getId());
        assertEquals(updateTask.getTitle(), testTask.getTitle());
        assertEquals(updateTask.getDescription(), testTask.getDescription());
    }

    @Test
    public void deleteTaskTest(){
        Task task = new Task();
        task.setTitle("Test tittle");
        task.setDescription("Test Description");
        task.setStatus(Task.Status.NOT_STARTED);

        //when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        taskService.deleteTask(1L);
        verify(taskRepository, times(1)).deleteById(1L);
    }

    @Test
    public void updateTaskStatusTest(){
        Task task = new Task();
        task.setTitle("Test tittle");
        task.setDescription("Test Description");
        task.setStatus(Task.Status.NOT_STARTED);

        Task updateTask = new Task();
        updateTask.setTitle("Test tittle");
        updateTask.setDescription("Test Description");
        updateTask.setStatus(Task.Status.COMPLETED);

        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        when(taskRepository.save(task)).thenReturn(task);

        Task testTask = taskService.updateTaskStatus(1L, updateTask);
        assertEquals(updateTask.getStatus(), testTask.getStatus());
    }

    @Test
    public void getTaskByStatusTest(){
        Task task1 = new Task();
        task1.setTitle("Test tittle1");
        task1.setDescription("Test Description1");
        task1.setStatus(Task.Status.NOT_STARTED);
        Task task2 = new Task();
        task2.setTitle("Test tittle2");
        task2.setDescription("Test Description2");
        task2.setStatus(Task.Status.COMPLETED);
        Task task3 = new Task();
        task3.setTitle("Test tittle3");
        task3.setDescription("Test Description3");
        task3.setStatus(Task.Status.NOT_STARTED);

        List<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);

        List<Task> taskTest = new ArrayList<>();

        when(taskRepository.getTasksByStatus(Task.Status.NOT_STARTED)).thenReturn(taskTest);

        List<Task> taskResult = taskService.getTasksByStatus(Task.Status.NOT_STARTED);

        assertEquals(taskTest, taskResult);
    }

}
