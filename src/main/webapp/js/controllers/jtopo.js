/**
 * Copyright 2016 Zeus, All Rights Reserved
 * map.js
 * 
 */

'use strict';

angular.module('ZeusApp').controller(
	'jtopoCtrl', ['$scope', '$state', '$stateParams', 'appConstant', jtopoCtrl]);

function jtopoCtrl($scope, $state, $stateParams, appConstant) {

	var canvas = document.getElementById('canvas');
	var stage = new JTopo.Stage(canvas);
	var scene = new JTopo.Scene();

	var tcontainer = new JTopo.Container('科技大楼');
	var tgridLayout = JTopo.layout.GridLayout(8, 2);

	var icontainer = new JTopo.Container('传输隔离系统');
	var igridLayout = JTopo.layout.GridLayout(1, 3);

	var dcontainer = new JTopo.Container('检测防御系统');
	var dgridLayout = JTopo.layout.GridLayout(1, 3);

	tcontainer.layout = tgridLayout;
	tcontainer.setBound(80, 80, 180, 540);
	tcontainer.textPosition = 'Middle_Center';
	tcontainer.fontColor = '100,255,0';
	tcontainer.font = '18pt 微软雅黑';
	tcontainer.borderColor = '255,0,0';
	tcontainer.borderRadius = 10; // 圆角
	scene.add(tcontainer);

	icontainer.layout = igridLayout;
	icontainer.setBound(500, 80, 240, 120);
	icontainer.fontColor = '100,255,0';
	icontainer.font = '16pt 微软雅黑';
	icontainer.borderColor = '255,0,0';
	icontainer.borderRadius = 10; // 圆角
	scene.add(icontainer);

	dcontainer.layout = igridLayout;
	dcontainer.setBound(800, 460, 240, 120);
	dcontainer.fontColor = '100,255,0';
	dcontainer.font = '16pt 微软雅黑';
	dcontainer.borderColor = '255,0,0';
	dcontainer.borderRadius = 10; // 圆角
	scene.add(dcontainer);

	var add_container = function(container, obj, img) {
		$.each(obj, function(i, o) {
			var nodes = new JTopo.Node(o.kindName);
			nodes.setImage('./img/statistics/' + img, true);
			scene.add(nodes);
			container.add(nodes);
			if(o.ip != "") {
				nodes.click(function(event) {
					location.href = '/zeus/#/app/menu/view?hostName=' + o.ip;

					//					$state.go('app.menu.view', {
					//						"ipAddress": o.ip
					//					});
				});
			}
		});
	}

	function node(x, y, img, text) {
		var node = new JTopo.Node(text);
		node.setImage('./img/statistics/' + img, true);
		node.setLocation(x, y);
		scene.add(node);
		return node;
	}

	function linkNode(nodeA, nodeZ, f) {
		var link;
		if(f) {
			link = new JTopo.FoldLink(nodeA, nodeZ);
		} else {
			link = new JTopo.Link(nodeA, nodeZ);
		}
		link.direction = 'vertical';
		scene.add(link);
		return link;
	}

	var w1 = node(300, 290, 'switches.png', '交换机');
	linkNode(tcontainer, w1);

	linkNode(icontainer, w1, true);

	linkNode(icontainer, dcontainer, true);

	var w2 = node(1100, 240, 'internet.png', '英特网');
	linkNode(w2, dcontainer, true);
	stage.add(scene);

	(function() {

		$.ajax({
			url: appConstant.BaseUrl + 'service.json',
			type: 'get',
			dataType: "json",
			success: function(response) {
				add_container(tcontainer, response.data, "server.png");
			},
			error: function(e) {

			}
		})

		$.ajax({
			url: appConstant.BaseUrl + 'service2.json',
			type: 'get',
			dataType: "json",
			success: function(response) {
				add_container(dcontainer, response.data, "server.png");
			},
			error: function(e) {

			}
		})

		$.ajax({
			url: appConstant.BaseUrl + 'service3.json',
			type: 'get',
			dataType: "json",
			success: function(response) {
				add_container(icontainer, response.data, "server.png");
			},
			error: function(e) {

			}
		})
	})();

}