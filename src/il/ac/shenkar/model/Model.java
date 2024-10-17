// Created By: Maria Azaev (315082529) & Sivan Cohen (209111590)

package il.ac.shenkar.model;

import il.ac.shenkar.classes.Category;
import il.ac.shenkar.classes.Expense;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.*;
import java.sql.Date; // Imported Date class from java.util package as java.sql.Date is used in the code.

/**
 * This is the Model class that implements the IModel interface.
 */
public class Model implements IModel{

    private Connection connection = null;
    /**
     * Constructor for the Model class.
     *
     * @throws ModelException if there is an error initializing the database.
     */
    public Model() throws ModelException {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            connection = DriverManager.getConnection("jdbc:derby:costManagerDB;create=true");
            initializeDB();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new ModelException("Failed to connect to DB", e);
        }
    }


    /**
     * Initializes the database tables if they do not exist.
     *
     * @throws ModelException if there is an error initializing the database.
     */
    public void initializeDB() throws ModelException {
        if (!doesTableExist("CATEGORIES")) {
            createCategoriesTable();
        }
        if (!doesTableExist("EXPENSES")) {
            createExpensesTable();
        }
    }

    /**
     * Checks if a table with the given name exists in the database.
     *
     * @param tableName the name of the table to check.
     * @return true if the table exists, false otherwise.
     * @throws ModelException if there is an error executing the SQL query.
     */
    private boolean doesTableExist(String tableName) throws ModelException {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM SYS.SYSTABLES WHERE TABLENAME = '" + tableName + "'");
            rs.next();
            return rs.getInt(1) > 0;
        } catch (SQLException e) {
            throw new ModelException("Failed", e);
        }
    }

    /**
     * Creates the Categories table in the database.
     *
     * @throws ModelException if there is an error executing the SQL statement.
     */
    private void createCategoriesTable() throws ModelException {
        try {
            Statement stmt = connection.createStatement();
            String sql = """
                CREATE TABLE Categories (
                  id INT GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY,
                  name VARCHAR(45) UNIQUE
            )""";
            stmt.executeUpdate(sql);
            stmt.executeUpdate("INSERT INTO Categories (name) VALUES ('Entertainment'), ('Food'), " +
                    "('Travel and Vacation'), ('Clothing Purchases'), ('Healthcare'), ('Housing')");
        } catch (SQLException e) {
            throw new ModelException("Failed", e);
        }
    }

    /**
     * Creates the Expenses table in the database.
     *
     * @throws ModelException if there is an error executing the SQL statement.
     */
    private void createExpensesTable() throws ModelException {
        try (Statement stmt = connection.createStatement()) {
            String sql = """
                CREATE TABLE Expenses (
                  id INT GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY,
                  catID INT,
                  total INT,
                  currency VARCHAR(45),
                  description VARCHAR(255),
                  createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                  FOREIGN KEY (catID) REFERENCES Categories (id)
            )""";
            stmt.executeUpdate(sql);
            stmt.executeUpdate("""
                INSERT INTO Expenses (catID, total, currency, description) VALUES
                    (1, 150, 'USD', 'Buying movie tickets at the cinema'),
                    (2, 300, 'EUR', 'Food shopping for the week'),
                    (3, 1500, 'USD', 'Buying plane tickets to London'),
                    (4, 200, 'JPY', 'Buying a dress for an event'),
                    (5, 20, 'USD', 'Dental care'),
                    (4, 700, 'JPY', 'Buying high heels'),
                    (6, 350, 'USD', 'Monthly electricity payment'),
                    (1, 70, 'EUR', 'Going to a restaurant with friends')
            """);
        } catch (SQLException e) {
            throw new ModelException("Failed", e);
        }
    }

    /**
     * Adds a new category to the database.
     *
     * @param categoryName The name of the category to be added.
     * @throws ModelException if there is an error adding the category.
     */
    @Override
    public void addCategory(String categoryName) throws ModelException { //OK
        try {
             PreparedStatement stmt = connection.prepareStatement("INSERT INTO Categories (name) VALUES (?)");
            stmt.setString(1, categoryName);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ModelException("Error adding category: " + e.getMessage());
        }
    }

    /**
     * Retrieves a list of all categories from the database.
     *
     * @return A list of Category objects representing the categories.
     * @throws ModelException if there is an error fetching categories.
     */
    @Override
    public List<Category> getCategories() throws ModelException { //OK
        List<Category> categories = new ArrayList<>();
        try {
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Categories");

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                categories.add(new Category(id, name));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ModelException("Error adding category: " + e.getMessage());
        }
        return categories;
    }

    /**
     * Retrieves a list of expenses that match the given date.
     *
     * @param date The date for which to retrieve expenses.
     * @return A list of Expense objects matching the specified date.
     * @throws ModelException if there is an error fetching expenses by date.
     */

    @Override
    public List<Expense> getExpensesByDate(Date date) throws ModelException {
        List<Expense> expenses = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Expenses WHERE DATE(createdAt) = ?");
            stmt.setDate(1, date);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int catID = rs.getInt("catID");
                int total = rs.getInt("total");
                String currency = rs.getString("currency");
                String description = rs.getString("description");
                Timestamp createdAt = rs.getTimestamp("createdAt");

                Expense expense = new Expense(id, catID, total, currency, description, createdAt);
                expenses.add(expense);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ModelException("Error fetching expenses by date: " + e.getMessage());
        }
        return expenses;
    }

    /**
     * Retrieves a list of expenses that match the given month and year.
     *
     * @param month The month for which to retrieve expenses.
     * @param year  The year for which to retrieve expenses.
     * @return A list of Expense objects matching the specified month and year.
     * @throws ModelException if there is an error fetching expenses by month and year.
     */

    @Override
    public List<Expense> getExpensesByMonthAndYear(int month, int year) throws ModelException {
        List<Expense> expenses = new ArrayList<>();
        try {
             PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Expenses WHERE MONTH(createdAt) = ? AND YEAR(createdAt) = ?");
            stmt.setInt(1, month);
            stmt.setInt(2, year);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int catID = rs.getInt("catID");
                int total = rs.getInt("total");
                String currency = rs.getString("currency");
                String description = rs.getString("description");
                Timestamp createdAt = rs.getTimestamp("createdAt");

                Expense expense = new Expense(id, catID, total, currency, description, createdAt);
                expenses.add(expense);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ModelException("Error fetching expenses by month and year: " + e.getMessage());
        }
        return expenses;
    }

    /**
     * Adds a new expense to the database.
     *
     * @param expense The Expense object representing the expense to be added.
     * @throws ModelException if there is an error adding the expense.
     */

    @Override
    public void addExpense(Expense expense) throws ModelException {
        try {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO Expenses (catID, total, currency, description) VALUES (?, ?, ?, ?)");
            stmt.setInt(1, expense.getCatID());
            stmt.setInt(2, expense.getTotal());
            stmt.setString(3, expense.getCurrency());
            stmt.setString(4, expense.getDescription());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ModelException("Error adding expense: " + e.getMessage());
        }
    }
}
