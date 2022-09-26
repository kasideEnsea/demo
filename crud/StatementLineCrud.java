package com.company.demo.crud;
import com.company.demo.dao.StatementLineDao;
import com.company.demo.entity.StatementLine;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StatementLineCrud extends CrudRepository<StatementLineDao, Integer> {
    List<StatementLineDao> getByCourseId(int courseId);
}
