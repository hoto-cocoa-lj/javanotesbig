自己写的ssh分层代码，自己的类全部用注解，包括action也用注解，
只有service的事务声明和dao层c3p0/sessionfactory/hibernateTemplate/配置扫描 使用了xml配置。
applicationContext.xml也分层了，各层只写各自的applicationContext.xml。
没有hibernate.cfg.xml文件，内容都在applicationContext.xml。

流程见 外面的 创建maven整合分层的的ssh项目.txt，
不过这次搭建的时候没有在java ee tools里出现generate，
所以在->preferences->project facts里选了dynamic web module 2.5和java 1.7，
然后futhur里删掉src路径，并在web-root folder设置src/main/webapp，
将模块转成web模块（？）。
