package com.outbrain.selenium.extjs.components;

import com.outbrain.selenium.extjs.core.locators.ComponentLocator;
import com.thoughtworks.selenium.Selenium;

/**
 * @author Asaf Levy
 * @version $Revision: 1.0
 */
public class TreeNode extends Component {

  final String getUIfunction = ".getUI()";

  /**
   * Field nodeExpression.
   */
  private String nodeExpression = "";
  /**
   * Field nodeUiExpression.
   */
  private String nodeUiExpression = "";
  /**
   * Field treeExpression.
   */
  private String treeExpression = "";

  /**
   * Constructor for TreeNode.
   * @param selenium Selenium
   * @param expression String
   */
  public TreeNode(final Selenium selenium, final String expression) {
    super(selenium, expression);
    treeExpression = expression;
  }

  /**
   * @param parentTree Locator
   */
  public TreeNode(final ComponentLocator parentTree) {
    super(parentTree.getSelenium(), parentTree.getComponentId());
    treeExpression = String.format("window.Ext.getCmp('%s')", parentTree.getComponentId());
  }

  /**
   * return  the root node of given Tree 
  

   * @return TreeNode */
  public TreeNode getRootNode() {
    nodeExpression = ".getRootNode()";
    runCleanScript(String.format("%s.%s", treeExpression, nodeExpression));
    return this;

  }

  /**
   * Trying to search in tree and find a child with given value if found the node will be select 
   * @param val
   * @return - selected node (if found one) */
  public TreeNode findChild(final String val) {
    if (nodeExpression.isEmpty() || nodeExpression.length() == 0) {
      getRootNode();
    }
    getEval(nodeExpression + ".findChild( 'name' ,'" + val + "' , true ).select()");
    return this;
  }

  /**
   * Method findNodeGridChild.
   * @param val String
   * @return TreeNode */
  public TreeNode findNodeGridChild(final String val) {
    if (nodeExpression.isEmpty() || nodeExpression.length() == 0) {
      getRootNode();
    }
    getCleanEval(treeExpression + nodeExpression + ".findChild( 'name' ," + "\"<span style='color:black'>" + val + "</span>\" , true ).select()");
    return this;
  }

  /**
   * return child count of selected node 
  
   * @return - the count of node child */
  public int getLength() {
    return Integer.parseInt(getCleanEval(String.format("%s%s.childNodes.length", treeExpression, nodeExpression)));

  }

  /**
   * Method select.
   * @param id String
  
   * @return TreeNode */
  public TreeNode select(final String id) {
    runScript(".getSelectionModel().select(" //
        + getExpression() + ".nodeHash['" + id + "']" + //
        ")");

    return getSelectedNode();
  }

  /**
   * return the Selected node
  
  
   * @return TreeNode */

  public TreeNode getSelectedNode() {
    nodeExpression = ".getSelectionModel().getSelectedNode()";
    runCleanScript(treeExpression + nodeExpression);
    return this;
  }

  /**
   * return node UI of the selected node
   * @return String */
  public String getNodeUI() {
    nodeUiExpression = nodeExpression + ".ui.getEl().firstChild";
    return getCleanEval(treeExpression + nodeExpression + ".ui.getEl().firstChild");
  }

  /**
   * Method getCell.
   * @param index Integer
  
   * @return String */
  public String getCell(final Integer index) {
    if (nodeUiExpression.isEmpty()) {
      getNodeUI();
    }

    return getCleanEval(treeExpression + nodeUiExpression + ".childNodes[" + index + "].firstChild.innerHTML").replaceAll("\\<.*?>", "");
  }

  /**
   * return the value by given attribute (the node should be selected) 
   * @param att
  
  
   * @return String */
  public String getSelectedNodeAtt(final String att) {
    return getEval(".getSelectionModel().getSelectedNode().attributes." + att);

  }

  /**
   * return Node attribute 
   * @param att
  
  
   * @return String */
  public String getNodeAtt(final String att) {
    return getEval(nodeExpression + ".attributes." + att);
  }

  /**
   * expend the Selected node
   */
  public void expentNode() {
    nodeExpression = ".getSelectionModel().getSelectedNode()";
    runScript(String.format("%s.expand()", nodeExpression));
  }

  /**
   * expend the root Node
   */
  public void expentRootNode() {
    nodeExpression = ".getSelectionModel().getSelectedNode()";
    runScript(String.format("%s.expand()", getRootNode()));
  }

  /**
   * collapse the Selected node
   */
  public void collapseNode() {
    nodeExpression = ".getSelectionModel().getSelectedNode()";
    runScript(String.format("%s.collapse()", nodeExpression));
  }

  /**
   * collapse the root Node
   */
  public void collapseRootNode() {
    nodeExpression = ".getSelectionModel().getSelectedNode()";
    runScript(String.format("%s.collapse()", getRootNode()));
  }

  /**
   * check if the selected node has chides
   * @return boolean */
  public boolean hasChildNodes() {
    return Boolean.parseBoolean(getEval(String.format("%s.hasChildNodes() ", nodeExpression)));
  }

  /**
   * Returns true if the selected node is the first child of its parent
   * @return boolean */
  public boolean isFirst() {
    return Boolean.parseBoolean(getEval(String.format("%s.isFirst() ", nodeExpression)));
  }

  /**
   * Returns true if the selected node node is a leaf
   * @return boolean */
  public boolean isLeaf() {
    return Boolean.parseBoolean(getEval(String.format("%s.isLeaf() ", nodeExpression)));
  }

  /**
   * Returns true if the selected node is Selected
   * @return boolean */
  public boolean isSelected() {
    return Boolean.parseBoolean(getEval(String.format("%s.isSelected() ", nodeExpression)));
  }

  /**
   * 
   * @return
   */
  public Boolean isChecked() {
    return Boolean.parseBoolean(getEval(String.format("%s.isChecked() ", treeExpression + getUIfunction)));
  }

  /**
   * Sets the checked status of the tree node to the passed value . The node must be selected
   * @param check
   */
  public void toggleCheck(final Boolean check) {
    runCleanScript(String.format("%s.getSelectionModel().getSelectedNode().getUI().toggleCheck(%s)", treeExpression, check));
  }

  /**
   * try to select an node and Sets the checked status of the tree node to the passed value .
   * @param attribute
   * @param value
   * @param check
   */
  public void toggleCheck(final String attribute, final String value, final Boolean check) {
    findChild(attribute, value);
    getSelectedNode();
    runCleanScript(String.format("%s%s%s.toggleCheck(%s)", treeExpression, nodeExpression, getUIfunction, check));
  }

  /**
   * Expand this node. the node must be selected;
   */
  public void expand() {
    getSelectedNode();
    runCleanScript(String.format("%s%s.expand()", treeExpression, nodeExpression));
  }

  /**
   * find specific node in tree by given attribute and SELECT him
   * @param attribute
   * @param value
   * @return
   */
  public TreeNode findChild(final String attribute, final String value) {
    runCleanScript(String.format("%s.getRootNode().findChild('%s','%s',true).select()", treeExpression, attribute, value));
    return this;
  }

}
