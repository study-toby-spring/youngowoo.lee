package com.zum.study.service.sql.service;

import com.zum.study.exception.SqlRetrievalFailureException;
import com.zum.study.jaxb.SqlType;
import com.zum.study.jaxb.Sqlmap;
import com.zum.study.service.sql.reader.SqlReader;
import com.zum.study.service.sql.registry.HashMapSqlRegistry;
import com.zum.study.service.sql.registry.SqlRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.oxm.Unmarshaller;

import javax.annotation.PostConstruct;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;

public class OxmSqlService implements SqlService {

    private BaseSqlService sqlService = new BaseSqlService();

    private SqlRegistry registry = new HashMapSqlRegistry();

    @Autowired
    public void setSqlRegistry(SqlRegistry registry) {

        this.registry = registry;
    }

    private OxmSqlReader reader = new OxmSqlReader();

    private class OxmSqlReader implements SqlReader {

        private Resource sqlMap = new ClassPathResource("/sql/sqlmap.xml");

        public Resource getSqlMap() {

            return this.sqlMap;
        }

        public void setSqlMap(Resource sqlMap) {

            this.sqlMap = sqlMap;
        }

        private Unmarshaller unmarshaller;

        public void setUnmarshaller(Unmarshaller unmarshaller) {

            this.unmarshaller = unmarshaller;
        }

        public void load(SqlRegistry registry) {

            try {

                Source source = new StreamSource(sqlMap.getInputStream());
                Sqlmap sqlmap = (Sqlmap) unmarshaller.unmarshal(source);

                for (SqlType type : sqlmap.getSql()) {
                    registry.register(type.getKey(), type.getValue());
                }
            }
            catch (IOException e) {

                throw new RuntimeException();
            }
        }
    }


    @Autowired
    public void setUnmarshaller(Unmarshaller unmarshaller) {

        reader.setUnmarshaller(unmarshaller);
    }

    public void setSqlMap(Resource sqlMap) {

        reader.setSqlMap(sqlMap);
    }


    @PostConstruct
    public void initialize() {

        sqlService.setSqlRegistry(registry);
        sqlService.setSqlReader(reader);

        sqlService.initialize();
    }

    public String getSql(String key) throws SqlRetrievalFailureException {

        return registry.get(key);
    }
}
