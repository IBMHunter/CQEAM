//ϵͳ������
function getTitle(){
	var myTitle;
	myTitle="PMIS �豸������Ϣϵͳ";
	document.write(myTitle);
}
	
//��¼ҳ�ϵİ�ť����	
function  login_hover(){
	document.all.btn.src="/images/login_btn_over.gif";
}


function login_out(){
	document.all.btn.src="/images/login_btn.gif";
}	


var popUpWin2=0;
function popUpWindow(URLStr, left, top, width, height){
	if(popUpWin2){
		if(!popUpWin.closed){
			popUpWin.close();
		}
	}
	popUpWin = open(URLStr, 'popUpWin2', 'toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=yes,copyhistory=yes,width='+width+',height='+height+',left='+left+', top='+top+',screenX='+left+',screenY='+top+'');
}

