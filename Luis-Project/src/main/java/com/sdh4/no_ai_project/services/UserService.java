package com.sdh4.no_ai_project.services;

import com.sdh4.no_ai_project.entities.User;
import com.sdh4.no_ai_project.services.exceptions.BadDataException;
import com.sdh4.no_ai_project.services.exceptions.NotFoundException;

public interface UserService {
    User createUser(User user) throws BadDataException;
    User resetPassword(Long id, String newPassword) throws NotFoundException, BadDataException;
    User toggleUnlocked(Long id) throws NotFoundException;
    void deleteUser(Long id) throws NotFoundException;
}
