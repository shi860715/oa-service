$(function(){
    	   $('#datagrid').treegrid({  
    		   fit:true,
    		    url:'/sys/dept/depts',    
    		   loadMsg : '正在准备数据，请稍后。。。。。。',
			    striped : true,//斑马线效果
			    fitColumns : true,
			    animate:true,
			   
			    idField:'deptId',    
			    treeField:'name',
			  
				queryParams:{

					},
   		   
   		    
   		    columns:columns,
   		   toolbar:toolbars,
			onBeforeEdit : function(row) {
				row.edit = true;
				refreshRowActions(row.deptId);
			},
			onAfterEdit : function(row, changes) {
				row.edit = false;
				refreshRowActions(row.deptId);
			},
			onCancelEdit : function(row) {
				row.edit = false;
				refreshRowActions(row.deptId);
			},
			onDblClickRow : function(row) {
				if (editRowIndex == 'undefined') {
					$(this).treegrid('beginEdit', row.deptId);
				} else {
					$(this).treegrid('endEdit', editRowIndex);
					$(this).treegrid('beginEdit', row.deptId);

				}
				editRowIndex=row.deptId;

			}
   		
   		     
   		});  
    	   
    	/*查询框  start */  
	   $("#ss").searchbox({
			searcher : function(value, name) {
				
			
				$('#datagrid').treegrid('load', {query : value});
			},
			prompt : '查询关键字'
		});   
	   /*查询框  end */  
	 
    });

//编辑标识
var editRowIndex = 'undefined';


function insert() {
	var node = $("#datagrid").treegrid('getSelected');
	if (node) {
		$("#datagrid").treegrid('append', {
			parent : node.deptId,
			data : [ {
				deptId : 0,
				deptName : '请填写部门名称',
				parentId : node.deptId
			} ]
		});
	} else {
		parent.$.messager.alert("提示", "请先选中添加的部门的父节点");
	}
	$('#datagrid').treegrid('beginEdit', 0);
}


function refreshRowActions(index) {
	$('#datagrid').treegrid('refreshRow', index);
}
/**
 * 更新行
 * @param index
 * @returns
 */
function updateActions(index) {
	$('#datagrid').treegrid('updateRow', {	index : index,row : {}});
}
/**
 * 编辑行
 * @param target
 * @returns
 */
function editRow(target) {
	if (editRowIndex == 'undefined') {
		$("#datagrid").treegrid('beginEdit', target);
	} else {
		$("#datagrid").treegrid('endEdit', editRowIndex);
		$("#datagrid").treegrid('beginEdit', target);
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
			deleteObject(index);
		}
	});
}

/**
 * 保存行
 * @param index
 * @returns
 */
function saveRow(target) {
	$('#datagrid').treegrid('endEdit', target);
	refreshRowActions(target);
	var row = $('#datagrid').treegrid('find',target);
	
	saveOrUpdateObject(row);
	editRowIndex = 'undefind';
}

/**
 * 退出保存
 * @param index
 * @returns
 */
function cancelRow(target) {
	$('#datagrid').treegrid('cancelEdit', target);
	editRowIndex = 'undefind';
}


//=================数据存储=========================

function saveOrUpdateObject(row){
	

	
	$.ajax({
		type : 'post',
		url : '/sys/dept/saveORupdate',
		data : JSON.stringify(row),
		contentType : 'application/json;charset=UTF-8',
		success : function(data) {
			$.messager.show({
				title : '提示消息',
				msg : data.message,
				timeout : 5000,
				showType : 'slide'
			});
			$("#datagrid").treegrid('clearSelections');
			$('#datagrid').treegrid('reload');
		}
	});

}

function deleteObject(editId) {

	$.ajax({
		type : 'post',
		url : '/sys/dept/delete',
		data : {
			"deptId" : editId
		},
		success : function(data) {
			console.log(data);
			if (data.code == 1) {
				parent.$.messager.alert('提示消息', data.message);
				$('#datagrid').treegrid('reload');
			}
		}
	});

}

var columns=[[    
	
    {field:'deptId',title:'部门编号',checkbox:true,width:180},    
    {field:'name',title:'部门名称',width:60,align:'left',editor : {
		type : 'validatebox',
		required : true
	}},    
    {field:'level',title:'类型',width:80,formatter:function(value,row,index){
    	if(value=='0'){
    		return '集团';
    	}else if(value=='1'){
    		return '一级公司';
    	}else if(value=='2'){
    		return '部门';
    	}else{
    		return '未知';
    	}
    	
    },editor : {
		type : 'validatebox',
		required : true
	}},    
    {field:'remark',title:'备注',width:80,editor : {
		type : 'validatebox',
		required : true
	}}  ,
    {field : 'action',title : '操作',width : 100,align : 'center',
		formatter : function(value, row, index) {
			var editId = row.deptId;
			if (row.edit) {
				var s = '<a href="#" onclick="saveRow('+ editId + ')">保存</a>';
				var c = '<a href="#" onclick="cancelRow('+ editId + ')">取消</a>';
				return s + '&nbsp&nbsp' + c;
			} else {
				var e = '<a href="#" onclick="editRow('+ editId + ')">编辑</a>';
				var d = '<a href="#" onclick="deleteRow('+ editId + ')">删除</a>';
				return e + '&nbsp&nbsp' + d;
			}
		}
	}
]]    

var toolbars=[{text : "检索：<input type='text' id='ss' />"}, 
    {iconCls : 'icon-add',text : '添加部门',handler : function() {insert();}}]



