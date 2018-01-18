/*
 * Copyright 2017 Tobias Schumacher
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import de.tschumacher.smartdate.domain.SmartDate;



public class MonthlySmartDateParserItemTest {

  private static String[] QUERIES = {"November 2013", "11 2013", "Nov 2013"};

  private MonthlySmartDateParserItem parser;

  @Before
  public void setUp() {
    this.parser = new MonthlySmartDateParserItem();
  }


  @Test
  public void isParsableTest() {

    for (int i = 0; i < QUERIES.length; i++) {
      final boolean parsable = this.parser.isParsable(QUERIES[i]);
      assertTrue(parsable);
    }

  }

  @Test
  public void parseTest() {

    for (int i = 0; i < QUERIES.length; i++) {
      final SmartDate smartDate = this.parser.parse(QUERIES[i]);
      assertEquals(new DateTime("2013-11-01T00:00:00.000"), smartDate.getFrom());
      assertEquals(new DateTime("2013-11-30T23:59:59.999"), smartDate.getTo());
    }

  }
}