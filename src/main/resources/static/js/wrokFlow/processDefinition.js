$(function(){
    	   $('#datagrid').datagrid({  
    		    fit:true,
    		    url:'/sys/workFlow/definitions',    
    		    loadMsg : '正在准备数据，请稍后。。。。。。',
    		    singleSelect:true,
			    striped : true,//斑马线效果
			    fitColumns : true,
			    pagination : true,
			    pageNumber : 1,
				pageSize :20,
				queryParams:{
                      query:""
					},
				pageList : [ 20, 30, 50 ],
   		   
   		    
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
	   
	   
	   parent.$("#definition_dialog").dialog({
		    title: '流程部署',    
		    width: 600,    
		    height: 400,    
		    closed: true,    
		    cache: false,    
		    modal: true,
		    content:createContent('defintion','defintionComm')
		   
	   });
	   

	
	   
	   
	   
	 
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
	parent.$.messager.confirm('删除提示', '您确定要删除现在的流程吗?', function(r) {
		if (r) {
			var rows = $('#datagrid').datagrid('getRows');
			var row = rows[index];
			deleteObject(row.deploymentId,false);
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


function deleteObject(editId,flag) {

	$.ajax({
		type : 'post',
		url : '/sys/workFlow/delete',
		data : {
			"deploymentId" : editId,
			"flag":flag
			
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
	{field:'id',title:'主键',checkbox:true,width:180},    
    {field:'name',title:'名称',width:60,align:'center',editor:{
			type:'text'
			
		}},    
    {field:'key',title:'key值',width:60,align:'center',editor:{
		type:'text'
			
	}},
    {field:'version',title:'版本',width:60,align:'center',editor:{
			type:'text'
			
	}},
	{field:'resourceName',title:'资源名',width:60,align:'center',formatter:function(value,row,index){
     var html ="<a href='/sys/workFlow/showDefinitionFile?deploymentId="+row.deploymentId+"' target='_blank' >查看流程文件</a>";
		
		return html;
	}},
	{field:'diagramResourceName',title:'资源图片',width:60,align:'center',formatter:function(value,row,index){
		var html ="<a href='/sys/workFlow/showDefinitionImage?deploymentId="+row.deploymentId+"' target='_blank' >查看流程图</a>";
		
		return html;
		
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
	          {iconCls : 'icon-add',text : '部署资源',handler : function() {
	        	  parent.$("#definition_dialog").dialog('open');
	        	  
	        	  
	          }},
	          {iconCls : 'icon-edit',text : '强制删除流程',handler : function() {
	        	
	        	  parent.$.messager.confirm('删除提示', '您确定要删除现在的流程吗?删除后流程相关数据将丢失', function(r) {
	        			if (r) {
	        				var rows = $('#datagrid').datagrid('getSelections');
	        				var row = rows[0];
	        				deleteObject(row.deploymentId,true);
	        			}
	        		});
	        	  
	        	  
	          }}]


