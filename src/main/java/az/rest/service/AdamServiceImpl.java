package az.rest.service;

import az.rest.domain.Adam;
import az.rest.domain.Datatable;
import az.rest.repository.AdamRepository;
import az.rest.repository.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AdamServiceImpl implements AdamService {
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


    @Override
    public Datatable getDataTable(Datatable datatable) {
        Map<Integer,String> columnMapping=new HashMap<>();
        columnMapping.put(1,"id");
        columnMapping.put(2,"name");
        columnMapping.put(3,"age");
        String sql= SQLQuery.GET_ALL_ADAMS_LIMIT_OFSET;
      sql=  sql.replace("{SORT_COLUMN}",columnMapping.get(datatable.getSortColumn()) );
      sql=  sql.replace("{SORT_DIRECTION}",datatable.getSortDirection());
        //total daat count  umumi   57
        //filtered  count  -implement seaarch data count      seacha uyugungelen datalar  12
        //datasearch result with paging    ilk sehfede gosterielen datalar   10
        //sort column and dir
        int umumiCount = adamRepository.getAdamCount();
        datatable.setRecordsTotal(umumiCount);
     int filteredCount=adamRepository.getAdamCountFiltered(datatable.getSearch());
        datatable.setRecordsFiltered(filteredCount);

        List<Adam> adams = adamRepository.getAllAdams(datatable.getLength(),datatable.getStart(),datatable.getSearch(),sql);

        datatable.setData(new Object[adams.size()][4]);
        for (int i = 0; i < adams.size(); i++) {
            datatable.getData()[i][0] = adams.get(i).getId();
            datatable.getData()[i][1] = adams.get(i).getName();
            datatable.getData()[i][2] = adams.get(i).getAge();
            datatable.getData()[i][3] =
                            " <a onclick='showAdam(" + adams.get(i).getId() + ")'>View</a> &nbsp" +
                            " <a onclick='editAdam(" + adams.get(i).getId() + ")'>Edit</a> &nbsp " +
                            " <a onclick='deleteAdam(" + adams.get(i).getId() + ")'>Delete</a>  ";
        }
        return datatable;
    }




}
