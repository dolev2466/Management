package controllers;

import java.util.ArrayList;
import java.io.InputStream;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import boundaries.CenterRow;
import boundaries.ListsRow;
import boundaries.TraineesRow;
import boundaries.TrainersRow;
import controllers.Enums.DataBaseAction;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.StringConverter;
import javafx.util.converter.LocalDateStringConverter;
import jdk.nashorn.internal.ir.BreakableNode;

public class MainController extends BaseController 
{
	private String title;
	
	private String[] window = { "מאמנים", "מרכזים","רשימות","ספורטאים" };
//------------------------------------------------------------------trainer anchor pane variables-----------------------------------------------//	
	private @FXML AnchorPane anchorpane_trainers;
	
	private @FXML TableView<TrainersRow> trainier_table;
	
	private @FXML TableColumn<TrainersRow,String> tablecolumn_name;
	
	private @FXML TableColumn<TrainersRow,String> tablecolumn_last_name;
	
	private @FXML TableColumn<TrainersRow,String> tablecolumn_phone;
	
	private @FXML Button button_add_trainer;
	
	private @FXML Button button_back_trainer;
	
	private @FXML Button button_delete_trainer;
	
	private @FXML Label label_id;
	
	private @FXML Label label_name;
	
	private @FXML Label label_lastname;
	
	private @FXML Label label_birthday;
	
	private @FXML Label label_city;
	
	private @FXML Label label_address;
	
	private @FXML Label label_postcode;
	
	private @FXML Label label_phone;
	
	private @FXML Label label_cellphone;
	
	private @FXML Label label_email;
	
	private @FXML TextField textfield_id;
	
	private @FXML TextField textfield_name;
	
	private @FXML TextField textfield_lastname;
	
	private @FXML TextField textfield_birthday;
	
	private @FXML TextField textfield_city;
	
	private @FXML TextField textfield_address;
	
	private @FXML TextField textfield_postcode;
	
	private @FXML TextField textfield_phone;
	
	private @FXML TextField textfield_cellphone;
	
	private @FXML TextField textfield_email;
	
	private @FXML ImageView image_id;
	
	private @FXML ImageView image_name;
	
	private @FXML ImageView image_lastname;
	
	private @FXML ImageView image_birthday;
	
	private @FXML ImageView image_city;
	
	private @FXML ImageView image_address;
	
	private @FXML ImageView image_postcode;
	
	private @FXML ImageView image_phone;
	
	private @FXML ImageView image_cellphone;
	
	private @FXML ImageView image_email;
	
	private ObservableList<TrainersRow> trainer_row = FXCollections.observableArrayList();
	
//-------------------------------------------------------------end->trainer anchor pane variables-------------------------------------------//

//-------------------------------------------------------------center anchor pane variables-------------------------------------------//

	private ObservableList<CenterRow> center_row = FXCollections.observableArrayList();
	
	private @FXML AnchorPane anchorpane_centers;
	
	private @FXML TableView<CenterRow> center_table;
	
	private @FXML TableColumn<CenterRow,String> tablecolumn_classname;
	
	private @FXML TableColumn<CenterRow,String> tablecolumn_price;
	
	private @FXML TableColumn<CenterRow,String> tablecolumn_trainer;
	
	private @FXML Button button_add_center;
	
	private @FXML Button button_back_center;
	
	private @FXML Button button_delete_center;
	
	private @FXML TextField textfield_center_classname;
	
	private @FXML TextField textfield_center_price;
	
	private @FXML Label label_center_classname;
	
	private @FXML Label label_center_price;
	
	private @FXML Label label_center_trainer;
	
	private @FXML ComboBox<String> combobox_center_trainers;
	
	private ObservableList<String> center_trainer_list = FXCollections.observableArrayList();
	
	private int center_pr_key;
	
//-------------------------------------------------------------end->center anchor pane variables-------------------------------------------//


//-------------------------------------------------------------lists anchor pane variables-------------------------------------------//	
	private @FXML AnchorPane anchorpane_lists;

//-------------------------------------------------------------end->lists anchor pane variables-------------------------------------------//

//-------------------------------------------------------------trainee anchor pane variables-------------------------------------------//	
		private @FXML AnchorPane anchorpane_trainee;
		
		private static final DateTimeFormatter s_dateForamt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				
		private @FXML TableView<TraineesRow> trainee_table;
		
		private @FXML TableColumn<TraineesRow,String> tablecolumn_trainee_id;
		
		private @FXML TableColumn<TraineesRow,String> tablecolumn_trainee_name;
		
		private @FXML TableColumn<TraineesRow,String> tablecolumn_trainee_last_name;
		
		private @FXML TableColumn<TraineesRow,String> tablecolumn_trainee_phone;
		
		private @FXML TableColumn<TraineesRow,String> tablecolumn_trainee_gender;
		
		private @FXML TableColumn<TraineesRow,String> tablecolumn_trainee_center;
		
		private @FXML TableColumn<TraineesRow,String> tablecolumn_trainee_group;
		
		private @FXML TableColumn<TraineesRow,String> tablecolumn_trainee_register_date;
		
		private @FXML TableColumn<TraineesRow,String> tablecolumn_trainee_subscription_end_date;
		
		private ObservableList<TraineesRow> trainee_row = FXCollections.observableArrayList();
		
		private ObservableList<String> trainee_level = FXCollections.observableArrayList();
		
		private ObservableList<String> trainee_group_list = FXCollections.observableArrayList();
		
		private @FXML Button button_add_trainee;
		
		private @FXML Button button_back_trainee;
		
		private @FXML Button button_delete_trainee;
		
		private @FXML Label label_trainee_id;
		
		private @FXML Label label_trainee_name;
		
		private @FXML Label label_trainee_lastname;
		
		private @FXML Label label_trainee_birthday;
		
		private @FXML Label label_trainee_city;
		
		private @FXML Label label_trainee_address;
		
		private @FXML Label label_trainee_postcode;
		
		private @FXML Label label_trainee_phone;
		
		private @FXML Label label_trainee_cellphone;
		
		private @FXML Label label_trainee_email;
		
		private @FXML Label label_trainee_gender;
		
		private @FXML Label label_trainee_center;
		
		private @FXML Label label_trainee_level;
		
		private @FXML Label label_trainee_hight;
		
		private @FXML Label label_trainee_weight;
		
		private @FXML Label label_trainee_start_date;
		
		private @FXML Label label_trainee_end_date;
		
		private @FXML Label label_trainee_comments;
		
		private @FXML Label label_trainee_group;
		
		private @FXML TextField textfield_trainee_id;
		
		private @FXML TextField textfield_trainee_name;
		
		private @FXML TextField textfield_trainee_lastname;
		
		private @FXML TextField textfield_trainee_birthday;
		
		private @FXML TextField textfield_trainee_city;
		
		private @FXML TextField textfield_trainee_address;
		
		private @FXML TextField textfield_trainee_postcode;
		
		private @FXML TextField textfield_trainee_phone;
		
		private @FXML TextField textfield_trainee_cellphone;
		
		private @FXML TextField textfield_trainee_email;
		
