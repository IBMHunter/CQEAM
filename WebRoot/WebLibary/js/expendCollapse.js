
var cPath = "../images/buttonbar/Collapse.gif";
var ePath = "../images/buttonbar/Expand.gif";
var ep = ePath.substring(ePath.lastIndexOf("/")+1);
var cp = cPath.substring(cPath.lastIndexOf("/")+1);

 function instead(obj){//����ͼ��
    var op = obj.src.substring(obj.src.lastIndexOf("/")+1);
    var path = (op == ep) ? cPath : ePath;  
    obj.src = path;
    return path == ePath ? ep : cp;
 }
 
 function displayOrNone(obj,imgName){
    obj.style.display = (imgName == ep) ? "" : "none";
 }
 
 function expendOrColl(imgObj,childTrId){//һ��
    var os = instead(imgObj);
    var childs = document.getElementsByName(childTrId);
    for(var i=0;i<childs.length;i++){
        displayOrNone(childs.item(i),os);
    }    
 }
         
 function expendOrColl2(imgObj,trId){//����
    var os = instead(imgObj);
    for(var i=1;;i++){
        var childTrId = trId+"_"+i;
        var childObj = document.getElementById(childTrId);
        if(childObj == null) return;
        displayOrNone(childObj,os);
        
        childTrId = childTrId+"_tr";
        var childs = document.getElementsByName(childTrId);
        var img = childObj.getElementsByTagName("img")[0];
        var cs = img.src.substring(img.src.lastIndexOf("/")+1);
        for(var j=0;j<childs.length;j++){
            if(os == ep){
               displayOrNone(childs.item(j),cs);
            }else{
               displayOrNone(childs.item(j),os);
            }
        }
        
    }  
 }
     
 function operate(tag,path){//һ��
 	for(i=1;;i++){
 		var trId = "tr_"+i;
 		var obj = document.getElementById(trId);
 		if(obj == null) return;
        var img = obj.getElementsByTagName("img")[0];
        img.src = path;
        var trId2 = trId + "_tr";
        var childObj = document.getElementsByName(trId2);
        for(var j=0;j<childObj.length;j++){
            childObj.item(j).style.display=tag;
       	}
 	}
 }
 
 function operate2(tag,path){//����
    for(var i=1;;i++){
        var trId = "tr_" + i;
        var obj = document.getElementById(trId);
        if(obj == null) return;
        var img = obj.getElementsByTagName("img")[0];
        img.src = path;
        for(var j=1;;j++){
            var trId2 = trId + "_"+j;
            var childObj = document.getElementById(trId2);
            if(childObj == null) break;
            childObj.style.display = tag;
            var img = childObj.getElementsByTagName("img")[0];
            img.src = path;
            var trId3 = trId2 + "_tr";
            var childs = document.getElementsByName(trId3);
            for(var k=0;k<childs.length;k++){
                childs.item(k).style.display = tag;
            }
        }       
    }
 }
 
 function expendAll(){//չ��ȫ��
    operate("",ePath);
 }
 
 function collAll(){//�۵�ȫ��
    operate("none",cPath);
 }
 
 function expendAll2(){//����չ��ȫ��
    operate2("",ePath);
 }
 
 function collAll2(){//�����۵�ȫ��
    operate2("none",cPath);
 }
      
 function del(actionStr){//ɾ��
		var tc = document.getElementsByName("mdc");
		for(var i=0;i<tc.length;i++){
			if(tc.item(i).checked){
				if(confirm("ȷ��ɾ����ѡ���� ����������ȷ���� ��������ȡ����")){
					mainFrm.action = actionStr;
					mainFrm.submit();
					return;
				}else{
					return;
				}	
			}
		}
	}

function sdAll(cbName){//ȫѡ��ѡ���и�ѡ��
	var cn = document.getElementsByName(cbName);
	for(var i=0;i<cn.length;i++){
		if(cn.item(i).checked == false){
			for(var j=0;j<cn.length;j++){
				cn.item(j).checked = true;
			}
			return;
		}
		if(i == cn.length-1){
			for(var j=0;j<cn.length;j++){
				cn.item(j).checked = false;
			}
			return;
		}
	}
}
    