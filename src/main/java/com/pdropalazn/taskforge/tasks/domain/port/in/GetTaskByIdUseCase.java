package com.pdropalazn.taskforge.tasks.domain.port.in;

import com.pdropalazn.taskforge.tasks.domain.model.Task;
import com.pdropalazn.taskforge.tasks.domain.model.vo.TaskId;

public interface GetTaskByIdUseCase {

    Task getById(TaskId taskId);
}
