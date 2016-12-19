/**
 * Copyright 2016 Zeus, All Rights Reserved
 * basic.js
 * 
 */

'use strict';

angular.module('ZeusApp').controller(
	'basicCtrl', ['$scope', '$state', '$stateParams', 'appConstant', basicCtrl]);

function basicCtrl($scope, $state, $stateParams, appConstant) {

	// 当前日志过滤状态(2:ALL, 0:未解决， 1:解决)
	$scope.init = {
		searchValue: '',
		status: 2,
		levelSort: 0, // 0：不排序   1： 升序    2：降序
		timeSort: 0 // 0：不排序   1： 升序    2：降序
	};

	/**
	 * 根据level等级排序
	 */
	$scope.levelSort = function() {
		var sort = $scope.init.levelSort + 1;
		$scope.init.levelSort = sort % 3;
		$scope.reFilterLogs();
	};

	/**
	 * 根据时间排序
	 */
	$scope.timeSort = function() {
		var sort = $scope.init.timeSort + 1;
		$scope.init.timeSort = sort % 3;
		$scope.reFilterLogs();
	};

	$scope.changeStatus = function(status) {
		$scope.currentPage = 1;
		$scope.init.status = status;
		$scope.reFilterLogs();
	};

	// 当前页
	$scope.currentPage = 1;
	// 分页下标最大10
	$scope.maxSize = 10;
	// 每页显示数
	$scope.itemsPerPage = 10;

	$scope.$watch('currentPage + itemsPerPage', function() {
		$scope.reFilterLogs();
	});

	$scope.$watch('init.searchValue', function(searchValue) {
		$scope.reFilterLogs(searchValue);
	});

	$scope.reFilterLogs = function() {
		var begin = (($scope.currentPage - 1) * $scope.itemsPerPage),
			end = begin + $scope.itemsPerPage;

		if($scope.init.status !== 2) {
			$scope._logs = _.filter($scope.logs, {
				'status': $scope.init.status
			});
		} else {
			$scope._logs = $scope.logs;
		}

		if($scope.init.levelSort === 1) {
			$scope._logs = _.orderBy($scope._logs, 'level');
		} else if($scope.init.levelSort === 2) {
			$scope._logs = _.orderBy($scope._logs, 'level', 'desc');
		}

		if($scope.init.timeSort === 1) {
			$scope._logs = _.orderBy($scope._logs, 'datetime');
		} else if($scope.init.timeSort === 2) {
			$scope._logs = _.orderBy($scope._logs, 'datetime', 'desc');
		}

		// 查询过滤
		$scope._logs = _.filter($scope._logs, function(o) {
			var isRet = true;
			var re = /\s* \s*|\s*　\s*/;
			var allTextAttay = $scope.init.searchValue.split(re);
			for(var i = 0; i < allTextAttay.length; i++) {
				if(isRet && !(o.content.indexOf(allTextAttay[i]) >= 0 ||
						o.datetime.indexOf(allTextAttay[i]) >= 0 ||
						o.origin.indexOf(allTextAttay[i]) >= 0 ||
						o.ipAddress.indexOf(allTextAttay[i]) >= 0)) {
					isRet = false;
					break;
				}
			}
			if(isRet) {
				return o;
			}
		});

		$scope.filteredLogs = $scope._logs.slice(begin, end);
	};

	$scope.update = function(status) {
		var name = "";
		if(status === 0) {
			name = '未监控';
		} else {
			name = '已监控';
		}

		$.confirm({
			confirmButton: '是',
			cancelButton: '否',
			title: '',
			content: '请您确认当前告警是否修改为' + name + '。'
		});
	};

	(function() {
		$.ajax({
			url: appConstant.BaseUrl + 'BASICInfo.json',
			type: 'get',
			dataType: "json",
			success: function(response) {
				$scope.logs = response.data;
				$scope._logs = $scope.logs;
				$scope.reFilterLogs();

			},
			error: function(e) {}
		})

	})();
}