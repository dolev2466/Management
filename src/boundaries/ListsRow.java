package boundaries;

public class ListsRow 
{
	private int pr_key;
	
	private String center;
	
	private String males;
	
	private String females;
	
	private String trainer;
	

	// end region -> Fields

	// region Constructors

	public ListsRow(int m_pr_key,String m_center,String m_male,String m_female,String m_trainer)
	{
		super();
		pr_key=m_pr_key;
		center=m_center;
		males=m_male;
		females=m_female;
		trainer=m_trainer;
	}
	// end region -> Constructors


	public int getPr_key() {
		return pr_key;
	}


	public void setPr_key(int pr_key) {
		this.pr_key = pr_key;
	}


	public String getCenter() {
		return center;
	}


	public void setCenter(String center) {
		this.center = center;
	}


	public String getMales() {
		return males;
	}


	public void setMales(String males) {
		this.males = males;
	}


	public String getFemales() {
		return females;
	}


	public void setFemales(String females) {
		this.females = females;
	}


	public String getTrainer() {
		return trainer;
	}


	public void setTrainer(String trainer) {
		this.trainer = trainer;
	}
	
}
