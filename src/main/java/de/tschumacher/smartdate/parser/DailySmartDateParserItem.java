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
