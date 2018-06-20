# Overview

Java code style for use with code formatter tool in Java IDE like _Eclipse_ or _IntelliJ_.

## Files

Find code formatter file in :

* [Eclipse formatter file](codestyle/eclipse/eclipse_code_formatter.xml)

## Other details

See details in [Contribution guide](CONTRIBUTING.md).

## Eclipse JDT Core Code Formatter internal details

Useful classes for handling code formatting settings :

* `org.eclipse.jdt.core.formatter.DefaultCodeFormatterConstants` : Constants used to set up the options of the code formatter.
    * Example : `FORMATTER_BLANK_LINES_AFTER_IMPORTS` > `"org.eclipse.jdt.core.formatter.blank_lines_after_imports"`
* For creating Map of settings : 
    * `org.eclipse.jdt.core.formatter.DefaultCodeFormatterConstants#getEclipseDefaultSettings()`
    * `org.eclipse.jdt.core.formatter.DefaultCodeFormatterConstants#getJavaConventionsSettings()`
    