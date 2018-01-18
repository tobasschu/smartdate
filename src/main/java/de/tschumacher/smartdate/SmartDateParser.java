/*
 * Copyright 2018 Tobias Schumacher
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
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

  public SmartDateParser() {
    super();
    this.parserItems = new ArrayList<SmartDateParserItem>();
    addDefaultParser();
  }

  private void addDefaultParser() {
    for (final Class<? extends SmartDateParserItem> itemsClass : defaultParserItems) {
      this.parserItems.add(createInstance(itemsClass));
    }
  }

  private SmartDateParserItem createInstance(final Class<? extends SmartDateParserItem> itemsClass) {
    try {
      return itemsClass.newInstance();
    } catch (InstantiationException | IllegalAccessException e) {
      throw new UnsupportedOperationException(e);
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
