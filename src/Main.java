// Created By: Maria Azaev (315082529) & Sivan Cohen (209111590)

import javax.swing.SwingUtilities;
import il.ac.shenkar.model.Model;
import il.ac.shenkar.model.IModel;
import il.ac.shenkar.viewmodel.ViewModel;
import il.ac.shenkar.view.View;

/**
 * The Main class is the entry point for the application.
 */
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    // Create the Model, ViewModel, and View
                    IModel model = new Model();
                    ViewModel viewModel = new ViewModel(model);
                    View swingView = new View(viewModel);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.err.println("Error initializing the application: " + e.getMessage());
                }
            }
        });
    }
}
