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
package de.tschumacher.smartdate.data;

import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import de.tschumacher.smartdate.domain.SmartDate;

public class DataCreater {


  protected static PodamFactory factory = createFactory();

  private static PodamFactoryImpl createFactory() {
    final PodamFactoryImpl podamFactory = new PodamFactoryImpl();
    return podamFactory;
  }

  public static String createString() {
    return factory.manufacturePojo(String.class);
  }

  public static boolean createBoolean() {
    return factory.manufacturePojo(Boolean.class);
  }

  public static SmartDate createSmartDate() {
    return factory.manufacturePojo(SmartDate.class);
  }

}
