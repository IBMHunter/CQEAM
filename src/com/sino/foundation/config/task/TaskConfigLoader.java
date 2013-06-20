package com.sino.foundation.config.task;


import com.sino.base.exception.ConfigException;
import com.sino.base.exception.ContainerException;
import com.sino.base.exception.ReflectException;
import com.sino.base.log.Logger;
import com.sino.base.util.ReflectionUtil;
import com.sino.foundation.config.AbstractConfigLoader;
import com.sino.foundation.config.IConfigLoader;
import com.sino.foundation.task.TaskRunner;
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title: SinoApplication</p>
 * <p>Description: Java Enterprise Edition ƽ̨Ӧ�ÿ����������</p>
 * <p>@todo����ʱλ�ڸð��£�����ʵ����Ŀ����֤֮�󣬽����������⣬��ȡ��Ŀǰ���������ù���</p>
 * <p>Copyright: ����˼ŵ����Ȩ����Copyright (c) 2003~2008��
 * <p>Copyright: ����ʹ�õ��ĵ���������������л����񹲺͹���ط����Լ��л����񹲺͹��������ع��ʹ�Լ����Ȩ��ԭ�������С�</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 *
 * @author ����ʤ
 * @version 0.1
 */
public class TaskConfigLoader extends AbstractConfigLoader implements IConfigLoader {

    private TaskConfigs taskConfigs = null;
    private List<TaskConfig> tasks = null;
    private List<String> taskNames = null;
    private List<TriggerConfig> triggers = null;
    private List<String> triggerNames = null;
    private List<SchedulerConfig> schedulers = null;

    public void loadConfig() throws ConfigException {
        try {
            if (configFile == null || !configFile.exists()) {
                return;
            }
            initConfigParameters();
            loadConfigFromFile();
            combineAllConfigs();
        } catch (ContainerException ex) {
            ex.printLog();
            throw new ConfigException(ex);
        } catch (Throwable ex) {
            Logger.logError(ex);
            throw new ConfigException(ex.getMessage());
        }
    }

