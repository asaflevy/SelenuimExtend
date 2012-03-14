package com.outbrain.selenium.extjs.components;

import com.outbrain.selenium.extjs.core.locators.ComponentLocator;

/**
 * @author Asaf Levy
 * @version $Revision: 1.0
 */
public class Field extends Component {

  /**
   * Constructor for Field.
   * @param locator ComponentLocator
   */
  public Field(final ComponentLocator locator) {
    super(locator);

  }

  /**
   * Method resetValue.
   */
  public void resetValue() {
    evalTrue(".setValue( '' )");
  }

  /**
   * Method getErrorText.
  
   * @return String */
  public String getErrorText() {
    return selenium.getText(getXPath() + "/../..//div[@class='x-form-invalid-msg']");
  }

  /**
   * Sets a data value into the field and validates it. To set the value directly without validation 
   * @param value
  
   * @return String */
  public String setValue(final String value) {
    return getEval(String.format(".setValue('%s')", value));
  }

  /**
   * Resets the current field value to the originally-loaded value and clears any validation messages
   */
  public void reset() {
    getEval(".reset()");
  }

  /**
   * Method type.
   * @param text String
   */
  public void type(final String text) {
    waitToLoad();

    focus();
    selenium.type(getComponentId(), text);
    blur();
  }

  /**
   * Method hasErrorText.
   * @param err String
  
   * @return boolean */
  public boolean hasErrorText(final String err) {
    final String text = getErrorText();

    return err.equals(text);
  }

  /**
   * return the value of component
  
   * @return String - theValue */
  public String getValue() {
    return getEval(".getValue()");
  }

  /**
   * Returns the raw data value which may or may not be a valid, defined value.Returns the normalized data for date field
  
   * @return String - theValue */
  public String getRawValue() {
    return getEval(".getRawValue()");
  }

  /**
   * return the value of component and wait 5 sec(max) unil the value is not null
  
   * @return String - theValue */
  public String getAndWaitForValue() {
    return waitForCmpValue(String.format("%s.getValue()", getExpression()));
  }

}
