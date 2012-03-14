package com.outbrain.selenium.extjs.core.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.mockito.Mockito;

import com.outbrain.selenium.extjs.components.Window;
import com.outbrain.selenium.extjs.core.locators.ComponentLocator;
import com.outbrain.selenium.extjs.core.locators.ComponentLocatorFactory;
import com.outbrain.selenium.extjs.core.locators.TextOrLableInComponentLocator;
import com.outbrain.selenium.extjs.core.locators.TextOrLableLocator;
import com.outbrain.selenium.util.ExtjsUtils.Xtype;
import com.thoughtworks.selenium.Selenium;

/**
 * @author Asaf Levy
 * @version $Revision: 1.0
 */
public class ButtonComponentTest {

  /**
   * Method testWindow.
   */
  @Test
  public void testWindow() {

    final Selenium sel = Mockito.mock(Selenium.class);

    final ComponentLocatorFactory loc = new ComponentLocatorFactory(sel);
    final ComponentLocator locator = loc.createLocator("test", Xtype.WINDOW);
    Mockito.when(sel.getEval("window.findComponentByText('test','window')")).thenReturn("koko");
    assertTrue("locator has the wrong type", locator instanceof TextOrLableLocator);
    locator.getComponentId();
    final Window window = new Window(locator);
    assertEquals("Window's id is incorrect", "koko", window.getComponentId());
    final ComponentLocator btnLocator = window.findComponentIn("OK", Xtype.BUTTON, loc);
    assertTrue("locator has the wrong type", btnLocator instanceof TextOrLableInComponentLocator);
  }

  /**
   * Method testWindowMethod.
   */
  @Test
  public void testWindowMethod() {

    final Selenium sel = Mockito.mock(Selenium.class);
    final ComponentLocatorFactory loc = new ComponentLocatorFactory(sel);
    Mockito.when(sel.getEval("window.findComponentByText('win_title','window')")).thenReturn("win_id");
    final ComponentLocator locator = loc.createLocator("win_title", Xtype.WINDOW);
    final Window result = new Window(locator);

    assertNotNull(result);

    assertEquals("win_id", result.getComponentId());
    assertEquals(true, result.visible());
    assertEquals(false, result.disabled());
    assertEquals(false, result.isDisabled());
    assertEquals("window.Ext.getCmp('win_id')", result.getExpression());
    assertEquals(false, result.hidden());
    assertEquals("//*[@id='win_id']", result.getXPath());
    assertEquals(true, result.waitForVisible());
  }

}
