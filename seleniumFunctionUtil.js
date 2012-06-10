/**
 * auter Asaf levy
 *Extend javastring String function
 *
 */


function installAjaxListeners() {
	window.wasAjaxFailure = false;
	window.ajaxCalls = [];
	Ext.Ajax.on('requestexception', function() {(window.wasAjaxFailure = true)}, this);
	Ext.Ajax.on('beforerequest', function(conn, options) {
		window.ajaxCalls.push(conn.transId);
	});
}

Ext.onReady(function(){
	installAjaxListeners();
});


String.prototype.trim = function() {
	return this.replace(/^\s\s*/, '').replace(/\s\s*$/, '');
};

String.prototype.ltrim = function() {
	if (this)
		return this.replace(/^\s+/, '');
}

String.prototype.rtrim = function() {
	return this.replace(/\s+$/, '');
}

String.prototype.fulltrim = function() {
	return this.replace(/(?:(?:^|\n)\s+|\s+(?:$|\n))/g, '')
			.replace(/\s+/g, ' ');
}

/**
 * start trim on string from the left string side
 * @param {} s
 * @return {Boolean}
 */

function ltrim(s) {
	if (!s)
		return false;
	var l = 0;
	while (l < s.length && s[l] == ' ') {
		l++;
	}
	return s.substring(l, s.length);
}

/**
 * start trim on string from the right string side
 * @param {} s
 * @return {Boolean}
 */
function rtrim(s) {
	if (!s)
		return false;
	var r = s.length - 1;
	while (r > 0 && s[r] == ' ') {
		r -= 1;
	}
	return s.substring(0, r + 1);
}

/**
 * removing the last charecter from given string
 * @param {} s
 */
function rlastchar(s) {
	if (!s)
		return false;
	return s.substring(0, s.length - 1);
}

function foundIn(component, xtype) {
	var xtypes = component.getXTypes().split("/");
	var i = 0;
	for (i; i < xtypes.length; i++) {
		if (xtype == xtypes[i])
			return true
	};
	return false
}

/**
 * main function return  extjs component id from the Exjs component manager
 * @param {} text - text to search (title,lable,text....)
 * @param {} xtype - component type like grid,textfield,combobox ....)
 * @return {}
 */
var findComponentByText = function(text, xtype) {
	var item = "";
	var items = "";
	Ext.ComponentMgr.all.find(function(c) {
		switch (xtype) {
			case 'window' :
				item = Ext.WindowMgr.getActive().id + ",";
				break;
			case 'grid' :
				if ((foundIn(c, (rtrim(ltrim(xtype)))) && (c.isVisible()))
						|| ((c.isVisible()))
						&& (c.getXType() == rtrim(ltrim(xtype)) || c.xtype == rtrim(ltrim(xtype)))) {
					item += c.id + ",";
				}
				break;
			case 'tabpanel' :
				if ((rtrim(ltrim(c.text)) == text
						|| rtrim(ltrim(c.fieldLabel)) == text || rtrim(ltrim(c.title)) == text)
						&& (c.ownerCt.xtype == xtype || c.ownerCt.type == rtrim(ltrim(xtype)))
						&& (c.ownerCt.isVisible())) {
					item = c.ownerCt.id + ",";
				}
				break;
			default :
				if (xtype) {
					if (text) {
						if ((c.isVisible())
								&& (rtrim(ltrim(c.text)) == text 
								|| rtrim(ltrim(c.boxLabel)) == text
								|| rtrim(ltrim(c.fieldLabel)) == text || rtrim(ltrim(c.title)) == text)
								&& (c.getXType() == rtrim(ltrim(xtype))
										|| c.xtype == rtrim(ltrim(xtype)) || c.type == rtrim(ltrim(xtype))))
							item += c.id+  ",";
					} else {
						if ((c.isVisible())
								&& (c.getXType() == rtrim(ltrim(xtype))
										|| c.xtype == rtrim(ltrim(xtype)) || c.type == rtrim(ltrim(xtype))))
							items += c.id + ",";
					}
				} else {
					if ((c.isVisible())
							&& ((rtrim(ltrim(c.text)) == text
									|| rtrim(ltrim(c.fieldLabel)) == text || rtrim(ltrim(c.title)) == text)))
						item += c.id + ",";
				}
				break;
		}
	});
	if (item) {
		return rlastchar(item);
	} else {
		if (items) {
			return rlastchar(items);
		}
		return null;
	}
}

