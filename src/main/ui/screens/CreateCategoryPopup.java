package ui.screens;

import exceptions.CategoryExistsException;
import exceptions.NullEntryException;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import ui.UserInterface;

public class CreateCategoryPopup extends Popup {
    private final UserInterface userInterface;
    private TextField categoryName;
    private Text mainLabel;
    private Stage stage;
    private Pane pane;


    public CreateCategoryPopup(UserInterface userInterface) {
        this.userInterface = userInterface;
    }

    public void renderCategoryPopup() {
        stage = createPopupStage(STANDARD_POPUP_WIDTH, STANDARD_POPUP_HEIGHT);
        initializeFinalPane();
        userInterface.setMiddle(stage);
        initializeScreen(pane, stage);
    }

    @Override
    protected void initializeFinalPane() {
        VBox vbox = new VBox();
        vbox.setSpacing(20.0);
        setMainLabel();
        setTextField();
        HBox buttonPane = makeFormButtons(stage, CREATE_CATEGORY, userInterface);
        vbox.getChildren().addAll(mainLabel, categoryName, buttonPane);
        vbox.setAlignment(Pos.CENTER);
        pane = vbox;
    }

    private void setMainLabel() {
        mainLabel = new Text("Enter a name for your your category:");
        mainLabel.setTextAlignment(TextAlignment.CENTER);
        mainLabel.setStyle("-fx-font-size:16px;");
    }

    private void setTextField() {
        categoryName = new TextField();
        categoryName.setAlignment(Pos.CENTER);
        categoryName.setMaxWidth(300);
        categoryName.setStyle("-fx-font-size:14px;");
    }

    public void createNewCategory() {
        try {
            userInterface.getSession().createNewCategory(categoryName.getText());
            addSuccessfulAlert();
            stage.close();
        } catch (NullEntryException e1) {
            nullEntryAlert();
        } catch (CategoryExistsException exception) {
            categoryAlreadyExistsAlert();
        }
    }

    private void categoryAlreadyExistsAlert() {
        Alert a = new Alert(Alert.AlertType.WARNING);
        a.setContentText("Sorry, that category already exists. Please try again.");
        a.show();
    }

    private void nullEntryAlert() {
        Alert a = new Alert(Alert.AlertType.WARNING);
        a.setContentText("You must enter a name for your category.");
        a.show();
    }

    private void addSuccessfulAlert() {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setContentText("You've successfully added the category.");
        a.show();
    }

}