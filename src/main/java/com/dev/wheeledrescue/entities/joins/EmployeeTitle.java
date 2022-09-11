package com.dev.wheeledrescue.entities.joins;

import com.dev.wheeledrescue.entities.Employee;
import com.dev.wheeledrescue.entities.KeyEntity;
import com.dev.wheeledrescue.entities.Title;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class EmployeeTitle extends KeyEntity {
    
    @ManyToOne
    @JoinColumn(name = "PK_employee")
    private Employee employee;
    
    @ManyToOne
    @JoinColumn(name = "PK_title")
    private Title title;
    
    public Employee getEmployee() {
        return employee;
    }
    
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    
    public Title getTitle() {
        return title;
    }
    
    public void setTitle(Title title) {
        this.title = title;
    }
}
