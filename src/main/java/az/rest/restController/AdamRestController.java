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


    @GetMapping("/")
    public List<Adam> getAdamList() {
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
    @GetMapping("/{id}")
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

    @PutMapping("/adam")
    public Adam updateAdam(@RequestBody Adam adam) {
        System.out.println("adam= " + adam);
        try {
            Optional<Adam> adamOptional = adamService.getAdamById(adam.getId());

            if (adamOptional.isPresent()) {
                adamService.updateAdamById(adam);
            } else {
                adamService.insertAdam(adam);
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return adam;
    }

    @DeleteMapping("adam/{id}")
    public void deleteAdam(@PathVariable(name = "id") long id) {
        System.out.println("Delet adam ");
        boolean success = true;
        try {
         success=adamService.deleteAdamById(id);
         if(success){
             System.out.println("silindi");
         }
        } catch (Exception e) {
            String message = "Error deleting with id  " + id;
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, message);
        }
        if(!success){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Adam with id "+ id+" not found");
        }
    }






    @PostMapping("/adam")
    public void addAdam(@RequestBody Adam adam) {
try{
    System.out.println("tryin ici");
    adamService.insertAdam(adam);
}catch (Exception e){
    throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Error addin with new adam ");
}
    }


}
