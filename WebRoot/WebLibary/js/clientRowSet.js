
/**
  * ����:��ajax��ʽ��������������󣬲��ύ������������Ҫ�Ĳ���
  * @version 1.0
  * @time 2008-8-8
  */


 var http_request;
 var fg;
 function send_request(path){
     http_request = false;
       if(window.XMLHttpRequest) { 						             //Mozilla �����
               http_request = new XMLHttpRequest();
               if (http_request.overrideMimeType) {			        //����MiME���
                       http_request.overrideMimeType('text/xml');
               }
       }
       else if (window.ActiveXObject) { 					         // IE�����
               try {
                       http_request = new ActiveXObject("Msxml2.XMLHTTP");
               } catch (e) {
                       try {
                               http_request = new ActiveXObject("Microsoft.XMLHTTP");
                       } catch (e) {}
               }
       }

       if (!http_request) { 							                  // �쳣����������ʵ��ʧ��
               throw "���ܴ���XMLHttpRequest����ʵ��.";
               return false;
       }
       http_request.onreadystatechange = processRequest;               // �趨�����Ľ���ĺ���
       http_request.open("POST", path, false);				          // ȷ����������ķ�ʽ��URL�Լ��Ƿ�ͬ��ִ���¶δ���
       http_request.send(null);                                       // �����������
 }


 /*
  * �������������ĺ���
  */
 function processRequest(){
     if (http_request.readyState == 4) { 				         //  �ж϶���״̬
         if (http_request.status == 200) { 				         // ��Ϣ�Ѿ��ɹ����أ���ʼ������Ϣ
             var result = http_request.responseText;
             fg = eval( "("+ result +")");
         } else { 							                     //ҳ�治����
             alert("�����ҳ�����쳣��");
         }
       }
 }


 /*
  * ajax��������
  */
 function clientRowSet(){

     this.modelClassName;                                                         //����
     this.methodName;                                                        //������
     this.methodParameterName = new Array();                               //���������б�
 
     this.send_request = function(){                                       //�������󲢷��ؽ����
         try{
             var url = this.joinURL();
             send_request(url);
         }catch(e){
             throw e;
         }
         return fg;
     }

     /*
      * ƴ��URL
      */
     this.joinURL = function(){

         if(!this.modelClassName){
             throw "����modelClassNameδ����";
             return;
         }
         if(!this.methodName){
             throw "����methodNameδ����";
             return;
         }
        if(!this.methodParameterName){
             throw "����methodParameterNameδ����";
             return;
         }

       var url = "/servlet/com.sino.base.service.ClientRowSetService";
       url +=
             "?modelClassName="+this.modelClassName+
             "&methodName="+this.methodName;
       for(var i=0;i<this.methodParameterName.length;i++){
          url += "&methodParameterName="+this.methodParameterName[i];
       }
       return url;
     }
 }
