package com.xuecheng.manage_cms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan("com.xuecheng.framework.domain.cms") // 扫描实体类
@ComponentScan("com.xuecheng.api") // 扫描API
@ComponentScan("com.xuecheng.manage_cms") // 本项目所有包（为什么不加，404）
@ComponentScan("com.xuecheng.framework") // 本项目所有包（为什么不加，404）
public class ManagerCmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(ManagerCmsApplication.class,args);
    }
}
