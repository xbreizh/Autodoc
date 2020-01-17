package com.autodoc.model.dtos;

import com.autodoc.model.models.tasks.Task;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class TaskList {
    private List<Task> list = new ArrayList<>();

    public TaskList(List<Task> list) {
        this.list = list;
    }

    public TaskList() {
    }

    public void addTask(Task task) {
        list.add(task);
    }


    @Override
    public String toString() {
        return "TaskList{" +
                "list=" + list +
                '}';
    }
}