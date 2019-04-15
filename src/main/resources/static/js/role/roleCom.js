$(function(){
    	   $('#datagrid').datagrid({  
    		    fit:true,
    		    url:'/sys/role/roles',    
    		    loadMsg : '正在准备数据，请稍后。。。。。。',
    			idField:'roleId',
    		    singleSelect:false,
			    striped : true,//斑马线效果
			    fitColumns : true,
			    pagination : true,
			    pageNumber : 1,
				pageSize :20,
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
			},
			
   		
   		     
   		});  
    	   
       $("#role_search").searchbox({
    	   
    	   prompt:'请输入值' ,
    	   width:180,
    	   height:35,
    	   searcher:function(value,name){
    		   
    	   }

    	   
       });
	   
    	   
    	   
    	   
	   
	   $("#menu_dialog").dialog({
		   
		   title: '分配资源',    
		    width: 400,    
		    height: 400,    
		    closed: true,    
		    cache: false,    
		    modal: true,
		    buttons:[{
				text:'保存',
				handler:function(){
			       updateRoleMenu();
					
				}
			},{
				text:'关闭',
				handler:function(){
					 $("#menu_dialog").dialog('close');	
				}
			}]
		   
		   
	   });
	   

	   $("#menu_tree").tree({
		    url:'/sys/menu/getMenuTree',
		    cascadeCheck: true,
			idFiled : 'id',
			textFiled : 'text',
			parentField : 'parentId',
			checkbox:true,
   		  
			animate:true
		   	   
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

/*保存角色和资源之间的关系*/
function updateRoleMenu(){
	
	var nodes = $("#menu_tree").tree('getChecked');
//	获取菜单的id
	var ids = getMenuIds(nodes);
	var rows = $('#datagrid').datagrid('getSelections');
	

	
	var obj={};
	obj.roleId=rows[0].roleId;
	obj.ids=ids;
	
	$.ajax({
		type : 'post',
		url : '/sys/role/updateRoleMenu',
		data : JSON.stringify(obj),
		contentType : 'application/json;charset=UTF-8',
		success : function(data) {
			$.messager.show({
				title : '提示消息',
				msg : data.message,
				timeout : 5000,
				showType : 'slide'
			});
			 $("#menu_dialog").dialog('close');
			$("#datagrid").datagrid('clearSelections');
			$('#datagrid').datagrid('reload');
		}
	});

	
}


/*获取菜单id*/
function getMenuIds(nodes){
	var ids = new Array();
	nodes.forEach(function(item,index){
		
		ids.push(item.id);
		
	});
	
	return ids;
	
}

//  用于角色，菜单权限的返现
function checkInfoByRoleId(roleId){
	var obj = {};
	obj.roleId=roleId;
	
	$.ajax({
		type : 'post',
		url : '/sys/role/checkInfoByRoleId',
		data : JSON.stringify(obj),
		contentType : 'application/json;charset=UTF-8',
		success : function(data) {
			var nodes = $("#menu_tree").tree('getChecked');
			nodes.forEach(function(item){
				
				$("#menu_tree").tree("uncheck",item.target);
			});
			
			data.ids.forEach(function(item,index){
				 var node =  $("#menu_tree").tree("find",item);
				 $("#menu_tree").tree("check",node.target);
			});
			
			
			
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
	

var toolbars =[{text : '检索：<input type="text" id="role_search" />'},
	          {iconCls : 'icon-add',text : '添加角色',handler : function() {insert();}}]


