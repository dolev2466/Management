package boundaries;

public class GroupsRow 
{
	private int pr_key;
	
	private String trainer;
	
	private String group;
	
	private String activity_time;
	
	private String center;
	
	private String males;
	
	private String females;
	
	// end region -> Fields

	// region Constructors

	public GroupsRow(int m_pr_key,String m_trainer,String m_group,String m_activity_time,String m_center,String m_males,String m_females)
	{
		super();
		pr_key=m_pr_key;
		group=m_group;
		activity_time=m_activity_time;
		trainer=m_trainer;
		center=m_center;
		males=m_males;
		females=m_females;
	}
	// end region -> Constructors
	
	public int getPr_key() {
		return pr_key;
	}

	public void setPr_key(int pr_key) {
		this.pr_key = pr_key;
	}

	public String getTrainer() {
		return trainer;
	}

	public void setTrainer(String trainer) {
		this.trainer = trainer;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getActivity_time() {
		return activity_time;
	}

	public void setActivity_time(String activity_time) {
		this.activity_time = activity_time;
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
	
	
	
}
