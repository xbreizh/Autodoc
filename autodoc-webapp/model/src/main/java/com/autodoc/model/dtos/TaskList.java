package com.autodoc.model.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class TaskList {
    private List<Integer> list = new ArrayList<>();

    public TaskList(List<Integer> list) {
        this.list = list;
    }

    public TaskList() {
    }

    public void addTask(Integer taskId) {
        list.add(taskId);
    }


    @Override
    public String toString() {
        return "TaskList{" +
                "list=" + list +
                '}';
    }
}