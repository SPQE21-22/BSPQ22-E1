package data.dao;

import data.dto.BookDTO;

import java.util.List;

public interface IDAO<DomainObject> {
    void save(DomainObject object);
    void delete(DomainObject object);
    boolean update(DomainObject object);
    List<DomainObject> getAll();
    DomainObject find(String param);
}