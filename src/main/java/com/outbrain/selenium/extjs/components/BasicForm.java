package com.outbrain.selenium.extjs.components;

import com.outbrain.selenium.extjs.core.locators.ComponentLocator;
import com.outbrain.selenium.extjs.core.locators.ComponentLocatorFactory;
import com.outbrain.selenium.util.ExtjsUtils.Xtype;
import com.thoughtworks.selenium.Selenium;

/**
 * @author Asaf Levy
 * @version $Revision: 1.0
 */
public class BasicForm extends Component {

  /**
   * Constructor for BasicForm.
   * @param locator ComponentLocator
   */
  protected BasicForm(final ComponentLocator locator) {
    super(locator);

  }

  /**
   * Constructor for BasicForm.
   * @param selenium Selenium
   * @param expression String
   */
  protected BasicForm(final Selenium selenium, final String expression) {
    super(selenium, expression);

  }

  /**
   * Method findComponentIn.
   * @param textOrLable String
   * @param type Xtype
   * @param locFactory ComponentLocatorFactory
  
   * @return ComponentLocator */
  public ComponentLocator findComponentIn(final String textOrLable, final Xtype type, final ComponentLocatorFactory locFactory) {
    return locFactory.createLocator(selenium, getComponentId(), textOrLable, type);

  }

}
