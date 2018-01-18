package de.tschumacher.smartdate.domain;

import org.joda.time.DateTime;

public class SmartDate {
  private final DateTime from;
  private final DateTime to;

  public SmartDate(DateTime from, DateTime to) {
    super();
    this.from = from;
    this.to = to;
  }

  public DateTime getFrom() {
    return this.from;
  }

  public DateTime getTo() {
    return this.to;
  }


}
