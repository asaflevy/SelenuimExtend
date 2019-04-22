# Selenium Tests with Ext framework 
---


Extjs Is a very powerful JavaScript framework and one of the most popular javascript users interface open source framework, however when it comes to automated test with selenium the real challenge begin.

It is very difficult to write an automated test to Ext application with selenium because Ext generates many `<div>` and `<span>` tags with an automatically-generated ID (something like “ext-comp-11xx”). Accessing these tags through Selenium is the big challenge we are trying to solve. We wanted to find a way to get these automatically-generated IDs automatically.
How do we approach this?

Ext has a component manager, where all of the developers’ components are being saved.  We can “ask” the component manager for the component ID by sending it a descriptor of the component. To simplify – we (the selenium server) tell the component manager “I need the ID of the currently visible window which, btw, is labeled as ‘campaign editor'”.

**Usage:**
ComponentLocatorFactory  extjsCmpLoc = new ComponentLocatorFactory(selenuim);

Window testWin = new Window(extjsCmpLoc.createLocator(”campaign editor”Xtype.WINDOW));
Then we can to use Ext window method like close -> testWin.close();

Anther Example :

ComponentLocatorFactory  extjsCmpLoc = new ComponentLocatorFactory(selenuim);

Button newButton = new  Button(extjsCmpLoc.createLocator(“Add Campaign”, ExtjsUtils.Xtype.BUTTON));

newButton.click();

![](https://i.imgur.com/PeTNvOn.png)


```

```
Here’s a simple diagram of my solution:

![](https://i.imgur.com/SU633ZX.png)
