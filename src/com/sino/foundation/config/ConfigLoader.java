package com.sino.foundation.config;

import com.sino.base.exception.ConfigException;
import com.sino.base.log.Logger;
import com.sino.base.util.ReflectionUtil;
import com.sino.base.util.StrUtil;
import com.sino.foundation.config.system.EntryConfig;
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* <p>Title: SinoApplication</p>
* <p>Description: Java Enterprise Edition ƽ̨Ӧ�ÿ����������</p>
* <p>@todo����ʱλ�ڸð��£�����ʵ����Ŀ����֤֮�󣬽����������⣬��ȡ��Ŀǰ���������ù���</p>
* <p>Copyright: ����˼ŵ����Ȩ����Copyright (c) 2003~2008��
* <p>Copyright: ����ʹ�õ��ĵ���������������л����񹲺͹���ط����Լ��л����񹲺͹��������ع��ʹ�Լ����Ȩ��ԭ�������С�</p>
* <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
* @author ����ʤ
* @version 0.1
 */
public abstract class ConfigLoader {
    private static File systemEntryFile = null; //��������ļ�
    private static Map<String, Long> fileMap = new HashMap<String, Long>();
    private static List<IConfigLoader> loaders = new ArrayList<IConfigLoader>();//Ϊ�����ļ���ж��׼��
    private static ConfigManager configManager = new ConfigManager();

    private static String contextPath = "";  //WebӦ�ø�Ŀ¼
    private static final String defaultConfigPath = "/SinoConfig/EntryConfig.xml";

    public static void setContextPath(String contextPath) {
        ConfigLoader.contextPath = contextPath;
    }

    public static void loadDefaultConfig() throws ConfigException {
        loadConfig(defaultConfigPath);
    }

    /**
     * ���ܣ����������ļ�����ڼ��ش����ɳ�ʼ��������ã�����WebӦ���еĳ�ʼ��Servlet��
     *
     * @param relativePath String
     * @throws ConfigException
     */
    public static void loadConfig(String relativePath) throws ConfigException {
        try {
            String absolutePath = ConfigPathUtil.getConfigPath(relativePath);//��Ҫ����д
            systemEntryFile = new File(absolutePath);
            Element root = getXMLConfigRoot();
            List<Element> confs = root.getChildren();
            if (confs != null && !confs.isEmpty()) {
                for (Element child : confs) {
                    EntryConfig entryConfig = getEntryConfig(child);
                    if (!entryConfig.isLoadConfig()) {
                        continue;
                    }
                    try {
                        IConfigLoader configLoader = getConfigLoader(entryConfig);
                        loadEntryConfig(entryConfig, configLoader);
                        System.out.println(entryConfig.getDescription() + " ���سɹ�");
                    } catch (Throwable ex) {
                        Logger.logError(ex); //������¼�쳣������������һ�������ļ�
                        System.out.println(entryConfig.getDescription() + " ����ʧ��");
                    }
                }
            }
        } catch (Throwable ex) {
            Logger.logError(ex);
            throw new ConfigException(ex.getMessage());
        }
    }

    /**
     * ���ܣ����������ļ������¼��ص���ڴ����������ļ����������á�
     *
     * @throws ConfigException
     */
    public static void refreshConfig() throws ConfigException {
        if (systemEntryFile == null) {
            throw new ConfigException("ϵͳ�����Ѿ�ж�أ���������Ҫ����");
        }
        Element root = getXMLConfigRoot();
        List<Element> confs = root.getChildren();
        if (confs != null && !confs.isEmpty()) {
            unLoadRemovedConfigs(confs);
            loadChangeConfigs(confs);
        } else {
            unloadAllConfigs();
        }
    }

    /**
     * ���ܣ���ȡ���������ļ��ĸ��ڵ�
     *
     * @return
     * @throws ConfigException
     */
    private static Element getXMLConfigRoot() throws ConfigException {
        Element xmlRoot = null;
        try {
            SAXBuilder builder = new SAXBuilder();
            Document xmlDoc = builder.build(systemEntryFile);
            xmlRoot = xmlDoc.getRootElement();
        } catch (JDOMException ex) {
            Logger.logError(ex);
            throw new ConfigException(ex);
        } catch (IOException ex) {
            Logger.logError(ex);
            throw new ConfigException(ex);
        }
        return xmlRoot;
    }

