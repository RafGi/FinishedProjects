package repo;


import data.Era;

import java.util.List;

public interface EraRepo {

    Era getEraById(int id);

    Era getEraByName(String name);

    List<Era> getEras();

    void addEra(Era era);

    void deleteEra(Era era);
}
