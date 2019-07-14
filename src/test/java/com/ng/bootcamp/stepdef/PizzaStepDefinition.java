package com.ng.bootcamp.stepdef;

import java.util.List;

import com.pages.BasePage;
import com.pages.pizza.Pizza;

import cucumber.api.DataTable;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;

public class PizzaStepDefinition {
	static Pizza pizza;

	@Before
	public void driverSetup() {
		if (BasePage.getDriver() == null) {
			BasePage.setDriver("chrome");
		}
	}

	@Given("^User goes to home page \"([^\"]*)\"$")
	public void user_goes_to_home_page(String url) throws Throwable {
		BasePage.homePage(url);
	}

	@When("^User clicks on (.+)$")
	public void user_clicks_on(String link) throws Throwable {
		pizza = new Pizza();
		pizza.pizzaLink();
	}

	@Then("^User is on (.+) page$")
	public void user_is_on_page(String validate) throws Throwable {
		Assert.assertEquals(validate, pizza.getTitle());
	}

	@When("^User clicks on Piza$")
	public void user_clicks_on_Piza() throws Throwable {
	}

	@Given("^User is on \"([^\"]*)\" page - number is (\\d+) and cost is (.+)$")
	public void user_is_on_page(String title, int value, double arg) throws Throwable {
		System.out.println("Title:" + title + ", number:" + value + ", Cost:" + arg);
		Assert.assertEquals(title, pizza.getTitle());
	}

	@Given("^Customer \"([^\"]*)\" \"([^\"]*)\"$")
	public void user(String first, String last) throws Throwable {
		pizza.firstName(first);
		pizza.lastName(last);
	}

	@Given("^Called from number \"([^\"]*)\"$")
	public void called_from_number(String phone) throws Throwable {
		pizza.phone(phone);
	}

	@Given("^Orders \"([^\"]*)\" pizza using \"([^\"]*)\" crust and following toppings$")
	public void orders_pizza_using_crust_and_following_toppings(String size, String crust, DataTable toppings)
			throws Throwable {
		pizza.size(size);
		pizza.crust(crust);
		List<List<String>> topping = toppings.raw();
		pizza.selectTopping(topping.get(0).get(0));
		pizza.selectTopping(topping.get(1).get(0));
	}
	/*
	public void test(io.cucumber.datatable.DataTable listOfObject) { 1.2.5
		List<List<String>> list = listOfObject.cells();
		for (List<String> one:list) {
			one.get(0)
		}
		}
	*/
	@Given("^Wants pizza \"([^\"]*)\" to address$")
	public void wants_pizza_to_address(String deliveryMethod, DataTable deleviryAddress) throws Throwable {
		pizza.deliveryMethod(deliveryMethod);
		
		
		List<List<String>> address = deleviryAddress.raw();
		pizza.address1(address.get(0).get(0));
		pizza.city(address.get(0).get(1));
		pizza.state(address.get(0).get(2));
		pizza.zip(address.get(0).get(3));
	}

	@Given("^With special instructions\"([^\"]*)\"$")
	public void with_special_instructions(String instruction) throws Throwable {
		pizza.instructions("Please hurry!!! I am hungry");
	}

	@Given("^Orders the pizza$")
	public void orders_the_pizza() throws Throwable {
		pizza.order();
	}

	@Then("^Order is verified$")
	public void order_is_verified() throws Throwable {
		String actualResult = pizza.verify();
		Assert.assertEquals("Click OK to return to the Main Dashboard", actualResult);
	}

	@Then("^Call customer service$")
	public void call_customer_service() throws Throwable {
		System.out.println("I am here");
		String actualResult = pizza.verify();
		Assert.assertNotSame("Call Customer Servide!", "Click OK to return to the Main Dashboard", actualResult);
	}

}
