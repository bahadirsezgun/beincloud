ptBossApp.factory("commonService",function($rootScope){
	var sharedService={};
	sharedService.loaderValue="true";
	sharedService.search;
	sharedService.searchBoxPH;
	
	$rootScope.ptGlobal=null;
	$rootScope.user=null;
	
	sharedService.setPtGlobal=function(global){
		
		$rootScope.ptGlobal=new Object();
		$rootScope.ptGlobal.ptTz=global.ptTz;
		$rootScope.ptGlobal.ptCurrency=global.ptCurrency;
		$rootScope.ptGlobal.ptStaticIp=global.ptStaticIp;
		$rootScope.ptGlobal.ptLang=(global.ptLang).substring(0,2);
		$rootScope.ptGlobal.ptDateFormat=global.ptScrDateFormat;
	}
	
	sharedService.getPtGlobal=function(){
		if(sharedService.ptGlobal==null){
			return null;
		}else{
			return $rootScope.ptGlobal;
		}
	}
	
	sharedService.setUser=function(user){
		$rootScope.user=user;
	}
	
	
		
	
	
	sharedService.modal=function(lval){
		this.loaderValue=lval;
		this.broadCastItem();
	};
	
	sharedService.broadCastItem = function(){
		$rootScope.$broadcast("handlebroadcast");
	};
	
	sharedService.searchItem = function(){
		$rootScope.$broadcast("search");
	};
	
	sharedService.searchBoxPHItem =function(){
		$rootScope.$broadcast("searchBoxPH");
	};
	
	sharedService.helpItem = function(){
		$rootScope.$broadcast("help");
	};

	sharedService.pageName;
	sharedService.pageComment;
	sharedService.normalHeaderVisible=false;
	
	sharedService.setNormalHeader = function(){
		$rootScope.$broadcast("setNormalHeader");
	};
	
	sharedService.changeLang = function(lang){
		$rootScope.lang=lang;
		$rootScope.$broadcast("changeLang");
	};
	
	return sharedService;
});