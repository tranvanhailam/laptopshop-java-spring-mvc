package vn.hoidanit.laptopshop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User handleSaveUser(User user) {
        return this.userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    public List<User> getAllUsersByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    public User getFirstUserByEmail(String email) {
        return this.userRepository.findFirstByEmail(email);
    }

    public List<User> getAllUsersByEmailAndAddress(String email, String address) {
        return this.userRepository.findByEmailAndAddress(email, address);
    }

    public User getUserById(long id) {
        return this.userRepository.findById(id);
    }

    public void deleteUserByID(long id) {
        this.userRepository.deleteById(id);
    }

    public boolean checkEmailExists(String email) {
        return this.userRepository.existsByEmail(email);
    }

    public boolean checkEmailAndPasswordExists(String email, String password) {
        return this.userRepository.existsByEmailAndPassword(email, password);
    }

}
