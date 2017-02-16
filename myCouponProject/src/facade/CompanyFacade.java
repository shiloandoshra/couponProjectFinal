package facade;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import exceptions.DuplicateCouponTypeException;
import exceptions.MyExceptions;
import exceptions.WrongDataInputException;
import members.Company;
import dbdao.CompanyDBDAO;
import members.Coupon;
import facade.CouponClientFacade;
import dbdao.CouponDBDAO;
import utilities.CouponType;

/**
 * The class CompanyFacade is part of myCouponProject
 * Facade is a design pattern that is being used in myCouponProject
 * @author oshra & shilo
 *
 */
public class CompanyFacade implements CouponClientFacade{

	long loginId;
	private CompanyDBDAO daocompany= new CompanyDBDAO();
	private CouponDBDAO daocoupon = new CouponDBDAO();
	/**
	 * method createCoupon creates a new coupon by calling CouponDBDAO
	 * @see CouponDBDAO
	 * @param Coupon coupon stores the coupon that is being added to the company's coupons
	 */
	public void createCoupon(Coupon coupon) {
		
	
		try {
			daocompany.createCoupon(coupon);
		} catch (ClassNotFoundException | SQLException | DuplicateCouponTypeException | InterruptedException | ParseException e) {
			e.printStackTrace();
			MyExceptions.Exceptions(e, coupon.getTitle());
		}
				
		}

	/**
	 * method removeCoupon removes a coupon by calling CouponDBDAO
	 * @see CouponDBDAO
	 * @param Coupon coupon stores the coupon that is being removed from the company's coupons
	 */
	public void removeCoupon(Coupon coupon) {
		try {
			daocoupon.removeCoupon(coupon);
		} catch (ClassNotFoundException | SQLException | InterruptedException | ParseException e) {
			MyExceptions.Exceptions(e);

		}
	
	
	}

	/**
	 * method updateCoupon updates an existing coupon by calling CouponDBDAO
	 * @see CouponDBDAO
	 * @param Coupon coupon stores the coupon that is being updated in the company's coupons
	 */
	
	public void updateCoupon(Coupon coupon) {
		try {
			daocoupon.updateCoupon(coupon);
		} catch (ClassNotFoundException | SQLException | WrongDataInputException | InterruptedException e) {
			MyExceptions.Exceptions(e);
		}

		
	}
	/**
	 * method getCoupon returns a coupon according to an ID it receives
	 * @param long id stores the id of the coupon that needs to be returned
	 * @return Coupon coupon returns the coupon with the matching ID
	 */
	
	
	public Coupon getCoupon(long id)  {
			try {
				return daocoupon.getCoupon(id);
			} catch (ClassNotFoundException | SQLException | InterruptedException | ParseException e) {
				MyExceptions.Exceptions(e,"the coupon id "+id+" was not found");
			}
	
		return null;
	}

	/**
	 * method getAllCoupons returns all of the company's coupons
	 * @return Collection<Coupon> returns all of the company's coupons
	 */
	public Collection<Coupon> getAllCoupons() {
		
			try {
				return daocompany.getCoupons();
			} catch (SQLException | ClassNotFoundException | InterruptedException | ParseException e) {
				MyExceptions.Exceptions(e);
			}
		
		return null;
	}
	/**
	 * method getCouponByType returns all coupons of a specific type
	 * @param CouponType couponType stores the type of coupons that need to be returned
	 * @return Collection<Coupon> couponsFinal returns all the coupons with the matching type
	 */
	
	public Collection<Coupon> getCouponsByType(CouponType couponType) {
		Coupon temp= null;
		Set<Coupon> couponsFinal =new HashSet<>(); 
		Set<Coupon> coupons =new HashSet<>();
		try {
			coupons=(Set<Coupon>) daocompany.getCoupons();
		} catch (SQLException | ClassNotFoundException | InterruptedException | ParseException e) {
			MyExceptions.Exceptions(e);
		}
		Iterator<Coupon> iter = coupons.iterator();
		while(iter.hasNext())
		{
			temp = iter.next();
			if (temp.getType().equals(couponType))
			{
				couponsFinal.add(temp);
			}
		}
		return couponsFinal;
	}
	/**
	 * method getCouponByPrice returns all coupons that are below a certain price
	 * @param double price stores the maximum price of the coupons that need to be returned
	 * @return Collection<Coupon> couponsFinal returns all the coupons below the maximum price
	 */
		public Collection<Coupon> getCouponsByPrice(double price) {
		Coupon temp= null;
		Set<Coupon> couponsFinal =new HashSet<>(); 
		Set<Coupon> coupons =new HashSet<>();
		try {
			coupons=(Set<Coupon>) daocompany.getCoupons();
		} catch (SQLException | ClassNotFoundException | InterruptedException | ParseException e) {
			MyExceptions.Exceptions(e);
		}
		Iterator<Coupon> iter = coupons.iterator();
		while(iter.hasNext())
		{
			temp = iter.next();
			if (temp.getPrice()<price)
			{
				couponsFinal.add(temp);
			}
		}
		return couponsFinal;
	} 
		/**
		 * method getCouponByDate returns all coupons that start before a certain date
		 * @param Date date stores the date that we are comparing all coupons to
		 * @return Collection<Coupon> couponsFinal returns all the coupons that start before the given date
		 */
	public Collection<Coupon> getCouponsByDate(Date date) {
		Coupon temp= null;
		Set<Coupon> couponsFinal =new HashSet<>(); 
		Set<Coupon> coupons =new HashSet<>();
		try {
			coupons=(Set<Coupon>) daocompany.getCoupons();
		} catch (SQLException | ClassNotFoundException | InterruptedException | ParseException e) {
			MyExceptions.Exceptions(e);
		}
		Iterator<Coupon> iter = coupons.iterator();
		while(iter.hasNext())
		{
			temp = iter.next();
			if (temp.getStartDate().before(date))
			{
				couponsFinal.add(temp);
			}
		}
		return couponsFinal;
	}
	/**
	 * method printCompany returns the whole company
	 * @return Company returns the company 
	 */
	public Company printCompany()	
	{
		try {
			return daocompany.printCompany();
		} catch (SQLException | ClassNotFoundException | InterruptedException e) {
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
		
				try {
					if (daocompany.login(name, password))
					{
						this.loginId= daocompany.getLoginId();
						return this;
					}
				} catch (ClassNotFoundException | SQLException | InterruptedException e) {
					MyExceptions.Exceptions(e);
				}

		return null;
	}


}
