<%@ page contentType="text/html; charset=GBK" language="java"%>

<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
 
<c:set var="num" value="1" scope="page"></c:set>
<%
	String[][] str = new String[][]{
									new String[]{"�����ࣺ","�ؼ���������"},
									new String[]{"�η��ࣺ","����������"},
									new String[]{"�������ࣺ","����������"}};
    pageContext.setAttribute("str",str);
	
	String[][] str2 = new String[][]{
									new String[]{"����1��","����4��"},
									new String[]{"����2��","����5"},
									new String[]{"����3��","����6��"}};
	pageContext.setAttribute("str2",str2);
	
%>

<html>
	<head>
	</head>
	
	<body>
		<form name="form1" method="post" action="">
 			<div id="appType">
				<table  borderColor=#666666 cellSpacing=0 borderColorDark=#ffffff cellPadding=2 width="90%" border=1 align="center">
					<tr>
						<td width="40%">*Ӧ�����ͣ�</td>
						<td width="60%">
							<input type="radio" name="radiobutton" value="radiobutton">��������
							<input type="radio" name="radiobutton" value="radiobutton">����������
						</td>
					</tr>
					
					<tr>
						<td>*�������ƣ�</td>
						<td>
							<select name="select"></select>
						</td>
					</tr>
					
					<tr>
						<td>*��ʹ�������</td>
						<td>
							<select name="select2"></select>
						</td>
					</tr>
					
					<tr>
						<td>*��ʹ���߽�ɫ��</td>
						<td>
							<select name="select3"></select>
						</td>
					</tr>
					
					<tr>
						<td>*�������ƣ�</td>
						<td>
							<input type="text" name="textfield">
						</td>
					</tr>
					
					<tr>
						<td>*Ӧ�÷�������</td>
						<td>
							<input type="text" name="textfield2">
						</td>
					</tr>
					
					<tr>
						<td>*Ӧ����ʾ�������ͣ�</td>
						<td>
							<input type="radio" name="radiobutton" value="radiobutton">�ҿ����
							<input type="radio" name="radiobutton" value="radiobutton">�´���
						</td>
					</tr>
				</table>
			</div>
		
			<div id="flowDefine">
				<table  borderColor=#666666 cellSpacing=0 borderColorDark=#ffffff cellPadding=2 width="90%" border=1 align="center">
					<tr>
						<td width="40%">*Ӧ�����ƣ�</td>
						<td width="60%">
							<input type="text"/>
						</td>
					</tr>
					
						<tr>
						<td>�����Ƿ�Ӧ�ã�</td>
						<td>
							<input type="radio" name="radiobutton" value="radiobutton">��
							<input type="radio" name="radiobutton" value="radiobutton">��
						</td>
					</tr>
					
					<tr>
						<td>ȷ����Ϣ��</td>
						<td>
							<input type="radio" name="radiobutton" value="radiobutton">��ֹ
							<input type="radio" name="radiobutton" value="radiobutton">����
						</td>
					</tr>
					
					<tr>
						<td>���ʱ������Ϣ:</td>
						<td>
							<input type="radio" name="radiobutton" value="radiobutton">��
							<input type="radio" name="radiobutton" value="radiobutton">��
						</td>
					</tr>
					
						<tr>
						<td>��������</td>
						<td>
							<input type="checkbox" name="radiobutton" value="radiobutton">
							<input type="checkbox" name="radiobutton" value="radiobutton">
							<input type="checkbox" name="radiobutton" value="radiobutton">
							<input type="checkbox" name="radiobutton" value="radiobutton">
							<input type="checkbox" name="radiobutton" value="radiobutton">
							<input type="checkbox" name="radiobutton" value="radiobutton">
						</td>
					</tr>
					
					<tr>
						<td>*Ӧ�����ݽӿ�����:</td>
						<td>
							<input type="text"/>
						</td>
					</tr>
					
					<tr>
						<td>Ӧ������ SQLModel:</td>
						<td>
							<input type="text"/>
						</td>
					</tr>
					
					<tr>
						<td>Ӧ��URL��</td>
						<td>
							<input type="text">
						</td>
					</tr>
					
				</table>
			</div>
				
			
			<div id="jobDefine">
				<table  borderColor=#666666 cellSpacing=0 borderColorDark=#ffffff cellPadding=2 width="90%" border=1 align="center">
					<tr>
						<td colspan="2">���������ַ����Ӧ������</td>
						<td colspan="2">Ӧ���ĵ����빤������Ӧ�ñ�����</td>
					</tr>
					<c:forEach items="${pageScope.str}" var="kk">
                        <tr>
							<td width=""><c:out value="${kk[0]}"/></td>
							<td width="">
								<input type="text"/>
							</td>
							
							<td width=""><c:out value="${kk[1]}"/></td>
							<td width="">
								<input type="text"/>
							</td>
						</tr>
					</c:forEach>
					
					<tr>
						<td colspan="4">Ӧ���ĵ����빤�����ĵ�Ӧ�ñ�����</td>
					</tr>
					
					<c:forEach items="${pageScope.str2}" var="kk">
						<tr>
							<td width=""><c:out value="${kk[0]}"/></td>
							<td width="">
								<input type="text"/>
							</td>
							
							<td width=""><c:out value="${kk[1]}"/></td>
							<td width="">
								<input type="text"/>
							</td>
						</tr>					
					</c:forEach>
				</table>
			</div>
			
			<div id="apiDefine">
				<table  borderColor=#666666 cellSpacing=0 borderColorDark=#ffffff cellPadding=2 width="90%" border=1 align="center">
					<tr>
						<td></td>
					</tr>
				</table>
			</div>
		</form>
	</body>
</html>
