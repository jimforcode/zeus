/**
 * Copyright 2016 Zeus, All Rights Reserved
 * main.js
 * 
 */

'use strict';

angular
	.module('ZeusApp')
	.controller('startupCtrl', [
		'$scope',
		'$rootScope',
		'$state',
		'$stateParams',
		'$base64',
		startupCtrl
	]);

function startupCtrl($scope, $rootScope, $state, $stateParams, $base64) {
	$scope.$$id = 'startup';

	if($stateParams.p) {
		$rootScope.loginUser = angular.fromJson($base64.decode($stateParams.p));
	}
	// 默认到订单管理
	$state.go('app.map');
}