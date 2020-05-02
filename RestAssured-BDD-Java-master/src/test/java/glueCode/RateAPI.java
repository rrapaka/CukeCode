package glueCode;


import com.jayway.restassured.response.Response;

import static com.jayway.restassured.RestAssured.get;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


import static org.junit.Assert.assertEquals;

import utilities.Utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hamcrest.Matchers;

public class RateAPI extends Utils {

    private Response response;
    public String path;
    
    
    
    @Given("^I Set GET Rate api endpoint \"([^\"]*)\"$")
    public void i_Set_GET_Rate_api_endpoint(String arg1) throws Throwable {
    	//set the base URI.
    	path = arg1;
               
    }

	@When("^I Set base param request country code as \"([^\"]*)\"$")
    public void i_Set_base_param_request_country_code_as(String arg1) throws Throwable {
		//Add the country params to the base URI
        path = path + "?" + "base=" + arg1;
    }

    @When("^Send GET HTTP request$")
    public void send_GET_HTTP_request() throws Throwable {
    	//post the request
    	response = get(path);
        System.out.println("************The Response value as --" + response.prettyPrint());
    }

    @Then("^I receive valid HTTP response code (\\d+) for \"([^\"]*)\"$")
    public void i_receive_valid_HTTP_response_code_for(int arg1, String arg2) throws Throwable {
    	//validate the status code response
    	assertEquals("Status Check Failed!", arg1, response.getStatusCode());
    }

    @Then("^Response BODY \"([^\"]*)\" is non-empty$")
    public void response_BODY_is_non_empty(String arg1) throws Throwable {
    	//verify the response is not empty.
        response.then().assertThat().body("isEmpty()", Matchers.is(false));
    }

    @Then("^I receive invalid HTTP response code which is not equals to \"([^\"]*)\"$")
    public void i_receive_invalid_HTTP_response_code_which_is_not_equals_to(String arg1) throws Throwable {
    	// negative test - validate the invalid response codes
    	assertEquals("Invalid status Check Failed!", 400, response.getStatusCode());
    }
    
    
    @Given("^to a specific date \"([^\"]*)\"$")
    public void to_a_specific_date(String arg1) throws Throwable {
    	path = path + "/"+ arg1;
    }
    
    @Then("^Response Body should match the current date$")
    public void response_Body_should_match_the_current_date() throws Throwable {
    	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String curr_Date = dateFormat.format(date);	
        assertEquals("Date Value Check Failed!", curr_Date, response.then().extract().path("date"));
    }
}