package de.tschumacher.smartdate.parser;

import de.tschumacher.smartdate.domain.SmartDate;

public interface SmartDateParserItem {

  SmartDate parse(String query);

  boolean isParsable(String query);

}
