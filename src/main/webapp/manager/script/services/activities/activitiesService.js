/**
 * 活动加载组件
 */
(function(){
	angular.module("activities").factory('activitiesScrollService',['env','activitiesResource',function(env,activitiesResource){
		 var activitiesScroll={
			        	busy:false,
			        	times:0,
			        	items:[],
			        	pageNum:{pageNum:1},
			        	doScroll:function(){
			        		if(this.busy){return;};
			        		this.busy=true;
			        		
			        		console.log(this.times);
			        		this.times++;
			        		
			        		activitiesResource.activities_all.save(this.pageNum,function(res){
						    	for (var i = 0; i <res.data.length; i++) {
						    		res.data[i].photo="manager/images/actphoto/"+res.data[i].photo+".jpg";
                                    res.data[i].organization.icon="manager/images/icon/"+ res.data[i].organization.icon+".jpg";
							        this.items.push(res.data[i]);
							      };
						    	this.pageNum.pageNum++;
						    	if(res.page.totalNum!=this.items.length){
							    this.busy = false;};
			        		}.bind(this));
			        		console.log(this.busy);
			        }
			  };
			    
			  return activitiesScroll;
	}]);
	
	
	
	
}
)();