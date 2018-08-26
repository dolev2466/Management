package controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Formatter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

import boundaries.CenterRow;
import boundaries.GroupsRow;
import boundaries.LevelsRow;
import boundaries.ListsRow;
import boundaries.PaymentRow;
import boundaries.TraineesRow;
import boundaries.TrainersRow;
import controllers.Enums.DataBaseAction;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterAttributes;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.transform.Scale;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.StringConverter;
import javafx.util.converter.LocalDateStringConverter;
import javafx.util.converter.LocalTimeStringConverter;
import jdk.nashorn.internal.ir.BreakableNode;

public class MainController extends BaseController 
{
	private String title;
	
	private String[] window = { "מאמנים", "מרכזים","רשימות","ספורטאים","פעילויות","פג תוקף" };
	
	public static final String MY_SQL_DRIVER_NAME="net.ucanaccess.jdbc.UcanaccessDriver";
//------------------------------------------------------------------trainer anchor pane variables-----------------------------------------------//	
	private @FXML AnchorPane anchorpane_trainers;
	
	private @FXML TableView<TrainersRow> trainier_table;
	
	private @FXML TableColumn<TrainersRow,String> tablecolumn_name;
	
	private @FXML TableColumn<TrainersRow,String> tablecolumn_last_name;
	
	private @FXML TableColumn<TrainersRow,String> tablecolumn_phone;
	
	private @FXML Button button_add_trainer;
	
	private @FXML Button button_back_trainer;
	
	private @FXML Button button_delete_trainer;
	
	private @FXML Button button_search_trainer;
	
	private @FXML Label label_search;
	
	private @FXML Label label_search_name;
	
	private @FXML Label label_search_lastname;
	
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
	
	private @FXML TextField textfield_search_name;
	
	private @FXML TextField textfield_search_lastname;
	
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
	
	private @FXML Button button_search_center;
	
	private @FXML TextField textfield_center_classname;
	
	private @FXML TextField textfield_center_price;
	
	private @FXML TextField textfield_search_centername;
	
	private @FXML Label label_center_classname;
	
	private @FXML Label label_center_price;
	
	private @FXML Label label_center_trainer;
	
	private @FXML Label label_search_center;
	
	private @FXML Label label_search_centername;
	
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
		
		private ObservableList<String> trainee_paymentway_list = FXCollections.observableArrayList();
		
		private ObservableList<Integer> trainee_paymentway_num = FXCollections.observableArrayList();
		
		private @FXML Button button_add_trainee;
		
		private @FXML Button button_level_trainee;
		
		private @FXML Button button_back_trainee;
		
		private @FXML Button button_delete_trainee;
		
		private @FXML Button button_search_trainee;
		
		private @FXML Button button_payment_trainee;
		
		private @FXML Button button_paymentback_trainee;
		
		private @FXML Button button_add_image;
		
		private @FXML Button button_print_trainee;
		
		private @FXML Button button_search_traineecenter;
		
		private @FXML Button button_print_traineeinfo;
		
		private @FXML Label label_traineesearch;
		
		private @FXML Label label_search_traineeid;
		
		private @FXML Label label_search_traineename;
		
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
		
		private @FXML Label label_trainee_sum;
		
		private @FXML Label label_trainee_cheksnum;
		
		private @FXML Label label_trainee_chek1;
		
		private @FXML Label label_trainee_chek2;
		
		private @FXML Label label_trainee_chek3;
		
		private @FXML Label label_trainee_chek4;
		
		private @FXML Label label_trainee_chek5;
		
		private @FXML Label label_trainee_chek6;
		
		private @FXML Label label_trainee_chek7;
		
		private @FXML Label label_trainee_chek8;
		
		private @FXML Label label_trainee_chek9;
		
		private @FXML Label label_trainee_chek10;
		
		private @FXML Label label_trainee_chek11;
		
		private @FXML Label label_trainee_chek12;
		
		private @FXML Label label_trainee_bank;
		
		private @FXML Label label_trainee_factory;
		
		private @FXML Label label_trainee_number;
		
		private @FXML Label label_trainee_eachsum;
		
		private @FXML Label label_search_traineegroup;
		
		private @FXML Label label_search_traineecenter;
		
		private @FXML Label label_trainee_num;
		
		private @FXML Label label_trainee_acc;
		
		private @FXML CheckBox checkbox_trainee_acc;
		
		private @FXML TextField textfield_trainee_num;
		
		private @FXML TextField textfield_search_traineeid;
		
		private @FXML TextField textfield_search_traineename;
		
		private @FXML TextField textfield_search_traineegroup;
		
		private @FXML TextField textfield_search_traineecenter;
		
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
		
		private @FXML TextField textfield_trainee_sum;
		
		private @FXML TextField textfield_trainee_eachsum;
		
		private @FXML TextField textfield_trainee_bank;
		
		private @FXML TextField textfield_trainee_factory;
		
		private @FXML TextField textfield_trainee_chek1;
		
		private @FXML TextField textfield_trainee_chek2;
		
		private @FXML TextField textfield_trainee_chek3;
		
		private @FXML TextField textfield_trainee_chek4;
		
		private @FXML TextField textfield_trainee_chek5;
		
		private @FXML TextField textfield_trainee_chek6;
		
		private @FXML TextField textfield_trainee_chek7;
		
		private @FXML TextField textfield_trainee_chek8;
		
		private @FXML TextField textfield_trainee_chek9;
		
		private @FXML TextField textfield_trainee_chek10;
		
		private @FXML TextField textfield_trainee_chek11;
		
		private @FXML TextField textfield_trainee_chek12;
		
		private @FXML TextArea textarea_trainee_comments;
		
		private @FXML ComboBox<String> combobox_trainee_gender;
		
		private @FXML ComboBox<String> combobox_trainee_center;
		
		private @FXML ComboBox<String> combobox_trainee_level;
		
		private @FXML ComboBox<String> combobox_trainee_group;
				
		private @FXML ComboBox<Integer> combobox_trainee_paymentnum;
		
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
		
		private @FXML ImageView image_trainee_mainimage;
		
		private @FXML RadioButton radio_trainee_chekpayment;
		
		private @FXML RadioButton radio_trainee_cashpayment;
		
		private @FXML RadioButton radio_trainee_creditpayment;
		
		String correct_id;
		
		String main_image;
		

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
		
		private @FXML Button button_search_list;
			
		private @FXML TextField textfield_list_males;
		
		private @FXML TextField textfield_list_females;
		
		private @FXML TextField textfield_search_listname;
		
		private @FXML TextField textfield_search_listtrainername;
		
		private @FXML ComboBox<String> combobox_list_center;
		
		private @FXML ComboBox<String> combobox_list_trainers;
		
		private @FXML Label label_list_females;
		
		private @FXML Label label_list_males;
		
		private @FXML Label label_list_trainer;
		
		private @FXML Label label_list_center;
		
		private @FXML Label label_listsearch;
		
		private @FXML Label label_search_listname;
		
		private @FXML Label label_search_listtrainer;
		
		private ListsRow correct_list;
		
//-------------------------------------------------------------end->Lists anchor pane variables-------------------------------------------//

//-------------------------------------------------------------Group anchor pane variables-------------------------------------------//
		private @FXML AnchorPane anchorpane_groups;
		
		private @FXML TableView<GroupsRow> groups_table;
			
		private @FXML TableColumn<GroupsRow,String> tablecolumn_group_group;
		
		private @FXML TableColumn<GroupsRow,String> tablecolumn_group_activity_time;
		
		private @FXML TableColumn<GroupsRow,String> tablecolumn_group_trainer;
		
		private @FXML TableColumn<GroupsRow,String> tablecolumn_group_males;
		
		private @FXML TableColumn<GroupsRow,String> tablecolumn_group_females;
		
		private @FXML Button button_add_group;
		
		private @FXML Button button_back_group;
		
		private @FXML Button button_delete_group;
		
		private @FXML Button button_search_group;
		
		private @FXML Label label_search_groupcenter;
		
		private @FXML Label label_search_grouptrainer;
		
		private @FXML Label label_groupsearch;
		
		private @FXML Label label_group_center;
		
		private @FXML Label label_group_name;
		
		private @FXML Label label_group_day;
		
		private @FXML Label label_group_trainer;
		
		private @FXML Label label_group_start;
		
		private @FXML Label label_group_end;
		
		private @FXML Label label_group_males;
		
		private @FXML Label label_group_females;
		
		private @FXML TextField textfield_group_name;
		
		private @FXML TextField textfield_group_males;
		
		private @FXML TextField textfield_group_females;
		
		private @FXML TextField textfield_search_groupcenter;
		
		private @FXML TextField textfield_search_grouptrainer;
		
		private @FXML RadioButton radio_group_add;
		
		private @FXML ComboBox<String> combobox_group_center;
		
		private @FXML ComboBox<String> combobox_group_trainer;
		
		private @FXML ComboBox<String> combobox_group_days;
		
		private @FXML ComboBox<String> combobox_group_days2;
		
		private @FXML Spinner<LocalTime>spinner_group_start;
		
		private @FXML Spinner<LocalTime>spinner_group_end;
		
		private @FXML Spinner<LocalTime>spinner_group_start2;
		
		private @FXML Spinner<LocalTime>spinner_group_end2;
		

		private ObservableList<GroupsRow> group_list = FXCollections.observableArrayList();
		
		private ObservableList<String> group_center_list = FXCollections.observableArrayList();
		
		private ObservableList<String> group_days_list = FXCollections.observableArrayList();

		private ObservableList<String> group_trainer_list = FXCollections.observableArrayList();
		
		private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		
		private GroupsRow correct_group;
		

//-------------------------------------------------------------end->Group anchor pane variables-------------------------------------------//
//-------------------------------------------------------------Payments anchor pane variables-------------------------------------------//
		private @FXML AnchorPane anchorpane_payment;

		private @FXML TableView<GroupsRow> payment_table;
		
		private @FXML TableColumn<GroupsRow,String> tablecolumn_payment_name;
		
		private @FXML TableColumn<GroupsRow,String> tablecolumn_payment_lastname;
		
		private @FXML TableColumn<GroupsRow,String> tablecolumn_payment_start_date;
		
//----------------------------------------------------------------------------------------------------------------//
		private @FXML AnchorPane anchorpane_expiried;
		
		private ObservableList<TraineesRow> expiried_row = FXCollections.observableArrayList();
		
		private @FXML TableView<TraineesRow> expiried_table;
		
		private @FXML TableColumn<TraineesRow,String> tablecolumn_expiried_id;
		
		private @FXML TableColumn<TraineesRow,String> tablecolumn_expiried_name;
		
		private @FXML TableColumn<TraineesRow,String> tablecolumn_expiried_last_name;
		
		private @FXML TableColumn<TraineesRow,String> tablecolumn_expiried_phone;
		
		private @FXML TableColumn<TraineesRow,String> tablecolumn_expiried_gender;
		
		private @FXML TableColumn<TraineesRow,String> tablecolumn_expiried_center;
		
		private @FXML TableColumn<TraineesRow,String> tablecolumn_expiried_group;
		
		private @FXML TableColumn<TraineesRow,String> tablecolumn_expiried_register_date;
		
		private @FXML TableColumn<TraineesRow,String> tablecolumn_expiried_subscription_end_date;
		
//anchor pane level
		private @FXML AnchorPane anchorpane_level;
		
		private @FXML TableView<LevelsRow> levels_table;
		
		private @FXML TableColumn<LevelsRow,String> tablecolumn_level_name;
		
		private @FXML TableColumn<LevelsRow,String> tablecolumn_level_date;
		
		private @FXML TableColumn<LevelsRow,String> tablecolumn_level_number;
		
		private @FXML Button button_add_level;
		
		private @FXML Button button_back_level;
		
		private @FXML Label label_level_level;
		
		private @FXML Label label_level_date;
		
		private @FXML Label label_level_number;
		
		private @FXML TextField textfield_level_number;
		
		private @FXML ComboBox<String> combobox_level_levels;
		
		private @FXML DatePicker datepicker_level_date;
		
		private ObservableList<LevelsRow> levels_list = FXCollections.observableArrayList();
		
