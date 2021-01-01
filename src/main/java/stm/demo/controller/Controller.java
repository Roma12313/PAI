package stm.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import stm.demo.model.Task;
import stm.demo.model.User;
import stm.demo.model.enums.Status;
import stm.demo.model.enums.Type;
import stm.demo.service.TaskService;
import stm.demo.service.UserService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Stack;

@RestController
@EnableSwagger2
public class Controller {
    private UserService userService;
    private TaskService taskService;

    @Autowired
    public Controller(UserService userService, TaskService taskService) {
        this.userService = userService;
        this.taskService = taskService;
    }
    //a
    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userService.selectUsers();
    }
    //b
    @PostMapping("/users/create")
    public User createUser(
            @RequestParam("name") String name,
            @RequestParam("lastName") String lastName,
            @RequestParam("email") String email,
            @RequestParam("password") String password
    ){
        return userService.insertUser(new User(name,lastName,email,password,false, LocalDateTime.now()));
    }
    //c
    @GetMapping("users/IdOrEmail")
    public List<User> getByEmail(Integer userId,String email){
        return userService.getUserByEmailOrId(userId,email);
    }
    //d
    @PutMapping("/users/activate/id={userId}")
    public boolean activateUser(
            @PathVariable("userId") int userId
    ){
        return userService.activateUser(userId);
    }
    //e
    @DeleteMapping("/users/delete")
    public boolean deleteUserById(
            @RequestParam("userId") int userId
    ){
        return userService.deleteUserById(userId);
    }
    //f
    @PostMapping("/tasks/create")
    public Task createTask(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("type") Type type,
            @RequestParam("status") Status status,
            @RequestParam("user_id") User userId

    ){
        return taskService.createTask(new Task(title,description,LocalDateTime.now(),type,status,userId));
    }
    //g
    @GetMapping("/tasks/date")
    public List<Task> getAllTasks(){
        return taskService.selectTasks();
    }
    //h
    @GetMapping("tasks/TitleOrStatusOrType")
    public List<Task> getByTitleOrStatusOrType(String title, Type type, Status status){
        return taskService.getTaskByTytleOrStatusOrType(title,type,status);
    }

    //j
    @DeleteMapping("/tasks/delete")
    public boolean deleteTaskById(
            @RequestParam("taskId") int taskId
    ){
        return taskService.deleteTaskById(taskId);
    }
    //i
    @PutMapping("/task/status/id={taskId}")
    public Task changeTaskStatus(
            @PathVariable("taskId") int taskId,
            @RequestParam("status") Status status
    ) {
        return taskService.changeTaskStatus(taskId,status);
    }


}
