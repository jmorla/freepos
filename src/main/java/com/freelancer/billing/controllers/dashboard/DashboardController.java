package com.freelancer.billing.controllers.dashboard;

import com.ocpsoft.pretty.faces.annotation.URLMapping;
import org.springframework.stereotype.Controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@Controller
@ManagedBean
@RequestScoped
@URLMapping(id = "dashboard", pattern = "/dashboard/", viewId = "/dashboard/dashboard.xhtml")
public class DashboardController {

    private int salesLastMonth;

    private int salesLastWeek;

    private int salesToday;


    public DashboardController(){}
}
