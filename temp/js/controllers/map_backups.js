/**
 * Copyright 2016 Zeus, All Rights Reserved
 * map.js
 * 
 */

'use strict';

angular.module('ZeusApp').controller(
	'mapCtrl', ['$scope', '$state', '$stateParams', 'appConstant', mapCtrl]);

function mapCtrl($scope, $state, $stateParams, appConstant) {

	// 默认窗口样式
	var opts = {
		width: 300, // 信息窗口宽度
		height: 155, // 信息窗口高度
		title: "监控信息", // 信息窗口标题
		enableMessage: true //设置允许信息窗发送短息
	};

	$scope.addClickHandler = function(position, marker) {
		var content = '<div class="pop-content">';
		content += '<div style="background-color: rgba(0, 0, 0, 0) !important;">告警数 : ' + position.count + '</div>';
		content += '<div style="background-color: rgba(0, 0, 0, 0) !important;">地址 : ' + position.address + '</div>';
		content += '<div style="background-color: rgba(0, 0, 0, 0) !important;">电话 : ' + position.tel + '</div>';
		content += '<div class="row">';
		content += '<div class="col-lg-6 col-sm-12" style="background-color: rgba(0, 0, 0, 0) !important;">';
		content += '<button type="button" class="btn btn-primary btn-block">';
		content += '机房信息';
		content += '</button></div>';
		content += '<div class="col-lg-6 col-sm-12" style="background-color: rgba(0, 0, 0, 0) !important;">';
		content += '<a href="#/app/menu/init">'
		content += '<button type="button" class="btn btn-primary btn-block" >';
		content += '主页菜单';
		content += '</button></a></div>';
		content += '</div>';
		content += '</div>';

		marker.addEventListener("click", function(e) {
			$scope.openInfo(content, e)
		});
	}

	$scope.openInfo = function(content, e) {
		var p = e.target;
		var point = new BMap.Point(p.getPosition().lng, p.getPosition().lat);
		// 创建信息窗口对象 
		var infoWindow = new BMap.InfoWindow(content, opts);
		// 开启信息窗口
		$scope.map.openInfoWindow(infoWindow, point);
	}

	// count：告警数
	$scope.positions = [{
		lon: 103.996396357,
		lat: 36.7410733023,
		count: 15,
		address: "湖南省长沙市蔡锷北路233号",
		tel: "13073472318",
		name: "湖南中心"
	}, {
		lon: 116.404,
		lat: 39.915,
		count: 6,
		address: "北京市丰台区六里桥西南侧",
		tel: "18373472515",
		name: "北京中心"
	}, {
		lon: 120.585316,
		lat: 31.298886,
		count: 3,
		address: "江苏省苏州市工业园区创业产业园",
		tel: "13321312332",
		name: "苏州中心"
	}, {
		lon: 100.585316,
		lat: 27.098886,
		count: 8,
		address: "云南省丽江市",
		tel: "13321312332",
		name: "云南中心"
	}];

	$scope.defaultCenter = {
		lon: 104.996396357,
		lat: 34.7410733023
	};

	$scope.createMarks = function() {
		// 标记颜色
		var color = "";

		for(var i = 0; i < $scope.positions.length; i++) {
			// 中心点坐标
			var points = [new BMap.Point($scope.defaultCenter.lon, $scope.defaultCenter.lat)];

			var position = $scope.positions[i];
			var point = new BMap.Point(position.lon, position.lat);

			// mark图标
			var myIcon;
			if(position.count > 10) {
				myIcon = new BMap.Icon("img/mark_warning.png", new BMap.Size(46, 126));
			} else {
				myIcon = new BMap.Icon("img/mark_default.png", new BMap.Size(46, 126));
			}

			// 创建标注
			var marker = new BMap.Marker(point, {
				icon: myIcon
			});

			// 将标注添加到地图中
			$scope.map.addOverlay(marker);
			// 跳动的动画
			if(position.count > 10) {
				marker.setAnimation(BMAP_ANIMATION_BOUNCE);
				color = "rgb(255, 0, 0)";
			} else {
				color = "rgb(37, 122, 167)";
			}

			$scope.addClickHandler(position, marker);

			points.push(point);
			// 创建弧线对象
			var curve = new BMapLib.CurveLine(points, {
				strokeColor: color,
				strokeWeight: 3,
				strokeOpacity: 1
			});
			// 添加到地图中
			$scope.map.addOverlay(curve);

			// 添加Label
			// 创建文本标注对象
			var label = new BMap.Label(position.name, {
				position: point, // 指定文本标注所在的地理位置
				offset: new BMap.Size(-36, 5) //设置文本偏移量
			});
			label.setStyle({
				color: color,
				fontSize: "12px",
				height: "20px",
				lineHeight: "16px",
				minWidth: "72px",
				fontFamily: "微软雅黑",
				padding: "0px 4px",
				textAlign: "center",
				border: "1px solid " + color,
				fontWeight: "bold"
			});

			$scope.map.addOverlay(label);

		}

	};

	$scope.loadMap = function() {
		// 创建Map实例
		$scope.map = new BMap.Map("mainMap", {
			minZoom: 4
		});
		// 初始化地图,设置中心点坐标和地图级别
		$scope.map.centerAndZoom(
			new BMap.Point(
				$scope.defaultCenter.lon, $scope.defaultCenter.lat
			), 6
		);

		// 添加地图类型控件
		// $scope.map.addControl(new BMap.MapTypeControl());

		// 设置地图显示的城市
		$scope.map.setCurrentCity("");

		// 开启鼠标滚轮缩放
		$scope.map.enableScrollWheelZoom(true);

		// 地图主题 午夜蓝
		$scope.map.setMapStyle({
			style: 'midnight'
		});

		$scope.createMarks();

	};

	(function() {
		$scope.loadMap();
	})();

}