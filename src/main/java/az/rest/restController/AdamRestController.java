package az.rest.restController;

import az.rest.Adam;
import az.rest.service.AdamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;


@RequestMapping("/rest/")
@RestController
public class AdamRestController {
    @Autowired
    private AdamService adamService;


    @GetMapping("/adams")
    public List<Adam> getAdamList() {
        System.out.println("adamService.getAllAdams()=="+adamService.getAllAdams());
        return adamService.getAllAdams();
    }
//kohne qayda ile
/*    @GetMapping("/adams/{id}")
    public ResponseEntity<Adam> getAdam(@PathVariable(name = "id") long id) {
        Optional<Adam> adamOptional = adamService.getAdamById(id);
        ResponseEntity<Adam> entity = null;
        Adam adam = null;
        if (adamOptional.isPresent()) {
            adam = adamOptional.get();
            entity = new ResponseEntity<>(adam, HttpStatus.OK);
        }else{
            entity=new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return entity;
    }*/

    //yeni qayda ile
    @GetMapping("/adams/{id}")
    public Adam getAdam(@PathVariable(name = "id") long id) {
        Adam adam = null;
        try {
            Optional<Adam> adamOptional = adamService.getAdamById(id);

            if (adamOptional.isPresent()) {
                adam = adamOptional.get();

            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "adam with id " + id + " not found");
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return adam;
    }


    @PostMapping("/register")
    public ResponseEntity<Boolean> register(@RequestBody Adam adam) {

        System.out.println("postmandan gelen adam  " + adam);
        return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
    }


}
