/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.shardingsphere.infra.exception.dialect;

import org.apache.shardingsphere.infra.database.core.type.DatabaseType;
import org.apache.shardingsphere.infra.exception.dialect.exception.syntax.database.DatabaseCreateExistsException;
import org.apache.shardingsphere.infra.exception.dialect.fixture.DatabaseProtocolExceptionExceptionFixture;
import org.apache.shardingsphere.infra.hint.SQLHintDataSourceNotExistsException;
import org.apache.shardingsphere.infra.spi.type.typed.TypedSPILoader;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SQLExceptionTransformEngineTest {
    
    @Test
    void assertSQLExceptionTransformEngineConvertException() {
        assertThat(SQLException.class, is(SQLExceptionTransformEngine.toSQLException(new SQLException(), null).getClass()));
        assertThat(SQLException.class, is(SQLExceptionTransformEngine.toSQLException(new SQLHintDataSourceNotExistsException("only for test"), null).getClass()));
        assertThat(SQLException.class,
                is(SQLExceptionTransformEngine.toSQLException(new DatabaseProtocolExceptionExceptionFixture("only for test"), TypedSPILoader.getService(DatabaseType.class, "MySQL")).getClass()));
        assertThat(SQLException.class,
                is(SQLExceptionTransformEngine.toSQLException(new DatabaseCreateExistsException("only for test"), TypedSPILoader.getService(DatabaseType.class, "MySQL")).getClass()));
        assertThat(SQLException.class, is(SQLExceptionTransformEngine.toSQLException(new RuntimeException("only for test"), null).getClass()));
    }
}
