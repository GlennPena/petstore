package com.pena.petstore.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;
import java.net.URI;

@Configuration
@Profile("prod")
public class RenderDataSourceConfig {

    @Bean
    public DataSource dataSource() {
        String dbUrl = System.getenv("DATABASE_URL");
        try {
            if (dbUrl != null && !dbUrl.isBlank()) {
                // Heroku/Render provide postgres://user:pass@host:port/dbname
                if (dbUrl.startsWith("postgres://")) {
                    URI uri = new URI(dbUrl);
                    String userInfo = uri.getUserInfo();
                    String username = null;
                    String password = null;
                    if (userInfo != null) {
                        String[] parts = userInfo.split(":", 2);
                        username = parts[0];
                        if (parts.length > 1) password = parts[1];
                    }
                    String host = uri.getHost();
                    int port = uri.getPort() == -1 ? 5432 : uri.getPort();
                    String path = uri.getPath(); // includes leading '/'
                    String jdbcUrl = String.format("jdbc:postgresql://%s:%d%s", host, port, path);

                    HikariConfig cfg = new HikariConfig();
                    cfg.setJdbcUrl(jdbcUrl);
                    if (username != null) cfg.setUsername(username);
                    if (password != null) cfg.setPassword(password);
                    cfg.setMaximumPoolSize(10);
                    cfg.setMinimumIdle(2);
                    return new HikariDataSource(cfg);
                }

                // If DATABASE_URL already contains a jdbc: prefix
                if (dbUrl.startsWith("jdbc:")) {
                    HikariConfig cfg = new HikariConfig();
                    cfg.setJdbcUrl(dbUrl);
                    String username = System.getenv("SPRING_DATASOURCE_USERNAME");
                    String password = System.getenv("SPRING_DATASOURCE_PASSWORD");
                    if (username != null) cfg.setUsername(username);
                    if (password != null) cfg.setPassword(password);
                    cfg.setMaximumPoolSize(10);
                    cfg.setMinimumIdle(2);
                    return new HikariDataSource(cfg);
                }
            }
        } catch (Exception ignored) {
            // fall through to H2 fallback
        }

        // Fallback to in-memory H2 so the app can start without a DATABASE_URL
        HikariConfig cfg = new HikariConfig();
        cfg.setJdbcUrl("jdbc:h2:mem:petstore;MODE=PostgreSQL;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE");
        cfg.setMaximumPoolSize(10);
        cfg.setMinimumIdle(2);
        return new HikariDataSource(cfg);
    }
}
