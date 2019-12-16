package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        ObservableList<Product> list = FXCollections.observableArrayList(
                new Product(
                        "Pfeffer",
                        "1 Stück",
                        3.49,
                        2.79,
                        "pfeffer_600x600.jpg",
                        "Schwarzer Pfeffer verleiht Ihren Speisen eine pikante Schärfe, besonders wenn er länger mitgekocht wird."
                ),

                new Product(
                        "Schafmilchkäse",
                        "200 Gramm Packung",
                        2.59,
                        1.99,
                        "cheese_salakis_600x600.jpg",
                        "Hier gibt es keine Beschreibung, weil unsere Handelskette kennst sich nur bedingt damit aus, wie man eine Werbebeschreibung schreibt."
                ),

                new Product(
                        "Vöslauer",
                        "1.5 Liter Flasche",
                        0.75,
                        0.49,
                        "voslauer_600x600.jpg",
                        "Spritziges Vöslauer Mineralwasser."
                ),

                new Product(
                        "Zucker",
                        "500 Gramm Paket",
                        1.39,
                        0.89,
                        "zucker_600x600.jpg",
                        "Natürliches Gelieren wird durch Apfelpektin unterstützt, welches im richtigen Verhältnis mit Zitronensäure und Kristallzucker abgemischt wurde."
                )
        );

        ListView<Product> listItem = new ListView<>();
        listItem.getItems().addAll(list);

        //Labels
        Label productName = new Label("Product Name: ");
        Label quantity = new Label("Quantity: ");
        Label oldPrice = new Label("Old price: ");
        Label newPrice = new Label("New price: ");

        Label description = new Label("Description: ");
        description.setStyle("-fx-font-size: 20; -fx-font-weight: bold");

        //Textfields
        TextField textProductName = new TextField();
        TextField textQuantity = new TextField();
        TextField textOldPrice = new TextField();
        TextField textNewPrice = new TextField();

        //Text
        Text textDescription = new Text();
        textDescription.setWrappingWidth(300);

        //Buttons
        Button updatePrice = new Button("Update price");
        Button saveReport = new Button("create Report");
        Button exitButton = new Button("Exit");


        //Controls
        HBox hBoxProductName = new HBox(productName, textProductName);
        HBox hBoxQuantity = new HBox(quantity, textQuantity);
        HBox hBoxOldPrice = new HBox(oldPrice,textOldPrice);
        HBox hBoxNewPrice = new HBox(newPrice, textNewPrice);
        HBox hBoxButtons = new HBox(updatePrice, saveReport, exitButton);
        HBox hBoxDescription = new HBox(description);
        HBox hBoxTextDescription = new HBox(textDescription);

        ImageView selectedImage = new ImageView();
        selectedImage.setFitHeight(250);
        selectedImage.setFitWidth(250);

        //Productcontrol
        VBox vBoxProduct = new VBox(hBoxProductName, hBoxQuantity, hBoxOldPrice, hBoxNewPrice, hBoxButtons, selectedImage, hBoxDescription, hBoxTextDescription);
        HBox main = new HBox(vBoxProduct, listItem);

        vBoxProduct.setSpacing(5);
        description.setWrapText(true);

        //neuen Preis setzten
        listItem.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            int selectedIndex = listItem.getSelectionModel().getSelectedIndex();

            if(selectedIndex != -1){
                textProductName.setText(newValue.getProductName());
                textProductName.setDisable(true);

                textQuantity.setText(newValue.getQuantity());
                textQuantity.setDisable(true);

                textOldPrice.setText(String.valueOf(newValue.getOldPrice()));
                textNewPrice.setText(String.valueOf(newValue.getNewPrice())) ;

                //Images
                try{
                    InputStream input = this.getClass().getResourceAsStream("/images/" +newValue.getImage());
                    Image image = new Image(input);
                    ImageView imageView = new ImageView(image);
                    imageView.setFitHeight(200);
                    imageView.setFitWidth(200);

                } catch(Exception e){
                    System.out.println("Something went wrong!");
                }

                textDescription.setText(newValue.getDescription());
            }
        });

        //Action
        updatePrice.setOnAction(actionEvent ->  {
            System.out.println("Updated");
            int selectedIdx = listItem.getSelectionModel().getSelectedIndex();

            if(selectedIdx != -1) {
                //get values
                double old_Price = Double.valueOf(textOldPrice.getText());
                double new_Price = Double.valueOf(textNewPrice.getText());

                //set values
                listItem.getItems().get(selectedIdx).setOldPrice(old_Price);
                listItem.getItems().get(selectedIdx).setNewPrice(new_Price);
                listItem.refresh();
            }
        });

        //Report
        saveReport.setOnAction(ActionEvent -> {
            StringBuilder report = new StringBuilder();
            System.out.println("<-----REPORT----->");

            for(int i=0; i<listItem.getItems().size(); i++){
                String product_Name = listItem.getItems().get(i).getProductName();
                String product_quantity = listItem.getItems().get(i).getQuantity();
                Double old_price = listItem.getItems().get(i).getOldPrice();
                Double new_price = listItem.getItems().get(i).getNewPrice();
                String text_Description = listItem.getItems().get(i).getDescription();

                report.append("PRODUCT ").append(product_Name).append("\nQUANTITY ").append(product_quantity).append("\nOLD PRICE ").append(old_price).append("\nNEW PRICE ").append(new_price).append("\nDESCRIPTION\n").append(text_Description).append("\n\n");
            }
            System.out.println(report);


            try {
                PrintWriter printWriter = new PrintWriter("report.txt");
                printWriter.print(report);
                printWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        exitButton.setOnAction(ActionEvent -> {
            primaryStage.close();
        });

        Scene scene = new Scene(main, 800, 600);
        primaryStage.setTitle("CodeReview 04");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

