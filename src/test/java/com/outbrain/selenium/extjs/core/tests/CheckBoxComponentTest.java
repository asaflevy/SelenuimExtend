package com.outbrain.selenium.extjs.core.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.outbrain.selenium.extjs.components.Checkbox;
import com.outbrain.selenium.extjs.core.locators.ComponentLocator;
import com.outbrain.selenium.extjs.core.locators.ComponentLocatorFactory;
import com.outbrain.selenium.extjs.core.locators.TextOrLableLocator;
import com.outbrain.selenium.util.ExtjsUtils.Xtype;
import com.thoughtworks.selenium.Selenium;

/**
 * @author Asaf Levy
 * @version $Revision: 1.0
 */
public class CheckBoxComponentTest {

  /**
   * Field loc.
   */
  ComponentLocatorFactory loc;
  /**
   * Field sel.
   */
  Selenium sel;

  /**
   * Method setUp.
   * @throws Exception
   */
  @Before
  public void setUp() throws Exception {
    sel = Mockito.mock(Selenium.class);
    loc = new ComponentLocatorFactory(sel);

  }

  /**
   * testCheckBox Method
   */
  @Test
  public void testCheckBox() {

    final ComponentLocator locator = loc.createLocator("checkBox_title", Xtype.CHECKBOX);
    Mockito.when(sel.getEval("window.findComponentByText('checkBox_title','checkbox')")).thenReturn("checkBox_id");
    assertTrue("locator has the wrong type", locator instanceof TextOrLableLocator);

    final Checkbox chk = new Checkbox(locator);
    assertEquals("checkbox id is incorrect", "checkBox_id", chk.getComponentId());

  }

}
