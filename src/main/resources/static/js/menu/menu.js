$(function(){
    	   $('#datagrid').treegrid({  
    		   fit:true,
    		    url:'/sys/menu/menus',    
    		   loadMsg : '正在准备数据，请稍后。。。。。。',
			    striped : true,//斑马线效果
			    fitColumns : true,
			    animate:true,
			   
			    idField:'menuId',    
			    treeField:'name',
			  
				queryParams:{

					},
   		   
   		    
   		    columns:columns,
   		   toolbar:toolbars,
			onBeforeEdit : function(row) {
				row.edit = true;
				refreshRowActions(row.menuId);
			},
			onAfterEdit : function(row, changes) {
				row.edit = false;
				refreshRowActions(row.menuId);
			},
			onCancelEdit : function(row) {
				row.edit = false;
				refreshRowActions(row.menuId);
			},
			onDblClickRow : function(row) {
				if (editRowIndex == 'undefined') {
					$(this).treegrid('beginEdit', row.menuId);
				} else {
					$(this).treegrid('endEdit', editRowIndex);
					$(this).treegrid('beginEdit', row.menuId);

				}
				editRowIndex=row.menuId;

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
			parent : node.menuId,
			data : [ {
				menuId : 0,
				name : '请填写资源名称',
				parentId : node.menuId
			} ]
		});
	} else {
		parent.$.messager.alert("提示", "请先选中添加的资源的父节点");
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
function deleteRow(target) {
	parent.$.messager.confirm('Confirm', 'Are you sure?', function(r) {
		if (r) {
			var row = $('#datagrid').treegrid('find',target);
			
			deleteObject(row.menuId);
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
		url : '/sys/menu/saveORupdate',
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
		url : '/sys/menu/delete',
		data : {
			"menuId" : editId
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
	
    {field:'menuId',title:'资源编号',checkbox:true,},    
    {field:'name',title:'资源名称',width:100,align:'left',editor : {
		type : 'validatebox',
		required : true
	}},    
    {field:'type',title:'类型',width:80,formatter:function(value,row,index){
    	if(value=='0'){
    		return '功能模块';
    	}else if(value=='1'){
    		return '菜单';
    	}else if(value=='2'){
    		return '按钮';
    	}else{
    		return '未知';
    	}
    	
    },editor : {
		type : 'validatebox',
		required : true
	}},    
    {field:'url',title:'访问路径',width:80,editor : {
		type : 'validatebox',
		required : true
	}}  ,
	 {field:'sort',title:'排序',width:80,editor : {
			type : 'validatebox',
			required : true
		}}  ,
	 {field:'icon',title:'图标',width:80,editor : {
			type : 'validatebox',
			required : true
		}}  ,
	 {field:'target',title:'打开方式',width:80,editor : {
			type : 'validatebox',
			required : true
		}}  ,
    {field : 'action',title : '操作',width : 100,align : 'center',
		formatter : function(value, row, index) {
			var editId = row.menuId;
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
    {iconCls : 'icon-add',text : '添加资源',handler : function() {insert();}}]



