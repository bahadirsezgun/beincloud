ptBossApp.controller('New_PacketSaleUserFindController', function($rootScope,$scope,$http,$translate,parameterService,$location,homerService,commonService,globals) {
	
	$scope.filterName="";
	$scope.filterSurname="";
	
	$scope.noMember=false;
	$scope.searchSection=true;
	$scope.members;
	$scope.search;
	
	$scope.dateFormat;
	
	$scope.init = function(){
		$('.animate-panel').animatePanel();
		commonService.normalHeaderVisible=false;
		commonService.setNormalHeader();
		if(commonService.ptGlobal!=null){
			$scope.dateFormat=commonService.ptGlobal.ptDateFormat;
			$scope.dateTimeFormat=commonService.ptGlobal.ptDateTimeFormat;
		}else{
			findGlobals();
		}
		
	};
	
	$scope.$on("search",function(){
		$scope.search=commonService.search;
	});
	
	$scope.findKey=function(keyEvent){
		if (keyEvent.which === 13){
			$scope.find();
		}
	}
	
	$scope.find =function(){
		 var user=new Object();
		 user.userName=$scope.filterName;
		 user.userSurname=$scope.filterSurname;
		
		 $http({
			  method: 'POST',
			  url: "/bein/member/findByUsernameAndUsersurname",
			  data:angular.toJson(user)
			}).then(function successCallback(response) {
				$scope.members=response.data.resultObj;
				
				if($scope.members.length==0){
					$scope.noMember=true;
					$scope.searchSection=false;
				}else{
					$scope.noMember=false;
					$scope.searchSection=false;
				}
				
			}, function errorCallback(response) {
			    // called asynchronously if an error occurs
			    // or server returns response with an error status.
			});
		
	};
	
	
	$rootScope.$on("$routeChangeStart", function (event, next, current) {
		commonService.searchBoxPH="";
		commonService.searchBoxPHItem();
	});
	
	
	function findGlobals(){
		$http({
			method: 'POST',
  		    url: "/bein/global/getGlobals",
  		}).then(function successCallback(response) {
  			var res=response.resultObj;
  			if(res!=null){
  				
  				$scope.ptLang=(res.ptLang).substring(0,2);
  				
  				$translate.use($scope.ptLang);
				$translate.refresh;
				commonService.setPtGlobal(res);
				
				$scope.dateFormat=commonService.ptGlobal.ptDateFormat;
				$scope.dateTimeFormat=commonService.ptGlobal.ptDateTimeFormat;
  			}
  			
  		});
}
});