package com.outbrain.selenium.extjs.components;

import com.outbrain.selenium.extjs.core.locators.ComponentLocator;

/**
 * @author Asaf Levy
 * @version $Revision: 1.0
 */

public class Checkbox extends Component {

  /**
   * Constructor for Checkbox.
   * @param locator ComponentLocator
   */
  public Checkbox(final ComponentLocator locator) {
    super(locator);
  }

  /**
  
  
   * @return boolean */
  public boolean isChecked() {
    return evalTrue(".checked");
  }

  /**
   * Method check.
   * @param enable boolean
   */
  public void check(final boolean enable) {
    if (enable != evalTrue(".getValue()")) {
      click();
    }
    runScript(".setValue(" + enable + ")");
  }

  /**
   * Method click.
   */
  public void click() {
    waitForEvalTrue(".disabled == false");
    selenium.click(getXPath());
  }

}