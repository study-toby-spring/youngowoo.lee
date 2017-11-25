package com.zum.study.service.sql.event.listener;

import com.zum.study.service.sql.event.entry.SqlEvent;

public interface AdminEventListener {

    void onEvent(SqlEvent event);
}