		private @FXML TextField textfield_trainee_hight;
		
		private @FXML TextField textfield_trainee_weight;
		
		private @FXML TextArea textarea_trainee_comments;
		
		private @FXML ComboBox<String> combobox_trainee_gender;
		
		private @FXML ComboBox<String> combobox_trainee_center;
		
		private @FXML ComboBox<String> combobox_trainee_level;
		
		private @FXML ComboBox<String> combobox_trainee_group;
		
		private @FXML DatePicker datepicker_trainee_start;
		
		private @FXML DatePicker datepicker_trainee_end;
		
		private @FXML ImageView image_trainee_id;
		
		private @FXML ImageView image_trainee_name;
		
		private @FXML ImageView image_trainee_lastname;
		
		private @FXML ImageView image_trainee_birthday;
		
		private @FXML ImageView image_trainee_city;
		
		private @FXML ImageView image_trainee_address;
		
		private @FXML ImageView image_trainee_postcode;
		
		private @FXML ImageView image_trainee_phone;
		
		private @FXML ImageView image_trainee_cellphone;
		
		private @FXML ImageView image_trainee_email;
		
		private @FXML ImageView image_trainee_gender;
		


//-------------------------------------------------------------end->trainee anchor pane variables-------------------------------------------//
	
		
//-------------------------------------------------------------Lists anchor pane variables-------------------------------------------//
		private ObservableList<ListsRow> list_row = FXCollections.observableArrayList();
		
		private ObservableList<String> list_center = FXCollections.observableArrayList();
		
		private ObservableList<String> list_trainer = FXCollections.observableArrayList();
		
		private @FXML TableView<ListsRow> lists_table;
		
		private @FXML TableColumn<ListsRow,String> tablecolumn_list_center;
		
		private @FXML TableColumn<ListsRow,String> tablecolumn_list_males;
		
		private @FXML TableColumn<ListsRow,String> tablecolumn_list_females;
		
		private @FXML TableColumn<ListsRow,String> tablecolumn_list_trainer;
		
		private @FXML Button button_add_list;
		
		private @FXML Button button_delete_list;
		
		private @FXML Button button_back_list;
			
		private @FXML TextField textfield_list_males;
		
		private @FXML TextField textfield_list_females;
		
		private @FXML ComboBox<String> combobox_list_center;
		
		private @FXML ComboBox<String> combobox_list_trainers;
		
		private @FXML Label label_list_females;
		
		private @FXML Label label_list_males;
		
		private @FXML Label label_list_trainer;
		
		private @FXML Label label_list_center;
		
		private ListsRow correct_list;
		
//-------------------------------------------------------------end->Lists anchor pane variables-------------------------------------------//

		
//-------------------------------------------------------------DataBase Fields-----------------------------------------------------//
	Connection conn;
	Statement s;
	ResultSet rs;
	String temp; 
//-------------------------------------------------------------end-> DataBase Fields-----------------------------------------------------//


//---------------------------------------------------------------Initialize Functions-------------------------------------------------//
	
	@Override
	protected void internalInitialize() throws Exception {
		IntializeDataBaseConnection();
		Initialize_pictures();
		InitalizeTrainerTable();
		CenterTabInitialize();
		InitalizeCenterTable();
		InitalizeLevelList();
		InitalizeTraineeTable();
	}
	
	private void TrainersTabInitialize()
	{
		tablecolumn_name.setCellValueFactory(new PropertyValueFactory<TrainersRow, String>("name"));
		tablecolumn_last_name.setCellValueFactory(new PropertyValueFactory<TrainersRow, String>("last_name"));
		tablecolumn_phone.setCellValueFactory(new PropertyValueFactory<TrainersRow, String>("phone"));
		trainer_row = FXCollections.observableArrayList();
		try {
				String selTable = "SELECT * FROM Trainers";
				s.execute(selTable);
				rs = s.getResultSet();
			}catch(Exception ex)
			 {
				System.out.println(ex.getStackTrace());
			 }
		
		TrainerTableBuilder(rs);
		Platform.runLater(() -> {
			trainier_table.setItems(trainer_row);
			trainier_table.refresh();
		});
	}
	private void IntializeDataBaseConnection()
	{
        try
        {
        	//------------------Open Connection to Access Data Base--------------------------------
        	
        			conn=DriverManager.getConnection(
        	        "jdbc:ucanaccess://C:\\Users\\Dolev\\Desktop\\Management\\src\\Database.accdb");
        			 s = conn.createStatement();
        }
        			catch(Exception ex)
        	        {
        	            ex.printStackTrace();
        	        }
            //------------------------------------------------------------------------------------
	}
	
	private void Initialize_pictures()
	{
		InputStream view = getClass().getResourceAsStream("/boundaries/images/numbers12.jpg");
		Image Image;
		if (view != null) {
			Image = new Image(view);
			image_id.setImage(Image);
			image_trainee_id.setImage(Image);
		}
			view = getClass().getResourceAsStream("/boundaries/images/name.jpg");
			if (view != null) {
				Image = new Image(view);
				image_name.setImage(Image);
				image_trainee_name.setImage(Image);
			}
		
		view = getClass().getResourceAsStream("/boundaries/images/family.jpg");
		if (view != null) {
			Image = new Image(view);
			image_lastname.setImage(Image);
			image_trainee_lastname.setImage(Image);
		}
		
		view = getClass().getResourceAsStream("/boundaries/images/birthday.jpg");
		if (view != null) {
			Image = new Image(view);
			image_birthday.setImage(Image);
			image_trainee_birthday.setImage(Image);
		}
		
		view = getClass().getResourceAsStream("/boundaries/images/city.jpg");
		if (view != null) {
			Image = new Image(view);
			image_city.setImage(Image);
			image_trainee_city.setImage(Image);
		}
		
		view = getClass().getResourceAsStream("/boundaries/images/address.png");
		if (view != null) {
			Image = new Image(view);
			image_address.setImage(Image);
			image_trainee_address.setImage(Image);
		}
		
		view = getClass().getResourceAsStream("/boundaries/images/postcode.jpg");
		if (view != null) {
			Image = new Image(view);
			image_postcode.setImage(Image);
			image_trainee_postcode.setImage(Image);
		}
		
		view = getClass().getResourceAsStream("/boundaries/images/phone.jpg");
		if (view != null) {
			Image = new Image(view);
			image_phone.setImage(Image);
			image_trainee_phone.setImage(Image);
		}
		
		view = getClass().getResourceAsStream("/boundaries/images/cellphone.jpg");
		if (view != null) {
			Image = new Image(view);
			image_cellphone.setImage(Image);
			image_trainee_cellphone.setImage(Image);
		}
		
		view = getClass().getResourceAsStream("/boundaries/images/email.jpg");
		if (view != null) {
			Image = new Image(view);
			image_email.setImage(Image);
			image_trainee_email.setImage(Image);
		}
		
		view = getClass().getResourceAsStream("/boundaries/images/gender.png");
		if (view != null) {
			Image = new Image(view);
			image_trainee_gender.setImage(Image);
		}
	}
	
