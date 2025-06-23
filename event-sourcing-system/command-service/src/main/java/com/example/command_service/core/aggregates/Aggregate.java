package com.example.command_service.core.aggregates;

public interface Aggregate<Id> {
  Id id();
  int version();

  Object[] dequeueUncommittedEvents();
}
