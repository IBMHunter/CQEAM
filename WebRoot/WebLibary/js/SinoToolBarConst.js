var constImgPath="/images/buttonbar/";

var constTitleBarBGColor="#358BCE";
var constTitleBarHeight=22;
var constTitleBarFont="style=\"font-size: 10pt\" color=#FFFFFF";
var constTitleBarBg = "/cms_images/table_bg1.jpg";
var constTitleTdClass = "titleTdClass"; //add

var constButtonBarFrameHeadColor="#EFEFEF";
var constButtonBarBGColor="#EFEFEF";
var constButtonBarFrameBottomColor="#EFEFEF";
var constButtonBarHeight=20;
var constButtonHeadHeight=4;
var constButtonBottomHeight=4;

var constContentTitleBGColor="#EFEFEF";
var constContentTitleHeight=20;

var constIsXPStype=true;    //�Ƿ�XP��ʽ
var constTitleCheckBoxValue=false;   //������CheckBoxֵ

var constTHeadClass = "headerTable";
  
var CONFIRMMESSAGE_MASK = 0x8000;
var FINISHMESSAGE_MASK = 0x4000;
var REVIEW_STATUS_MASK = 0x200;
var REVIEW_MASK = 0x100;
var CANCEL_MASK = 0x1;
var SPECIALSEND_MASK = 0x2;
var SENDBACK_MASK = 0x4;
var VIEWPROCESS_MASK = 0x8;
var SENDTO_MASK = 0x10;
var CYCLE_MASK = 0x20;
var CYCLE_STATUS = 0x40;
var SIGN_MASK = 0x400;
var SAVE_MASK = 0x800;
var COMPLETE_MASK = 0x1000;

//=================================================================
//printTitleBar(desc)
//��ʾ��ʾ����Ϣ
//=================================================================

//=================================================================
//parameter 1:display action true or false
//parameter 2:button description
//parameter 3:gif name
//parameter 4:tip message
//parameter 5:function name
//=====================================================================
//var ArrAction1=new Array(true,"ɾ��","del.gif","ɾ����Ϣ","del");
//var ArrAction2=new Array(true,"ɾ��2","del.gif","ɾ����Ϣ2","del2");
//var ArrActions=new Array(ArrAction1,ArrAction2);

//=================================================================
//parameter 1:View description
//parameter 2:url 
//=====================================================================
//var ArrSinoView1=new Array("view1","url1");
//var ArrSinoView2=new Array("view2","url2");
//var ArrSinoViews=new Array(ArrSinoView1,ArrSinoView2);
//setViewName(desc);
//��ʾView����,�磺��ҳ�롱
//=================================================================

//=================================================================
//printToolBar
//��ʾ��ť����Ϣ�����������ArrActions��ť��ArrSinoViews��ͼ��Ϣ
//=================================================================

//=====================================================================
//parameter 1:Title description
//parameter 2:isCheckBox 
//parameter 3:width
//=====================================================================
//var ArrSinoTitle1=new Array("",true,"10%");
//var ArrSinoTitle2=new Array("name",false,"50%");
//var ArrSinoTitle3=new Array("group",false,"40%");
//var ArrSinoTitles=new Array(ArrSinoTitle1,ArrSinoTitle2,ArrSinoTitle3);
//printViewTitleHead();�����ʾ���ݱ�������table��Ϣ
//printViewTitle();�����ʾ���ݱ�����
//printViewTitleBottom;//�ر���ʾ���ݱ�������table��Ϣ
//=====================================================================
	
	
//=================================================================
//ShowSinoButton(nPosition)
//��ʾ�ڼ�����ť��nPosition��0��ʼ
//
//HideSinoButton(nPosition)
//���صڼ�����ť��nPosition��0��ʼ
//=================================================================