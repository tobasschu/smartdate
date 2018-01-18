package de.tschumacher.smartdate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.tschumacher.smartdate.domain.SmartDate;
import de.tschumacher.smartdate.parser.DailySmartDateParserItem;
import de.tschumacher.smartdate.parser.MonthlySimpleSmartDateParserItem;
import de.tschumacher.smartdate.parser.MonthlySmartDateParserItem;
import de.tschumacher.smartdate.parser.SmartDateParserItem;
import de.tschumacher.smartdate.parser.YearlySmartDateParserItem;

public class SmartDateParser {
  private static final List<Class<? extends SmartDateParserItem>> defaultParserItems =
      new ArrayList<Class<? extends SmartDateParserItem>>(Arrays.asList(
          DailySmartDateParserItem.class, MonthlySmartDateParserItem.class,
          MonthlySimpleSmartDateParserItem.class, YearlySmartDateParserItem.class));

  private final List<SmartDateParserItem> parserItems;


  public SmartDateParser(List<SmartDateParserItem> parserItems) {
    super();
    this.parserItems = parserItems;
  }

  public SmartDateParser() throws InstantiationException, IllegalAccessException {
    super();
    this.parserItems = new ArrayList<SmartDateParserItem>();
    for (final Class<? extends SmartDateParserItem> itemsClass : defaultParserItems) {
      this.parserItems.add(itemsClass.newInstance());
    }
  }


  public boolean isParsable(String query) {
    for (final SmartDateParserItem parserItem : this.parserItems) {
      final boolean isParsable = parserItem.isParsable(query);
      if (isParsable == true)
        return true;
    }
    return false;
  }

  public SmartDate parse(String query) {
    for (final SmartDateParserItem parserItem : this.parserItems) {
      final SmartDate smartDate = parserItem.parse(query);
      if (smartDate != null)
        return smartDate;
    }
    throw new UnsupportedOperationException();
  }

}