	public void InitalizeTrainerTable()
	{
		trainier_table.setRowFactory(param -> {
			TableRow<TrainersRow> tableRow = new TableRow<>();
			tableRow.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2 && (!tableRow.isEmpty())) {
					 TrainersRow rowData = tableRow.getItem();

					if (rowData.getName() == " ") {
						return;
					}
					SetVisableEditTrainerTab(rowData);
				}

			});
			return tableRow;
		});
		
	}
	
	public void InitalizeCenterTable()
	{
		center_table.setRowFactory(param -> {
			TableRow<CenterRow> tableRow = new TableRow<>();
			tableRow.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2 && (!tableRow.isEmpty())) {
					 CenterRow rowData = tableRow.getItem();

					if (rowData.getClassname() == " ") {
						return;
					}
					center_pr_key=rowData.getPr_key();
					SetCenterTabVisable();
					textfield_center_classname.setText(rowData.getClassname());
					textfield_center_price.setText(rowData.getPrice());
					combobox_center_trainers.setValue(rowData.getTrainer());
					button_add_center.setText("ערוך שיעור");
				}

			});
			return tableRow;
		});
	}

	public void CenterTabInitialize()
	{
		tablecolumn_classname.setCellValueFactory(new PropertyValueFactory<CenterRow, String>("classname"));
		tablecolumn_price.setCellValueFactory(new PropertyValueFactory<CenterRow, String>("price"));
		tablecolumn_trainer.setCellValueFactory(new PropertyValueFactory<CenterRow, String>("trainer"));
		trainer_row = FXCollections.observableArrayList();
		try {
				String selTable = "SELECT * FROM Centers";
				s.execute(selTable);
				rs = s.getResultSet();
			}catch(Exception ex)
			 {
				System.out.println(ex.getStackTrace());
			 }
		
		CenterTableBuilder(rs);
		Platform.runLater(() -> {
			center_table.setItems(center_row);
			center_table.refresh();
		});
	}

	public void TraineeTabInitialize()
	{
		tablecolumn_trainee_id.setCellValueFactory(new PropertyValueFactory<TraineesRow, String>("id"));
		tablecolumn_trainee_name.setCellValueFactory(new PropertyValueFactory<TraineesRow, String>("name"));
		tablecolumn_trainee_last_name.setCellValueFactory(new PropertyValueFactory<TraineesRow, String>("last_name"));
		tablecolumn_trainee_center.setCellValueFactory(new PropertyValueFactory<TraineesRow, String>("center"));
		tablecolumn_trainee_group.setCellValueFactory(new PropertyValueFactory<TraineesRow, String>("group"));
		tablecolumn_trainee_register_date.setCellValueFactory(new PropertyValueFactory<TraineesRow, String>("register_date"));
		tablecolumn_trainee_subscription_end_date.setCellValueFactory(new PropertyValueFactory<TraineesRow,String>("subscription_end_date"));
		trainee_row = FXCollections.observableArrayList();
		try {
				String selTable = "SELECT * FROM Trainees";
				s.execute(selTable);
				rs = s.getResultSet();
			}catch(Exception ex)
			 {
				System.out.println(ex.getStackTrace());
			 }
		
		TraineeTableBuilder(rs);
		Platform.runLater(() -> {
			trainee_table.setItems(trainee_row);
			trainee_table.refresh();
		});
	}

	public void InitalizeLevelList()
	{
		trainee_level.add("קיו 1");
		trainee_level.add("קיו 1+פס");
		trainee_level.add("קיו 2+1פסים");
		trainee_level.add("קיו 2");
		trainee_level.add("קיו 2+פס");
		trainee_level.add("קיו 2+2פסים");
		trainee_level.add("קיו 3");
		trainee_level.add("קיו 3+פס");
		trainee_level.add("קיו 2+3פסים");
		trainee_level.add("קיו 4");
		trainee_level.add("קיו 4+פס");
		trainee_level.add("קיו 2+4פסים");
		trainee_level.add("קיו 5");
		trainee_level.add("קיו 5+פס");
		trainee_level.add("קיו 2+5פסים");
		trainee_level.add("קיו 6");
		trainee_level.add("קיו 6+פס");
		trainee_level.add("קיו 2+6פסים");
		trainee_level.add("קיו 7");
		trainee_level.add("קיו 7+פס");
		trainee_level.add("קיו 2+7פסים");
		trainee_level.add("קיו 8");
		trainee_level.add("קיו 8+פס");
		trainee_level.add("קיו 2+8פסים");
		trainee_level.add("קיו 9");
		trainee_level.add("קיו 9+פס");
		trainee_level.add("קיו 2+9פסים");
		trainee_level.add("דאן 1");
		trainee_level.add("דאן 2");
		trainee_level.add("דאן 3");
		trainee_level.add("דאן 4");
		trainee_level.add("דאן 5");
		trainee_level.add("דאן 6");
		trainee_level.add("דאן 7");
		trainee_level.add("דאן 8");
	}

	public void InitalizeTraineeTable()
	{
		trainee_table.setRowFactory(param -> {
			TableRow<TraineesRow> tableRow = new TableRow<>();
			tableRow.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2 && (!tableRow.isEmpty())) {
					 TraineesRow rowData = tableRow.getItem();

					if (rowData.getName() == " ") {
						return;
					}
					SetVisableEditTraineeTab(rowData);
				}

			});
			return tableRow;
		});
		
	}
	
	public void InitializeListsTable()
	{
		tablecolumn_list_center.setCellValueFactory(new PropertyValueFactory<ListsRow, String>("center"));
		tablecolumn_list_males.setCellValueFactory(new PropertyValueFactory<ListsRow, String>("males"));
		tablecolumn_list_females.setCellValueFactory(new PropertyValueFactory<ListsRow, String>("females"));
		tablecolumn_list_trainer.setCellValueFactory(new PropertyValueFactory<ListsRow, String>("trainer"));
		list_row = FXCollections.observableArrayList();
		try {
				String selTable = "SELECT * FROM Lists";
				s.execute(selTable);
				rs = s.getResultSet();
			}catch(Exception ex)
			 {
				System.out.println(ex.getStackTrace());
			 }
		
		ListsTableBuilder(rs);
		Platform.runLater(() -> {
			lists_table.setItems(list_row);
			lists_table.refresh();
		});
	}

	public void ListsTableInitialize()
	{
		lists_table.setRowFactory(param -> {
			TableRow<ListsRow> tableRow = new TableRow<>();
			tableRow.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2 && (!tableRow.isEmpty())) {
					 ListsRow rowData = tableRow.getItem();

					if (rowData.getCenter() == " ") {
						return;
					}
					SetVisableEditListTab(rowData);
				}

			});
			return tableRow;
		});
	}
	
	
//---------------------------------------------------------------end->Initialize Functions-------------------------------------------------//

