package ma.enset.Containers;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.gui.GuiEvent;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.ControllerException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ma.enset.agents.Player2;


public class Player2Cont extends Application {

    static Player2 player2;
    public ObservableList<String> items = FXCollections.observableArrayList();

    public void setAgent(Player2 player2) {
        this.player2 = player2;
    }

    public Player2 getPlayer2() {
        return player2;
    }

    public static void main(String[] args) throws ControllerException {
        Player2Cont player2Container = new Player2Cont();
        player2Container.startContainer();

        launch(args);
    }

    public void startContainer() throws ControllerException {
        Runtime runtime = Runtime.instance();
        ProfileImpl profile = new ProfileImpl();
        profile.setParameter(ProfileImpl.MAIN_HOST, "localhost");
        AgentContainer agentContainer = runtime.createAgentContainer(profile);
        AgentController agentController = agentContainer.createNewAgent("Player2", "ma.enset.agents.Player2", new Object[]{this});
        agentController.start();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();

        HBox hBox = new HBox();
        hBox.setPadding(new javafx.geometry.Insets(10, 10, 10, 10));
        Label label = new Label("choose a number");
        label.setPadding(new javafx.geometry.Insets(0, 10, 0, 10));
        TextField textField = new TextField();
        Button button = new Button("game");

        hBox.getChildren().addAll(label, textField, button);
        root.setTop(hBox);

        ListView<String> listView = new ListView<>();
        listView.setItems(items);

        VBox vBox = new VBox();
        vBox.setPadding(new javafx.geometry.Insets(10, 10, 10, 10));
        vBox.getChildren().addAll(listView);
        root.setCenter(vBox);

        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Player2");
        primaryStage.show();

        button.setOnAction(event -> {
            String message = textField.getText();
            GuiEvent guiEvent = new GuiEvent(event, 0);
            guiEvent.addParameter(message);
            guiEvent.addParameter("Server");
            guiEvent.addParameter(listView);
            player2.onGuiEvent(guiEvent);
        });
    }
}
