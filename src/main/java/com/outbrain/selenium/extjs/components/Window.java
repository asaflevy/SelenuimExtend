package com.outbrain.selenium.extjs.components;

import com.outbrain.selenium.extjs.core.locators.ComponentLocator;
import com.thoughtworks.selenium.Selenium;

/**
 * @author Asaf Levy
 * @version $Revision: 1.0
 */
public class Window extends BasicForm {

  /**
   * Constructor for Window.
   * @param locator ComponentLocator
   */
  public Window(final ComponentLocator locator) {
    super(locator);
  }

  /**
   * Constructor for Window.
   * @param selenium Selenium
   * @param expression String
   */
  public Window(final Selenium selenium, final String expression) {
    super(selenium, expression);
  }

  /**
   * Method close.
   */
  public void close() {
    selenium.click(getXPath() + "//div[contains(@class, 'x-tool-close')]");
  }

}
