package az.rest.restController;

import az.rest.domain.Adam;
import az.rest.domain.Datatable;
import az.rest.service.AdamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @GetMapping("/datatable")
    public Datatable getAdamListDatatable(
            @RequestParam(name = "draw", required = false, defaultValue = "0") int draw,
            @RequestParam(name = "start", required = false, defaultValue = "0") int start,
            @RequestParam(name = "length", required = false, defaultValue = "5") int length,
            @RequestParam(name = "search[value]", required = false, defaultValue = "") String search,
            @RequestParam(name = "order[0][column]", required = false, defaultValue = "0") int col,
            @RequestParam(name = "order[0][dir]", required = false, defaultValue = "asc") String sortDirection) {


            Datatable datatable = new Datatable();
            datatable.setDraw(draw);
            datatable.setStart(start);
            datatable.setLength(length);
            datatable.setSearch(search);
            datatable.setSortColumn(++col);
            datatable.setSortDirection(sortDirection);

            datatable = adamService.getDataTable(datatable);


            return datatable;
        }

        @GetMapping("/{id}")
        public Adam getAdam ( @PathVariable(name = "id") long id){
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
        public Adam updateAdam (@RequestBody Adam adam){
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
        public void deleteAdam ( @PathVariable(name = "id") long id){
            System.out.println("Delet adam ");
            boolean success = true;
            try {
                success = adamService.deleteAdamById(id);
                if (success) {
                    System.out.println("silindi");
                }
            } catch (Exception e) {
                String message = "Error deleting with id  " + id;
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, message);
            }
            if (!success) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Adam with id " + id + " not found");
            }
        }


        @PostMapping("/adam")
        public void addAdam (@RequestBody Adam adam){
            try {
                System.out.println("tryin ici");
                adamService.insertAdam(adam);
            } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error addin with new adam ");
            }
        }


    }
