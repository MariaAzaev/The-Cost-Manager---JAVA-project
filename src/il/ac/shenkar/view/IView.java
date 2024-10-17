// Created By: Maria Azaev (315082529) & Sivan Cohen (209111590)

package il.ac.shenkar.view;

import il.ac.shenkar.classes.Expense;

import java.util.List;

/**
 * This interface defines the contract for the View component in the Expense Tracking System.
 */
public interface IView {
    /**
     * Initializes the user interface.
     */
    void initializeUI();

    /**
     * Displays a dialog to generate a report by a specific date.
     */
    void showGenerateReportByDateDialog();

    /**
     * Displays a dialog to generate a report by a specific month and year.
     */
    void showGenerateReportByMonthAndYearDialog();

    /**
     * Displays a report of expenses.
     *
     * @param expenses The list of expenses to display in the report.
     */
    void displayReport(List<Expense> expenses);

    /**
     * Displays a message to the user.
     *
     * @param message The message to display.
     */
    void showMessage(String message);

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to display.
     */
    void showError(String message);
}
