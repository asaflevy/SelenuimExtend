package com.outbrain.selenium.extjs.components;

import com.outbrain.selenium.extjs.core.locators.ComponentLocator;

/**
 * @author Asaf Levy
 * @version $Revision: 1.0
 */
public class Tree extends Component {

  /**
   * Constructor for Tree.
   * @param locator ComponentLocator
   */
  public Tree(final ComponentLocator locator) {
    super(locator);
  }

  /**
   * Method getRootNode.
   * @return TreeNode */
  public TreeNode getRootNode() {

    final TreeNode treeNode = new TreeNode(selenium, getExpression());
    return treeNode.getRootNode();

  }

  /**
   * Method select By Id.
   * @param id String
   * @return TreeNode */
  public TreeNode select(final String id) {
    runScript(".getSelectionModel().select(" //
        + getExpression() + ".nodeHash['" + id + "']" + //
        ")");
    final TreeNode treeNode = new TreeNode(selenium, getExpression());

    return treeNode.getSelectedNode();
  }

  /**
   * Method hasErrorText.
   * @param err String
   * @return boolean */
  public boolean hasErrorText(final String err) {
    final String text = getErrorText();

    return err.equals(text);
  }

  /**
   * Method getErrorText.  
   * @return String */
  public String getErrorText() {
    final String text = selenium.getText(getXPath() + "//div[@class='x-form-invalid-msg']");
    return text;
  }

  /**
   * Method contains.
   * @param id String
   * @return boolean */
  public boolean contains(final String id) {
    final String eval = getEval(".nodeHash['" + id + "'] != null");
    return Boolean.parseBoolean(eval);
  }

}
