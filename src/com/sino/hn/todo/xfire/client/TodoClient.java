package com.sino.hn.todo.xfire.client;

import java.io.StringReader;
import java.lang.reflect.Proxy;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.codehaus.xfire.XFireFactory;
import org.codehaus.xfire.client.Client;
import org.codehaus.xfire.client.XFireProxy;
import org.codehaus.xfire.client.XFireProxyFactory;
import org.codehaus.xfire.service.Service;
import org.codehaus.xfire.service.binding.ObjectServiceFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.InputSource;

import com.mochasoft.todo.beans.Open;
import com.mochasoft.todo.service.ITodoService;
//import com.sino.hn.todo.xfire.beans.Open;
import com.sino.hn.todo.xfire.service.ConfigReader;
//import com.sino.hn.todo.xfire.service.ITodoService;

public class TodoClient {
	
	/**
	 * ������Ϣ
	 */
	private static String message;
	
	private static String resultCode;
	/**
	 * �õ�webservice�ͻ���ʵ��
	 * @return
	 * @throws MalformedURLException
	 */
	public static ITodoService getClient() throws MalformedURLException
	{
		Service srvcModel = new ObjectServiceFactory().create(ITodoService.class);
		XFireProxyFactory factory = new XFireProxyFactory(XFireFactory.newInstance().getXFire());
		
		//�����ļ���Ϣ
		String todo_url = ConfigReader.TODO_URL;
		String todo_username = ConfigReader.TODO_USERNAME;
		String todo_password = ConfigReader.TODO_PASSWORD;
		
		ITodoService service = (ITodoService) factory.create(srvcModel, todo_url);
		//�����֤��Ϣ
		XFireProxy proxy = (XFireProxy)Proxy.getInvocationHandler(service);
	    Client client = proxy.getClient();
	    client.addOutHandler(new ClientAuthenticationHandler(todo_username,todo_password));
	    
	    //�����������
	   /* client.setProperty(CommonsHttpMessageSender.HTTP_TIMEOUT, "300");
	    client.setProperty(CommonsHttpMessageSender.DISABLE_KEEP_ALIVE, "true");
	    client.setProperty(CommonsHttpMessageSender.DISABLE_EXPECT_CONTINUE, "true");
	    client.setProperty(CommonsHttpMessageSender.HTTP_PROXY_HOST, "MY_PROXY_ADDRESS");
	    client.setProperty(CommonsHttpMessageSender.HTTP_PROXY_PORT, "MY_PROXY_PORT");
*/
	    
	    return service;
	};
	/**
	 * ����ɹ�ʧ��xml���ɹ�����true��ʧ�ܷ���false��getMessage���Եõ�����ʧ����Ϣ
	 * @param responseXml
	 * @return
	 */
	public static boolean isSuccess(String responseXml) 
	{
		TodoClient.setMessage(null);//��ս��
		boolean ret = false;
		DocumentBuilder docBuilder = null;
		Document doc = null;
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		try {
			docBuilder = docBuilderFactory.newDocumentBuilder();
			doc = docBuilder.parse(new InputSource(new StringReader(responseXml)));   
		} catch (Exception e) {
			throw new RuntimeException("��ȡ���췴��xml���ݳ���");
		} 
		doc.getDocumentElement().normalize();
		Element orgsNode=(Element)doc.getFirstChild();//�õ�orgs�ڵ�
		
		Element org=orgsNode;//�õ�һ��result
		NodeList attList = org.getChildNodes();//�õ����Լ���
		for (int j = 0; j < attList.getLength(); j++) {
			if(attList.item(j).getParentNode()==(Node)org)//����ѯ�ӽڵ�
			{
				if(attList.item(j) instanceof Element)
				{
					Element att = (Element)attList.item(j);//�õ�һ������
					String name = att.getNodeName();//�õ���������
					
					NodeList attValueNode = att.getChildNodes(); 
					Text text = (Text)attValueNode.item(0);
					String value = text.getData(); //�õ�����ֵ
					
					if("resultCode".equals(name))
					{
						resultCode = value;
						if("1".equals(value))
						{
							ret = true;
						}else if("-1".equals(value))
						{
							ret = false;
						}
					}else if("resultDesc".equals(name))
					{
						TodoClient.setMessage(value);
					}
				}
			}
		}
		return ret;
	}
	public static String getMessage() {
		return message;
	}
	public static void setMessage(String message) {
		TodoClient.message = message;
	}
	
	/**
	 * ����
	 * @param args
	 */
	public static void main(String[] args) {
		try {
		    Service serviceModel = new ObjectServiceFactory().create(ITodoService.class);
//		    ITodoService service = (ITodoService) new XFireProxyFactory().create(serviceModel,
//		        "http://10.87.13.247:9081/todoWs/services/TodoService" );  
		    ITodoService service = (ITodoService) new XFireProxyFactory().create(serviceModel,
			        "http://10.87.9.32:9080/todoWs/services/TodoService" ); 
		    XFireProxy proxy = (XFireProxy)Proxy.getInvocationHandler(service);
		    Client client = proxy.getClient();
		    client.addOutHandler(new ClientAuthenticationHandler("abcd","1234"));
		    //������Ȩ��Ϣ
//		    client.addOutHandler(new ClientAuthenticationHandler("mocha_ha_portal_todo","Ha3slkC6"));
		    //�������web services�����ķ�����Ϣ
		    
		    List list = new ArrayList();
			Open open = new Open();
//			open.setPri("1");
//			open.setDoc_id("123123");
//			open.setDoc_type("��˾����");
//			open.setSender("����2");
//			open.setSource_id("PR");
//			open.setStart_time("2010-11-23 14:40:57");
//			open.setSys_id("2");
//			opeb.setTitle("�ɹ����뵥�Ӱ���");
//			open.setType("1");
//			open.setUrl("/snoa01.sn.cmcc:80/SGSOA/fwgl.nsf/0/FE5798DB97BB6C4F482575DE00140421?OpenDocument");
//			open.setUser_id("1234567");
//			open.setWork_id("12345");
			
			open.setPri("1");
			open.setDoc_id("111231312");
			open.setDoc_type("sdfsfsd");
			open.setSender("chchchchchchchchc");
			open.setSource_id("PR");
			open.setStart_time("2010-11-23 14:40:57");
			open.setSys_id("123");
			open.setTitle("132213123123");
			open.setType("1");
			open.setUrl("/snoa01.sn.cmcc:80/SGSOA/fwgl.nsf/0/FE5798DB97BB6C4F482575DE00140421?OpenDocument");
			open.setUser_id("chchchchchchchchc");
			open.setWork_id("33fbe5f001340ceed16d515733fbe5f001340817c70f34bb");
			
			list.add(open);
			//���÷���
			String ret = service.saveOpen(list);
		    System.out.println(ret);
		   } catch (MalformedURLException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		   } catch (IllegalArgumentException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		   } catch( Throwable e){
			   e.printStackTrace();
		   }

	}
	public static String getResultCode() {
		return resultCode;
	}
	public static void setResultCode(String resultCode) {
		TodoClient.resultCode = resultCode;
	}

}
