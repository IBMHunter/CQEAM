/**
 * @author mshtang
 */


/**************************************************���(�����)��Сдת��*******************************************/

/**
 * ���ܣ������ֽ��ת��Ϊ��д���Ľ�
 */
function toChineseCapital(num)
{
    if (isNaN(num) || num > Math.pow(10, 12))   return   ""
    var cn = "��Ҽ��������½��ƾ�"
    var unit = new Array("ʰ��Ǫ", "�ֽ�")
    var unit1 = new Array("����", "")
    var numArray = num.toString().split(".")
    var start = new Array(numArray[0].length - 1, 2)

    function toChinese(num, index)
    {
        var num = num.replace(/\d/g, function   ($1)
        {
            return   cn.charAt($1) + unit[index].charAt(start-- % 4 ? start % 4 : -1)
        })
        return   num
    }

    for (var i = 0; i < numArray.length; i++)
    {
        var tmp = ""
        for (var j = 0; j * 4 < numArray[i].length; j++)
        {
            var strIndex = numArray[i].length - (j + 1) * 4
            var str = numArray[i].substring(strIndex, strIndex + 4)
            var start = i ? 2 : str.length - 1
            var tmp1 = toChinese(str, i)
            tmp1 = tmp1.replace(/(��.)+/g, "��").replace(/��+$/, "")
            tmp1 = tmp1.replace(/^Ҽʰ/, "ʰ")
            tmp = (tmp1 + unit1[i].charAt(j - 1)) + tmp
        }
        numArray[i] = tmp
    }

    numArray[1] = numArray[1] ? numArray[1] : ""
    numArray[0] = numArray[0] ? numArray[0] + "Բ" : numArray[0], numArray[1].replace(/^��+/, "");
    numArray[1] = numArray[1].match(/��/) ? numArray[1] : numArray[1] + "��"
    return   numArray[0] + numArray[1]
}

function toNumberCase(num)
{
    var numArray = new Array()
    var unit = "����Բ$"
    for (var i = 0; i < unit.length; i++)
    {
        var re = eval("/" + (numArray[i - 1] ? unit.charAt(i - 1) : "") + "(.*)" + unit.charAt(i) + "/")
        if (num.match(re))
        {
            numArray[i] = num.match(re)[1].replace(/^ʰ/, "Ҽʰ")
            numArray[i] = numArray[i].replace(/[��Ҽ��������½��ƾ�]/g, function   ($1)
            {
                return   "��Ҽ��������½��ƾ�".indexOf($1)
            })
            numArray[i] = numArray[i].replace(/[�ֽ�ʰ��Ǫ]/g, function   ($1)
            {
                return   "*" + Math.pow(10, "�ֽ�   ʰ��Ǫ   ".indexOf($1) - 2) + "+"
            }).replace(/^\*|\+$/g, "").replace(/��/, "0")
            numArray[i] = "(" + numArray[i] + ")*" + Math.ceil(Math.pow(10, (2 - i) * 4))
        }
        else   numArray[i] = 0
    }
    return   eval(numArray.join("+"))
}
