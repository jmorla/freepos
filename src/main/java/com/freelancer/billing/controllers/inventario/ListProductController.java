package com.freelancer.billing.controllers.inventario;

import com.freelancer.billing.domain.Product;
import com.freelancer.billing.service.ProductService;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;

@Controller
@ManagedBean
@ViewScoped
@URLMapping(id = "list", pattern = "/products/view/", viewId = "/products/products.xhtml")
public class ListProductController {

    @Autowired
    private ProductService productService;

    private List<Product> list;

    public void init(){
        list = productService.findAll();
    }

    public void initNew(){
        init();
    }

    public List<Product> getList() {
        return list;
    }

    public void setList(List<Product> list) {
        this.list = list;
    }
}
