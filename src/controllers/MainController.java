package controllers;

import java.util.ArrayList;
import java.io.InputStream;
import java.sql.*;

import boundaries.CenterRow;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import jdk.nashorn.internal.ir.BreakableNode;

public class MainController extends BaseController 
{
	private String title;
	
	private String[] window = { "מאמנים", "מרכזים","רשימות" };
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
        	        "jdbc:ucanaccess://C:\\Users\\Dolev\\eclipse-workspace\\Managment\\src\\Database.accdb");
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
		}
			view = getClass().getResourceAsStream("/boundaries/images/name.jpg");
			if (view != null) {
				Image = new Image(view);
				image_name.setImage(Image);
			}
		
		view = getClass().getResourceAsStream("/boundaries/images/family.jpg");
		if (view != null) {
			Image = new Image(view);
			image_lastname.setImage(Image);
		}
		
		view = getClass().getResourceAsStream("/boundaries/images/birthday.jpg");
		if (view != null) {
			Image = new Image(view);
			image_birthday.setImage(Image);
		}
		
		view = getClass().getResourceAsStream("/boundaries/images/city.jpg");
		if (view != null) {
			Image = new Image(view);
			image_city.setImage(Image);
		}
		
		view = getClass().getResourceAsStream("/boundaries/images/address.png");
		if (view != null) {
			Image = new Image(view);
			image_address.setImage(Image);
		}
		
		view = getClass().getResourceAsStream("/boundaries/images/postcode.jpg");
		if (view != null) {
			Image = new Image(view);
			image_postcode.setImage(Image);
		}
		
		view = getClass().getResourceAsStream("/boundaries/images/phone.jpg");
		if (view != null) {
			Image = new Image(view);
			image_phone.setImage(Image);
		}
		
		view = getClass().getResourceAsStream("/boundaries/images/cellphone.jpg");
		if (view != null) {
			Image = new Image(view);
			image_cellphone.setImage(Image);
		}
		
		view = getClass().getResourceAsStream("/boundaries/images/email.jpg");
		if (view != null) {
			Image = new Image(view);
			image_email.setImage(Image);
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
			break;

			case "מרכזים":
				anchorpane_trainers.setVisible(false);
				anchorpane_centers.setVisible(true);
				anchorpane_lists.setVisible(false);
				SetCenterTabInVisable();

			break;

			case "רשימות":
					anchorpane_trainers.setVisible(false);
					anchorpane_centers.setVisible(false);
					anchorpane_lists.setVisible(true);
			
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
			// TODO Auto-generated catch block
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
				// TODO Auto-generated catch block
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
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
	}
	public void DeleteFromTrainerTable(String id)
	{
		String delete="delete from Trainers where ID = "+id;
		try {
			s.execute(delete);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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



}
