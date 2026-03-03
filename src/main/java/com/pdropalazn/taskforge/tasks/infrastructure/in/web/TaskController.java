package com.pdropalazn.taskforge.tasks.infrastructure.in.web;

import com.pdropalazn.taskforge.tasks.domain.model.Task;
import com.pdropalazn.taskforge.tasks.domain.model.vo.TaskId;
import com.pdropalazn.taskforge.tasks.domain.port.in.CreateTaskUseCase;
import com.pdropalazn.taskforge.tasks.domain.port.in.GetTaskByIdUseCase;
import com.pdropalazn.taskforge.tasks.domain.port.in.dto.CreateTaskCommand;
import com.pdropalazn.taskforge.tasks.infrastructure.in.web.dto.CreateTaskRequest;
import com.pdropalazn.taskforge.tasks.infrastructure.in.web.dto.TaskResponse;
import com.pdropalazn.taskforge.tasks.infrastructure.in.web.mapper.TaskWebMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final CreateTaskUseCase createTaskUseCase;
    private final GetTaskByIdUseCase getTaskByIdUseCase;
    private final TaskWebMapper mapper;

    public TaskController(CreateTaskUseCase createTaskUseCase,
                          GetTaskByIdUseCase getTaskByIdUseCase,
                          TaskWebMapper mapper) {
        this.createTaskUseCase = createTaskUseCase;
        this.getTaskByIdUseCase = getTaskByIdUseCase;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<TaskResponse> createTask(@Valid @RequestBody CreateTaskRequest request) {
        CreateTaskCommand command = mapper.toCommand(request);
        Task savedTask = createTaskUseCase.create(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(savedTask));
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<TaskResponse> getTaskById(@PathVariable String taskId) {
        Task task = getTaskByIdUseCase.getById(TaskId.from(taskId));
        return ResponseEntity.ok(mapper.toResponse(task));
    }
}
