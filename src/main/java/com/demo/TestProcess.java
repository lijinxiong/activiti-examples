package com.demo;

import org.activiti.engine.*;
import org.activiti.engine.task.Task;

public class TestProcess {

    public static void main(String[] args) {

        //创建流程引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        //得到运行时服务组件
        RuntimeService runtimeService = processEngine.getRuntimeService();

        //获取流程任务组件
        TaskService taskService = processEngine.getTaskService();

        //得到流程存储服务组件
        RepositoryService repositoryService = processEngine.getRepositoryService();

        //部署流程文件
        repositoryService.createDeployment().addClasspathResource("bpmn/askForLeave.bpmn").deploy();

        //启动流程
        runtimeService.startProcessInstanceByKey("ask_for_leave");

        //获取第一个任务
        Task employeeTask = taskService.createTaskQuery().singleResult();

        System.out.println(employeeTask.getName());

        //完成第一个任务
        taskService.complete(employeeTask.getId());


        Task managerTask = taskService.createTaskQuery().singleResult();
        System.out.println(managerTask.getName());

        taskService.complete(managerTask.getId());


    }

}
