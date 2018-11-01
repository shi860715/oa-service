$(function(){
    	   $('#datagrid').datagrid({  
    		   fit:true,
    		    url:'/sys/user/users',    
    		   loadMsg : '正在准备数据，请稍后。。。。。。',
			    striped : true,//斑马线效果
			    fitColumns : true,
			    pagination : true,
			    pageNumber : 1,
				pageSize :5,
				queryParams:{

					},
				pageList : [ 5, 10, 20 ],
   		   
   		    
   		    columns:[[  
   		    	{field : 'xyz',checkbox : true,width : 100,align : 'center'},
   		        {field:'userNo',title:'员工编号',width:100,editor:{
   		        	type:'validatebox'
   		        }},    
   		        {field:'userName',title:'姓名',width:100,editor:{
   		        	type:'validatebox'
   		        }},  
   		        {field:'sex',title:'性别',width:100,formatter:function(value){
   		        	  if(value=="1"){
   		        		  return '男';
   		        	  }else{
   		        		return '女'; 
   		        	  }
   		        },
   		        editor:{
   		        	type:'combobox',
   		        	options:{
   		        		valueField:'label',
   	   		        	textField:'text',
   	   		        	data:[{
   	   		        		label:'0',
   	   		        		text:'女'
   	   		        	},{
   	   		        		label:'1',
   	   		        		text:'男'
   	   		        	}]
   		        	}
   		        }
   		        }, 
   		        {field:'email',title:'邮箱',width:100,
   		        editor:{
   		        	type:'validatebox'
   		        		}
   		             }
   		        , 
   		        {field:'brith',title:'生日',width:100,align:'center',
   		        	editor:{
   		        		type:'datebox'
   		        		
   		        	}	
   		        
   		        
   		        },
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
   		   
   		    ]],
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
   		 toolbar:[{text : "检索：<input type='text' id='ss' />"}, 
				{iconCls : 'icon-add',text : '添加用户',	handler : function() {insert();}}
			, '-',
			{iconCls : 'icon-edit',text : '更换部门',	handler : function() {
				var rows =$('#datagrid').datagrid('getSelections');
				if(rows.length<1){
		               parent.$.messager.alert('提示',"请至少选中一个用户，更换部门");
		                return;
					}
			        $("#dialogDept").dialog('open');
			}}
			, '-',
			{iconCls : 'icon-edit',text : '授予角色',	handler : function() {
				
				var rows =$('#datagrid').datagrid('getSelections');
				if(rows.length<1){
		               parent.$.messager.alert('提示',"请至少选中一个用户，授予角色");
		                return;
					}
			        $("#dialogRole").dialog('open');
			}}
			, '-',
        {iconCls : 'icon-remove',text : '批量删除',	handler : function() {	
                     removeAccountBath();

	           } } , '-',
        {iconCls : 'icon-save',text : '保存',	handler : function() {	} }]
   		     
   		});  
    	   
    	/*查询框  start */  
	   $("#ss").searchbox({
			searcher : function(value, name) {
				var node =$("#tree").tree('getSelected');
				console.log("==="+node);
			
				$('#datagrid').datagrid('load', {query : value,id:node.id});
			},
			prompt : '查询关键字'
		});   
	   /*查询框  end */  
	   
	   /*树形结构      start */  
    	$("#tree").tree({
    		url:'/sys/dept/getTree',
    		idFiled : 'id',
			textFiled : 'text',
			parentField : 'parentId',
			onLoadSuccess:function(node,data){
				var n= $("#tree").tree("find",1);
				 if(n!=null){   
		                $("#tree").tree("select",n.target);    //相当于默认点击了一下第一个节点，执行onSelect方法   
		           }
		        
				

				},
				onSelect : function(node) {
				
					
					$("#datagrid").datagrid('reload',{
						    id : node.id,
							query:$("#ss").searchbox('getValue')
					});

				}
    		
    	});   
    	   
    	/*树形结构      end */     
    	   
    	   
    	   
    	   
    
    	
    	
    });

//编辑标识
var editRowIndex = 'undefined';

//获取id
function getdeptIds(node,ids) {
	ids.push(node.id);
	if (node.children) {
		for (var i = 0; i < node.children.length; i++) {
			getdeptIds(node.children[i], ids);
		}
	}
	return ids;
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
	
	var node =$("#tree").tree('getSelected');
	row.deptId=node.id;
	
	$.ajax({
		type : 'post',
		url : '/sys/user/saveORupdate',
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
