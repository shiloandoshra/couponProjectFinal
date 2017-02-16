package facade;
import java.sql.SQLException;

import java.text.ParseException;
import java.util.Collection;

import exceptions.DuplicateUserException;
import exceptions.MyExceptions;
import exceptions.WrongDataInputException;
import exceptions.NullPointerException;
import members.Company;
import dbdao.CompanyDBDAO;
import members.Customer;
import dbdao.CustomerDBDAO;
/**
 * The class AdminFacade is part of myCouponProject
 * Facade is a design pattern that is being used in myCouponProject
 * AdminFacade is the facade for the admin user
 * @author Oshra & Shilo
 *
 */
public class AdminFacade implements CouponClientFacade{

	private CompanyDBDAO daocompany= new CompanyDBDAO();
	private CustomerDBDAO daocustomer= new CustomerDBDAO();
	private String admin="admin";
	private String password="password";
	/**
	 * method createCompany creates a new company by calling CompanyDBDAO
	 * @see CompanyDBDAO
	 * @param Company company stores the company that is being created
	 */
	
	public void createCompany(Company company)  
	{
		try {
			daocompany.createCompany(company);
		} catch (SQLException | ClassNotFoundException | InterruptedException | DuplicateUserException e) {
			MyExceptions.Exceptions(e,company.getCompName());
		}
	}
	/**
	 * method removeCompany removes a company by calling CompanyDBDAO
	 * @see CompanyDBDAO
	 * @param Company company stores the company that is being removed
	 */
	public void removeCompany(Company company)  
	{		
		try {
			daocompany.removeCompany(company);
		} catch (SQLException | ClassNotFoundException | InterruptedException | NullPointerException | ParseException e) {
			MyExceptions.Exceptions(e,company.getCompName());
		}
	}

	/**
	 * method updateCompany updates an exsisting caompany by calling CompanyDBDAO
	 * @see CompanyDBDAO
	 * @param Company company stores the company that is being updated
	 */
	public void updateCompany(Company company)  
	{
		
			try {
				daocompany.updateCompany(company);
			} catch (SQLException | WrongDataInputException | ClassNotFoundException | InterruptedException e) {
				MyExceptions.Exceptions(e);
			}
		
	}

	/**
	 * method getCompany returns a company according to its id by calling CompanyDBDAO
	 * @see CompanyDBDAO
	 * @param long id stores the id of the company that needs to be returned
	 * @return returns Company with the matching id if not found returns null
	 */
	public Company getCompany(long id)  
	{
		try {
			return daocompany.getCompany(id);
		} catch (SQLException | ClassNotFoundException | InterruptedException e) {
			MyExceptions.Exceptions(e);
		}
		return null;
	}

	/**
	 * method getAllCompanies returns all companies by calling CompanyDBDAO
	 * @see CompanyDBDAO
	 * @return returns Collection<Company> returns all companies if not found returns null
	 */
	public Collection<Company> getAllCompanies() 
	{
		try {
			return daocompany.getAllCompanies();
		} catch (SQLException | ClassNotFoundException | InterruptedException e) {
			MyExceptions.Exceptions(e);
		}
		return null;
	}
	
	/**
	 * method createCustomer creates a new customer by calling CustomerDBDAO
	 * @see CustomerDBDAO
	 * @param Customer customer stores the customer that is being created
	 */
	public void createCustomer(Customer customer) {
		
				try {
					daocustomer.createCustomer(customer);
				} catch (ClassNotFoundException | SQLException
						| InterruptedException | DuplicateUserException e) {
				MyExceptions.Exceptions(e,customer.getCustName());
				}

	}

	/**
	 * method removeCustomer removes a customer by calling CustomerDBDAO
	 * @see CustomerDBDAO
	 * @param Customer customer stores the customer that is being removed
	 */
	public void removeCustomer(Customer customer){

			try {
				daocustomer.removeCustomer(customer);
			} catch (ClassNotFoundException | SQLException | InterruptedException | WrongDataInputException e) {
				MyExceptions.Exceptions(e);
			}
	
	
	}

	/**
	 * method updateCustomer updates an existing customer by calling CustomerDBDAO
	 * @see CustomerDBDAO
	 * @param Customer customer stores the customer that is being updated
	 */
	public void updateCustomer(Customer customer){

			try {
				daocustomer.updateCustomer(customer);
			} catch (ClassNotFoundException | WrongDataInputException | SQLException | InterruptedException e) {
				MyExceptions.Exceptions(e);
			}

	}

	/**
	 * method getCustomer returns a customer according to its id by calling CustomerDBDAO
	 * @see CustomerDBDAO
	 * @param long id stores the id of the customer that needs to be returned
	 * @return returns Customer with the matching id if not found returns null
	 */
	public Customer getCustomer(long id) {

			try {
				return daocustomer.getCustomer(id);
			} catch (ClassNotFoundException | SQLException | InterruptedException e) {
				MyExceptions.Exceptions(e);
			}

	return null;
	}

	/**
	 * method getAllCustomers returns all customers by calling CustomerDBDAO
	 * @see CustomrDBDAO
	 * @return returns Collection<Customer> returns all customers if not found returns null
	 */
	public Collection<Customer> getAllCustomers(){
	
				try {
					return daocustomer.getAllCustomers();
				} catch (ClassNotFoundException | SQLException | InterruptedException e) {
					MyExceptions.Exceptions(e);
				}
	
		return null;
	}
	/**
	 * this method logs in 
	 * @return if successful it returns itself otherwise returns null
	 */
	@Override
	public CouponClientFacade login(String name, String password, String clientType) {
		if (name.equals(this.admin) && password.equals(this.password))
		{
			return this;
		}
		return null;
	}
}