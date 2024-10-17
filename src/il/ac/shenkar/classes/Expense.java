// Created By: Maria Azaev (315082529) & Sivan Cohen (209111590)

package il.ac.shenkar.classes;

import java.sql.Timestamp;

/**
 * Represents an Expense with details such as category, currency, description and more.
 *
 * @author YourName
 */
public class Expense {

    /** Unique identifier for the expense */
    private int id;

    /** Identifier for the category */
    private int catID;

    /** Total expense amount */
    private int total;

    /** Currency of the expense */
    private String currency;

    /** Brief description of the expense */
    private String description;

    /** Timestamp of when the expense was created */
    private Timestamp createdAt;

    /**
     * Creates an instance of the Expense.
     *
     * @param id Unique identifier
     * @param catID Category identifier
     * @param total Amount of the expense
     * @param currency Currency type
     * @param description Description of the expense
     * @param createdAt Timestamp of creation
     */
    public Expense(int id, int catID, int total, String currency, String description, Timestamp createdAt) {
        setId(id);
        setCatID(catID);
        setTotal(total);
        setCurrency(currency);
        setDescription(description);
        setCreatedAt(createdAt);
    }

    /**
     * Returns the ID of the Expense.
     *
     * @return The ID of the Expense.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Sets the ID of the Expense.
     *
     * @param id The ID to set.
     */
    private void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the category ID of the Expense.
     *
     * @return The category ID of the Expense.
     */
    public int getCatID() {
        return this.catID;
    }

    /**
     * Sets the category ID of the Expense.
     *
     * @param catID The category ID to set.
     */
    private void setCatID(int catID) {
        this.catID = catID;
    }

    /**
     * Returns the total amount of the Expense.
     *
     * @return The total amount of the Expense.
     */
    public int getTotal() {
        return this.total;
    }

    /**
     * Sets the total amount of the Expense.
     *
     * @param total The total amount to set.
     */
    private void setTotal(int total) {
        this.total = total;
    }

    /**
     * Returns the currency of the Expense.
     *
     * @return The currency of the Expense.
     */
    public String getCurrency() {
        return this.currency;
    }

    /**
     * Sets the currency of the Expense.
     *
     * @param currency The currency to set.
     */
    private void setCurrency(String currency) {
        this.currency = currency;
    }


    /**
     * Returns the description of the Expense.
     *
     * @return The description of the Expense.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets the description of the Expense.
     *
     * @param description The description to set.
     */
    private void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the timestamp when the Expense was created.
     *
     * @return The timestamp when the Expense was created.
     */
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the timestamp when the Expense was created.
     *
     * @param createdAt The timestamp to set.
     */
    private void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
