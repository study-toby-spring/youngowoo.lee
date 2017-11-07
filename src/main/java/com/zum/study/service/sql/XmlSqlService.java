package com.zum.study.service.sql;

import com.zum.study.exception.SqlRetrievalFailureException;
import com.zum.study.jaxb.SqlType;
import com.zum.study.jaxb.Sqlmap;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Joeylee on 2017-11-07.
 */
public class XmlSqlService implements SqlService {

    private String sqlMapFile;

    private Map<String, String> map = new HashMap<String, String>();

    public String getSqlMapFile() {

        return this.sqlMapFile;
    }

    public void setSqlMapFile(String sqlMapFile) {

        this.sqlMapFile = sqlMapFile;
    }

    @PostConstruct
    public void load() {

        try {

            String path = Sqlmap.class.getPackage().getName();
            JAXBContext context = JAXBContext.newInstance(path);

            Unmarshaller unmarshaller = context.createUnmarshaller();

            InputStream stream = getClass().getResourceAsStream(getSqlMapFile());
            Sqlmap sqlmap = (Sqlmap) unmarshaller.unmarshal(stream);

            for (SqlType type : sqlmap.getSql()) {
                map.put(type.getKey(), type.getValue());
            }
        }
        catch (JAXBException e) {

            throw new RuntimeException();
        }
    }

    public String getSql(String key) throws SqlRetrievalFailureException {

        String sql = map.get(key);

        if (sql == null) {
            throw new SqlRetrievalFailureException(key + "에 대한 SQL을 찾을 수 없습니다");
        }

        return sql;
    }
}