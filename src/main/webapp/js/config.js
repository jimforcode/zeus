/**
 * Copyright 2016 Zeus, All Rights Reserved
 * config.js
 * 
 */

function configState($stateProvider, $urlRouterProvider, $compileProvider) {

	// Optimize load start with remove binding information inside the DOM element
	$compileProvider.debugInfoEnabled(true);

	// Set default state
	$urlRouterProvider.otherwise('/login');
	$stateProvider
		.state('startup', {
			url: '/?p',
			templateUrl: 'views/common/startup.html',
			data: {
				pageTitle: 'Startup'
			},
			controller: 'startupCtrl'
		})
		.state('login', {
			url: '/login',
			templateUrl: 'views/login.html',
			data: {
				pageTitle: 'login'
			},
			controller: 'loginCtrl'
		})
		.state('app', {
			abstract: true,
			url: '/app',
			templateUrl: 'views/common/content.html',
			data: {
				pageTitle: 'app'
			},
			controller: 'contentCtrl'
		})		
		.state('app.menu', {
			abstract: true,
			url: '/menu',
			templateUrl: 'views/menu.html',
			data: {
				pageTitle: 'menu'
			},
			controller: 'menuCtrl'
		})
		.state('app.menu.map', {
			url: '/map',
			templateUrl: 'views/map.html',
			data: {
				pageTitle: 'map'
			},
			controller: 'mapCtrl'
		})
		.state('app.menu.jtopo', {
			url: '/jtopo',
			templateUrl: 'views/jtopo.html',
			data: {
				pageTitle: 'jtopo'
			},
			controller: 'jtopoCtrl'
		})
		.state('app.menu.init', {
			url: '/init',
			templateUrl: 'views/init.html',
			params: {
				selectedIndex: 0,
			},
			data: {
				pageTitle: 'init'
			},
			controller: 'initCtrl'
		})
		.state('app.menu.host', {
			url: '/host',
			templateUrl: 'views/host.html',
			params: {
				selectedIndex: 0,
			},
			data: {
				pageTitle: 'host'
			},
			controller: 'hostCtrl'
		})
		.state('app.menu.network', {
			url: '/network',
			templateUrl: 'views/network.html',
			params: {
				selectedIndex: 0,
			},
			data: {
				pageTitle: 'network'
			},
			controller: 'networkCtrl'
		})
		.state('app.menu.database', {
			url: '/database',
			templateUrl: 'views/database.html',
			params: {
				selectedIndex: 0,
			},
			data: {
				pageTitle: 'database'
			},
			controller: 'databaseCtrl'
		})
		.state('app.menu.web', {
			url: '/web',
			templateUrl: 'views/web.html',
			params: {
				selectedIndex: 0,
			},
			data: {
				pageTitle: 'web'
			},
			controller: 'dwebCtrl'
		})
		.state('app.menu.view', {
			url: '/view',
			templateUrl: 'views/view.html',
			params: {
				ipAddress: "",
			},
			data: {
				pageTitle: 'view'
			},
			controller: 'viewCtrl'
		})
		.state('app.menu.log', {
			url: '/log',
			templateUrl: 'views/log.html',
			params: {
				selectedIndex: 0,
			},
			data: {
				pageTitle: 'log'
			},
			controller: 'logCtrl'
		})
		.state('app.menu.pushlog', {
			url: '/pushlog',
			templateUrl: 'views/pushlog.html',
			params: {
				selectedIndex: 0,
			},
			data: {
				pageTitle: 'pushlog'
			},
			controller: 'pushlogCtrl'
		})
		.state('app.menu.basic', {
			url: '/basic',
			templateUrl: 'views/basic.html',
			params: {
				selectedIndex: 0,
			},
			data: {
				pageTitle: 'basic'
			},
			controller: 'basicCtrl'
		})
		.state('app.menu.user', {
			url: '/user',
			templateUrl: 'views/user.html',
			params: {
				selectedIndex: 0,
			},
			data: {
				pageTitle: 'user'
			},
			controller: 'userCtrl'
		});
}

function configHttp($httpProvider) {
	$httpProvider.defaults.headers.common['Client-Agent'] = 'GIS/1.0.0 (AngularJS ' + angular.version.full + ')';
}

angular
	.module('ZeusApp')
	.config(configState)
	.config(configHttp)
	.run([
		'$rootScope',
		'$state',
		'$window',
		'appConstant',
		function($rootScope, $state, $window, appConstant) {
			$rootScope.$$id = 'rootScope';

			$rootScope.doingPromise = null;

			$rootScope.$state = $state;

			$rootScope.appConstant = appConstant;

			//$rootScope.$on('$stateChangeSuccess', function(event, toState, toParams, fromState, fromParams) {
			//  try {
			//    if(toState.name !== 'login' && toState.name !== 'startup') {
			//      if (!window.$rootScope || !window.$rootScope.loginUser) {
			//        $window.location.replace($window.location.pathname);
			//      }
			//    }
			//  } catch(e) {
			//  }
			//});

			$window.$rootScope = $rootScope;
		}
	]);