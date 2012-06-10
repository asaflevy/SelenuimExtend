package com.outbrain.selenium.extjs.core.locators;

import com.outbrain.selenium.util.ExtjsUtils.Xtype;
import com.thoughtworks.selenium.Selenium;

/**
 * @author Asaf Levy
 * @version $Revision: 1.0
 */
public class TextOrLableLocator extends ComponentLocator {

  Integer index = null;

  /**
   * Field textOrLable.
   */
  String textOrLable;

  /**
   * Constructor for TextOrLableLocator.
   * @param sel Selenium
   * @param nameOrLable String
   * @param xtype Xtype
   */
  public TextOrLableLocator(final Selenium sel, final String nameOrLable, final Xtype xtype) {
    super(sel, xtype);
    textOrLable = nameOrLable;
  }

  /**
   * 
   * @param sel
   * @param nameOrLable
   * @param xtype
   * @param index
   */
  public TextOrLableLocator(final Selenium sel, final String nameOrLable, final Xtype xtype, final Integer index) {
    super(sel, xtype);
    textOrLable = nameOrLable;
    this.index = index;
  }

  /**
   * Method getComponentId.
  
   * @return String */
  @Override
  public String getComponentId() {
    String[] component;
    if (textOrLable != null) {
      component = waitCmpNotNull(String.format("window.findComponentByText('%s','%s')", textOrLable, getXtype().getName())).split(",");
    } else {
      component = waitCmpNotNull(String.format("window.findComponentByText(null,'%s')", getXtype().getName())).split(",");
    }

    if (component.length == 0) {
      return null;
    }
    if (index == null) {
      return component[0];
    }
    return component[index];

  }

}
