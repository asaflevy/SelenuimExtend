package com.outbrain.selenium.extjs.core.locators;

import com.outbrain.selenium.util.ExtjsUtils.Xtype;
import com.thoughtworks.selenium.Selenium;

/**
 * @author Asaf Levy
 * @version $Revision: 1.0
 */
public class ComponentLocatorFactory {

  private final Selenium selenium;

  /**
   * 
   * @param Selenium sel
   */
  public ComponentLocatorFactory(final Selenium sel) {
    selenium = sel;
  }

  /**
   * Method createLocator.
   * @param textOrLable String
   * @param xtype Xtype
   * @return ComponentLocator */
  public ComponentLocator createLocator(final String textOrLable, final Xtype xtype) {
    return new TextOrLableLocator(selenium, textOrLable, xtype);
  }

  /**
   * 
   * @param textOrLable
   * @param xtype
   * @return
   */
  public ComponentLocator createLocator(final String textOrLable, final Xtype xtype, final Integer index) {
    return new TextOrLableLocator(selenium, textOrLable, xtype, index);
  }

  /**
   * Method createLocator.
   * @param type Xtype
   * @param idx int  
   * @return ComponentLocator */
  public ComponentLocator createLocator(final Xtype type, final int idx) {
    return new TypeLocator(selenium, type, idx);
  }

  /**
   *selecting by default index component 0; 
   * @param type
   * @return
   */
  public ComponentLocator createLocator(final Xtype type) {
    return new TypeLocator(selenium, type);
  }

  /**
   * Method createLocator.
   * @param componentId String  
   * @return ComponentLocator */
  public ComponentLocator createLocator(final String componentId) {
    return new ComponentIdLocator(selenium, componentId);
  }

  /**
   * Method createLocator.
   * @param parentComponentId String
   * @param textOrLable String
   * @param xtype Xtype  
   * @return ComponentLocator */
  public ComponentLocator createLocator(final String parentComponentId, final String textOrLable, final Xtype xtype) {
    return new TextOrLableInComponentLocator(selenium, parentComponentId, textOrLable, xtype);
  }

  /**
   * Method createLocator.
   * @param selenium
   * @param parentComponentId
   * @param textOrLable
   * @param xtype 
   * @return
   */
  public ComponentLocator createLocator(final Selenium selenium, final String parentComponentId, final String textOrLable, final Xtype xtype) {
    return new TextOrLableInComponentLocator(selenium, parentComponentId, textOrLable, xtype);
  }

  public Selenium getSelenium() {
    return selenium;
  }

}
