package com.freelancer.billing.controllers.inventario;

import com.freelancer.billing.domain.Location;
import com.freelancer.billing.domain.Providers;
import com.freelancer.billing.exception.DuplicatedResourceException;
import com.freelancer.billing.repository.ProvidersRepository;
import com.freelancer.billing.service.ProvidersService;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.util.List;

@ManagedBean
@Controller
@ViewScoped
@URLMapping(id = "provider", pattern = "/providers/add/", viewId = "/provider/addProvider.xhtml")
public class ProvidersController {

    @Autowired
    private ProvidersService providersService;

    private Providers providers;

    private List<Providers> list;
    
    private Location location;

    private String id;

    private void init(){
        providers = new Providers();
        list = providersService.buscarTodo();
    }

    public void initNew(){
        this.init();
    }

    public Providers getProviders() {
        return providers;
    }

    public void setProviders(Providers providers) {
        this.providers = providers;
    }

    public List<Providers> getList() {
        return list;
    }

    public void setList(List<Providers> list) {
        this.list = list;
    }
    
    

    public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void guardar(){

        try {
            providersService.guardar(providers);
            init();
            FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Proveedor agregado!"));
        }catch (DuplicatedResourceException e){
            FacesContext.getCurrentInstance().addMessage("mensaje", new FacesMessage(FacesMessage.SEVERITY_WARN, "Incorrecto", e.getMessage()));
        }

    }

    public void actualizar(){
	    providersService.actualizar(providers);
	    init();
    }

    public void eliminar(){
        providersService.eliminar(providers);
        init();
    }

    public void buscarPorId(String id){
        providers = providersService.buszarPorId(id);
    }

}
