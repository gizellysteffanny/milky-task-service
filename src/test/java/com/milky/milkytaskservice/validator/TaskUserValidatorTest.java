package com.milky.milkytaskservice.validator;

import com.milky.milkytaskservice.exception.TaskException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
public class TaskUserValidatorTest {
    @InjectMocks
    private TaskUserValidator taskUserValidator;

    @Test
    @DisplayName("should not throw exception when task user is valid to create task")
    public void shouldNotThrowExceptionWhenTaskUserIsValidToCreateTask() {
        taskUserValidator.userCanCreateTask("1");
    }

    @Test
    @DisplayName("should throw exception when task user is invalid to create task")
    public void shouldThrowExceptionWhenTaskUserIsInvalidToCreateTask() {
        assertThrows(TaskException.class, () -> {
            taskUserValidator.userCanCreateTask("");
        });
    }

    @Test
    @DisplayName("should throw exception when task user is null to create task")
    public void shouldThrowExceptionWhenTaskUserIsNullToCreateTask() {
        assertThrows(TaskException.class, () -> {
            taskUserValidator.userCanCreateTask(null);
        });
    }

    @Test
    @DisplayName("should not throw exception when task user is valid to update task")
    public void shouldNotThrowExceptionWhenTaskUserIsValidToUpdateTask() {
        taskUserValidator.userCanUpdateTask("1", "1");
    }

    @Test
    @DisplayName("should throw exception when task user is invalid to update task")
    public void shouldThrowExceptionWhenTaskUserIsInvalidToUpdateTask() {
        assertThrows(TaskException.class, () -> {
            taskUserValidator.userCanUpdateTask("1", "2");
        });
    }

    @Test
    @DisplayName("should throw exception when task user is null to update task")
    public void shouldThrowExceptionWhenTaskUserIsNullToUpdateTask() {
        assertThrows(TaskException.class, () -> {
            taskUserValidator.userCanUpdateTask(null, "1");
        });
    }
}
