package facade;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import dbdao.CustomerDBDAO;
import exceptions.CustomerHasCouponAlready;
import exceptions.MyExceptions;
import exceptions.UnavailableCouponException;
import members.Coupon;
import facade.CouponClientFacade;
import dbdao.CouponDBDAO;
import utilities.CouponType;
/**
 * The class CustomerFacade is part of myCouponProject
 * Facade is a design pattern that is being used in myCouponProject
 * @author oshra & shilo
 *
 */

public class CustomerFacade implements CouponClientFacade{

	long loginId;
	private CustomerDBDAO daocustomer= new CustomerDBDAO();
	private CouponDBDAO daocoupon = new CouponDBDAO();

	/**
	 * method purchaseCoupon purchases a coupon
	 * @param Coupon coupon stores the coupon that is being purchased
	 * @see purchaseCoupon in CustomerDBDAO
	 */
	public void purchaseCoupon (Coupon coupon) 
	{
			try 
			{
				daocustomer.purchaseCoupon(coupon);
			} catch (SQLException | CustomerHasCouponAlready | UnavailableCouponException | ClassNotFoundException | InterruptedException | ParseException e) 
			{
					MyExceptions.Exceptions(e);	
	
			}
		
	}
	/**
	 * method getAllPurchasedCoupons returns all purchased coupons
	 * @return Collection<Coupon> returns all purchased coupons 
	 * @see getCoupons in CustomerDBDAO 
	 */
	public Collection<Coupon> getAllPurchasedCoupons() 
	{
		Set<Coupon> coupons =new HashSet<>(); 
		try {
			coupons=(Set<Coupon>) daocustomer.getCoupons();
		} catch (SQLException | ClassNotFoundException | InterruptedException | ParseException e) {
			MyExceptions.Exceptions(e);

		}
		return coupons;
	}
	/**
	 *  method getAllPurchasedCouponsByType returns all purchased coupons of a certain type
	 * @param CouponType type stores the type of coupons that need to be returned
	 * @return Collection<Coupon> returns all purchased coupons of the required type
	 * @see getCouponsByType in CouponDBDAO
	 */
	public Collection<Coupon> getAllPurchasedCouponsByType(CouponType type) 
	{
		Coupon temp= null;
		Set<Coupon> couponsFinal =new HashSet<>(); 
		Set<Coupon> coupons =new HashSet<>(); 
		try {
			coupons=(Set<Coupon>) daocoupon.getCouponByType(type.toString());
		} catch (SQLException | ClassNotFoundException | InterruptedException | ParseException e) {
			MyExceptions.Exceptions(e);

		}
		Iterator<Coupon> iter = coupons.iterator();
		while(iter.hasNext())
		{
			temp = iter.next();
			try {
				if (daocustomer.checkCustomerToCoupon(loginId, temp.getId()))
				{
					couponsFinal.add(temp);
				}
			} catch (SQLException | ClassNotFoundException | InterruptedException e) {
				MyExceptions.Exceptions(e);

			}
		}
		return couponsFinal;
	}
	/**
	 *  method getAllPurchasedCouponsByPrice returns all purchased coupons bellow a certain price
	 * @param double price stores the maximum price of the coupons that need to be returned
	 * @return Collection<Coupon> returns all purchased coupons that are below the maximum price
	 */
	public Collection<Coupon> getAllPurchasedCouponsByPrice(double price){
		Coupon temp= null;
		Set<Coupon> couponsFinal =new HashSet<>(); 
		Set<Coupon> coupons =new HashSet<>();
		try {
			coupons=(Set<Coupon>) daocustomer.getCoupons();
		} catch (SQLException | ClassNotFoundException | InterruptedException | ParseException e) {
			MyExceptions.Exceptions(e);

		}
		Iterator<Coupon> iter = coupons.iterator();
		while(iter.hasNext())
		{
			temp = iter.next();
			if (temp.getPrice()<=price)
			{
				couponsFinal.add(temp);
			}
		}
		return couponsFinal;
	}	
	/**
	 * this method logs in 
	 * @return if successful it returns itself otherwise returns null
	 */
	@Override
	public CouponClientFacade login(String name, String password, String clientType) {
		try {
			if (daocustomer.login(name, password))
			{
				return this;
			}
		} catch (SQLException | ClassNotFoundException | InterruptedException e) {
			MyExceptions.Exceptions(e);

		}
		return null;
	}

}
