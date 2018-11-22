$(function(){
    	   $('#datagrid').datagrid({  
    		    fit:true,
    		    url:'/sys/workFlow/tasks',    
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
   		    toolbar:toolbars
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


var columns = [[
	{field:'xyz',title:'全选',checkbox:true,width:30},    
	{field:'taskId',title:'任务ID',width:60,align:'center'},    
    {field:'name',title:'任务阶段',width:60,align:'center'},    
    {field:'executionId',title:'执行实例ID',width:60,align:'center'},
    {field:'processId',title:'流程实例',width:60,align:'center'},
	{field:'processDefId',title:'流程定义ID',width:60,align:'center'},
	{field:'definitionName',title:'流程类型',width:60,align:'center'},
	{field:'createTime',title:'创建时间',width:60,align:'center'},
    {field : 'action',title : '操作',width : 100,align : 'center',
		formatter : function(value, row, index) {
			var id=row.taskId;
			var ck='<a href="/sys/workFlow/detailTask?taskId='+id+'" target="_bank">查看详情</a>';
			return ck;
		}
	}
    ]]
	

var toolbars =[{text : "检索：<input type='text' id='ss' />"}]


