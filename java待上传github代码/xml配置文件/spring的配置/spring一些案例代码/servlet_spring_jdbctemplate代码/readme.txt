使用 servlet + jdbctemplate + spring开发项目：

web层使用了servlet，servlet不受spring管理，所以要手动注入，
使用方法是重写init方法，
详细见\servlet_spring_hibernate\src\main\java\cn\me\web。


