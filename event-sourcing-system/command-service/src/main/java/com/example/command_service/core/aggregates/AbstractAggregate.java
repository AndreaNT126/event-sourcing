package com.example.command_service.core.aggregates;

import java.util.LinkedList;
import java.util.Queue;

public abstract class AbstractAggregate<Event, Id> implements Aggregate<Id> {
  protected Id id;
  protected int version;

  private final Queue uncommittedEvents = new LinkedList<>();

  public Id id() {
    return id;
  }

  public int version() {
    return version;
  }

  // lấy tất cả event phát sinh ra khoit queue để xử lý
  public Object[] dequeueUncommittedEvents() {
    var dequeuedEvents = uncommittedEvents.toArray();

    uncommittedEvents.clear();

    return dequeuedEvents;
  }

  public abstract void when(Event event);

  // thêm event vào queue , đồng thời tăng version
  protected void enqueue(Event event) {
    uncommittedEvents.add(event);
    when(event);
    version++;
  }
}
