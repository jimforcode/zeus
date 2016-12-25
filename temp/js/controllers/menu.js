/**
 * Copyright 2016 Zeus, All Rights Reserved
 * menu.js
 * 
 */

'use strict';

angular.module('ZeusApp').controller(
	'menuCtrl', ['$scope', '$state', '$timeout', '$interval', menuCtrl]);

function menuCtrl($scope, $state, $timeout, $interval) {
	$scope.$$id = 'menu';

	$scope.menu = {
		mcount: 20,
		wcount: 5
	};

	$scope.logout = function() {
		$state.go('login');
	};

	$scope.items = [{
		name: '监控中心',
		listshow: false,
		iconfont: '',
		list: [{
			name: '主机',
			iconfont: 'icon icon-monitor',
			url: '#/app/menu/host'
		}, {
			name: '网络设备',
			iconfont: 'icon-feed',
			url: '#/app/menu/network'
		}, {
			name: '数据库',
			iconfont: 'icon-cloud',
			url: '#/app/menu/database'
		}, {
			name: 'Web应用',
			iconfont: 'icon-media-loop',
			url: '#/app/menu/web'
		}]
	}, {
		name: '管理中心',
		listshow: false,
		iconfont: '',
		list: [{
			name: '设备管理',
			iconfont: 'icon icon-microphone',
			url: '#',
			list: [{
				name: '基础设备',
				iconfont: 'entypo-tools',
				url: '#/app/menu/basic'
			}]
		}, {
			name: '告警管理',
			iconfont: 'icon icon-warning',
			url: '#',
			list: [{
				name: '告警日志',
				iconfont: 'entypo-book-open',
				url: '#/app/menu/log'
			}, {
				name: '消息推送',
				iconfont: 'entypo-newspaper',
				url: '#/app/menu/pushlog'
			}]
		}, {
			name: '用户管理',
			iconfont: 'icon icon-user',
			url: '#/app/menu/user'
		}]
	}];

	$("#skin-select #toggle").click(function() {
		if($(this).hasClass('active')) {
			$(this).removeClass('active')
			$('#skin-select').animate({
				left: 0
			}, 100);
			$('.wrap-fluid').css({
				"width": "auto",
				"margin-left": "250px"
			});
			$('.navbar').css({
				"margin-left": "240px"
			});
			$('#skin-select li').css({
				"text-align": "left"
			});
			$('#skin-select li span, ul.topnav h4, .side-dash, .noft-blue, .noft-purple-number, .noft-blue-number, .title-menu-left').css({
				"display": "inline-block",
				"float": "none"
			});
			// $('body').css({"padding-left":"250px"});
			$('.ul.topnav li a:hover').css({
				" background-color": "green!important"
			});
			$('.ul.topnav h4').css({
				"display": "none"
			});
			$('.tooltip-tip2').addClass('tooltipster-disable');
			$('.tooltip-tip').addClass('tooltipster-disable');
			$('.datepicker-wrap').css({
				"position": "absolute",
				"right": "300px"
			});
			$('.skin-part').css({
				"visibility": "visible"
			});
			$('#menu-showhide, .menu-left-nest').css({
				"margin": "10px"
			});
			$('.dark').css({
				"visibility": "visible"
			});
			$('.search-hover').css({
				"display": "none"
			});
			$('.dropdown-wrap').css({
				"position": "absolute",
				"left": "0px",
				"top": "53px"
			});
		} else {
			$(this).addClass('active');
			// $('#skin-select').animate({ left:-200 }, 100);
			$('#skin-select').animate({
				left: -200
			}, 100);
			$('.wrap-fluid').css({
				"width": "auto",
				"margin-left": "50px"
			});
			$('.navbar').css({
				"margin-left": "50px"
			});
			$('#skin-select li').css({
				"text-align": "right"
			});
			$('#skin-select li span, ul.topnav h4, .side-dash, .noft-blue, .noft-purple-number, .noft-blue-number, .title-menu-left').css({
				"display": "none"
			});
			// $('body').css({"padding-left":"50px"});
			$('.tooltip-tip2').removeClass('tooltipster-disable');
			$('.tooltip-tip').removeClass('tooltipster-disable');
			$('.datepicker-wrap').css({
				"position": "absolute",
				"right": "84px"
			});
			$('.skin-part').css({
				"visibility": "visible",
				"top": "3px"
			});
			$('.dark').css({
				"visibility": "hidden"
			});
			$('#menu-showhide, .menu-left-nest').css({
				"margin": "0"
			});
			$('.search-hover').css({
				"display": "block",
				"position": "absolute",
				"right": "-100px"
			});
			$('.dropdown-wrap').css({
				"position": "absolute",
				"left": "-10px",
				"top": "53px"
			});
		}
		return false;
	});

	$scope.skipInit = function() {
		var nt_title = $('#nt-title').newsTicker({
			row_height: 18,
			max_rows: 1,
			duration: 5000,
			pauseOnHover: 0
		});
		$(".topnav").accordionze({
			accordionze: true,
			speed: 500,
			closedSign: '<img src="img/plus.png">',
			openedSign: '<img src="img/minus.png">'
		});

		$('.tooltip-tip-x').tooltipster({
			position: 'right'

		});

		$('.tooltip-tip').tooltipster({
			position: 'right',
			animation: 'slide',
			theme: '.tooltipster-shadow',
			delay: 1,
			offsetX: '-12px',
			onlyOne: true

		});
		$('.tooltip-tip2').tooltipster({
			position: 'right',
			animation: 'slide',
			offsetX: '-12px',
			theme: '.tooltipster-shadow',
			onlyOne: true

		});
		$('.tooltip-top').tooltipster({
			position: 'top'
		});
		$('.tooltip-right').tooltipster({
			position: 'right'
		});
		$('.tooltip-left').tooltipster({
			position: 'left'
		});
		$('.tooltip-bottom').tooltipster({
			position: 'bottom'
		});
		$('.tooltip-reload').tooltipster({
			position: 'right',
			theme: '.tooltipster-white',
			animation: 'fade'
		});
		$('.tooltip-fullscreen').tooltipster({
			position: 'left',
			theme: '.tooltipster-white',
			animation: 'fade'
		});
	}
	setTimeout(function() {
		$("#skin-select #toggle").trigger('click');
		$scope.skipInit();
	}, 10);

	/**
	 * 设置日期, 时间
	 */
	$scope.getTime = function() {
		var newDate = new Date();
		var monthNames = ["1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月"];
		var dayNames = ["星期日, ", "星期一, ", "星期二, ", "星期三, ", "星期四, ", "星期五, ", "星期六, "]
		$('#Date').html(dayNames[newDate.getDay()] + ' ' + newDate.getFullYear() + '年' + monthNames[newDate.getMonth()] + newDate.getDate() + '日');

		$scope.hours = newDate.getHours();
		$scope.minutes = newDate.getMinutes();
		$scope.seconds = newDate.getSeconds();
	};

	(function() {
		// 设置日期, 时间
		$scope.getTime();
		$interval($scope.getTime,  1000);
		$interval(function() {
			$scope.menu.mcount = Math.round(Math.random() * 20);
			$scope.menu.wcount = Math.round(Math.random() * 20);
		},  5000);
	})();

}