    /**
     * ���ܣ���ȡ�����ļ�������w
     *
     * @param entryConfig
     * @return
     * @throws ConfigException
     */
    public static IConfigLoader getConfigLoader(EntryConfig entryConfig) throws ConfigException {
        File configFile = new File(entryConfig.getAbsolutePath());
        IConfigLoader configLoader = LoaderFactory.getConfigLoader(entryConfig);
        configLoader.setConfigFile(configFile);
        configLoader.setContextPath(contextPath);
        return configLoader;
    }


    /**
     * ���ܣ�ж����ǰ���أ������ڴ������ļ�SystemConfig.xml���Ƴ������á�
     *
     * @param confs ��ǰ��SystemConfig.xml�����ļ���config�ڵ��б�
     * @throws  ConfigException
     */
    private static void unLoadRemovedConfigs(List<Element> confs) throws ConfigException {
        List<EntryConfig> configs = configManager.getAllConfigs().getAllConfigs();
        for (int i = 0; i < configs.size(); i++) {
            EntryConfig config = configs.get(i);
            boolean foundConfig = false;
            for (Element child : confs) {
                EntryConfig entryConfig = getEntryConfig(child);
                if (entryConfig.equals(config)) {
                    foundConfig = true;
                    break;
                }
            }
            if (!foundConfig) {
                System.out.println(config.getDescription() + config.getAbsolutePath() + "�Ѵ�ϵͳ�����ļ����Ƴ���ϵͳ��ж����������Ϣ��");
                configManager.removeConfig(config.getName());
            }
        }
    }

    /**
     * ���ܣ����ر仯�˵�����
     *
     * @param confs ��ǰ��SystemConfig.xml�����ļ���config�ڵ��б�
     */
    private static void loadChangeConfigs(List<Element> confs) throws ConfigException {
        for (Element child : confs) {
            EntryConfig entryConfig = getEntryConfig(child);
            if (configManager.contains(entryConfig.getName())) {//ԭ��������Ϣ
                if (!entryConfig.isLoadConfig()) {
                    configManager.removeConfig(entryConfig.getName());
                } else if (entryConfig.isSupportReload()) { //�Ƿ�֧���ȼ���
                    if (hasConfigChanged(entryConfig)) {
                        System.out.println(entryConfig.getDescription()
                                + "�����ļ�"
                                + entryConfig.getAbsolutePath()
                                + "�����޸ģ�ϵͳ�������¼���");
                        IConfigLoader configLoader = getConfigLoader(entryConfig);
                        unloadConfig(entryConfig, configLoader);
                        loadEntryConfig(entryConfig, configLoader);
                        System.out.println(entryConfig.getDescription() + "���¼��سɹ�");
                    }
                }
            } else {
                if (entryConfig.isLoadConfig()) {
                    System.out.println(entryConfig.getDescription()
                            + "�¼��������ļ�"
                            + entryConfig.getAbsolutePath()
                            + "��ϵͳ��������м���");
                    IConfigLoader configLoader = getConfigLoader(entryConfig);
                    loadEntryConfig(entryConfig, configLoader);
                    System.out.println(entryConfig.getDescription() + "���سɹ�");
                }
            }
        }
    }

    /**
     * ���ܣ������������
     *
     * @param entryEle Element
     * @return EntryConfig
     * @throws ConfigException
     */

    private static EntryConfig getEntryConfig(Element entryEle) throws ConfigException {
        EntryConfig entryConfig = new EntryConfig();
        try {
            List<Attribute> attrs = entryEle.getAttributes();
            for (Attribute attr : attrs) {
                if (StrUtil.isEmpty(attr.getValue())) {
                    continue;
                }
                ReflectionUtil.setProperty(entryConfig, attr.getName(), attr.getValue());
            }
            List<Element> children = entryEle.getChildren();
            for (Element ele : children) {
                if (StrUtil.isEmpty(ele.getValue())) {
                    continue;
                }
                ReflectionUtil.setProperty(entryConfig, ele.getName(), ele.getValue());
            }
            if(systemEntryFile != null){
                entryConfig.setEntryPath(systemEntryFile.getParent());
            }
        } catch (Throwable ex) {
            Logger.logError(ex);
            throw new ConfigException(ex.getMessage());
        }
        return entryConfig;
    }

