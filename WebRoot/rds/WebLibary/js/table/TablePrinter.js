function TableHeaderPrinter() {
    this.setTableWidth = function(tableWidth) {
        this.tableWidth = tableWidth;
    };

    this.setColumnArr = function(columnArr) {
        this.columnArr = columnArr;
    };

    this.setWidthArr = function(widthArr) {
        this.widthArr = widthArr;
    };

    this.setXScroll = function(xScroll) {
        this.xScroll = xScroll;
    };

    this.setCheckObjName = function(checkObjName) {
        this.checkObjName = checkObjName;
    };

    this.setSortTable = function(sortTable) {
        if(sortTable == null || sortTable == undefined){
            alert("δ���ô�������ID��ϵͳ�������С�");
            return;
        }
        this.sortTable = sortTable;
    };

    this.createTableHeader = function() {
        if(this.columnArr == null || this.columnArr == undefined){
            alert("δ���ø��б��⣬ϵͳ�������С�");
            return;
        }
        if(this.widthArr == null || this.widthArr == undefined){
            alert("δ���ø��п�ȣ�ϵͳ�������С�");
            return;
        }
        if(this.columnArr.length != this.widthArr.length){
            alert("�б����������п�����鳤�Ȳ�һ�£�ϵͳ�������С�");
            return;
        }
        var scrollProp = "overflow-y:scroll";
        if (this.xScroll) {
            scrollProp += ";overflow-x:scroll";
        } else {
            scrollProp += ";overflow-x:hidden";
        }
        if(this.tableWidth == null || this.tableWidth == undefined){
            this.tableWidth = "100%";
        }
        document.write("<div style=\"position:absolute;top:52px;left:1px;width:100%;" + scrollProp + "\" id=\"headDiv\">");
        document.write("<table id=\"headTable\" border=\"1\" bordercolor=\"#666666\" width=\"" + this.tableWidth + "\" cellpadding=\"0\" cellspacing=\"0\">");
        document.write("<tr class=\"headerTR\">");
        var hasChkObj = false;
        if (this.columnArr[0].toLowerCase() == "checkbox") {
            hasChkObj = true;
            document.write("<td width=\"" + this.widthArr[0] + "\" align=\"center\"><input type=\"checkBox\" name=\"titleCheck\" onclick=\"checkAll('titleCheck','" + this.checkObjName + "');\"></td>");
        } else if (this.columnArr[0].toLowerCase() == "radio") {
            hasChkObj = true;
            document.write("<td width=\"" + this.widthArr[0] + "\" align=\"center\"></td>");
        }
        var startPosition = 0;
        if(hasChkObj){
            startPosition = 1;
        }
        for (var i = startPosition; i < this.columnArr.length; i++) {
            var tdStrProp = "<td width=\"" + this.widthArr[i] + "\" align=\"center\"";
            if(this.sortTable != null && this.sortTable != undefined){
                tdStrProp += " onclick=\"do_SortTable('" + this.sortTable + ")\" style=\"cursor:pointer\"";
            }
            tdStrProp += ">";
            tdStrProp += this.columnArr[i];
            tdStrProp += "</td>";
            document.write(tdStrProp);
        }
        document.write("</tr></table></div>");
    };
}

function do_SortTable(tabId){
    alert("���Ͽ���");
}