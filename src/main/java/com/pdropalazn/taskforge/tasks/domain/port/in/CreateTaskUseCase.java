package com.pdropalazn.taskforge.tasks.domain.port.in;


import com.pdropalazn.taskforge.tasks.domain.port.in.dto.CreateTaskCommand;
import com.pdropalazn.taskforge.tasks.domain.model.Task;

//interfaz que implementa al service de aplicacion para definir como tiene que ser
//el caso de uso
public interface CreateTaskUseCase {

Task create (CreateTaskCommand command);

}
