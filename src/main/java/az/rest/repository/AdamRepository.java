package az.rest.repository;

import az.rest.domain.Adam;

import java.util.List;
import java.util.Optional;

public interface AdamRepository {
    List<Adam> getAllAdams();
    Optional<Adam> getAdamById(long id);
    boolean updateAdamById(Adam adam);
    boolean deleteAdamById(long id);
    Adam insertAdam(Adam adam);

    //-----Datatable-----
    int getAdamCount();





}
