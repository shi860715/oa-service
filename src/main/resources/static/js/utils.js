

var easyuiPanelOnMove = function(left, top) {
	var parentObj = $(this).panel('panel').parent();
	if (left < 0) {
		$(this).window('move', {
			left : 1
		});
	}
	if (top < 0) {
		$(this).window('move', {
			top : 1
		});
	}
	var width = $(this).panel('options').width;
	var height = $(this).panel('options').height;
	var right = left + width;
	var buttom = top + height;
	var parentWidth = parentObj.width();
	var parentHeight = parentObj.height();
	if(parentObj.css("overflow")=="hidden"){
		if(left > parentWidth-width){
			$(this).window('move', {
				"left":parentWidth-width
			});
		}
		if(top > parentHeight-height){
			$(this).window('move', {
				"top":parentHeight-height
			});
		}
	}
};
$.fn.panel.defaults.onMove = easyuiPanelOnMove;
$.fn.window.defaults.onMove = easyuiPanelOnMove;
$.fn.dialog.defaults.onMove = easyuiPanelOnMove;


/*$.fn.datebox.defaults.formatter = function(date){
	var y = date.getFullYear();
	var m = date.getMonth()+1;
	var d = date.getDate();
	return y+'-' + m +'-'+d;
 }
*/


window.isEmpty = function(obj) {
	return (obj == null || typeof obj == "undefined" || obj.length == 0)
}
 
/**
 * 判断当前对象是否非空
 * @method isNotEmpty
 * @param {Object} obj
 * @return {Boolean}  
 */
window.isNotEmpty = function(obj) {
	return !isEmpty(obj);
}





var myformatter =function(date){
	var y = date.getFullYear();
	var m = date.getMonth()+1;
	var d = date.getDate();
	return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);

}
var myparser=function(s){
	if(!s) return new Date();
	var ss = (s.split('-'));
	console.log(ss);
    var y = parseInt(ss[0],10);
    var m = parseInt(ss[1],10);
    var d = parseInt(ss[2],10);
	console.log(d);
    if (!isNaN(y) && !isNaN(m) && !isNaN(d)){
        return new Date(y,m-1,d);
        
    } else {
        return new Date();
    }

	
}

var myformatterTime =function(date){
	var y = date.getFullYear();
	var m = date.getMonth()+1;
	var d = date.getDate();
	
	var hh = date.getHours();
	var mm = date.getMinutes();
	var ss = date.getSeconds();

	//如果是单个数，则前面补0
	m = m < 10 ? "0" + m : m;
	d = d < 10 ? "0" + d : d;
	hh = hh < 10 ? "0" + hh : hh;
	mm = mm < 10 ? "0" + mm : mm;
	ss = ss < 10 ? "0" + ss : ss;
	return y+'-'+m+'-'+d+' '+hh+':'+mm+':'+ss;

}
var myparserTime=function(s){
	if(!s) return new Date();
	console.log("==="+s);
	var ss = (s.trim().split(" "));
	
	var sss =ss[0].split("-");
	var hhh =ss[1].split(":");
	
	console.log(ss);
	
	
	
	
    var y = parseInt(sss[0],10);
    var m = parseInt(sss[1],10);
    var d = parseInt(sss[2],10);
    
    var hh = parseInt(hhh[0],10);
    var mm = parseInt(hhh[1],10);
    var dd = parseInt(hhh[2],10);
    
	console.log(d);
    if (!isNaN(y) && !isNaN(m) && !isNaN(d)&& !isNaN(hh)&& !isNaN(mm)&& !isNaN(dd)){
        return new Date(y,m-1,d,hh,mm,dd);
        
    } else {
        return new Date();
    }

	
}
	
function showTime() { 
	var today = new Date();
	//分别取出年、月、日、时、分、秒
	var year = today.getFullYear();
	var month = today.getMonth() + 1;
	var day = today.getDate();
	var hours = today.getHours();
	var minutes = today.getMinutes();
	var seconds = today.getSeconds();

	//如果是单个数，则前面补0
	month = month < 10 ? "0" + month : month;
	day = day < 10 ? "0" + day : day;
	hours = hours < 10 ? "0" + hours : hours;
	minutes = minutes < 10 ? "0" + minutes : minutes;
	seconds = seconds < 10 ? "0" + seconds : seconds;

	//构建要输出的字符串
	var str = year + "年" + month + "月" + day + "日 " + hours + ":" + minutes
			+ ":" + seconds;
	//获取id=result的对象
	var obj = document.getElementById("timer");
	//将str的内容写入到id=result的<div>中去
	obj.innerHTML = str;
	//延时器
	window.setTimeout("showTime()", 1000);

}


function createContent(name,url) {
    var strHtml = '<iframe name="'+name+'" src="' + url + '" scrolling="no" frameborder="0" width="100%" height="100%"></iframe>';
    return strHtml;
}

function getJsonObj(id,button) {
	var variables ={"button":button};
	var obj ={"id":id,"variables":variables};
	
	return obj;
	
}
