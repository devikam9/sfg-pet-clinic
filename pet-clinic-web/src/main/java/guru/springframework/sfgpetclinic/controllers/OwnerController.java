package guru.springframework.sfgpetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OwnerController {

   @RequestMapping ({"/owners","/owners/index","/owners/index.html"})
   public String listOwners (){

        return "owners/index";
    }

    @RequestMapping ({"/owners/","/owners/index/"})
    public String listingOwners (){

        return "owners/index";
    }
}
