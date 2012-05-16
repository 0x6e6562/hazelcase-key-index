package com.acme;

import java.io.Serializable;

public class Key implements Serializable {
  private String prefix;
  private int number;

  public Key() {
  }

  public Key(String prefix, int number) {
    this.prefix = prefix;
    this.number = number;
  }

  public String getPrefix() {
    return prefix;
  }

  public void setPrefix(String prefix) {
    this.prefix = prefix;
  }

  public int getNumber() {
    return number;
  }

  public void setNumber(int number) {
    this.number = number;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Key key = (Key) o;

    if (number != key.number) return false;
    if (!prefix.equals(key.prefix)) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = prefix.hashCode();
    result = 31 * result + number;
    return result;
  }

  @Override
  public String toString() {
    return "Key{" +
        "prefix='" + prefix + '\'' +
        ", number=" + number +
        '}';
  }
}
