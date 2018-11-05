

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





$.fn.datebox.defaults.formatter =function(date){
	var y = date.getFullYear();
	var m = date.getMonth()+1;
	var d = date.getDate();
	return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);

}

$.fn.datebox.defaults.myparser=function(s){
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


	


