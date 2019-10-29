package az.rest.controller;

import az.rest.Adam;
import az.rest.service.AdamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
public class WebController {
    @Autowired
    private AdamService adamService;

    @GetMapping("/ajax")
    public String viewIndexAjax() {
        return "web/index";
    }

}