    /**
     * ���ܣ������������ָ����Ӧ�������ļ�
     *
     * @param entryConfig  EntryConfig
     * @param configLoader AbstractConfigLoader
     * @throws ConfigException
     */
    public static void loadEntryConfig(EntryConfig entryConfig, IConfigLoader configLoader) throws ConfigException {
        try {
            File configFile = new File(entryConfig.getAbsolutePath());
            configLoader.loadConfig();
            if (!loaders.contains(configLoader)) {
                loaders.add(configLoader);
            }
            processPostProcessor(entryConfig, configLoader.getLoadedResult());
            fileMap.put(configFile.getAbsolutePath(), configFile.lastModified());
            configManager.addConfig(entryConfig, configLoader.getLoadedResult());
        } catch (Throwable ex) {
            Logger.logError(ex);
            throw new ConfigException(ex.getMessage());
        }
    }


    @SuppressWarnings("unchecked")
    /**
     * ���ܣ�������ô�����
     *
     * @param entryConfig  ������ö���
     * @param loadedResult ���ؽ��
     * @throws ConfigException
     */
    private static void processPostProcessor(EntryConfig entryConfig, final ConfigLoadedResult loadedResult) throws ConfigException {
        try {
            final String postProcessorName = entryConfig.getPostProcessor();
            if (!StrUtil.isEmpty(postProcessorName)) {
                long delayTime = entryConfig.getDelayTime();
                Class<ConfigPostProcessor> cls = (Class<ConfigPostProcessor>) Class.forName(postProcessorName);
                Constructor<ConfigPostProcessor> constructor = cls.getDeclaredConstructor();
                constructor.setAccessible(true);
                ConfigPostProcessor postProcessor = constructor.newInstance();
                if (delayTime == 0) {
                    postProcessor.postProcessConfig(loadedResult);
                } else {
                    PostProcessorContainer processorContainer = new PostProcessorContainer(postProcessor, loadedResult, delayTime);
                    processorContainer.start();
                }
            }
        } catch (Throwable ex) {
            Logger.logError(ex);
            throw new ConfigException(ex.getMessage());
        }
    }


    /**
     * ���ܣ�����ļ������Ƿ����仯
     *
     * @param entryConfig
     * @return
     */
    private static boolean hasConfigChanged(EntryConfig entryConfig) {
        File configFile = new File(entryConfig.getAbsolutePath());
        Long modified = configFile.lastModified();
        Long lastModified = fileMap.get(configFile.getAbsolutePath());
        if (lastModified == null) {
            lastModified = -1L;
        }
        return ((modified.compareTo(lastModified) != 0));
    }

    /**
     * ���ܣ�ж�����������Ϣ
     *
     * @param entryConfig  EntryConfig
     * @param configLoader AbstractConfigLoader
     * @throws ConfigException
     */
    private static void unloadConfig(EntryConfig entryConfig, IConfigLoader configLoader) throws ConfigException {
        String loaderName = entryConfig.getLoaderClassName();
        configManager.removeConfigByLoaderName(loaderName);
        loaders.remove(configLoader);
        configLoader.unloadConfig();
    }


    /**
     * ���ܣ�ж���������á�
     */
    public static void unloadAllConfigs() {
        try {
            if (!loaders.isEmpty()) {
                for (IConfigLoader configLoader : loaders) {
                    if (configLoader != null) {
                        configLoader.unloadConfig();
                        configManager.removeConfigByLoaderName(configLoader.getClass().getName());
                    }
                }
                loaders.clear();
                loaders = null;
                fileMap = null;
                systemEntryFile = null;
            }
        } finally {
            System.gc();
        }
    }
}
