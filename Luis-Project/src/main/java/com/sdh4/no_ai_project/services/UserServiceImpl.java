package com.sdh4.no_ai_project.services;

import com.sdh4.no_ai_project.entities.User;
import com.sdh4.no_ai_project.repositories.UserRepository;
import com.sdh4.no_ai_project.services.exceptions.BadDataException;
import com.sdh4.no_ai_project.services.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User createUser(User user) throws BadDataException {
        if (user == null || user.getUsername() == null || user.getPassword() == null) {
            throw new BadDataException("Invalid user data");
        }
        return userRepository.save(user);
    }

    @Override
    public User resetPassword(Long id, String newPassword) throws NotFoundException, BadDataException {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
        if (newPassword == null || newPassword.isEmpty()) {
            throw new BadDataException("Password cannot be empty");
        }
        user.setPassword(newPassword);
        return userRepository.save(user);
    }

    @Override
    public User toggleUnlocked(Long id) throws NotFoundException {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
        user.setUnlocked(!user.isUnlocked());
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) throws NotFoundException {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
        userRepository.delete(user);
    }
}
