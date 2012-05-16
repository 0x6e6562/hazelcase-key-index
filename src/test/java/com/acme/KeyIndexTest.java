package com.acme;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.IMap;
import com.hazelcast.core.MapEntry;
import com.hazelcast.query.Predicate;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class KeyIndexTest {

  @Test
  public void testWithoutIndex() {

    runBenchmark(20);
    runBenchmark(200);
    runBenchmark(2000);
    runBenchmark(20000);
    runBenchmark(200000);
  }

  private void runBenchmark(Integer iterations) {
    IMap<Key, Long> map = Hazelcast.getMap("default");
    map.clear();

    final String prefix = RandomStringUtils.randomAlphabetic(10);
    map.put(new Key(prefix, -1), System.currentTimeMillis());

    long mapFillStart = System.currentTimeMillis();
    for (int i = 0; i < iterations ; i++) {
      String pre = RandomStringUtils.randomAlphabetic(10);
      map.put(new Key(pre, i), System.currentTimeMillis());
    }
    long mapFillFinish = System.currentTimeMillis();

    long mapFillTimeTaken = (mapFillFinish - mapFillStart);
    System.out.println(String.format("Time to put %s entries into map: %s", iterations, mapFillTimeTaken));
    System.out.println();
    System.out.println(map.getLocalMapStats());
    System.out.println();

    Predicate predicate = new KeyPredicate(prefix);

    long querystart = System.currentTimeMillis();
    Set<Key> keys = map.keySet(predicate);
    long queryFinish = System.currentTimeMillis();

    assertEquals(1, keys.size());
    assertTrue(keys.contains(new Key(prefix, -1)));

    long queryTimeTaken = (queryFinish - querystart);
    System.out.println(String.format("Time to query %s entries: %s", map.size(), queryTimeTaken));

  }
}
