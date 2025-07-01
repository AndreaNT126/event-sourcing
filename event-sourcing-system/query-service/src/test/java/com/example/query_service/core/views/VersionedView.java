package com.example.query_service.core.views;


import com.example.common.serialization.EventMetadata;

public interface VersionedView {
  long getLastProcessedPosition();

  void setMetadata(EventMetadata eventMetadata);
}
