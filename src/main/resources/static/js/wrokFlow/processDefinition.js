$(function(){
    	   $('#datagrid').datagrid({  
    		    fit:true,
    		    url:'/sys/wrokFlow/roles',    
    		    loadMsg : '正在准备数据，请稍后。。。。。。',
    		    singleSelect:true,
			    striped : true,//斑马线效果
			    fitColumns : true,
			    pagination : true,
			    pageNumber : 1,
				pageSize :20,
				queryParams:{

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
	   
	   
	   parent.$("#menu_dialog").dialog({
		   
		   title: '分配资源',    
		    width: 1200,    
		    height: 800,    
		    closed: true,    
		    cache: false,    
		    modal: true,
		    content:createContent('menuTree','menuTreeComm'),
		    toolbar:[{
					text:'保存',
					iconCls:'icon-save',
					handler:function(){
						updateRoleMenu();
					}
				}]
		   
	   });
	   

	
	   
	   
	   
	 
    });

//编辑标识
var editRowIndex = 'undefined';

function createContent(name,url) {
    var strHtml = '<iframe name="'+name+'" src="' + url + '" scrolling="no" frameborder="0" width="100%" height="100%"></iframe>';
    return strHtml;
}



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
	
	var nodes = parent.frames['menuTree'].$("#menu_tree").tree('getChecked');
	

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
			
			 parent.$("#menu_dialog").dialog('close');
			 
			$.messager.show({
				title : '提示消息',
				msg : data.message,
				timeout : 5000,
				showType : 'slide'
			});
		
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
function checkInfoByRoleId(){
	
	var rows=$('#datagrid').datagrid('getSelections');
	console.log(rows);
	if(rows.length==1){
		var obj = {};
		var roleId = rows[0].roleId;
		
		
		obj.roleId=roleId;
		
		$.ajax({
			type : 'post',
			url : '/sys/role/checkInfoByRoleId',
			data : JSON.stringify(obj),
			contentType : 'application/json;charset=UTF-8',
			success : function(data) {
				
				
			var checkdnodes= data.ids;
			
			
			var nodes =parent.frames['menuTree'].$("#menu_tree").tree('getChecked');
			nodes.forEach(function(item,index){
				
				parent.frames['menuTree'].$("#menu_tree").tree('uncheck',item.target);
				
				
			});
			
			checkdnodes.forEach(function(item,index){
				
	    	var node =parent.frames['menuTree'].$("#menu_tree").tree('find',item);
	    	parent.frames['menuTree'].$("#menu_tree").tree('check',node.target);
				
				
				
			});	
				
			}
		});
		
	}else{
		
		parent.$.messager.alert('警告','只能选中一条记录进行修改');  
		return;
		
	}
	
	
	
	
	
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
	          {iconCls : 'icon-add',text : '添加角色',handler : function() {insert();}},
	          {iconCls : 'icon-edit',text : '授予资源',handler : function() {
	        	  checkInfoByRoleId();
	        	  parent.$("#menu_dialog").dialog('open');
	        	  
	        	  
	        	  
	          }}]


