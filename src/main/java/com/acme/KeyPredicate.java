package com.acme;

import com.hazelcast.core.MapEntry;
import com.hazelcast.query.Predicate;

import java.io.Serializable;

public class KeyPredicate implements Predicate<Key, Long>, Serializable {

  private String matchValue;

  public KeyPredicate() {
  }

  public KeyPredicate(String matchValue) {
    this.matchValue = matchValue;
  }

  public String getMatchValue() {
    return matchValue;
  }

  public void setMatchValue(String matchValue) {
    this.matchValue = matchValue;
  }

  @Override
  public boolean apply(MapEntry<Key, Long> mapEntry) {
    return mapEntry.getKey().getPrefix().equals(matchValue);
  }
}
