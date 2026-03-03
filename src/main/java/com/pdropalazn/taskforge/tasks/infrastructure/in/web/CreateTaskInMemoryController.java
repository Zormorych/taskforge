package com.pdropalazn.taskforge.tasks.infrastructure.in.web;

import com.pdropalazn.taskforge.identity.domain.model.vo.UserId;
import com.pdropalazn.taskforge.tasks.api.request.CreateTaskRequest;
import com.pdropalazn.taskforge.tasks.domain.model.Task;
import com.pdropalazn.taskforge.tasks.domain.model.vo.TaskDescription;
import com.pdropalazn.taskforge.tasks.domain.model.vo.TaskId;
import com.pdropalazn.taskforge.tasks.domain.model.vo.TaskTitle;
import com.pdropalazn.taskforge.tasks.domain.port.in.CreateTaskUseCase;
import com.pdropalazn.taskforge.tasks.domain.port.in.dto.CreateTaskCommand;
import com.pdropalazn.taskforge.tasks.infrastructure.out.persistence.inmemory.InMemoryTaskRepositoryAdapter;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks/in-memory")
public class CreateTaskInMemoryController {

    private final CreateTaskUseCase createTaskUseCase;
    private final InMemoryTaskRepositoryAdapter inMemoryTaskRepository;

    public CreateTaskInMemoryController(CreateTaskUseCase createTaskUseCase,
                                        InMemoryTaskRepositoryAdapter inMemoryTaskRepository) {
        this.createTaskUseCase = createTaskUseCase;
        this.inMemoryTaskRepository = inMemoryTaskRepository;
    }

    @PostMapping
    public ResponseEntity<TaskResponse> createTask(@Valid @RequestBody CreateTaskRequest request) {
        CreateTaskCommand command = new CreateTaskCommand(
                request.projectId(),
                new TaskTitle(request.title()),
                new TaskDescription(request.description()),
                request.priority(),
                request.dateTime(),
                new UserId(request.assigneeId())
        );

        Task savedTask = createTaskUseCase.create(command);
        return ResponseEntity.ok(toResponse(savedTask));
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<TaskResponse> getTaskById(@PathVariable String taskId) {
        return taskRepository.findById(TaskId.from(taskId))
                .map(task -> {
                    return ResponseEntity.ok(toResponse(task));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    private TaskResponse toResponse(Task task) {
        return new TaskResponse(
                task.getTaskId().value(),
                task.getProjectId(),
                task.getTitle().title(),
                task.getDescription() == null ? null : task.getDescription().description(),
                task.getStatus(),
                task.getPriority(),
                task.getDateTime(),
                task.getUserAssignedId().value()
        );
    }
}
