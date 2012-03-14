package com.outbrain.selenium.extjs.components;

import com.outbrain.selenium.extjs.core.locators.ComponentLocator;
import com.thoughtworks.selenium.Selenium;

/**
 * @author Asaf Levy
 * @version $Revision: 1.0
 */
public class TriggerField extends Component {

  /**
   * Field trigger.
   */
  private Button trigger;

  /**
   * Constructor for TriggerField.
   * @param locator ComponentLocator
   */
  public TriggerField(final ComponentLocator locator) {
    super(locator);
  }

  /**
   * Constructor for TriggerField.
   * @param selenium Selenium
   * @param expression String
   */
  public TriggerField(final Selenium selenium, final String expression) {
    super(selenium, expression);

    trigger = new Button(selenium, expression + ".trigger");
    //trigger.idFunction = ".id";
  }

  /**
   * Method clickTrigger.
  
   * @return TriggerField */
  public TriggerField clickTrigger() {
    selenium.click(trigger.getComponentId());
    selenium.click(trigger.getXPath());

    return this;
  }

}
