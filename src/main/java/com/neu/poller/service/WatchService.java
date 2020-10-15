package com.neu.poller.service;

import java.util.List;

import com.neu.poller.model.Watch;

public interface WatchService {
	void AddWatch(Watch watch);
	Watch UpdateWatch(Watch watch);
	void DeleteWatch(Watch watch);
	List<Watch> getWatches();

}
