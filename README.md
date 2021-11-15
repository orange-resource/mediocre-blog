# MEDIOCRE BLOG 1.0.0

一款看起来十分简洁的博客系统，源于2018年开始编写然后断断续续的重构优化，最后形成了这个版本，基于`Spring Boot`构建，网站采用`Thymeleaf`模板引擎，后端管理页面使用`Vue`。

---

## 系统亮点

- 尽量实现了后台可配置，这意味着你可以在后台管理轻松设置网站的Logo，标题，还有作用于SEO用的Keywords等等，你还可以直接在后台管理配置百度统计拿到Script脚本代码然后作用到每个页面；
- 支持RSS订阅功能；
- 支持调整页面灰色模式，类似于哔哩哔哩网站在某些日子整个页面将没有任何色彩；
- 因为页面通用性，除了拿来写写技术文章，你更可以拿来做软件博客，或者可以放置一些自己的产品介绍

---

## 技术方面

**主要使用技术如下**

- spring boot2.0.8；
- thymeleaf(模板引擎)；
- mybatis plus；
- mybatis；
- hutool(工具包)；
- mapstruct(对象之间的转换)；
- redis；
- mysql5.7；
- lombok(用于类自动生成get set方法)；
- hikari数据库连接池；
- vue；
- element ui；

---

**关于网站的前端我想重点说明下，网站的前端(Spring Boot项目资源下那些Html文件)并不是我编写的，是我在2018年时候找的开源作品，已经不记得是在Github找到的还是码云...当时也没有做任何记录...当我在2020年想开源这个博客的时候尝试回头去找，发现已经找不到了...咳，如果您是这个网站的前端作者可以联系我，我会在这份README文件贴上您的开源项目地址**

---

## 使用说明

- sql文件在blog-build下的resources文件夹下；
- 项目线上编译文件在deploy文件夹下，你可以手动把application-release.yml文件放到blog-web项目下，然后再进行简单更改即可；
- 线上环境我推荐使用Nginx做反向代理，然后再配合CDN做下网站缓存，提高访问速度；
- 因为考虑到网站通常是一个人单独使用的，所以并未做后台管理端的接口严格验证，望知悉；
- blog-builder项目主要功能为快速从数据库转换实体类到项目中，执行DbToPOGenerator类即可一键生成

---

## 即将要做的

**如果Star量多的话，后面会做的如下**

- 支持markdown编写文章；
- 网站支持黑夜模式；
- 增加时间线功能梳理文章；
- 优化网站缓存(例如文章放置到Redis中)，更快速访问；
- 管理端登录接口防爆破处理；

---

## 项目开源协议

**MIT**

---

## 图片预览

**网站图片预览**
![首页](https://ongsat.oss-cn-hangzhou.aliyuncs.com/opensource/MediocreBlog/%E9%A6%96%E9%A1%B5.png)

![列表页](https://ongsat.oss-cn-hangzhou.aliyuncs.com/opensource/MediocreBlog/%E5%88%97%E8%A1%A8%E9%A1%B5.png)

![首页底部](https://ongsat.oss-cn-hangzhou.aliyuncs.com/opensource/MediocreBlog/%E9%A6%96%E9%A1%B5%E5%BA%95%E9%83%A8.png)

![文章](https://ongsat.oss-cn-hangzhou.aliyuncs.com/opensource/MediocreBlog/%E6%96%87%E7%AB%A0.png)

**后台管理页面预览**
![后台管理](https://ongsat.oss-cn-hangzhou.aliyuncs.com/opensource/MediocreBlog/%E5%90%8E%E5%8F%B0%E7%AE%A1%E7%90%86.png)