package com.neu.poller.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neu.poller.model.Watch;
import com.neu.poller.repository.WatchDao;

@Service
public class WatchServiceImpl implements WatchService {

	@Autowired
	WatchDao watchdao;
	@Override
	public void AddWatch(Watch watch) {
		watchdao.save(watch);
		
		
	}

	@Override
	public Watch UpdateWatch(Watch watch) {
	return watchdao.save(watch);
		
	}

	@Override
	public void DeleteWatch(Watch watch) {
		watchdao.delete(watch);
		
	}
    
	@Override
	public List<Watch> getWatches() {
		return (List<Watch>) watchdao.findAll();
		
	}

}
