package com.zum.study.service.sql.admin;

import com.zum.study.service.sql.event.entry.SqlEvent;
import com.zum.study.service.sql.event.entry.SqlEventType;
import com.zum.study.service.sql.event.listener.AdminEventListener;
import com.zum.study.service.sql.registry.UpdatableSqlRegistry;

public class SqlAdminService implements AdminEventListener {

    private UpdatableSqlRegistry registry;

    public void setSqlRegistry(UpdatableSqlRegistry registry) {

        this.registry = registry;
    }

    public void onEvent(SqlEvent event) {

        if (event.getEventType() == SqlEventType.UPDATE) {
            registry.update(event.getKey(), event.getSql());
        }
    }
}
