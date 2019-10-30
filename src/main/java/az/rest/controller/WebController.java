package az.rest.controller;

import az.rest.service.AdamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class WebController {
    @Autowired
    private AdamService adamService;

    /*@GetMapping("/ajax")
    public String viewIndexAjax() {
        return "web/indexAdiTable";
    }*/
    @GetMapping("/ajax")
    public String viewIndexAjax() {
        return "web/index";
    }

}
