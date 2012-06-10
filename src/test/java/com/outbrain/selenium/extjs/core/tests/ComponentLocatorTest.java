package com.outbrain.selenium.extjs.core.tests;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import com.outbrain.selenium.extjs.core.locators.ComponentIdLocator;
import com.outbrain.selenium.extjs.core.locators.TextOrLableInComponentLocator;
import com.outbrain.selenium.extjs.core.locators.TextOrLableLocator;
import com.outbrain.selenium.extjs.core.locators.TypeLocator;
import com.outbrain.selenium.util.ExtjsUtils.Xtype;
import com.thoughtworks.selenium.Selenium;

/**
 * @author Asaf Levy
 * @version $Revision: 1.0
 */
public class ComponentLocatorTest {

  /**
   * Method testTextOrLableLocator.
   */
  @Test
  public void testTextOrLableLocator() {
    final Selenium sel = Mockito.mock(Selenium.class);
    final TextOrLableLocator locator = new TextOrLableLocator(sel, "cpc", Xtype.LABEL);
    Mockito.when(sel.getEval("window.findComponentByText('cpc','label')")).thenReturn("cpcId");

    Assert.assertEquals("component id is wrong", "cpcId", locator.getComponentId());

    final TextOrLableLocator treeLocator = new TextOrLableLocator(sel, "null", Xtype.TREEGRID);
    Mockito.when(sel.getEval("window.findComponentByText('null','treegrid')")).thenReturn("treeGrid_id");
    Assert.assertEquals("tree grid component id is wrong", "treeGrid_id", treeLocator.getComponentId());
  }

  /**
   * Method testTypeLocator.
   */
  @Test
  public void testTypeLocator() {
    final Selenium sel = Mockito.mock(Selenium.class);
    final TypeLocator locator = new TypeLocator(sel, Xtype.GRID, 0);
    Mockito.when(sel.getEval("window.findComponentByText(null,'grid')")).thenReturn("cpcId");

    Assert.assertEquals("component id is wrong", "cpcId", locator.getComponentId());

    final TextOrLableLocator treeLocator = new TextOrLableLocator(sel, "null", Xtype.TREEGRID);
    Mockito.when(sel.getEval("window.findComponentByText('null','treegrid')")).thenReturn("Grid_id");
    Assert.assertEquals("grid component id is wrong", "Grid_id", treeLocator.getComponentId());
  }

  /**
   * Method testIdLocator.
   */
  @Test
  public void testIdLocator() {
    final Selenium sel = Mockito.mock(Selenium.class);
    final ComponentIdLocator idLocator = new ComponentIdLocator(sel, "test_id");
    Assert.assertEquals("wrong id", "test_id", idLocator.getComponentId());
  }

  /**
   * Method testTextOrlableInComponent.
   */
  @Test
  public void testTextOrlableInComponent() {
    final Selenium sel = Mockito.mock(Selenium.class);
    final TextOrLableInComponentLocator locator = new TextOrLableInComponentLocator(sel, "win_id", "test_lable", Xtype.BUTTON);

    Mockito.when(sel.getEval("window.findInComponentByText('win_id','test_lable','button')")).thenReturn("button_id");

    Assert.assertEquals("wrong id", "button_id", locator.getComponentId());
  }

}
