/**
 * Copyright 2016 Zeus, All Rights Reserved
 * login.js
 * 
 */

'use strict';

angular
	.module('ZeusApp')
	.controller('loginCtrl', [
		'$scope',
		'$location',
		'$base64',
		loginCtrl
	]);

function loginCtrl($scope, $location, $base64) {
	$scope.$$id = 'login';

	$scope.closeError = function() {
		$scope.showError = false;
	};

	$scope.showError = false;
	$scope.errorMessage = '';

	$scope.user = {
		userId: '',
		password: ''
	};

	$scope.loginUser = {
		userName: ''
	};

	$scope.doLogin = function() {
		//		$.ajax({
		//			url: 'doLogin',
		//			data: {
		//				"userId": $scope.user.userId,
		//				"password": $scope.user.password
		//			},
		//			type: 'post',
		//			cache: false,
		//			dataType: "json",
		//			async: false,
		//			success: function(response) {
		//				if(response.commonResponse.code === 'I00001') {
		//					var pathname = window.location.pathname;
		//					window.location = pathname + '#/?p=' + $base64.encode(angular.toJson($scope.loginUser, false));
		//				} else {
		//					$scope.showError = true;
		//					$scope.errorMessage = "用户名或密码不正确。";
		//				}
		//			},
		//			error: function(e) {
		//				$scope.showError = true;
		//				$scope.errorMessage = "用户名或密码不正确。";
		//			}
		//		});

		//		$.ajax({
		//			url:'http://139.196.50.175:8080/zeus/memory/cpuInfo',
		//			type:'get',
		//			dataType: "json",
		//			async: false,
		//			success: function(response) {
		//				console.log(response);
		//			},
		//			error: function(e) {
		//				
		//			}
		//		})
		var pathname = window.location.pathname;
		$scope.loginUser.userName = $scope.user.userId;
		window.location = pathname + '#/?p=' + $base64.encode(angular.toJson($scope.loginUser, false));
	};
};