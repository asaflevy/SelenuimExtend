package com.outbrain.selenium.extjs.components;

import com.outbrain.selenium.extjs.core.locators.ComponentLocator;
import com.thoughtworks.selenium.Selenium;

/**
 * @author Asaf Levy
 * @version $Revision: 1.0
 */
public class Button extends Component {
  /**
   * Constructor for Button.
   * @param locator ComponentLocator
   */
  public Button(final ComponentLocator locator) {
    super(locator);
  }

  /**
   * Constructor for Button.
   * @param selenium Selenium
   * @param expression String
   */
  public Button(final Selenium selenium, final String expression) {
    super(selenium, expression);
  }

  /**
   * return true if the component is disabled
  
  
   * @return boolean */
  @Override
  public boolean disabled() {
    return evalTrue(".disabled");
  }

  /**
   * Method click.
   */
  public void click() {
    waitForEvalTrue(".disabled == false");
    selenium.click(getXPath());
  }

  /**
   * Method click and check if is no error after Ajax callback.
   * @throws InterruptedException 
   */
  public void clickAndWaitForAjaxValid() throws InterruptedException {
    waitForEvalTrue(".disabled == false");
    selenium.click(getXPath());
    wait(2);
    waitForFinshAjaxRequst();
    waitForDialogFailure();
  }

  /**
   * Method clickNoWait.
   */
  public void clickNoWait() {
    selenium.click(getXPath());
  }
}