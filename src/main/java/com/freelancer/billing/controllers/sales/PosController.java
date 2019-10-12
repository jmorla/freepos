package com.freelancer.billing.controllers.sales;

import com.freelancer.billing.domain.*;
import com.freelancer.billing.exception.ApplicationException;
import com.freelancer.billing.model.CustomerDTO;
import com.freelancer.billing.model.PaymentDetails;
import com.freelancer.billing.service.CategoryService;
import com.freelancer.billing.service.CustomerService;
import com.freelancer.billing.service.ProductService;
import com.ocpsoft.pretty.faces.annotation.URLMapping;

import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.ManagedBean;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Controller
@ManagedBean
@SessionScoped
@URLMapping(id = "PosView", pattern = "/pos/", viewId = "/sales/pos.xhtml")
public class PosController {

    private final Logger LOGGER = LoggerFactory.getLogger(PosController.class);

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CustomerService customerService;

    private Double subtotal;

    private Double itbis;

    private Double discount;

    private Double total;

    private String barcode; // product to find

    private String productName; //product name to be matched

    private PaymentDetails paymentDetails;

    private Customer customer;

    private List<Item> items = new ArrayList<>();

    private List<Product> products = new ArrayList<>();

    private List<Category> categories = new ArrayList<>();

    private CustomerDTO customerDTO;


    private void init(){

        FacesContext.getCurrentInstance().getViewRoot().setLocale(Locale.US);

        items = new ArrayList<>();

        subtotal = 0.0;
        itbis = 0.0;
        discount = 0.0;
        total = 0.0;
        barcode = null;

        findAllProducts();

        customer = new Customer();

        categories = categoryService.findAll();

        paymentDetails = new PaymentDetails();

        customerDTO = new CustomerDTO();
    }

    public void initNew() {
        this.init();
    }


    public List<Customer> completeCustomer(String value){
        LOGGER.info("Looking for customers using param {}", value);
        List<Customer> customers = customerService.findCustomerByNameOrSocialId(value);

        LOGGER.info("fetching {} customers", customers.size());
        return customers;
    }

    public void addOrder(String productId){
        LOGGER.info("Adding Item");
        //check if the product is already on the order list
        Optional<Item> order = items.stream().filter(e -> e.getProductId().equals(productId)).findFirst();

        if(order.isPresent()){

            raiseOrderQuantity(order.get().getProductId());

        }else {
            items.add(productService.findProductByProductId(productId).map(e ->
                    new Item(e.getId(), e.getBarcode(),  e.getName(), e.getSalePrice(),
                            e.getItbis(), 1, e.getStocks(), e.getSalePrice(), e)
            ).get());

            calculateTotals();
        }

    }

    public void removeOrder(String productId){
        LOGGER.info("Removing order from the list with productId = {}", productId);
        for(Item o : items){
            if(o.getProductId().equals(productId)){
                items.remove(o);
                break;
            }
        }

        calculateTotals();
    }

    public void calculateTotals(){
        subtotal = 0d;
        total = 0d;
        itbis = 0d;

        items.stream().forEach(e -> {
           subtotal += e.getTotal();
           itbis = itbis + ( (e.getTotal() * e.getItbis()) / 100 );
           total = subtotal + itbis - discount;
        });

    }

    public void raiseOrderQuantity(String productId){
        for(Item o : items){
            if(o.getProductId().equals(productId)){

                if(o.getQuantity() < o.getStock()){

                    raiseOrderQuantity(o);

                }else{

                    FacesMessage message = new FacesMessage();
                    message.setSeverity(FacesMessage.SEVERITY_WARN);
                    message.setSummary("Este producto se a agotado");

                    FacesContext.getCurrentInstance().addMessage(null, message);
//                    FacesContext.getCurrentInstance().getViewRoot().setLocale(Locale.ENGLISH);
                }

                break;
            }
        }
        LOGGER.info("total orders {}", items.size());

        calculateTotals();
    }

    public void cancelOrders(){
        LOGGER.info("canceling items");
        initNew();
    }

    public void decreaseOrderQuantity(String productId){
        for(Item o : items){
            if(o.getProductId().equals(productId)){

                if(o.getQuantity() > 1) {

                    decreaseOrderQuantity(o);

                }

                break;
            }

        }

        calculateTotals();
    }

    public void findProductByBarcode() {
        LOGGER.info("Finding product by barcode");
        //check if the product is already on the order list
        Optional<Item> order = items.stream().filter(e -> e.getBarcode().equals(barcode)).findFirst();

        if(order.isPresent()){

            raiseOrderQuantity(order.get().getProductId());

        }else {
            items.add(productService.findProductByBarcode(barcode).map(e ->
                    new Item(e.getId(), e.getBarcode(),  e.getName(), e.getSalePrice(),
                            e.getItbis(), 1, e.getStocks(), e.getSalePrice(), e)
            ).get());

            calculateTotals();
        }

        barcode = null;

    }

    public void generatePaymentDetails() {
        LOGGER.info("Billing details");

        int itemsCount = 0;

        for(Item i : items) {
            itemsCount = itemsCount + i.getQuantity();
        }

        if(this.customer!=null && this.customer.getId()!=null) {
            Customer c = customerService.findCustomerById(this.customer.getId());
            paymentDetails.setCustomerId(c.getId());
            paymentDetails.setCustomerName(c.getFullName());
        }

        paymentDetails.setItemsCount(itemsCount);
        paymentDetails.setTotal(getTotal());
        paymentDetails.setChange(0d);

        LOGGER.info("item size {}", items.size());
    }

    public void findAllProducts(){
        LOGGER.info("Finding product");
        products = productService.findAll();
    }

    public void findProductsByCategory(String categoryId){
        LOGGER.info("Looking for products on category {}", categoryId);

        products = productService.findProductsByCategoryId(categoryId);
    }

    public void findProductsByName() {
        LOGGER.info("looking for products with match name {}", productName);
        if(productName != null && !productName.trim().isEmpty())
            products = productService.findProductsByProductNameLike(productName.trim());
        else
            products = productService.findAll();
    }

    private void raiseOrderQuantity(Item e){
        e.setQuantity( 1+e.getQuantity() ); //raising quantity
        e.setTotal( e.getPrice() * e.getQuantity() ); //recalculating total
    }

    private void decreaseOrderQuantity(Item e){
        e.setQuantity( e.getQuantity()-1 ); //raising quantity
        e.setTotal( e.getPrice() * e.getQuantity() ); //recalculating total
    }

    public void saveCustomer() {
        LOGGER.info("executing save Customer method");
        FacesMessage message;

        try {
            customerService.saveCustomer(customerDTO);
            customerDTO = new CustomerDTO();
            message = new FacesMessage(FacesMessage.SEVERITY_INFO,"Customer saved successfully", null);
            PrimeFaces.current().executeScript("PF('customer-dialog').hide()");
        }catch (ApplicationException ex){
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, ex.getMessage(), null);
        }

        FacesContext.getCurrentInstance().addMessage(null, message);
    }


    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Double getItbis() {
        return itbis;
    }

    public void setItbis(Double itbis) {
        this.itbis = itbis;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public PaymentDetails getPaymentDetails() {
        return paymentDetails;
    }

    public void setPaymentDetails(PaymentDetails paymentDetails) {
        this.paymentDetails = paymentDetails;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public CustomerDTO getCustomerDTO() {
        return customerDTO;
    }

    public void setCustomerDTO(CustomerDTO customerDTO) {
        this.customerDTO = customerDTO;
    }
}
