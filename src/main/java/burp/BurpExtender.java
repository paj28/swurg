/*
#    Copyright (C) 2016-2022 Alexandre Teyar

# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at

# http://www.apache.org/licenses/LICENSE-2.0

# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
#    limitations under the License. 
*/

package burp;

import java.util.Calendar;

import swurg.gui.MainTabGroup;

public class BurpExtender implements IBurpExtender {

  public static final String COPYRIGHT = String.format(
      "<html>Copyright \u00a9 2016 - %s Alexandre Teyar, Aegis Cyber &lt;<a href=\"https://aegiscyber.co.uk\">www.aegiscyber.co.uk</a>&gt;. All Rights Reserved.</htnl>",
      Calendar.getInstance().get(Calendar.YEAR));
  public static final String EXTENSION = "OpenAPI Parser";
  public static final String VERSION = "3.0";

  @Override
  public void registerExtenderCallbacks(IBurpExtenderCallbacks callbacks) {
    MainTabGroup mainTabGroup = new MainTabGroup(callbacks);

    callbacks.setExtensionName(EXTENSION);

    callbacks.addSuiteTab(mainTabGroup);
    callbacks.customizeUiComponent(mainTabGroup.getUiComponent());
    callbacks.printOutput(String.format("'%s' tab initialised", EXTENSION));

    callbacks.registerContextMenuFactory(new ContextMenuFactory(callbacks, mainTabGroup.getParserPanel()));
    callbacks.printOutput(String.format("'Send to %s' option added to the context menu", EXTENSION));
    callbacks.registerHttpListener(mainTabGroup.getParametersPanel());
    callbacks.printOutput("'HTTPListener' registered");
    callbacks.registerMessageEditorTabFactory(mainTabGroup.getParametersPanel());
    callbacks.printOutput("'MessageEditorTabFactory' registered");
  }
}
