package com.outbrain.selenium.extjs.components;

import com.outbrain.selenium.extjs.core.locators.ComponentLocator;

/**
 * @author Asaf Levy
 * @version $Revision: 1.0
 */
public class Combobox extends Component {

  /**
   * Constructor for Combobox.

   * @param locator ComponentLocator
   */
  public Combobox(final ComponentLocator locator) {
    super(locator);
  }

  /**
   * Method click.
   */
  public void click() {
    selenium.click(getXPath());
  }

  /**
   * Method setValue.
   * @param value String
   * @return String
   */
  public String setValue(final String value) {

    focus();
    evalTrue(".setValue( '" + value + "' )");
    runScript(".fireEvent( 'select', " + getExpression() + ", " + getExpression() + ".store.getById('" + value + "'), " + getExpression()
        + ".store.indexOfId('" + value + "') )");
    blur();
    return value;
  }

  /**
   * 
   * @param value
   * @param fieldName
   */
  public void setValue(final String value, final String fieldName) {
    focus();
    final Integer index = findInStore(fieldName, value);
    runScript(".setValue(" + getExpression() + ".store.getAt(" + index + ").get(" + getExpression() + ".valueField) )");
    runScript(".fireEvent( 'select', " + getExpression() + ", " + getExpression() + ".store.getAt(" + index + "), " + index + " )");
    blur();
  }

  /**
   * Finds the index of the first matching Record in this store by a specific field value
   * @param fieldName
   * @param value
   * @return
   */
  public int findInStore(final String fieldName, final String value) {
    return Integer.parseInt(getEval(String.format(".store.find('%s','%s')", fieldName, value)));
  }

  /**
   * Method select.
   * @param i int
   */
  public void select(final int i) {

    focus();
    runScript(".setValue(" + getExpression() + ".store.getAt(" + i + ").get(" + getExpression() + ".valueField) )");
    runScript(".fireEvent( 'select', " + getExpression() + ", " + getExpression() + ".store.getAt(" + i + "), " + i + " )");
    blur();
  }

  /**
   * Method getCount.
   * @return Integer
   */
  public Integer getCount() {
    final String eval = getEval(".store.getCount()");
    if (eval == null || "null".equals(eval)) {
      return null;
    }

    return Integer.valueOf(eval);

  }

  /**
   * return the selected value inner component
  
   * @return String
   */
  public String getRawValue() {
    return getEval(String.format(".getRawValue()"));
  }

  /**
   * Returns the currently selected field value or empty string if no value is set.
   * @return String
   */
  public String getValue() {
    return waitForCmpValue(String.format("%s.getValue()", getExpression()));
  }

  /**
   * Resets the current field value to the originally-loaded value and clears any validation messages.
   * @return String
   */
  public String reset() {
    return getEval(String.format(".reset()"));
  }

}
