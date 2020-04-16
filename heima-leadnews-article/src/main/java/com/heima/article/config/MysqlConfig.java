package com.heima.article.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
//配置类标识
@Configuration
//ComponentScan做的事情就是告诉Spring从哪里找到bean
@ComponentScan("com.heima.common.mysql.core")
public class MysqlConfig {
}

