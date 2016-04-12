# JEE Course
Recently, we are having the JEE course, this is the project at the end of this course.

### Project description
This project is called 'Project Farm', it is a quite simplified version of the [KickStarter] project, which aims at helping people to get funding for project ideas in several fields. [KickStarter] is a complex project and unfortunately, we do not have enough time to implememt all of its features. Thus, we propose a "lite" version, with a limited number of feateres.

In our application, we have two types of users: 
- **Owners**: those who register project ideas in our system.
- **Evaluators**: those who evaluate project ideas. 

Modeling techniques related to users and roles are out-of-scope in this project, so, users with both roles are not allowed in this version.

### Application Logic
It is not an anonymous site: To use it, you must be a registered user.
![image](https://github.com/Xx-william/JEE-Course/blob/master/Screen%20Shots/figure1.jpg)

**Owner** are allowed to create project ideas, add relevant documentation and check thr project acceptability.
![image](https://github.com/Xx-william/JEE-Course/blob/master/Screen%20Shots/figure2.jpg)

When the **Owner** starts a new idea, he or she must provide basic information about the project such as acronym, description, and budget.
![image](https://github.com/Xx-william/JEE-Course/blob/master/Screen%20Shots/figure3.jpg)

**Owner** are allwed to view only their projects and related statistics of each project(i.e. eht average attractiveness, the average risk level and the number of evaluators.
![image](https://github.com/Xx-william/JEE-Course/blob/master/Screen%20Shots/figure4.jpg)

At any time, the project owner can attach documents related to the project (summaries, images, videos, etc.) to draw the attention of evaluators.
![image](https://github.com/Xx-william/JEE-Course/blob/master/Screen%20Shots/figure5.jpg)

**Evaluator** cannot create project ideas. However, they have access to all projects registered in the application.
![image](https://github.com/Xx-william/JEE-Course/blob/master/Screen%20Shots/figure6.jpg)

The **Evaluator** chooses a project from the list and assigns a numeric value to two dimensions of the proposal. Attractiveness and risk level are numeric values that range from 1 to 5. For attractiveness, 1 is the lowest attractive project, and 5 is the most attractive project. The same rule is applied for risk level: 1 is the lowest perceived risk and 5 is the biggest perceived risk. For the sake of simplicity, **Evaluator** cannot change, or remove their evaluations.
![image](https://github.com/Xx-william/JEE-Course/blob/master/Screen%20Shots/figure7.jpg)
### Tech
In this project, we will use

* [JAVA] 
* [JAVA Servlet] 
* [JSP] 
* [HTML] 
* [JQuery] 
* [Ajax] 
* [BootStrap] 
* [Font-Awesome]

And we used some of the open source projects at GitHub.
* [bootstrap-select] 
* [bootstrap-validator]


And of course this project itself is open source with a [public repository]
on GitHub.
### Environment
* Eclipse Mars.1 Release(4.5.1)
* MySql V 14.14 Distrib 5.7.11
* Tomcat V 8.0
* JAVA V 1.8

### Installation

```sh
1. Import the project to Eclipse.
2. Import the DataBase to MySQL.
3. In the project, under the /ProjectFarm/WebContent/META-INF/context.xml, you need to change the setting of your own database.
4. In the project, under the /ProjectFarm/WebContent/WEB-INF/web.xml, you need to change the path of the uploaded file.
```
Now you are ready to go!!!

License
----

MIT


[KickStarter]: <https://www.kickstarter.com/>
[public repository]: <https://github.com/Xx-william/JEE-Course>
[JAVA]: <https://www.java.com/en/download/whatis_java.jsp>
[JAVA Servlet]: <http://www.oracle.com/technetwork/java/index-jsp-135475.html>
[JSP]: <http://www.oracle.com/technetwork/java/javaee/jsp/index.html>
[HTML]: <http://www.w3schools.com/tags/default.asp>
[JQuery]: <https://jquery.com/>
[Ajax]: <http://api.jquery.com/jquery.ajax/>
[BootStrap]: <http://getbootstrap.com/>
[bootstrap-select]: <https://github.com/silviomoreto/bootstrap-select>
[bootstrap-validator]: <https://github.com/1000hz/bootstrap-validator>
[Font-Awesome]:  <https://fortawesome.github.io/Font-Awesome/>


