package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;
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
    public TextField txtWordC;
    private String cut;
    private String Copy;
    private boolean textChanged = false;
    private Matcher matcher;
    private int lastSearchIndex = 0;
    private final String ls = System.getProperty("line.separator");

    private final static int BOLD = 1;
    private final static int ITALIC = 2;
    private final static int UNDERLINE = 4;
    public void initialize(){
txtArea.clear();
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            textChanged = true;
        });
        String myStr = txtArea.getText();
        String[] words = myStr.split("\\s+");
        System.out.println("Word Count is: "+words.length);
        lblWordCount.setText(String.valueOf(words.length));





    }

    public void btnSave2_OnAction(ActionEvent event) {
        if(txtArea.getText().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Empty values cannot save ! please enter something",ButtonType.OK).show();
        }else{
            try{
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
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public void btnBold_OnAction(ActionEvent event) {
applyFormatting();
    }

    public void btnItalic_OnAction(ActionEvent event) {
        applyFormatting();
    }

    public void btnUndeline_OnAction(ActionEvent event) {
        applyFormatting();
    }

    public void btnNew_OnAction(ActionEvent event) {
        txtArea.clear();
    }

    public void btnCut_OnAction(ActionEvent event) {
        if(txtArea.getText().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Thee is no any text here to cut, Enter something",ButtonType.OK).show();
        }else{
            try{
                byte[] bytes = txtArea.getSelectedText().getBytes(StandardCharsets.UTF_8);
                cut = new String(bytes);
                int caretPosition = txtArea.getCaretPosition();
                txtArea.setText(txtArea.getText().replace(txtArea.getSelectedText(), ""));
                txtArea.positionCaret(caretPosition);
            }catch(Exception e){
                e.printStackTrace();
            }
        }

    }

    public void btnCopy_OnAction(ActionEvent event) {

        if(txtArea.getText().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Thee is no any text here to cut, Enter something",ButtonType.OK).show();
        }else{
            try{
                byte[] bytes = txtArea.getSelectedText().getBytes(StandardCharsets.UTF_8);
                Copy = new String(bytes);
            }catch(Exception e){
                e.printStackTrace();
            }

        }

    }

    public void btnSave_OnAction(ActionEvent event) {
     if(txtArea.getText().isEmpty()){
         new Alert(Alert.AlertType.ERROR,"Empty values cannot save ! please enter something",ButtonType.OK).show();
     }else{
         try{
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
         }catch(Exception e){
             e.printStackTrace();
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
       try{
           FileChooser chooser=new FileChooser();
           chooser.setTitle("open a text file");
           chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text file","*.txt"), new FileChooser.ExtensionFilter("All files","*.*"));
           File file= chooser.showOpenDialog(null);
           if (file!=null)
           {
               readFromFile(file);
           }
       }catch (Exception e){
           e.printStackTrace();
       }
    }

    private void readFromFile(File file) {
       try
       {
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
       }catch (Exception e){
           e.printStackTrace();
       }
    }

    public void btnUp_OnAction(ActionEvent event) {
        if(txtArea.getText().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"There is no any text here, Enter something",ButtonType.OK).show();
        }else{
            try{
                txtSearch.deselect();
                if (textChanged) {
                    matcher = Pattern.compile(txtSearch.getText(), !btnCaseSensitive.isSelected() ? Pattern.CASE_INSENSITIVE : 0).matcher(txtArea.getText());
                    textChanged = false;
                }
                if (matcher.find()) {
                    int start = matcher.start();
                    int end = matcher.end();
                    lastSearchIndex = end;
                    System.out.println(end);
                    txtArea.selectRange(end, start);

                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }


    }

    public void btnDown_OnAction(ActionEvent event) {

        if(txtArea.getText().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"There is no any text here, Enter something",ButtonType.OK).show();
        }else{
            txtSearch.deselect();
            if (textChanged) {
                matcher = Pattern.compile(txtSearch.getText(), !btnCaseSensitive.isSelected() ? Pattern.CASE_INSENSITIVE : 0).matcher(txtArea.getText());
                textChanged = false;
            }
            if (matcher.find()) {
                int start = matcher.start();
                int end = matcher.end();
                lastSearchIndex = end;
                System.out.println(start);
                txtArea.selectRange(start, end);
            }

        }

    }

    public void txtSearcth_OnAction(ActionEvent event) {

    }

    public void btnFind_OnAction(ActionEvent event) {
        if(txtArea.getText().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"There is no any text here, Enter something",ButtonType.OK).show();
        }else{
            try{
                txtSearch.deselect();
                if (textChanged) {
                    matcher = Pattern.compile(txtSearch.getText(), !btnCaseSensitive.isSelected() ? Pattern.CASE_INSENSITIVE : 0).matcher(txtArea.getText());
                    textChanged = false;
                }
                if (matcher.find()) {
                    int start = matcher.start();
                    int end = matcher.end();
                    lastSearchIndex = end;
                    System.out.println(start);
                    txtArea.selectRange(start, end);


                    String string = txtArea.getText();
                    int count;


                    string = string.toLowerCase();


                    String words[] = string.split(" ");

                    System.out.println("Duplicate words in a given string : ");
                    for(int i = 0; i < words.length; i++) {
                        count = 1;
                        for(int j = i+1; j < words.length; j++) {
                            if(words[i].equals(words[j])) {
                                count++;

                                words[j] = "0";
                            }
                        }


                        if(count > 1 && words[i] != "0") {
                            System.out.println(words[i] + "" + count);
                            lblFindCount.setText(String.valueOf(count));
                        }
                    }
                }

            }catch(Exception e){
                e.printStackTrace();
            }




        }


    }

    public void btnPaste_OnAction(ActionEvent event) {

       try{
           int caretPosition = txtArea.getCaretPosition();
           txtArea.insertText(caretPosition, Copy);
           txtArea.insertText(caretPosition, cut);
       }catch(Exception e){
           e.printStackTrace();
       }
    }

    public void menuCut_OnAction(ActionEvent event) {
        if(txtArea.getText().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"There is no any text here, Enter something",ButtonType.OK).show();
        }else{
            try{
                byte[] bytes = txtArea.getSelectedText().getBytes(StandardCharsets.UTF_8);
                cut = new String(bytes);
                int caretPosition = txtArea.getCaretPosition();
                txtArea.setText(txtArea.getText().replace(txtArea.getSelectedText(), ""));
                txtArea.positionCaret(caretPosition);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }

    public void menuCopy_OnAction(ActionEvent event) {
        if(txtArea.getText().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"There is no any text here, Enter something",ButtonType.OK).show();
        }else{

            try{
                byte[] bytes = txtArea.getSelectedText().getBytes(StandardCharsets.UTF_8);
                Copy = new String(bytes);
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }

    }

    public void menuNew_OnAction(ActionEvent event) {
        txtArea.clear();
    }

    public void menuExit_OnAction(ActionEvent event) {
        System.exit(0);
    }

    public void menuSave_OnAction(ActionEvent event) {
        if(txtArea.getText().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"There is no any text here, Enter something",ButtonType.OK).show();
        }else
        {
            try{
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
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }

    public void menuOpen_OnAction(ActionEvent event) {
        if(txtArea.getText().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"There is no any text here, Enter something",ButtonType.OK).show();
        }else
        {
            try{
                FileChooser chooser=new FileChooser();
                chooser.setTitle("open a text file");
                chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text file","*.txt"), new FileChooser.ExtensionFilter("All files","*.*"));
                File file= chooser.showOpenDialog(null);
                if (file!=null)
                {
                    readFromFile(file);
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }

    }

    public void menuSelectAll_OnAction(ActionEvent event) {
        txtArea.selectAll();
    }

    public void about_OnAction(ActionEvent event) throws IOException {
        try{
            URL resource = getClass().getResource("/view/Aboutus.fxml");
            Parent load = FXMLLoader.load(resource);
            Scene scene = new Scene(load);
            Stage stage = new Stage();
            stage.setScene(scene);
stage.setTitle("About us");
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void btnCaseSensitive_OnAction(ActionEvent event) {
        try{
            textChanged = true;
            btnFind.fire();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void findCount() {


    }

    public void btnReplace_OnAction(ActionEvent event) {
        if(txtArea.getText().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"There is no any text here, Enter something",ButtonType.OK).show();
        }else

        {
            try{
                txtSearch.deselect();
                if (textChanged) {
                    matcher = Pattern.compile(txtSearch.getText(), !btnCaseSensitive.isSelected() ? Pattern.CASE_INSENSITIVE : 0).matcher(txtArea.getText());
                    textChanged = false;
                }
                if (matcher.find()) {
                    int start = matcher.start();
                    int end = matcher.end();
                    lastSearchIndex = end;
                    System.out.println(start);
                    txtArea.selectRange(start, end);}


                String text1 = txtArea.getText();
                String text2 = text1.toString().replace("-Click 2U - Files", "");

            }catch (Exception e){
                e.printStackTrace();
            }
        }
        }
    private void applyFormatting() {

        int flags = 0;
        if (btnBold.isSelected()) flags = flags | BOLD;
        if (btnItalic.isSelected()) flags = flags | ITALIC;
        if (btnUndeline.isSelected()) flags = flags | UNDERLINE;
        applyLogic3(flags);
    }
    private void applyLogic3(int formattingOptions) {
        txtArea.getStyleClass().clear();
        if ((formattingOptions & BOLD) == BOLD) txtArea.getStyleClass().add("bold");
        if ((formattingOptions & ITALIC) == ITALIC) txtArea.getStyleClass().add("italic");
        if ((formattingOptions & UNDERLINE) == UNDERLINE) txtArea.getStyleClass().add("underline");
    }

}
