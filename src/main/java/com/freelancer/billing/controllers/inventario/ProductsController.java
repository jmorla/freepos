package com.freelancer.billing.controllers.inventario;

import com.freelancer.billing.domain.Category;
import com.freelancer.billing.domain.Product;
import com.freelancer.billing.domain.Providers;
import com.freelancer.billing.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import com.ocpsoft.pretty.faces.annotation.URLMapping;
import org.springframework.stereotype.Controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.util.List;

@Controller
@ManagedBean
@ViewScoped
@URLMapping(id="productsView", pattern="/products/add/", viewId="/products/addProducts.xhtml" )
public class ProductsController {

    @Autowired
    private ProductService productService;

    private Product product;

    private List<Product> list;

    private Category category;

    private Providers providers;

    private String idProd;

    public void init(){
        list = productService.findAll();
        product = new Product();
        category = new Category();
        providers = new Providers();
    }

    public void initNew(){
        init();
    }

    public ProductService getProductService() {
        return productService;
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Product> getList() {
        return list;
    }

    public void setList(List<Product> list) {
        this.list = list;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Providers getProviders() {
        return providers;
    }

    public void setProviders(Providers providers) {
        this.providers = providers;
    }

    public String getIdProd() {
        return idProd;
    }

    public void setIdProd(String idProd) {
        this.idProd = idProd;
    }

    //Metodo para guardar un producto
    public void guardar(){
        try {
            product.setProviders(providers);
            product.setCategory(category);
            productService.saveProduct(product);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Producto agregado"));
            init();
        }catch (Exception e){
            e.getMessage();

        }
    }

    //Metodo para actualizar un producto
    public void actualizar(){
        productService.saveProduct(product);
        init();
    }


    //Metodo para eliminar un producto
    public void eliminar(){
        productService.deleteProduct(product);
        init();
    }

    //Metodo para listar todos los productos
    public List<Product> listarProductos(){
        list = productService.findAll();

        return list;
    }

    public void buscarProductoPorId(String idProd){

       product= productService.findProductByProductId(idProd).get();
    }
    
    public void cancelar() {
    	initNew();
    }
}