		public LevelsRow correct_level;
		
//end--> anchor pane level		
//---------------------------------------------------------DataBase Fields------------------------------------------
	Connection conn;	Statement s;
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
		LevelTableInitalize();
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
		String path="";
        try
        {
        	//------------------Open Connection to Access Data Base--------------------------------	
        		//path.substring(0, path.indexOf("Avitan-Dojo"))+"Database.accdb" enter this into path;	
        		//jdbc:ucanaccess://C:\\Users\\Dolev\\Desktop\\Management\\src\\Database.accdb"
        	Class.forName(MY_SQL_DRIVER_NAME).newInstance();
        	path = this.getClass().getResource("").toString().substring(9);
        	path =path.substring(0, path.indexOf("Avitan-Dojo"))+"Database.accdb";
        		conn=DriverManager.getConnection(
        			"jdbc:ucanaccess://" + path);
        			 s = conn.createStatement();
        }
        			catch(Exception ex)
        	        {
        	            ex.printStackTrace();
        	            showAlertMessage(path, AlertType.ERROR);
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
		view = getClass().getResourceAsStream("/boundaries/images/unknow.jpg");
		if (view != null) {
			Image = new Image(view);
			image_trainee_mainimage.setImage(Image);
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
	
	public void InitalizeExpiriedTable()
	{
		tablecolumn_expiried_id.setCellValueFactory(new PropertyValueFactory<TraineesRow, String>("id"));
		tablecolumn_expiried_name.setCellValueFactory(new PropertyValueFactory<TraineesRow, String>("name"));
		tablecolumn_expiried_last_name.setCellValueFactory(new PropertyValueFactory<TraineesRow, String>("last_name"));
		tablecolumn_expiried_center.setCellValueFactory(new PropertyValueFactory<TraineesRow, String>("center"));
		tablecolumn_expiried_group.setCellValueFactory(new PropertyValueFactory<TraineesRow, String>("group"));
		tablecolumn_expiried_register_date.setCellValueFactory(new PropertyValueFactory<TraineesRow, String>("register_date"));
		tablecolumn_expiried_subscription_end_date.setCellValueFactory(new PropertyValueFactory<TraineesRow,String>("subscription_end_date"));
		String command="select * from Trainees";
		String names="";
		expiried_row.clear();
		try {
			s.execute(command);
			ResultSet set=s.getResultSet();
			while((set!=null) && (set.next()))
			{
				if(DateCompare(set.getString(19), s_dateForamt.format(LocalDate.now())))
				{	
			   TraineesRow temp=new TraineesRow(set.getString(13), set.getString(1), set.getString(2), set.getString(10),set.getString(11),set.getString(18),set.getString(19));
			   expiried_row.add(temp);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		expiried_table.setItems(expiried_row);
		command="select * from Trainees where subscription_end_date = "+"\""+s_dateForamt.format(LocalDate.now())+"\"";
		try {
			s.execute(command);
			ResultSet set=s.getResultSet();
			while((set!=null) && (set.next()))
			{
				names= names+ "\n" + set.getString(1)+" "+ set.getString(2);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(names!="")
		{
			showAlertMessage("המנויים שנגמרו היום הם:"+ "\n"+names, AlertType.INFORMATION);
		}
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
	
	public void InitializeGroupTable()
	{
		tablecolumn_group_trainer.setCellValueFactory(new PropertyValueFactory<GroupsRow, String>("trainer"));
		tablecolumn_group_group.setCellValueFactory(new PropertyValueFactory<GroupsRow, String>("group"));
		tablecolumn_group_activity_time.setCellValueFactory(new PropertyValueFactory<GroupsRow, String>("activity_time"));
		tablecolumn_group_males.setCellValueFactory(new PropertyValueFactory<GroupsRow, String>("males"));
		tablecolumn_group_females.setCellValueFactory(new PropertyValueFactory<GroupsRow, String>("females"));
		list_row = FXCollections.observableArrayList();
		Platform.runLater(() -> {
			lists_table.refresh();
		});
	}

	public void InitializeTimeSpinner()
	{

		SpinnerValueFactory<LocalTime> value1=new SpinnerValueFactory<LocalTime>() {
			{
				 setConverter(new LocalTimeStringConverter(formatter,null));
			}
			@Override
	        public void decrement(int steps) {
	            if (getValue() == null)
	                setValue(LocalTime.now());
	            else {
	                LocalTime time = (LocalTime) getValue();
	                setValue(time.minusMinutes(steps));
	            }
	        }

	        @Override
	        public void increment(int steps) {
	            if (this.getValue() == null)
	                setValue(LocalTime.now());
	            else {
	                LocalTime time = (LocalTime) getValue();
	                setValue(time.plusMinutes(steps));
	            }
	        }
			
				};
		SpinnerValueFactory<LocalTime> value2=new SpinnerValueFactory<LocalTime>() {
					{
						 setConverter(new LocalTimeStringConverter(formatter,null));
					}
					@Override
			        public void decrement(int steps) {
			            if (getValue() == null)
			                setValue(LocalTime.now());
			            else {
			                LocalTime time = (LocalTime) getValue();
			                setValue(time.minusMinutes(steps));
			            }
			        }

			        @Override
			        public void increment(int steps) {
			            if (this.getValue() == null)
			                setValue(LocalTime.now());
			            else {
			                LocalTime time = (LocalTime) getValue();
			                setValue(time.plusMinutes(steps));
			            }
			        }
					
						};		
		SpinnerValueFactory<LocalTime> value3=new SpinnerValueFactory<LocalTime>() {
							{
								 setConverter(new LocalTimeStringConverter(formatter,null));
							}
							@Override
					        public void decrement(int steps) {
					            if (getValue() == null)
					                setValue(LocalTime.now());
					            else {
					                LocalTime time = (LocalTime) getValue();
					                setValue(time.minusMinutes(steps));
					            }
					        }

					        @Override
					        public void increment(int steps) {
					            if (this.getValue() == null)
					                setValue(LocalTime.now());
					            else {
					                LocalTime time = (LocalTime) getValue();
					                setValue(time.plusMinutes(steps));
					            }
					        }
							
								};
		SpinnerValueFactory<LocalTime> value4=new SpinnerValueFactory<LocalTime>() {
									{
										 setConverter(new LocalTimeStringConverter(formatter,null));
									}
									@Override
							        public void decrement(int steps) {
							            if (getValue() == null)
							                setValue(LocalTime.now());
							            else {
							                LocalTime time = (LocalTime) getValue();
							                setValue(time.minusMinutes(steps));
							            }
							        }

							        @Override
							        public void increment(int steps) {
							            if (this.getValue() == null)
							                setValue(LocalTime.now());
							            else {
							                LocalTime time = (LocalTime) getValue();
							                setValue(time.plusMinutes(steps));
							            }
							        }
									
										};
		spinner_group_start.setEditable(true);
		spinner_group_end.setEditable(true);
		spinner_group_start2.setEditable(true);
		spinner_group_end2.setEditable(true);
		spinner_group_start.setValueFactory(value1);
		spinner_group_end.setValueFactory(value2);
		spinner_group_start2.setValueFactory(value3);
		spinner_group_end2.setValueFactory(value4);
	}
//---------------------------------------------------------------end->Initialize Functions-------------------------------------------------//

//------------------------------------------------------------------Override Functions---------------------------------------------//


	
	public void GroupTabInitialize()
	{
		groups_table.setRowFactory(param -> {
			TableRow<GroupsRow> tableRow = new TableRow<>();
			tableRow.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2 && (!tableRow.isEmpty())) {
					 GroupsRow rowData = tableRow.getItem();

					if (rowData.getCenter() == " ") {
						return;
					}
					correct_group=rowData;
					SetVisableEditGroupTab(rowData);
				}

			});
			return tableRow;
		});
	}
	
	public void InitializeLevelsTab()
	{
		tablecolumn_level_name.setCellValueFactory(new PropertyValueFactory<LevelsRow, String>("level"));
		tablecolumn_level_date.setCellValueFactory(new PropertyValueFactory<LevelsRow, String>("date"));
		tablecolumn_level_number.setCellValueFactory(new PropertyValueFactory<LevelsRow, String>("number"));
		trainer_row = FXCollections.observableArrayList();		
	}
	
	public void LevelTableInitalize()
	{
		levels_table.setRowFactory(param -> {
			TableRow<LevelsRow> tableRow = new TableRow<>();
			tableRow.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2 && (!tableRow.isEmpty())) {
					 LevelsRow rowData = tableRow.getItem();

					if (rowData.getId() == " ") {
						return;
					}
					correct_level=rowData;
					String command="delete from Levels where id = "+"\""+correct_level.getId()+"\""+"and level = "+"\""+correct_level.getLevel()+"\""+" and level_date = "+"\""+correct_level.getDate()+"\""+
							" and level_info = "+"\""+correct_level.getNumber()+"\"";
					try {
						s.execute(command);
						LevelsButtonClick();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return;
					}
					
				}

			});
			return tableRow;
		});
	}
	

	//---------------------------------------------------------------------------------------------------------------------------------------//	
	
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
				anchorpane_groups.setVisible(false);
				anchorpane_expiried.setVisible(false);
				anchorpane_level.setVisible(false);
			break;

			case "מרכזים":
				anchorpane_trainers.setVisible(false);
				anchorpane_centers.setVisible(true);
				anchorpane_lists.setVisible(false);
				SetCenterTabInVisable();
				anchorpane_trainee.setVisible(false);
				anchorpane_groups.setVisible(false);
				anchorpane_expiried.setVisible(false);
				anchorpane_level.setVisible(false);

			break;

			case "רשימות":
					anchorpane_trainers.setVisible(false);
					anchorpane_centers.setVisible(false);
					anchorpane_lists.setVisible(true);
					anchorpane_trainee.setVisible(false);
					anchorpane_groups.setVisible(false);
					anchorpane_expiried.setVisible(false);
					anchorpane_level.setVisible(false);
					InitializeListsTable();
					ListsTableInitialize();
			
			break;	
			
			case "ספורטאים":
				anchorpane_trainers.setVisible(false);
				anchorpane_centers.setVisible(false);
				anchorpane_lists.setVisible(false);
				anchorpane_groups.setVisible(false);
				anchorpane_trainee.setVisible(true);
				anchorpane_expiried.setVisible(false);
				anchorpane_level.setVisible(false);
				TraineeTabInitialize();
				SetPaymentTabInvisable();
				SetTraineeTabInVisable();
				break;
			
			case "פעילויות":
				anchorpane_trainers.setVisible(false);
				anchorpane_centers.setVisible(false);
				anchorpane_lists.setVisible(false);
				anchorpane_trainee.setVisible(false);
				anchorpane_groups.setVisible(true);
				anchorpane_expiried.setVisible(false);
				anchorpane_level.setVisible(false);
				InitializeGroupTable();
				SetGroupTabInVisable();
				InitializeTimeSpinner();
				GroupTabInitialize();
				break;
			case "פג תוקף":
					anchorpane_trainers.setVisible(false);
					anchorpane_centers.setVisible(false);
					anchorpane_lists.setVisible(false);
					anchorpane_trainee.setVisible(false);
					anchorpane_groups.setVisible(false);
					anchorpane_expiried.setVisible(true);
					anchorpane_level.setVisible(false);
					InitalizeExpiriedTable();
				break;
			default:
				return false;
		}
		return true;
	}
	
	
	public String substring(String string)
	{
		int index=string.indexOf("ggggggg");
		string.substring(0, index);
		return string;
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
					command="select * from "+table_name+ " where "+title+ " = " + "\""+paramter+"\"";
					break;
		case GetAll:
					command="select * from "+table_name;
					break;
		case Delete:
					command="delete from "+table_name+ " where "+title+ " = " + "\"" + paramter + "\"";
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
		label_search.setVisible(false);
		button_search_trainer.setVisible(false);;
		label_search_name.setVisible(false);;
		label_search_lastname.setVisible(false);
		textfield_search_name.setVisible(false);
		textfield_search_lastname.setVisible(false);
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
		label_search.setVisible(true);
		button_search_trainer.setVisible(true);;
		label_search_name.setVisible(true);;
		label_search_lastname.setVisible(true);
		textfield_search_name.setVisible(true);
		textfield_search_lastname.setVisible(true);
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
	
	
	@FXML
	public void SearchTrainerButtonClick(ActionEvent event)
	{
		String command="";
		if(textfield_search_name.getText().equals("")&&textfield_search_lastname.getText().equals(""))
		{
			command="select * from Trainers";
		}
		else if(textfield_search_name.getText().equals(""))
			{
			 command="select * from Trainers where lastname = " +"\"" +textfield_search_lastname.getText()+"\"";
			}
			else
				if(textfield_search_lastname.getText().equals(""))
				{
					command="select * from Trainers where name = " + "\""+textfield_search_name.getText()+"\"";
				}
				else
				{
					command="select * from Trainers where name = " + "\""+textfield_search_name.getText()+"\""+" "+"And lastname = "+"\""+textfield_search_lastname.getText()+"\"";
				}
			try {
				s.execute(command);
				ResultSet set=s.getResultSet();
			
				for ( int i = 0; i<trainier_table.getItems().size(); i++) {
					trainier_table.getItems().clear();
				}
			
				while((set!=null) && (set.next()))
				{
					TrainersRow temp=new TrainersRow(set.getString(1), set.getString(2), set.getString(8));
					trainer_row.add(temp);
				}
				trainier_table.setItems(trainer_row);
				trainier_table.refresh();
			
			} catch (SQLException e) {
				showAlertMessage("המאמן לא נימצא במערכת", AlertType.INFORMATION);
				e.printStackTrace();
				return;
			}
		
	}
	
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
		label_search_center.setVisible(false);
		label_search_centername.setVisible(false);
		textfield_search_centername.setVisible(false);
		button_search_center.setVisible(false);
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
		label_search_center.setVisible(true);
		label_search_centername.setVisible(true);
		textfield_search_centername.setVisible(true);
		button_search_center.setVisible(true);
		button_delete_center.setVisible(false);
		button_back_center.setVisible(false);
		button_add_center.setLayoutX(420);
		button_add_center.setText("הוסף מרכז חדש");
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
	@FXML
	public void SearchCenterButtonClick(ActionEvent event)
	{
		String command="";
		if(textfield_search_centername.getText().equals(""))
		{
			command="select * from Centers";
		}
		else 
					command="select * from Centers where classname = " + "\""+textfield_search_centername.getText()+"\"";
			try {
				s.execute(command);
				ResultSet set=s.getResultSet();
			
				for ( int i = 0; i<center_table.getItems().size(); i++) {
					center_table.getItems().clear();
				}
			
				while((set!=null) && (set.next()))
				{
					CenterRow temp=new CenterRow(set.getInt(4), set.getString(3), set.getString(1), set.getString(2));
					center_row.add(temp);
				}
				center_table.setItems(center_row);
				center_table.refresh();
			
			} catch (SQLException e) {
				showAlertMessage("המרכז לא נמצא במערכת", AlertType.INFORMATION);
				e.printStackTrace();
				return;
			}
	}
	
	
	//Trainee Functions

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
				GetFromDataBase("Trainees", DataBaseAction.Delete, "id", correct_id);
				if(checkbox_trainee_acc.isSelected())
				{
				String command= "INSERT into Trainees(id,name,lastname,birthday,city,address,postcode,phone,cellphone,email,groupp,gender,center,level,hight,weight,register_date,subscription_end_date,image,num,acc,commnts) "+
						   "VALUES('"+textfield_trainee_id.getText()+"','"+textfield_trainee_name.getText()+"','"+textfield_trainee_lastname.getText()+"','"+textfield_trainee_birthday.getText()+
						   "','"+textfield_trainee_city.getText()+"','"+textfield_trainee_address.getText()+"','"+textfield_trainee_postcode.getText()+
						   "','"+textfield_trainee_phone.getText()+"','"+textfield_trainee_cellphone.getText()+"','"+textfield_trainee_email.getText()+
						   "','"+combobox_trainee_group.getValue()+"','"+combobox_trainee_gender.getValue()+"','"+combobox_trainee_center.getValue()+
						   "','"+combobox_trainee_level.getValue()+ "','"+textfield_trainee_hight.getText()+ "','"+textfield_trainee_weight.getText()+
						   "','"+s_dateForamt.format(datepicker_trainee_start.getValue())+"','"+s_dateForamt.format(datepicker_trainee_end.getValue())+
						   "','"+main_image+"','"+textfield_trainee_num.getText()+"','"+"true"+"','"+textarea_trainee_comments.getText()+"')";
				try {
					s.execute(command);
					} catch (SQLException e) {
					e.printStackTrace();
					}
				SetTraineeTabInVisable();
				}
				else
				{
					String command= "INSERT into Trainees(id,name,lastname,birthday,city,address,postcode,phone,cellphone,email,groupp,gender,center,level,hight,weight,register_date,subscription_end_date,image,num,acc,commnts) "+
							   "VALUES('"+textfield_trainee_id.getText()+"','"+textfield_trainee_name.getText()+"','"+textfield_trainee_lastname.getText()+"','"+textfield_trainee_birthday.getText()+
							   "','"+textfield_trainee_city.getText()+"','"+textfield_trainee_address.getText()+"','"+textfield_trainee_postcode.getText()+
							   "','"+textfield_trainee_phone.getText()+"','"+textfield_trainee_cellphone.getText()+"','"+textfield_trainee_email.getText()+
							   "','"+combobox_trainee_group.getValue()+"','"+combobox_trainee_gender.getValue()+"','"+combobox_trainee_center.getValue()+
							   "','"+combobox_trainee_level.getValue()+ "','"+textfield_trainee_hight.getText()+ "','"+textfield_trainee_weight.getText()+
							   "','"+s_dateForamt.format(datepicker_trainee_start.getValue())+"','"+s_dateForamt.format(datepicker_trainee_end.getValue())+
							   "','"+main_image+"','"+textfield_trainee_num.getText()+"','"+"false"+"','"+textarea_trainee_comments.getText()+"')";
					try {
						s.execute(command);
						} catch (SQLException e) {
						e.printStackTrace();
						}
					SetTraineeTabInVisable();
				}
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
					if(checkbox_trainee_acc.isSelected())
					{
					String command= "INSERT into Trainees(id,name,lastname,birthday,city,address,postcode,phone,cellphone,email,groupp,gender,center,level,hight,weight,register_date,subscription_end_date,image,num,acc,commnts) "+
							   "VALUES('"+textfield_trainee_id.getText()+"','"+textfield_trainee_name.getText()+"','"+textfield_trainee_lastname.getText()+"','"+textfield_trainee_birthday.getText()+
							   "','"+textfield_trainee_city.getText()+"','"+textfield_trainee_address.getText()+"','"+textfield_trainee_postcode.getText()+
							   "','"+textfield_trainee_phone.getText()+"','"+textfield_trainee_cellphone.getText()+"','"+textfield_trainee_email.getText()+
							   "','"+combobox_trainee_group.getValue()+"','"+combobox_trainee_gender.getValue()+"','"+combobox_trainee_center.getValue()+
							   "','"+combobox_trainee_level.getValue()+ "','"+textfield_trainee_hight.getText()+ "','"+textfield_trainee_weight.getText()+
							   "','"+s_dateForamt.format(datepicker_trainee_start.getValue())+"','"+s_dateForamt.format(datepicker_trainee_end.getValue())+"','"+main_image+
							   "','"+textfield_trainee_num.getText()+"','"+"true"+"','"+textarea_trainee_comments.getText()+"')";
					try {
						s.execute(command);
						} catch (SQLException e) {
						e.printStackTrace();
						}
					SetTraineeTabInVisable();
					}
					else
					{
						String command= "INSERT into Trainees(id,name,lastname,birthday,city,address,postcode,phone,cellphone,email,groupp,gender,center,level,hight,weight,register_date,subscription_end_date,image,num,acc,commnts) "+
								   "VALUES('"+textfield_trainee_id.getText()+"','"+textfield_trainee_name.getText()+"','"+textfield_trainee_lastname.getText()+"','"+textfield_trainee_birthday.getText()+
								   "','"+textfield_trainee_city.getText()+"','"+textfield_trainee_address.getText()+"','"+textfield_trainee_postcode.getText()+
								   "','"+textfield_trainee_phone.getText()+"','"+textfield_trainee_cellphone.getText()+"','"+textfield_trainee_email.getText()+
								   "','"+combobox_trainee_group.getValue()+"','"+combobox_trainee_gender.getValue()+"','"+combobox_trainee_center.getValue()+
								   "','"+combobox_trainee_level.getValue()+ "','"+textfield_trainee_hight.getText()+ "','"+textfield_trainee_weight.getText()+
								   "','"+s_dateForamt.format(datepicker_trainee_start.getValue())+"','"+s_dateForamt.format(datepicker_trainee_end.getValue())+"','"+main_image+
								   "','"+textfield_trainee_num.getText()+"','"+"false"+"','"+textarea_trainee_comments.getText()+"')";
						try {
							s.execute(command);
							} catch (SQLException e) {
							e.printStackTrace();
							}
						SetTraineeTabInVisable();
					}
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
		GetFromDataBase("Payment", DataBaseAction.Delete, "id", textfield_trainee_id.getText());
		SetTraineeTabInVisable();
		
	}
	
