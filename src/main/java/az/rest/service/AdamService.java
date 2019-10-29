package az.rest.service;

import az.rest.Adam;

import java.util.List;
import java.util.Optional;

public interface AdamService {
    List<Adam> getAllAdams();
    Optional<Adam> getAdamById(long id);
}