//------------------------------------------------------------------Override Functions---------------------------------------------//


	@Override
 	protected boolean onSelection(String title) 
	{
		this.title = title;
		switch (title) {
			case "מאמנים":
				anchorpane_trainers.setVisible(true);
				anchorpane_centers.setVisible(false);
				anchorpane_lists.setVisible(false);
				TrainersTabInitialize();
				SetInvisableTrainerTab();
				anchorpane_trainee.setVisible(false);
			break;

			case "מרכזים":
				anchorpane_trainers.setVisible(false);
				anchorpane_centers.setVisible(true);
				anchorpane_lists.setVisible(false);
				SetCenterTabInVisable();
				anchorpane_trainee.setVisible(false);

			break;

			case "רשימות":
					anchorpane_trainers.setVisible(false);
					anchorpane_centers.setVisible(false);
					anchorpane_lists.setVisible(true);
					anchorpane_trainee.setVisible(false);
					InitializeListsTable();
					ListsTableInitialize();
			
			break;	
			
			case "ספורטאים":
				anchorpane_trainers.setVisible(false);
				anchorpane_centers.setVisible(false);
				anchorpane_lists.setVisible(false);
				anchorpane_trainee.setVisible(true);
				TraineeTabInitialize();
				SetTraineeTabInVisable();
				break;
			default:
				return false;
		}
		return true;
	}

	@Override
	protected String[] getSideButtonsNames()
	{
		return window;
	}
	
	public void TrainerTableBuilder(ResultSet set)  
	{
		try {
			while((set!=null) && (set.next()))
			{
			   TrainersRow temp=new TrainersRow(set.getString(1), set.getString(2), set.getString(8));
			   trainer_row.add(temp);   
			}
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}

	}
	
	public ResultSet GetFromDataBase(String table_name,DataBaseAction action,String title,String paramter)
	{
		String command;
		switch(action)
		{
		case Get:
					command="select * from "+table_name+ " where "+title+ " = " + paramter;
					break;
		case GetAll:
					command="select * from "+table_name;
					break;
		case Delete:
					command="delete from "+table_name+ " where "+title+ " = " + paramter;
					break;
		default:
				return null;				
		}
		try {
			s.execute(command);
			ResultSet resulte=s.getResultSet();
			return resulte;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}	
		

	}
//------------------------------------------------------------end->Override Functions---------------------------------------------//

