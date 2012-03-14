package com.outbrain.selenium.extjs.components;

import com.outbrain.selenium.extjs.core.locators.ComponentLocator;
import com.thoughtworks.selenium.Selenium;

/**
 * @author Asaf Levy
 * @version $Revision: 1.0
 */
public class Menu extends Component {

  /**
   * Constructor for Menu.
   * @param locator ComponentLocator
   */
  public Menu(final Selenium selenium, final ComponentLocator locator) {
    super(locator);
  }

  /**
   * Constructor for Menu.
   * @param selenium Selenium
   * @param exprsion String
   */
  public Menu(final Selenium selenium, final String exprsion) {
    super(selenium, exprsion);
  }

  /**
   * Method click.
   * @param itemKey String
   */
  public void click(final String itemKey) {
    final String id = getEval(".items.items[" + getExpression() + ".items.indexOfKey('" + itemKey + "')].el.dom.id");
    selenium.click("//*[@id='" + id + "']");
  }

  /**
   * Method click.
   * @param propName String
   * @param propValue String
   */
  public void click(final String propName, final String propValue) {
    final String id = getEval(".items.items[" + getExpression() + ".items.findIndex('" + propName + "', '" + propValue + "')].el.dom.id");
    selenium.click("//*[@id='" + id + "']");
  }
}