package az.rest.repository;

import az.rest.domain.Adam;

import java.util.List;
import java.util.Optional;

public interface AdamRepository {
    List<Adam> getAllAdams();
    List<Adam> getAllAdams(int limit,int offset);
    Optional<Adam> getAdamById(long id);
    boolean updateAdamById(Adam adam);
    boolean deleteAdamById(long id);
    Adam insertAdam(Adam adam);

    //-----Datatable-----
    int getAdamCount();






}
