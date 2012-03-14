package com.outbrain.selenium.extjs.components;

import com.outbrain.selenium.extjs.core.locators.ComponentLocator;

/**
 * @author Asaf Levy
 * @version $Revision: 1.0
 */
public class Fieldset extends Component {

  /**
   * Field checkbox.
   */
  private Checkbox checkbox;

  /**
   * Constructor for Fieldset.

   * @param locator ComponentLocator
   */
  public Fieldset(final ComponentLocator locator) {
    super(locator);
    //@Todo -- need to fix it s 
    //checkbox = new Checkbox(selenium, +".checkbox");
    //checkbox.idFunction=".id";
  }

  /**
   * Method check.
   * @param enable boolean
  
   * @return Fieldset */
  public Fieldset check(final boolean enable) {
    checkbox.check(enable);

    return this;
  }

}
