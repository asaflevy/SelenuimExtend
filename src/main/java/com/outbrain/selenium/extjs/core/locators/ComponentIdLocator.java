package com.outbrain.selenium.extjs.core.locators;

import com.thoughtworks.selenium.Selenium;

/**
 * @author Asaf Levy
 * @version $Revision: 1.0
 */
public class ComponentIdLocator extends ComponentLocator {

  /**
   * Field componentId.
   */
  private String componentId = null;

  /**
   * Constructor for ComponentIdLocator.
   * @param selenium Selenium
   * @param componentId String
   */
  public ComponentIdLocator(final Selenium selenium, final String componentId) {
    super(selenium);
    this.componentId = componentId;
  }

  /**
   * Method getComponentId.  
   * @return String */
  @Override
  public final String getComponentId() {

    return componentId;
  }
}