	public void SetTraineeTabVisable()
	{
		trainee_table.setVisible(false);
		label_search_traineeid.setVisible(false);
		label_search_traineename.setVisible(false);
		label_traineesearch.setVisible(false);
		textfield_search_traineename.setVisible(false);
		textfield_search_traineeid.setVisible(false);
		button_search_trainee.setVisible(false);
		button_payment_trainee.setVisible(true);
		button_back_trainee.setVisible(true);
		button_add_trainee.setLayoutX(310);
		button_add_trainee.setLayoutY(519);
		button_delete_trainee.setVisible(true);
		button_search_traineecenter.setVisible(false);
		button_print_trainee.setVisible(false);
		button_add_image.setVisible(true);
		button_level_trainee.setVisible(true);
		button_print_traineeinfo.setVisible(true);
		label_trainee_num.setVisible(true);
		label_trainee_acc.setVisible(true);
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
		label_search_traineecenter.setVisible(false);
		label_search_traineegroup.setVisible(false);
		checkbox_trainee_acc.setVisible(true);
		textfield_trainee_num.setVisible(true);
		textfield_search_traineecenter.setVisible(false);
		textfield_search_traineegroup.setVisible(false);
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
		image_trainee_mainimage.setVisible(true);
		InputStream view = getClass().getResourceAsStream("/boundaries/images/unknow.jpg");
		if (view != null) {
			Image Image = new Image(view);
			image_trainee_mainimage.setImage(Image);
		}
	}
	
