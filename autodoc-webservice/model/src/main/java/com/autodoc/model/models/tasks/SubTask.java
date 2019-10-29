package com.autodoc.model.models.tasks;

import com.autodoc.model.models.person.employee.Employee;
import com.autodoc.model.models.pieces.Piece;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "subTask")
@Getter
@Setter
public class SubTask {




    public SubTask() {
    }

    public SubTask(@NotNull List<Piece> pieces, List<Employee> employees, @NotNull String name, @NotNull double estimatedTime) {
        this.pieces = pieces;
        this.employees = employees;
        this.name = name;
        this.estimatedTime = estimatedTime;
    }

/*    public SubTask(TemplateSubTask templateSubTask, List<Employee> employees) {
        this.templateSubTask = templateSubTask;
        this.pieces = templateSubTask.getPieces();
        this.estimatedTime = templateSubTask.getEstimatedTime();
        this.name = templateSubTask.getName();
        this.employees = employees;
    }*/




    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;



    @ManyToMany(cascade = CascadeType.REMOVE)
    private List<Task> tasks;

    @OneToMany(cascade = CascadeType.REMOVE)
    @NotNull
    private List<Piece> pieces;

    @ManyToMany(cascade = CascadeType.REMOVE)
   // @Max(2)
    @NotNull
    private List<Employee> employees;

    @ManyToOne
    private TemplateSubTask templateSubTask;

    @NotNull
    private String name;

    @NotNull
    private double estimatedTime;

    @Override
    public String toString() {
        int templateSubTaskId = 0;
        int pieceSize=0;
        int employeeSize=0;
        if(templateSubTask!=null)templateSubTaskId=templateSubTask.getId();
        if(employees!=null)employeeSize=employees.size();
        if(pieces!=null)pieceSize=pieces.size();
        return "SubTask{" +
                "id=" + id +
                ", pieces=" + pieceSize +
                ", employees=" + employeeSize +
                ", templateSubTask=" + templateSubTaskId+
                ", name='" + name + '\'' +
                ", estimatedTime=" + estimatedTime +
                '}';
    }
}
