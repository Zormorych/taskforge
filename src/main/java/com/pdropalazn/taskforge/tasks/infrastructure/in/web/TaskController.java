package com.pdropalazn.taskforge.tasks.infrastructure.in.web;

import com.pdropalazn.taskforge.tasks.domain.model.Task;
import com.pdropalazn.taskforge.tasks.domain.model.vo.TaskId;
import com.pdropalazn.taskforge.tasks.domain.port.in.CreateTaskUseCase;
import com.pdropalazn.taskforge.tasks.domain.port.in.DeleteTaskUseCase;
import com.pdropalazn.taskforge.tasks.domain.port.in.GetAllTasksUseCase;
import com.pdropalazn.taskforge.tasks.domain.port.in.GetTaskByIdUseCase;
import com.pdropalazn.taskforge.tasks.domain.port.in.UpdateTaskUseCase;
import com.pdropalazn.taskforge.tasks.application.usecase.port.dto.CreateTaskCommand;
import com.pdropalazn.taskforge.tasks.application.usecase.port.dto.UpdateTaskCommand;
import com.pdropalazn.taskforge.tasks.infrastructure.in.web.dto.CreateTaskRequest;
import com.pdropalazn.taskforge.tasks.infrastructure.in.web.dto.TaskResponse;
import com.pdropalazn.taskforge.tasks.infrastructure.in.web.dto.UpdateTaskRequest;
import com.pdropalazn.taskforge.tasks.infrastructure.in.web.mapper.TaskWebMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final CreateTaskUseCase createTaskUseCase;
    private final DeleteTaskUseCase deleteTaskUseCase;
    private final GetAllTasksUseCase getAllTasksUseCase;
    private final GetTaskByIdUseCase getTaskByIdUseCase;
    private final UpdateTaskUseCase updateTaskUseCase;
    private final TaskWebMapper mapper;

    public TaskController(CreateTaskUseCase createTaskUseCase,
                          DeleteTaskUseCase deleteTaskUseCase,
                          GetAllTasksUseCase getAllTasksUseCase,
                          GetTaskByIdUseCase getTaskByIdUseCase,
                          UpdateTaskUseCase updateTaskUseCase,
                          TaskWebMapper mapper) {
        this.createTaskUseCase = createTaskUseCase;
        this.deleteTaskUseCase = deleteTaskUseCase;
        this.getAllTasksUseCase = getAllTasksUseCase;
        this.getTaskByIdUseCase = getTaskByIdUseCase;
        this.updateTaskUseCase = updateTaskUseCase;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<TaskResponse> createTask(@Valid @RequestBody CreateTaskRequest request) {
        CreateTaskCommand command = mapper.toCommand(request);
        Task savedTask = createTaskUseCase.create(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(savedTask));
    }

    @GetMapping
    public ResponseEntity<List<TaskResponse>> getAllTasks() {
        // GetAllTasks: lists every task and maps the domain objects with the existing web mapper
        List<TaskResponse> tasks = getAllTasksUseCase.getAll().stream()
                .map(mapper::toResponse)
                .toList();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<TaskResponse> getTaskById(@PathVariable String taskId) {
        Task task = getTaskByIdUseCase.getById(TaskId.from(taskId));
        return ResponseEntity.ok(mapper.toResponse(task));
    }

    // DeleteTask: hard delete  task id
    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable String taskId) {

        deleteTaskUseCase.delete(TaskId.from(taskId));
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/{taskId}")
    public ResponseEntity<TaskResponse> updateTask(@PathVariable String taskId,
                                                   @Valid @RequestBody UpdateTaskRequest request) {
        UpdateTaskCommand command = mapper.toCommand(taskId, request);
        Task updatedTask = updateTaskUseCase.update(command);
        return ResponseEntity.ok(mapper.toResponse(updatedTask));
    }
}
