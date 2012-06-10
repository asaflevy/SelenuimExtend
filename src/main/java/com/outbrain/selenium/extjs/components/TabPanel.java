package com.outbrain.selenium.extjs.components;

import com.outbrain.selenium.extjs.core.locators.ComponentLocator;

/***
 * TabPanel represent Ext TabPanel (The Master tab Panel) So if search for specific tab inside a tabPanel the function will return the master tab panel
/**
 * @author Asaf Levy
 * @version $Revision: 1.0
 */
public class TabPanel extends Component {

  /**
   * Field tabIndex.
   */
  private int tabIndex;

  /**
   * Constructor for TabPanel.
   * @param locator ComponentLocator
   * @param tabIndex Integer
   */
  public TabPanel(final ComponentLocator locator, final Integer tabIndex) {
    super(locator);
    this.tabIndex = tabIndex;

  }

  /**
   * try to collapse the selected tab
   */
  public void collapse() {
    evalTrue(".collapse()");
    waitForEvalTrue(".collapsed == true");
  }

  /**
   * try to expend the selected tab
   */
  public void expand() {
    evalTrue(".expand()");
    waitForEvalTrue(".collapsed == false");
  }

  /**
   * Set active by given index of wanted panel
   * @param indexPanel
   */
  public void setActiveTab(final int indexPanel) {
    runCleanScript(String.format("%s.setActiveTab(%d)", getExpression(), indexPanel));
  }

  /**
   * set Active tab after the search
   */
  public void setAsActiveTab() {
    setActiveTab(tabIndex);
  }

  /**
   * 
  
   * @return - (int) the current index of selected Tab */
  public Integer getCurentIndexTab() {
    final String activeTabId = getCleanEval(String.format("%s.getActiveTab().getActiveTab().id", getExpression()));
    return Integer.parseInt(getCleanEval(String.format("%s.items.indexOf('%s')", getExpression(), activeTabId)));
  }

  /**
   * return All id's of the current parent tabPanel
  
  
   * @return String[] */
  public String[] getKeys() {
    return getEval("data.keys").split(",");
  }

  /**
   * Method getTabIndex.
  
   * @return Integer */
  public Integer getTabIndex() {
    return tabIndex;
  }

  /**
   * Method setTabIndex.
   * @param tabIndex Integer
   */
  public void setTabIndex(final Integer tabIndex) {
    this.tabIndex = tabIndex;
  }

}
