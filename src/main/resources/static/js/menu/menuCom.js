$(function(){
	
	   $("#menu_tree").tree({
		    url:'/sys/menu/getMenuTree',
		       cascadeCheck: true,
			idFiled : 'id',
			textFiled : 'text',
			parentField : 'parentId',
			checkbox:true,
			onlyLeafCheck:true,
			animate:true
		   	   
		}); 
	
	
});