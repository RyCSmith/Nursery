Nursery
=======


Design: The concept and initial components of this program were designed by Diane Wolff at Virginia Western Community College. Implementation and code designed by Ryan Smith.



Overview: Program to run the fundamental operations of an agricultural nursery. Enforces principles of defensive programming. *Please see note below regarding documentation.

This program consists of 13 classes all contained within the package rsmithNurseryProject.
A brief description of the classes is as follows:

—RunNursery - used to launch the operation of the nursery.

—InputOutput - provides sample data, that is loaded into the program automatically for demonstration purposes

—MenuInfo - provides methods that service the menu options provided by RunNursery.

—MakeSale - provide items to take customer and product info and complete a sale.

—Sales - provides blueprint for Sales object. Implements Serializable.

—Customer - provides the blueprint for a Customer object. Implements Comparable and Serializable for sorting.

—Inventory - abstract class for inventory items. Implements Comparable and Serializable for sorting.

—Plants - abstract class that extends Inventory. Lays blueprint for Plant objects.

—BulkProducts - extends Inventory. Lays blueprint for non-plant items.

—Perennials - extends Plants. Provides blueprint for Perennial plant objects.

—Trees - extends Plants. Provides blueprint for Tree plant objects.

—Day - provides methods for tracking and assigning dates to sales.

—DayComparator - implements Comparator and used for arranging sales by date.



Use: This project does not use a GUI. It is intended to be run from the command line.



Other Info: This project was designed within the Eclipse IDE which provided an overview of constructors, methods etc. While it attempts to enforce proper naming conventions, it lacks proper documentation at this time. Documentation will be added to improve readability.