//-----------------------------------------------------------TrainerTab Functions--------------------------------------------------//	
	
	@FXML
	public void AddTrainerButtonClick(ActionEvent event)
	{	if(button_add_trainer.getText().equals("ערוך מאמן"))
		{
			DeleteFromTrainerTable(textfield_id.getText());
			if((textfield_id.getText().equals(""))||(textfield_name.getText().equals(""))||(textfield_lastname.getText().equals(""))||
					(textfield_birthday.getText().equals(""))||textfield_address.getText().equals("")||textfield_city.getText().equals("")||
					textfield_postcode.getText().equals("")||textfield_phone.getText().equals("")||textfield_cellphone.getText().equals("")
					||textfield_email.getText().equals(""))
				showAlertMessage("אתה חייב למלא את כל השדות ", AlertType.ERROR);
			else
			{
				if(InsertIntoTrainerTable(textfield_id.getText(), textfield_name.getText(), textfield_lastname.getText(),
						textfield_birthday.getText(), textfield_city.getText(), textfield_address.getText(),
						textfield_postcode.getText(), textfield_phone.getText(), textfield_cellphone.getText(),
						textfield_email.getText()))
				{
					SetInvisableTrainerTab();
				}
			}
		}
		else
		{
		if(!label_id.isVisible())
		{
			SetVisableTrainerTab();
		}
		else
		{
			if((textfield_id.getText().equals(""))||(textfield_name.getText().equals(""))||(textfield_lastname.getText().equals(""))||
					(textfield_birthday.getText().equals(""))||textfield_address.getText().equals("")||textfield_city.getText().equals("")||
					textfield_postcode.getText().equals("")||textfield_phone.getText().equals("")||textfield_cellphone.getText().equals("")
					||textfield_email.getText().equals(""))
			{
				showAlertMessage("אתה חייב למלא את כל השדות ", AlertType.ERROR);
			}
			else
				if(InsertIntoTrainerTable(textfield_id.getText(), textfield_name.getText(), textfield_lastname.getText(),
									textfield_birthday.getText(), textfield_city.getText(), textfield_address.getText(),
									textfield_postcode.getText(), textfield_phone.getText(), textfield_cellphone.getText(),
									textfield_email.getText()))
				{
					SetInvisableTrainerTab();
				}
		}
		}
	}
	
	@FXML
	public void DeleteTrainerButtonClick(ActionEvent event)
	{
		DeleteFromTrainerTable(textfield_id.getText());
		SetInvisableTrainerTab();
		InitalizeTrainerTable();
	}
	
	@FXML
	public void BackTrainerButtonClick(ActionEvent event)
	{
		SetInvisableTrainerTab();
	}
	
	public void SetVisableTrainerTab()
	{
		trainier_table.setVisible(false);
		button_add_trainer.setLayoutY(500.0);
		button_add_trainer.setLayoutX(300.0);
		button_back_trainer.setVisible(true);
		label_id.setVisible(true);
		label_name.setVisible(true);
		label_lastname.setVisible(true);
		label_birthday.setVisible(true);
		label_city.setVisible(true);
		label_address.setVisible(true);
		label_postcode.setVisible(true);
		label_phone.setVisible(true);
		label_cellphone.setVisible(true);
		label_email.setVisible(true);
		textfield_id.setVisible(true);
		textfield_name.setVisible(true);
		textfield_lastname.setVisible(true);
		textfield_birthday.setVisible(true);
		textfield_city.setVisible(true);
		textfield_address.setVisible(true);
		textfield_postcode.setVisible(true);
		textfield_phone.setVisible(true);
		textfield_cellphone.setVisible(true);
		textfield_email.setVisible(true);
		image_id.setVisible(true);
		image_name.setVisible(true);
		image_lastname.setVisible(true);
		image_birthday.setVisible(true);
		image_city.setVisible(true);
		image_address.setVisible(true);
		image_postcode.setVisible(true);
		image_phone.setVisible(true);
		image_cellphone.setVisible(true);
		image_email.setVisible(true);
	}
	
	
	public void SetInvisableTrainerTab()
	{
		trainier_table.setVisible(true);
		button_add_trainer.setLayoutY(368);
		button_add_trainer.setLayoutX(420.0);
		button_back_trainer.setVisible(false);
		button_delete_trainer.setVisible(false);
		button_add_trainer.setText("הוסף מדריך חדש");
		label_id.setVisible(false);
		label_name.setVisible(false);
		label_lastname.setVisible(false);
		label_birthday.setVisible(false);
		label_city.setVisible(false);
		label_address.setVisible(false);
		label_postcode.setVisible(false);
		label_phone.setVisible(false);
		label_cellphone.setVisible(false);
		label_email.setVisible(false);
		textfield_id.setVisible(false);
		textfield_name.setVisible(false);
		textfield_lastname.setVisible(false);
		textfield_birthday.setVisible(false);
		textfield_city.setVisible(false);
		textfield_address.setVisible(false);
		textfield_postcode.setVisible(false);
		textfield_phone.setVisible(false);
		textfield_cellphone.setVisible(false);
		textfield_email.setVisible(false);
		textfield_id.clear();
		textfield_name.clear();
		textfield_lastname.clear();
		textfield_birthday.clear();
		textfield_city.clear();
		textfield_address.clear();
		textfield_postcode.clear();
		textfield_phone.clear();
		textfield_cellphone.clear();
		textfield_email.clear();
		image_id.setVisible(false);
		image_name.setVisible(false);
		image_lastname.setVisible(false);
		image_birthday.setVisible(false);
		image_city.setVisible(false);
		image_address.setVisible(false);
		image_postcode.setVisible(false);
		image_phone.setVisible(false);
		image_cellphone.setVisible(false);
		image_email.setVisible(false);
		textfield_id.setEditable(true);
		TrainersTabInitialize();
	}
	
	public boolean InsertIntoTrainerTable(String id,String name,String lastname,String birthday,String city,String address
						,String postcode,String phone,String cellphone,String email)
	{		
		boolean exist=false;
		try {
			String selTable = "SELECT * FROM Trainers";
			s.execute(selTable);
			rs = s.getResultSet();
		}catch(Exception ex)
		 {
			System.out.println(ex.getStackTrace());
		 }
			try {
				while((rs!=null) && (rs.next()))
				{
					if(rs.getString(10).equals(id))
					{
						showAlertMessage("המאמן כבר קיים במערכת", AlertType.INFORMATION);
						exist=true;
						break;
					}
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		
				if(!exist)
				{
				temp = "INSERT into Trainers(id,name,lastname,birthday,city,address,postcode,phone,cellphone,email) "+
				   "VALUES('"+id+"','"+name+"','"+lastname+"','"+birthday+"','"+city+"','"+address+"','"+postcode+
				   "','"+phone+"','"+cellphone+"','"+email+"')";
				try {
					s.execute(temp);
				} catch (SQLException e) 
				{
					showAlertMessage("לא יכול להוסיף מאמן", AlertType.ERROR);
					return false;
				}
				return true;
				}
				else
				{
					return false;
				}
	}
	public void SetVisableEditTrainerTab(TrainersRow rowdata)
	{
		try {

				String selTable = "SELECT * FROM Trainers";
				s.execute(selTable);
				rs = s.getResultSet();
			while((rs!=null) && (rs.next()))
			{
				if((rs.getString(1).equals(rowdata.getName()))&&(rs.getString(2).equals(rowdata.getLast_name())&&
						(rs.getString(8).equals(rowdata.getPhone()))))
				{
					textfield_name.setText(rs.getString(1));
					textfield_lastname.setText(rs.getString(2));
					textfield_birthday.setText(rs.getString(3));
					textfield_city.setText(rs.getString(4));
					textfield_address.setText(rs.getString(5));
					textfield_postcode.setText(rs.getString(6));
					textfield_phone.setText(rs.getString(7));
					textfield_cellphone.setText(rs.getString(8));
					textfield_email.setText(rs.getString(9));
					textfield_id.setText(rs.getString(10));
					textfield_id.setEditable(false);
					SetVisableTrainerTab();
					button_add_trainer.setText("ערוך מאמן");
					button_delete_trainer.setVisible(true);	
					break;
				}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	
	}
	public void DeleteFromTrainerTable(String id)
	{
		String delete="delete from Trainers where ID = "+id;
		try {
			s.execute(delete);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
//------------------------------------------------------------end->Trainer tab Functions-------------------------------------------//

//-----------------------------------------------------------------Center tab Functions-------------------------------------------//
	
	
	public void CenterTableBuilder(ResultSet set)
	{
		center_row.clear();
		try {
			while((set!=null) && (set.next()))
			{
			   CenterRow temp=new CenterRow(set.getInt(4), set.getString(3), set.getString(1), set.getString(2));
			   center_row.add(temp);   
			}
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}

	}

	@FXML
	public void AddCenterButtonClick (ActionEvent event)
	{
		if(button_add_center.getText()=="ערוך שיעור")
		{
			String command="delete from Centers where pr_key ="+center_pr_key;
			try {
				s.execute(command);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(textfield_center_classname.getText().equals("")||textfield_center_price.getText().equals("")||
					combobox_center_trainers.getValue().equals(""))
			{
				showAlertMessage("אתה חייב למלא את כל השדות", AlertType.ERROR);
			}
			else
			{
				if(AddNewCenter(textfield_center_classname.getText(),textfield_center_price.getText()
						,combobox_center_trainers.getValue()))
					SetCenterTabInVisable();
			}
					
		}
		else
		{
			if(center_table.isVisible())
				SetCenterTabVisable();
			else
			{
				if(textfield_center_classname.getText().equals("")||textfield_center_price.getText().equals("")||
						combobox_center_trainers.getValue().equals(""))
				{
					showAlertMessage("אתה חייב למלא את כל השדות", AlertType.ERROR);
				}
				else
				{
					if(AddNewCenter(textfield_center_classname.getText(),textfield_center_price.getText()
							,combobox_center_trainers.getValue()))
						SetCenterTabInVisable();
				}
			}
		}
	}
	
	public void SetCenterTabVisable()
	{
		center_table.setVisible(false);
		button_back_center.setVisible(true);
		button_add_center.setLayoutX(310);
		button_delete_center.setVisible(true);
		textfield_center_classname.setVisible(true);
		textfield_center_price.setVisible(true);
		label_center_classname.setVisible(true);
		label_center_price.setVisible(true);
		label_center_trainer.setVisible(true);
		combobox_center_trainers.setVisible(true);
		ResultSet trainers_set=GetFromDataBase("Trainers",DataBaseAction.GetAll,null,null);
		try {
			while((trainers_set!=null) && (trainers_set.next()))
			{
			   String temp=trainers_set.getString(1)+" "+trainers_set.getString(2);
			   center_trainer_list.add(temp);   
			}
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
		combobox_center_trainers.setItems(center_trainer_list);
		
	}
	
	public void SetCenterTabInVisable()
	{
		center_table.setVisible(true);
		button_delete_center.setVisible(false);
		button_back_center.setVisible(false);
		button_add_center.setLayoutX(420);
		button_add_center.setText("הוסף שיעור חדש");
		textfield_center_classname.setVisible(false);
		textfield_center_price.setVisible(false);
		label_center_classname.setVisible(false);
		label_center_price.setVisible(false);
		label_center_trainer.setVisible(false);
		combobox_center_trainers.setVisible(false);
		textfield_center_classname.clear();
		textfield_center_price.clear();
		combobox_center_trainers.getItems().clear();
		combobox_center_trainers.setValue(null);
		CenterTabInitialize();
	}
	
	public boolean AddNewCenter(String classname,String price,String trainer)
	{
		String command="INSERT into Centers(classname,price,trainer)"+
				   "VALUES('"+classname+"','"+price+"','"+trainer+"')";
		try {
			s.execute(command);
		} catch (SQLException e) {
			showAlertMessage("Somthing Went Wrong", AlertType.ERROR);
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@FXML
	public void BackCenterButtonClick(ActionEvent event)
	{
		SetCenterTabInVisable();
	}
	
	@FXML
	public void DeleteCenterButtonClick(ActionEvent event)
	{
		String command="delete from Centers where pr_key ="+center_pr_key;
		try {
			s.execute(command);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		SetCenterTabInVisable();
	}

//---------------------------------------------------------end->Center tab Functions------------------------------------------------//

//---------------------------------------------------------Trainee tab Functions------------------------------------------------//	
	
	public void TraineeTableBuilder(ResultSet set)
	{
		trainee_row.clear();
		try {
			while((set!=null) && (set.next()))
			{
			   TraineesRow temp=new TraineesRow(set.getString(13), set.getString(1), set.getString(2), set.getString(10),set.getString(11),set.getString(18),set.getString(19));
			   trainee_row.add(temp);   
			}
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

	@FXML
	public void AddTraineeButtonClick(ActionEvent event)
	{
		if(trainee_table.isVisible())
		SetTraineeTabVisable();
		else
		{
			if(button_add_trainee.getText().equals("ערוך ספורטאי"))
			{
				GetFromDataBase("Trainees", DataBaseAction.Delete, "id", textfield_trainee_id.getText());
				if((textfield_trainee_id.getText().equals(""))||(textfield_trainee_name.getText().equals(""))||(textfield_trainee_lastname.getText().equals(""))||
						(textfield_trainee_birthday.getText().equals(""))||textfield_trainee_address.getText().equals("")||textfield_trainee_city.getText().equals("")||
						textfield_trainee_postcode.getText().equals("")||textfield_trainee_phone.getText().equals("")||textfield_trainee_cellphone.getText().equals("")
						||textfield_trainee_email.getText().equals("")||combobox_trainee_gender.getValue()==null||combobox_trainee_group.getValue()==null
						||combobox_trainee_center.getValue()==null||combobox_trainee_level.getValue()==null||textfield_trainee_hight.getText().equals("")
						||textfield_trainee_weight.getText().equals("")||datepicker_trainee_start.getValue()==null||datepicker_trainee_end.getValue()==null)
				{
					showAlertMessage("אתה חייב למלא את כל השדות", AlertType.ERROR);
			}
			else
				{
				String command= "INSERT into Trainees(id,name,lastname,birthday,city,address,postcode,phone,cellphone,email,group,gender,center,level,hight,weight,register_date,subscription_end_date,commnts) "+
						   "VALUES('"+textfield_trainee_id.getText()+"','"+textfield_trainee_name.getText()+"','"+textfield_trainee_lastname.getText()+"','"+textfield_trainee_birthday.getText()+
						   "','"+textfield_trainee_city.getText()+"','"+textfield_trainee_address.getText()+"','"+textfield_trainee_postcode.getText()+
						   "','"+textfield_trainee_phone.getText()+"','"+textfield_trainee_cellphone.getText()+"','"+textfield_trainee_email.getText()+
						   "','"+combobox_trainee_group.getValue()+"','"+combobox_trainee_gender.getValue()+"','"+combobox_trainee_center.getValue()+
						   "','"+combobox_trainee_level.getValue()+ "','"+textfield_trainee_hight.getText()+ "','"+textfield_trainee_weight.getText()+
						   "','"+s_dateForamt.format(datepicker_trainee_start.getValue())+"','"+s_dateForamt.format(datepicker_trainee_end.getValue())+
						   "','"+textarea_trainee_comments.getText()+"')";
				try {
					s.execute(command);
					} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					}
				SetTraineeTabInVisable();
				}
			}
			else
			{
				boolean flag= false;
				ResultSet set=GetFromDataBase("Trainees", DataBaseAction.GetAll, null, null);
				try {
					while((set!=null) && (set.next()))
					{
						if(set.getString(13).equals(textfield_trainee_id.getText()))
							flag=true;
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				if(flag)
				{
					showAlertMessage("הספורטאי כבר קיים במערכת", AlertType.INFORMATION);
				}
				else
				{
					if((textfield_trainee_id.getText().equals(""))||(textfield_trainee_name.getText().equals(""))||(textfield_trainee_lastname.getText().equals(""))||
							(textfield_trainee_birthday.getText().equals(""))||textfield_trainee_address.getText().equals("")||textfield_trainee_city.getText().equals("")||
							textfield_trainee_postcode.getText().equals("")||textfield_trainee_phone.getText().equals("")||textfield_trainee_cellphone.getText().equals("")
							||textfield_trainee_email.getText().equals("")||combobox_trainee_gender.getValue()==null||combobox_trainee_group.getValue()==null
							||combobox_trainee_center.getValue()==null||combobox_trainee_level.getValue()==null||textfield_trainee_hight.getText().equals("")
							||textfield_trainee_weight.getText().equals("")||datepicker_trainee_start.getValue()==null||datepicker_trainee_end.getValue()==null)
					{
						showAlertMessage("אתה חייב למלא את כל השדות", AlertType.ERROR);
				}
				else
					{
					String command= "INSERT into Trainees(id,name,lastname,birthday,city,address,postcode,phone,cellphone,email,group,gender,center,level,hight,weight,register_date,subscription_end_date,commnts) "+
							   "VALUES('"+textfield_trainee_id.getText()+"','"+textfield_trainee_name.getText()+"','"+textfield_trainee_lastname.getText()+"','"+textfield_trainee_birthday.getText()+
							   "','"+textfield_trainee_city.getText()+"','"+textfield_trainee_address.getText()+"','"+textfield_trainee_postcode.getText()+
							   "','"+textfield_trainee_phone.getText()+"','"+textfield_trainee_cellphone.getText()+"','"+textfield_trainee_email.getText()+
							   "','"+combobox_trainee_group.getValue()+"','"+combobox_trainee_gender.getValue()+"','"+combobox_trainee_center.getValue()+
							   "','"+combobox_trainee_level.getValue()+ "','"+textfield_trainee_hight.getText()+ "','"+textfield_trainee_weight.getText()+
							   "','"+s_dateForamt.format(datepicker_trainee_start.getValue())+"','"+s_dateForamt.format(datepicker_trainee_end.getValue())+
							   "','"+textarea_trainee_comments.getText()+"')";
					try {
						s.execute(command);
						} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						}
					SetTraineeTabInVisable();
					}
				}
			
			}	
		}
	}
	
	@FXML
	public void BackTraineeButtonClick(ActionEvent event)
	{
		SetTraineeTabInVisable();
	}
	
	@FXML
	public void DeleteTraineeButtonClick(ActionEvent event)
	{
		GetFromDataBase("Trainees", DataBaseAction.Delete, "id", textfield_trainee_id.getText());
		SetTraineeTabInVisable();
		
	}
	
	public void SetTraineeTabVisable()
	{
		trainee_table.setVisible(false);
		button_back_trainee.setVisible(true);
		button_add_trainee.setLayoutX(310);
		button_add_trainee.setLayoutY(519);
		button_delete_trainee.setVisible(true);
		label_trainee_id.setVisible(true);
		label_trainee_name.setVisible(true);
		label_trainee_lastname.setVisible(true);
		label_trainee_birthday.setVisible(true);
		label_trainee_city.setVisible(true);
		label_trainee_address.setVisible(true);
		label_trainee_postcode.setVisible(true);
		label_trainee_phone.setVisible(true);
		label_trainee_cellphone.setVisible(true);
		label_trainee_email.setVisible(true);
		label_trainee_gender.setVisible(true);
		label_trainee_center.setVisible(true);
		label_trainee_level.setVisible(true);
		label_trainee_hight.setVisible(true);
		label_trainee_weight.setVisible(true);
		label_trainee_start_date.setVisible(true);
		label_trainee_end_date.setVisible(true);
		label_trainee_comments.setVisible(true);
		label_trainee_group.setVisible(true);
		textfield_trainee_id.setVisible(true);
		textfield_trainee_name.setVisible(true);
		textfield_trainee_lastname.setVisible(true);
		textfield_trainee_birthday.setVisible(true);
		textfield_trainee_city.setVisible(true);
		textfield_trainee_address.setVisible(true);
		textfield_trainee_postcode.setVisible(true);
		textfield_trainee_phone.setVisible(true);
		textfield_trainee_cellphone.setVisible(true);
		textfield_trainee_email.setVisible(true);
		textfield_trainee_hight.setVisible(true);
		textfield_trainee_weight.setVisible(true);
		textarea_trainee_comments.setVisible(true);
		ObservableList<String> gender_list = FXCollections.observableArrayList();
		gender_list.add("זכר");
		gender_list.add("נקבה");
		combobox_trainee_gender.setItems(gender_list);
		combobox_trainee_gender.setVisible(true);
		SetTraineeCenterComboBox();
		combobox_trainee_center.setVisible(true);
		combobox_trainee_level.setItems(trainee_level);
		combobox_trainee_level.setVisible(true);
		SetTraineegroupCombobox();
		combobox_trainee_group.setItems(trainee_group_list);
		combobox_trainee_group.setVisible(true);
		datepicker_trainee_start.setVisible(true);
		datepicker_trainee_end.setVisible(true);
		image_trainee_id.setVisible(true);
		image_trainee_name.setVisible(true);
		image_trainee_lastname.setVisible(true);
		image_trainee_birthday.setVisible(true);
		image_trainee_city.setVisible(true);
		image_trainee_address.setVisible(true);
		image_trainee_postcode.setVisible(true);
		image_trainee_phone.setVisible(true);
		image_trainee_cellphone.setVisible(true);
		image_trainee_email.setVisible(true);
		image_trainee_gender.setVisible(true);
		
	}
	
	public void SetTraineeTabInVisable()
	{
		trainee_table.setVisible(true);
		button_back_trainee.setVisible(false);
		button_add_trainee.setLayoutX(420);
		button_add_trainee.setLayoutY(368);
		button_add_trainee.setText("הוסף ספורטאי חדש");
		button_delete_trainee.setVisible(false);
		label_trainee_id.setVisible(false);
		label_trainee_name.setVisible(false);
		label_trainee_lastname.setVisible(false);
		label_trainee_birthday.setVisible(false);
		label_trainee_city.setVisible(false);
		label_trainee_address.setVisible(false);
		label_trainee_postcode.setVisible(false);
		label_trainee_phone.setVisible(false);
		label_trainee_cellphone.setVisible(false);
		label_trainee_email.setVisible(false);
		label_trainee_gender.setVisible(false);
		label_trainee_center.setVisible(false);
		label_trainee_level.setVisible(false);
		label_trainee_hight.setVisible(false);
		label_trainee_weight.setVisible(false);
		label_trainee_start_date.setVisible(false);
		label_trainee_end_date.setVisible(false);
		label_trainee_comments.setVisible(false);
		label_trainee_group.setVisible(false);
		image_trainee_id.setVisible(false);
		image_trainee_name.setVisible(false);
		image_trainee_lastname.setVisible(false);
		image_trainee_birthday.setVisible(false);
		image_trainee_city.setVisible(false);
		image_trainee_address.setVisible(false);
		image_trainee_postcode.setVisible(false);
		image_trainee_phone.setVisible(false);
		image_trainee_cellphone.setVisible(false);
		image_trainee_email.setVisible(false);
		image_trainee_gender.setVisible(false);
		textfield_trainee_id.setVisible(false);
		textfield_trainee_name.setVisible(false);
		textfield_trainee_lastname.setVisible(false);
		textfield_trainee_birthday.setVisible(false);
		textfield_trainee_city.setVisible(false);
		textfield_trainee_address.setVisible(false);
		textfield_trainee_postcode.setVisible(false);
		textfield_trainee_phone.setVisible(false);
		textfield_trainee_cellphone.setVisible(false);
		textfield_trainee_email.setVisible(false);
		textfield_trainee_hight.setVisible(false);
		textfield_trainee_weight.setVisible(false);
		textfield_trainee_id.clear();
		textfield_trainee_name.clear();
		textfield_trainee_lastname.clear();
		textfield_trainee_birthday.clear();
		textfield_trainee_city.clear();
		textfield_trainee_address.clear();
		textfield_trainee_postcode.clear();
		textfield_trainee_phone.clear();
		textfield_trainee_cellphone.clear();
		textfield_trainee_email.clear();
		textfield_trainee_hight.clear();
		textfield_trainee_weight.clear();
		textarea_trainee_comments.setVisible(false);
		textarea_trainee_comments.clear();
		combobox_trainee_gender.setVisible(false);
		combobox_trainee_gender.getItems().clear();
		combobox_trainee_gender.setValue(null);
		combobox_trainee_center.setVisible(false);
		combobox_trainee_center.getItems().clear();
		combobox_trainee_center.setValue(null);
		combobox_trainee_level.setVisible(false);
		combobox_trainee_level.setValue(null);
		combobox_trainee_group.setVisible(false);
		combobox_trainee_group.getItems().clear();
		combobox_trainee_group.setValue(null);
		datepicker_trainee_start.setVisible(false);
		datepicker_trainee_start.setValue(null);
		datepicker_trainee_end.setVisible(false);
		datepicker_trainee_end.setValue(null);
		textfield_trainee_id.setEditable(true);
		TraineeTabInitialize();
	}
	
	public void SetTraineeCenterComboBox()
	{
		ResultSet set=GetFromDataBase("Centers", DataBaseAction.GetAll, null, null);
		ObservableList<String> trainee_center_list = FXCollections.observableArrayList();
		try {
			while((set!=null) && (set.next()))
			{
			   trainee_center_list.add(set.getString(3));   
			}
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		combobox_trainee_center.setItems(trainee_center_list);
	}
	
	
	public void SetVisableEditTraineeTab(TraineesRow trainee)
	{
		SetTraineeTabVisable();
		button_add_trainee.setText("ערוך ספורטאי");
		String id=trainee.getId();
		ResultSet set =GetFromDataBase("Trainees", DataBaseAction.Get, "id", id);
		try {
			while((set!=null) && (set.next()))
			{
				textfield_trainee_id.setText(set.getString(13));
				textfield_trainee_name.setText(set.getString(1));
				textfield_trainee_lastname.setText(set.getString(2));
				textfield_trainee_birthday.setText(set.getString(3));
				textfield_trainee_city.setText(set.getString(4));
				textfield_trainee_address.setText(set.getString(5));
				textfield_trainee_postcode.setText(set.getString(6));
				textfield_trainee_phone.setText(set.getString(7));
				textfield_trainee_cellphone.setText(set.getString(8));
				textfield_trainee_email.setText(set.getString(9));
				textfield_trainee_hight.setText(set.getString(14));
				textfield_trainee_weight.setText(set.getString(15));
				textarea_trainee_comments.setText(set.getString(16));
				datepicker_trainee_start.setValue(LocalDate.parse(set.getString(18), s_dateForamt));
				datepicker_trainee_end.setValue(LocalDate.parse(set.getString(19), s_dateForamt));
				combobox_trainee_group.setValue(set.getString(11));
				combobox_trainee_gender.setValue(set.getString(12));
				combobox_trainee_center.setValue(set.getString(10));
				combobox_trainee_level.setValue(set.getString(17));
			}
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
		textfield_trainee_id.setEditable(false);
	}
	

	public void SetTraineegroupCombobox()
	{
		//TODO-Add groups list
		trainee_group_list.add("6");
		trainee_group_list.add("5");
		trainee_group_list.add("4");
		trainee_group_list.add("3");
		trainee_group_list.add("2");
		trainee_group_list.add("1");
	}
	
//---------------------------------------------------------end->Trainee tab Functions------------------------------------------------//	

    public void ListsTableBuilder(ResultSet set)
	 {
		list_row.clear();
		try {
			while((set!=null) && (set.next()))
			{
			   ListsRow temp=new ListsRow(set.getInt(1),set.getString(2), set.getString(3), set.getString(4), set.getString(5));
			   list_row.add(temp);   
			}
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
	 }

    @FXML
    public void AddListButtonClick(ActionEvent event)
    {
    	if(lists_table.isVisible())
    	{
    		SetListsTabVisable();
    	}
    	else
    	{
    		if(button_add_list.getText().equals("ערוך רשימה"))
    		{
    			if(combobox_list_center.getValue()==null||combobox_list_trainers.getValue()==null)
    			{
    				showAlertMessage("אתה חייב למלא את כל השדות", AlertType.ERROR);
    			}
    			else
    			{
    				GetFromDataBase("Lists", DataBaseAction.Delete, "id", Integer.toString(correct_list.getPr_key()));
    				String command="INSERT into Lists(center,males,females,trainer)"+
    						"VALUES('"+combobox_list_center.getValue()+"','"+textfield_list_males.getText()+"','"
    					   			  +textfield_list_females.getText()+"','"+combobox_list_trainers.getValue()+"')";
    				try {
    					s.execute(command);
    				} catch (SQLException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
    				SetListsTabInVisable();
    			}
    		}
    		else
    		{
    	
    			if(combobox_list_center.getValue()==null||combobox_list_trainers.getValue()==null)
    			{
    				showAlertMessage("אתה חייב למלא את כל השדות", AlertType.ERROR);
    			}
    			else
    			{
    				String command="INSERT into Lists(center,males,females,trainer)"+
    						"VALUES('"+combobox_list_center.getValue()+"','"+textfield_list_males.getText()+"','"
    					   			  +textfield_list_females.getText()+"','"+combobox_list_trainers.getValue()+"')";
    				try {
    					s.execute(command);
    				} catch (SQLException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
    				SetListsTabInVisable();
    			}
    			
    		}
    	}
    }

    public void SetListsTabVisable()
    {
    	button_add_list.setLayoutX(320);
    	lists_table.setVisible(false);
    	button_delete_list.setVisible(true);
    	button_back_list.setVisible(true);
    	label_list_trainer.setVisible(true);
    	label_list_males.setVisible(true);
    	label_list_females.setVisible(true);
    	textfield_list_males.setVisible(true);
    	textfield_list_females.setVisible(true);
    	combobox_list_center.setVisible(true);
    	combobox_list_trainers.setVisible(true);
    	FillListCenterCombobox();
    	combobox_list_center.setItems(list_center);
    	FillListTrainerCombobox();
    	combobox_list_trainers.setItems(list_trainer);
    	label_list_center.setVisible(true);
    }
    
    public void SetListsTabInVisable()
    {
    	button_add_list.setLayoutX(420);
    	button_add_list.setText("הוסף רשימה חדשה");
    	lists_table.setVisible(true);
    	button_delete_list.setVisible(false);
    	button_back_list.setVisible(false);
    	label_list_trainer.setVisible(false);
    	label_list_males.setVisible(false);
    	label_list_females.setVisible(false);
    	textfield_list_males.setVisible(false);
    	textfield_list_females.setVisible(false);
    	combobox_list_center.setVisible(false);
    	combobox_list_trainers.setVisible(false);
    	label_list_center.setVisible(false);
    	textfield_list_females.clear();
    	textfield_list_males.clear();
    	combobox_list_center.setValue(null);
    	InitializeListsTable();
    	
    }
    
    @FXML
    public void DeleteListButtonClick(ActionEvent event)
    {
    	GetFromDataBase("Lists", DataBaseAction.Delete, "id", Integer.toString(correct_list.getPr_key()));
    	SetListsTabInVisable();
    }

    public void BackListButtonClick(ActionEvent event)
    {
    	SetListsTabInVisable();
    }

    public void FillListCenterCombobox()
    {
    	ResultSet set=GetFromDataBase("Centers", DataBaseAction.GetAll, null, null);
    	list_center.clear();
		try {
			while((set!=null) && (set.next()))
			{
			   String temp=set.getString(3);
			   list_center.add(temp);   
			}
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
    }

    public void FillListTrainerCombobox()
    {
    	ResultSet set=GetFromDataBase("Trainers", DataBaseAction.GetAll, null, null);
    	list_trainer.clear();
		try {
			while((set!=null) && (set.next()))
			{
			   String temp=set.getString(1)+" "+set.getString(2);
			   list_trainer.add(temp);   
			}
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
    }

    @FXML
    public void ListCenterComboClick(ActionEvent event)
    {
    	int male_counter=0;
    	int female_counter=0;
    	if(combobox_list_center.getValue()==null)  
    		return;
    	 ResultSet set=GetFromDataBase("Trainees", DataBaseAction.GetAll, null, null);
    	 try {
			while((set!=null) && (set.next()))
				{
					if(set.getString(10).equals(combobox_list_center.getValue()))
					{
						if(set.getString(12).equals("זכר"))
							male_counter++;
						else
							female_counter++;
					}
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    		textfield_list_males.setText(Integer.toString(male_counter));
    		textfield_list_females.setText(Integer.toString(female_counter));
    }

    public void SetVisableEditListTab(ListsRow list)
    {
    	correct_list=list;
    	SetListsTabVisable();
    	button_add_list.setText("ערוך רשימה");
    	combobox_list_center.setValue(list.getCenter());
    	combobox_list_trainers.setValue(list.getTrainer());
    }
}
