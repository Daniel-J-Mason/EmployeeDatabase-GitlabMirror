package com.dev.wheeledrescue.entities;

import javax.persistence.*;

@MappedSuperclass
public class KeyEntity {
    
    @Id
    @Column(name = "PK", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pk;
    
    public Long getPk() {
        return pk;
    }
    
    public void setPk(Long pk) {
        this.pk = pk;
    }
}
