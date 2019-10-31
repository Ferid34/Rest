package az.rest.service;

import az.rest.domain.Adam;
import az.rest.domain.Datatable;

import java.util.List;
import java.util.Optional;

public interface AdamService {
    List<Adam> getAllAdams();
    Optional<Adam> getAdamById(long id);
    boolean updateAdamById(Adam adam);
    boolean deleteAdamById(long id);
    Adam insertAdam(Adam adam);




    Datatable getDataTable(Datatable datatable);
    //yeni


}
