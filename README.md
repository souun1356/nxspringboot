# Project Title

SpringBoot learning.

## Getting Started

學習 SpringBoot 微服務架構.

## Prerequisites

開發環境

- JDK v17
- Node.js v16
- Git
- Visual Studio Code or Eclipse

## 環境建置

- 建置 nx 專案
  - npx create-nx-workspace@latest --style=scss --name=nx --appName=frontend --preset=angular
  - npm install -g nx
- 建置 backend springboot 專案
  - npm i @nxrocks/nx-spring-boot -D --legacy-peer-deps
  - nx g @nxrocks/nx-spring-boot:new backend
- 前端加入 tailwindcss
  - npm i tailwindcss -D --legacy-peer-deps
- 啟動
  - 多專案：nx run-many --target=serve --projects=frontend,backend
  - 前端：nx serve frontend （cd apps/frontend/ && ng serve)
  - 後段：nx serve backend（cd apps/backend/ && ./mvnw spring-boot:run)

## 課程大綱

### ch01

建立一支 API

- 增加 Ch01Controller，呼叫顯示'Hello World'

- Run BackendApplication and open http://localhost:8080/ch01

```java
@CrossOrigin
@RestController
public class Ch01Controller {

  @GetMapping("/ch01")
  String ch01() {
    return "Hello World";
  }
}
```

### ch02

學習建立各環境設定檔並印出

- 增加 Ch02Controller，呼叫取得各環境的變數

- resources 目錄增加 application-ut.properties、application-uat.properties、application-prod.properties

- application-ut.properties 設定檔增加 env.name 變數

- application.properties 設定 spring.profiles.active 變數來決定環境

- Run BackendApplication and open http://localhost:8080/ch02

```java
@RestController
public class Ch02Controller {

	@Value("${env.name}")
	private String envName;

	@GetMapping("/ch02")
	public String ch02() {
		return envName;
	}
}
```

### ch03

根據上下行電文 json，建立 java 相關物件

- 上行電文

```json
{
  "header": {
    "funId": "1",
    "key": "2",
    "role": "3",
    "clientIp": "4"
  },
  "body": {
    "customerId": "A123456789"
  }
}
```

- 下行電文

```json
{
  "header": {
    "funId": "1",
    "key": "2",
    "role": "3",
    "clientIp": "4",
    "code": "5",
    "msg": "6"
  },
  "body": [
    {
      "customerId": "A123456789",
      "name": "Alan",
      "age": 20,
      "tel": "0912345678",
      "addr": "地址"
    }
  ]
}
```

### ch04

透過 spring data jpa 取得資料

- 建立 schema.sql、data.sql
- 建立 entity、repository、service 相關物件
  - 建立 Customer.java
  - 建立 CustomerRepository.java
  - 建立 CustomerService.java
- 根據上行 customerId 欄位查詢資料

### ch05

練習 JpaRepository

- 查詢性別為男性的客戶

- 上行電文

```json
{
  "header": {
    "funId": "1",
    "key": "2",
    "role": "3",
    "clientIp": "4"
  },
  "body": {
    "gender": "M"
  }
}
```

### ch06

練習 JpaRepository interface dto

- 查詢性別為男性的客戶姓名

- 上行電文

```json
{
  "header": {
    "funId": "1",
    "key": "2",
    "role": "3",
    "clientIp": "4"
  },
  "body": {
    "gender": "M"
  }
}
```

- 下行電文

```json
{
  "header": {
    "funId": "1",
    "key": "2",
    "role": "3",
    "clientIp": "4",
    "code": "0000",
    "msg": "成功"
  },
  "body": [
    {
      "name": "Rodney",
      "customerId": "A123456789"
    },
    {
      "name": "Steve",
      "customerId": "C123456789"
    },
    {
      "name": "Vanessa",
      "customerId": "E123456789"
    },
    {
      "name": "Madhuri",
      "customerId": "G123456789"
    }
  ]
}
```

### ch07

練習 JDK8 stream

- 使用 filter 過濾資料

### ch08

加入檢核機制

- 檢核 gender 不能為空白

### ch09

學習用 thymeleaf 框架(Hello World)

- pom.xml 加入 spring-boot-starter-thymeleaf

```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>
```

- 增加 Ch09Controller，把訊訊息文字返回至 hello.html

```java
@Controller
public class Ch09Controller {

    @GetMapping("/ch09")
    public String ch09(Model model) {
        model.addAttribute("text", "Hello World!!");
        return "hello";
    }
}
```

- hello.html 需增加 `xmlns:th="http://www.thymeleaf.org"` 屬性
- 使用 th:text 印出後端的傳遞的資料

### ch10

學習用 servlet 印出 hello world

- nx serve servlet-sample
- http://localhost:8080/demo/hello

### ch11

學習用 jstl、el 印出資料

- nx serve servlet-sample
- http://localhost:8080/demo/category

### ch12

學習用 servlet 產生 api

- nx serve servlet-api
- http://localhost:8080/demo/hello

### TODO

- 建立個人的 github 帳號，fork 本專案 and pull request（PR 前需先 git rebase）
- 建立本機 postgresql、mysql database
- 建立 config 目錄，設定 Multiple Data Sources
- save or update 需同時寫入 database，且需要事務處理機制
- 使用 thymeleaf 印出所有客戶資料在 table 上
- 組件化 thymeleaf customer table，讓其他畫面可以 include
- 小組討論畫面設計，依據畫面設計 table 等相關 api 功能
- 挑戰：改使用 r2dbc 的 webflux 方式改寫 api