/**
 * WARNING -- DOESN'T WORK WELL! Causes problems... use the new version instead
 * 
 * trying to create a valid selenium Xpath from Ext element
 * @param {} elm
 * @return {}
 */
/*
function createXPathFromElement(elm) {
	var allNodes = document.getElementsByTagName('*');
	for (segs = []; elm && elm.nodeType == 1; elm = elm.parentNode) {
		if (elm.hasAttribute('id')) {
			var uniqueIdCount = 0;
			for (var n = 0; n < allNodes.length; n++) {
				if (allNodes[n].hasAttribute('id') && allNodes[n].id == elm.id)
					uniqueIdCount++;
				if (uniqueIdCount > 1)
					break;
			};
			if (uniqueIdCount == 1) {
				segs.unshift('id("' + elm.getAttribute('id') + '")');
				return segs.join('/');
			} else {
				segs.unshift(elm.localName.toLowerCase() + '[@id="'
						+ elm.getAttribute('id') + '"]');
			}
		} else if (elm.hasAttribute('class')) {
			segs.unshift(elm.localName.toLowerCase() + '[@class="'
					+ elm.getAttribute('class') + '"]');
		} else {
			for (i = 1, sib = elm.previousSibling; sib; sib = sib.previousSibling) {
				if (sib.localName == elm.localName)
					i++;
			};
			segs.unshift(elm.localName.toLowerCase() + '[' + i + ']');
		};
	};
	return segs.length ? '/' + segs.join('/') : null;
};*/

function getElementIdx(elt)
{
    var count = 1;
    for (var sib = elt.previousSibling; sib ; sib = sib.previousSibling)
    {
        if(sib.nodeType == 1 && sib.tagName == elt.tagName)	count++
    }
    
    return count;
}

function createXPathFromElement(elt)
	{
		var path = "";
		for (; elt && elt.nodeType == 1; elt = elt.parentNode)
			{
				idx = getElementIdx(elt);
				xname = elt.tagName;
				xname += "[" + idx + "]";
				path = "/" + xname + path;
			}
		
		return "/" + path;
	}


function lookupElementByXPath(path) {
	var evaluator = new XPathEvaluator();
	var result = evaluator.evaluate(path, document.documentElement, null,
			XPathResult.FIRST_ORDERED_NODE_TYPE, null);
	return result.singleNodeValue;
}

/**
 * try to search in components like windows panels etc.. for some prop
 * @param conponentId
 * @param text
 * @param xtype
 */
function findInComponentByText(conponentId, text, xtype) {
	var item = null;

	var component = Ext.getCmp(conponentId)
	var componentsArray = component.findBy(function(c) {
				return true
			});
	Ext.each(componentsArray, function(c, index) {
		if ((c.isVisible())
				&& (rtrim(ltrim(c.text)) == text
						|| rtrim(ltrim(c.fieldLabel)) == text || rtrim(ltrim(c.title)) == text)
				&& (c.getXType() == rtrim(ltrim(xtype))
						|| c.xtype == rtrim(ltrim(xtype)) || c.type == rtrim(ltrim(xtype)))) {
			item = c;
		}
	});

	if (item != null) {
		return c.getId();
	} else {

		switch (xtype) {
			case 'button' :
				if (component.getXType() == 'window') {
					var winButtons = component.buttons;
					Ext.each(winButtons, function(c, index) {
						if ((c.isVisible())
								&& (rtrim(ltrim(c.text)) == text
										|| rtrim(ltrim(c.fieldLabel)) == text || rtrim(ltrim(c.title)) == text)
								&& (c.getXType() == rtrim(ltrim(xtype))
										|| c.xtype == rtrim(ltrim(xtype)) || c.type == rtrim(ltrim(xtype)))) {
							item = c;
						}
					});
					break;
				}
		}
	}
	if (item != null) {
		return item.getId();
	} else {
		return null;
	}
}

 

function verifyNoAjaxCalls() {
	for (i in window.ajaxCalls) {
		var ajaxLoading = Ext.Ajax.isLoading(window.ajaxCalls[i]);
		if (ajaxLoading) {
			return false;
		}
	}
	return true;
}
