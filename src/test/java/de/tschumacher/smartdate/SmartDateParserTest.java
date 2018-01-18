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

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import de.tschumacher.smartdate.data.DataCreater;
import de.tschumacher.smartdate.domain.SmartDate;
import de.tschumacher.smartdate.parser.SmartDateParserItem;



public class SmartDateParserTest {

  private SmartDateParser smartDateParser;
  private SmartDateParserItem parserItem;
  private SmartDateParserItem parserItem2;

  @Before
  public void setUp() {

    this.parserItem = Mockito.mock(SmartDateParserItem.class);
    this.parserItem2 = Mockito.mock(SmartDateParserItem.class);
    this.smartDateParser = new SmartDateParser(Arrays.asList(this.parserItem, this.parserItem2));
  }

  @After
  public void shutDown() {
    Mockito.verifyNoMoreInteractions(this.parserItem);
    Mockito.verifyNoMoreInteractions(this.parserItem2);
  }

  @Test
  public void isParsableTest() {
    final String query = DataCreater.createString();
    final Boolean expected = true;

    Mockito.when(this.parserItem.isParsable(query)).thenReturn(expected);

    final boolean actual = this.smartDateParser.isParsable(query);

    assertEquals(expected, actual);

    Mockito.verify(this.parserItem).isParsable(query);
  }


  @Test
  public void isNotParsableTest() {
    final String query = DataCreater.createString();
    final Boolean expected = false;

    Mockito.when(this.parserItem.isParsable(query)).thenReturn(expected);
    Mockito.when(this.parserItem2.isParsable(query)).thenReturn(expected);

    final boolean actual = this.smartDateParser.isParsable(query);

    assertEquals(expected, actual);

    Mockito.verify(this.parserItem).isParsable(query);
    Mockito.verify(this.parserItem2).isParsable(query);
  }



  @Test
  public void isParsableMultiTest() {
    final String query = DataCreater.createString();
    final Boolean expected = true;

    Mockito.when(this.parserItem.isParsable(query)).thenReturn(false);
    Mockito.when(this.parserItem2.isParsable(query)).thenReturn(expected);

    final boolean actual = this.smartDateParser.isParsable(query);

    assertEquals(expected, actual);

    Mockito.verify(this.parserItem).isParsable(query);
    Mockito.verify(this.parserItem2).isParsable(query);
  }


  @Test
  public void parseTest() {
    final String query = DataCreater.createString();
    final SmartDate expected = DataCreater.createSmartDate();

    Mockito.when(this.parserItem.parse(query)).thenReturn(expected);
    final SmartDate actual = this.smartDateParser.parse(query);

    assertEquals(expected, actual);

    Mockito.verify(this.parserItem).parse(query);
  }


  @Test(expected = UnsupportedOperationException.class)
  public void parseNotPossibleTest() {
    final String query = DataCreater.createString();

    Mockito.when(this.parserItem.parse(query)).thenReturn(null);
    Mockito.when(this.parserItem2.parse(query)).thenReturn(null);

    try {
      this.smartDateParser.parse(query);
    } catch (final UnsupportedOperationException e) {
      throw e;
    } finally {
      Mockito.verify(this.parserItem).parse(query);
      Mockito.verify(this.parserItem2).parse(query);
    }
  }


  @Test
  public void parseMultiTest() {
    final String query = DataCreater.createString();
    final SmartDate expected = DataCreater.createSmartDate();

    Mockito.when(this.parserItem.parse(query)).thenReturn(null);
    Mockito.when(this.parserItem2.parse(query)).thenReturn(expected);

    final SmartDate actual = this.smartDateParser.parse(query);

    assertEquals(expected, actual);

    Mockito.verify(this.parserItem).parse(query);
    Mockito.verify(this.parserItem2).parse(query);
  }
}
