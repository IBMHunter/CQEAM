/**
  * ����:ҳ��ѡ�Ч��
  * @version 1.0
  * @time 2008-9-26
  */

/** ѡ�����Ҫ��ʾ������ */
var menuItem = new Array();

/**ѡ���˳�򣬱�ʾ�л�ʱ��ѡ����*/
var thismenu = 0;

/**Ҫ��ѡ����ʾ�Ĳ�Id*/
var layerId="";

/**
 *���ܣ���������ʾѡ����������� 
 */
function ElemDisplay(elem,show){
    show = show ? "block" : "none"
		if (elem && elem.style.display != show) elem.style.display = show;
}

/**
 * ���ܣ�����ѡ���Ч��
 * @return {}
 */

function gettabValueDefault(value,i){	 
    var span = 10 + i*120;
    var val1 = "<div id='div_" + value + "' onCLick='menu_click(" + i + ")' style='position:absolute;left:" + span + "px;top:58px;width:112px;height:30px;z-index:0;'>";
	val1 += '<table width=100% border=0 cellspacing=0 cellpadding=0>';
	val1 += "<tr>";
	val1 += '<td width="10%" class=tab>&nbsp;</td>';
    val1 += '<td width="80%"  align=center class=tab1>' + value + '</td>';
    val1 += ' <td width="10%" class=tab2>&nbsp;</td>';
    val1 += '</tr>';
	val1 += '</table>';
	val1 += '</div>';
    return val1;
}

function gettabValueNormal(value,i){
	var span = 10 + i*120;
	var val1 = "<div id='div_" + value + "' onCLick='menu_click(" + i + ")' style='position:absolute;left:" + span + "px;top:68px;width:112px;height:30px;z-index:0;'>";
	val1 += '<table  width=100% border=0 cellspacing=0 cellpadding=0>';
	val1 += "<tr>";
	val1 += '<td width="10%">&nbsp;</td>';
	val1 += "<td width=80% align=center class=text_gray3>" + value + "</td>";
	val1 += '<td width="10%" >&nbsp;</td>';
	val1 += '</tr>';
	val1 += '</table>';
    val1 += '</div>';
	return val1;	 
}

function ie_menu(){
	var builder = "";
	var builderdiv = "";
	builder = "<table border=0 cellspacing=0 cellpadding=0><tr>";

	for (var i=0; i < menuItem.length; i++) {
		if (thismenu==i) { 
			builder+="<td height=33px width=150px onCLick='menu_click("+i+")'>&nbsp;";
			builderdiv += gettabValueDefault(menuItem[i][0],i);
			builder+= "</td>";
						
			ElemDisplay(document.all(menuItem[i][1]),true);
		} else {
			//builder += "<td width=150px onCLick='menu_click("+i+")'>" + gettabValueNormal(menuItem[i][0]) + "</td>";
			builderdiv += gettabValueNormal(menuItem[i][0],i);
//			if (i==thismenu-1) { 
//				builder+="<td class=tab1 nowrap><div class=tab onClick='menu_click("+i+")'>&nbsp;"+menuItem[i][0]+"&nbsp;</div></td>";
//			} else if (i==thismenu+1) {
//				builder+="<td class=tab1 nowrap><div class=tab1 onClick='menu_click("+i+")'>&nbsp;"+menuItem[i][0]+"&nbsp;</div></td>";
//			} else {
//				builder+="<td class=tab1 nowrap><div class=tab2 onClick='menu_click("+i+")'>&nbsp;"+menuItem[i][0]+"&nbsp;</div></td>";
//			}
			ElemDisplay(document.all(menuItem[i][1]),false);
		}
	}
	
	builder = builderdiv + builder;

	//tdline = "<table height=\"4\" bgcolor=\"#D4D0C8\" bordercolorlight=\"#808080\" bordercolordark=\"#808080\" border=\"1\" cellpadding=\"0\" cellspacing=\"0\""+
		//"width=\"100%\"> <tr><td  nowrap bgcolor=\"#D4D0C8\" bordercolorlight=\"#808080\" bordercolordark=\"#808080\"></td> </tr> </table>"; 
	//builder += "<td height=2 width=100% class=menu>&nbsp;</td><tr><td height=4 colspan="+(menuItem.length+1)+"></td></tr></table>" +tdline;	    
	return builder;
} 

/**
 * ���ܣ�ѡ������¼�
 * @param {} num
 */
function menu_click(num) {
	if (thismenu != num) {
		thismenu = num;
		document.getElementById(layerId).innerHTML = ie_menu();
	}
} 

/**
 * ���ܣ�����ӿڣ���ʱֻ����ô˺���
 * @param {} ѡ�����ʾ�ı�����
 * @param {} Ҫ��ʾ�Ĳ�
 */
function println(folders,aId){
	menuItem = folders;
	layerId = aId;
	document.getElementById(layerId).innerHTML = ie_menu();
}