$(function(){
	
	
	$("#btn").click(function(){
		 var formData = new FormData($("#deployment")[0]);
		 console.log(formData);
		
		 $.ajax({
             url:"/sys/workFlow/addDeployment",
             type:"post",
             data:formData,
             processData:false,
             contentType:false,
             success:function(data){
            	 $.messager.alert('提示',data.message);    
            	 parent.$("#definition_dialog").dialog('close');
            	 parent.frames['流程管理'].$("#datagrid").datagrid('reload');
            	 
             },
             error:function(e){
                 alert("错误！！");
                 
             }
         });        
	});
	
	
	
	
});