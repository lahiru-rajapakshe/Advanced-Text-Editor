package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    public JFXButton btnPaste;
    public ToggleButton btnCaseSensitive;
    private String cut;
    private String Copy;
    private boolean textChanged = false;
    private Matcher matcher;
    private int lastSearchIndex = 0;
    private final String ls = System.getProperty("line.separator");

    public void initialize(){
txtArea.clear();
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            textChanged = true;
        });
    }

    public void btnSave2_OnAction(ActionEvent event) {
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

    public void btnBold_OnAction(ActionEvent event) {
    }

    public void btnItalic_OnAction(ActionEvent event) {
    }

    public void btnUndeline_OnAction(ActionEvent event) {
    }

    public void btnNew_OnAction(ActionEvent event) {
        txtArea.clear();
    }

    public void btnCut_OnAction(ActionEvent event) {
        byte[] bytes = txtArea.getSelectedText().getBytes(StandardCharsets.UTF_8);
        cut = new String(bytes);
        int caretPosition = txtArea.getCaretPosition();
        txtArea.setText(txtArea.getText().replace(txtArea.getSelectedText(), ""));
        txtArea.positionCaret(caretPosition);
    }

    public void btnCopy_OnAction(ActionEvent event) {
        byte[] bytes = txtArea.getSelectedText().getBytes(StandardCharsets.UTF_8);
        Copy = new String(bytes);
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
        txtSearch.deselect();
        if (textChanged) {
            matcher = Pattern.compile(txtSearch.getText(), !btnCaseSensitive.isSelected() ? Pattern.CASE_INSENSITIVE : 0).matcher(txtArea.getText());
            textChanged = false;
        }
        if (matcher.find()) {   //stroing last index
            int start = matcher.start();   //staritng index of the word
            int end = matcher.end();
            lastSearchIndex = end;
            System.out.println(end);                    //to find
            txtArea.selectRange(end, start);

        }
       
    }

    public void btnDown_OnAction(ActionEvent event) {
        txtSearch.deselect();
        if (textChanged) {
            matcher = Pattern.compile(txtSearch.getText(), !btnCaseSensitive.isSelected() ? Pattern.CASE_INSENSITIVE : 0).matcher(txtArea.getText());
            textChanged = false;
        }
        if (matcher.find()) {   //stroing last index
            int start = matcher.start();   //staritng index of the word
            int end = matcher.end();
            lastSearchIndex = end;
            System.out.println(start);                    //to find
            txtArea.selectRange(start, end);
        }

    }

    public void txtSearcth_OnAction(ActionEvent event) {
    }

    public void btnFind_OnAction(ActionEvent event) {
        txtSearch.deselect();
        if (textChanged) {
            matcher = Pattern.compile(txtSearch.getText(), !btnCaseSensitive.isSelected() ? Pattern.CASE_INSENSITIVE : 0).matcher(txtArea.getText());
            textChanged = false;
        }
        if (matcher.find()) {   //stroing last index
            int start = matcher.start();   //staritng index of the word
            int end = matcher.end();
            lastSearchIndex = end;
            System.out.println(start);                    //to find
            txtArea.selectRange(start, end);
        }


    }

    public void btnPaste_OnAction(ActionEvent event) {
        int caretPosition = txtArea.getCaretPosition();
        txtArea.insertText(caretPosition, Copy);
        txtArea.insertText(caretPosition, cut);
    }

    public void menuCut_OnAction(ActionEvent event) {
        byte[] bytes = txtArea.getSelectedText().getBytes(StandardCharsets.UTF_8);
        cut = new String(bytes);
        int caretPosition = txtArea.getCaretPosition();
        txtArea.setText(txtArea.getText().replace(txtArea.getSelectedText(), ""));
        txtArea.positionCaret(caretPosition);
    }

    public void menuCopy_OnAction(ActionEvent event) {
        byte[] bytes = txtArea.getSelectedText().getBytes(StandardCharsets.UTF_8);
        Copy = new String(bytes);
    }

    public void menuNew_OnAction(ActionEvent event) {
        txtArea.clear();
    }

    public void menuExit_OnAction(ActionEvent event) {
        System.exit(0);
    }

    public void menuSave_OnAction(ActionEvent event) {
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

    public void menuOpen_OnAction(ActionEvent event) {
        FileChooser chooser=new FileChooser();
        chooser.setTitle("open a text file");
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text file","*.txt"), new FileChooser.ExtensionFilter("All files","*.*"));
        File file= chooser.showOpenDialog(null);
        if (file!=null)
        {
            readFromFile(file);
        }
    }

    public void menuSelectAll_OnAction(ActionEvent event) {
        txtArea.selectAll();
    }

    public void about_OnAction(ActionEvent event) throws IOException {
        URL resource = getClass().getResource("/view/Aboutus.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.setScene(scene);

        stage.show();
    }

    public void btnCaseSensitive_OnAction(ActionEvent event) {
        textChanged = true;
        btnFind.fire();
    }
}
