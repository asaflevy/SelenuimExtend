package com.outbrain.selenium.extjs.components;

import com.outbrain.selenium.extjs.core.locators.ComponentLocator;
import com.thoughtworks.selenium.Selenium;

/**
 * @author Asaf Levy
 * @version $Revision: 1.0
 */
public class Panel extends Component {
  /**
   * Constructor for Panel.
   * @param locators ComponentLocator
   */
  public Panel(final Selenium selenium, final ComponentLocator locators) {
    super(locators);
  }

  /**
   * Method collapse.
   */
  public void collapse() {
    evalTrue(".collapse()");
    waitForEvalTrue(".collapsed == true");
  }

  /**
   * Method expand.
   */
  public void expand() {
    evalTrue(".expand()");
    waitForEvalTrue(".collapsed == false");
  }
}
