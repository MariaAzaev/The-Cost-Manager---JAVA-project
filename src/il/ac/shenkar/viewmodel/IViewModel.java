// Created By: Maria Azaev (315082529) & Sivan Cohen (209111590)

package il.ac.shenkar.viewmodel;

import il.ac.shenkar.classes.Category;
import il.ac.shenkar.classes.Expense;
import il.ac.shenkar.model.ModelException;

import java.sql.Date;
import java.util.List;

/**
 * The IViewModel interface defines the methods that must be implemented by the ViewModel class.
 */
public interface IViewModel {
    /**
     * Adds a category with the specified name.
     *
     * @param categoryName The name of the category to add.
     * @throws ModelException If an error occurs while adding the category.
     */
    void addCategory(String categoryName) throws ModelException;

    /**
     * Retrieves a list of categories.
     *
     * @return A list of Category objects.
     * @throws ModelException If an error occurs while fetching the categories.
     */
    List<Category> getCategories() throws ModelException;

    /**
     * Adds an expense to the system.
     *
     * @param expense The Expense object to add.
     * @throws ModelException If an error occurs while adding the expense.
     */
    void addExpense(Expense expense) throws ModelException;

    /**
     * Retrieves a list of expenses by the specified date.
     *
     * @param date The date to filter expenses by.
     * @return A list of Expense objects.
     * @throws ModelException If an error occurs while fetching expenses.
     */
    List<Expense> getExpensesByDate(Date date) throws ModelException;

    /**
     * Retrieves a list of expenses by the specified month and year.
     *
     * @param month The month to filter expenses by.
     * @param year  The year to filter expenses by.
     * @return A list of Expense objects.
     * @throws ModelException If an error occurs while fetching expenses.
     */
    List<Expense> getExpensesByMonthAndYear(int month, int year) throws ModelException;
}
