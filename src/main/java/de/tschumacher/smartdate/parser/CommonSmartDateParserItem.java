package de.tschumacher.smartdate.parser;

import java.util.Locale;
import java.util.Set;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import de.tschumacher.smartdate.domain.SmartDate;

public abstract class CommonSmartDateParserItem {
  private static final Locale ENGLISH = Locale.ENGLISH;


  protected boolean isParsable(String query, Set<String> patterns) {
    final DateTime dateTime = parseDateTime(query, patterns);
    return dateTime != null;
  }

  protected SmartDate parse(String query, Set<String> patterns) {
    final DateTime dateTime = parseDateTime(query, patterns);

    if (dateTime != null)
      return new SmartDate(computeFrom(dateTime), computeEnd(dateTime));

    return null;
  }


  private DateTime parseDateTime(String query, Set<String> patterns) {
    for (final String pattern : patterns) {
      final DateTime dateTime = parseWithPattern(query, pattern);
      if (dateTime != null)
        return dateTime;
    }

    return null;
  }

  private DateTime parseWithPattern(String query, String pattern) {
    try {
      return DateTime.parse(query, DateTimeFormat.forPattern(pattern).withLocale(ENGLISH));
    } catch (final Exception e) {
      return null;
    }
  }

  protected abstract DateTime computeEnd(DateTime dateTime);

  protected abstract DateTime computeFrom(DateTime dateTime);


}
