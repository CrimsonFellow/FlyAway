package com.flyaway.service;

import com.flyaway.model.Airport;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class AirportService {

    private final SessionFactory sessionFactory;

    public AirportService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Airport> getAllAirports() {
        try (Session session = sessionFactory.openSession()) {
            Query<Airport> query = session.createQuery("FROM Airport", Airport.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Airport getAirportById(int airportId) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Airport.class, airportId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void addAirport(Airport airport) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(airport);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateAirport(Airport airport) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(airport);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteAirport(int airportId) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(airportId);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

