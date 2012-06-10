package com.outbrain.selenium.extjs.core.locators;

import com.outbrain.selenium.util.ExtjsUtils.Xtype;
import com.thoughtworks.selenium.Selenium;

/**
 * @author Asaf Levy
 * @version $Revision: 1.0
 */
public class TypeLocator extends ComponentLocator {

  /**
   * Field index.
   */
  Integer index = null;

  /**
   * Constructor for TypeLocator.
   * @param selenium Selenium
   * @param type Xtype
   * @param idx int
   */
  public TypeLocator(final Selenium selenium, final Xtype type, final int idx) {
    super(selenium, type);
    index = idx;
  }

  /**
   *  
   * @param selenium
   * @param type
   */
  public TypeLocator(final Selenium selenium, final Xtype type) {
    super(selenium, type);
  }

  /**
   * Method getComponentId.
  
   * @return String */
  @Override
  public String getComponentId() {

    if (getXtype() == null) {
      return null;
    }

    final String[] component = getSelenium().getEval(String.format("window.findComponentByText(null,'%s')", getXtype().getName())).split(",");

    return (component.length > 0 && component != null && component[getIndex()] != null) ? component[getIndex()] : null;
  }

  public int getIndex() {
    if (index == null) {
      return 0;
    }
    return index;
  }
}
