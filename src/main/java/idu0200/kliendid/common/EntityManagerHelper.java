package idu0200.kliendid.common;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerHelper {
    private static EntityManagerHelper instance;
    private EntityManagerFactory entityManagerFactory;


    private EntityManagerHelper() {
        entityManagerFactory = Persistence.createEntityManagerFactory("idu0200");
    }

    public static EntityManagerHelper getInstance() {
        if (instance == null) {
            instance = new EntityManagerHelper();
        }

        return instance;
    }


    public EntityManagerFactory getFactory() {
        return entityManagerFactory;
    }
}
