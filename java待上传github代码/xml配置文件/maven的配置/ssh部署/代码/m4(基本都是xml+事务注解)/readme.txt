自己写的ssh分层代码，除了service层的事务用了@Transactional注解，其他都使用xml配置。
applicationContext.xml也分层了，各层只写各自的applicationContext.xml。
没有hibernate.cfg.xml文件，内容都在applicationContext.xml。

配制方法见 外面的 创建maven整合分层的的ssh项目.txt。

