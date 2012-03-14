package com.outbrain.selenium.util;

import com.thoughtworks.selenium.Selenium;

public class ExtjsUtils {

  public static enum Xtype {
    NUMBERFIELD,
    BOX,
    DATEFIELD,
    BUTTON,
    BUTTONGROUP,
    COLORPALETTE,
    COMPONENT,
    CONTAINER,
    CYCLE,
    DATAVIEW,
    DATEPICKER,
    EDITOR,
    EDITORGRID,
    FLASH,
    GRID,
    LISTVIEW,
    PANEL,
    WINDOW,
    SPLITBUTTON,
    TABPANEL,
    TREEPANEL,
    VIEWPORT,
    TREEGRID,
    TEXTFIELD,
    TEXTAREA,
    COMBO,
    CHECKBOX,
    TRIGGER,
    RADIO,
    LABEL,
    MENU,
    SUPERBOXSELECT;

    public String getName() {
      return toString().toLowerCase();
    }
  }

  public static String getComponentXpath(final Selenium selenium, final String element) {
    return selenium.getEval(String.format("window.createXPathFromElement(%s)", element));
  }

  public static String getComponentByTextOrLable(final Selenium selenium, final String textOrLable, final Xtype xtype) {
    String component = "";
    if (xtype != null) {
      if (textOrLable != null) {
        component = selenium.getEval(String.format("window.findComponentByText('%s','%s')", textOrLable, xtype.getName()));
      } else {
        component = selenium.getEval(String.format("window.findComponentByText(null,'%s')", xtype.getName()));
      }
    } else {

      component = selenium.getEval(String.format("window.findComponentByText('%s',null)", textOrLable));
    }

    return getComponentById(component);

  }

  /**
   * just return array of id's be aware - (After you got the result you need to run getComponentExpression with component id to get the reference to the component 
   * @param selenium
   * @param xtype
   * @return
   */

  public static String[] getComponentIdsByType(final Selenium selenium, final Xtype xtype) {
    String[] component = null;
    if (xtype != null) {
      component = selenium.getEval(String.format("window.findComponentByText(null,'%s')", xtype.getName())).split(",");
    }
    return component;

  }

  /**
   * return  reference to extjs component
   * @param componentId
   * @return
   */
  public static String getComponentById(final String componentId) {
    return "window.Ext.getCmp('" + componentId + "')";
  }

  public static String getComponentType(final Selenium selenium, final String textOrLable) {
    return selenium.getEval(String.format("%s.getXType()", getComponentByTextOrLable(selenium, textOrLable, null)));
  }

  public static void injectSupportingJavaScript(final Selenium selenium) {

    try {
      final String[] resources = new String[] { "seleniumFunctionUtil.js" };

      try {
        //JavascriptInjection.injectJavaScriptResourcesTogether( resources, new String[0], "ff-Extjs-javascript", selenium);
        JavascriptInjection.injectJavaScriptResource(resources[0], "ff-aut-javascript-" + 0, selenium);
      } catch (final Throwable e) {

        try {
          for (int i = 0; i < resources.length; i++) {
            JavascriptInjection.injectJavaScriptResource(resources[i], "ff-aut-javascript-" + i, selenium);
          }
        } catch (final Throwable e2) {
          final StringBuilder emsg = new StringBuilder();
          emsg.append("Unable to inject required Java Script as individual streams. ");

        }
      }

    } catch (final Throwable e) {
      if (RuntimeException.class.isInstance(e)) {
        throw RuntimeException.class.cast(e);
      } else {
        throw new RuntimeException(e);
      }
    }
  }

  public static boolean waitForAjaxRequests(final Selenium selenium) {
    for (int second = 0; second <= 20; second++) {

      try {
        final String result = selenium.getEval("window.verifyNoAjaxCalls()");
        if ("false".equals(result)) {
          Thread.sleep(1000);

        } else {
          return true;
        }

      } catch (final Exception e) {

      }
    }
    return false;
  }

}
