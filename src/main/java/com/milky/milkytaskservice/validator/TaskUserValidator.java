package com.milky.milkytaskservice.validator;

import com.milky.milkytaskservice.exception.TaskException;
import org.springframework.stereotype.Component;

@Component
public class TaskUserValidator {

    public void userCanCreateTask(String user) {
        if (!userIsValid(user)) {
            throw new TaskException("User not allowed to create task");
        }
    }

    public void userCanUpdateTask(String user, String userFromTaskSaved) {
        if (!userIsValid(user) || !userIsValid(userFromTaskSaved) || !user.equals(userFromTaskSaved)) {
            throw new TaskException("User not allowed to update this task");
        }
    }

    private boolean userIsValid(String user) {
        return user != null && !user.isBlank() && !user.isEmpty();
    }
}
