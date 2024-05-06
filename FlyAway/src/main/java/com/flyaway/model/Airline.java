package com.flyaway.model;

import javax.persistence.*;

@Entity
@Table(name = "airlines") // Specify the name of the table in the database
public class Airline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "airline_id")
    private int airlineId;

    @Column(name = "airline_name")
    private String name;

    // Constructors, getters, and setters

    public Airline() {
    }

    public Airline(String name) {
        this.name = name;
    }

    public int getAirlineId() {
        return airlineId;
    }

    public void setAirlineId(int airlineId) {
        this.airlineId = airlineId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


