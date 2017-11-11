package com.zum.study.service.sql.reader;

import com.zum.study.service.sql.registry.SqlRegistry;

public interface SqlReader {

    void load(SqlRegistry registry);
}
