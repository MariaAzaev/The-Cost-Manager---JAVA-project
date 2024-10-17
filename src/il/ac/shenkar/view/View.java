// Created By: Maria Azaev (315082529) & Sivan Cohen (209111590)

package il.ac.shenkar.view;

import il.ac.shenkar.classes.Category;
import il.ac.shenkar.classes.Expense;
import il.ac.shenkar.model.ModelException;
import il.ac.shenkar.viewmodel.ViewModel;
import il.ac.shenkar.viewmodel.IViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * The View class represents the user interface of the Expense Tracking System.
 */
public class View implements IView{
    private IViewModel vm;
    private ViewModel viewModel;
    private JFrame frame;
    private JButton addCategoryButton;
    private JButton viewCategoriesButton;
    private JButton addExpenseButton;
    private JButton generateReportButton;
    private JTextArea outputTextArea;

    /**
     * Constructs a new View instance.
     *
     * @param viewModel The ViewModel instance to associate with this view.
     */
    public View(ViewModel viewModel) {
        this.viewModel = viewModel;
        initializeUI();
    }

    /**
     * Initializes the user interface components.
     */

    public void initializeUI() {
        frame = new JFrame("Expense Tracking System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1));

        addCategoryButton = new JButton("Add Category");
        viewCategoriesButton = new JButton("View Categories");
        addExpenseButton = new JButton("Add Expense");
        generateReportButton = new JButton("Generate Expense Report");

        outputTextArea = new JTextArea();
        outputTextArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(outputTextArea);

        panel.add(addCategoryButton);
        panel.add(viewCategoriesButton);
        panel.add(addExpenseButton);
        panel.add(generateReportButton);
        panel.add(scrollPane);

        frame.add(panel);
        frame.setVisible(true);

        addCategoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle add category button click
                String categoryName = JOptionPane.showInputDialog("Enter category name:");
                if (categoryName != null) {
                    try {
                        viewModel.addCategory(categoryName);
                        showMessage("Category added successfully.");
                    } catch (ModelException ex) {
                        showError("Error adding category: " + ex.getMessage());
                    }
                }
            }
        });

        viewCategoriesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    List<Category> categories = viewModel.getCategories();
                    StringBuilder categoryList = new StringBuilder("Categories:\n");
                    for (Category category : categories) {
                        categoryList.append(category.getName()).append("\n");
                    }
                    showMessage(categoryList.toString());
                } catch (ModelException ex) {
                    showError("Error fetching categories: " + ex.getMessage());
                }
            }
        });

        addExpenseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JPanel expensePanel = new JPanel(new GridLayout(5, 2));

                expensePanel.add(new JLabel("Category ID:"));
                JTextField catIDField = new JTextField();
                expensePanel.add(catIDField);

                expensePanel.add(new JLabel("Total:"));
                JTextField totalField = new JTextField();
                expensePanel.add(totalField);

                expensePanel.add(new JLabel("Currency:"));
                JTextField currencyField = new JTextField();
                expensePanel.add(currencyField);

                expensePanel.add(new JLabel("Description:"));
                JTextField descriptionField = new JTextField();
                expensePanel.add(descriptionField);

                int result = JOptionPane.showConfirmDialog(null, expensePanel, "Add Expense", JOptionPane.OK_CANCEL_OPTION);

                if (result == JOptionPane.OK_OPTION) {

                    try {
                        int catID = Integer.parseInt(catIDField.getText());
                        int total = Integer.parseInt(totalField.getText());
                        String currency = currencyField.getText();
                        String description = descriptionField.getText();

                        Expense expense = new Expense(0, catID, total, currency, description, new java.sql.Timestamp(System.currentTimeMillis()));

                        viewModel.addExpense(expense);

                        JOptionPane.showMessageDialog(null, "Expense added successfully.");
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Invalid input. Please enter valid numeric values.");
                    } catch (ModelException ex) {
                        JOptionPane.showMessageDialog(null, "Error adding expense: " + ex.getMessage());
                    }
                }
            }
        });

        generateReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showGenerateReportDialog();
            }
        });
    }

    /**
     * Displays the generate report dialog.
     */
    private void showGenerateReportDialog() {
        Object[] options = {"By Specific Date", "By Specific Month and Year"};
        int choice = JOptionPane.showOptionDialog(frame, "Select report type:", "Generate Expense Report", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        if (choice == 0) {
            showGenerateReportByDateDialog();
        } else if (choice == 1) {
            showGenerateReportByMonthAndYearDialog();
        }
    }

    /**
     * Displays a dialog to generate a report by a specific date.
     */
    public void showGenerateReportByDateDialog() {
        JTextField dateField = new JTextField(10);
        JPanel datePanel = new JPanel();
        datePanel.add(new JLabel("Enter date (YYYY-MM-DD):"));
        datePanel.add(dateField);

        int result = JOptionPane.showConfirmDialog(null, datePanel, "Generate Report by Date", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            String dateText = dateField.getText();

            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date parsedDate = dateFormat.parse(dateText);
                Date date = new Date(parsedDate.getTime());

                List<Expense> expenses = viewModel.getExpensesByDate(date);

                if (!expenses.isEmpty()) {
                    displayReport(expenses);
                } else {
                    showMessage("No expenses found for the selected date.");
                }
            } catch (Exception ex) {
                showError("Invalid date format. Use YYYY-MM-DD.");
            }
        }
    }

    /**
     * Displays a dialog to generate a report by a specific month and year.
     */

    public void showGenerateReportByMonthAndYearDialog() {
        JTextField monthField = new JTextField(2);
        JTextField yearField = new JTextField(4);
        JPanel monthYearPanel = new JPanel();
        monthYearPanel.add(new JLabel("Enter month (MM):"));
        monthYearPanel.add(monthField);
        monthYearPanel.add(new JLabel("Enter year (YYYY):"));
        monthYearPanel.add(yearField);

        int result = JOptionPane.showConfirmDialog(null, monthYearPanel, "Generate Report by Month and Year", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            try {
                int month = Integer.parseInt(monthField.getText());
                int year = Integer.parseInt(yearField.getText());

                List<Expense> expenses = viewModel.getExpensesByMonthAndYear(month, year);

                if (!expenses.isEmpty()) {
                    displayReport(expenses);
                } else {
                    showMessage("No expenses found for the selected month/year.");
                }
            } catch (NumberFormatException ex) {
                showError("Invalid input. Please enter valid numeric values.");
            } catch (ModelException ex) {
                showError("Error fetching expenses: " + ex.getMessage());
            }
        }
    }

    /**
     * Displays a report of expenses in the text area.
     *
     * @param expenses The list of expenses to display in the report.
     */

    public void displayReport(List<Expense> expenses) {
        StringBuilder report = new StringBuilder("Expense Report:\n\n");
        for (Expense expense : expenses) {
            report.append("ID: ").append(expense.getId()).append("\n");
            report.append("Category id: ").append(expense.getCatID()).append("\n");
            report.append("Total: ").append(expense.getTotal()).append("\n");
            report.append("Currency: ").append(expense.getCurrency()).append("\n");
            report.append("Description: ").append(expense.getDescription()).append("\n");
            report.append("Created At: ").append(expense.getCreatedAt()).append("\n\n");
        }
        showMessage(report.toString());
    }

    /**
     * Displays a message in the output text area.
     *
     * @param message The message to display.
     */
    public void showMessage(String message) {
        outputTextArea.setText(message);
    }

    /**
     * Displays an error message in a dialog.
     *
     * @param message The error message to display.
     */
    public void showError(String message) {
        JOptionPane.showMessageDialog(frame, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Sets the ViewModel for this view.
     *
     * @param vm The ViewModel instance to set.
     */
    public void setViewModel(IViewModel vm) {
        this.vm = vm;
    }
}
