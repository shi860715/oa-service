$(function(){
	
	
	  $('#datagrid').datagrid({  
		    fit:true,
		    url:'/sys/leave/leavesByUserId',    
		    loadMsg : '正在准备数据，请稍后。。。。。。',
		    singleSelect:true,
		    striped : true,//斑马线效果
		    fitColumns : true,
		    pagination : true,
		    pageNumber : 1,
			pageSize :20,
			pageList : [ 20, 30, 50 ],
			queryParams:{query:""},
		    columns:columns,
		    toolbar:toolbars,
		    
		    onDblClickRow : function(index, row) {
			$(this).datagrid('beginEdit', index);
		},
		onBeforeEdit : function(index, row) {
			row.edit = true;
			updateActions(index);
			refreshRowActions(index);
		},
		onAfterEdit : function(index, row) {
			row.edit = false;
			updateActions(index);
			refreshRowActions(index);
		},
		onCancelEdit : function(index, row) {
			row.edit = false;
			refreshRowActions(index);
			updateActions(index);
		},
		onDblClickRow : function(index, row) {
			if (editRowIndex == 'undefined') {
				$(this).datagrid('beginEdit', index);
			} else {
				$(this).datagrid('endEdit', editRowIndex);
				$(this).datagrid('beginEdit', index);
			}
			var edFlag = $(this).datagrid('getEditor', {
				index : index,
				field : 'flag'
			});
			editRowIndex = index;
		}
		
		     
		});  
	   
	/*查询框  start */  
 $("#ss").searchbox({
		searcher : function(value, name) {
			
		
			$('#datagrid').datagrid('load', {query : value});
		},
		prompt : '查询关键字'
	});   
 /*查询框  end */  
 



 
 
 

});

//编辑标识
var editRowIndex = 'undefined';




function insert() {
var row = $("#datagrid").datagrid('getSelected');
if (row) {
	var index = $("#datagrid").datagrid('getRowIndex', row);
} else {
	index = 0;
}
$('#datagrid').datagrid('insertRow', {index : index,row : {}});
$('#datagrid').datagrid('selectRow', index);
$('#datagrid').datagrid('beginEdit', index);
}


function refreshRowActions(index) {
$('#datagrid').datagrid('refreshRow', index);
}
/**
* 更新行
* @param index
* @returns
*/
function updateActions(index) {
$('#datagrid').datagrid('updateRow', {	index : index,row : {}});
}
/**
* 编辑行
* @param target
* @returns
*/
function editRow(target) {
if (editRowIndex == 'undefined') {
	$("#datagrid").datagrid('beginEdit', target);
} else {
	$("#datagrid").datagrid('endEdit', editRowIndex);
	$("#datagrid").datagrid('beginEdit', target);
}
editRowIndex = target;
}

/**
* 删除行
* @param index
* @returns
*/
function deleteRow(index) {
parent.$.messager.confirm('Confirm', 'Are you sure?', function(r) {
	if (r) {
		var rows = $('#datagrid').datagrid('getRows');
		var row = rows[index];
		deleteObject(row.roleId);
	}
});
}

/**
* 保存行
* @param index
* @returns
*/
function saveRow(index) {
$('#datagrid').datagrid('endEdit', index);
refreshRowActions(index);
var rows = $('#datagrid').datagrid('getRows');
var row = rows[index];
saveOrUpdateObject(row);
}

/**
* 退出保存
* @param index
* @returns
*/
function cancelRow(target) {
$('#datagrid').datagrid('cancelEdit', target);
}


//=================数据存储=========================

function saveOrUpdateObject(row){
	
$.ajax({
	type : 'post',
	url : '/sys/role/saveORupdate',
	data : JSON.stringify(row),
	contentType : 'application/json;charset=UTF-8',
	success : function(data) {
		$.messager.show({
			title : '提示消息',
			msg : data.message,
			timeout : 5000,
			showType : 'slide'
		});
		$("#datagrid").datagrid('clearSelections');
		$('#datagrid').datagrid('reload');
	}
});



}

/*删除角色*/
function deleteObject(editId) {

$.ajax({
	type : 'post',
	url : '/sys/role/delete',
	data : {
		"roleId" : editId
	},
	success : function(data) {
		
		if (data.code == 1) {
			parent.$.messager.alert('提示消息', data.message);
			$('#datagrid').datagrid('reload');
		}
	}
});

}










var columns = [[
{field:'leaveId',title:'请假单编号',checkbox:true,width:180},    
{field:'userName',title:'姓名',width:60,align:'center',editor:{
		type:'text'
		
	}}, 
	{field:'startTime',title:'开始时间',width:60,align:'center',editor:{
		type:'text'
		
	}},  
	{field:'endTime',title:'结束时间',width:60,align:'center',editor:{
		type:'text'
		
	}},  
	{field:'type',title:'请假类型',width:60,align:'center',editor:{
		type:'text'
		
	}}, 
	 
	{field:'reson',title:'请假原因',width:60,align:'center',editor:{
		type:'text'
		
	}}, 
	{field:'days',title:'天数',width:60,align:'center',editor:{
		type:'text'
		
	}}, 
	{field:'status',title:'状态',width:60,align:'center',formatter:function(value){
		if(value=='0'){
			return "待审核";
		}else{
			return "已完结";
		}
	},editor:{
		type:'text'
			
	}},
	{field:'remark',title:'备注',width:60,align:'center',editor:{
			type:'text'
			
		}},
	{field : 'action',title : '操作',width : 100,align : 'center',
		formatter : function(value, row, index) {
			if (row.edit) {
				var s = '<a href="#" onclick="saveRow('+ index + ')">保存</a>';
				var c = '<a href="#" onclick="cancelRow('+ index + ')">取消</a>';
				return s + '&nbsp&nbsp' + c;
			} else {
				var e = '<a href="#" onclick="editRow('+ index + ')">编辑</a>';
				var d = '<a href="#" onclick="deleteRow('+ index + ')">删除</a>';
				return e + '&nbsp&nbsp' + d;
			}
		}
	}
	]]


var toolbars =[{text : "检索：<input type='text' id='ss' />"}, 
        {iconCls : 'icon-add',text : '请假申请',handler : function() {insert();}},
        {iconCls : 'icon-edit',text : '授予资源',handler : function() {
      	  
      	  
      	
      	  
      	  
      	  
        }}]


