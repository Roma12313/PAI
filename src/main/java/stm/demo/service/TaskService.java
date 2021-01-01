package stm.demo.service;

import javafx.scene.control.TableColumn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import stm.demo.model.Task;
import stm.demo.model.User;
import stm.demo.model.enums.Status;
import stm.demo.model.enums.Type;
import stm.demo.repository.TaskRepository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class TaskService {
    @Autowired
    TaskRepository taskRepository;
    //podpunkt g
    public List<Task> selectTasks(){
        return taskRepository.findAll(Sort.by(Sort.Direction.DESC, "dateAdded"));
    }
    //podpunkt h
    public List<Task> getTaskByTytleOrStatusOrType(String title,  Type type,Status status) {
        return taskRepository.findByTitleOrStatusOrType(title,type,status);
    }
    //podpunkt f
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }
    public Optional<Task> getTaskById(int taskId){
        return  taskRepository.findById(taskId);
    }

    //podpunkt j
    public boolean deleteTaskById(int taskId){
        AtomicBoolean isDeleted = new AtomicBoolean(false);
        getTaskById(taskId).ifPresent(user -> {
            taskRepository.deleteById(taskId);
            isDeleted.set(true);
        });
        return isDeleted.get();
    }
    //podpunkt i
    public Task changeTaskStatus(int taskId, Status status) {
        Task task = taskRepository.findByTaskId(taskId);
        task.setStatus(status);
        return taskRepository.save(task);
    }


}
