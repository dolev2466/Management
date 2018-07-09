package boundaries;

import java.sql.Date;

public class TraineesRow 
{

	// region Fields
	
	private String id;
	
	private String name;
	
	private String last_name;
	
	private String center;
	
	private String group;
	
	private Date register_date;
	
	private Date subscription_end_date;
	
	// end region -> Fields
	
	// region Constructors

		public TraineesRow(String m_id,String m_name,String m_lastname,String m_center,String m_group
							,Date m_register,Date m_end_date)
		{
			super();
			id=m_id;
			name = m_name;
			last_name =m_lastname;
			center=m_center;
			group=m_group;
			register_date=m_register;
			subscription_end_date=m_end_date;
			
		}
		// end region -> Constructors

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getLast_name() {
			return last_name;
		}

		public void setLast_name(String last_name) {
			this.last_name = last_name;
		}

		public String getCenter() {
			return center;
		}

		public void setCenter(String center) {
			this.center = center;
		}

		public String getGroup() {
			return group;
		}

		public void setGroup(String group) {
			this.group = group;
		}

		public Date getRegister_date() {
			return register_date;
		}

		public void setRegister_date(Date register_date) {
			this.register_date = register_date;
		}

		public Date getSubscription_end_date() {
			return subscription_end_date;
		}

		public void setSubscription_end_date(Date subscription_end_date) {
			this.subscription_end_date = subscription_end_date;
		}
		
		

}
