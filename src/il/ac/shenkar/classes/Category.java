// Created By: Maria Azaev (315082529) & Sivan Cohen (209111590)

package il.ac.shenkar.classes;

/**
 * Represents a category with an ID and name.
 *
 * @author YourNameHere (replace with your name)
 */
public class Category {
    // Declaring the private variables
    private int id;
    private String name;

    /**
     * Primary constructor for Category.
     *
     * @param id The ID of the category.
     * @param name The name of the category.
     */
    public Category(int id, String name) {
        setId(id);
        setName(name);
    }

    /**
     * Returns the ID of the category.
     *
     * @return The ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the category.
     *
     * @param id The ID to set.
     */
    private void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the name of the category.
     *
     * @return The name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the category.
     *
     * @param name The name to set.
     */
    private void setName(String name) {
        this.name = name;
    }

    /**
     * Returns a string representation of the Category object.
     *
     * @return A string containing the Category's id and name.
     */
    @Override
    public String toString() {
        return "Category [id=" + id + ", name=" + name + "]";
    }
}
