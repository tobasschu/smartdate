package de.tschumacher.smartdate.parser;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.joda.time.DateTime;

import de.tschumacher.smartdate.domain.SmartDate;

public class YearlySmartDateParserItem extends CommonSmartDateParserItem implements
    SmartDateParserItem {


  private static Set<String> patterns = new HashSet<String>(Arrays.asList("YYYY"));



  @Override
  public boolean isParsable(String query) {
    return super.isParsable(query, patterns);
  }

  @Override
  public SmartDate parse(String query) {
    return super.parse(query, patterns);
  }


  @Override
  protected DateTime computeEnd(DateTime dateTime) {
    return new DateTime().withTimeAtStartOfDay().withDayOfMonth(1).withMonthOfYear(1)
        .withYear(dateTime.year().get()).plusYears(1).minusMillis(1);
  }

  @Override
  protected DateTime computeFrom(DateTime dateTime) {
    return new DateTime().withTimeAtStartOfDay().withDayOfMonth(1).withMonthOfYear(1)
        .withYear(dateTime.year().get());
  }

}
