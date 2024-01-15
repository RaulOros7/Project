import org.json.simple.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

public class ProductForm extends JFrame {

    private JTextField nameField, priceField;

    private JCheckBox inStockCheckBox;

    private JRadioButton typeRadio1, typeRadio2;

    private JComboBox<String> categoryComboBox;

    public ProductForm() {

        initializeUI();

    }

    private void initializeUI() {



        setTitle("Formular Produs");

        setSize(400, 300);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        JPanel panel = new JPanel(new GridLayout(6, 2));

        panel.add(new JLabel("Nume:"));

        nameField = new JTextField();

        panel.add(nameField);

        panel.add(new JLabel("Preț:"));

        priceField = new JTextField();

        panel.add(priceField);

        panel.add(new JLabel("Disponibil în stoc:"));

        inStockCheckBox = new JCheckBox();

        panel.add(inStockCheckBox);

        panel.add(new JLabel("Tip:"));

        typeRadio1 = new JRadioButton("Tip 1");

        typeRadio2 = new JRadioButton("Tip 2");

        ButtonGroup typeGroup = new ButtonGroup();

        typeGroup.add(typeRadio1);

        typeGroup.add(typeRadio2);

        panel.add(typeRadio1);

        panel.add(typeRadio2);

        panel.add(new JLabel("Categorie:"));

        String[] categories = {"Electronice", "Îmbrăcăminte", "Alimente"};

        categoryComboBox = new JComboBox<>(categories);

        panel.add(categoryComboBox);

        JButton saveButton = new JButton("Salvare");

        JButton cancelButton = new JButton("Anulare");

        saveButton.addActionListener(new ActionListener() {

            @Override

            public void actionPerformed(ActionEvent e) {

                saveDataToJson();

            }

        });

        cancelButton.addActionListener(new ActionListener() {

            @Override

            public void actionPerformed(ActionEvent e) {

                dispose(); // Închide fereastra

            }

        });

        panel.add(saveButton);

        panel.add(cancelButton);


        add(panel);



        setLocationRelativeTo(null);

        setVisible(true);

    }

    private void saveDataToJson() {



        JSONObject jsonObject = new JSONObject();

        jsonObject.put("Nume", nameField.getText());

        jsonObject.put("Preț", priceField.getText());

        jsonObject.put("În stoc", inStockCheckBox.isSelected());

        jsonObject.put("Tip", typeRadio1.isSelected() ? "Tip 1" : "Tip 2");

        jsonObject.put("Categorie", categoryComboBox.getSelectedItem());



        try (FileWriter file = new FileWriter("product_data.json", true)) {

            file.write(jsonObject.toJSONString() + "\n");

            file.flush();

            JOptionPane.showMessageDialog(this, "Datele au fost salvate cu succes!");

            clearForm();

        } catch (IOException e) {

            e.printStackTrace();

            JOptionPane.showMessageDialog(this, "Eroare la salvare!");

        }

    }

    private void clearForm() {



        nameField.setText("");

        priceField.setText("");

        inStockCheckBox.setSelected(false);

        typeRadio1.setSelected(true);

        categoryComboBox.setSelectedIndex(0);

    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {

            @Override

            public void run() {

                new ProductForm();

            }

        });

    }

}
