package boundaries;

public class TrainersRow
{

	// region Fields
	private String id;
	
	private String name;
	
	private String last_name;
	
	private String phone;

	// end region -> Fields

	// region Constructors

	public TrainersRow(String m_name,String m_lastname,String m_phone)
	{
		super();
		name = m_name;
		last_name =m_lastname;
		phone =m_phone;
	}
	// end region -> Constructors
	
	// region Getters 

	public String getId() {
		return id;
	}	
	
	public String getName() {
		return name;
	}
	
	public String getLast_name() {
		return last_name;
	}
	
	public String getPhone() {
		return phone;
	}
	// end region -> Getters

	
	// region Setters
	
	public void setId(String m_id) {
		this.id = m_id;
	}
	
	public void setName(String m_name) {
		this.name = m_name;
	}

	public void setLast_name(String m_last_name) {
		this.last_name = m_last_name;
	}

	public void setPhone(String m_phone) {
		this.phone = m_phone;
	}
	// end region -> Setters
}
