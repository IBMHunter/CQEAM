<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.sino.soa.common.SOAConstant"%>
<%@page import="com.sino.hn.todo.util.HnOAConfig"%>
<%@page import="com.sino.framework.security.bean.SessionUtil"%>
<%@page import="com.sino.framework.security.dto.ServletConfigDTO"%>
<%@page import="java.net.URL"%>
<%@page import="java.net.URLClassLoader"%>
<%-- 
 * @ϵͳ����: 
 * @��������: ϵͳ�����鿴ҳ��
 * @�޸���ʷ: ��ʼ�汾1.0
 * @��˾����: ����˼ŵ����Ϣ�������޹�˾
 * @��ǰ�汾��1.0
 * @��������: sj
 * @����ʱ��: Nov 30, 2011
--%>
<%
	try {
%>
<html>
	<head>
		<title>ϵͳ����</title>    
	</head>

	<body > 
		<p>
			���ݿ���Ϣ��
			<%  
				
			%>
		</p>
		<p>
			SOA������<br>
			������:
			<%=SOAConstant.SERVER_NAME%><br>
			�˿�:
			<%=SOAConstant.SERVER_PORT%><br>
		</p>
		<br>

		<p>
			OA���컷����<br>
			���ϴ���·��:
			<%=HnOAConfig.getTodo_url()%><br>
			���ϴ����û���:
			<%=HnOAConfig.getTodo_username()%><br>
			Todo_password:
			<%=HnOAConfig.getTodo_password()%><br>
			EAM�ʲ�·��:
			<%=HnOAConfig.getEam_url()%><br>
		</p>
		<br>

		<p>
			SOA����������<br>
			OrgStructureName:
			<%=HnOAConfig.getOrgStructureName()%><br>
			TDOrgStructureName:
			<%=HnOAConfig.getTDOrgStructureName()%><br>
			ProvinceCode:
			<%=HnOAConfig.getProvinceCode()%><br>
			TDProvinceCode:
			<%=HnOAConfig.getTDProvinceCode()%><br>
			ODIUser:
			<%=HnOAConfig.getODIUser()%><br>
			OaThreadSleepTime:
			<%=HnOAConfig.getOaThreadSleepTime()%><br>
			FlexValueSetNameMis:
			<%=HnOAConfig.getFlexValueSetNameMis()%><br>
			FlexValueSetNameTD:
			<%=HnOAConfig.getFlexValueSetNameTD()%><br>
			Loc1SetNameMis:
			<%=HnOAConfig.getLoc1SetNameMis()%><br>
			Loc1SetNameTD:
			<%=HnOAConfig.getLoc1SetNameTD()%><br>
		</p>
		<br>

		<%
			ServletConfigDTO servletConfig = SessionUtil
						.getServletConfigDTO(request);
		%>
		<p>
			web config ������<br>
			servletConfig.getEnvName :<%=servletConfig.getEnvName()%><br>
			servletConfig.getProvinceCode :<%=servletConfig.getProvinceCode()%><br>
			servletConfig.getProCompanyCode :<%=servletConfig.getProCompanyCode()%><br>
			servletConfig.getProCompanyName :<%=servletConfig.getProCompanyName()%><br>
			<br>

			servletConfig.getTdProvinceCode :<%=servletConfig.getTdProvinceCode()%><br>
			servletConfig.getTdProCompanyCode :<%=servletConfig.getTdProCompanyCode()%><br>
			servletConfig.getTdProvinceOrgId :<%=servletConfig.getTdProvinceOrgId()%><br>
			<br>


			servletConfig.getCompAssetsMgr :<%=servletConfig.getCompAssetsMgr()%><br>
			servletConfig.getDeptAssetsMgr :<%=servletConfig.getDeptAssetsMgr()%><br>
			servletConfig.getCityAdminRole :<%=servletConfig.getCityAdminRole()%><br>

		</p>
		<br>
	</body>
</html>

<% 

	} catch (Exception ex) {
		out.println( ex.getMessage() );
		ex.printStackTrace();
	}
%>


<%

ClassLoader systemClassloader=ClassLoader.getSystemClassLoader();
URL[] dd = ((URLClassLoader)ClassLoader.getSystemClassLoader()).getURLs();
for (int i = 0; i < dd.length; i++) {
    out.println("*********  "+dd[i] + "<BR>");
}
out.println(" system classloader : "+systemClassloader );


%>



<%

try{
	ClassLoader loader = this.getClass().getClassLoader();
	loader.loadClass( "org.apache.cxf.jaxws.spi.ProviderImpl" );
}catch(Throwable ex){
	out.println( "<br>Load Exception : " + ex.getMessage() );
}
%>


<%

try{ 
	ClassLoader.getSystemClassLoader().loadClass( "org.apache.cxf.jaxws.spi.ProviderImpl" );
}catch(Exception ex){
	out.println( "<br>System Load Exception : " + ex.getLocalizedMessage() );
}catch(Throwable ex){
	out.println( "<br>System Load Exception : " + ex.getLocalizedMessage() );
}
%>