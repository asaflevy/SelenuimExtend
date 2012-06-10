/***
 * 
 * @author Asaf
 *
 */

package com.outbrain.selenium.extjs.components;

import com.outbrain.selenium.extjs.core.locators.ComponentLocator;

/**
 * @author Asaf Levy
 * @version $Revision: 1.0
 */
public class TreeGrid extends Component {

  /**
   * Constructor for TreeGrid.
   * @param locator ComponentLocator
   */
  public TreeGrid(final ComponentLocator locator) {
    super(locator);
  }

  /**
   * return the root-node of the tree 
  
   * @return - node */
  public TreeNode getRootNode() {
    final TreeNode treeNode = new TreeNode(selenium, getExpression());
    return treeNode.getRootNode();
  }

  /**
   * select node by given string
   * @param name 
  
  
   * @return TreeNode */
  public TreeNode selectNodeByName(final String name) {
    final TreeNode treeNode = new TreeNode(selenium, getExpression());
    treeNode.getRootNode().findNodeGridChild(name);

    return treeNode.getSelectedNode();
  }

  /**
   * Select the ui-node by given nodeId
   * @param id - nodeId
  
  
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

  /**(search in node attribute)
  * return True if found any match 
  * @param id - nodeId
  
  
   * @return boolean */
  public boolean contains(final String id) {
    final String eval = getEval(".nodeHash['" + id + "'] != null");
    return Boolean.parseBoolean(eval);
  }

  /**
   * return the selected Treenode
  
   * @return TreeNode */
  public TreeNode getSelectedNode() {
    final TreeNode node = new TreeNode(selenium, getExpression());
    return node.getSelectedNode();
  }

  /**
   * Method whaitForloading.
   */
  public void waitForloading() {
    waitForTreeLoadingMask("loading-mask");
  }
}
