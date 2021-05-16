package org.exl.test.demo.core.javabeans;

/* Stores data returned from the Restful web service */
public class HeroTextBean {
      
      
    private String myJSON_Response ; 
    private String employee_code ; 
     
      
    public String getMyJSON_Response() {
		return myJSON_Response;
	}

	public void setMyJSON_Response(String myJSON_Response) {
		this.myJSON_Response = myJSON_Response;
	}

	public String getEmployee_code() {
		return employee_code;
	}

	public void setEmployee_code(String employee_code) {
		this.employee_code = employee_code;
	}      
  
}