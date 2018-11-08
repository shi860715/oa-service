$(function(){
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
	
	
    	   $('#datagrid').datagrid({  
    		   fit:true,
    		    url:'/sys/user/users',    
    		   loadMsg : '正在准备数据，请稍后。。。。。。',
			    striped : true,//斑马线效果
			    fitColumns : true,
			    pagination : true,
			    pageNumber : 1,
				pageSize :15,
				queryParams:{
                       id:1
					},
				pageList : [ 15, 20, 50 ],
   		   
   		    
   		    columns:cloumns,
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
   		 toolbar:toolbars
   		     
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
	   
	  
    	   
    	/*树形结构      end */     
    	   
    	
	   
	   $("#dept_dialog").dialog({
		    title: '调整部门',    
		    width: 400,    
		    height: 400,    
		    closed: true,    
		    cache: false,    
		    modal: true,
		    buttons:[{
				text:'保存',
				handler:function(){
			       
					updateUserDept();
				}
			},{
				text:'关闭',
				handler:function(){
					 $("#dept_dialog").dialog('close');
				}
			}]
		    
	   });
    	   
       $("#dept_tree").tree({
    	    url:'/sys/dept/getDeptTree',
    	    cascadeCheck: false,
			idFiled : 'id',
			textFiled : 'text',
			parentField : 'parentId',
			checkbox:true,
			onlyLeafCheck:true,
			animate:true,
			onLoadSuccess:function(node,data){
				  
			},
			onSelect:function(node){
				chekTreeSingle(node);
				
			}
			/*,
			onCheck:function(node){
				chekTreeSingle(node);
			}*/
    	   
       });	  
       
       
       
       parent.$("#dialog").dialog({
    	    title: '授予角色',    
		    width: 1500,    
		    height: 800,    
		    closed: true,    
		    cache: false,
		    content:createContent('roles','/roleComm'),
		    modal: true,
		   
       });
      
    	
    	
    });



//编辑标识
var editRowIndex = 'undefined';


function createContent(name,url) {
    var strHtml = '<iframe name="'+name+'" src="' + url + '" scrolling="no" frameborder="0" width="100%" height="100%"></iframe>';
    return strHtml;
}



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
			var rows = $('#datagrid').datagrid('getRows');
			var row = rows[index];
			deleteObject(row.userId);
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
	
	if(row.userId==null){
		var node =$("#tree").tree('getSelected');
		row.deptId=node.id;
		console.log(row);
	}

	
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
			$('#dept_dialog').dialog('close');
			var node = $('#dept_tree').tree('find', row.deptId);
              console.log(node);
			$('#dept_tree').tree('uncheck',node.target);
			
		}
	});

	
	
}

function deleteObject(editId) {

	$.ajax({
		type : 'post',
		url : '/sys/user/delete',
		data : {
			"userId" : editId
		},
		success : function(data) {
		
				parent.$.messager.alert('提示消息', data.msg);
				$('#datagrid').datagrid('reload');
			
		}
	});

}


function chekTreeSingle(node){
	 var cknodes = $('#dept_tree').tree("getChecked");
    for (var i = 0; i < cknodes.length; i++) {
        if (cknodes[i].id != node.id) {
       	 $('#dept_tree').tree("uncheck", cknodes[i].target);
        }
    }
    if (node.checked) {
   	 $('#dept_tree').tree('uncheck', node.target);

    } else {
   	 $('#dept_tree').tree('check', node.target);

    }

}


function updateUserDept(){
	 var cknodes=$("#dept_tree").tree('getChecked');
//	 console.log(cknodes);
	 var rows =$('#datagrid').datagrid('getSelections');
//	 console.log(rows[0]);
	 rows[0].deptId=cknodes[0].id;
	 saveOrUpdateObject(rows[0]);
	
}

function saveUserRoles(){
//	var com =parent.window.frames['roles'].$("#datagrid").datagrid('getSelections');
	var roleGridCom =parent.frames['roles'].$("#datagrid");
	var rolenodes =roleGridCom.datagrid("getSelections");
	var usernode= $("#datagrid").datagrid("getSelections");
	
	console.log(rolenodes);
	console.log(usernode);
	
	
}



var cloumns=[[  
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
       {field:'status',title:'状态',width:100,formatter:function(value){
		  if(value=="1"){
	   		  return '启用';
	   	  }else{
	   		return '禁用'; 
	   	  }
	  },
       editor:{
       	type:'validatebox'
       		}
            }
       , 
       {field:'email',title:'邮箱',width:100,
           editor:{
           	type:'validatebox'
           		}
                }
           , 
       {field:'brith',title:'生日',width:100,align:'center',
       	editor:{
       		type:'datebox',
       		options:{
       			formatter:myformatter,
       			parser:myparser
       		}
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
  
   ]];



var toolbars = [{text : "检索：<input type='text' id='ss' />"}, 
	{iconCls : 'icon-add',text : '添加用户',	handler : function() {insert();}}
	, '-',
	{iconCls : 'icon-edit',text : '更换部门',	handler : function() {
		var rows =$('#datagrid').datagrid('getSelections');
		if(rows.length<1){
	           parent.$.messager.alert('提示',"请至少选中一个用户，更换部门");
	            return;
			}
		   $("#dept_dialog").dialog('open');
		  var node= $("#dept_tree").tree('find',rows[0].deptId);
		  $("#dept_tree").tree('check',node.target);
		   
		   
	}}
	, '-',
	{iconCls : 'icon-edit',text : '授予角色',	handler : function() {
		
		var rows =$('#datagrid').datagrid('getSelections');
		if(rows.length<1){
	           parent.$.messager.alert('提示',"请至少选中一个用户，授予角色");
	            return;
			}
		parent.$("#dialog").window('open');
	        
	        
	}}
	, '-',
	{iconCls : 'icon-remove',text : '批量删除',	handler : function() {	
	         removeAccountBath();
	
	   } }
	, '-',
	{iconCls : 'icon-save',text : '保存',	handler : function() {	
		
	} }];

