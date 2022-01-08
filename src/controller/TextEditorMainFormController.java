package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

import java.io.*;

public class TextEditorMainFormController {
    public MenuItem btnSave;
    public JFXButton btnSave2;
    public JFXTextArea txtArea;
    public ToggleButton btnBold;
    public ToggleButton btnItalic;
    public ToggleButton btnUndeline;
    public JFXButton btnNew;
    public JFXButton btnCut;
    public JFXButton btnCopy;
    public MenuItem menuSave;
    public JFXButton btnOpen;
    public JFXButton btnUp;
    public JFXTextField txtSearch;
    public JFXButton btnDown;
    public JFXButton btnFind;
    public Label lblFindCount;
    public Label lblWordCount;
    public JFXButton btnSaveMini;
    private final File file = null;
    private final String ls = System.getProperty("line.separator");

    public void initialize(){
txtArea.clear();
    }

    public void btnSave2_OnAction(ActionEvent event) {
    }

    public void btnBold_OnAction(ActionEvent event) {
    }

    public void btnItalic_OnAction(ActionEvent event) {
    }

    public void btnUndeline_OnAction(ActionEvent event) {
    }

    public void btnNew_OnAction(ActionEvent event) {
    }

    public void btnCut_OnAction(ActionEvent event) {
    }

    public void btnCopy_OnAction(ActionEvent event) {
    }

    public void btnSave_OnAction(ActionEvent event) {
        if (file != null) {
            if (file.exists()) writeToFile(file);
        } else {
            FileChooser chooser = new FileChooser();
            chooser.setTitle("Save file");
            chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text File", "*.txt")
                    , new FileChooser.ExtensionFilter("All Files", "*.*"));
            File file = chooser.showSaveDialog(null);
            if (file != null) if (file.exists()) {
                writeToFile(file);
            } else {
                try {
                    file.createNewFile();
                } catch (IOException ex) {
                    return;
                }
                writeToFile(file);
            }
        }
    }
    private void writeToFile(File file) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, false))) {
            bw.write(txtArea.getText());
        } catch (IOException ex) {
            new Alert(Alert.AlertType.ERROR, ex.toString(), ButtonType.OK).show();
        }
    }
    public void btnOpen_OnAction(ActionEvent event) {
        FileChooser chooser=new FileChooser();
        chooser.setTitle("open a text file");
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text file","*.txt"), new FileChooser.ExtensionFilter("All files","*.*"));
        File file= chooser.showOpenDialog(null);
        if (file!=null)
        {
            readFromFile(file);
        }
    }

    private void readFromFile(File file) {
        if (file != null) {
            String text = "", buff = "";
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                text = br.readLine();
                while ((buff = br.readLine()) != null) {
                    text += ls + buff;
                }
            } catch (IOException ex) {
                new Alert(Alert.AlertType.ERROR, ex.toString(), ButtonType.OK).show();
            }
            txtArea.setText(text);
        }
    }

    public void btnUp_OnAction(ActionEvent event) {
    }

    public void btnDown_OnAction(ActionEvent event) {
    }

    public void txtSearcth_OnAction(ActionEvent event) {
    }

    public void btnFind_OnAction(ActionEvent event) {
    }
}
