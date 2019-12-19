package com.autodoc.spring.controller.impl;

import com.autodoc.business.contract.TaskManager;
import com.autodoc.helper.LibraryHelper;
import com.autodoc.model.dtos.tasks.TaskDTO;
import com.autodoc.model.dtos.tasks.TaskForm;
import com.autodoc.model.models.tasks.Task;
import com.autodoc.spring.controller.contract.TaskController;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@ControllerAdvice
@RequestMapping("/tasks")
public class TaskControllerImpl extends GlobalController<TaskDTO, Task> implements TaskController {

    private static Logger LOGGER = Logger.getLogger(TaskControllerImpl.class);
    // @Inject
    TaskManager manager;

    public TaskControllerImpl(LibraryHelper helper, TaskManager manager) {
        super(helper);
        this.manager = manager;
    }


    @GetMapping("")
    public ModelAndView tasks() throws Exception {
        LOGGER.info("retrieving tasks");
        ModelAndView mv = checkAndAddConnectedDetails("tasks");
        List<Task> tasks;
        try {
            tasks = manager.getAll(helper.getConnectedToken());
            LOGGER.info("tasks found: " + tasks.size());
            if (tasks.isEmpty()) {
                return sendError(mv, "no task found");
            }
            mv.addObject("tasks", tasks);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }


        return mv;

    }


    @GetMapping(value = "/{id}")
    @ResponseBody
    public ModelAndView taskById(@PathVariable Integer id) throws Exception {
        LOGGER.info("trying to get member with id " + id);
        ModelAndView mv = checkAndAddConnectedDetails("tasks_details");
        System.out.println("task is null");
        Task task = (Task) manager.getById(helper.getConnectedToken(), id);
        LOGGER.info("phoneMumber: " + task.getName());
        LOGGER.info("task: " + task);
        mv.addObject("taskForm", task);
        mv.addObject("showForm", 1);
        mv.addObject("task", task);
        return mv;
    }


    @PostMapping(value = "/update/{id}")
    @ResponseBody
    public ModelAndView update(@Valid TaskForm taskForm, BindingResult bindingResult) throws Exception {
        LOGGER.info("trying to update member with id " + taskForm.getId());
        ModelAndView mv = checkAndAddConnectedDetails("tasks_details");
        mv.addObject("taskForm", new TaskForm());
        if (bindingResult.hasErrors()) {
            LOGGER.error("binding has errors");
            Task task = (Task) manager.getById(helper.getConnectedToken(), taskForm.getId());
            mv.addObject("task", task);
            mv.addObject("taskForm", taskForm);
            mv.addObject("showForm", 0);
            return mv;
        }
        LOGGER.info("carrying on");
        LOGGER.info("task retrieved: " + taskForm);
        manager.update(helper.getConnectedToken(), taskForm);
        return new ModelAndView("redirect:" + "/tasks/" + taskForm.getId());
    }

    @GetMapping(value = "/delete/{id}")
    @ResponseBody
    public ModelAndView delete(@PathVariable Integer id) throws Exception {
        LOGGER.info("trying to delete member with id " + id);
        manager.delete(helper.getConnectedToken(), id);
        return tasks();
    }

    @GetMapping(value = "/new")
    public ModelAndView getCreate() {
        LOGGER.info("getting create form");
        ModelAndView mv = checkAndAddConnectedDetails("tasks_new");
        mv.addObject("taskForm", new TaskForm());
        mv.addObject("showForm", 1);
        return mv;
    }


    @PostMapping(value = "/new")
    @ResponseBody
    public ModelAndView create(@Valid TaskForm taskForm, BindingResult bindingResult) throws Exception {
        LOGGER.info("trying to create member ");
        ModelAndView mv = checkAndAddConnectedDetails("tasks_new");
        LOGGER.info("empl: " + taskForm);
        mv.addObject("taskForm", new TaskForm());
        if (bindingResult.hasErrors()) {
            LOGGER.error("binding has errors");
            mv.addObject("taskForm", taskForm);
            mv.addObject("showForm", 1);
            return mv;
        }
        LOGGER.info("task retrieved: " + taskForm);
        manager.add(helper.getConnectedToken(), taskForm);
        return tasks();
        //return new ModelAndView("redirect:" + "/tasks/" );
    }

    private TaskDTO convertFormIntoDto(TaskForm taskForm) {
        LOGGER.info("TODO");
        return null;
    }


}

