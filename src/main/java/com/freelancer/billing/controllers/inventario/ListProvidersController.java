package com.freelancer.billing.controllers.inventario;

import com.freelancer.billing.domain.Providers;
import com.freelancer.billing.service.ProvidersService;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;

@Controller
@ManagedBean
@ViewScoped
@URLMapping(id = "listProvider", pattern = "/providers/view/", viewId = "/provider/providers.xhtml")
public class ListProvidersController {

    @Autowired
    private ProvidersService providersService;

    private List<Providers> list;

    public void init(){
        list = providersService.buscarTodo();
    }

    public void initNew(){
        init();
    }

    public List<Providers> getList() {
        return list;
    }

    public void setList(List<Providers> list) {
        this.list = list;
    }
}
