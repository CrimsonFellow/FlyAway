package com.flyaway.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "flights")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flight_id")
    private int flightId;

    @ManyToOne 
    @JoinColumn(name = "airline_id")
    private Airline airline;

    @ManyToOne 
    @JoinColumn(name = "departure_airport_id")
    private Airport departureAirport;

    @ManyToOne 
    @JoinColumn(name = "destination_airport_id")
    private Airport destinationAirport;

    @Column(name = "departure_datetime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date departureDatetime;

    @Column(name = "arrival_datetime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date arrivalDatetime;

    @Column(name = "ticket_price")
    private BigDecimal ticketPrice;

    // Constructors, getters, and setters

    public Flight() {
    }

    public Flight(Airline airline, Airport departureAirport, Airport destinationAirport, Date departureDatetime, Date arrivalDatetime, BigDecimal ticketPrice) {
        this.airline = airline;
        this.departureAirport = departureAirport;
        this.destinationAirport = destinationAirport;
        this.departureDatetime = departureDatetime;
        this.arrivalDatetime = arrivalDatetime;
        this.ticketPrice = ticketPrice;
    }

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }

    public Airport getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(Airport departureAirport) {
        this.departureAirport = departureAirport;
    }

    public Airport getDestinationAirport() {
        return destinationAirport;
    }

    public void setDestinationAirport(Airport destinationAirport) {
        this.destinationAirport = destinationAirport;
    }

    public Date getDepartureDatetime() {
        return departureDatetime;
    }

    public void setDepartureDatetime(Date departureDatetime) {
        this.departureDatetime = departureDatetime;
    }

    public Date getArrivalDatetime() {
        return arrivalDatetime;
    }

    public void setArrivalDatetime(Date arrivalDatetime) {
        this.arrivalDatetime = arrivalDatetime;
    }

    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(BigDecimal ticketPrice) {
        this.ticketPrice = ticketPrice;
    }
}



