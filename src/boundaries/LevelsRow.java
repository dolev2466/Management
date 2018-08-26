package boundaries;

public class LevelsRow 
{
	private String id;
	
	private String level;
	
	private String date;
	
	private String number;
	
	// end region -> Fields

		// region Constructors

	public LevelsRow(String m_id,String m_level,String m_date,String m_number)
	{
		super();
		id=m_id;
		level=m_level;
		date=m_date;
		number=m_number;
	}
		// end region -> Constructors

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	
}
