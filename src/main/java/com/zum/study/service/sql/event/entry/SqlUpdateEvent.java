package com.zum.study.service.sql.event.entry;

public class SqlUpdateEvent extends SqlEvent {

    public SqlUpdateEvent() {
        super(SqlEventType.UPDATE);
    }
}
