// Created By: Maria Azaev (315082529) & Sivan Cohen (209111590)

package il.ac.shenkar.viewmodel;

import il.ac.shenkar.classes.Category;
import il.ac.shenkar.classes.Expense;
import il.ac.shenkar.model.IModel;
import il.ac.shenkar.model.ModelException;

import java.sql.Date;
import java.util.List;

/**
 * The ViewModel class implements the IViewModel interface to interact with the model.
 */
public class ViewModel implements IViewModel {
    private IModel model;

    /**
     * Constructs a ViewModel with the provided model.
     *
     * @param model The model to use for interaction.
     */
    public ViewModel(IModel model) {
        this.model = model;
    }

    /**
     * Adds a category with the specified name.
     *
     * @param categoryName The name of the category to add.
     * @throws ModelException If an error occurs while adding the category.
     */
    public void addCategory(String categoryName) throws ModelException {
        model.addCategory(categoryName);
    }

    /**
     * Retrieves a list of categories.
     *
     * @return A list of Category objects.
     * @throws ModelException If an error occurs while fetching the categories.
     */
    public List<Category> getCategories() throws ModelException {
        return model.getCategories();
    }

    /**
     * Adds an expense to the system.
     *
     * @param expense The Expense object to add.
     * @throws ModelException If an error occurs while adding the expense.
     */
    public void addExpense(Expense expense) throws ModelException {
        model.addExpense(expense);
    }

    /**
     * Retrieves a list of expenses by the specified date.
     *
     * @param date The date to filter expenses by.
     * @return A list of Expense objects.
     * @throws ModelException If an error occurs while fetching expenses.
     */
    public List<Expense> getExpensesByDate(Date date) throws ModelException {
        return model.getExpensesByDate(date);
    }

    /**
     * Retrieves a list of expenses by the specified month and year.
     *
     * @param month The month to filter expenses by.
     * @param year  The year to filter expenses by.
     * @return A list of Expense objects.
     * @throws ModelException If an error occurs while fetching expenses.
     */
    public List<Expense> getExpensesByMonthAndYear(int month, int year) throws ModelException {
        return model.getExpensesByMonthAndYear(month, year);
    }
}
