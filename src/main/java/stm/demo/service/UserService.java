package stm.demo.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stm.demo.model.Task;
import stm.demo.model.User;
import stm.demo.model.enums.Status;
import stm.demo.repository.TaskRepository;
import stm.demo.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private TaskRepository taskRepository;

    //podpunkt a
    public User insertUser(User user) {
        return userRepository.save(user);
    }

    //podpunkt b
    public List<User> selectUsers() {
        return userRepository.findAll();
    }

    //podpunkt c
    public List<User> getUserByEmailOrId(Integer userId,String email) {
        return userRepository.findByUserIdOrEmail(userId,email);
    }

    public Optional<User> getUserById(int userId) {
        return userRepository.findById(userId);
    }
    //podpunkt d
    public boolean activateUser(int userId){

        Optional<User> userOptional = getUserById(userId);
        if(userOptional.isPresent()){
            User userToActivate = userOptional.get();
            if(userToActivate.isStatus()){
                userToActivate.setStatus(false);
            }else{
                userToActivate.setStatus(true);
            }
            userRepository.save(userToActivate);     // działa jak update gdy dotyczy istniejącego w repo obiektu
        }
        return userOptional.get().isStatus();

    }

    //podpunkt e
    public boolean deleteUserById(int userId){
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isPresent()) {
            for (Task task : taskRepository.findTasksByUserId(userId)) {
                taskRepository.delete(task);
            }
            userRepository.delete(optionalUser.get());
            return true;
        }
        return false;
    }

}
