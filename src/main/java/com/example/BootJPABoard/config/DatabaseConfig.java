package com.example.BootJPABoard.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

@Configuration //스프링은 @Configuration이 지정된 클래스를 자바 기반의 설정파일로 인식
@PropertySource("classpath:/application.properties") // 해당 클래스에서 참조할 properties 파일의 위치를 지정
public class DatabaseConfig {
  @Bean
  @ConfigurationProperties(prefix = "spring.datasource.hikari") //prefix속성 지정.
  public HikariConfig hikariConfig() {
      return new HikariConfig();
  }
  //HikariConfig : 히카리 CP객체를 생성, 히카리CP는 커넥션풀(Connection pool)라이브러리 중 하나
  @Bean
  public DataSource dataSource() {
      return new HikariDataSource(hikariConfig());
  }

  
}
//스프링 부트에서 데이터소스 설정은 크게 두 가지.
//Bean 어노테이션 혹은 application.properties
//jdbc-url[데이터베이스 주소]
//username[postrgresql 아이디]
//password[postgresql 비밀번호]

//@Autowired : 빈으로 등록된 인스턴스(이하 객체)를 클래스를 주입하는데 사용
//@Autowired 이외에도 @Resource, @Inject 등이 존재
//@Bean @Configuration 클래스의 메서드 레벨에서만 지정가능 @Bean이 지정된 객체는 컨테이너에 의해 관리되는 빈으로 등록