package com.outbrain.selenium.extjs.core.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.mockito.Mockito;

import com.outbrain.selenium.extjs.components.Button;
import com.outbrain.selenium.extjs.core.locators.ComponentLocator;
import com.outbrain.selenium.extjs.core.locators.ComponentLocatorFactory;
import com.outbrain.selenium.extjs.core.locators.TextOrLableLocator;
import com.outbrain.selenium.util.ExtjsUtils.Xtype;
import com.thoughtworks.selenium.Selenium;

/**
 * @author Asaf Levy
 * @version $Revision: 1.0
 */
public class WindowComponentTest {

  /**
   * Method testButton.
   */
  @Test
  public void testButton() {

    final Selenium sel = Mockito.mock(Selenium.class);

    final ComponentLocatorFactory loc = new ComponentLocatorFactory(sel);
    final ComponentLocator locator = loc.createLocator("button_title", Xtype.BUTTON);
    Mockito.when(sel.getEval("window.findComponentByText('button_title','button')")).thenReturn("button_id");
    assertTrue("locator has the wrong type", locator instanceof TextOrLableLocator);
    final Button window = new Button(locator);
    assertEquals("Window's id is incorrect", "button_id", window.getComponentId());

  }

}
