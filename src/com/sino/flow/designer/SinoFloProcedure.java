package com.sino.flow.designer;

import java.io.InputStream;
import java.util.Vector;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class SinoFloProcedure extends SinoDocBase
{

    SinoFloTasks tasks;
    SinoFloInfos flows;
    SinoFloTexts labels;
    protected SinoDocField FIELDS[] = {
        new SinoDocField("Name", 1, ""), new SinoDocField("Desc", 1, ""), new SinoDocField("InBasketMode", 0, 0), new SinoDocField("Remarks", 1, ""), new SinoDocField("CreateUser", 1, ""), new SinoDocField("CreateDate", 1, ""), new SinoDocField("ModifyUser", 1, ""), new SinoDocField("ModifyDate", 1, ""), new SinoDocField("CurTaskID", 0, 9), new SinoDocField("CurFlowID", 0, 9),
        new SinoDocField("CurLabelID", 0, 9)
    };

    public SinoFloProcedure()
    {
        fields = FIELDS;
        tasks = new SinoFloTasks();
        flows = new SinoFloInfos();
        labels = new SinoFloTexts();
    }

    
    public int getTaskCount()
    {
        return tasks.getCount();
    }

    public int getFlowCount()
    {
        return flows.getCount();
    }

    public int getLabelCount()
    {
        return labels.getCount();
    }

    public SinoFloTask getFirstTask()
    {
        return (SinoFloTask)tasks.getFirstItem();
    }

    public SinoFloTask getNextTask()
    {
        return (SinoFloTask)tasks.getNextItem();
    }
   
    public SinoFloTask getNthTask(int i)
    {
        return (SinoFloTask)tasks.getNthItem(i);
    }

    public SinoFloInfo getFirstFlow()
    {
        return (SinoFloInfo)flows.getFirstItem();
    }

    public SinoFloInfo getNextFlow()
    {
        return (SinoFloInfo)flows.getNextItem();
    }

    public SinoFloInfo getNthFlow(int i)
    {
        return (SinoFloInfo)flows.getNthItem(i);
    }

    public SinoFloText getFirstLabel()
    {
        return (SinoFloText)labels.getFirstItem();
    }

    public SinoFloText getNextLabel()
    {
        return (SinoFloText)labels.getNextItem();
    }

    public SinoFloText getNthLabel(int i)
    {
        return (SinoFloText)labels.getNthItem(i);
    }

    public SinoFloTask getTaskByName(String s)
    {
        return (SinoFloTask)tasks.getItemByName(s);
    }

    public SinoFloTask getTaskByID(int i)
    {
        return (SinoFloTask)tasks.getItemByValue("TaskID", i);
    }

    public SinoFloInfo getFlowByID(int i)
    {
        return (SinoFloInfo)flows.getItemByValue("FlowID", i);
    }

    public SinoFloTask getStartTask()
    {
        for(int i = 0; i < flows.getCount(); i++)
        {
            SinoFloInfo wfflow = (SinoFloInfo)flows.getNthItem(i);
            if(wfflow.getIntValue("PrevTaskID") == 1)
                return getTaskByID(wfflow.getIntValue("NextTaskID"));
        }

        return null;
    }

    public SinoFloInfo getStartFlow()
    {
        for(int i = 0; i < flows.getCount(); i++)
        {
            SinoFloInfo wfflow = (SinoFloInfo)flows.getNthItem(i);
            if(wfflow.getIntValue("PrevTaskID") == 1)
                return wfflow;
        }

        return null;
    }
    /**
     * ����:��������ڵ����Ϣ(sinoFloTask)��ȡ������������ڵ����Ϣ(sinoFloTask)
     * @param wftask ָ��������ڵ����Ϣ(sinoFloTask)
     * @return Vector ���sinoFloTask��Ϣ
     */
    public Vector getAllNextTask(SinoFloTask wftask)
    {
        Vector vector = getAllOutFlow(wftask);
        Vector retVector=new Vector();
        
        for(int i = 0; i < vector.size(); i++)
        {
            SinoFloInfo wfflow = (SinoFloInfo)vector.elementAt(i);
            SinoFloTask ts=getNextTask(wfflow);
            retVector.add(ts);
        }

        return retVector;
    }    
    /**
     * ����:��������ڵ����Ϣ(sinoFloTask)��ȡ���е������Flow��Ϣ��sinoFloInfo��
     * @param wftask ָ��������ڵ����Ϣ(sinoFloTask)
     * @return Vector ���sinoFloInfo��Ϣ
     */
    public Vector getAllOutFlow(SinoFloTask wftask)
    {
        Vector vector = new Vector();
        for(int i = 0; i < flows.getCount(); i++)
        {
            SinoFloInfo wfflow = (SinoFloInfo)flows.getNthItem(i);
            if(wfflow.getIntValue("PrevTaskID") == wftask.getIntValue("TaskID"))
                vector.addElement(wfflow);
        }

        return vector;
    }
    /**
     * ���ܣ�����ָ����Flow��Ϣ��sinoFloInfo������ȡ��һ������ڵ����Ϣ(sinoFloTask)��
     * @param wfflow ָ����Flow��Ϣ��sinoFloInfo��
     * @return sinoFloTask
     */
    public SinoFloTask getPreTask(SinoFloInfo wfflow)
    {
        return getTaskByID(wfflow.getIntValue("PreTaskID"));
    }
    
    /**
     * ���ܣ�����ָ����Flow��Ϣ��sinoFloInfo������ȡ��һ������ڵ����Ϣ(sinoFloTask)��
     * @param wfflow ָ����Flow��Ϣ��sinoFloInfo��
     * @return sinoFloTask
     */
    public SinoFloTask getNextTask(SinoFloInfo wfflow)
    {
        return getTaskByID(wfflow.getIntValue("NextTaskID"));
    }

    public boolean readFrom(InputStream inputstream)
    {
        if(!super.readFrom(inputstream))
            return false;
        if(!tasks.readFrom(inputstream))
            return false;
        if(!flows.readFrom(inputstream))
            return false;
        return labels.readFrom(inputstream);
    }



    public boolean loadFromDomNodeList(NodeList nodelist)
    {
        boolean flag = false;
        try
        {
            for(int i = 0; i < nodelist.getLength(); i++)
            {
                Node node = nodelist.item(i);
                if(node.getNodeType() != 1)
                    continue;
                if(node.getNodeName().equalsIgnoreCase("properties") && !super.loadFromDomNodeList(node.getChildNodes()))
                {
                    flag = false;
                    break;
                }
                if(node.getNodeName().equalsIgnoreCase("tasks") && !tasks.loadFromDomNodeList(node.getChildNodes()))
                {
                    flag = false;
                    break;
                }
                if(node.getNodeName().equalsIgnoreCase("flows") && !flows.loadFromDomNodeList(node.getChildNodes(), false))
                {
                    flag = false;
                    break;
                }
                if(!node.getNodeName().equalsIgnoreCase("lables") || labels.loadFromDomNodeList(node.getChildNodes()))
                    continue;
                flag = false;
                break;
            }

            flag = true;
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
            flag = false;
        }
        finally
        {
            
        }
        return flag;
    }

    public void dump()
    {
        super.dump();
        tasks.dump();
        flows.dump();
        labels.dump();
    }
}
