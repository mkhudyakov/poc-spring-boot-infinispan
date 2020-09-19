package com.poc.spring.infinispan.config;

import com.poc.spring.infinispan.data.entity.Employee;
import org.infinispan.notifications.Listener;
import org.infinispan.notifications.cachelistener.annotation.*;
import org.infinispan.notifications.cachelistener.event.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Listener
public class CacheListener {

    private static final Logger LOG = LoggerFactory.getLogger(SchedulerConfiguration.class);

    @CacheEntryCreated
    public void entryCreated(CacheEntryCreatedEvent<String, Employee> event) {
        this.printLog(String.format("Adding key %s to cache", event.getKey()), event);
    }

    @CacheEntryExpired
    public void entryExpired(CacheEntryExpiredEvent<String, Employee> event) {
        this.printLog(String.format("Expiring key %s from cache", event.getKey()), event);
    }

    @CacheEntryVisited
    public void entryVisited(CacheEntryVisitedEvent<String, Employee> event) {
        this.printLog(String.format("Key %s was visited", event.getKey()), event);
    }

    @CacheEntryActivated
    public void entryActivated(CacheEntryActivatedEvent<String, Employee> event) {
        this.printLog(String.format("Activating key %s on cache", event.getKey()), event);
    }

    @CacheEntryPassivated
    public void entryPassivated(CacheEntryPassivatedEvent<String, Employee> event) {
        this.printLog(String.format("Passivating key %s from cache", event.getKey()), event);
    }

    @CacheEntryLoaded
    public void entryLoaded(CacheEntryLoadedEvent<String, Employee> event) {
        this.printLog(String.format("Loading key %s to cache", event.getKey()), event);
    }

    @CacheEntriesEvicted
    public void entriesEvicted(CacheEntriesEvictedEvent<String, Employee> event) {
        StringBuilder builder = new StringBuilder();
        event.getEntries().forEach((key, value) -> builder.append(key).append(", "));
        LOG.info("Evicting following entries from cache: " + builder.toString());
    }

    private void printLog(String log, CacheEntryEvent event) {
        if (!event.isPre()) {
            LOG.info(log);
        }
    }
}