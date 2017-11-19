package com.zum.study.service.sql.service;

import com.zum.study.exception.SqlRetrievalFailureException;
import com.zum.study.jaxb.SqlType;
import com.zum.study.jaxb.Sqlmap;
import com.zum.study.service.sql.reader.SqlReader;
import com.zum.study.service.sql.registry.HashMapSqlRegistry;
import com.zum.study.service.sql.registry.SqlRegistry;
import org.springframework.oxm.Unmarshaller;

import javax.annotation.PostConstruct;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;

public class OxmSqlService implements SqlService {

    private BaseSqlService sqlService = new BaseSqlService();

    private SqlRegistry registry = new HashMapSqlRegistry();

    public void setSqlRegistry(SqlRegistry registry) {

        this.registry = registry;
    }

    private OxmSqlReader reader = new OxmSqlReader();

    private class OxmSqlReader implements SqlReader {

        private static final String DEFAULT_SQLMAP_FILEPATH = "/sql/sqlmap.xml";

        private String sqlMapFile = DEFAULT_SQLMAP_FILEPATH;

        public String getSqlMapFile() {

            return this.sqlMapFile;
        }

        public void setSqlMapFile(String sqlMapFile) {

            this.sqlMapFile = sqlMapFile;
        }

        private Unmarshaller unmarshaller;

        public void setUnmarshaller(Unmarshaller unmarshaller) {

            this.unmarshaller = unmarshaller;
        }

        public void load(SqlRegistry registry) {

            try {

                Source source = new StreamSource(getClass().getResourceAsStream(getSqlMapFile()));
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


    private Unmarshaller unmarshaller;

    public void setUnmarshaller(Unmarshaller unmarshaller) {

        reader.setUnmarshaller(unmarshaller);
    }

    private String sqlMapFile;

    public void setSqlMapFile(String sqlMapFile) {

        reader.setSqlMapFile(sqlMapFile);
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
