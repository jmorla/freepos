package com.freelancer.billing.controllers.inventario;

import com.freelancer.billing.domain.Category;
import com.freelancer.billing.service.CategoryService;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;

@Controller
@ManagedBean
@URLMapping(id = "categoria", pattern = "/categoria/add", viewId = "/products/addProducts.xhtml")
@ViewScoped
public class CategoriaController {

    @Autowired
    private CategoryService categoryService;

    private Category category;
    private List<Category> list;

    public void init(){
        list = categoryService.findAll();
        category = new Category();
    }

    public void initNew(){
        init();
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Category> getList() {
        return list;
    }

    public void setList(List<Category> list) {
        this.list = list;
    }

    public void guardar(){
        categoryService.guardar(category);
    }

    public void actualizar(){
        categoryService.actualizar(category);
    }

    public void eliminar(){
        categoryService.eliminar(category);
    }

}
