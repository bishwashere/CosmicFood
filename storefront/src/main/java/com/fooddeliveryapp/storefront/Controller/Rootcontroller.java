package com.fooddeliveryapp.storefront.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class Rootcontroller {

    @RequestMapping(method = RequestMethod.GET)
    public String SwaggerUi(){
        return "redirect:/swagger-ui.html";
    }
}
