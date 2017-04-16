/**
 * 活动加载组件
 */
(function(){
	// 首页滑动加载活动
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
						    		// 字符串原型
                                    // ["10828.673269889174.jpg", "10866.96277089403203.jpg", "10888.77000360630373.jpg"]
                                    var a=res.data[i].photo;
                                    while(a.indexOf('"')!=-1){a=a.replace('"','');}
                                    a=a.replace(']','');
                                    a=a.replace('[','');
                                    a=a.split(',');
                                    if(a.length>0){
                                        res.data[i].photo=new Array();
                                        _.each(a,function (item) {
                                            item=item.trim();
                                            res.data[i].photo.push("/activity/getPhoto?photo="+item);
                                        })
                                    }else {
                                        res.data[i].photo=['/activity/getPhoto?photo=default.jpg'];
                                    }
                                    res.data[i].organization.icon='/user/getIcon?role=organization&icon='+ res.data[i].organization.icon;
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