	public void SetTraineeTabInVisable()
	{
		trainee_table.setVisible(true);
		label_search_traineeid.setVisible(true);
		label_search_traineename.setVisible(true);
		label_traineesearch.setVisible(true);
		textfield_search_traineename.setVisible(true);
		textfield_search_traineeid.setVisible(true);
		button_search_trainee.setVisible(true);
		button_back_trainee.setVisible(false);
		button_add_trainee.setLayoutX(420);
		button_add_trainee.setLayoutY(368);
		button_add_trainee.setText("הוסף ספורטאי חדש");
		button_delete_trainee.setVisible(false);
		button_payment_trainee.setVisible(false);
		button_print_traineeinfo.setVisible(false);
		button_add_image.setVisible(false);
		button_level_trainee.setVisible(false);
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
		label_search_traineecenter.setVisible(true);
		label_search_traineegroup.setVisible(true);
		label_trainee_num.setVisible(false);
		label_trainee_acc.setVisible(false);
		checkbox_trainee_acc.setVisible(false);
		textfield_trainee_num.setVisible(false);
		textfield_search_traineecenter.setVisible(true);
		textfield_search_traineegroup.setVisible(true);
		button_search_traineecenter.setVisible(true);
		button_print_trainee.setVisible(true);
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
		image_trainee_mainimage.setVisible(false);
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
		checkbox_trainee_acc.setSelected(false);
		textfield_trainee_num.clear();
		TraineeTabInitialize();
		InputStream view = getClass().getResourceAsStream("/boundaries/images/unknow.jpg");
		if (view != null) {
			Image Image = new Image(view);
			image_trainee_mainimage.setImage(Image);
			main_image="/boundaries/images/unknow.jpg";
		}
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
		correct_id=trainee.getId();
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
				main_image=set.getString(20);
				if(set.getString(22).equals("true"))
				{
					checkbox_trainee_acc.setSelected(true);
				}
				textfield_trainee_num.setText(set.getString(21));
				if(set.getString(20)!=null||set.getString(18).equals(""))
				{
					try {
					Image img = new Image(set.getString(20));
	    			image_trainee_mainimage.setImage(img);
					}catch(Exception e)
					{
						InputStream view = getClass().getResourceAsStream("/boundaries/images/unknow.jpg");
						if (view != null) {
							Image Image = new Image(view);
							image_trainee_mainimage.setImage(Image);
						}
					}
				}
				else
				{
					InputStream view = getClass().getResourceAsStream("/boundaries/images/unknow.jpg");
					if (view != null) {
						Image Image = new Image(view);
						image_trainee_mainimage.setImage(Image);
					}
				}
			}
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}	
	public void SetTraineegroupCombobox()
	{
		ResultSet set=GetFromDataBase("Groups", DataBaseAction.GetAll, null, null);
		trainee_group_list.clear();
		try {
			while((set!=null) && (set.next()))
			{
				if(set.getString(4).equals(combobox_trainee_center.getValue()))
				{
					trainee_group_list.add(set.getString(3));
				}
			     
			}
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}

	}
	
	@FXML
	public void ComboBoxTraineeCenterSelect(ActionEvent event)
	{
		if(combobox_trainee_center.getValue()!=null)
		{
			SetTraineegroupCombobox();
		}
	}
	@FXML
	public void SearchTraineeButtonClick(ActionEvent event)
	{
		String command="";
		if(textfield_search_traineeid.getText().equals("")&&textfield_search_traineename.getText().equals(""))
		{
			command="select * from Trainees";
		}
		else if(textfield_search_traineeid.getText().equals(""))
			{
			 command="select * from Trainees where name = " +"\"" +textfield_search_traineename.getText()+"\"";
			}
			else
				if(textfield_search_traineename.getText().equals(""))
				{
					command="select * from Trainees where id = " + "\""+textfield_search_traineeid.getText()+"\"";
				}
				else
				{
					command="select * from Trainees where id = " + "\""+textfield_search_traineeid.getText()+"\""+" "+"And name = "+"\""+textfield_search_traineename.getText()+"\"";
				}
			try {
				s.execute(command);
				ResultSet set=s.getResultSet();
			
				for ( int i = 0; i<trainee_table.getItems().size(); i++) {
					trainee_table.getItems().clear();
				}
			
				while((set!=null) && (set.next()))
				{
					TraineesRow temp=new TraineesRow(set.getString(13), set.getString(1), set.getString(2), set.getString(10),set.getString(11),set.getString(18),set.getString(19));
					trainee_row.add(temp);
				}
				trainee_table.setItems(trainee_row);
				trainee_table.refresh();
			
			} catch (SQLException e) {
				showAlertMessage("המאמן לא נימצא במערכת", AlertType.INFORMATION);
				e.printStackTrace();
				return;
			}
	}
	@FXML
	public void SetPaymentTabVisable(ActionEvent event)
	{
		PaymentRow row;
		String tempid;
		if(textfield_trainee_id.isVisible())
		{
		button_payment_trainee.setLayoutX(450);
		button_payment_trainee.setText("הוסף פרטי תשלום"); 
		button_back_trainee.setVisible(false);
		button_add_trainee.setVisible(false);
		button_delete_trainee.setVisible(false);
		button_level_trainee.setVisible(false);
		button_paymentback_trainee.setVisible(true);
		button_add_image.setVisible(false);
		button_print_traineeinfo.setVisible(false);
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
		label_trainee_num.setVisible(false);
		label_trainee_acc.setVisible(false);
		checkbox_trainee_acc.setVisible(false);
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
		image_trainee_mainimage.setVisible(false);
		textfield_trainee_num.setVisible(false);
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
		textarea_trainee_comments.setVisible(false);
		combobox_trainee_gender.setVisible(false);
		combobox_trainee_center.setVisible(false);
		combobox_trainee_level.setVisible(false);
		combobox_trainee_group.setVisible(false);
		datepicker_trainee_start.setVisible(false);
		datepicker_trainee_end.setVisible(false);
		textfield_trainee_id.setEditable(true);
		label_trainee_sum.setVisible(true);
		textfield_trainee_sum.setVisible(true);
		trainee_paymentway_list.clear();
		trainee_paymentway_list.add("מזומן");
		trainee_paymentway_list.add("אשראי");
		trainee_paymentway_list.add("צקים");
		radio_trainee_cashpayment.setVisible(true);
		radio_trainee_chekpayment.setVisible(true);
		radio_trainee_creditpayment.setVisible(true);
			if(button_add_trainee.getText().equals("ערוך ספורטאי"))
			{
				String commend="select * from Payment where id = "+"\""+textfield_trainee_id.getText()+"\"";
				try {
					s.execute(commend);
					ResultSet rs=s.getResultSet();
					while((rs!=null) && (rs.next()))
					{
						if(rs.getString(16).equals("cash"))
						{
							textfield_trainee_sum.setText(rs.getString(17));
							radio_trainee_cashpayment.setSelected(true);
						}
						else if(rs.getString(16).equals("creditcard"))
						{
							radio_trainee_creditpayment.setSelected(true);
							RadioTraineeCreditPaymentClick();
							textfield_trainee_sum.setText(rs.getString(17));
							switch(rs.getString(18))
							{
							case "1":
									combobox_trainee_paymentnum.setValue(1);
									textfield_trainee_eachsum.setText(Integer.toString(Integer.parseInt(rs.getString(17))/1));
									break;
							case "2":
								combobox_trainee_paymentnum.setValue(2);
								textfield_trainee_eachsum.setText(Integer.toString(Integer.parseInt(rs.getString(17))/2));
								break;
							case "3":
								combobox_trainee_paymentnum.setValue(3);
								textfield_trainee_eachsum.setText(Integer.toString(Integer.parseInt(rs.getString(17))/3));
								break;	
							case "4":
								combobox_trainee_paymentnum.setValue(4);
								textfield_trainee_eachsum.setText(Integer.toString(Integer.parseInt(rs.getString(17))/4));
								break;	
							case "5":
								combobox_trainee_paymentnum.setValue(5);
								textfield_trainee_eachsum.setText(Integer.toString(Integer.parseInt(rs.getString(17))/5));
								break;	
							case "6":
								combobox_trainee_paymentnum.setValue(6);
								textfield_trainee_eachsum.setText(Integer.toString(Integer.parseInt(rs.getString(17))/6));
								break;	
							case "7":
								combobox_trainee_paymentnum.setValue(7);
								textfield_trainee_eachsum.setText(Integer.toString(Integer.parseInt(rs.getString(17))/7));
								break;	
							case "8":
								combobox_trainee_paymentnum.setValue(8);
								textfield_trainee_eachsum.setText(Integer.toString(Integer.parseInt(rs.getString(17))/8));
								break;	
							case "9":
								combobox_trainee_paymentnum.setValue(9);
								textfield_trainee_eachsum.setText(Integer.toString(Integer.parseInt(rs.getString(17))/9));
								break;	
							case "10":
								combobox_trainee_paymentnum.setValue(10);
								textfield_trainee_eachsum.setText(Integer.toString(Integer.parseInt(rs.getString(17))/10));
								break;	
							case "11":
								combobox_trainee_paymentnum.setValue(11);
								textfield_trainee_eachsum.setText(Integer.toString(Integer.parseInt(rs.getString(17))/11));
								break;	
							case "12":
								combobox_trainee_paymentnum.setValue(12);
								textfield_trainee_eachsum.setText(Integer.toString(Integer.parseInt(rs.getString(17))/12));
								break;				
							}
						}
						else
						{
							textfield_trainee_sum.setText(rs.getString(17));
							radio_trainee_chekpayment.setSelected(true);
							RadioTraineeChekPaymentClick();
							textfield_trainee_bank.setText(rs.getString(1));
							textfield_trainee_factory.setText(rs.getString(2));
							switch(rs.getString(18))
							{
							case "1":
									combobox_trainee_paymentnum.setValue(1);
									ComboboxPaymentNumSelect();
									textfield_trainee_eachsum.setText(Integer.toString(Integer.parseInt(rs.getString(17))/1));
									textfield_trainee_chek1.setText(rs.getString(3));
									break;
							case "2":
								combobox_trainee_paymentnum.setValue(2);
								ComboboxPaymentNumSelect();
								textfield_trainee_eachsum.setText(Integer.toString(Integer.parseInt(rs.getString(17))/2));
								textfield_trainee_chek1.setText(rs.getString(3));
								textfield_trainee_chek2.setText(rs.getString(4));
								break;
							case "3":
								combobox_trainee_paymentnum.setValue(3);
								ComboboxPaymentNumSelect();
								textfield_trainee_eachsum.setText(Integer.toString(Integer.parseInt(rs.getString(17))/3));
								textfield_trainee_chek1.setText(rs.getString(3));
								textfield_trainee_chek2.setText(rs.getString(4));
								textfield_trainee_chek3.setText(rs.getString(5));
								break;	
							case "4":
								combobox_trainee_paymentnum.setValue(4);
								ComboboxPaymentNumSelect();
								textfield_trainee_eachsum.setText(Integer.toString(Integer.parseInt(rs.getString(17))/4));
								textfield_trainee_chek1.setText(rs.getString(3));
								textfield_trainee_chek2.setText(rs.getString(4));
								textfield_trainee_chek3.setText(rs.getString(5));
								textfield_trainee_chek4.setText(rs.getString(6));
								break;	
							case "5":
								combobox_trainee_paymentnum.setValue(5);
								ComboboxPaymentNumSelect();
								textfield_trainee_eachsum.setText(Integer.toString(Integer.parseInt(rs.getString(17))/5));
								textfield_trainee_chek1.setText(rs.getString(3));
								textfield_trainee_chek2.setText(rs.getString(4));
								textfield_trainee_chek3.setText(rs.getString(5));
								textfield_trainee_chek4.setText(rs.getString(6));
								textfield_trainee_chek5.setText(rs.getString(7));
								break;	
							case "6":
								combobox_trainee_paymentnum.setValue(6);
								ComboboxPaymentNumSelect();
								textfield_trainee_eachsum.setText(Integer.toString(Integer.parseInt(rs.getString(17))/6));
								textfield_trainee_chek1.setText(rs.getString(3));
								textfield_trainee_chek2.setText(rs.getString(4));
								textfield_trainee_chek3.setText(rs.getString(5));
								textfield_trainee_chek4.setText(rs.getString(6));
								textfield_trainee_chek5.setText(rs.getString(7));
								textfield_trainee_chek6.setText(rs.getString(8));
								break;	
							case "7":
								combobox_trainee_paymentnum.setValue(7);
								ComboboxPaymentNumSelect();
								textfield_trainee_eachsum.setText(Integer.toString(Integer.parseInt(rs.getString(17))/7));
								textfield_trainee_chek1.setText(rs.getString(3));
								textfield_trainee_chek2.setText(rs.getString(4));
								textfield_trainee_chek3.setText(rs.getString(5));
								textfield_trainee_chek4.setText(rs.getString(6));
								textfield_trainee_chek5.setText(rs.getString(7));
								textfield_trainee_chek6.setText(rs.getString(8));
								textfield_trainee_chek7.setText(rs.getString(9));
								break;	
							case "8":
								combobox_trainee_paymentnum.setValue(8);
								ComboboxPaymentNumSelect();
								textfield_trainee_eachsum.setText(Integer.toString(Integer.parseInt(rs.getString(17))/8));
								textfield_trainee_chek1.setText(rs.getString(3));
								textfield_trainee_chek2.setText(rs.getString(4));
								textfield_trainee_chek3.setText(rs.getString(5));
								textfield_trainee_chek4.setText(rs.getString(6));
								textfield_trainee_chek5.setText(rs.getString(7));
								textfield_trainee_chek6.setText(rs.getString(8));
								textfield_trainee_chek7.setText(rs.getString(9));
								textfield_trainee_chek8.setText(rs.getString(10));
								break;	
							case "9":
								combobox_trainee_paymentnum.setValue(9);
								ComboboxPaymentNumSelect();
								textfield_trainee_eachsum.setText(Integer.toString(Integer.parseInt(rs.getString(17))/9));
								textfield_trainee_chek1.setText(rs.getString(3));
								textfield_trainee_chek2.setText(rs.getString(4));
								textfield_trainee_chek3.setText(rs.getString(5));
								textfield_trainee_chek4.setText(rs.getString(6));
								textfield_trainee_chek5.setText(rs.getString(7));
								textfield_trainee_chek6.setText(rs.getString(8));
								textfield_trainee_chek7.setText(rs.getString(9));
								textfield_trainee_chek8.setText(rs.getString(10));
								textfield_trainee_chek9.setText(rs.getString(11));
								break;	
							case "10":
								combobox_trainee_paymentnum.setValue(10);
								ComboboxPaymentNumSelect();
								textfield_trainee_eachsum.setText(Integer.toString(Integer.parseInt(rs.getString(17))/10));
								textfield_trainee_chek1.setText(rs.getString(3));
								textfield_trainee_chek2.setText(rs.getString(4));
								textfield_trainee_chek3.setText(rs.getString(5));
								textfield_trainee_chek4.setText(rs.getString(6));
								textfield_trainee_chek5.setText(rs.getString(7));
								textfield_trainee_chek6.setText(rs.getString(8));
								textfield_trainee_chek7.setText(rs.getString(9));
								textfield_trainee_chek8.setText(rs.getString(10));
								textfield_trainee_chek9.setText(rs.getString(11));
								textfield_trainee_chek10.setText(rs.getString(12));
								break;	
							case "11":
								combobox_trainee_paymentnum.setValue(11);
								ComboboxPaymentNumSelect();
								textfield_trainee_eachsum.setText(Integer.toString(Integer.parseInt(rs.getString(17))/11));
								textfield_trainee_chek1.setText(rs.getString(3));
								textfield_trainee_chek2.setText(rs.getString(4));
								textfield_trainee_chek3.setText(rs.getString(5));
								textfield_trainee_chek4.setText(rs.getString(6));
								textfield_trainee_chek5.setText(rs.getString(7));
								textfield_trainee_chek6.setText(rs.getString(8));
								textfield_trainee_chek7.setText(rs.getString(9));
								textfield_trainee_chek8.setText(rs.getString(10));
								textfield_trainee_chek9.setText(rs.getString(11));
								textfield_trainee_chek10.setText(rs.getString(12));
								textfield_trainee_chek11.setText(rs.getString(13));
								break;	
							case "12":
								combobox_trainee_paymentnum.setValue(12);
								ComboboxPaymentNumSelect();
								textfield_trainee_eachsum.setText(Integer.toString(Integer.parseInt(rs.getString(17))/12));
								textfield_trainee_chek1.setText(rs.getString(3));
								textfield_trainee_chek2.setText(rs.getString(4));
								textfield_trainee_chek3.setText(rs.getString(5));
								textfield_trainee_chek4.setText(rs.getString(6));
								textfield_trainee_chek5.setText(rs.getString(7));
								textfield_trainee_chek6.setText(rs.getString(8));
								textfield_trainee_chek7.setText(rs.getString(9));
								textfield_trainee_chek8.setText(rs.getString(10));
								textfield_trainee_chek9.setText(rs.getString(11));
								textfield_trainee_chek10.setText(rs.getString(12));
								textfield_trainee_chek11.setText(rs.getString(13));
								textfield_trainee_chek12.setText(rs.getString(14));
								break;		
						}
						}	
					}
				} catch (SQLException e) {

				}
			}
			}
		else
		{
			String commend;
			if(radio_trainee_cashpayment.isSelected())
			{
				if(textfield_trainee_sum.getText().equals(""))
				{
					showAlertMessage("אתה חייב להקליד סכום", AlertType.ERROR);
					return;
				}
				if(textfield_trainee_id.getText().equals(""))
				{
					showAlertMessage("בבקשה חזור לחלון הקודם והכנס תז", AlertType.ERROR);
					return;
				}
				commend="Insert into Payment(id,sum)"+
						   "VALUES('"+textfield_trainee_id.getText()+"','"+textfield_trainee_sum.getText()+"','"+"cash"+"')";
				
				try {
					s.execute(commend);
				} catch (SQLException e) {
					commend="delete * from Payment where id ="+"\""+textfield_trainee_id.getText()+"\"";
					try {
						s.execute(commend);
						commend="Insert into Payment(id,sum,paymentway)"+
								   "VALUES('"+textfield_trainee_id.getText()+"','"+textfield_trainee_sum.getText()+"','"+"cash"+"')";
						s.execute(commend);
					} catch (SQLException e1) {
						showAlertMessage("משו עבד לא טוב", AlertType.ERROR);
						return;
					}
				}
			}
			else if(radio_trainee_chekpayment.isSelected())
			{
				if(textfield_trainee_sum.getText().equals(""))
				{
					showAlertMessage("אתה חייב להקליד סכום", AlertType.ERROR);
				}
				if(textfield_trainee_id.getText().equals(""))
				{
					showAlertMessage("חזור לחלון הקודם והקלד תז", AlertType.ERROR);
				}
				commend="Insert into Payment(id,sum,bank,factory,chek1,chek2,chek3,chek4,chek5,chek6,chek7,chek8,chek9,chek10,chek11,chek12,numpayment,paymentway)"+
						   "VALUES('"+textfield_trainee_id.getText()+"','"+textfield_trainee_sum.getText()+"','"+textfield_trainee_bank.getText()+"','"+textfield_trainee_factory.getText()
						   			+"','"+textfield_trainee_chek1.getText()+"','"+textfield_trainee_chek2.getText()+"','"+textfield_trainee_chek3.getText()+
						   			"','"+textfield_trainee_chek4.getText()+"','"+textfield_trainee_chek5.getText()+"','"+textfield_trainee_chek6.getText()+
						   			"','"+textfield_trainee_chek7.getText()+"','"+textfield_trainee_chek8.getText()+"','"+textfield_trainee_chek9.getText()+
						   			"','"+textfield_trainee_chek10.getText()+"','"+textfield_trainee_chek11.getText()+"','"+textfield_trainee_chek12.getText()+"','"+
						   			combobox_trainee_paymentnum.getValue()+"','"+"chek"+"')";
				try {
					s.execute(commend);
				} catch (SQLException e) {
					commend="delete * from Payment where id ="+"\""+textfield_trainee_id.getText()+"\"";
					try {
						s.execute(commend);
						commend="Insert into Payment(id,sum,bank,factory,chek1,chek2,chek3,chek4,chek5,chek6,chek7,chek8,chek9,chek10,chek11,chek12,numpayment,paymentway)"+
								   "VALUES('"+textfield_trainee_id.getText()+"','"+textfield_trainee_sum.getText()+"','"+textfield_trainee_bank.getText()+"','"+textfield_trainee_factory.getText()
								   			+"','"+textfield_trainee_chek1.getText()+"','"+textfield_trainee_chek2.getText()+"','"+textfield_trainee_chek3.getText()+
								   			"','"+textfield_trainee_chek4.getText()+"','"+textfield_trainee_chek5.getText()+"','"+textfield_trainee_chek6.getText()+
								   			"','"+textfield_trainee_chek7.getText()+"','"+textfield_trainee_chek8.getText()+"','"+textfield_trainee_chek9.getText()+
								   			"','"+textfield_trainee_chek10.getText()+"','"+textfield_trainee_chek11.getText()+"','"+textfield_trainee_chek12.getText()+"','"+
								   			combobox_trainee_paymentnum.getValue()+"','"+"chek"+"')";
						s.execute(commend);
					} catch (SQLException e1) {
						e1.printStackTrace();
						showAlertMessage("משו לא עבד", AlertType.ERROR);
						return;
					}
				}
			}
			else if(radio_trainee_creditpayment.isSelected())
			{
				if(textfield_trainee_sum.getText().equals(""))
				{
					showAlertMessage("אתה חייב להקליד סכום", AlertType.ERROR);
				}
				if(textfield_trainee_id.getText().equals(""))
				{
					showAlertMessage("חזור לחלון הקודם והקלד תז", AlertType.ERROR);
				}
				commend="Insert into Payment(id,sum,numpayment,paymentway)"+
						   "VALUES('"+textfield_trainee_id.getText()+"','"+textfield_trainee_sum.getText()+"','"+combobox_trainee_paymentnum.getValue()
						   +"','"+"creditcard"+"')";
				try {
					s.execute(commend);
				} catch (SQLException e) {
					commend="delete * from Payment where id ="+"\""+textfield_trainee_id.getText()+"\"";
					try {
						s.execute(commend);
						commend="Insert into Payment(id,sum,numpayment,paymentway)"+
								   "VALUES('"+textfield_trainee_id.getText()+"','"+textfield_trainee_sum.getText()+"','"+combobox_trainee_paymentnum.getValue()
								   +"','"+"creditcard"+"')";
						s.execute(commend);
					} catch (SQLException e1) {
						e1.printStackTrace();
						showAlertMessage("משו לא עבד", AlertType.ERROR);
						return;
				}
			}
				}
			else 
			{
				showAlertMessage("אתה חייב לבחור אחת מהאפשרויות", AlertType.ERROR);
			}
			PaymentTabCleaner();
			SetPaymentTabInvisable();
			
		}
	}
	
	public void SetPaymentTabInvisable()
	{
		button_payment_trainee.setLayoutX(310);
		button_payment_trainee.setText("תשלומים");
		radio_trainee_cashpayment.setVisible(false);
		radio_trainee_chekpayment.setVisible(false);
		radio_trainee_creditpayment.setVisible(false);
		button_paymentback_trainee.setVisible(false);
		button_back_trainee.setVisible(true);
		button_add_trainee.setVisible(true);
		button_delete_trainee.setVisible(true);
		button_add_image.setVisible(true);
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
		label_trainee_num.setVisible(true);
		label_trainee_acc.setVisible(true);
		checkbox_trainee_acc.setVisible(true);
		textfield_trainee_num.setVisible(true);
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
		image_trainee_mainimage.setVisible(true);
		image_trainee_gender.setVisible(true);
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
		combobox_trainee_gender.setVisible(true);
		combobox_trainee_center.setVisible(true);
		combobox_trainee_level.setVisible(true);
		combobox_trainee_group.setVisible(true);
		datepicker_trainee_start.setVisible(true);
		datepicker_trainee_end.setVisible(true);
		textfield_trainee_id.setEditable(true);
		label_trainee_sum.setVisible(false);
		textfield_trainee_sum.setVisible(false);
		label_trainee_chek1.setVisible(false);
		label_trainee_chek2.setVisible(false);
		textfield_trainee_bank.setVisible(false);
		textfield_trainee_factory.setVisible(false);
		textfield_trainee_chek1.setVisible(false);
		textfield_trainee_chek2.setVisible(false);
		label_trainee_chek3.setVisible(false);
		textfield_trainee_chek3.setVisible(false);
		label_trainee_chek4.setVisible(false);
		textfield_trainee_chek4.setVisible(false);
		label_trainee_chek5.setVisible(false);
		textfield_trainee_chek5.setVisible(false);
		label_trainee_chek6.setVisible(false);
		textfield_trainee_chek6.setVisible(false);
		label_trainee_chek7.setVisible(false);
		textfield_trainee_chek7.setVisible(false);
		label_trainee_chek8.setVisible(false);
		textfield_trainee_chek8.setVisible(false);
		label_trainee_chek9.setVisible(false);
		textfield_trainee_chek9.setVisible(false);
		label_trainee_chek10.setVisible(false);
		textfield_trainee_chek10.setVisible(false);
		label_trainee_chek11.setVisible(false);
		textfield_trainee_chek11.setVisible(false);
		label_trainee_chek12.setVisible(false);
		textfield_trainee_chek12.setVisible(false);
		label_trainee_eachsum.setVisible(false);
		textfield_trainee_eachsum.setVisible(false);
		label_trainee_bank.setVisible(false);
		label_trainee_factory.setVisible(false);
		label_trainee_number.setVisible(false);
		label_trainee_cheksnum.setVisible(false);
		combobox_trainee_paymentnum.setVisible(false);
		textfield_trainee_sum.clear();
		radio_trainee_cashpayment.setSelected(false);
		radio_trainee_chekpayment.setSelected(false);
		radio_trainee_creditpayment.setSelected(false);
		textfield_trainee_eachsum.clear();
		button_print_traineeinfo.setVisible(true);
		button_level_trainee.setVisible(true);
	}
	
	@FXML
	public void BackPaymentButtonClick(ActionEvent event)
	{
		SetPaymentTabInvisable();
	}
	
	@FXML
    public void RadioTraineeChekPaymentClick()
    {
		textfield_trainee_eachsum.clear();
    	if(radio_trainee_chekpayment.isSelected())
    	{
        	radio_trainee_cashpayment.setSelected(false);
        	radio_trainee_creditpayment.setSelected(false);
    		PaymentTabCleaner();
    		label_trainee_eachsum.setVisible(true);
    		textfield_trainee_eachsum.setVisible(true);
    		combobox_trainee_paymentnum.setVisible(true);
    		label_trainee_cheksnum.setVisible(true);
    		trainee_paymentway_num.clear();
    		trainee_paymentway_num.add(1);
    		trainee_paymentway_num.add(2);
    		trainee_paymentway_num.add(3);
    		trainee_paymentway_num.add(4);
    		trainee_paymentway_num.add(5);
    		trainee_paymentway_num.add(6);
    		trainee_paymentway_num.add(7);
    		trainee_paymentway_num.add(8);
    		trainee_paymentway_num.add(9);
    		trainee_paymentway_num.add(10);
    		trainee_paymentway_num.add(11);
    		trainee_paymentway_num.add(12);
    		combobox_trainee_paymentnum.setItems(trainee_paymentway_num);
    		
    	}
    	else PaymentTabCleaner();
    }
    
    @FXML
    public void RadioTraineeCashPaymentClick(ActionEvent event)
    {
    	radio_trainee_chekpayment.setSelected(false);
    	radio_trainee_creditpayment.setSelected(false);
    	PaymentTabCleaner(); 	
    }
    
    @FXML
    public void RadioTraineeCreditPaymentClick()
    {
    	
    	radio_trainee_chekpayment.setSelected(false);
    	radio_trainee_cashpayment.setSelected(false);
    	PaymentTabCleaner();
    	if(radio_trainee_creditpayment.isSelected())
    	{
    	label_trainee_eachsum.setVisible(true);
		textfield_trainee_eachsum.setVisible(true);
		combobox_trainee_paymentnum.setVisible(true);
		label_trainee_cheksnum.setVisible(true);
		trainee_paymentway_num.clear();
		trainee_paymentway_num.add(1);
		trainee_paymentway_num.add(2);
		trainee_paymentway_num.add(3);
		trainee_paymentway_num.add(4);
		trainee_paymentway_num.add(5);
		trainee_paymentway_num.add(6);
		trainee_paymentway_num.add(7);
		trainee_paymentway_num.add(8);
		trainee_paymentway_num.add(9);
		trainee_paymentway_num.add(10);
		trainee_paymentway_num.add(11);
		trainee_paymentway_num.add(12);
		combobox_trainee_paymentnum.setItems(trainee_paymentway_num);
    	}    
    }
    
    @FXML
    public void ComboboxPaymentNumSelect()
    {
    	if(radio_trainee_chekpayment.isSelected())
    	{
    		int temp;
    		try {
    		 temp=Integer.parseInt((textfield_trainee_sum.getText()))/combobox_trainee_paymentnum.getValue();
    		}catch(Exception e)
    		{
    			showAlertMessage("אתה חייב להקליד ספרות בלבד", AlertType.ERROR);
    			return;
    		}
    		textfield_trainee_eachsum.setText(Integer.toString(temp));
			label_trainee_chek1.setVisible(false);
			label_trainee_chek2.setVisible(false);
			textfield_trainee_bank.setVisible(false);
			textfield_trainee_factory.setVisible(false);
			textfield_trainee_chek1.setVisible(false);
			textfield_trainee_chek2.setVisible(false);
			label_trainee_chek3.setVisible(false);
			textfield_trainee_chek3.setVisible(false);
			label_trainee_chek4.setVisible(false);
			textfield_trainee_chek4.setVisible(false);
			label_trainee_chek5.setVisible(false);
			textfield_trainee_chek5.setVisible(false);
			label_trainee_chek6.setVisible(false);
			textfield_trainee_chek6.setVisible(false);
			label_trainee_chek7.setVisible(false);
			textfield_trainee_chek7.setVisible(false);
			label_trainee_chek8.setVisible(false);
			textfield_trainee_chek8.setVisible(false);
			label_trainee_chek9.setVisible(false);
			textfield_trainee_chek9.setVisible(false);
			label_trainee_chek10.setVisible(false);
			textfield_trainee_chek10.setVisible(false);
			label_trainee_chek11.setVisible(false);
			textfield_trainee_chek11.setVisible(false);
			label_trainee_chek12.setVisible(false);
			textfield_trainee_chek12.setVisible(false);
    		switch(combobox_trainee_paymentnum.getValue())
			{
				case 1:
						label_trainee_chek1.setVisible(true);
						textfield_trainee_bank.setVisible(true);
						textfield_trainee_factory.setVisible(true);
						textfield_trainee_chek1.setVisible(true);
						break;
				case 2:
						label_trainee_chek1.setVisible(true);
						label_trainee_chek2.setVisible(true);
						textfield_trainee_bank.setVisible(true);
						textfield_trainee_factory.setVisible(true);
						textfield_trainee_chek1.setVisible(true);
						textfield_trainee_chek2.setVisible(true);
						break;
				case 3:
					label_trainee_chek1.setVisible(true);
					label_trainee_chek2.setVisible(true);
					textfield_trainee_bank.setVisible(true);
					textfield_trainee_factory.setVisible(true);
					textfield_trainee_chek1.setVisible(true);
					textfield_trainee_chek2.setVisible(true);
					label_trainee_chek3.setVisible(true);
					textfield_trainee_chek3.setVisible(true);
					break;
				case 4:
					label_trainee_chek1.setVisible(true);
					label_trainee_chek2.setVisible(true);
					textfield_trainee_bank.setVisible(true);
					textfield_trainee_factory.setVisible(true);
					textfield_trainee_chek1.setVisible(true);
					textfield_trainee_chek2.setVisible(true);
					label_trainee_chek3.setVisible(true);
					textfield_trainee_chek3.setVisible(true);
					label_trainee_chek4.setVisible(true);
					textfield_trainee_chek4.setVisible(true);
					break;
				case 5:
					label_trainee_chek1.setVisible(true);
					label_trainee_chek2.setVisible(true);
					textfield_trainee_bank.setVisible(true);
					textfield_trainee_factory.setVisible(true);
					textfield_trainee_chek1.setVisible(true);
					textfield_trainee_chek2.setVisible(true);
					label_trainee_chek3.setVisible(true);
					textfield_trainee_chek3.setVisible(true);
					label_trainee_chek4.setVisible(true);
					textfield_trainee_chek4.setVisible(true);
					label_trainee_chek5.setVisible(true);
					textfield_trainee_chek5.setVisible(true);
					break;
				case 6:
					label_trainee_chek1.setVisible(true);
					label_trainee_chek2.setVisible(true);
					textfield_trainee_bank.setVisible(true);
					textfield_trainee_factory.setVisible(true);
					textfield_trainee_chek1.setVisible(true);
					textfield_trainee_chek2.setVisible(true);
					label_trainee_chek3.setVisible(true);
					textfield_trainee_chek3.setVisible(true);
					label_trainee_chek4.setVisible(true);
					textfield_trainee_chek4.setVisible(true);
					label_trainee_chek5.setVisible(true);
					textfield_trainee_chek5.setVisible(true);
					label_trainee_chek6.setVisible(true);
					textfield_trainee_chek6.setVisible(true);
					break;
				case 7:
					label_trainee_chek1.setVisible(true);
					label_trainee_chek2.setVisible(true);
					textfield_trainee_bank.setVisible(true);
					textfield_trainee_factory.setVisible(true);
					textfield_trainee_chek1.setVisible(true);
					textfield_trainee_chek2.setVisible(true);
					label_trainee_chek3.setVisible(true);
					textfield_trainee_chek3.setVisible(true);
					label_trainee_chek4.setVisible(true);
					textfield_trainee_chek4.setVisible(true);
					label_trainee_chek5.setVisible(true);
					textfield_trainee_chek5.setVisible(true);
					label_trainee_chek6.setVisible(true);
					textfield_trainee_chek6.setVisible(true);
					label_trainee_chek7.setVisible(true);
					textfield_trainee_chek7.setVisible(true);
					break;		
				case 8:
					label_trainee_chek1.setVisible(true);
					label_trainee_chek2.setVisible(true);
					textfield_trainee_bank.setVisible(true);
					textfield_trainee_factory.setVisible(true);
					textfield_trainee_chek1.setVisible(true);
					textfield_trainee_chek2.setVisible(true);
					label_trainee_chek3.setVisible(true);
					textfield_trainee_chek3.setVisible(true);
					label_trainee_chek4.setVisible(true);
					textfield_trainee_chek4.setVisible(true);
					label_trainee_chek5.setVisible(true);
					textfield_trainee_chek5.setVisible(true);
					label_trainee_chek6.setVisible(true);
					textfield_trainee_chek6.setVisible(true);
					label_trainee_chek7.setVisible(true);
					textfield_trainee_chek7.setVisible(true);
					label_trainee_chek8.setVisible(true);
					textfield_trainee_chek8.setVisible(true);
					break;	
				case 9:
					label_trainee_chek1.setVisible(true);
					label_trainee_chek2.setVisible(true);
					textfield_trainee_bank.setVisible(true);
					textfield_trainee_factory.setVisible(true);
					textfield_trainee_chek1.setVisible(true);
					textfield_trainee_chek2.setVisible(true);
					label_trainee_chek3.setVisible(true);
					textfield_trainee_chek3.setVisible(true);
					label_trainee_chek4.setVisible(true);
					textfield_trainee_chek4.setVisible(true);
					label_trainee_chek5.setVisible(true);
					textfield_trainee_chek5.setVisible(true);
					label_trainee_chek6.setVisible(true);
					textfield_trainee_chek6.setVisible(true);
					label_trainee_chek7.setVisible(true);
					textfield_trainee_chek7.setVisible(true);
					label_trainee_chek8.setVisible(true);
					textfield_trainee_chek8.setVisible(true);
					label_trainee_chek9.setVisible(true);
					textfield_trainee_chek9.setVisible(true);
					break;
				case 10:
					label_trainee_chek1.setVisible(true);
					label_trainee_chek2.setVisible(true);
					textfield_trainee_bank.setVisible(true);
					textfield_trainee_factory.setVisible(true);
					textfield_trainee_chek1.setVisible(true);
					textfield_trainee_chek2.setVisible(true);
					label_trainee_chek3.setVisible(true);
					textfield_trainee_chek3.setVisible(true);
					label_trainee_chek4.setVisible(true);
					textfield_trainee_chek4.setVisible(true);
					label_trainee_chek5.setVisible(true);
					textfield_trainee_chek5.setVisible(true);
					label_trainee_chek6.setVisible(true);
					textfield_trainee_chek6.setVisible(true);
					label_trainee_chek7.setVisible(true);
					textfield_trainee_chek7.setVisible(true);
					label_trainee_chek8.setVisible(true);
					textfield_trainee_chek8.setVisible(true);
					label_trainee_chek9.setVisible(true);
					textfield_trainee_chek9.setVisible(true);
					label_trainee_chek10.setVisible(true);
					textfield_trainee_chek10.setVisible(true);
					break;
				case 11:
					label_trainee_chek1.setVisible(true);
					label_trainee_chek2.setVisible(true);
					textfield_trainee_bank.setVisible(true);
					textfield_trainee_factory.setVisible(true);
					textfield_trainee_chek1.setVisible(true);
					textfield_trainee_chek2.setVisible(true);
					label_trainee_chek3.setVisible(true);
					textfield_trainee_chek3.setVisible(true);
					label_trainee_chek4.setVisible(true);
					textfield_trainee_chek4.setVisible(true);
					label_trainee_chek5.setVisible(true);
					textfield_trainee_chek5.setVisible(true);
					label_trainee_chek6.setVisible(true);
					textfield_trainee_chek6.setVisible(true);
					label_trainee_chek7.setVisible(true);
					textfield_trainee_chek7.setVisible(true);
					label_trainee_chek8.setVisible(true);
					textfield_trainee_chek8.setVisible(true);
					label_trainee_chek9.setVisible(true);
					textfield_trainee_chek9.setVisible(true);
					label_trainee_chek10.setVisible(true);
					textfield_trainee_chek10.setVisible(true);
					label_trainee_chek11.setVisible(true);
					textfield_trainee_chek11.setVisible(true);
					break;
				case 12:
					label_trainee_chek1.setVisible(true);
					label_trainee_chek2.setVisible(true);
					textfield_trainee_bank.setVisible(true);
					textfield_trainee_factory.setVisible(true);
					textfield_trainee_chek1.setVisible(true);
					textfield_trainee_chek2.setVisible(true);
					label_trainee_chek3.setVisible(true);
					textfield_trainee_chek3.setVisible(true);
					label_trainee_chek4.setVisible(true);
					textfield_trainee_chek4.setVisible(true);
					label_trainee_chek5.setVisible(true);
					textfield_trainee_chek5.setVisible(true);
					label_trainee_chek6.setVisible(true);
					textfield_trainee_chek6.setVisible(true);
					label_trainee_chek7.setVisible(true);
					textfield_trainee_chek7.setVisible(true);
					label_trainee_chek8.setVisible(true);
					textfield_trainee_chek8.setVisible(true);
					label_trainee_chek9.setVisible(true);
					textfield_trainee_chek9.setVisible(true);
					label_trainee_chek10.setVisible(true);
					textfield_trainee_chek10.setVisible(true);
					label_trainee_chek11.setVisible(true);
					textfield_trainee_chek11.setVisible(true);
					label_trainee_chek12.setVisible(true);
					textfield_trainee_chek12.setVisible(true);
					break;		
			}
    	}
    	else
    	{
    		textfield_trainee_eachsum.clear();
    		int temp;
    		try {
    		 temp=Integer.parseInt((textfield_trainee_sum.getText()))/combobox_trainee_paymentnum.getValue();
    		 textfield_trainee_eachsum.setText(Integer.toString(temp));
    		}catch(Exception e)
    		{
    			showAlertMessage("אתה חייב להקליד ספרות בלבד", AlertType.ERROR);
    			return;
    		}
    	}
    }
    
    public void PaymentTabCleaner()
    {
		label_trainee_chek1.setVisible(false);
		label_trainee_chek2.setVisible(false);
		textfield_trainee_bank.setVisible(false);
		textfield_trainee_factory.setVisible(false);
		textfield_trainee_chek1.setVisible(false);
		textfield_trainee_chek2.setVisible(false);
		label_trainee_chek3.setVisible(false);
		textfield_trainee_chek3.setVisible(false);
		label_trainee_chek4.setVisible(false);
		textfield_trainee_chek4.setVisible(false);
		label_trainee_chek5.setVisible(false);
		textfield_trainee_chek5.setVisible(false);
		label_trainee_chek6.setVisible(false);
		textfield_trainee_chek6.setVisible(false);
		label_trainee_chek7.setVisible(false);
		textfield_trainee_chek7.setVisible(false);
		label_trainee_chek8.setVisible(false);
		textfield_trainee_chek8.setVisible(false);
		label_trainee_chek9.setVisible(false);
		textfield_trainee_chek9.setVisible(false);
		label_trainee_chek10.setVisible(false);
		textfield_trainee_chek10.setVisible(false);
		label_trainee_chek11.setVisible(false);
		textfield_trainee_chek11.setVisible(false);
		label_trainee_chek12.setVisible(false);
		textfield_trainee_chek12.setVisible(false);
		label_trainee_eachsum.setVisible(false);
		textfield_trainee_eachsum.setVisible(false);
		combobox_trainee_paymentnum.setVisible(false);
		label_trainee_cheksnum.setVisible(false);
    }
    @FXML
    public void AddImageButtonClick()
    {
    	FileChooser fc=new FileChooser();
    	fc.getExtensionFilters().addAll(new ExtensionFilter("jpg File","*.jpg"),new ExtensionFilter("png File","*.png"));
    	File selected_file=fc.showOpenDialog(null);
    	if(selected_file!=null)
    	{
    		Image Image;
    		
    			try {
					Image = new Image(selected_file.toURI().toURL().toExternalForm());
	    			image_trainee_mainimage.setImage(Image);
	    			image_trainee_mainimage.setVisible(false);
	    			image_trainee_mainimage.setVisible(true);
	    			main_image=selected_file.toURI().toURL().toExternalForm();
				} catch (MalformedURLException e) {
					InputStream view = getClass().getResourceAsStream("/boundaries/images/unknow.jpg");
					if (view != null) {
						Image = new Image(view);
						image_trainee_mainimage.setImage(Image);
					}
				}
    			
    	}
    }
    @FXML
    public void printer()
    {
    	try {
			printNode(trainee_table);
		} catch (NoSuchMethodException | InstantiationException | IllegalAccessException
				| InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @FXML
    public void printerinfo()
    {
    	try {
			printNode(anchorpane_trainee);
		} catch (NoSuchMethodException | InstantiationException | IllegalAccessException
				| InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public static  void printNode(final Node node) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
    	Printer printer = Printer.getDefaultPrinter();
        PageLayout pageLayout
            = printer.createPageLayout(Paper.A4, PageOrientation.LANDSCAPE, Printer.MarginType.HARDWARE_MINIMUM);
        PrinterAttributes attr = printer.getPrinterAttributes();
        PrinterJob job = PrinterJob.createPrinterJob();
        double scaleX
            = pageLayout.getPrintableWidth() / node.getBoundsInParent().getWidth();
        double scaleY
            = pageLayout.getPrintableHeight() / node.getBoundsInParent().getHeight();
        Scale scale = new Scale(scaleX, scaleY);
        node.getTransforms().add(scale);

        if (job != null && job.showPrintDialog(node.getScene().getWindow())) {
          boolean success = job.printPage(pageLayout, node);
          if (success) {
            job.endJob();

          }
        }
        node.getTransforms().remove(scale);
    }
    
    public void SearchTraineeCenterButtonClick()
    {
    	String command="";
		if(textfield_search_traineecenter.getText().equals("")&&textfield_search_traineegroup.getText().equals(""))
		{
			command="select * from Trainees";
		}
		else if(textfield_search_traineecenter.getText().equals(""))
			{
			 showAlertMessage("אתה חייב לבחור מרכז אם אתה מחפש קבוצה", AlertType.ERROR);
			 return;
			}
			else
				if(textfield_search_traineegroup.getText().equals(""))
				{
					command="select * from Trainees where center = " + "\""+textfield_search_traineecenter.getText()+"\"";
				}
				else
				{
					command="select * from Trainees where center = " + "\""+textfield_search_traineecenter.getText()+"\""+" "+" and groupp= "+"\""+textfield_search_traineegroup.getText()+"\"";
				}
			try {
				s.execute(command);
				ResultSet set=s.getResultSet();
			
				for ( int i = 0; i<trainee_table.getItems().size(); i++) {
					trainee_table.getItems().clear();
				}
			
				while((set!=null) && (set.next()))
				{
					TraineesRow temp=new TraineesRow(set.getString(13), set.getString(1), set.getString(2), set.getString(10),set.getString(11),set.getString(18),set.getString(19));
					trainee_row.add(temp);
				}
				trainee_table.setItems(trainee_row);
				trainee_table.refresh();
			
			} catch (SQLException e) {
				showAlertMessage("המאמן לא נימצא במערכת", AlertType.INFORMATION);
				e.printStackTrace();
				return;
			}
    }

    @FXML
    public void LevelsButtonClick()
    {
    	if(textfield_trainee_id.getText().equals(""))
    	{
    		showAlertMessage("אתה חייב למלא תז לפני שתוכל לעבור לחלון זה", AlertType.ERROR);
    		return;
    	}
    	
    	anchorpane_trainee.setVisible(false);
    	anchorpane_level.setVisible(true);
    	InitializeLevelsTab();
    	combobox_level_levels.setItems(trainee_level);
    	combobox_level_levels.setValue("");
    	textfield_level_number.clear();
    	datepicker_level_date.setValue(null);
    	if(button_add_trainee.getText().equals("ערוך ספורטאי"))
    	{
    		levels_list.clear();
    		ResultSet set;
    		String command="Select * From Levels where id = "+"\""+textfield_trainee_id.getText()+"\"";
    		try {
				s.execute(command);
				set=s.getResultSet();
				while((set!=null) && (set.next()))
				{
					LevelsRow row= new LevelsRow(set.getString(4),set.getString(1),set.getString(2),set.getString(3)); 
					levels_list.add(row);
				}
			} catch (SQLException e) {
				showAlertMessage("Boom", AlertType.ERROR);
				return;
			}
    		levels_table.setItems(levels_list);
    		levels_table.refresh();
    	}
    
    }
	//Lists Functions
//---------------------------------------------------------end->Trainee tab Functions------------------------------------------------//	

//---------------------------------------------------------List tab Functions------------------------------------------------//	
	
	
    public void ListsTableBuilder(ResultSet set)
	 {
    	String command;
		list_row.clear();
		try {
			while((set!=null) && (set.next()))
			{
				command="Select * From Trainees where center = "+"\""+ set.getString(2)+"\""+" And gender = "+"\""+"זכר"+"\"";
				s.execute(command);
				int males=0;
				ResultSet temprs=s.getResultSet();
				while((temprs!=null) && (temprs.next()))
				{
					males++;
				}
				command="Select * From Trainees where center = "+"\""+ set.getString(2)+"\""+" And gender = "+"\""+"נקבה"+"\"";
				s.execute(command);
				int females=0;
				temprs=s.getResultSet();
				while((temprs!=null) && (temprs.next()))
				{
					females++;
				}
			    ListsRow temp=new ListsRow(set.getInt(1),set.getString(2), Integer.toString(males), Integer.toString(females), set.getString(5));
			    list_row.add(temp);
			    command="Update Lists set males = "+"\""+males+"\" , "+ "females = "+"\""+females+"\""+"where id = "+set.getInt(1);
			    s.execute(command);
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
    	label_listsearch.setVisible(false);
    	label_search_listname.setVisible(false);
    	label_search_listtrainer.setVisible(false);
    	textfield_search_listname.setVisible(false);
    	textfield_search_listtrainername.setVisible(false);
    	button_search_list.setVisible(false);
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
    	label_listsearch.setVisible(true);
    	label_search_listname.setVisible(true);
    	label_search_listtrainer.setVisible(true);
    	textfield_search_listname.setVisible(true);
    	textfield_search_listtrainername.setVisible(true);
    	button_search_list.setVisible(true);
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
    
    @FXML
    public void SearchListButtonClick(ActionEvent event)
    {
    	String command="";
		if(textfield_search_listname.getText().equals("")&&textfield_search_listtrainername.getText().equals(""))
		{
			command="select * from Lists";
		}
		else if(textfield_search_listname.getText().equals(""))
			{
			 command="select * from Lists where trainer = " +"\"" +textfield_search_listtrainername.getText()+"\"";
			}
			else
				if(textfield_search_listtrainername.getText().equals(""))
				{
					command="select * from Lists where center = " + "\""+textfield_search_listname.getText()+"\"";
				}
				else
				{
					command="select * from Lists where center = " + "\""+textfield_search_listname.getText()+"\""+" "+"And trainer = "+"\""+textfield_search_listtrainername.getText()+"\"";
				}
			try {
				s.execute(command);
				ResultSet set=s.getResultSet();
			
				for ( int i = 0; i<lists_table.getItems().size(); i++) {
					lists_table.getItems().clear();
				}
			
				while((set!=null) && (set.next()))
				{
					ListsRow temp=new ListsRow(set.getInt(1),set.getString(2), set.getString(3), set.getString(4), set.getString(5));
					list_row.add(temp);
				}
				lists_table.setItems(list_row);
				lists_table.refresh();
			
			} catch (SQLException e) {
				showAlertMessage("הרשימה לא נימצא במערכת", AlertType.INFORMATION);
				e.printStackTrace();
				return;
			}
    }
    
    
    //Group Functions

//-------------------------------------------------------end->List tab Functions------------------------------------------------//	

  //-------------------------------------------------------group tab Functions------------------------------------------------//	
    
   
    //------------------------------------------------------------------------------------------------------------------------------------------//
    @FXML
    public void ComboboxCenterClick(ActionEvent event)
    {
    	if(combobox_group_center.getValue()==null)
    		return;
    	for ( int i = 0; i<groups_table.getItems().size(); i++) {
    		groups_table.getItems().clear();
    	}
		ResultSet set= GetFromDataBase("Groups", DataBaseAction.GetAll, null, null);
		String command;
		try {
				while((set!=null) && (set.next()))
				{
					if(set.getString(4).equals(combobox_group_center.getValue()))
					{
						command="Select * From Trainees where center = "+"\""+ set.getString(4)+"\""+" And gender = "+"\""+"זכר"+"\""+" And groupp = "+"\""+set.getString(3)+"\"";
						s.execute(command);
						int males=0;
						ResultSet temprs=s.getResultSet();
						while((temprs!=null) && (temprs.next()))
						{
							males++;
						}
						command="Select * From Trainees where center = "+"\""+ set.getString(4)+"\""+" And gender = "+"\""+"נקבה"+"\""+" And groupp = "+"\""+set.getString(3)+"\"";
						s.execute(command);
						int females=0;
						temprs=s.getResultSet();
						while((temprs!=null) && (temprs.next()))
						{
							females++;
						}
					GroupsRow temp=new GroupsRow(set.getInt(1),set.getString(2), set.getString(3), set.getString(5), set.getString(4),Integer.toString(males),Integer.toString(females));
					group_list.add(temp);
					command="Update Groups set males = "+"\""+males+"\" , "+ "females = "+"\""+females+"\""+"where id = "+set.getInt(1);
				    s.execute(command);
					}
				}
				if(group_list.isEmpty())
					showAlertMessage("אין קבוצות פעילות למרכז הנבחר", AlertType.INFORMATION);
				groups_table.setItems(group_list);
			}
			catch (SQLException e) {
			e.printStackTrace();
		}
		
    	
    }

    public void SetGroupTabInVisable()
    {
    	groups_table.setVisible(true);
    	label_groupsearch.setVisible(true);
    	label_search_groupcenter.setVisible(true);
    	label_search_grouptrainer.setVisible(true);
    	textfield_search_groupcenter.setVisible(true);
    	textfield_search_grouptrainer.setVisible(true);
    	button_search_group.setVisible(true);
    	FillGroupCenterCombobox();
    	button_add_group.setLayoutX(420);
    	button_add_group.setText("הוסף שיעור חדש");
    	button_back_group.setVisible(false);
    	combobox_group_center.setItems(group_center_list);
    	combobox_group_center.setVisible(true);
    	label_group_center.setVisible(true);
    	label_group_day.setVisible(false);
    	label_group_name.setVisible(false);
    	label_group_trainer.setVisible(false);
    	label_group_start.setVisible(false);
    	label_group_end.setVisible(false);
    	textfield_group_males.setVisible(false);
    	textfield_group_males.clear();
    	textfield_group_females.setVisible(false);
    	textfield_group_females.clear();
    	label_group_males.setVisible(false);
    	label_group_females.setVisible(false);
    	textfield_group_name.setVisible(false);
    	radio_group_add.setVisible(false);
    	combobox_group_days.setVisible(false);
    	combobox_group_trainer.setVisible(false);
    	spinner_group_start.setVisible(false);
    	spinner_group_end.setVisible(false);
    	spinner_group_start2.setVisible(false);
    	spinner_group_end2.setVisible(false);
    	combobox_group_days2.setVisible(false);
    	combobox_group_center.setValue(null);
    	textfield_group_name.clear();
    	for ( int i = 0; i<groups_table.getItems().size(); i++) {
    		groups_table.getItems().clear();
    	}
       	button_delete_group.setVisible(false);
       	radio_group_add.setSelected(false);
       	RadioGroupButtonClick();
    	
    }
  
    public void SetGroupTabVisable()
    {
    	groups_table.setVisible(false);
    	label_groupsearch.setVisible(false);
    	label_search_groupcenter.setVisible(false);
    	label_search_grouptrainer.setVisible(false);
    	textfield_search_groupcenter.setVisible(false);
    	textfield_search_grouptrainer.setVisible(false);
    	button_search_group.setVisible(false);
    	button_back_group.setVisible(true);
    	button_add_group.setLayoutX(300);
    	combobox_group_center.setVisible(false);
    	label_group_center.setVisible(false);
    	label_group_day.setVisible(true);
    	label_group_name.setVisible(true);
    	label_group_trainer.setVisible(true);
    	label_group_start.setVisible(true);
    	label_group_end.setVisible(true);
    	textfield_group_name.setVisible(true);
    	textfield_group_males.setVisible(true);
    	textfield_group_females.setVisible(true);
    	label_group_males.setVisible(true);
    	label_group_females.setVisible(true);
    	radio_group_add.setVisible(true);
    	combobox_group_days.setVisible(true);
    	FillGroupDaysList();
    	combobox_group_days.setItems(group_days_list);
    	combobox_group_trainer.setVisible(true);
    	FillGroupTrainers();
    	combobox_group_trainer.setItems(group_trainer_list);
    	spinner_group_start.setVisible(true);
    	spinner_group_end.setVisible(true);
    	InitializeTimeSpinner();
    	spinner_group_start.increment();
		spinner_group_start.decrement();
		spinner_group_end.increment();
		spinner_group_end.decrement();
		spinner_group_start2.increment();
		spinner_group_start2.decrement();
		spinner_group_end2.increment();
		spinner_group_end2.decrement();
		textfield_group_males.setText("0");
		textfield_group_females.setText("0");
    	
    }
    
    public void FillGroupCenterCombobox()
   {
    	group_center_list.clear();
	   ResultSet set =GetFromDataBase("Centers", DataBaseAction.GetAll, null, null);
	   try {
	   while((set!=null) && (set.next()))
		{
			String temp= set.getString(3);
			group_center_list.add(temp);   
		}
	   }catch(SQLException e) {
			showAlertMessage("אין מרכזים במערכת", AlertType.INFORMATION);

	   }
   }
  
    @FXML
    public void AddGroupButtonClick()
    {
    		if(groups_table.isVisible())
    		{
    			if(combobox_group_center.getValue()==null)
    			{
    				showAlertMessage("אתה חייב לבחור מרכז לפני שתוכל להוסיף שיעור", AlertType.ERROR);
    				return;
    			}
    		SetGroupTabVisable();
    		}
    		else
    		{
    			if(button_add_group.getText().equals("הוסף שיעור חדש"))
    			{
    			if(textfield_group_name.getText().equals("")||combobox_group_trainer.getValue()==null||
    					combobox_group_days.getValue()==null||spinner_group_start.getValue()==null||
    					spinner_group_end.getValue()==null)
    			{
    				showAlertMessage("אתה חייב למלא את כל השדות", AlertType.ERROR);
    			}
    			else
    			{
    				if(radio_group_add.isSelected())
    				{
    					if(combobox_group_days.getValue()==null||spinner_group_start2.getValue()==null||
    						spinner_group_end2.getValue()==null)
    					{
    						showAlertMessage("אתה חייב למלא את כל השדות או לכבות את הכפתור", AlertType.ERROR);
    						return;
    					}
    				}
    				spinner_group_start.increment();
    				spinner_group_start.decrement();
    				spinner_group_end.increment();
    				spinner_group_end.decrement();
    				spinner_group_start2.increment();
    				spinner_group_start2.decrement();
    				spinner_group_end2.increment();
    				spinner_group_end2.decrement();
    				String times="יום"+" "+combobox_group_days.getValue()+" "+"בשעה "+spinner_group_start.getValue().toString().substring(0, 5)+"-"+spinner_group_end.getValue().toString().substring(0, 5);
    				if(radio_group_add.isSelected())
    				{
    					times=times+"\n"+
    							"יום"+" "+combobox_group_days2.getValue()+" "+"בשעה "+spinner_group_start2.getValue().toString().substring(0, 5)+"-"+spinner_group_end2.getValue().toString().substring(0, 5);		
    				}
    				String command1="INSERT into Groups(trainer,group,activity_time,center)"+
    						   "VALUES('"+combobox_group_trainer.getValue()+"','"+textfield_group_name.getText()+"','"+
    						   			times+"','"+combobox_group_center.getValue()+"')";
    				try {
						s.execute(command1);
					} catch (SQLException e) {
						e.printStackTrace();
					}
    				SetGroupTabInVisable();
    			}
    			}
    			else
    			{
    				GetFromDataBase("Groups", DataBaseAction.Delete, "id", Integer.toString(correct_group.getPr_key()));
    				button_add_group.setText("הוסף שיעור חדש");
    				AddGroupButtonClick();
    				
    			}
    		}
    }
 
    @FXML
    public void RadioGroupButtonClick()
    {
    	if(radio_group_add.isSelected())
    	{
    		combobox_group_days2.setVisible(true);
    		spinner_group_end2.setVisible(true);
    		spinner_group_start2.setVisible(true);
    		combobox_group_days2.setItems(group_days_list);
    	}
    	else
    	{
    		combobox_group_days2.setVisible(false);
    		spinner_group_end2.setVisible(false);
    		spinner_group_start2.setVisible(false);
    	}
    }
 
    public void FillGroupDaysList()
    {
    	group_days_list.clear();
    	group_days_list.add("א");
    	group_days_list.add("ב");
    	group_days_list.add("ג");
    	group_days_list.add("ד");
    	group_days_list.add("ה");
    	group_days_list.add("ו");
    	group_days_list.add("ש");
    }
    
    public void FillGroupTrainers()
    {
    	group_trainer_list.clear();
    	ResultSet set=GetFromDataBase("Trainers", DataBaseAction.GetAll, null, null);
    	
    	try {
    	while((set!=null) && (set.next()))
		{
			String temp=set.getString(1)+" "+set.getString(2); 
			group_trainer_list.add(temp);
		}
    	}catch(SQLException e)
    	{
    		showAlertMessage("אין מאמנים במערכת", AlertType.INFORMATION);
    	}
    }
   
    @FXML
    public void BackGroupButtonClick()
    {
    	SetGroupTabInVisable();
    }
 
    @FXML
    public void DeleteGroupButtonClick(ActionEvent event)
    {
    	GetFromDataBase("Groups", DataBaseAction.Delete, "id",Integer.toString(correct_group.getPr_key()));
    	SetGroupTabInVisable();
    }
   
    public void SetVisableEditGroupTab(GroupsRow rowData)
    {
    	SetGroupTabVisable();
    	DateTimeFormatter fmt = DateTimeFormatter.ofPattern("HH:mm");
    	button_add_group.setText("ערוך שיעור");
    	button_delete_group.setVisible(true);
    	textfield_group_name.setText(rowData.getGroup());
    	combobox_group_trainer.setValue(rowData.getTrainer());
    	combobox_group_days.setValue(rowData.getActivity_time().substring(4,5));
    	textfield_group_males.setText(rowData.getMales());
    	textfield_group_females.setText(rowData.getFemales());
		spinner_group_start.getValueFactory().setValue(LocalTime.parse(rowData.getActivity_time().substring(11, 16), fmt));
		spinner_group_end.getValueFactory().setValue(LocalTime.parse(rowData.getActivity_time().substring(17, 22), fmt));
		if(rowData.getActivity_time().length()>23)
		{
			radio_group_add.setSelected(true);
			RadioGroupButtonClick();
			combobox_group_days2.setValue(rowData.getActivity_time().substring(27,28));
			spinner_group_start2.getValueFactory().setValue(LocalTime.parse(rowData.getActivity_time().substring(34, 39), fmt));
			spinner_group_end2.getValueFactory().setValue(LocalTime.parse(rowData.getActivity_time().substring(40, 45), fmt));
		}
    	
    	
    }
    
    @FXML
    public void SearchGroupButtonClick(ActionEvent event)
    {
    	String command="";
		if(textfield_search_groupcenter.getText().equals("")&&textfield_search_grouptrainer.getText().equals(""))
		{
			command="select * from Groups where center = "+"\""+combobox_group_center.getValue()+"\"";
		}
		else if(textfield_search_groupcenter.getText().equals(""))
			{
			 showAlertMessage("אתה חייב למלא את השדה מרכז", AlertType.ERROR);
			 return;
			}
			else
				if(textfield_search_grouptrainer.getText().equals(""))
				{
					 showAlertMessage("אתה חייב למלא את השדה מאמן", AlertType.ERROR);
					 return;
				}
				else
				{
					command="select * from Groups where center = " + "\""+textfield_search_groupcenter.getText()+"\""+" "+"And trainer = "+"\""+textfield_search_grouptrainer.getText()+"\"";
				}
			try {
				s.execute(command);
				ResultSet set=s.getResultSet();
			
				for ( int i = 0; i<groups_table.getItems().size(); i++) {
					groups_table.getItems().clear();
				}
			
				while((set!=null) && (set.next()))
				{
					GroupsRow temp=new GroupsRow(set.getInt(1),set.getString(2), set.getString(3), set.getString(5), set.getString(4),set.getString(6),set.getString(7));
					group_list.add(temp);
				}
				groups_table.setItems(group_list);
				groups_table.refresh();
			
			} catch (SQLException e) {
				showAlertMessage("הרשימה לא נימצא במערכת", AlertType.INFORMATION);
				e.printStackTrace();
				return;
			}
    }
    	
    public boolean DateCompare(String date,String local)
    {
    	int localint =Integer.parseInt(local.substring(6))*10000+Integer.parseInt(local.substring(3,5))*1000+Integer.parseInt(local.substring(0,2));
    	int dateint =Integer.parseInt(date.substring(6))*10000+Integer.parseInt(date.substring(3,5))*1000+Integer.parseInt(date.substring(0,2));
    	if(localint>=dateint)
    		return true;
    	return false;
    }
    
    
    @FXML
    public void BackLevelButtonClick()
    {
    	anchorpane_level.setVisible(false);
    	anchorpane_trainee.setVisible(true);
    }
    
    @FXML
    public void AddLevelButtonClick()
    {
    	if(combobox_level_levels.getValue().equals("")||datepicker_level_date.getValue()==null||
    			textfield_level_number.getText().equals(""))
    	{
    		showAlertMessage("אתה חייב למלא את כל השדות", AlertType.ERROR);
    		return;
    	}
    	String command="INSERT into Levels(id,level,level_date,level_info) "+
				   "VALUES('"+textfield_trainee_id.getText()+"','"+combobox_level_levels.getValue()+"','"+s_dateForamt.format(datepicker_level_date.getValue())+
				   "','"+textfield_level_number.getText()+"')";
    	try {
			s.execute(command);
			LevelsButtonClick();
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}

    }
}
