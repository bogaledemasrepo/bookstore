## Simple todo application using Spring-boot

# Usage

Step 1: Run postgresql in your machine & create database todos
Step 2: Replece value of spring.datasource.username to your database user name & spring.datasource.password with actual database user password
Step 3: Start application and browse http://localhost:4000/ and check it on
or use postman & check
Get: http://localhost:4000/todos
Post: http://localhost:4000/todos {title:your title,discription:your discription}
Put: http://localhost:4000/todos/${todoId} {title:new your title,discription:new your discription}
        Delete: http://localhost:4000/todos/${todoId}
