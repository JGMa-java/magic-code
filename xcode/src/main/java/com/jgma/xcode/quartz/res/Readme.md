# Quartz核心
### 1.Scheduler接口
> Scheduler翻译成调度器，Quartz通过调度器来注册、暂停、删除Trigger和JobDetail。
> Scheduler还拥有一个SchedulerContext，顾名思义就是上下文，
>通过SchedulerContext我们可以获取到触发器和任务的一些信息。
### 2.Trigger接口
> Trigger可以翻译成触发器，通过cron表达式或是SimpleScheduleBuilder等类，指定任务执行的周期。
> 系统时间走到触发器指定的时间的时候，触发器就会触发任务的执行。
### 3.JobDetail接口
> Job接口是真正需要执行的任务。JobDetail接口相当于将Job接口包装了一下，Trigger和Scheduler实际用到的都是JobDetail。

#### 注意：
1、我选择将定时任务的信息保存在数据库中，优点是显而易见的，定时任务不会因为系统的崩溃而丢失。<br>
2、使用数据库需要导入相应的sql,见res目录