package com.dev.wheeledrescue.entities;

import com.dev.wheeledrescue.entities.joins.Gender;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "employees")
public class Employee extends KeyEntity{
    private int number;
    private String firstName;
    private String lastName;
    private Date birthday;
    @Enumerated(EnumType.ORDINAL)
    private Gender gender;
    private Date hireDate;
    
    @OneToMany(mappedBy = "employee")
    private List<Employee> employeeTitles;
    
    public int getNumber() {
        return number;
    }
    
    public void setNumber(int number) {
        this.number = number;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public Date getBirthday() {
        return birthday;
    }
    
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    
    public Gender getGender() {
        return gender;
    }
    
    public void setGender(Gender gender) {
        this.gender = gender;
    }
    
    public Date getHireDate() {
        return hireDate;
    }
    
    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }
}
