package com.mutuelle.application.views;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InaccessibleObjectException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mutuelle.application.Impl.ClientImpl;
import com.mutuelle.application.dao.ClientDAO;
import com.mutuelle.application.dao.OfficerDAO;
import com.mutuelle.application.models.Client;
import com.mutuelle.application.models.CodePays;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class RegisterController implements Initializable {
	@FXML
	private Button registerClick;
	@FXML
	private TextField numeroBadge;
	@FXML
	private Label errorNumeroBadge;
	@FXML
	private TextField nomEntreprise;
	@FXML
	private Label errorNomEntreprise;
	@FXML
	private TextField prenomClient;
	@FXML
	private Label errorPrenomClient;
	@FXML
	private TextField nomClient;
	@FXML
	private Label errorNomClient;
	@FXML
	private TextField telephone;
	@FXML
	private Label errorTelephone;
	@FXML
	private TextField cin;
	@FXML
	private Label errorCIN;
	@FXML
	private TextArea adresse;
	@FXML
	private TextField email;
	@FXML
	private Label errorEmail;
	@FXML
	private DatePicker dateDebutTravail;
	@FXML
	private Label verifChamps;
	@FXML
	private ComboBox<CodePays> choisePhone ;
	
	@FXML
	private ComboBox<String> nameCompany ;
	@FXML
	private TextField filtre;

	@FXML
	private TableColumn<Client, String> badge;
	@FXML
	private TableColumn<Client, String> entrepriseName;
	@FXML
	private TableColumn<Client, String> prenom;
	@FXML
	private TableColumn<Client, String> nom;
	@FXML
	private TableColumn<Client, String> tele;
	@FXML
	private TableColumn<Client, String> identite;
	@FXML
	private TableColumn<Client, String> adresseClient;
	@FXML
	private TableColumn<Client, String> emailClient;
	@FXML
	private TableColumn<Client, String> dateTravail;
	@FXML
	private TableView<Client> tableClientList;
	boolean filledCin;

	
	
	@FXML
	private NumberAxis naVisits;
	@FXML
	private CategoryAxis caDevices;
	@FXML
	private BarChart<String, Number> barChart;
	private Parent root;
	private Stage stage;
	private Scene scene;

	List<Client> clientList = new ArrayList<>();
	@FXML
	private Label choixIdentite;
	@FXML
	public void cinIdentite() {
		cin.setPromptText("CIN de Client");
		cin.setVisible(true);
		choixIdentite.setText("CIN :");
		choixIdentite.setVisible(true);
		filledCin = true;
	}
	@FXML
	public void passIdentite() {
		cin.setPromptText("Passport de Client");
		cin.setVisible(true);
		choixIdentite.setText("PASSPORT :");
		choixIdentite.setVisible(true);
	}

	@FXML
	public void logout(ActionEvent e) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("welcomePage.fxml"));
		stage = (Stage)((Node)e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	@FXML
	public void registerButton(ActionEvent event) throws SQLException {
		errorCIN.setText("");
		errorNomClient.setText("");
		errorNomEntreprise.setText("");
		errorNumeroBadge.setText("");
		errorPrenomClient.setText("");
		errorTelephone.setText("");
		errorEmail.setText("");
		verifChamps.setText("");

		int compErr=0;
		if(numeroBadge.getText().isBlank() == true || nomEntreprise.getText().isBlank() == true || prenomClient.getText().isBlank() == true || nomClient.getText().isBlank() == true || telephone.getText().isBlank() == true || cin.getText().isBlank() == true || adresse.getText().isBlank() == true || email.getText().isBlank() == true || dateDebutTravail.getValue().toString().isBlank() == true) {
			verifChamps.setText("Svp remplire tous les champs");
			compErr++;
		}
		if(numeroBadge.getText().length() <= 10 ) {
			System.out.println("done");

		}

		else {
			compErr++;
			errorNumeroBadge.setText("max 10");
		}
		if(nomEntreprise.getText().length() <= 10) {
			System.out.println("done");

		}
		else {
			compErr++;
			errorNomEntreprise.setText("max 10");
		}
		if(prenomClient.getText().length() <= 10) {
			System.out.println("done");

		}
		else {
			compErr++;
			errorPrenomClient.setText("max 10");
		}
		if(nomClient.getText().length() <= 10) {
			System.out.println("done");

		}
		else {
			compErr++;
			errorNomClient.setText("max 10");
		}


		if(!telephone.getText().matches("\\d{9}")) {
			errorTelephone.setText("the phone number is invalid\n");
			compErr++;
		}
		if(filledCin && cin.getText().length() > 8) {
			compErr++;
			errorCIN.setText("the cin number can not have more then 8 N");

		}

		if(filledCin && !cin.getText().matches("[a-zA-Z]{2}\\d{6}")) {
			compErr++;
			errorCIN.setText("the cin must be 2 numbers and 6 L");
		}
		if(!filledCin && !cin.getText().matches("[a-zA-Z]{2}\\d{7}")) {
			compErr++;
			errorCIN.setText("the Passport must be 2 numbers and 7 L");
		}
		if(!email.getText().matches("^(.+)@(.+)$")) {
			compErr++;
			errorEmail.setText("format invalide");
		}

		if(compErr==0)
			dataClient();
	}

	public void dataClient() throws SQLException {
		ClientImpl clientImpl = new ClientImpl();
		ClientDAO clientDAO = new ClientDAO();
		clientDAO.addClient(clientImpl.addClient(prenomClient.getText(),nomClient.getText(),email.getText(), (choisePhone.getValue() + telephone.getText()  ),adresse.getText(),cin.getText(),numeroBadge.getText(),nomEntreprise.getText(),dateDebutTravail.getValue().toString()));
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Success");
		alert.setHeaderText("Results:");
		alert.setContentText("Client Sera ajouter!");
		alert.showAndWait();
		emptyChamp();
	}
	public void emptyChamp() {
		prenomClient.setText("");
		nomClient.setText("");
		nomClient.setText("");
		email.setText("");
		telephone.setText("");
		adresse.setText("");
		cin.setText("");
		numeroBadge.setText("");
		nomEntreprise.setText("");
		dateDebutTravail.getEditor().clear();
		errorCIN.setText("");
		errorNomClient.setText("");
		errorNomEntreprise.setText("");
		errorNumeroBadge.setText("");
		errorPrenomClient.setText("");
		errorTelephone.setText("");
		errorEmail.setText("");
		verifChamps.setText("");
		buildData();
		statistique();
	}

	
	public void loadpayscodes() {
        ArrayList<CodePays> codepays = new ArrayList<CodePays>();
       ObjectMapper objectMapper = new ObjectMapper();
         try {
               InputStream inputStream = new FileInputStream(new File("C:/Users/adm/Desktop/MutuelleCentralisee/src/com/mutuelle/application/views/data2.json"));
               TypeReference<List<CodePays>> typeReference = new TypeReference<List<CodePays>>() {};
               codepays = (ArrayList<CodePays>) objectMapper.readValue(inputStream, typeReference);              
           }catch(FileNotFoundException e) {
               e.printStackTrace();
           } catch (StreamReadException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
           } catch (DatabindException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
           } catch (IOException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
           }
         catch (InaccessibleObjectException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
           }
         
         choisePhone.getItems().addAll(codepays);
   }
	//remplisage de table et combobox de nom company
	public void buildData() {
		ClientDAO clientDAO = new ClientDAO();
		badge.setCellValueFactory(new PropertyValueFactory<Client, String>("numeroBadge"));
		entrepriseName.setCellValueFactory(new PropertyValueFactory<Client, String>("nomEntreprise"));
		prenom.setCellValueFactory(new PropertyValueFactory<Client, String>("firstname"));
		nom.setCellValueFactory(new PropertyValueFactory<Client, String>("lastname"));
		tele.setCellValueFactory(new PropertyValueFactory<Client, String>("phone"));
		identite.setCellValueFactory(new PropertyValueFactory<Client, String>("cin"));
		adresseClient.setCellValueFactory(new PropertyValueFactory<Client, String>("address"));
		emailClient.setCellValueFactory(new PropertyValueFactory<Client, String>("email"));
		dateTravail.setCellValueFactory(new PropertyValueFactory<Client, String>("dateDebut"));
		tableClientList.setItems(clientDAO.buildData());
		nameCompany.getItems().add("All");
		nameCompany.getItems().addAll(clientDAO.getNameCompany());	
		
	}
	public void statistique() {
		ClientDAO clientDAO = new ClientDAO();
		XYChart.Series<String, Number> series = new XYChart.Series<>();
		series.setName("Date de l'inscription");	
		var data = new XYChart.Series<String, Number>();
        for(Map<String,Integer> elemt:clientDAO.statistique()) {
             //System.out.println(elemt.keySet()+""+elemt.values().toArray()[0]);
             int r=(int) elemt.values().toArray()[0];
             series.getData().add(new XYChart.Data<>(elemt.keySet().toString(),r));
        }
        barChart.getData().add(series);
        barChart.setLegendVisible(false);
		
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		buildData();
		loadpayscodes();
		statistique();
	}
	
	
	//Filtre With Name of company
	@FXML
	public void filtreWithCompany() {
		ClientDAO clientDAO = new ClientDAO();
		badge.setCellValueFactory(new PropertyValueFactory<Client, String>("numeroBadge"));
		entrepriseName.setCellValueFactory(new PropertyValueFactory<Client, String>("nomEntreprise"));
		prenom.setCellValueFactory(new PropertyValueFactory<Client, String>("firstname"));
		nom.setCellValueFactory(new PropertyValueFactory<Client, String>("lastname"));
		tele.setCellValueFactory(new PropertyValueFactory<Client, String>("phone"));
		identite.setCellValueFactory(new PropertyValueFactory<Client, String>("cin"));
		adresseClient.setCellValueFactory(new PropertyValueFactory<Client, String>("address"));
		emailClient.setCellValueFactory(new PropertyValueFactory<Client, String>("email"));
		dateTravail.setCellValueFactory(new PropertyValueFactory<Client, String>("dateDebut"));
		tableClientList.setItems(clientDAO.filtreWithCompany(nameCompany.getValue()));
	}
	
	@FXML
	public void filtreWithString() {
		ClientDAO clientDAO = new ClientDAO();
		badge.setCellValueFactory(new PropertyValueFactory<Client, String>("numeroBadge"));
		entrepriseName.setCellValueFactory(new PropertyValueFactory<Client, String>("nomEntreprise"));
		prenom.setCellValueFactory(new PropertyValueFactory<Client, String>("firstname"));
		nom.setCellValueFactory(new PropertyValueFactory<Client, String>("lastname"));
		tele.setCellValueFactory(new PropertyValueFactory<Client, String>("phone"));
		identite.setCellValueFactory(new PropertyValueFactory<Client, String>("cin"));
		adresseClient.setCellValueFactory(new PropertyValueFactory<Client, String>("address"));
		emailClient.setCellValueFactory(new PropertyValueFactory<Client, String>("email"));
		dateTravail.setCellValueFactory(new PropertyValueFactory<Client, String>("dateDebut"));
		tableClientList.setItems(clientDAO.filtre(filtre.getText().trim()));
	}

	
}
