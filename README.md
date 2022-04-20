
### install

```
    git clone https://github.com/maksim-maslov/qa-pageobject-allure
```

### run tests

```
    mvn clean test  
    mvn allure:serve  
```

### Структура файлов проекта

```
.allure/                                    --> allure reporter
drivers/                                    --> gecko драйвер                   	
src/ 
  main/ 
    java/
      org.example.qa.pageobject/
        page/                               --> pageobject
  test/
    java/
      org.example.qa.pageobject/
        test/                               --> тесты
        resources/                          --> ресурсы
pom.xml                                     --> конфигурация maven
.gitignore                                  --> gitignore
README.md                                   --> описание проекта
```
