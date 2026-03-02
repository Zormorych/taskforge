package com.pdropalazn.taskforge.tasks.domain.port.in.dto;


import com.pdropalazn.taskforge.identity.domain.model.vo.UserId;
import com.pdropalazn.taskforge.tasks.domain.model.TaskPriority;
import com.pdropalazn.taskforge.tasks.domain.model.TaskStatus;
import com.pdropalazn.taskforge.tasks.domain.model.vo.TaskDescription;
import com.pdropalazn.taskforge.tasks.domain.model.vo.TaskTitle;

import java.time.LocalDateTime;
import java.util.UUID;

//command/dto se encarga de gestionar la entrada del caso de uso
//aqui no viaja taskId porque eso lo gestiona el dominio
public record CreateTaskCommand(
        UUID projectId,
        TaskTitle title,
        TaskDescription description,
        TaskPriority priority,
        LocalDateTime dateTime,
        UserId assigneeId
) { }
