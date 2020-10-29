package com.neu.poller.repository;



import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.neu.poller.model.Alert;

public interface HealthDAO extends CrudRepository<Alert, String> {

    @Query(value = "select 1", nativeQuery = true)
    int findDbConnectivityStatus();
}
