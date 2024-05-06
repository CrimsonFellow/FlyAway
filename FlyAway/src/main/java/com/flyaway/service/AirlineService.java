package com.flyaway.service;

import com.flyaway.model.Airline;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class AirlineService {
    private final SessionFactory sessionFactory;

    public AirlineService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Airline> getAllAirlines() {
        try (Session session = sessionFactory.openSession()) {
            Query<Airline> query = session.createQuery("FROM Airline", Airline.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Airline getAirlineById(int airlineId) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Airline.class, airlineId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public String getAirlineName(int airlineId) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Airline airline = session.get(Airline.class, airlineId);
            session.getTransaction().commit();
            return (airline != null) ? airline.getName() : null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void addAirline(Airline airline) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(airline);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateAirline(Airline airline) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(airline);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteAirline(int airlineId) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            // Retrieve the Airline entity by its ID
            Airline airline = session.get(Airline.class, airlineId);
            // Check if the Airline exists before attempting to delete
            if (airline != null) {
                session.delete(airline);
                transaction.commit();
            } else {
                // Handle case where airline with provided ID doesn't exist
                System.out.println("Airline with ID " + airlineId + " does not exist.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
