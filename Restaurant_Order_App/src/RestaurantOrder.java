import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.text.Font; // needed for JavaFX Font object
import javafx.scene.text.FontWeight;
import javafx.scene.Scene; // needed for JavaFX scene object
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent; // needed for JavaFX ActionEvent
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Label; //needed for Label
import javafx.scene.control.Button; // needed for JavaFX Button control
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.control.TextField; // needed for JavaFX TextField control

public class RestaurantOrder extends Application {
	
	@Override
	public void start(Stage primaryStage){
		
		//Toggle Groups
		ToggleGroup toggleGroupDrinks = new ToggleGroup();
		ToggleGroup toggleGroupFoodItems = new ToggleGroup();
		
		//flowPane layout 
		FlowPane flow = new FlowPane();
		flow.setPadding(new Insets(20, 20, 20, 20));
		flow.setVgap(10);
		flow.setHgap(20);
		
		Label drinkLabel = new Label("Drink to choose");
		Label foodLabel = new Label("   Food item to choose");
		
		foodLabel.setFont(Font.font("Arial", 20));
		drinkLabel.setFont(Font.font("Arial", 20));
	
		//add images
		ImageView iv1 = new ImageView("Drink1.jpg");
		iv1.setFitWidth(75);
		iv1.setFitHeight(95);
		
		ImageView iv2 = new ImageView("Drink2.jpg");
		iv2.setFitWidth(75);
		iv2.setFitHeight(95);
		
		ImageView iv3 = new ImageView("FoodItem1.jpg");
		iv3.setFitWidth(75);
		iv3.setFitHeight(95);
		
		ImageView iv4 = new ImageView("FoodItem2.jpg");
		iv4.setFitWidth(75);
		iv4.setFitHeight(95);
		
		//Toggle Buttons
		ToggleButton toggleButton1 = new ToggleButton("Soda");
		toggleButton1.setGraphic(iv1);
		ToggleButton toggleButton2 = new ToggleButton("Horchata");
		toggleButton2.setGraphic(iv2);
		ToggleButton toggleButton3 = new ToggleButton("Hamburger");
		toggleButton3.setGraphic(iv3);
		ToggleButton toggleButton4 = new ToggleButton("Sonoran Hotdog");
		toggleButton4.setGraphic(iv4);
		
		//create border
		Border border = new Border(new BorderStroke(Color.BLACK, 
				BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT));
		
		toggleButton1.setBorder(border);
		toggleButton2.setBorder(border);
		toggleButton3.setBorder(border);
		toggleButton4.setBorder(border);
				
		
		toggleButton1.setMinWidth(150);
		toggleButton2.setMinWidth(150);
		toggleButton3.setMinWidth(200);
		toggleButton4.setMinWidth(200);
 
		//add toggleButtons to appropriate toggleGroup
		toggleButton1.setToggleGroup(toggleGroupDrinks);
		toggleButton2.setToggleGroup(toggleGroupDrinks);
		toggleButton3.setToggleGroup(toggleGroupFoodItems);
		toggleButton4.setToggleGroup(toggleGroupFoodItems);
		
		//add nodes to flow
		flow.getChildren().add(drinkLabel);
		flow.getChildren().add(foodLabel);
		flow.getChildren().add(toggleButton1);
		flow.getChildren().add(toggleButton3);
		flow.getChildren().add(toggleButton2);
		flow.getChildren().add(toggleButton4);
		
		//order button 
		Button orderButton = new Button("Order");
		orderButton.setMinWidth(150);
		orderButton.setFont(Font.font("Arial", FontWeight.BOLD, 12));
		
		
		//Feedback TextField
		TextField feedback = new TextField("Please select your order");
		//code to make normal looking text field that is non-reactive to user
		feedback.setEditable(false);
		feedback.setMouseTransparent(true);
		feedback.setFocusTraversable(false);
		
		feedback.setFont(Font.font("Arial", 10));
		feedback.setMinHeight(60);
		feedback.setMinWidth(230);
		
		flow.getChildren().add(orderButton);
		flow.getChildren().add(feedback);

		//add listeners to toggle groups 
		addToggleGroupListener(toggleGroupFoodItems, feedback);
		addToggleGroupListener(toggleGroupDrinks, feedback);
		
		
		orderButton.setOnAction((ActionEvent e)->{
			ToggleButton selectedFoodButton = (ToggleButton)toggleGroupFoodItems.getSelectedToggle();
			ToggleButton selectedDrinkButton = (ToggleButton)toggleGroupDrinks.getSelectedToggle();
			if(selectedFoodButton != null && selectedDrinkButton != null) {
				feedback.setText("You order is a " + selectedFoodButton.getText() + " and " + 
						selectedDrinkButton.getText());
			}
			else {
				feedback.setText("Please select both a food item and drink");
			}
		});
		
		
		//create Scene
		Scene scene = new Scene(flow, 500, 350);
		primaryStage.setTitle("Food and Drink Ordering");
		primaryStage.setScene(scene);
		primaryStage.show();
        
        	
	}
	public static void main(String[] args) {
		
		launch(args);
	}
	//add listener to toggle group
	public void addToggleGroupListener(ToggleGroup toggleGroup, TextField feedback) {
		
		toggleGroup.selectedToggleProperty().addListener((changed, oldvalue, newValue) ->{
			//create toggleButton object to be able access getter for new value 
			ToggleButton tb = (ToggleButton)newValue;
			//checks to see if you unselected a button
			if(newValue != null){
				feedback.setText("You selected " + tb.getText());
			}
			else{
				feedback.setText("Please select your order");
			}

		});
		
	}

}
