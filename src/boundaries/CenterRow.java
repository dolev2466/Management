package boundaries;

public class CenterRow 
{
	// region Fields
		private String classname;
		
		private String price;
		
		private String trainer;
		

		// end region -> Fields

		// region Constructors

		public CenterRow(String m_classname,String m_price,String m_trainer)
		{
			super();
			classname = m_classname;
			price =m_price;
			trainer =m_trainer;
		}
		// end region -> Constructors
		
		// region Getters 

		public String getClassname() {
			return classname;
		}	
		
		public String getPrice() {
			return price;
		}
		
		public String getTrainer() {
			return trainer;
		}
		
		// end region -> Getters

		
		// region Setters
		
		public void setClassname(String m_classname) {
			this.classname = m_classname;
		}
		
		public void setPrice(String m_price) {
			this.price = m_price;
		}

		public void setTrainer(String m_trainer) {
			this.trainer = m_trainer;
		}

		// end region -> Setters
}