    private void loadConfigFromFile() throws ConfigException {
        if (configFile.isFile()) {
            if (configFilter.isCertificate(configFile)) {
                loadSingleConfig(configFile);
            }
        } else {
            File[] files = configFile.listFiles(configFilter);
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        loadSingleConfig(file);
                    } else if (file.isDirectory()) {
                        loadPathConfig(file);
                    }
                }
            }
        }
    }

    private void initConfigParameters() {
        loadedResult = new TaskConfigs();
        taskConfigs = (TaskConfigs) loadedResult;
        tasks = new ArrayList<TaskConfig>();
        taskNames = new ArrayList<String>();
        triggers = new ArrayList<TriggerConfig>();
        triggerNames = new ArrayList<String>();
        schedulers = new ArrayList<SchedulerConfig>();
    }

    private void combineAllConfigs() {
        taskConfigs.setTasks(tasks);
        taskConfigs.setTriggers(triggers);
        taskConfigs.setSchedulers(schedulers);
        taskNames.clear();
        triggerNames.clear();
    }

    private void loadSingleConfig(File file) throws ConfigException {
        try {
            SAXBuilder builder = new SAXBuilder();
            Document xmlDoc = builder.build(file);
            Element root = xmlDoc.getRootElement();
            loadTasks(root);
            loadTriggers(root);
            loadSchedulers(root);
        } catch (IOException ex) {
            Logger.logError(ex);
            throw new ConfigException(ex);
        } catch (Throwable ex) {
            Logger.logError(ex);
            throw new ConfigException(ex.getMessage());
        }
    }

    /**
     * ���ܣ����������ļ�
     *
     * @param path File
     * @throws com.sino.base.exception.ConfigException
     *          ����ExportConfig�����ļ�����ʱ�׳����쳣
     */
    private void loadPathConfig(File path) throws ConfigException {
        File[] files = path.listFiles(configFilter);
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    loadSingleConfig(file);
                } else if (file.isDirectory()) {
                    loadPathConfig(file);
                }
            }
        }
    }

    /**
     * ���ܣ����ض�ʱ��������
     *
     * @param root
     * @throws ConfigException
     */
    private void loadTasks(Element root) throws ConfigException {
        List<Element> eles = root.getChild("tasks").getChildren();
        if (eles != null && !eles.isEmpty()) {
            for (Element ele : eles) {
                TaskConfig taskConfig = new TaskConfig();
                loadConfig(ele, taskConfig);
                if (taskNames.contains(taskConfig.getName())) {
                    String err = taskConfig.getName()
                            + "�Ѿ����ڣ����������ļ�"
                            + configFile.getAbsolutePath()
                            + " tasks�ڵ��е�ͬ������";
                    throw new ConfigException(err);
                }
                tasks.add(taskConfig);
                taskNames.add(taskConfig.getName());
            }
        }
    }


    /**
     * ���ܣ����ض�ʱ����ʱ���������
     *
     * @param root
     * @throws ConfigException
     */
    private void loadTriggers(Element root) throws ConfigException {
        List<Element> eles = root.getChild("triggers").getChildren();
        if (eles != null && !eles.isEmpty()) {
            for (Element ele : eles) {
                TriggerConfig triggerConfig = new TriggerConfig();
                loadConfig(ele, triggerConfig);
                if (triggerNames.contains(triggerConfig.getName())) {
                    String err = triggerConfig.getName()
                            + "�Ѿ����ڣ����������ļ�"
                            + configFile.getAbsolutePath()
                            + "triggers�ڵ��е�ͬ��������";
                    throw new ConfigException(err);
                }
                triggers.add(triggerConfig);
                triggerNames.add(triggerConfig.getName());
            }
        }
    }

    /**
     * ���ܣ����ص���������
     *
     * @param root
     * @throws ConfigException
     */
    private void loadSchedulers(Element root) throws ConfigException {
        List<Element> eles = root.getChild("schedulers").getChildren();
        if (eles != null && !eles.isEmpty()) {
            for (Element ele : eles) {
                SchedulerConfig schedulerConfig = new SchedulerConfig();
                loadConfig(ele, schedulerConfig);
                validateScheduler(schedulerConfig);
                schedulers.add(schedulerConfig);
            }
        }
    }

    /**
     * ���ܣ������������ֵ������Ƿ�Ϸ�
     *
     * @param schedulerConfig
     * @throws ConfigException ���Ϸ�ʱ�׳������쳣
     */
    private void validateScheduler(SchedulerConfig schedulerConfig) throws ConfigException {
        String taskName = schedulerConfig.getTaskName();
        boolean foundData = false;
        for (TaskConfig task : tasks) {
            if (taskName.equals(task.getName())) {
                foundData = true;
                break;
            }
        }
        if (!foundData) {
            String err = taskName
                    + "�����ڣ����������ļ�"
                    + configFile.getAbsolutePath()
                    + " ��tasks�ڵ��Ƿ���©"
                    + taskName
                    + "���������";
            throw new ConfigException(err);
        }

        String triggerName = schedulerConfig.getTriggerName();
        foundData = false;
        for (TriggerConfig trigger : triggers) {
            if (triggerName.equals(trigger.getName())) {
                foundData = true;
                break;
            }
        }
        if (!foundData) {
            String err = triggerName
                    + "�����ڣ����������ļ�"
                    + configFile.getAbsolutePath()
                    + " ��triggers�ڵ��Ƿ���©"
                    + triggerName
                    + "���������";
            throw new ConfigException(err);
        }
    }

    /**
     * ���ܣ�����������Ϣ������������
     *
     * @param ele Element
     * @return PoolConfig
     */
    private void loadConfig(Element ele, Object cfgObject) throws ConfigException {
        try {
            List<Attribute> attrs = ele.getAttributes();
            if (attrs != null && !attrs.isEmpty()) {
                for (Attribute attr : attrs) {
                    ReflectionUtil.setProperty(cfgObject, attr.getName(), attr.getValue());
                }
            }
            List<Element> dataChildren = ele.getChildren();
            if (dataChildren != null && !dataChildren.isEmpty()) {
                for (Element child : dataChildren) {
                    ReflectionUtil.setProperty(cfgObject, child.getName(), child.getText());
                }
            }
        } catch (ReflectException ex) {
            ex.printLog();
            throw new ConfigException(ex);
        }
    }

    /**
     * ���ܣ�ж�ض�ʱ�������ã�����ֹͣ�������С��÷�����Ҫ�޸ġ�
     */
    public void unloadConfig() {
        try {
            TaskRunner.destroyTask();
            super.unloadConfig();
        } catch (Throwable ex) {
            Logger.logError(ex);
        }
    }
}