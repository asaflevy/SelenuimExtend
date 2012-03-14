package com.outbrain.selenium.extjs.components;

import com.outbrain.selenium.extjs.core.locators.ComponentLocator;

/**
 * @author Asaf Levy
 * @version $Revision: 1.0
 */
public class Radio extends Component {
  /**
   * Constructor for Radio.
   * @param locator ComponentLocator
   */
  public Radio(final ComponentLocator locator) {
    super(locator);
  }

  /**
  
  
   * @return boolean */
  public boolean isChecked() {
    return evalTrue(".checked");
  }

}