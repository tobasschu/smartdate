package de.tschumacher.smartdate.parser;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.joda.time.DateTime;

import de.tschumacher.smartdate.domain.SmartDate;

public class DailySmartDateParserItem extends CommonSmartDateParserItem implements
    SmartDateParserItem {


  private static Set<String> patterns = new HashSet<String>(Arrays.asList("dd.MM.YYYY",
      "MMM dd, YYYY", "MMMM dd, YYYY", "MM-dd-YYYY", "MMM dd YYYY", "MMMM dd YYYY", "dd. MMM YYYY",
      "dd. MMMM YYYY"));



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
    return dateTime.withTimeAtStartOfDay().plusDays(1).minusMillis(1);
  }

  @Override
  protected DateTime computeFrom(DateTime dateTime) {
    return dateTime.withTimeAtStartOfDay().withDayOfMonth(dateTime.dayOfMonth().get());
  }


}
