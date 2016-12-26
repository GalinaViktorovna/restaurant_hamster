package com.goit.restaurant.hamster.dao.hibernate;

import com.goit.restaurant.hamster.dao.DAO;
import com.goit.restaurant.hamster.model.KitchenJournal;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class HKitchenJournalDao implements DAO<KitchenJournal> {

    private SessionFactory sessionFactory;

    @Override
    public void save(KitchenJournal value) {
        sessionFactory.getCurrentSession().save(value);
    }

    @Override
    public void delete(Long id) {
        Session session = sessionFactory.getCurrentSession();
        KitchenJournal kitchenJournal = session.load(KitchenJournal.class, id);
        session.delete(kitchenJournal);
    }

    @Override
    public KitchenJournal getById(Long id) {
        return sessionFactory.getCurrentSession().get(KitchenJournal.class, id);

    }

    @Override
    public List<KitchenJournal> getByName(String name) {
        return null;
    }

    @Override
    public List<KitchenJournal> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("Select journal from KitchenJournal journal").list();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


}
