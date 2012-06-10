package com.outbrain.selenium.extjs.core.locators;

import com.outbrain.selenium.util.ExtjsUtils.Xtype;
import com.thoughtworks.selenium.Selenium;

/**
 * Base Class for locating an extjs component
/**
 * @author Asaf Levy
 * @version $Revision: 1.0
 */
public abstract class ComponentLocator {

  /**
   * Field sel.
   */
  private final Selenium selenium;
  /**
   * Field xtype.
   */
  private Xtype xtype;
  /**
   * Field textOrLable.
   */
  private String textOrLable;

  /**
   * Method getComponentId.
  
   * @return String */
  public abstract String getComponentId();

  /**
   * Constructor for ComponentLocator.
   * @param sel Selenium
   * @param textOrLable String
   * @param xtype Xtype
   */
  public ComponentLocator(final Selenium sel, final String textOrLable, final Xtype xtype) {
    selenium = sel;
    this.xtype = xtype;
    setTextOrLable(textOrLable);
  }

  /**
   * Constructor for ComponentLocator.
   * @param sel Selenium
   * @param xtype Xtype
   */
  public ComponentLocator(final Selenium sel, final Xtype xtype) {
    selenium = sel;
    this.xtype = xtype;
  }

  /**
   * Constructor for ComponentLocator.
   * @param sel Selenium
   */
  public ComponentLocator(final Selenium sel) {
    selenium = sel;

  }

  /**
   * Method getSelenium.
  
   * @return Selenium */
  public Selenium getSelenium() {
    return selenium;
  }

  /**
   * Method getXtype.  
   * @return Xtype */
  protected Xtype getXtype() {
    return xtype;
  }

  /**
   * Method getTextOrLable.  
   * @return String */
  public String getTextOrLable() {
    return textOrLable;
  }

  /**
   * Method setTextOrLable.
   * @param textOrLable String
   */
  public void setTextOrLable(final String textOrLable) {
    this.textOrLable = textOrLable;
  }

  /**
   * Method waitCmpNotNull.
   * @param fullExpr String
   * @return String */
  protected String waitCmpNotNull(final String fullExpr) {
    for (int second = 0;; second++) {
      if (second >= 5) {
        throw new RuntimeException("Timeout");
      }

      try {
        String componentId = null;
        componentId = getSelenium().getEval(fullExpr);
        if (!"null".equals(componentId)) {
          return componentId;
        }
      } catch (final Exception e) {
        // ignore
      }

      try {
        Thread.sleep(1000);
      } catch (final InterruptedException e) {
        // ignore
      }
    }
  }
}
