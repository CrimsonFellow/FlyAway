package com.flyaway.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.flyaway.model.Flight;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class FlightService {
    private final SessionFactory sessionFactory;

    public FlightService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Flight> searchFlights(String dateOfTravel, int sourceId, int destinationId) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        try {
            // Convert dateOfTravel String to Date object
            Date travelDate = convertStringToDate(dateOfTravel);

            // Query flights based on date of travel, source, and destination IDs
            Query<Flight> query = session.createQuery(
                    "SELECT f FROM Flight f " +
                            "JOIN FETCH f.airline " +
                            "JOIN FETCH f.departureAirport " +
                            "JOIN FETCH f.destinationAirport " +
                            "WHERE f.departureAirport.airportId = :sourceId " +
                            "AND f.destinationAirport.airportId = :destinationId " +
                            "AND DATE(f.departureDatetime) = :travelDate", Flight.class);
            query.setParameter("sourceId", sourceId);
            query.setParameter("destinationId", destinationId);
            query.setParameter("travelDate", travelDate);

            List<Flight> flights = query.getResultList();

            session.getTransaction().commit();
            return flights;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    public Flight getFlightById(int flightId) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        try {
            Flight flight = session.get(Flight.class, flightId);
            session.getTransaction().commit();
            return flight;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    public double getTicketPrice(int flightId) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        try {
            Flight flight = session.get(Flight.class, flightId);
            double ticketPrice = flight.getTicketPrice().doubleValue();
            session.getTransaction().commit();
            return ticketPrice;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return 0.0;
        } finally {
            session.close();
        }
    }

    private Date convertStringToDate(String dateStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void addFlight(Flight flight) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(flight);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }


    public void updateFlight(Flight flight) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(flight);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

  
    public void deleteFlight(int flightId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Flight flight = session.get(Flight.class, flightId);
            if (flight != null) {
                session.delete(flight);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    
    public List<Flight> getAllFlights() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query<Flight> query = session.createQuery("FROM Flight", Flight.class);
            List<Flight> flights = query.getResultList();
            session.getTransaction().commit();
            return flights;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}




