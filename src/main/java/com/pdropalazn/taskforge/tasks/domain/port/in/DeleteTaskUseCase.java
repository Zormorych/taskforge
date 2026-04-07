package com.pdropalazn.taskforge.tasks.domain.port.in;

import com.pdropalazn.taskforge.tasks.domain.model.vo.TaskId;

public interface DeleteTaskUseCase {

    void delete(TaskId taskId);
}

