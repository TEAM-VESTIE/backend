package com.vestie.vestie.common;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Profile("test")
public class DataCleaner {

    private static final String REFERENTIAL_FORMAT = "SET FOREIGN_KEY_CHECKS %d";
    private static final String TRUNCATE_FORMAT = "TRUNCATE TABLE %s";

    private final List<String> tableNames = new ArrayList<>();

    @PersistenceContext
    private EntityManager entityManager;

    @PostConstruct
    @SuppressWarnings("unchecked")
    public void findDatabaseTableNames() {
        List<Object[]> tableInfos = entityManager.createNativeQuery("show tables").getResultList();
        for (Object[] tableInfo : tableInfos) {
            String tableName = (String) tableInfo[0];
            tableNames.add(tableName);
        }
    }

    @Transactional
    public void clear() {
        entityManager.clear();
        truncate();
    }

    private void truncate() {
        entityManager.createNativeQuery(String.format(REFERENTIAL_FORMAT, 0)).executeUpdate();
        for (String tableName : tableNames) {
            entityManager.createNativeQuery(String.format(TRUNCATE_FORMAT, tableName)).executeUpdate();
        }
        entityManager.createNativeQuery(String.format(REFERENTIAL_FORMAT, 1)).executeUpdate();
    }
}
