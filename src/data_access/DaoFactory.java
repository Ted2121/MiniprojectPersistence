package data_access;

import data_access.*;

public class DaoFactory {
	private static ClothingDaoImplementation clothingDao;
	private static CustomerDaoImplementation customerDao;
	private static InvoiceDaoImplementation invoiceDao;
	private static ItemDaoImplementation itemDao;
	private static ProductDaoImplementation productDao;
	private static SaleOrderDaoImplementation saleOrderDao;
	private static SupplierDaoImplementation supplierDao;
	private static SaleOrder_ProductDaoImplementation saleOrder_ProductDao;
	
	private DaoFactory(){}
	
	public static ClothingDaoImplementation getClothingDao() {
		if(clothingDao == null) {
			clothingDao = new ClothingDaoImplementation();
		}
		return clothingDao;
	}
	
	public static CustomerDaoImplementation getCustomerDao() {
		if(customerDao == null) {
			customerDao = new CustomerDaoImplementation();
		}
		return customerDao;
	}
	
	public static InvoiceDaoImplementation getInvoiceDao() {
		if(invoiceDao == null) {
			invoiceDao = new InvoiceDaoImplementation();
		}
		return invoiceDao;
	}
	
	public static ItemDaoImplementation getItemDao() {
		if(itemDao == null) {
			itemDao = new ItemDaoImplementation();
		}
		return itemDao;
	}
	
	public static ProductDaoImplementation getProductDao() {
		if(productDao == null) {
			productDao = new ProductDaoImplementation();
		}
		return productDao;
	}
	
	public static SaleOrderDaoImplementation getSaleOrderDao() {
		if(saleOrderDao == null) {
			saleOrderDao = new SaleOrderDaoImplementation();
		}
		return saleOrderDao;
	}
	
	public static SupplierDaoImplementation getSupplierDao() {
		if(supplierDao == null) {
			supplierDao = new SupplierDaoImplementation();
		}
		return supplierDao;
	}
	
	public static SaleOrder_ProductDaoImplementation getSaleOrder_ProductDao() {
		if(saleOrder_ProductDao == null) {
			saleOrder_ProductDao = new SaleOrder_ProductDaoImplementation();
		}
		return saleOrder_ProductDao;
	}
	
	
}
