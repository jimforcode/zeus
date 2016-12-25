/**
 * Copyright 2016 Zeus, All Rights Reserved
 * orderManger.js
 * 
 */

'use strict';

angular.module('ZeusApp').controller(
	'hostCtrl', ['$scope', '$state', '$stateParams', 'appConstant', hostCtrl
	]);

function hostCtrl($scope, $state, $stateParams, appConstant) {
	$scope.view = function() {
		$state.go('app.menu.view');
	};
	
	// 当前页
	$scope.currentPage = 1;
	// 分页下标最大10
	$scope.maxSize = 10;
	// 每页显示数
	$scope.itemsPerPage = 10;
	
	$scope.init = {
		searchValue: '',
		typeSort: 0,// 0：不排序   1： 升序    2：降序
		cpuSort: 0, // 0：不排序   1： 升序    2：降序
		memorySort: 0 // 0：不排序   1： 升序    2：降序
	};
	
	/**
	 * 根据level等级排序
	 */
	$scope.typeSort = function() {
		var sort = $scope.init.typeSort + 1;
		$scope.init.typeSort = sort % 3;
		$scope.reFilterServers();
	};
	
	$scope.cpuSort = function() {
		var sort = $scope.init.cpuSort + 1;
		$scope.init.cpuSort = sort % 3;
		$scope.reFilterServers();
	};
	
	$scope.memorySort = function() {
		var sort = $scope.init.memorySort + 1;
		$scope.init.memorySort = sort % 3;
		$scope.reFilterServers();
	};
	
	$scope.reFilterServers = function() {
		$scope._servers=$scope.servers;
		
		var begin = (($scope.currentPage - 1) * $scope.itemsPerPage),
			end = begin + $scope.itemsPerPage;

		if($scope.init.typeSort == 1) {
			$scope._servers = _.orderBy($scope._servers, 'type');
		} else if($scope.init.typeSort == 2) {
			$scope._servers = _.orderBy($scope._servers, 'type', 'desc');
		}
		
		if($scope.init.cpuSort == 1) {
			$scope._servers = _.orderBy($scope._servers, 'cpu');
		} else if($scope.init.cpuSort == 2) {
			$scope._servers = _.orderBy($scope._servers, 'cpu', 'desc');
		}
		
		if($scope.init.memorySort == 1) {
			$scope._servers = _.orderBy($scope._servers, 'memory');
		} else if($scope.init.memorySort == 2) {
			$scope._servers = _.orderBy($scope._servers, 'memory', 'desc');
		}

		// 查询过滤
		$scope._servers = _.filter($scope._servers, function(o) {
			var isRet = true;
			var re = /\s* \s*|\s*　\s*/;
			var allTextAttay = $scope.init.searchValue.split(re);
			for(var i = 0; i < allTextAttay.length; i++) {
				if(isRet && !(o.kindName.indexOf(allTextAttay[i]) >= 0 ||
						o.ip.indexOf(allTextAttay[i]) >= 0 ||
						o.type.indexOf(allTextAttay[i]) >= 0 ||
						o.cpu.indexOf(allTextAttay[i]) >= 0 ||
						o.memory.indexOf(allTextAttay[i]) >= 0 ||
						o.field.indexOf(allTextAttay[i]) >= 0)) {
					isRet = false;
					break;
				}
			}
			if(isRet) {
				return o;
			}
		});

		$scope.filteredService = $scope._servers.slice(begin, end);
	};

	$scope.$watch('currentPage + itemsPerPage + init.searchValue', function() {
		$scope.reFilterServers();
	});
	
	(function() {
		$.ajax({
			url: appConstant.BaseUrl + 'hostInfo.json',
			type: 'get',
			dataType: "json",
			success: function(response) {
				console.log(response.data);
				$scope.servers = response.data;
				$scope._servers = $scope.servers;
				$scope.reFilterServers();
			},
			error: function(e) {
				
			}
		})

	})();


}