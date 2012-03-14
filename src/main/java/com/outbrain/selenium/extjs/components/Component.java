package com.outbrain.selenium.extjs.components;

import java.util.concurrent.TimeUnit;

import com.outbrain.selenium.extjs.core.locators.ComponentLocator;
import com.outbrain.selenium.util.ThreadUtils;
import com.thoughtworks.selenium.Selenium;

/**
 * @author Asaf Levy
 * @version $Revision: 1.0
 */
public class Component {

  /**
   * Field expression.
   */
  protected String expression;

  /**
   * Field selenium.
   */
  protected Selenium selenium;

  /**
   * Field locator.
   */
  protected ComponentLocator locator = null;

  /**
   * Field componentId.
   */
  private String componentId = null;

  /**
   * Field idFunction.
   */
  protected final static String idFunction = ".getId()";

  /**
   * Field isVisible.
   */
  protected final static String isVisible = ".isVisible()";

  /**
   * Field ownerCt.
   */
  private final static String ownerCt = ".ownerCt";

  /**
   * Constructor for Component.
   * @param expression String
   * @param selenium Selenium
   */
  public Component(final Selenium selenium, final String expression) {
    this.selenium = selenium;
    this.expression = expression;
  }

  /**
   * Makes a proxy for a root Ext component; selenium - Selenium proxy through which it can fire Selenese commands,
   * expression - JavaScript that evaluates to the Ext component. should be window.Ext.getCmp('<id>')
   * @param locator ComponentLocator
   */
  public Component(final ComponentLocator locator) {

    selenium = locator.getSelenium();
    this.locator = locator;
    componentId = locator.getComponentId();
  }

  /**
   * Makes a proxy for an Ext component that is contained within another; parent - proxy for the container Ext
   * component, expression - JavaScript expression that evaluates this proxy's component on that of the container.
   * @param parent Component
   * @param expression String
   */
  public Component(final Component parent, final String expression) {
    selenium = parent.selenium;
  }

  /**
   * Method getOwnerCt.
  
   * @return String */
  public String getOwnerCt() {
    return selenium.getEval(getExpression() + ownerCt);
  }

  /**
   * return true if the component is visible
  
   * @return - True/False */

  public Boolean isVisible() {
    return Boolean.parseBoolean(selenium.getEval(String.format("%s%s", getExpression(), isVisible)));
  }

  /**
   * Returns an XPath to the Ext component, which contains the ID provided by getId()
  
   * @return String */
  public String getXPath() {
    return "//*[@id='" + getComponentId() + "']";
  }

  /**
   * Returns the absolute expression that resolves this proxy's Ext component.
  
   * @return String */
  public String getExpression() {
    return String.format("window.Ext.getCmp('%s')", getComponentId());
  }

  /**
   * Method waitForEvalTrue.
   * @param expr String
   */
  protected void waitForEvalTrue(final String expr) {
    final String fullExpr = getExpression() + expr;

    waitEvalTrue(fullExpr);
  }

