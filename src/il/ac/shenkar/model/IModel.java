// Created By: Maria Azaev (315082529) & Sivan Cohen (209111590)

package il.ac.shenkar.model;

import il.ac.shenkar.classes.Category;
import il.ac.shenkar.classes.Expense;

import java.util.List;
import java.sql.Date;

/**
 * Interface for the Model component.
 * This interface defines methods for interacting with a data model.
 */
public interface IModel {

    /**
     * Initializes the database connection and sets up necessary tables.
     *
     * @throws ModelException if there is an error initializing the database.
     */
    public void initializeDB() throws ModelException;

    /**
     * Adds a new category to the database.
     *
     * @param categoryName The name of the category to be added.
     * @throws ModelException if there is an error adding the category.
     */
    void addCategory(String categoryName) throws ModelException;

    /**
     * Retrieves a list of all categories from the database.
     *
     * @return A list of Category objects representing the categories.
     * @throws ModelException if there is an error fetching categories.
     */
    List<Category> getCategories() throws ModelException;

    /**
     * Adds a new expense to the database.
     *
     * @param expense The Expense object representing the expense to be added.
     * @throws ModelException if there is an error adding the expense.
     */
    void addExpense(Expense expense) throws ModelException;

    /**
     * Retrieves a list of expenses that match the given date.
     *
     * @param date The date for which to retrieve expenses.
     * @return A list of Expense objects matching the specified date.
     * @throws ModelException if there is an error fetching expenses by date.
     */
    List<Expense> getExpensesByDate(Date date) throws ModelException;

    /**
     * Retrieves a list of expenses that match the given month and year.
     *
     * @param month The month for which to retrieve expenses.
     * @param year  The year for which to retrieve expenses.
     * @return A list of Expense objects matching the specified month and year.
     * @throws ModelException if there is an error fetching expenses by month and year.
     */
    List<Expense> getExpensesByMonthAndYear(int month, int year) throws ModelException;
}

