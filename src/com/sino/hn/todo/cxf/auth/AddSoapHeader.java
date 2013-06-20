package com.sino.hn.todo.cxf.auth;

import java.util.List;

import javax.xml.namespace.QName;

import org.apache.cxf.binding.soap.SoapHeader;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.headers.Header;
import org.apache.cxf.helpers.DOMUtils;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.Phase;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.sino.hn.todo.util.HnOAConfig;

/**
 * 
 * @ϵͳ����:
 * @��������: ͷ��Ϣ����
 * @�޸���ʷ: ��ʼ�汾1.0
 * @��˾����: ����˼ŵ����Ϣ�������޹�˾
 * @��ǰ�汾��1.0
 * @��������: sj
 * @����ʱ��: Dec 1, 2011
 */
public class AddSoapHeader extends AbstractSoapInterceptor {
	public AddSoapHeader() {
		super(Phase.WRITE);
	}

	public void handleMessage(SoapMessage message) throws Fault {
		String spPassword = HnOAConfig.getTodo_username();
		String spName = HnOAConfig.getTodo_password();

		QName qname = new QName("AuthenticationToken");
		Document doc = DOMUtils.createDocument();
		// �Զ���ڵ�
		Element spId = doc.createElement("tns:Username");
		spId.setTextContent(spName);
		// �Զ���ڵ�
		Element spPass = doc.createElement("tns:Password");
		spPass.setTextContent(spPassword);

		Element root = doc.createElementNS(HnOAConfig.getTodo_url(),
				"tns:AuthenticationToken");
		root.appendChild(spId);
		root.appendChild(spPass);

		SoapHeader head = new SoapHeader(qname, root);
		List<Header> headers = message.getHeaders();
		headers.add(head);
		// System.out.println(">>>>>���header<<<<<<<");
	}
}
