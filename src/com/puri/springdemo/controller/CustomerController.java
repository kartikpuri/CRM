package com.puri.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.puri.springdemo.dao.CustomerDAO;
import com.puri.springdemo.entity.Customer;
import com.puri.springdemo.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/list")
	public String listCustomers(Model model) {
		
		//get customers from dao
		List<Customer> customers = customerService.getCustomers();
		
		//add the customers to the model
		model.addAttribute("customers", customers);
		return "list-customers";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model model) {
		Customer modelCutomer = new Customer();
		model.addAttribute("customer", modelCutomer);
		
		return "customer-form";
	}
	
	@PostMapping("saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer customer ) {
		
		customerService.saveCustomer(customer);
		
		return "redirect:/customer/list";
	}
	
	@GetMapping("showFormForUpdate")
	public String updateCustomer(@RequestParam("customerId")int id, Model model) {
		
		//get the customer from service
		Customer customer = customerService.getCustomer(id);
		
		//set the customer as a model attribute to pre-populate the form
		model.addAttribute("customer", customer);
		
		//send over to our form
		return "customer-form";
		
	}
	
	@GetMapping("delete")
	public String deleteCustomer(@RequestParam("customerId") int id) {
		
		//delete the customer
		customerService.deleteCustomer(id);
		
		return "redirect:/customer/list";
	}
	

}
