package az.rest.service;

import az.rest.Adam;
import az.rest.repository.AdamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdamServiceImpl implements AdamService
{
    @Autowired
    private AdamRepository adamRepository;


    @Override
    public List<Adam> getAllAdams() {
        return adamRepository.getAllAdams();
    }

    @Override
    public Optional<Adam> getAdamById(long id) {
        return adamRepository.getAdamById(id);
    }

    @Override
    public boolean updateAdamById(Adam adam) {
        return adamRepository.updateAdamById(adam);
    }

    @Override
    public boolean deleteAdamById(long id) {
        return adamRepository.deleteAdamById(id);
    }

    @Override
    public Adam insertAdam(Adam adam) {
        return adamRepository.insertAdam(adam);
    }


}
