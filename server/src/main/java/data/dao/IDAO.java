package data.dao;

import java.util.List;

public interface IDAO<DomainObject> {
    void save(DomainObject object);
    void delete(DomainObject object);
    List<DomainObject> getAll();
    DomainObject find(String param);
}