  /**
   * Method waitEvalTrue.
   * @param fullExpr String
   */
  protected void waitEvalTrue(final String fullExpr) {
    for (int second = 0;; second++) {
      if (second >= 15) {
        throw new RuntimeException("Timeout");
      }

      try {
        if ("true".equals(selenium.getEval(fullExpr))) {
          break;
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

  /**
   * Method waitEvalTrue.
  
   * @param time int
   */
  protected void wait(final int time) {
    for (int second = 0;; second++) {
      if (second >= time) {
        break;
      }

      try {
        Thread.sleep(500);
      } catch (final InterruptedException e) {
        // ignore
      }
    }
  }

  /**
   * Method getEval.
   * @param expr String
   * @return String */
  protected String getEval(final String expr) {
    waitForFinshAjaxRequst();
    final String fullExpr = getExpression() + expr;

    return selenium.getEval(fullExpr);
  }

  /**
   * Method getCleanEval.
   * @param expr String
   * @return String */
  protected String getCleanEval(final String expr) {
    waitForFinshAjaxRequst();
    return selenium.getEval(expr);
  }

  /**
   * Method runScript.
   * @param expr String
   */
  protected void runScript(final String expr) {
    waitForFinshAjaxRequst();
    final String fullExpr = getExpression() + expr;
    selenium.runScript(fullExpr);
  }

  /**
   * Method runCleanScript.
   * @param expr String
   */
  protected void runCleanScript(final String expr) {
    waitForFinshAjaxRequst();
    selenium.runScript(expr);
  }

  /**
   * Method evalNullComponent.
   * @param expr String
   * @return boolean */
  public boolean evalNullComponent(final String expr) {
    try {
      return "null".equals(getCleanEval(expr));
    } catch (final Exception e) {
      return false;
    }
  }

  /**
   * Method evalTrue.
   * @param expr String
   * @return boolean */
  protected boolean evalTrue(final String expr) {
    try {
      return "true".equals(getEval(expr));
    } catch (final Exception e) {
      return false;
    }
  }

  /**
   * return true if the the component is disabled  
   * @return boolean */
  public boolean disabled() {
    return evalTrue(".disabled");
  }

  /**
   * Method cleanEvalTrue.
   * @param expr String
   * @return boolean */
  public boolean cleanEvalTrue(final String expr) {
    try {
      if ("true".equals(getCleanEval(expr))) {
        return true;
      }
      if ("undefined".equals(getCleanEval(expr)) || "null".equals(getCleanEval(expr)) || "false".equals(getCleanEval(expr))) {
        return false;
      }
    } catch (final Exception e) {
      return false;
    }
    return false;
  }

  /**
   * Method hidden.
   * @return boolean */
  public boolean hidden() {
    return evalTrue(" == null") || evalTrue(".hidden");
  }

  /**
   * Method visible.
  
   * @return boolean */
  public boolean visible() {
    return !hidden();
  }

  /**
   * return true if the component is Disabled 
   * @return boolean */
  public boolean isDisabled() {
    return evalTrue(".disabled");
  }

  /**
   * Method waitForHidden.
   */
  public void waitForHidden() {
    final boolean success = ThreadUtils.waitFor(new ThreadUtils.WaitCondition() {
      @Override
      public boolean checkCondition(final long elapsedTimeInMs) {
        return hidden();
      }
    }, TimeUnit.SECONDS, 15);

    if (!success) {
      throw new RuntimeException("Timeout");
    }
  }

  /**
   * Wait until the component is viable
   * @return boolean */
  public boolean waitForVisible() {
    final boolean success = ThreadUtils.waitFor(new ThreadUtils.WaitCondition() {
      @Override
      public boolean checkCondition(final long elapsedTimeInMs) {
        return visible();
      }
    }, TimeUnit.SECONDS, 15);

    if (!success) {
      throw new RuntimeException("Timeout");
    }

    return success;
  }

  /**
   * Wait until the div is not viable any more
   * @param componentId String
   * @return boolean */

  protected boolean waitForGridLoadingMask(final String componentId) {
    final boolean success = ThreadUtils.waitFor(new ThreadUtils.WaitCondition() {
      @Override
      public boolean checkCondition(final long elapsedTimeInMs) {
        return !cleanEvalTrue(String.format("Ext.getCmp('%s').getEl().isMasked()", componentId));
      }
    }, TimeUnit.SECONDS, 15);

    if (!success) {
      throw new RuntimeException("Timeout");
    }

    return success;
  }

  /**
   * Method waitForTreeLoadingMask.
   * @param panelMaskId String
  
   * @return boolean */
  protected boolean waitForTreeLoadingMask(final String panelMaskId) {
    final boolean success = ThreadUtils.waitFor(new ThreadUtils.WaitCondition() {
      @Override
      public boolean checkCondition(final long elapsedTimeInMs) {
        return cleanEvalTrue(String.format("!window.Ext.fly('%s').isVisible()", panelMaskId));
      }
    }, TimeUnit.SECONDS, 15);

    if (!success) {
      throw new RuntimeException("Timeout");
    }

    return success;
  }

  /**
   * Wait for component. until the component is enable again.
   */
  public void waitToLoad() {
    waitForEvalTrue(".disabled != true");
  }

  /**
   * Returns this Component's xtype --hierarchy-- as a slash-delimited string
   *                                ------------
  
  
   * @return String */
  public String getXTypes() {
    return getEval(".getXTypes()");
  }

  /**
   * Gets the xtype for this component as registered with Ext.ComponentMgr
   *                                ------------
   
   * @return String */
  public String getXType() {
    return getEval(".getXType()");
  }

  /**
   * Returns the Ext.Element which encapsulates this Component.
  
  
   * @return String */
  public String getEl() {
    return getEval(".getEl()");
  }

  /**
   * Method blur.
   */
  public void blur() {
    selenium.fireEvent(getComponentId(), "blur");
  }

  /**
   * Method focus.
   */
  public void focus() {
    selenium.fireEvent(getComponentId(), "focus");
  }

  /**
   * return the componentId
  
   * @return String
   */
  public String getComponentId() {
    if (componentId == null) {
      componentId = selenium.getEval(getExpression() + idFunction);
    }
    return componentId;
  }

  /**
   * 
   * @param fullExpr
  
   * @return String
   */
  protected String waitForCmpId(final String fullExpr) {
    for (int second = 0;; second++) {
      if (second >= 5) {
        return null;
      }

      try {
        String componentId = null;
        componentId = selenium.getEval(fullExpr);
        if (!"null".equals(componentId) && !(componentId.isEmpty())) {
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

  /**
   * 
   * @param fullExpr

   * @return String
   */
  protected String waitForCmpValue(final String fullExpr) {
    for (int second = 0;; second++) {
      if (second >= 5) {
        return null;
      }

      try {
        String value = null;
        value = selenium.getEval(fullExpr);
        if (!"null".equals(value) && !(value.isEmpty())) {
          return value;
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

  /**
   * Method what until all ajax requests are finshed .
  * @return boolean return true if there is no ajax request  on AIRR*/
  protected boolean waitForFinshAjaxRequst() {
    for (int second = 0;; second++) {
      if (second >= 5) {
        return false;
      }

      try {

        if ("false".equals(selenium.getEval("window.Ext.Ajax.isLoading()"))) {
          if ("false".equals(selenium.getEval("window.wasAjaxFailure"))) {
            Thread.sleep(200);
            return true;
          }
        }
      } catch (final Exception e) {
        // ignore
      }

      try {
        Thread.sleep(300);
      } catch (final InterruptedException e) {
        // ignore
      }
    }
  }

  /**
   * Method waitFor msg dialog when has an error/failure .
   */
  public void waitForDialogFailure() {
    selenium.runScript("window.Ext.Msg.getDialog()");

    try {
      waitEvalTrue("window.Ext.Msg.isVisible() == false");
    } catch (final RuntimeException e) {
      throw e;
    }
  }

}
