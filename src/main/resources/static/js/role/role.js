$(function(){
    	   $('#datagrid').datagrid({  
    		   fit:true,
    		    url:'/sys/role/roles',    
    		   loadMsg : '正在准备数据，请稍后。。。。。。',
			    striped : true,//斑马线效果
			    fitColumns : true,
			    pagination : true,
			    pageNumber : 1,
				pageSize :5,
				queryParams:{

					},
				pageList : [ 5, 10, 20 ],
   		   
   		    
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
			deleteAccount(index);
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
	saveOrUpdateAccount(row);
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

function saveOrUpdateAccount(row){
	

	
	$.ajax({
		type : 'post',
		url : '/sys/role/saveORupdate',
		data : JSON.stringify(row),
		contentType : 'application/json;charset=UTF-8',
		success : function(data) {
			$.messager.show({
				title : '提示消息',
				msg : data.msg,
				timeout : 5000,
				showType : 'slide'
			});
			$("#datagrid").datagrid('clearSelections');
			$('#datagrid').datagrid('reload');
		}
	});

	
	
}


var columns = [[
	{field:'roleId',title:'角色编号',checkbox:true,width:180},    
    {field:'name',title:'名称',width:60,align:'center',editor:{
			type:'text'
			
		}},    
    {field:'flag',title:'状态',width:60,align:'center',formatter:function(value){
    	if(value=='0'){
    		return "启用";
    	}else{
    		return "禁用";
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
	          {iconCls : 'icon-add',text : '添加角色',handler : function() {insert();}}]


