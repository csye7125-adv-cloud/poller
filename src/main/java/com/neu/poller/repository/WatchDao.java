package com.neu.poller.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.neu.poller.model.Watch;

@Repository
public interface WatchDao extends CrudRepository<Watch, String>{

}
