package com.study.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration // @Configuration클래스를 java기반의 설정파일로 인식
@PropertySource("classpath:/application.properties") // 해당 클래스를 참조할 properties 파일의 위치
public class DatabaseConfig {

    @Bean // 스프링 컨테이너에 bean객체 등록
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    // @Propertysource에 등록된 설정파일 중 spring.datasource.hikari로 시작하는 설정을 모두 읽어들여 해당 메소드에 바인딩
    // 해당 annotation은 메소드뿐만 아니라 클래스에도 사용가능
    public HikariConfig hikariConfig() { // hikariCP(connection pool)객체 생성
        return new HikariConfig();
    }

    @Bean
    public DataSource dataSource() {
        return new HikariDataSource(hikariConfig());
    }

}