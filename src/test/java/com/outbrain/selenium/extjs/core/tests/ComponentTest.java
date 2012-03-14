package com.outbrain.selenium.extjs.core.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.outbrain.selenium.extjs.components.Component;
import com.outbrain.selenium.extjs.core.locators.ComponentLocator;
import com.outbrain.selenium.extjs.core.locators.ComponentLocatorFactory;
import com.outbrain.selenium.util.ExtjsUtils.Xtype;
import com.thoughtworks.selenium.Selenium;

/**
 * The class <code>ComponentTest</code> contains tests for the class <code>{@link Component}</code>.
 * @author Asaf Levy
 * @version $Revision: 1.0
 */

public class ComponentTest {

  ComponentLocatorFactory loc;

  Selenium sel;

  @Before
  public void setUp() throws Exception {
    sel = Mockito.mock(Selenium.class);
    loc = new ComponentLocatorFactory(sel);

  }

  /**
   * Run the Component(Selenium,ComponentLocator) constructor test.
   *
   * @throws Exception
   *
   */
  @Test
  public void testComponent_id() throws Exception {
    final ComponentLocator locator = loc.createLocator("cmp_id");
    final Component result = new Component(locator);

    // add additional test code here
    assertNotNull(result);
    assertEquals("cmp_id", result.getComponentId());
    assertEquals(true, result.visible());
    assertEquals(false, result.disabled());
    assertEquals(false, result.isDisabled());
    assertEquals("window.Ext.getCmp('cmp_id')", result.getExpression());
    assertEquals(false, result.hidden());
    assertEquals("cmp_id", result.getComponentId());
    assertEquals("//*[@id='cmp_id']", result.getXPath());
    assertEquals(true, result.waitForVisible());
  }

  /**
   * Run the void blur() method test.
   *
   * @throws Exception
   */
  @Test
  public void testBlur() throws Exception {
    final ComponentLocator locator = loc.createLocator("cmp_id");
    final Component result = new Component(locator);
    result.blur();
    Mockito.verify(sel).fireEvent("cmp_id", "blur");
  }

  /**
   * Run the boolean cleanEvalTrue(String) method test.
   *
   * @throws Exception
   */
  @Test
  public void testCleanEvalTrue() throws Exception {
    final ComponentLocator locator = loc.createLocator("cmp_id");
    final Component cmp_result = new Component(locator);

    Mockito.when(sel.getEval("window.Ext.getCmp('cmp_id')")).thenReturn("true");
    final boolean result = cmp_result.cleanEvalTrue(cmp_result.getExpression());

    assertTrue(result);
  }

  /**
   * Run the boolean cleanEvalTrue(String) method test.
   *
   * @throws Exception
   */
  @Test
  public void testCleanEvalTrue_1() throws Exception {
    final ComponentLocator locator = loc.createLocator("null");
    final Component cmp_result = new Component(locator);
    final boolean result = cmp_result.cleanEvalTrue(cmp_result.getExpression());
    assertEquals(false, result);
  }

  /**
   * Run the boolean disabled() method test.
   *
   * @throws Exception
   */
  @Test
  public void testDisabled() throws Exception {
    final ComponentLocator locator = loc.createLocator("cmp_id");
    final Component cmp_result = new Component(locator);
    Mockito.when(sel.getEval("window.Ext.getCmp('cmp_id').disabled")).thenReturn("true");
    assertTrue(cmp_result.disabled());
  }

  /**
   * Run the boolean disabled() method test.
   *
   * @throws Exception
   */
  @Test
  public void testDisabled_1() throws Exception {
    final ComponentLocator locator = loc.createLocator("cmp_id");
    final Component cmp_result = new Component(locator);
    assertEquals(false, cmp_result.disabled());
  }

  /**
   * Run the boolean evalNullComponent(String) method test.
   * @throws Exception
   */
  @Test
  public void testEvalNullComponent() throws Exception {
    final ComponentLocator locator = loc.createLocator("cmp_id");
    final Component cmp_result = new Component(locator);
    Mockito.when(sel.getEval("window.Ext.getCmp('cmp_id')")).thenReturn("null");
    final boolean result = cmp_result.evalNullComponent(cmp_result.getExpression());

    assertTrue(result);
  }

  /**
   * Run the boolean evalNullComponent(String) method test. return false
   * @throws Exception
   */
  @Test
  public void testEvalNullComponent_false() throws Exception {
    final ComponentLocator locator = loc.createLocator("cmp_id");
    final Component cmp_result = new Component(locator);
    Mockito.when(sel.getEval("window.Ext.getCmp('cmp_id')")).thenReturn("cmpid");
    final boolean result = cmp_result.evalNullComponent(cmp_result.getExpression());

    assertEquals(false, result);
  }

  /**
   * Run the String getComponentId() method test.
   *
   * @throws Exception
   */
  @Test
  public void testGetComponentId() throws Exception {
    final ComponentLocator locator = loc.createLocator("cmp_id");
    final Component cmp_result = new Component(locator);
    final String result = cmp_result.getComponentId();
    assertNotNull(result);
  }

  /**
   * Run the String getComponentId() .
   *  Use method type locator
   * @throws Exception
   */
  @Test
  public void testGetComponentId_1() throws Exception {
    final ComponentLocator locator = loc.createLocator("cmp_title", Xtype.BOX);
    Mockito.when(sel.getEval("window.findComponentByText('cmp_title','box')")).thenReturn("cmp_id");
    final Component cmp_result = new Component(locator);
    final String result = cmp_result.getComponentId();
    assertNotNull(result);
    assertEquals("cmp_id", cmp_result.getComponentId());
  }

  /**
   * Run the String getEl() method test.
   *
   * @throws Exception
   */
  @Test
  public void testGetEl() throws Exception {
    final ComponentLocator locator = loc.createLocator("cmp_id");
    final Component cmp_result = new Component(locator);
    assertNotNull(cmp_result);
    Mockito.when(sel.getEval("window.Ext.getCmp('cmp_id').getEl()")).thenReturn("cmp_el");
    assertEquals("cmp_el", cmp_result.getEl());

  }

}