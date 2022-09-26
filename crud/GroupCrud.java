package com.company.demo.crud;
        import com.company.demo.dao.GroupDao;
        import org.springframework.data.repository.CrudRepository;

public interface GroupCrud extends CrudRepository<GroupDao, Integer> {
    GroupDao getById(int groupId);
    boolean existsByName(String name);
    GroupDao getByName(String name);
}
